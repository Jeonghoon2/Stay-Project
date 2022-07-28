package com.example.roomservice.controller;


import com.example.roomservice.dto.RoomDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RoomController {

    @GetMapping("/health_check")
    public ResponseEntity healthCheck(){
        return ResponseEntity.status(HttpStatus.OK).body("정상 동작중");
    }

    @PostMapping("/register")
    public ResponseEntity createRoom(@RequestBody RoomDto roomDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(roomDto);
    }
}
