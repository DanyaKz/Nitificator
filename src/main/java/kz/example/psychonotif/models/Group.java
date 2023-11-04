package kz.example.psychonotif.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Set;
import java.util.HashSet;

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