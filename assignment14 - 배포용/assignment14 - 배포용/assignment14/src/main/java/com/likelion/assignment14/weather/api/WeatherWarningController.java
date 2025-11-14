package com.likelion.assignment14.weather.api;

import com.likelion.assignment14.weather.api.dto.response.WeatherWarningListApiResponse;
import com.likelion.assignment14.weather.application.WeatherWarningService;
import com.likelion.assignment14.global.code.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather-warnings")
@RequiredArgsConstructor
public class WeatherWarningController {

    private final WeatherWarningService weatherWarningService;

    @GetMapping("/raw")
    public ResponseEntity<String> getWeatherWarningRaw(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows,
            @RequestParam(required = false) String stnId,
            @RequestParam(required = false) String fromTmFc,
            @RequestParam(required = false) String toTmFc
    ) {
        String response = weatherWarningService.getWeatherWarningsRaw(pageNo, numOfRows, stnId, fromTmFc, toTmFc);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/from-api")
    public ResponseEntity<ApiResponse<WeatherWarningListApiResponse>> getWeatherWarningFromApi(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int numOfRows,
            @RequestParam(required = false) String stnId,
            @RequestParam(required = false) String fromTmFc,
            @RequestParam(required = false) String toTmFc
    ) {

        WeatherWarningListApiResponse response =
                weatherWarningService.getWeatherWarningsFromApi(pageNo, numOfRows, stnId, fromTmFc, toTmFc);

        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }
}
