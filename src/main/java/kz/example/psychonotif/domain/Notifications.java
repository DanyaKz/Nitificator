package kz.example.psychonotif.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "message")
    private String message;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "is_relevant")
    private Boolean isRelevant;

    @Column(name = "sending_date")
    private LocalDateTime sendingDate;

    @Column(name = "sending_count")
    private Integer sendingCount;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Notifications_Groups",
            joinColumns = @JoinColumn(name = "notif_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Groups> groups = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public Boolean getRelevant() {
        return isRelevant;
    }

    public LocalDateTime getSendingDate() {
        return sendingDate;
    }

    public Integer getSendingCount() {
        return sendingCount;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setRelevant(Boolean relevant) {
        isRelevant = relevant;
    }

    public void setSendingDate(LocalDateTime sendingDate) {
        this.sendingDate = sendingDate;
    }

    public void setSendingCount(Integer sendingCount) {
        this.sendingCount = sendingCount;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }

}
