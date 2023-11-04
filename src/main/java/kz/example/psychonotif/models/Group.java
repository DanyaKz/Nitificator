package kz.example.psychonotif.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany(mappedBy = "groups")
    private Set<Message> notifications = new HashSet<>();

    public Group() {
    }

}