package edu.codegym.toam.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rating", "account_id"})
})
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean rateStatus = false;
    @CreationTimestamp
    private Date createDate;
    @NotNull
    private String comment;
    @Min(0)
    @Max(5)
    private int rating;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    @ManyToOne
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (this.rateStatus) return;
        else {
            this.rating = rating;
            this.rateStatus = true;
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isRateStatus() {
        return rateStatus;
    }

    public void setRateStatus(boolean rateStatus) {
        this.rateStatus = rateStatus;
    }
}
