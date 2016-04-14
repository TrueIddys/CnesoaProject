package com.cnesoa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Maxime on 13/04/2016.
 */
@Entity
public class Binome implements Serializable, Comparable<Binome>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numBinome;

    @OneToOne
    @JoinColumn(name = "eleve1_id")
    private Eleve eleve1;

    @OneToOne
    @JoinColumn(name = "eleve2_id")
    private Eleve eleve2;

    @OneToMany
    private List<InfosConsult> infosConsult;

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
        this.infosConsult = infosConsult;
    }

    public void addInfosConsult(InfosConsult infosConsult){
        this.infosConsult.add(infosConsult);
    }

/*__________________________*/

    public String getName(){
        return eleve1.getName() + " et " + eleve2.getName();
    }

    /*__________________________*/

    @Override
    public int compareTo(Binome o) {
        int d = getName().compareTo(o.getName());
        return d;
    }

}
