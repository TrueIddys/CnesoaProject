package com.cnesoa.domain.Consultation;

import com.cnesoa.domain.Animal.Animal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Maxime on 07/04/2016.
 */

@Entity
@Table(name = "consultation")
public class Consultation implements Serializable, Comparable<Consultation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private Long id;

    //date de la consultation
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dateConsultation;

    //type de consultation
    private String typeConsultation;

    //motif de la consultation
    private String motif;

    //diagnostic lié à la consultation
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Diagnostic diagnostic;

    //traitement lié à la consultation
    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Traitement traitement;

    //infos complémentaires de la consultation
    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private InfosConsult infosConsult;

    //l'animal lié à la consultation
    @ManyToOne
    private Animal animal;

    /*________________________________________*/

    public  Consultation(){
        this.infosConsult = new InfosConsult(this);
        this.diagnostic = new Diagnostic(this);
        this.traitement = new Traitement(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    /*__________________________*/


    /**
     * Permet d'initialiser les objets contenus par une consultation
     */
    public void initializeNestedObjects(){
        if (this.infosConsult == null)
            this.infosConsult = new InfosConsult(this);
        if (this.diagnostic == null)
            this.diagnostic = new Diagnostic(this);
        if (this.traitement == null)
            this.traitement = new Traitement(this);
    }

    /**
     * permet de formater la date de consultation pour avoir les heures et minutes
     * @return
     */
    public String formatDateConsultation(){
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dt.format(this.dateConsultation);
    }

    @Override
    public String toString(){
        return "Consultation";
    }

    @Override
    public int compareTo(Consultation o) {
        if (getDateConsultation().before(o.getDateConsultation()))
            return -1;
        else if (getDateConsultation().after(o.getDateConsultation()))
            return 1;
        return 0;
    }
}
