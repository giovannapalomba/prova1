package it.unipr.mobdev.easythoraxus.models;

import java.util.ArrayList;

public class StepDescriptor {

    //TODO aggiungere id?

    private String question;
    private ArrayList<String> answers;
    private String description;
    private String last_question;

    public StepDescriptor(){

    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getDescription() {
        return description;
    }

    public String getLast_question() {
        return last_question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLast_question(String last_question) {
        this.last_question = last_question;
    }

}
