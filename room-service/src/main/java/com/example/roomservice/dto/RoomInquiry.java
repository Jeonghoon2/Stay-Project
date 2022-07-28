package com.example.roomservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomInquiry {
    private String userId;
    private String roomId;
    private String postTitle;
    private String postContent;
    private LocalDate postDate;


}
