package com.cnesoa.domain.Consultation;

import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Person.Professeur;

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

    //numéro du box
    private Integer numBox;

    //professeur lié à la consultation
    @ManyToOne
    private Professeur professeur;

    //binome lié à la consultation
    @ManyToOne
    private Binome binome;

    //consultation
    @OneToOne
    private Consultation consultation;

    //note (1 à 5 étoiles) sur le diagnostic
    private Integer noteDiag;

    //note (1 à 5 étoiles) sur le traitement
    private Integer noteTrait;

    //remarques particulières concernant les élèves
    private String remarque;

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

    public Integer getNoteDiag() {
        return noteDiag;
    }

    public void setNoteDiag(Integer noteDiag) {
        this.noteDiag = noteDiag;
    }

    public Integer getNoteTrait() {
        return noteTrait;
    }

    public void setNoteTrait(Integer noteTrait) {
        this.noteTrait = noteTrait;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
