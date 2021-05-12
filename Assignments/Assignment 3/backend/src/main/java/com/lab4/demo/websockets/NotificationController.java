package com.lab4.demo.websockets;

import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;


import static com.lab4.demo.UrlMapping.*;

@Controller
public class NotificationController {

    @MessageMapping(PATIENT)
    @SendTo(CONSULTATION+ENTITY)
    public Notification notification(Notification n){
        return new Notification(n.getNotification());
    }
}
