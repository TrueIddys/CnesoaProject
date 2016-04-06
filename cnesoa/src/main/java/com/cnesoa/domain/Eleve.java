package com.cnesoa.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maxime on 05/04/2016.
 */

@Entity
public class Eleve extends Person implements Serializable {


    @Override
    public int compareTo(Person o) {
        int d = getNom().compareTo(o.getNom());
        if (d == 0)
            d = getPrenom().compareTo(o.getPrenom());
        return d;
    }
}
