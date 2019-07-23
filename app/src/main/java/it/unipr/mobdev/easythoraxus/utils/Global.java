package it.unipr.mobdev.easythoraxus.utils;

import java.util.ArrayList;

import it.unipr.mobdev.easythoraxus.models.ChronoDescriptor;
import it.unipr.mobdev.easythoraxus.models.ProcedureDescriptor;

public class Global {

    //private Global() {    }

   // public static String chosen_procedureDescriptor, question, first_answer, second_answer, selected_option;
    public static String selected_option;
    public static int n_step = 1;
    public static String gender = "M"; // default è M
    public static String height, age, weight;
    public static Long time_start; //mi serve per la durata di ogni step
    public static ArrayList<String> to_save = new ArrayList<>();//formato: procedura,timestamp inizio,timestamp fine,n step
    public static ArrayList<String> step_time = new ArrayList<>(); //salvo i tempi di tutti gli step
    public static String filename = "time_history";
    public static String selected_line;
    public static String selected_line_title;

    public static ChronoDescriptor current_time_log;
    public static ProcedureDescriptor chosen_procedure;
    /*public static StepDescriptor current_step;
    public static DiagnosisDescriptor current_diagnosis;*/
}
