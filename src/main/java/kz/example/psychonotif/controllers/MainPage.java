package kz.example.psychonotif.controllers;

import kz.example.psychonotif.models.Group;
import kz.example.psychonotif.models.Message;
import kz.example.psychonotif.repository.GroupRepo;
import kz.example.psychonotif.repository.MessageRepo;
import kz.example.psychonotif.services.GroupService;
import kz.example.psychonotif.services.MessageService;
import kz.example.psychonotif.services.impl.MessageServiceImpl;
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

    @Autowired
    private MessageService messageService;
    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String greeting(Model model) {
        Iterable<Message> notifications = messageService.findAll();
        model.addAttribute("notifications", notifications);
        model.addAttribute("groups", groupService.findAll());
        return "index";
    }

    @PostMapping
    public String add(@RequestParam String message, @RequestParam LocalDateTime deadline,
                      @RequestParam Set<Group> groups, Map<String, Object> model){

        Message notification = new Message(message, deadline, groups);
        messageService.save(notification);

        Iterable<Message> notifications = messageService.findAll();
        model.put("notifications", notifications);
        model.put("groups", groupService.findAll());

        return "index";
    }

}