package com.example.map_toysocialnetwork.domain;

import com.example.map_toysocialnetwork.utils.events.Event;

import java.time.LocalDateTime;

public class UtilizatorPrietenieDTO implements Event {

    private Long idFriend;

    private String friendfirstName;

    private String friendLastName;

    private LocalDateTime dateTime;

    private String status;

    private Long IDprietenie;

    public UtilizatorPrietenieDTO(Long idFriend, String friendfirstName, String friendLastName, LocalDateTime dateTime, String status) {
        this.idFriend=idFriend;
        this.friendfirstName = friendfirstName;
        this.friendLastName = friendLastName;
        this.dateTime = dateTime;
        this.status = status;
    }


    public Long getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(Long idFriend) {
        this.idFriend = idFriend;
    }

    public String getFriendfirstName() {
        return friendfirstName;
    }

    public void setFriendfirstName(String friendfirstName) {
        this.friendfirstName = friendfirstName;
    }

    public String getFriendLastName() {
        return friendLastName;
    }

    public void setFriendLastName(String friendLastName) {
        this.friendLastName = friendLastName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
