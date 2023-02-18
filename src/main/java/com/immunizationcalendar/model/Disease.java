package com.immunizationcalendar.model;


public class Disease {

    private String name;
    private Recommendation recommendation;
    private VaccineType defaultVaccine;
    private boolean checked;

    public Disease(String name, Recommendation recommendation) {
        this.name = name;
        this.recommendation = recommendation;
        this.checked = false;
    }

    public void setDefaultVaccine(VaccineType defaultVaccine) {
        this.defaultVaccine = defaultVaccine;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public VaccineType getDefaultVaccine() {
        return defaultVaccine;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                ", checked=" + checked +
                '}';
    }
}
