package kz.example.psychonotif.controllers;

import kz.example.psychonotif.models.Group;
import kz.example.psychonotif.models.Message;
import kz.example.psychonotif.repository.GroupRepo;
import kz.example.psychonotif.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Controller
public class MainPage {
    @Autowired(required=false)
    private MessageRepo messageRepo;
    @Autowired(required=false)
    private GroupRepo groupRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        Iterable<Message> notifications = messageRepo.findAll();
        model.addAttribute("notifications", notifications);
        model.addAttribute("groups", groupRepo);
        return "index";
    }

    @PostMapping
    public String add(@RequestParam String message, @RequestParam LocalDateTime deadline,
                      @RequestParam Set<Group> groups, Map<String, Object> model){

        Message notification = new Message(message, deadline, groups);
        messageRepo.save(notification);

        Iterable<Message> notifications = messageRepo.findAll();
        model.put("notifications", notifications);
        model.put("groups", groupRepo);

        return "index";
    }

}