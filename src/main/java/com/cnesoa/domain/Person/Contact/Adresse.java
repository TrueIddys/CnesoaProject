package com.cnesoa.domain.Person.Contact;

import com.cnesoa.domain.Person.Client;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maxime on 20/04/2016.
 */

@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numero;
    
    private String rue;

    private String ville;

    private String codePostal;

    @OneToOne
    private Client client;

    /*___________________*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Client getClient(){
        return this.client;
    }

    public void setClient(Client client){
        this.client = client;
    }


    /*__________________*/

    public Adresse(){

    }

    public Adresse(Client client){
        this.client = client;
    }

    public Adresse(String numero, String rue, String codePostal, String ville){
        this.codePostal = formatCodePostal(codePostal);
        this.numero = formatNumero(numero);
        this.ville = formatVille(ville);
        this.rue = formatRue(rue);
    }

    //vérifie le format du code postal
    private String formatCodePostal(String codePostal){
        codePostal = codePostal.trim();

        Pattern p = Pattern.compile("([0-9]{5})");
        Matcher m = p.matcher(codePostal);
        if (!m.find()) {
            throw new IllegalArgumentException("Une code postal doit être composé de 5 chiffres : '" + codePostal + "' donné");
        }

        return codePostal;
    }

    //vérifie le format de la ville
    private String formatVille(String ville) {
        ville = ville.trim();
        ville = ville.toLowerCase();
        return WordUtils.capitalize(ville, ' ', '_', '-');
    }

    //vérifie le format du numéro
    private String formatNumero(String numero) {
        numero = numero.trim();
        numero = numero.toLowerCase();

        Pattern p = Pattern.compile("([0-9]+)[\\s]*(.*)?");
        Matcher m = p.matcher(numero);
        if (!m.find()) {
            throw new IllegalArgumentException("Une numéro doit etre composé d'un numéro " +
                    "(et optionnellement de 'bis', 'ter', ...) : '" + numero + "' a été donné");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(m.group(1));

        if (!StringUtils.isBlank(m.group(2))) {
            sb.append(" ").append(m.group(2));
        }

        return WordUtils.capitalize(sb.toString());
    }

    //vérifie le format de la rue
    private String formatRue(String rue) {
        rue = rue.trim();
        rue = rue.toLowerCase();
        return WordUtils.capitalize(rue, ' ', '_', '-');
    }

    //récupère la rue en combinant numéro et nom de rue
    public String getNumRue(){
        return numero + " " + rue;
    }
}
