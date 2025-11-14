package com.likelion.assignment14.weather.application;

import com.likelion.assignment14.weather.api.dto.response.CultureListApiResponse;
import com.likelion.assignment14.weather.client.CultureApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CultureService {

}
