package com.cnesoa.domain;

/**
 * Created by Maxime on 12/04/2016.
 */
public class Event {

    private String id;

    private Long date;

    private String binome;

    private String professeur;

    private String animal;

    private String proprio;

    /*_____________________________*/

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

    public void setBinome(String binome) {
        this.binome = binome;
    }

    public String getProfesseur() {
        return professeur;
    }

    public void setProfesseur(String professeur) {
        this.professeur = professeur;
    }

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
