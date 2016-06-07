package com.cnesoa.domain.Person.Contact;

import com.cnesoa.domain.Person.Person;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;

/**
 * Created by Maxime on 20/04/2016.
 */

@Entity
@Table(name = "contact")
@Indexed
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //email
    @Column(unique = true)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String mail;

    //numéro de téléphone
    @Column(unique = true)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String tel;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String nom;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String prenom;

    //personne liée aux informations de contact
    @OneToOne
    private Person person;

    /*___________________*/

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Person getPerson() { return person;}

    public void setPerson(Person person) {this.person = person;}

    /*_______________________________*/

    public Contact(){

    }

    public Contact(Person person){
        this.person = person;
    }
}
