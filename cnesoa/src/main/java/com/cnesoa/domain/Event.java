package com.cnesoa.domain;

/**
 * Created by Maxime on 12/04/2016.
 */
public class Event {

    private String id;

    private Long date;

    private String eleve1;

    private String eleve2;

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

    public String getEleve1() {
        return eleve1;
    }

    public void setEleve1(String eleve1) {
        this.eleve1 = eleve1;
    }

    public String getEleve2() {
        return eleve2;
    }

    public void setEleve2(String eleve2) {
        this.eleve2 = eleve2;
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
