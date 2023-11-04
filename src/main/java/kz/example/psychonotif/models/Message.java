package kz.example.psychonotif.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "is_relevant", columnDefinition = "BOOLEAN DEFAULT 1")
    private Boolean isRelevant;

    @Column(name = "sending_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime sendingDate;

    @Column(name = "num_of_notif", columnDefinition = "INTEGER DEFAULT 3")
    private Integer numOfNotif;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Notifications_Groups",
            joinColumns = @JoinColumn(name = "notif_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    public Message() {
    }

    public Message(String text, LocalDateTime deadline, Set<Group> groups) {
        this.text = text;
        this.deadline = deadline;
        this.groups = groups;
    }

}
