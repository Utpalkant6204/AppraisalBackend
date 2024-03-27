package com.utpal.AppraisalStudy.Services.Impl;

import com.utpal.AppraisalStudy.Entity.Employees;
import com.utpal.AppraisalStudy.Entity.Notification;
import com.utpal.AppraisalStudy.Exceptions.UserNotFoundException;
import com.utpal.AppraisalStudy.Repository.EmployeeRepository;
import com.utpal.AppraisalStudy.Repository.NotificationRepository;
import com.utpal.AppraisalStudy.Services.Interfaces.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification saveNotification(Notification notification) {
        Optional<Employees> emp = employeeRepository.findById(notification.getEmployeeId());

        if(emp.isPresent()){
            Employees emp1= emp.get();

            emp1.setNotifybyemployee(true);

            employeeRepository.save(emp1);
        }
        else{
            throw new UserNotFoundException("Employee not found with "+notification.getEmployeeId());
        }
        return notificationRepository.save(notification);
    }

    @Override
    public boolean deleteAllNotifications() {
        notificationRepository.deleteAll();
        List<Notification> nfty = notificationRepository.findAll();

        return nfty.isEmpty();
    }

    @Override
    @Transactional
    public boolean deleteNotification(long id) {
        notificationRepository.deleteByEmployeeId(id);
        Optional<Notification> ntfy = notificationRepository.findById(id);

        if(ntfy.isPresent()){
            System.out.println(ntfy.get());
            return false;
        }

        return  true;
    }


}
