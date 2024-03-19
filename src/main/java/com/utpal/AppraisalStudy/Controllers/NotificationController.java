package com.utpal.AppraisalStudy.Controllers;

import com.utpal.AppraisalStudy.Entity.Notification;
import com.utpal.AppraisalStudy.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getNotifications")
    public ResponseEntity<List<Notification>> getAllNotification(){
        List<Notification> notication = notificationService.getAllNotification();
        return new ResponseEntity<>(notication, HttpStatus.OK);
    }

}
