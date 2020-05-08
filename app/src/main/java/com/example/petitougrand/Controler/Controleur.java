package com.example.petitougrand.Controler;

import com.example.petitougrand.Modèle.Modèle_Jeu;

import com.example.petitougrand.Modèle.Formes.Forme_basic;

public class Controleur {

    private static Controleur instance = null;

    private Modèle_Jeu modele;

    public Controleur(){
        // Responsable du déroulement de la partie
        modele = new Modèle_Jeu();
    }

    public static Controleur getInstance(){
        if(instance == null){
            instance = new Controleur();
        }

        return instance;
    }

    public void SendInput(int input){
        modele.ReceptionChoix(input);
    }

    public Forme_basic[] getInitialisation(){
        return modele.InitiatisationPaquet();
    }
}
