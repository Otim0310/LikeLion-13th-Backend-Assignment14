package com.likelion.assignment14.weather.api.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
public class WeatherWarningListApiResponse {

    private Header header;
    private Body body;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @NoArgsConstructor
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @NoArgsConstructor
    public static class Body {

        private String dataType;

        @XmlElement(name = "items")
        private Items items;

        private Integer numOfRows;
        private Integer pageNo;
        private Integer totalCount;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @NoArgsConstructor
    public static class Items {

        @XmlElement(name = "item")
        private List<WeatherWarningItem> item;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @NoArgsConstructor
    public static class WeatherWarningItem {

        private String stnId;
        private String title;
        private String tmFc;
        private String tmSeq;
    }
}
