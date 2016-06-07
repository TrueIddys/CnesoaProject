package com.cnesoa.domain.Consultation;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maxime on 07/04/2016.
 */

@Entity
@Indexed
public class Traitement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //liste des différents traitements possibles
    @ElementCollection
    private Map<String, Boolean> traitements = new HashMap<>();

    //détails du traitement
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String details;

    //résultat de la consultation
    private String resultat;

    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String conseils;

    //validation du traitement
    private Boolean valide;

    //consultation liée au traitement
    @OneToOne
    private Consultation consultation;

    /*_____________________________________*/

    public Traitement(){
        remplirListe();
        this.valide = false;
    }

    public Traitement(Consultation consultation){
        this.consultation = consultation;
        this.valide = false;
        remplirListe();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Boolean> getTraitements() {
        return traitements;
    }

    public void setTraitements(Map<String, Boolean> traitements) {
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

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    /*_____________________________________*/

    /**
     * Remplis la liste des différents traitements possibles
     */
    public void remplirListe(){
        traitements.put("myotensif", false);
        traitements.put("structurel", false);
        traitements.put("tissulaire", false);
        traitements.put("crane/sacre", false);
        traitements.put("tog", false);
        traitements.put("visceral", false);

    }



}
