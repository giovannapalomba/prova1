package it.unipr.mobdev.easythoraxus.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChronoDescriptor {
    private String name;
    private Long ts_start;
    private Long ts_end;
    private String duration;
    private ArrayList<String> time_per_step = new ArrayList<>();

    public ChronoDescriptor(){
        //TODO
    }

    public ChronoDescriptor(String name, Long ts_start, Long ts_end, ArrayList<String> time_per_step){
        super();
        this.name = name;
        this.ts_start = ts_start;
        this.ts_end = ts_end;
        this.time_per_step = time_per_step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTs_start() {
        return ts_start;
    }

    public void setTs_start(Long ts_start) {
        this.ts_start = ts_start;
    }

    public Long getTs_end() {
        return ts_end;
    }

    public void setTs_end(Long ts_end) {
        this.ts_end = ts_end;
    }

    public ArrayList<String> getTime_per_step() {
        return time_per_step;
    }

    public void setTime_per_step(ArrayList<String> time_per_step) {
        this.time_per_step = time_per_step;
    }

    public String getDuration() {
        this.duration = "";
        Long interval_ms = this.ts_end - this.ts_start;
        Date interval = new Date(interval_ms);
        DateFormat formatter = new SimpleDateFormat("ss.SS"); //sec.centsec
        String intervalFormatted = formatter.format(interval);
        this.duration += intervalFormatted + "s";
        return this.duration;
    }
}
