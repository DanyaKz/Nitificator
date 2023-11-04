package kz.example.psychonotif.repository;

import kz.example.psychonotif.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
}
