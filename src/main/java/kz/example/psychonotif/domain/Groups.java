package kz.example.psychonotif.domain;

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
@Table(name = "Groups")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany(mappedBy = "groups")
    private Set<Notifications> notifications = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getGroupName() {
        return groupName;
    }

    public Set<Notifications> getNotifications() {
        return notifications;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNotifications(Set<Notifications> notifications) {
        this.notifications = notifications;
    }
}