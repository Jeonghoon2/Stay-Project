package com.example.roomservice.dto;

import lombok.Data;

@Data
public class RoomReservation {
    private String roomId;
    private String businessManId;
    private String user_uuid;
}
