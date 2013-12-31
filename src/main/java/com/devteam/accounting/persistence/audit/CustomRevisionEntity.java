package com.devteam.accounting.persistence.audit;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import com.devteam.accounting.audit.CustomRevisionListener;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;

@Entity
@Table(name = "SYS_REVISIONS")
@RevisionEntity(CustomRevisionListener.class)
public class CustomRevisionEntity implements Serializable {
    private static final long serialVersionUID = -1255842407304508513L;

    @Id
    @GeneratedValue
    @RevisionNumber
    private Long id;

    @RevisionTimestamp
    private long timestamp;

    @Column(name = "user_name")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public Date getRevisionDate() {
        return new Date(timestamp);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomRevisionEntity)) return false;

        CustomRevisionEntity that = (CustomRevisionEntity) o;

        if (id != that.id) return false;
        if (timestamp != that.timestamp) return false;
        if (timestamp != that.timestamp) return false;
        if (username != that.username) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = id.intValue();
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    public String toString() {
        return "DefaultRevisionEntity(user = " + username + "id = " + id + ", revisionDate = " + DateFormat.getDateTimeInstance().format(getRevisionDate()) + ")";
    }

}