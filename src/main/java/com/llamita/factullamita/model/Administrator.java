package com.llamita.factullamita.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabiosalasm on 16/03/14.
 */
@Entity
@Table(name="ADMINISTRATOR")
public class Administrator implements Serializable {

    private static final long serialVersionUID = -606876123123415452L;

    @Id
    @GeneratedValue
    @Column(name="IN_IDADMINISTRATOR")
    private Integer id;

    @Column(name="VC_USER")
    private String user;

    @Column(name="VC_PASSWORD")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
