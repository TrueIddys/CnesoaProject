package com.cnesoa.domain.Animal;

import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.domain.Person.Client;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Maxime on 30/03/2016.
 */

@Entity
@Table(name = "animal")
@Indexed
public class Animal implements Serializable, Comparable<Animal> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    //nom de l'animal
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String nom;

    //race de l'animal (berger allemand, labrador etc ...)
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String race;

    //type de l'animal (chien, chat etc...)
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String type;

    //date de naissance
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateNaissance;

    //robe de l'animal
    private String robe;

    //sexe de l'animal
    private String sexe;

    //le propriétaire de l'animal
    @ManyToOne
    private Client client;

    //la fiche médicale de l'animal
    @OneToOne(mappedBy = "animal",cascade = CascadeType.ALL)
    private FicheMedicale ficheMedicale;

    //les consultations de l'animal
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consultation> consultations = new ArrayList<>();

    /*____________________________________________*/

    public Animal() {

    }

    public Animal(String nom, String race, String sexe){
        if (nom == null)
            throw new IllegalArgumentException("Le nom est null.");
        if (race == null)
            throw new IllegalArgumentException("La race est null.");
        if (sexe == null)
            throw new IllegalArgumentException("Le sexe est null.");
        this.nom = nom;
        this.race = race;
        this.sexe = sexe;
    }

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

    public FicheMedicale getFicheMedicale() {
        return ficheMedicale;
    }

    public void setFicheMedicale(FicheMedicale ficheMedicale) {
        this.ficheMedicale = ficheMedicale;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public void addConsultation(Consultation consultation){
        if (consultation == null){
            throw new IllegalArgumentException("La consultation ne peut pas être ajoutée.");
        }
        this.consultations.add(consultation);
    }

    public void removeConsultation(Consultation consultation){
        this.consultations.remove(consultation);
    }

    /*Utils methods*/

    public String toString(){
        return getNom();
    }

    @Override
    public int compareTo(Animal o) {
        int d = getNom().compareTo(o.getNom());
        return d;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal other = (Animal) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equals(this.getId(), other.getId());
    }
}
