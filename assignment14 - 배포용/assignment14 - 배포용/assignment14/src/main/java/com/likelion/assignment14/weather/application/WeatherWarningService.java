package com.likelion.assignment14.weather.application;

import com.likelion.assignment14.weather.api.dto.response.WeatherWarningListApiResponse;
import com.likelion.assignment14.weather.client.WeatherWarningApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeatherWarningService {

    private final WeatherWarningApiClient weatherWarningApiClient;


    public String getWeatherWarningsRaw(int pageNo, int numOfRows, String stnId, String fromTmFc, String toTmFc) {
        return weatherWarningApiClient.getWeatherEventsAsString(pageNo, numOfRows, stnId, fromTmFc, toTmFc);
    }


    public WeatherWarningListApiResponse getWeatherWarningsFromApi(
            int pageNo, int numOfRows, String stnId, String fromTmFc, String toTmFc
    ) {
        return weatherWarningApiClient.getWeatherEvents(pageNo, numOfRows, stnId, fromTmFc, toTmFc);
    }
}
