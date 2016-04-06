package com.cnesoa.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maxime on 30/03/2016.
 */

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "animal_id")
    private Long id;

    private String nom;

    private String race;

    private String type;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateNaissance;

    private String robe;

    private String sexe;

    @ManyToOne
    private Client client;

    @OneToOne(mappedBy = "animal",cascade = CascadeType.ALL)
    private FicheMedicale ficheMedicale;

    /*____________________________________________*/

    public Animal() {
        this.ficheMedicale = new FicheMedicale(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getRobe() {
        return robe;
    }

    public void setRobe(String robe) {
        this.robe = robe;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


    public Client getClient(){
        return this.client;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public String toString(){
        return nom;
    }

    public FicheMedicale getFicheMedicale() {
        return ficheMedicale;
    }

    public void setFicheMedicale(FicheMedicale ficheMedicale) {
        this.ficheMedicale = ficheMedicale;
    }
}
