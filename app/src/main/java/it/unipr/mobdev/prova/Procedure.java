package it.unipr.mobdev.prova;

import java.util.ArrayList;

public class Procedure {
    public final String nome;
    public final ArrayList<String> abilitazione;


    public Procedure(String nomeProcedura, ArrayList<String> abilit) {
        nome  = nomeProcedura;
        abilitazione = abilit;
    }
}
