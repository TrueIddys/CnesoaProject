package com.cnesoa.domain;

import javax.persistence.*;

/**
 * Created by Maxime on 07/04/2016.
 */

@Entity
public class InfosConsult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numBinome;

    private Integer numBox;

    @ManyToOne
    private Professeur professeur;

    @ManyToOne
    private Eleve eleve1;

    @ManyToOne
    private Eleve eleve2;

    @OneToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    /*_________________________________________*/

    public InfosConsult(){

    }

    public InfosConsult(Consultation consultation){
        this.consultation = consultation;
    }

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

    public Integer getNumBox() {
        return numBox;
    }

    public void setNumBox(Integer numBox) {
        this.numBox = numBox;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
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

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
}
