package kz.example.psychonotif.repository;

import kz.example.psychonotif.models.Group;
import org.springframework.data.repository.CrudRepository;


public interface GroupRepo extends CrudRepository<Group, Long> {
}
