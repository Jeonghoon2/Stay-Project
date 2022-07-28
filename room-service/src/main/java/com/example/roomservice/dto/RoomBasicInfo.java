package com.example.roomservice.dto;

import lombok.Data;

@Data
public class RoomBasicInfo {
    private String roomId;
    private String roomNotice;
    private String roomAddress;         /* 숙소 도로명 주소*/
    private String roomLatitude;        /* 숙소 위도 */
    private String roomHardness;        /* 숙소 경도 */
    private int roomMaxPeople;
    private int roomBed;
    private String roomMainCategory;     /* 숙소 대분류 */
    private String roomSubCategory;   /* 숙소 소분류 */
    private int roomCondition;
    private String roomCheckInTime;
    private String roomCheckOutTime;
    private int roomStarPoint;

}
