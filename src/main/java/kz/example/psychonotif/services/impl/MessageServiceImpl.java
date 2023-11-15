package kz.example.psychonotif.services.impl;

import kz.example.psychonotif.models.Message;
import kz.example.psychonotif.repository.MessageRepo;
import kz.example.psychonotif.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Override
    public List<Message> findAll() {
        return messageRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public void save(Message message) {
        messageRepo.save(message);
    }
}
