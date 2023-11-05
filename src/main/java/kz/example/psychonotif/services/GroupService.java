package kz.example.psychonotif.services;

import kz.example.psychonotif.models.Group;

import java.util.List;
import java.util.Set;

public interface GroupService {
    public List<Group> findAll();

    public Set<Group> findExistById(Set<Long> setId);
}
