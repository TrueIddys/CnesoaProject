package com.cnesoa.domain.Person;

import com.cnesoa.domain.Binome;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 05/04/2016.
 */

@Entity
@DiscriminatorValue("Eleve")
public class Eleve extends User implements Serializable {

    //le binome de l'élève
    @OneToMany(mappedBy = "eleve1", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Binome> binomes1 = new ArrayList<>();

    @OneToMany(mappedBy = "eleve2", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Binome> binomes2 = new ArrayList<>();

    /*_______________________*/

    public List<Binome> getBinomes1() {
        return binomes1;
    }

    public void setBinomes1(List<Binome> binomes1) {
        this.binomes1 = binomes1;
    }

    public List<Binome> getBinomes2() {
        return binomes2;
    }

    public void setBinomes2(List<Binome> binomes2) {
        this.binomes2 = binomes2;
    }

    public List<Binome> getAllBinome(){
        List<Binome> allBinome = new ArrayList<>();
        allBinome.addAll(getBinomes1());
        allBinome.addAll(getBinomes2());
        return allBinome;
    }

    public void addBinome1(Binome binome){
        if (binome == null)
            throw new IllegalArgumentException("Le binome n'as pas pu être ajouté.");
        this.binomes1.add(binome);
    }

    public void addBinome2(Binome binome){
        if (binome == null)
            throw new IllegalArgumentException("Le binome n'as pas pu être ajouté.");
        this.binomes2.add(binome);
    }

    public void removeBinome1(Binome binome){
        this.binomes1.remove(binome);
    }

    public void removeBinome2(Binome binome){
        this.binomes2.remove(binome);
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
