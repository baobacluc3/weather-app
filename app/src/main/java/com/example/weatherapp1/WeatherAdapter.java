// WeatherAdapter.java
package com.example.weatherapp1 ;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private List<WeatherModel> weatherList;

    public WeatherAdapter(List<WeatherModel> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherModel weather = weatherList.get(position);
        holder.locationText.setText(weather.getLocation());
        holder.temperatureText.setText(String.format("%.1f°C", weather.getTemperature()));
        holder.humidityText.setText(String.format("Độ ẩm: %d%%", weather.getHumidity()));
        holder.descriptionText.setText(weather.getDescription());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationText, temperatureText, humidityText, descriptionText;

        public ViewHolder(View view) {
            super(view);
            locationText = view.findViewById(R.id.locationText);
            temperatureText = view.findViewById(R.id.temperatureText);
            humidityText = view.findViewById(R.id.humidityText);
            descriptionText = view.findViewById(R.id.descriptionText);
        }
    }
}