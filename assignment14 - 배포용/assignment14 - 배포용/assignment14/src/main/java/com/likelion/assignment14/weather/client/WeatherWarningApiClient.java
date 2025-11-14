package com.likelion.assignment14.weather.client;

import com.likelion.assignment14.weather.api.dto.response.WeatherWarningListApiResponse;
import com.likelion.assignment14.global.code.status.ErrorStatus;
import com.likelion.assignment14.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherWarningApiClient {

    private final RestTemplate restTemplate;

    @Value("${weather.api.base-url}")
    private String baseUrl;

    @Value("${weather.api.auth-key}")
    private String authKey;


    public String getWeatherEventsAsString(int pageNo, int numOfRows, String stnId, String fromTmFc, String toTmFc) {

        String url = buildUrl(pageNo, numOfRows, stnId, fromTmFc, toTmFc);
        log.info("▶ RAW XML URL = {}", url);

        try {
            return restTemplate.getForObject(new URI(url), String.class);
        } catch (Exception e) {
            log.error("기상특보 RAW XML 호출 실패", e);
            throw new GeneralException(ErrorStatus.WEATHER_API_ERROR);
        }
    }


    public WeatherWarningListApiResponse getWeatherEvents(int pageNo, int numOfRows,
                                                          String stnId, String fromTmFc, String toTmFc) {

        String url = buildUrl(pageNo, numOfRows, stnId, fromTmFc, toTmFc);
        log.info("▶ XML → DTO URL = {}", url);

        try {
            return restTemplate.getForObject(new URI(url), WeatherWarningListApiResponse.class);
        } catch (Exception e) {
            log.error("기상특보 DTO 매핑 실패", e);
            throw new GeneralException(ErrorStatus.WEATHER_API_ERROR);
        }
    }


    private String buildUrl(
            int pageNo, int numOfRows, String stnId, String fromTmFc, String toTmFc
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl)
                .append("/getWthrWrnList")
                .append("?serviceKey=").append(authKey)
                .append("&pageNo=").append(pageNo)
                .append("&numOfRows=").append(numOfRows)
                .append("&dataType=XML"); // Culture API처럼 명시적으로 XML 고정

        if (stnId != null && !stnId.isEmpty()) sb.append("&stnId=").append(stnId);
        if (fromTmFc != null && !fromTmFc.isEmpty()) sb.append("&fromTmFc=").append(fromTmFc);
        if (toTmFc != null && !toTmFc.isEmpty()) sb.append("&toTmFc=").append(toTmFc);

        return sb.toString();
    }
}
