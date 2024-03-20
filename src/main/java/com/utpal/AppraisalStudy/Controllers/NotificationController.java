package com.utpal.AppraisalStudy.Controllers;

import com.utpal.AppraisalStudy.Entity.Employees;
import com.utpal.AppraisalStudy.Entity.Notification;
import com.utpal.AppraisalStudy.Services.EmployeeService;
import com.utpal.AppraisalStudy.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getNotifications")
    public ResponseEntity<List<Notification>> getAllNotification(){
        List<Notification> notication = notificationService.getAllNotification();
        return new ResponseEntity<>(notication, HttpStatus.OK);
    }

    @PostMapping("/saveNotification")
    public ResponseEntity<Notification> saveNotification(@RequestBody Notification notification){
        Notification ntfy = notificationService.saveNotification(notification);
        return new ResponseEntity<>(ntfy, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAllNotification")
    public ResponseEntity<Boolean> deleteAllNotifications(){
        boolean res = notificationService.deleteAllNotifications();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/deleteNotification")
    public ResponseEntity<Boolean> deleteNotification(@PathVariable("id") long id){
        boolean res = notificationService.deleteNotification(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/updateNotification")
    public ResponseEntity<Boolean> updateNotifyByAdmin(@PathVariable("id") long id, @RequestBody Employees employees){
        boolean upd = employeeService.updateNotifyByAdmin(id, employees);
        return new ResponseEntity<>(upd, HttpStatus.OK);
    }

}
