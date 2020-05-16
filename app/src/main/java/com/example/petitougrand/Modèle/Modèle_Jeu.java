package com.example.petitougrand.Modèle;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.petitougrand.Controler.Controleur;
import com.example.petitougrand.Modèle.Enum.EnumPlace;
import com.example.petitougrand.Modèle.Enum.EnumType;

public class Modèle_Jeu {

    private List<EnumType> tas;
    private List<EnumType> paquet_J1;
    private List<EnumType> paquet_J2;
    private List<EnumType> paquet_temp;
    private int joueurActuel;

    private Controleur controleur;

    public Modèle_Jeu(Controleur controleur){
        tas = new ArrayList<>();
        paquet_J1 = new ArrayList<>();
        paquet_J2 = new ArrayList<>();
        paquet_temp = new ArrayList<>();
        this.controleur = controleur;

        InitiatisationPaquet();
        joueurActuel = 1;

        controleur.sendRendererOrder(EnumPlace.j_1, EnumType.Caché);
        controleur.sendRendererOrder(EnumPlace.j_2, EnumType.Caché);
        controleur.sendRendererOrder(EnumPlace.tas, tas.get(0));

        Log.e("DECK", debugPaquet());
    }

    /**
     * Reception du choix par l'intermédiaire des boutons
     *
     * @param pari -2 passe, -1 inférieur, 0 égal, 1 supérieur
     */
    public void ReceptionChoix(int pari) {

        Log.e("DECK", "joueur: " + joueurActuel);

        if(PariGagne(pari))
            Log.e("DECK", "gagne");
        else
            Log.e("DECK", "perdu");

        /*
        if (paquet_J1.size() > 0 && paquet_J2.size() > 0) {
            if (pari == -1 || pari == 0 || pari == 1) {
                if (PariGagne(pari)) {
                    Log.e("DECK", "gagné");

                    //On affiche la carte
                    if (joueurActuel == 1) {
                        controleur.sendRendererOrder(EnumPlace.j_1, paquet_J1.get(0));

                        //On l'ajoute au tas_temp
                        paquet_temp.add(paquet_J1.get(0));
                        paquet_J1.remove(0);

                    } else {
                        controleur.sendRendererOrder(EnumPlace.j_2, paquet_J2.get(0));

                        //On l'ajoute au tas_temp
                        paquet_temp.add(paquet_J2.get(0));
                        paquet_J2.remove(0);
                    }

                } else {
                    Log.e("DECK", "perdu");

                    //On remet toutes les cartes dans le paquet
                    for (EnumType element : paquet_temp) {
                        if(joueurActuel == 1)
                            paquet_J1.add(element);
                        else
                            paquet_J2.add(element);

                        paquet_temp.remove(element);
                    }

                    ChangementDeTour();
                }
            }

            if (pari == -2) {
                ChangementDeTour();

                //Met les cart de paquet_temps sur le tas
                for (EnumType element : paquet_temp) {
                    tas.add(element);
                    paquet_temp.remove(element);
                }

                controleur.sendRendererOrder(EnumPlace.tas, tas.get(0));
            }
        }else{
            controleur.sendRendererOrder(EnumPlace.j_1, EnumType.Rubis);
            controleur.sendRendererOrder(EnumPlace.tas, EnumType.Rubis);
            controleur.sendRendererOrder(EnumPlace.j_2, EnumType.Rubis);
        }
        */

        Log.e("DECK", debugPaquet());
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
        EnumType carteTas = tas.get(0);;

        if(joueurActuel == 1)
            carteJoueur = paquet_J1.get(0);
        else
            carteJoueur = paquet_J2.get(0);

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

    private void ChangementDeTour(){
        if(joueurActuel == 1)
            joueurActuel = 2;
        else
            joueurActuel = 1;
    }

    /**
     * Donne les figues au sommet des paquets pour tricher
     *
     * @return String
     */
    private String debugPaquet(){
        String resultat = "";

        resultat += "\n \ntas: " + tas.get(0).name() + "\n";
        resultat += "J_1: " + paquet_J1.get(0).name() + "\n";
        resultat += "J_2: " + paquet_J2.get(0).name() + "\n";

        return resultat;
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
