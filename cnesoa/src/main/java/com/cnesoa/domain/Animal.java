package com.cnesoa.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maxime on 30/03/2016.
 */

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nom;

    @Column
    private String race;

    @Column
    private String type;

    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateNaissance;

    @Column
    private String robe;

    @Column
    private String sexe;

    @Column
    private String origine;

    @Column
    private String etatGeneral;

    @Column
    private String antecedantMedicaux;

    @Column
    private String antecedentChirurgicaux;

    @Column
    private String antecedantTraumatiques;

    @Column
    private Boolean vaccinMaj;

    @Column
    private Boolean sterilise;

    @Column
    private Boolean vermifuge;

    @Column
    private Boolean marechal;

    @Column
    private Boolean dentiste;

    @ManyToOne
    private Client client;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getRobe() {
        return robe;
    }

    public void setRobe(String robe) {
        this.robe = robe;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Client getClient(){
        return this.client;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public String toString(){
        return nom;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getEtatGeneral() {
        return etatGeneral;
    }

    public void setEtatGeneral(String etatGeneral) {
        this.etatGeneral = etatGeneral;
    }

    public String getAntecedantMedicaux() {
        return antecedantMedicaux;
    }

    public void setAntecedantMedicaux(String antecedantMedicaux) {
        this.antecedantMedicaux = antecedantMedicaux;
    }

    public String getAntecedentChirurgicaux() {
        return antecedentChirurgicaux;
    }

    public void setAntecedentChirurgicaux(String antecedentChirurgicaux) {
        this.antecedentChirurgicaux = antecedentChirurgicaux;
    }

    public String getAntecedantTraumatiques() {
        return antecedantTraumatiques;
    }

    public void setAntecedantTraumatiques(String antecedantTraumatiques) {
        this.antecedantTraumatiques = antecedantTraumatiques;
    }

    public Boolean getVaccinMaj() {
        return vaccinMaj;
    }

    public void setVaccinMaj(Boolean vaccinMaj) {
        this.vaccinMaj = vaccinMaj;
    }

    public Boolean getSterilise() {
        return sterilise;
    }

    public void setSterilise(Boolean sterilise) {
        this.sterilise = sterilise;
    }

    public Boolean getVermifuge() {
        return vermifuge;
    }

    public void setVermifuge(Boolean vermifuge) {
        this.vermifuge = vermifuge;
    }

    public Boolean getMarechal() {
        return marechal;
    }

    public void setMarechal(Boolean marechal) {
        this.marechal = marechal;
    }

    public Boolean getDentiste() {
        return dentiste;
    }

    public void setDentiste(Boolean dentiste) {
        this.dentiste = dentiste;
    }
}
