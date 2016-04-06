package com.cnesoa.domain;

import javax.persistence.*;

/**
 * Created by Maxime on 06/04/2016.
 */

@Entity
public class FicheMedicale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String origine;

    private String etatGeneral;

    private String antMed;

    private String antChir;

    private String antTraum;

    private Boolean vaccinMaj;

    private Boolean sterilise;

    private Boolean vermifuge;

    private Boolean marechal;

    private Boolean dentiste;

    private String sport;

    private String alimentation;

    private String comportement;

    private String infoComp;

    @OneToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    /*_____________________________________________*/

    public FicheMedicale(){

    }

    public FicheMedicale(Animal animal){
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAntMed() {
        return antMed;
    }

    public void setAntMed(String antMed) {
        this.antMed = antMed;
    }

    public String getAntChir() {
        return antChir;
    }

    public void setAntChir(String antChir) {
        this.antChir = antChir;
    }

    public String getAntTraum() {
        return antTraum;
    }

    public void setAntTraum(String antTraum) {
        this.antTraum = antTraum;
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getAlimentation() {
        return alimentation;
    }

    public void setAlimentation(String alimentation) {
        this.alimentation = alimentation;
    }

    public String getComportement() {
        return comportement;
    }

    public void setComportement(String comportement) {
        this.comportement = comportement;
    }

    public String getInfoComp() {
        return infoComp;
    }

    public void setInfoComp(String infoComp) {
        this.infoComp = infoComp;
    }
}
