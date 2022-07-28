package com.example.roomservice.dto;

import lombok.Data;

@Data
public class RoomDto {

    private String roomName;            /* 숙소 이름 */
    private String roomTitleImage;      /* 숙소 대표 사진 */
    private String roomSubTitleImage;   /* 숙소 대표 사진 */
    private String roomRentPersonnel;   /* 숙소 대여 인실 ex) 공간 전체, 개인실, 다인실 */
    private Integer roomGuestCount;     /* 숙소 게스트 수*/

}
