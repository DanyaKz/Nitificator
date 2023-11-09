package kz.example.psychonotif.controllers;

import kz.example.psychonotif.models.Group;
import kz.example.psychonotif.models.Message;
import kz.example.psychonotif.services.GroupService;
import kz.example.psychonotif.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @PostMapping("/")
    public RedirectView add(@RequestParam String text, @RequestParam Set<Long> groups,
                            @RequestParam(required = false) Optional<LocalDateTime> deadline){

        Notificator notify;
        Set<Group> groupList = groupService.findExistById(groups);

        Message notification = deadline.map(
                localDateTime -> new Message(text, localDateTime, groupList))
                .orElseGet(() -> new Message(text, groupList));
        messageService.save(notification);

        notify = new Notificator(
                groupList.stream().map(Group::getChatId).collect(Collectors.toList()),
                text, deadline);
        notify.notificate().start();

        return new RedirectView("/");
    }

}