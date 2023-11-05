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
import org.springframework.web.servlet.view.RedirectView;

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
        model.addAttribute("groupList", groupService.findAll());
        return "index";
    }

    @PostMapping
    public RedirectView add(@RequestParam String text, @RequestParam LocalDateTime deadline,
                      @RequestParam Set<Long> groups){

        System.out.println(groupService.findExistById(groups));
        Message notification = new Message(text, deadline, groupService.findExistById(groups));
        messageService.save(notification);

        return new RedirectView("/");
    }

}