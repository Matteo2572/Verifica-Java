package com.engim.verificapratica;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private static Sorteggio sorteggio = new Sorteggio();


    /*
     * ES 1: get ("/add?nome=n&nazione=m") che aggiunge al sorteggio una squadra con nome n e nazione m (utilizzare
     * la classe Sorteggio) - 15 p
     *
     * ES 2: get ("/listaSquadre?nazione=n") che restituisce la lista delle squadre di nazione n - 20 p
     *
     * ES 3: get ("/sorteggia") che restituisce 2 squadre di nazioni diverse (utilizzare la classe Sorteggio, il metodo
     * next. Consiglio: dopo aver sorteggiato la prima, continuare a sorteggiare finché la seconda
     * non è di una nazione diversa) - 20 p
     *
     * ES 4: implementare il sorteggio delle squadre di una fase finale di un torneo a eliminazione diretta.
     * Creare il metodo sorteggiaPartite che:
     * - controlla se il numero di squadre aggiunte è una potenza di 2. Se non lo è lancia una RuntimeException.
     * - Finché ci sono squadre non sorteggiate: sorteggia 2 squadre e le inserisce in un oggetto della classe Partita (da creare)
     * - restituisce la lista di Partite.
     * creare get ("/getPartite") che restituisce la lista appena creata - 30 p
     * */

    //ES1
    @GetMapping(value = "/add")
    public void aggiungiSquadra(@RequestParam(value = "nome") String n, @RequestParam(value = "nazione") String m) {
        sorteggio.aggiungi(n, m);
    }

    //ES2
    @GetMapping(value = "/listaSquadre")
    public List<Squadra> listaSquadre(@RequestParam(value = "nazione") String n) {
        List<Squadra> squadreConN = new ArrayList<>();
        List<Squadra> squadre = sorteggio.getSquadre();
        for (Squadra squadra : squadre) {
            if (squadra.getNazione().contains(n))
                squadreConN.add(squadra);
        }
        return squadreConN;
    }

    //ES3
    @GetMapping(value = "/sorteggia")
    public List<String> sorteggia() {
        List<Squadra> squadraCasa = new ArrayList<>();
        List<Squadra> squadraTrasferta = new ArrayList<>();
        List<String> partita = new ArrayList<>();
        squadraCasa.add(sorteggio.next());
        squadraTrasferta.add(sorteggio.next());
        while (squadraCasa.equals(squadraTrasferta)) {
            squadraTrasferta.add(sorteggio.next());
        }
        partita.add(String.valueOf(squadraCasa));
        partita.add(String.valueOf(squadraTrasferta));
        return partita;
    }
}
