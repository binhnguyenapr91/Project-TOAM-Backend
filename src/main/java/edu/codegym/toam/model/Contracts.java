package edu.codegym.toam.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.persistence.*;
import java.util.Date;

//Không cho phép 1 renter tạo nhiều contracts với cùng 1 thời gian bắt đầu và cùng 1 địa chỉ 1renterId
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"beginTime","renterId", "propertiesId"})
})
@Entity
@ScriptAssert(lang = "javascript", script = "_this.createTime.before(_this.beginTime)")
@ScriptAssert(lang = "javascript", script = "_this.beginTime.before(_this.endTime)")

public class Contracts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createTime;
    private Date beginTime;
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "contractStatusId")
    private ContractStatus contractStatus;

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

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
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
