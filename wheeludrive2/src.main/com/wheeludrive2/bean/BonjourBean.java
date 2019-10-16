package com.wheeludrive2.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class BonjourBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            nom;

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }
}
