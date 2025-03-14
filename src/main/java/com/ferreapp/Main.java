package com.ferreapp;

import java.util.Map;

import com.ferreapp.domain.entities.Eps;
import com.ferreapp.infrastructure.adapter.ui.EpsUI;


public class Main {
    public static void main(String[] args) {
        EpsUI epsUI = new EpsUI();
        Map<Integer,Eps> myEps = epsUI.FindAllEps();
        //myEps.forEach((k,v) -> System.out.println("Id: " + k + " Nombre: " + v.getName()));
        // for (Eps eps : epsUI.FindAllEpsList("Coomeva", myEps)) {
        //     System.out.println("Id: " + eps.getId() + " Nombre: " + eps.getName());
        // }
        epsUI.FindAllEpsList("Coomeva", myEps).iterator().forEachRemaining(eps -> System.out.println("Id: " + eps.getId() + " Nombre: " + eps.getName()));

    }
}