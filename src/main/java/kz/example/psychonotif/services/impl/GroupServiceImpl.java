package kz.example.psychonotif.services.impl;

import kz.example.psychonotif.models.Group;
import kz.example.psychonotif.repository.GroupRepo;
import kz.example.psychonotif.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public List<Group> findAll() {
        return groupRepo.findAll();
    }
}
