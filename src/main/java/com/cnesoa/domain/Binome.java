package com.cnesoa.domain;

import com.cnesoa.domain.Consultation.InfosConsult;
import com.cnesoa.domain.Person.Eleve;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 13/04/2016.
 */
@Entity
public class Binome implements Serializable, Comparable<Binome>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //numéro du binome
    private Integer numBinome;

    //1er élève du binome
    @ManyToOne
    private Eleve eleve1;

    //2ème élève du binome
    @ManyToOne
    private Eleve eleve2;

    //liste des consultations liées au binome
    @OneToMany(mappedBy = "binome", cascade = CascadeType.PERSIST)
    private List<InfosConsult> infosConsult = new ArrayList<>();

    /*_____________________________*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumBinome() {
        return numBinome;
    }

    public void setNumBinome(Integer numBinome) {
        this.numBinome = numBinome;
    }

    public Eleve getEleve1() {
        return eleve1;
    }

    public void setEleve1(Eleve eleve1) {
        this.eleve1 = eleve1;
    }

    public Eleve getEleve2() {
        return eleve2;
    }

    public void setEleve2(Eleve eleve2) {
        this.eleve2 = eleve2;
    }

    public List<InfosConsult> getInfosConsult() {
        return infosConsult;
    }

    public void setInfosConsult(List<InfosConsult> infosConsult) {
        this.infosConsult.clear();
        this.infosConsult.addAll(infosConsult);
    }


    /*__________________________*/

    public Binome(){
    }

    public void addInfosConsult(InfosConsult infosConsult){
        this.infosConsult.add(infosConsult);
    }

    public void removeInfosConsult(InfosConsult infosConsult1) { this.infosConsult.remove(infosConsult1);}

    /*__________________________*/

    public String getName(){
        return eleve1.getName() + " et " + eleve2.getName();
    }

    @Override
    public int compareTo(Binome o) {
        int d = getName().compareTo(o.getName());
        return d;
    }

}
