package com.library.scheduler;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = "0 0 0 ? * * ")
    public void removeNotifications(){
        List<User> userList = userRepository.findAll();
        for (User user: userList) {
            user.clearNotifications();
        }
    }
}
