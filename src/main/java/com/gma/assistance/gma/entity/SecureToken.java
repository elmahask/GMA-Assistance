package com.gma.assistance.gma.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "secure_tokens")
public class SecureToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "token", unique = true)
    private String token;
    @CreationTimestamp
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "expire_at")
    private LocalDateTime expireAt;
    //    @ManyToOne
//    @JoinColumn(name = "operator_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Operator.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "operator_id")
    private Operator operator;

    @Transient
    private boolean isExpired;

    public SecureToken(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public boolean isExpired() {
        // this is generic implementation, you can always make it timezone specific
        return getExpireAt().isBefore(LocalDateTime.now());
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
