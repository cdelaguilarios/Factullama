package com.llamita.factullamita.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by fabiosalasm on 16/03/14.
 */
public class AdminPwdBean implements Serializable {

    private static final long serialVersionUID = 7947686657591762896L;

    @NotNull(message = "La clave es obligatoria")
    @Size(min = 1, max = 12, message = "La clave ingresada debe tener un máximo de 12 caracteres.")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
