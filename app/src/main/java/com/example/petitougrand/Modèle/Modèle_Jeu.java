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

    private Deque<EnumType> tas;
    private Deque<EnumType> paquet_J1;
    private Deque<EnumType> paquet_J2;
    private Deque<EnumType> paquet_temp;
    private int joueurActuel;

    private Controleur controleur;

    public Modèle_Jeu(Controleur controleur){
        tas = new ArrayDeque<>();
        paquet_J1 = new ArrayDeque<>();
        paquet_J2 = new ArrayDeque<>();
        paquet_temp = new ArrayDeque<>();
        this.controleur = controleur;

        InitiatisationPaquet();
        joueurActuel = 1;

        controleur.sendRendererOrder(EnumPlace.j_1, EnumType.Caché);
        controleur.sendRendererOrder(EnumPlace.j_2, EnumType.Caché);
        controleur.sendRendererOrder(EnumPlace.tas, tas.getFirst());
        controleur.sendRendererOrder(EnumPlace.indJ_1, EnumType.Indic);

        Log.e("DECK", debugPaquet());
    }

    /**
     * Reception du choix par l'intermédiaire des boutons
     *
     * @param pari -2 passe, -1 inférieur, 0 égal, 1 supérieur
     */
    public void ReceptionChoix(int pari) {

        if (paquet_J1.size() > 0 && paquet_J2.size() > 0) {
            Log.e("DECK", debugPaquet());

            if (pari == -2) {
                ChangementDeTour();

                //Met les cart de paquet_temps sur le tas
                for (int i = 0; i < paquet_temp.size(); i++) {
                    tas.addFirst(paquet_temp.pop());
                }

                controleur.sendRendererOrder(EnumPlace.tas, tas.getFirst());
            }
            else{
                if (PariGagne(pari)) {
                    //On affiche la carte
                    if (joueurActuel == 1) {
                        controleur.sendRendererOrder(EnumPlace.j_1, paquet_J1.getFirst());

                        //On l'ajoute au tas_temp
                        paquet_temp.add(paquet_J1.pop());
                    } else {
                        controleur.sendRendererOrder(EnumPlace.j_2, paquet_J2.getFirst());

                        //On l'ajoute au tas_temp
                        paquet_temp.add(paquet_J2.pop());
                    }

                } else {
                    //On remet toutes les cartes dans le paquet
                    for (int i = 0; i < paquet_temp.size(); i++) {
                        if(joueurActuel == 1)
                            paquet_J1.addLast(paquet_temp.pop());
                        else
                            paquet_J2.addLast(paquet_temp.pop());
                    }

                    ChangementDeTour();
                }
            }
        }else{
            //Si quelqu'un a gagné
            controleur.sendRendererOrder(EnumPlace.j_1, EnumType.Rubis);
            controleur.sendRendererOrder(EnumPlace.tas, EnumType.Rubis);
            controleur.sendRendererOrder(EnumPlace.j_2, EnumType.Rubis);
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

        //On mélange le deck
        Collections.shuffle(deck);

        tas.addFirst(deck.get(0));
        deck.remove(0);

        //Initialise les decks
        for(int i = 0; i < deck.size(); i++){
            EnumType carte = deck.get(i);

            if(i % 2 == 0)
                paquet_J1.addFirst(carte);
            else
                paquet_J2.addFirst(carte);
        }
    }

    /**
     * Evaluation d'un pari
     * Compare le sommet de la pile du joueur actuel avec le tas
     *
     * @param -1 0 1 le pari qui a été fait
     * @return True si on gagne le pari, False sinon
     */
    private boolean PariGagne(int pari){
        EnumType carteJoueur;
        EnumType carteTas = tas.getFirst();

        if(joueurActuel == 1)
            carteJoueur = paquet_J1.getFirst();
        else
            carteJoueur = paquet_J2.getFirst();

        if(pari == -1){
            if(carteJoueur.valeur < carteTas.valeur)
                return true;
        }

        if(pari == 0){
            if(carteJoueur.valeur == carteTas.valeur)
                return true;
        }

        if(pari == +1){
            if(carteJoueur.valeur > carteTas.valeur)
                return true;
        }

        return false;
    }

    /**
     * Change le tour et met à jour l'affichage
     */
    private void ChangementDeTour(){
        if(joueurActuel == 1) {
            joueurActuel = 2;
            controleur.sendRendererOrder(EnumPlace.indJ_1, EnumType.Caché);
            controleur.sendRendererOrder(EnumPlace.indJ_2, EnumType.Indic);
        }
        else {
            joueurActuel = 1;
            controleur.sendRendererOrder(EnumPlace.indJ_1, EnumType.Indic);
            controleur.sendRendererOrder(EnumPlace.indJ_2, EnumType.Caché);
        }
    }

    /**
     * Donne les figues au sommet des paquets pour tricher
     *
     * @return String
     */
    private String debugPaquet(){
        String resultat = "";

        resultat += "\n \ntas: " + tas.getFirst().name() + "\n";
        resultat += "J_1: " + paquet_J1.getFirst().name() + "\n";
        resultat += "J_2: " + paquet_J2.getFirst().name() + "\n";

        resultat += "carte restante J1: " + paquet_J1.size() + "\n";
        resultat += "carte restante J2: " + paquet_J2.size() + "\n";

        return resultat;
    }
}
