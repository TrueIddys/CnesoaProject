package com.cnesoa.domain.Person;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Person.Contact.Adresse;
import com.cnesoa.utils.Cotisation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxime on 30/03/2016.
 */

@Entity
@Table(name = "client")
@DiscriminatorValue("Client")
public class Client extends Person implements Serializable {

    //prix de la cotisation
    private Integer cotisation;

    //TODO mieux gérer la cotisation, demander à sophie
    //date de début de la cotisation
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date debutCotisation;

    //adresse du client
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Adresse adresse;

    //liste des animaux du client
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animaux = new ArrayList<>();

    /*______________________________*/

    public List<Animal> getAnimaux(){
        return animaux;
    }

    public void setAnimaux(List<Animal> animaux){
        this.animaux = animaux;
    }

    public Integer getCotisation() {
        return cotisation;
    }

    public void setCotisation(Integer cotisation) {
        this.cotisation = cotisation;
    }

    public Date getDebutCotisation() {
        return debutCotisation;
    }

    public void setDebutCotisation(Date debutCotisation) {
        this.debutCotisation = debutCotisation;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    /*___________________________________________________*/

    public Client(){
        super();
    }

    public Client(String nom, String prenom, String mail, String tel){
        super(nom, prenom, mail, tel);
    }

    public Client(String nom, String prenom, String mail, String tel, Adresse adresse){
        super(nom, prenom, mail, tel);
        if (adresse == null){
            throw new IllegalArgumentException("L'adresse est null.");
        }
        this.adresse = adresse;
    }

    //ajouter un animal
    public void addAnimal(Animal animal){
        if (animal == null){
            throw new IllegalArgumentException("L'animal ne peut pas être ajouté");
        }

        this.animaux.add(animal);
    }

    public void removeAnimal(Animal animal){
        this.animaux.remove(animal);
    }

    /**
     * permet de calculer la date de fin de la cotisation
     * @return
     */
    public Date getFinCotisation(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(debutCotisation);
        cal.add(Cotisation.dureeCotisationFormat,  Cotisation.nombreDuree);
        return cal.getTime();
    }

    /**
     * Permet de formater la cotisation afin de récupérer les informations nécessaires pour
     * l'utilisateur
     * @return
     */
    public String getFormatCotisation(){
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        if (cotisation != null){
            if (cotisation == 20){
                return "Paiement à la séance";
            }
            else {
                return cotisation.toString() + " euros, valable jusqu'au " + dt.format(getFinCotisation());
            }
        }
        else {
            return "Pas de cotisation souscrite.";
        }
    }

}
