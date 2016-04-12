package com.cnesoa.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maxime on 07/04/2016.
 */

@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consultation_id")
    private Long id;

    private Boolean valide;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dateConsultation;

    private String typeConsultation;

    private String motif;

    private String diagnostic;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
    private Traitement traitement;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
    private InfosConsult infosConsult;

    @ManyToOne
    private Animal animal;

    /*________________________________________*/

    public  Consultation(){

    }

    public Consultation(Animal animal){
        this.traitement = new Traitement();
        this.infosConsult = new InfosConsult(this);
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getTypeConsultation() {
        return typeConsultation;
    }

    public void setTypeConsultation(String typeConsultation) {
        this.typeConsultation = typeConsultation;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Traitement getTraitement() {
        return traitement;
    }

    public void setTraitement(Traitement traitement) {
        this.traitement = traitement;
    }

    public InfosConsult getInfosConsult() {
        return infosConsult;
    }

    public void setInfosConsult(InfosConsult infosConsult) {
        this.infosConsult = infosConsult;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString(){
        return "Consultation " +getId().toString();
    }

}
