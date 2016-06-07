package com.cnesoa.domain.Person;

import com.cnesoa.utils.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Maxime on 22/04/2016.
 */

@Entity
@Table(name = "user")
public class User extends Person{

    //nom d'utilisateur
    @NotNull
    @Column(unique = true)
    private String username;

    //mot de pass crypté
    @NotNull
    private String passwordHash;

    //role de l'utilisateur (admin, eleve, prof)
    @NotNull
    private Role role;


    /*_________________*/

    public User(){
        super();
    }

    public User(String nom, String prenom, String mail, String tel){
        super(nom, prenom, mail, tel);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
