package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText cityName;
    Button search;
    TextView show;
    String url;

    class GetWeather extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null) {
                show.setText("Cannot find weather. Please try again.");
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject main = jsonObject.getJSONObject("main");
                String weatherInfo = "Temperature: " + main.getString("temp") + " °C\n"
                        + "Feels Like: " + main.getString("feels_like") + " °C\n"
                        + "Temperature Max: " + main.getString("temp_max") + " °C\n"
                        + "Temperature Min: " + main.getString("temp_min") + " °C\n"
                        + "Pressure: " + main.getString("pressure") + " hPa\n"
                        + "Humidity: " + main.getString("humidity") + " %";
                show.setText(weatherInfo);
            } catch (Exception e) {
                e.printStackTrace();
                show.setText("Error parsing weather data. Please try again.");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = findViewById(R.id.cityName);
        search = findViewById(R.id.search);
        show = findViewById(R.id.weather);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityName.getText().toString().trim();
                if (city.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter City", Toast.LENGTH_SHORT).show();
                    return;
                }
                url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=cf5c21c66a37395b0ae0ac256327d538";
                try {
                    GetWeather task = new GetWeather();
                    task.execute(url).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    show.setText("Error fetching weather data. Please try again.");
                }
            }
        });
    }
}