package com.example.roomservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomReview {
    private String roomId;
    private String postId;
    private String postContent;
    private String postStarPoint;
    private LocalDate date;

}
