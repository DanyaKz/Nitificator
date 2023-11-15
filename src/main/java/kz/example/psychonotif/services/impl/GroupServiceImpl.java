package kz.example.psychonotif.services.impl;

import kz.example.psychonotif.models.Group;
import kz.example.psychonotif.repository.GroupRepo;
import kz.example.psychonotif.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public List<Group> findAll() {
        return groupRepo.findAll();
    }

    @Override
    public Set<Group> findExistById(Set<Long> setId) {
        return new HashSet<>(groupRepo.findAllById(setId));
    }
}
