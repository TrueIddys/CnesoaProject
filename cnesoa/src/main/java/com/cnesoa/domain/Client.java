package com.cnesoa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 30/03/2016.
 */

@Entity
public class Client extends Person implements Serializable {

    private String rue;

    private String ville;

    @OneToOne
    private Cotisation cotisation;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Animal> animaux = new ArrayList<>();

    public String getRue(){
        return rue;
    }

    public void setRue(String rue)
    {
        this.rue = rue;
    }

    public String getVille(){
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public Cotisation getCotisation(){
        return cotisation;
    }

    public void setCotisation(Cotisation cotisation){
        this.cotisation = cotisation;
    }

    public List<Animal> getAnimaux(){
        return animaux;
    }

    public void setAnimaux(List<Animal> animaux){
        this.animaux = animaux;
    }

    public void addAnimal(Animal animal){
        if (animal == null){
            throw new IllegalArgumentException("Cannot add a null animal");
        }

        this.animaux.add(animal);
    }

    public void removeAnimal(Animal animal){
        this.animaux.remove(animal);
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
