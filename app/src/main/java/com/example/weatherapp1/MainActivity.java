// MainActivity.java
package com.example.weatherapp1;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private List<WeatherModel> weatherList;
    private ProgressBar progressBar;
    private TextView errorText;

    // API key từ OpenWeatherMap
    private static final String API_KEY = "659b0506191ec705c6521656ae603f26";

    // Danh sách các địa điểm cần lấy thời tiết
    private String[][] locations = {
            {"Sơn Trà", "16.1059", "108.2352"},
            {"Hải Châu", "16.0678", "108.2208"},
            {"Liên Chiểu", "16.0678", "108.1560"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        errorText = findViewById(R.id.errorText);

        weatherList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(weatherList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(weatherAdapter);

        // Lấy dữ liệu thời tiết cho từng địa điểm
        for (String[] location : locations) {
            getWeatherData(location[0], location[1], location[2]);
        }
    }

    private void getWeatherData(String locationName, String lat, String lon) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric&lang=vi",
                lat, lon, API_KEY);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            WeatherModel weatherModel = new WeatherModel();
                            weatherModel.setLocation(locationName);

                            JSONObject main = response.getJSONObject("main");
                            weatherModel.setTemperature(main.getDouble("temp"));
                            weatherModel.setHumidity(main.getInt("humidity"));

                            String description = response.getJSONArray("weather")
                                    .getJSONObject(0)
                                    .getString("description");
                            weatherModel.setDescription(description);

                            weatherList.add(weatherModel);
                            weatherAdapter.notifyDataSetChanged();

                            progressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            showError();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void showError() {
        progressBar.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
    }
}