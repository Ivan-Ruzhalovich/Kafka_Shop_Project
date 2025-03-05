package com.example.ivan.ruzhalovich.notifications.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public ResponseEntity<String> sendNotification(String message){
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
