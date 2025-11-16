package com.likelion.assignment14.holiday.api;

import com.likelion.assignment14.holiday.api.dto.response.HolidayInfoResponse;
import com.likelion.assignment14.holiday.application.HolidayService;
import com.likelion.assignment14.global.code.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/holidays")
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping("/monthly")
    public ResponseEntity<ApiResponse<HolidayInfoResponse>> getMonthlyHolidays(
            @RequestParam int year,
            @RequestParam int month) {

        HolidayInfoResponse response = holidayService.getMonthlyHolidays(year, month);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @GetMapping("/yearly")
    public ResponseEntity<ApiResponse<HolidayInfoResponse>> getYearlyHolidays(
            @RequestParam int year) {

        HolidayInfoResponse response = holidayService.getYearlyHolidays(year);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }
}