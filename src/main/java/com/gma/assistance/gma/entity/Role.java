package com.gma.assistance.gma.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "authority", nullable = false)
    private String authority;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private List<Operator> operators;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

//    public List<Operator> getOperators() {
//        return operators;
//    }
//
//    public void setOperators(List<Operator> operators) {
//        this.operators = operators;
//    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id + '\'' +
                "authority=" + authority + '\'' +
                '}';
    }
}
