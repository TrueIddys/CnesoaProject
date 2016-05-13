package com.cnesoa.domain.Person;

import com.cnesoa.domain.Consultation.InfosConsult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maxime on 06/04/2016.
 */

@Entity
@DiscriminatorValue("Professeur")
public class Professeur extends User implements Serializable {

    //code de validation de bilan
    private String code;

    //matière enseigné par le professeur
    private String matiere;

    //liste de ses spécialités
    @ElementCollection
    private Map<String, Boolean> specialite = new HashMap<>();

    //liste des consultations auxquels il est lié
    @OneToMany(mappedBy = "professeur")
    private List<InfosConsult> infosConsults = new ArrayList<>();

    /*_________________________________*/

    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = new BCryptPasswordEncoder().encode(code);
    }

    public List<InfosConsult> getInfosConsult() {
        return infosConsults;
    }

    public void setInfosConsult(List<InfosConsult> infosConsults) {
        this.infosConsults = infosConsults;
    }

    public Map<String, Boolean> getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Map<String, Boolean> specialite) {
        this.specialite = specialite;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    /*______________________________________________*/

    public Professeur(){
        super();
        remplirSpecialite();
    }

    public Professeur(String nom, String prenom, String mail, String tel){
        super(nom, prenom, mail, tel);
        remplirSpecialite();
    }

    public void addInfosConsult(InfosConsult infosConsult){
        this.infosConsults.add(infosConsult);
    }

    public void removeInfosConsult(InfosConsult infosConsult){
        this.infosConsults.remove(infosConsult);
    }

    //récupère le prénom + nom du professeur
    public String getName(){
        return super.getName();
    }

    /**
     * Remplis la liste de specialité possible d'un professeur
     */
    public void remplirSpecialite(){
        try {

            specialite.put(new String("Petits".getBytes(), "UTF-8"), false);
            specialite.put(new String("Grands".getBytes(), "UTF-8"), false);
            specialite.put(new String("Bovins".getBytes(), "UTF-8"), false);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Person o) {
        int d = getNom().compareTo(o.getNom());
        if (d == 0)
            d = getPrenom().compareTo(o.getPrenom());
        return d;
    }
}
