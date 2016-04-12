package com.cnesoa.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 06/04/2016.
 */

@Entity
public class Professeur extends Person implements Serializable {

    private String code;

    @OneToMany
    private List<InfosConsult> infosConsults = new ArrayList<>();

    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public List<InfosConsult> getInfosConsulst() {
        return infosConsults;
    }

    public void setInfosConsults(List<InfosConsult> infosConsults) {
        this.infosConsults = infosConsults;
    }

    public void addInfosConsult(InfosConsult infosConsult){
        this.infosConsults.add(infosConsult);
    }

    public String getName(){
        return super.getName();
    }

    @Override
    public int compareTo(Person o) {
        int d = getNom().compareTo(o.getNom());
        if (d == 0)
            d = getPrenom().compareTo(o.getPrenom());
        return d;
    }
}
