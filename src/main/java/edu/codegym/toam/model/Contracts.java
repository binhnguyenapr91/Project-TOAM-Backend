package edu.codegym.toam.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
public class Contracts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date createTime;

    private Date beginTime;
    private Date endTime;
    private boolean status;

    @OneToOne
    private Properties properties;

    @OneToOne
    @JoinColumn(name = "hostId")
    private Account host;

    @OneToOne
    @JoinColumn(name = "renterId")
    private Account renter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Account getHost() {
        return host;
    }

    public void setHost(Account host) {
        this.host = host;
    }

    public Account getRenter() {
        return renter;
    }

    public void setRenter(Account renter) {
        this.renter = renter;
    }
}
