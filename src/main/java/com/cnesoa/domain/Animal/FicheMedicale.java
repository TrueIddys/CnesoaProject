package com.cnesoa.domain.Animal;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maxime on 06/04/2016.
 */

@Entity
public class FicheMedicale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //origine de l'animal
    private String origine;

    //etat général de l'animal
    private String etatGeneral;

    //antécédents médicaux
    private String antMed;

    //antécédents chirurgicaux
    private String antChir;

    //antécédents traumatiques
    private String antTraum;

    //liste des différentes cases à cocher
    @ElementCollection
    private Map<String, Boolean> medical = new HashMap<>();

    //habitude sportive
    private String sport;

    private String alimentation;

    private String comportement;

    //informations complémentaires
    private String infoComp;

    @OneToOne(cascade = CascadeType.ALL)
    private Animal animal;

    /*_____________________________________________*/

    public FicheMedicale(){
        remplirMedical();
    }

    public FicheMedicale(Animal animal){
        remplirMedical();
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

    public void setId(Long id) {this.id = id;}

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

    public Map<String, Boolean> getMedical() {
        return medical;
    }

    public void setMedical(Map<String, Boolean> medical) {
        this.medical = medical;
    }

    /*__________________*/

    /**
     * Remplis la liste medical avec les différentes cases à cocher
     */
    public void remplirMedical() {

        try {

            medical.put(new String("Vaccins à jour".getBytes(), "UTF-8"), false);
            medical.put(new String("Stérilisé(e)".getBytes(), "UTF-8"), false);
            medical.put(new String("Vermifugé(e)".getBytes(), "UTF-8"), false);
            medical.put(new String("Maréchal".getBytes(), "UTF-8"), false);
            medical.put(new String("Dentiste".getBytes(), "UTF-8"), false);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
