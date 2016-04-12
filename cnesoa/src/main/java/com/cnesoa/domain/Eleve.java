package com.cnesoa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 05/04/2016.
 */

@Entity
public class Eleve extends Person implements Serializable {

    @OneToMany
    private List<InfosConsult> infosConsults = new ArrayList<>();

    public List<InfosConsult> getInfosConsults() {
        return infosConsults;
    }

    public void setInfosConsults(List<InfosConsult> infosConsults) {
        this.infosConsults = infosConsults;
    }

    public void addInfosConsult(InfosConsult infosConsult){
        this.infosConsults.add(infosConsult);
    }

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
