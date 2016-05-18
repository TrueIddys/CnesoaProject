package com.cnesoa.domain.Person;

import com.cnesoa.domain.Binome;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by Maxime on 05/04/2016.
 */

@Entity
@DiscriminatorValue("Eleve")
public class Eleve extends User implements Serializable {

    //le binome de l'élève
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Binome binome;
    //TODO réfléchir à la possibilité d'avoir plusieurs binomes avec les mêmes élèves, car
    //il faut enregistrer les notes des élèves lors de consultations avec un binome plus existant

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
