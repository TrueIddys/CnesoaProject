package com.cnesoa.domain;

import javax.persistence.*;

/**
 * Created by Maxime on 07/04/2016.
 */

@Entity
public class InfosConsult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "infosconsult_id")
    private Long id;

    private Integer numBox;

    @ManyToOne
    private Professeur professeur;

    @ManyToOne
    private Binome binome;

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

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Binome getBinome() {
        return binome;
    }

    public void setBinome(Binome binome) {
        this.binome = binome;
    }
}
