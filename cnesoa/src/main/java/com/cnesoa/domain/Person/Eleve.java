package com.cnesoa.domain.Person;

import com.cnesoa.domain.Binome;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Maxime on 05/04/2016.
 */

@Entity
@DiscriminatorValue("Eleve")
public class Eleve extends User implements Serializable {

    //le binome de l'élève
    @OneToOne(cascade = CascadeType.REMOVE)
    private Binome binome;
    //TODO implémenter le changement de binome (mi année)

    /*_______________________*/

    public Binome getBinome() {
        return binome;
    }

    public void setBinome(Binome binome) {
        this.binome = binome;
    }

    /*________________________*/

    public Eleve(){
        super();
    }

    public Eleve(String nom, String prenom, String mail, String tel){
        super(nom, prenom, mail, tel);
    }

    public String getName(){
        return super.getName();
    }
}
