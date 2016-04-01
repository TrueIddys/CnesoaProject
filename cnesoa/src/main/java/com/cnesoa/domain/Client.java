package com.cnesoa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxime on 30/03/2016.
 */

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String prenom;

    @Column
    private String nom;

    @Column
    private String mail;

    @Column
    private String rue;

    @Column
    private String ville;

    @OneToOne
    private Cotisation cotisation;

    @OneToMany
    private Set<Animal> animaux = new HashSet<>();

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getMail()
    {
        return this.mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

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

    public Set<Animal> getAnimaux(){
        return animaux;
    }

    public void setAnimaux(Set<Animal> animaux){
        this.animaux = animaux;
    }

    public void addAnimal(Animal animal){
        if (animal == null){
            throw new IllegalArgumentException("Cannot add a null animal");
        }

        this.animaux.add(animal);
    }

    public  String toString(){
        return prenom + " " + nom;
    }
}
