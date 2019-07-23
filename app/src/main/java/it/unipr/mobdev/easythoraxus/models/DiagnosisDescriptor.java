package it.unipr.mobdev.easythoraxus.models;

public class DiagnosisDescriptor {
    private String name;
    private String description;

    public DiagnosisDescriptor(){ }

    /*public DiagnosisDescriptor(String name, String description){
        super();
        this.name = name;
        this.description = description;
    }*/

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
