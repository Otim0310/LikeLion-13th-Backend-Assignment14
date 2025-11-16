package com.likelion.assignment14.holiday.application;

import com.likelion.assignment14.holiday.api.dto.response.HolidayInfoResponse;
import com.likelion.assignment14.holiday.client.HolidayApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayApiClient holidayApiClient;


    public HolidayInfoResponse getMonthlyHolidays(int year, int month) {
        log.info("Fetching monthly holidays - year: {}, month: {}", year, month);
        return holidayApiClient.requestMonthlyHolidays(year, month);
    }


    public HolidayInfoResponse getYearlyHolidays(int year) {
        log.info("Fetching yearly holidays - year: {}", year);
        return holidayApiClient.requestMonthlyHolidays(year, 1);
    }
}