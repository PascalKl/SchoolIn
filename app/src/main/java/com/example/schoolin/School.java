package com.example.schoolin;

import java.io.Serializable;

public class School implements Serializable {
    private String schule;
    private String standort;
    private long id;

    public School(String schule, String location) {
        this.schule = schule;
        this.standort = location;
    }

    public School() {

    }

    //Get- & Set-Methode for School

    public String getSchool() {
        return schule;
    }

    public void setSchool(String schule) {
        this.schule = schule;
    }

    //Get- & Set-Methode for location

    public String getLocation() {
        return standort;
    }

    public void setLocation(String standort) {
        this.standort = standort;
    }

    //Get- & Set-Methode for id

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
