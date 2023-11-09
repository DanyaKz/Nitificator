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

    @Column(name = "message_text", columnDefinition="TEXT")
    private String text;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "is_relevant")
    private Boolean isRelevant = true;

    @Column(name = "sending_date")
    private LocalDateTime sendingDate = LocalDateTime.now();

    @Column(name = "num_of_notif")
    private Integer numOfNotif = 3;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Notifications_Groups",
            joinColumns = @JoinColumn(name = "notif_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> subscribers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Notifications_Groups",
            inverseJoinColumns = @JoinColumn(name = "group_id"),
            joinColumns = @JoinColumn(name = "notif_id"))
    private Set<Group> subscriptions = new HashSet<>();

    public Message() {
    }

    public Message(String text, Set<Group> subscribers) {
        this.text = text;
        this.subscribers = subscribers;
    }

    public Message(String text, LocalDateTime deadline, Set<Group> groups) {
        this.text = text;
        this.deadline = deadline;
        this.subscribers = groups;
    }

}
