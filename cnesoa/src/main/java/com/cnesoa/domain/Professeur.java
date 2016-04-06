package com.cnesoa.domain;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Maxime on 06/04/2016.
 */

@Entity
public class Professeur extends Person implements Serializable {

    private String code;

    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }


    @Override
    public int compareTo(Person o) {
        int d = getNom().compareTo(o.getNom());
        if (d == 0)
            d = getPrenom().compareTo(o.getPrenom());
        return d;
    }
}
