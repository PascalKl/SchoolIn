package com.example.schoolin;

public class school {
    private String schule;
    private String standort;
    private long id;

    public school(String schule, String standort, long id) {
        this.schule = schule;
        this.standort = standort;
        this.id = id;
    }

    //Get- & Set-Methode for school

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
