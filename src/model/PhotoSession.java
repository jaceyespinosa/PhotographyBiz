package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Business")
public class PhotoSession {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private int id;
    
    @Column(name="CLIENT_NAME")
    private String clientName;
    
    @Column(name="SESSION_LOCATION")
    private String sessionLocation;

    public PhotoSession() {
        super();
    }

    public PhotoSession(String clientName, String sessionLocation) {
        super();
        this.clientName = clientName;
        this.sessionLocation = sessionLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSessionLocation() {
        return sessionLocation;
    }

    public void setSessionLocation(String sessionLocation) {
        this.sessionLocation = sessionLocation;
    }
    
    public String returnSessionDetails() {
        return this.clientName + ": " + this.sessionLocation;
    }
}