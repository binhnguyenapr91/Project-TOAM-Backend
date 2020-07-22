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
}
