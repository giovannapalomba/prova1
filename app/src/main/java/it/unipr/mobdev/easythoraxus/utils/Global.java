package it.unipr.mobdev.easythoraxus.utils;

import java.util.ArrayList;

import it.unipr.mobdev.easythoraxus.models.ChronoDescriptor;
import it.unipr.mobdev.easythoraxus.models.ProcedureDescriptor;

public class Global {

    //private Global() {    }
    public static String gender = "M"; // default è M
    public static String height, age, weight;
    public static String selected_option;
    public static int n_step;
    public static Long time_start; //mi serve per la durata di ogni step_activity
    //public static ArrayList<String> to_save = new ArrayList<>();//formato: procedura,timestamp inizio,timestamp fine,n step_activity
    public static ArrayList<String> step_time = new ArrayList<>(); //salvo i tempi di tutti gli step_activity
    //public static String filename = "time_history";


    public static ChronoDescriptor current_time_log, selected_chrono;
    public static ProcedureDescriptor chosen_procedure;
    /*public static StepDescriptor current_step;
       public static DiagnosisDescriptor current_diagnosis;*/

    public static final String PROCEDURE_URL = "http://10.0.2.2:3000/procedure";
    public static final String STEP_URL = "http://10.0.2.2:3000/step/";
    public static final  String DIAGNOSIS_URL = "http://10.0.2.2:3000/diagnosi";
    public static final int LOADER_ID = 1;

    //localhost è 10.0.2.2 da emulatore
    //192.168.137.1 da device usb?
}
