package com.example.petitougrand.Controler;

import com.example.petitougrand.Modèle.Enum.EnumType;
import com.example.petitougrand.Modèle.Modèle_Jeu;

import com.example.petitougrand.Modèle.Enum.EnumPlace;
import com.example.petitougrand.Vue.MyGLRenderer;

public class Controleur {

    private static Controleur instance = null;

    private Modèle_Jeu modele;
    private MyGLRenderer renderer;

    public Controleur(MyGLRenderer renderer){
        // Responsable du déroulement de la partie
        this.modele = new Modèle_Jeu(this);
        this.renderer = renderer;
        instance = this;
    }

    public static Controleur getInstance(){
        return instance;
    }

    public void ReceiveInput(int input){
        modele.ReceptionChoix(input);
    }

    public void sendRendererOrder(EnumPlace emplacement, EnumType objet){
        renderer.aDessiner(emplacement, objet);
    }
}
