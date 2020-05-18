package com.example.petitougrand.Modèle.Enum;

public enum EnumPlaceRender {
    ref_0(new float[]{-4.0f, 8.0f}),
    ref_1(new float[]{-2.7f, 8.0f}),
    ref_2(new float[]{-1.4f, 8.0f}),
    ref_3(new float[]{-0.0f, 8.0f}),
    ref_4(new float[]{+1.3f, 8.0f}),
    ref_5(new float[]{+3.0f, 8.0f}),
    j_1(new float[]{-3.0f, 0.0f}),
    tas(new float[]{-0.0f, 0.0f}),
    j_2(new float[]{+3.0f, 0.0f}),
    Inférieur(new float[]{-3.5f, -7.0f}),
    Egal(new float[]{+0.0f, -7.0f}),
    Supérieur(new float[]{+3.5f, -7.0f}),
    Pass(new float[]{+0.0f, -9.0f}),
    indJ_1(new float[]{-3.0f, 5.0f}),
    indJ_2(new float[]{+3.0f, 5.0f});

    public final float[] position;

    EnumPlaceRender(float[] valeur){
        this.position = valeur;
    }
}
