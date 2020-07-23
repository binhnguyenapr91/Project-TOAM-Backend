package edu.codegym.toam.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

//Không cho phép 1 renter tạo nhiều contracts với cùng 1 thời gian bắt đầu và cùng 1 địa chỉ
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"beginTime","renterId", "propertiesId"})
})

@Entity
public class Contracts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreationTimestamp
    private Date createTime;

    private Date beginTime;
    private Date endTime;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "renterId")
    private Account renter;

    @ManyToOne
    @JoinColumn(name = "propertiesId")
    private Properties properties;

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

    public Account getRenter() {
        return renter;
    }

    public void setRenter(Account renter) {
        this.renter = renter;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
