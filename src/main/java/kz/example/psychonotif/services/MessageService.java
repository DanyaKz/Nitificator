package kz.example.psychonotif.services;

import kz.example.psychonotif.models.Message;

import java.util.List;

public interface MessageService {
    public List<Message> findAll();
    public void save(Message message);
}
