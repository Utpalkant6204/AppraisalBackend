package com.utpal.AppraisalStudy.Services.Interfaces;

import com.utpal.AppraisalStudy.Entity.Notification;

import java.util.List;

public interface NotificationService {
    public List<Notification> getAllNotification();

    public Notification saveNotification(Notification notification);

    public boolean deleteAllNotifications();

    public boolean deleteNotification(long id);
}
