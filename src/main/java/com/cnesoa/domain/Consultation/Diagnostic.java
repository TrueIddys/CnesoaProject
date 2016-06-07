package com.cnesoa.domain.Consultation;

import org.hibernate.search.annotations.*;

import javax.persistence.*;

/**
 * Created by Maxime on 15/04/2016.
 */
@Entity
@Table(name = "diagnostic")
@Indexed
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //le diagnostic
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String diagnosticText;

    //situez la douleur
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String douleur;

    //dynamique
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String dynamique;

    //palpatoire et tests
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String palpatoire;

    //validation du diagnostic
    private Boolean valide;

    //consultation liée au diagnostic
    @OneToOne
    private Consultation consultation;

    /*________________________________*/

    public Diagnostic(){
        this.valide = false;
    }

    public Diagnostic(Consultation consultation){
        this.consultation = consultation;
        this.valide = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosticText() {
        return diagnosticText;
    }

    public void setDiagnosticText(String diagnosticText) {
        this.diagnosticText = diagnosticText;
    }

    public String getDouleur() {
        return douleur;
    }

    public void setDouleur(String douleur) {
        this.douleur = douleur;
    }

    public String getDynamique() {
        return dynamique;
    }

    public void setDynamique(String dynamique) {
        this.dynamique = dynamique;
    }

    public String getPalpatoire() {
        return palpatoire;
    }

    public void setPalpatoire(String palpatoire) {
        this.palpatoire = palpatoire;
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
}
