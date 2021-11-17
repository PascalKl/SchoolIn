package com.example.schoolin;

public class searchpageMemo {
    private String schule;
    private String standort;
    private long id;

    public searchpageMemo(String schule, String standort, long id) {
        this.schule = schule;
        this.standort = standort;
        this.id = id;
    }

    //Get- & Set-Methode für schule

    public String getSchule() {
        return schule;
    }

    public void setSchule(String schule) {
        this.schule = schule;
    }

    //Get- & Set-Methode für standort

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    //Get- & Set-Methode für id

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
