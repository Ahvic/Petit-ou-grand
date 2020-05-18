package com.example.petitougrand.Modèle.Enum;

public enum EnumType {
    Triangle(1),
    Carré(2),
    Pentagone(3),
    Rubis(4),
    Gemme(5),
    Croix(6),
    Caché(0),
    Indic(0);

    public final int valeur;

    EnumType(int valeur){
        this.valeur = valeur;
    }

    public String toString(){
        return this.name();
    }
}
