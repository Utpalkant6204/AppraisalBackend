package com.utpal.AppraisalStudy.Services;

import com.utpal.AppraisalStudy.Entity.Notification;
import com.utpal.AppraisalStudy.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }
}
