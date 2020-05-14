package com.example.petitougrand.Modèle;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import com.example.petitougrand.Controler.Controleur;
import com.example.petitougrand.Modèle.Enum.EnumPlace;
import com.example.petitougrand.Modèle.Enum.EnumType;

public class Modèle_Jeu {

    private List<EnumType> tas;
    private List<EnumType> paquet_J1;
    private List<EnumType> paquet_J2;
    private int joueurActuel;

    private Controleur controleur;

    public Modèle_Jeu(Controleur controleur){
        tas = new ArrayList<>();
        paquet_J1 = new ArrayList<>();
        paquet_J2 = new ArrayList<>();
        this.controleur = controleur;

        InitiatisationPaquet();

        Log.d("DECK", toString());
    }

    /**
     * Reception du choix par l'intermédiaire des boutons
     *
     * @param choix -2 passe, -1 inférieur, 0 égal, 1 supérieur
     */
    public void ReceptionChoix(int choix){
        Log.e("BOUTONS", "reception de " + choix);

        switch (choix){
            case -1:
                controleur.sendRendererOrder(EnumPlace.j_1, EnumType.Carré);
                break;
            case 0:
                controleur.sendRendererOrder(EnumPlace.j_1, EnumType.Pentagone);
                break;
            case +1:
                controleur.sendRendererOrder(EnumPlace.j_1, EnumType.Caché);
                break;
        }
    }

    /**
     * Initialise les paquets des deux joueurs en répartissant les 52 cartes
     * Il n'y en a pas 61 parce qu'il manque une forme, j'avais plus d'idée.
     */
    public void InitiatisationPaquet(){

        List<EnumType> deck = new ArrayList<>();

        for(int i = 0; i < 8; i++)
            deck.add(EnumType.Triangle);

        for(int i = 0; i < 9; i++)
            deck.add(EnumType.Carré);

        for(int i = 0; i < 9; i++)
            deck.add(EnumType.Pentagone);

        for(int i = 0; i < 9; i++)
            deck.add(EnumType.Rubis);

        for(int i = 0; i < 9; i++)
            deck.add(EnumType.Gemme);

        for(int i = 0; i < 8; i++)
            deck.add(EnumType.Croix);

        //Log.d("DECK", "taille: " + deck.size());
        tas.add(deck.get(0));
        deck.remove(0);

        //Initialise les decks
        for(int i = 0; i < deck.size(); i++){
            EnumType carte = deck.get(i);

            if(i % 2 == 0)
                paquet_J1.add(carte);
            else
                paquet_J2.add(carte);
        }

        //On mélange les piles
        Collections.shuffle(paquet_J1);
        Collections.shuffle(paquet_J2);
    }

    /**
     * Evaluation d'un guess
     *
     * @return
     */
    private void EvaluationChoix(){

    }

    public String toString(){
        String resultat = "";

        resultat += "tas taille: " + tas.size() + "\n";
        for(int i = 0; i < tas.size(); i++)
            resultat += tas.get(i).name() + "\n";

        resultat += "paquet J1 taille: " + paquet_J1.size() + "\n";
        for(int i = 0; i < paquet_J1.size(); i++)
            resultat += paquet_J1.get(i).name() + "\n";

        resultat += "paquet J2 taille: " + paquet_J2.size() + "\n";
        for(int i = 0; i < paquet_J2.size(); i++)
            resultat += paquet_J2.get(i).name() + "\n";

        return resultat;
    }
}
