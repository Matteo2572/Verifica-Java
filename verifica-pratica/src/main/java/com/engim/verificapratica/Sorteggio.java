package com.engim.verificapratica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorteggio {
    private List<Squadra> squadre = new ArrayList<>();
    private boolean daMischiare = true;
    private int nextName = 0;
    public void aggiungi(String nome, String nazione){
        squadre.add(new Squadra(nome,nazione));
        daMischiare = true;
        nextName = 0;
    }

    public Squadra next(){
        if(daMischiare){
            Collections.shuffle(squadre);
            daMischiare = false;
            nextName = 0;
        }
        if(nextName >= squadre.size())
            throw new IndexOutOfBoundsException("squadre finite");
        return squadre.get(nextName++);
    }



    public List<Squadra> getSquadre() {
        return squadre;
    }

    public boolean isDaMischiare() {
        return daMischiare;
    }
}
