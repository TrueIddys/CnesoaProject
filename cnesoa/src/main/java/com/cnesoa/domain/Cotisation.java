package com.cnesoa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Maxime on 30/03/2016.
 */
@Entity
public class Cotisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateFin;
    private Date dateDebut;

    private Integer tarif;

    public Date getDateFin(){
        return dateFin;
    }

    public void setDateFin(Date dateFin){
        this.dateFin = dateFin;
    }

    public Date getDateDebut(){
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut){
        this.dateDebut = dateDebut;
    }

    public Integer getTarif(){
        return tarif;
    }

    public void setTarif(Integer tarif){
        this.tarif = tarif;
    }

    public String toString(){
        return this.tarif.toString();
    }
}
