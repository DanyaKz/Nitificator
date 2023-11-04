package kz.example.psychonotif.repository;

import kz.example.psychonotif.models.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageRepo extends CrudRepository<Message, Long> {
}
