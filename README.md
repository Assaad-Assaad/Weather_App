# 🌤️ Android Weather App

A simple Android application that allows users to check the current weather by entering the name of a city. It fetches real-time data from the **OpenWeatherMap API** and displays key weather metrics such as temperature, pressure, and humidity.

---

## 📱 App Overview

- 📍 Input a city name
- ☁️ Fetch live weather data from the OpenWeatherMap API
- 🌡️ Display temperature, pressure, humidity, and more
- ❌ Basic error handling for invalid city names or failed API requests

This is a **one-screen application** with a minimalist design, built using **Java**, **Gradle**, and the Android SDK.

---

## 🛠️ Tech Stack

- **Java**
- **Android SDK**
- **Gradle**
- **OpenWeatherMap API**
- **AsyncTask** (for background network calls)
- **HTTPURLConnection**
- **JSON Parsing**

---

## 🖥️ Screens

| Component | Description                      |
|-----------|----------------------------------|
| `EditText` | Input field for city name        |
| `Button`   | Triggers the API call            |
| `TextView` | Displays the weather information |

---

## 🔗 OpenWeatherMap API

- Endpoint used:  https://api.openweathermap.org/data/2.5/weather
