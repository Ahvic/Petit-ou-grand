package com.example.petitougrand.Modèle;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.petitougrand.Modèle.Formes.Carré;
import com.example.petitougrand.Modèle.Formes.Croix;
import com.example.petitougrand.Modèle.Formes.Forme_basic;
import com.example.petitougrand.Modèle.Formes.Triangle;

public class Modèle_Jeu {

    private List<Forme_basic> tas;
    private List<Forme_basic> pile_J1;
    private List<Forme_basic> pile_J2;
    private int joueurActuel;

    public Modèle_Jeu(){
        tas = new ArrayList<>();
        pile_J1 = new ArrayList<>();
        pile_J2 = new ArrayList<>();
    }

    /**
     * Reception du choix par l'intermédiaire des boutons
     *
     * @param choix -2 passe, -1 inférieur, 0 égal, 1 supérieur
     */
    public void ReceptionChoix(int choix){
        Log.e("BOUTONS", "reception de " + choix);

    }

    /**
     * Initialise les paquets des deux joueurs en répartissant les 52 cartes
     * Il n'y en a pas 61 parce qu'il manque une forme, j'avais plus d'idée.
     *
     * @Return Array contenant <formeJ1, formeJ2, sommetTas>
     */
    public Forme_basic[] InitiatisationPaquet(){

        /*
        List<Forme_basic> deck = new ArrayList<>();

        for(int i = 0; i < 8; i++)
            deck.add(new Triangle());

        for(int i = 0; i < 9; i++)
            deck.add(new Carré());

        for(int i = 0; i < 9; i++)
            deck.add(new Pentagone());

        for(int i = 0; i < 9; i++)
            deck.add(new Rubis());

        for(int i = 0; i < 9; i++)
            deck.add(new Gemme());

        for(int i = 0; i < 8; i++)
            deck.add(new Croix());

        Collections.shuffle(deck);

        //Initialise le tas
        tas.add(deck.get(0));
        deck.remove(0);

        //Initialise les decks
        for(int i = 0; i < deck.size(); i++){
            double rand = Math.random();

            if(rand < 0.5f)
                pile_J1.add(tas.get(i));
            else
                pile_J2.add(tas.get(i));

            tas.remove(i);
        }

        Forme_basic[] res = new Forme_basic[3];
        res[0] = pile_J1.get(0);
        res[1] = pile_J2.get(0);
        res[2] = tas.get(0);


         */

        Forme_basic[] res = new Forme_basic[3];
        res[0] = new Triangle();
        res[1] = new Carré();
        res[2] = new Croix();

        return res;
    }

}
