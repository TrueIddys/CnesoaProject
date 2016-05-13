package com.cnesoa.domain.Person;

import com.cnesoa.domain.Person.Contact.Contact;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Maxime on 05/04/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Comparable<Person>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="person_id")
    private Long id;

    //les informations de contact d'une personne
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Contact contact;

    /*____________________*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /*_____________________________*/

    public Person(){
        this.contact = new Contact(this);
    }

    public Person (String nom, String prenom, String mail, String tel){
        if (nom == null){
            throw new IllegalArgumentException("Le nom est null.");
        }
        if (prenom == null){
            throw new IllegalArgumentException("Le nom est null.");
        }
        if (mail == null){
            throw new IllegalArgumentException("Le nom est null.");
        }
        if (tel == null){
            throw new IllegalArgumentException("Le nom est null.");
        }
        this.contact = new Contact(this);
        this.contact.setNom(nom);
        this.contact.setPrenom(prenom);
        this.contact.setMail(mail);
        this.contact.setTel(tel);
    }

    //permet de récupérer le prénom
    public String getPrenom(){
        return this.contact.getPrenom();
    }

    //permet de récupérer le nom
    public String getNom(){
        return this.contact.getNom();
    }


    //permet de récupérer le prénom + nom
    public String getName(){
        return getPrenom() + " " + getNom();
    }

    @Override
    public int compareTo(Person o){
        int d = getNom().compareTo(o.getNom());
        if (d == 0)
            d = getPrenom().compareTo(o.getPrenom());
        return d;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person other = (Person) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equals(this.getId(), other.getId());
    }

}
