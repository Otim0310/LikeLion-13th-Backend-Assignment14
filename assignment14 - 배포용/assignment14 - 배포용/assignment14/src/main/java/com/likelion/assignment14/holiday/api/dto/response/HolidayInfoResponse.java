package com.likelion.assignment14.holiday.api.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
public class HolidayInfoResponse {

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
        private List<HolidayItem> item;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @NoArgsConstructor
    public static class HolidayItem {

        private String locdate;

        private String seq;

        private String dateKind;

        private String isHoliday;

        private String dateName;
    }
}