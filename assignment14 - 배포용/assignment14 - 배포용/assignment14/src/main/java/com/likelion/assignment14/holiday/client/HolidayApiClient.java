package com.likelion.assignment14.holiday.client;

import com.likelion.assignment14.holiday.api.dto.response.HolidayInfoResponse;
import com.likelion.assignment14.global.code.status.ErrorStatus;
import com.likelion.assignment14.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Slf4j
@Component
@RequiredArgsConstructor
public class HolidayApiClient {

    private final RestTemplate restTemplate;

    @Value("${holiday.api.endpoint}")
    private String apiEndpoint;

    @Value("${holiday.api.service-key}")
    private String serviceKey;


    private static final int DEFAULT_PAGE_NO = 1;
    private static final int DEFAULT_NUM_OF_ROWS = 100;


    public HolidayInfoResponse requestMonthlyHolidays(int year, int month) {
        String requestUrl = buildApiUrl(year, month, DEFAULT_PAGE_NO, DEFAULT_NUM_OF_ROWS);
        log.debug("Holiday API Request URL: {}", requestUrl);

        try {
            return restTemplate.getForObject(new URI(requestUrl), HolidayInfoResponse.class);
        } catch (Exception e) {
            log.error("Holiday API 조회 실패 - year: {}, month: {}", year, month, e);
            throw new GeneralException(ErrorStatus.HOLIDAY_API_ERROR);
        }
    }


    private String buildApiUrl(int year, int month, int pageNo, int numOfRows) {
        return UriComponentsBuilder.fromHttpUrl(apiEndpoint)
                .path("/getRestDeInfo")
                .queryParam("_type", "xml")
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .queryParam("solYear", year)
                .queryParam("solMonth", String.format("%02d", month))
                .toUriString();
    }
}