package com.cnesoa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 07/04/2016.
 */

@Entity
public class Traitement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass=Boolean.class)
    private List<Boolean> traitements = new ArrayList<Boolean>();

    private String details;

    private String resultat;

    private String conseils;

    @OneToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    /*_____________________________________*/

    public Traitement(){
        remplirListe();
    }

    public Traitement(Consultation consultation){
        this.consultation = consultation;
        remplirListe();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Boolean> getTraitements() {
        return traitements;
    }

    public void setTraitements(List<Boolean> traitements) {
        this.traitements = traitements;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getConseils() {
        return conseils;
    }

    public void setConseils(String conseils) {
        this.conseils = conseils;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public void remplirListe(){
        for (int i = 0; i < 6; i++) {
            traitements.add(new Boolean(false));
        }
    }



}
