package com.cnesoa.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by Maxime on 05/04/2016.
 */

@Entity
public class Eleve extends Person implements Serializable {

    @OneToOne
    private Binome binome;

    /*_______________________*/

    public Binome getBinome() {
        return binome;
    }

    public void setBinome(Binome binome) {
        this.binome = binome;
    }

    /*________________________*/

    @Override
    public int compareTo(Person o) {
        int d = getNom().compareTo(o.getNom());
        if (d == 0)
            d = getPrenom().compareTo(o.getPrenom());
        return d;
    }

    public String getName(){
        return super.getName();
    }
}
