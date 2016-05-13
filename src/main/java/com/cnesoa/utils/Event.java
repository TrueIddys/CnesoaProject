package com.cnesoa.utils;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Person.Client;
import com.cnesoa.domain.Person.Professeur;
import com.cnesoa.exceptions.NullObjectException;

import java.util.Date;

/**
 * Created by Maxime on 12/04/2016.
 * Class that contains cotisation information in json format
 */
public class Event {

    private String id;

    private Long date;

    private String binome;

    private String professeur;

    private String animal;

    private String proprio;

    /*_____________________________*/

    public Event(){

    }

    public Event(String id, Date date, Binome binome, Professeur professeur, Animal animal, Client client){
        this.id = id;
        if (date == null)
            throw new NullObjectException("La date pour cette consultation n'as pas été renseignée.");
        this.date = date.getTime();
        if (binome == null)
            this.binome = "Pas de binome.";
        else
            this.binome = binome.getName();
        if (professeur == null)
            this.professeur ="Pas de professeur";
        else
            this.professeur = professeur.getName();
        this.animal = animal.getNom();
        this.proprio = client.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getBinome() {
        return binome;
    }

    public void setBinome(String binomeName) {
        if (binomeName == null)
            binomeName = "Pas de binome enregistre.";
        this.binome = binomeName;
    }

    public String getProfesseur() {
        return professeur;
    }

    public void setProfesseur(String professeurName) {
        if (professeurName == null)
            professeurName = "Pas de professeur.";
        this.professeur = professeurName;    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getProprio() {
        return proprio;
    }

    public void setProprio(String proprio) {
        this.proprio = proprio;
    }
}
