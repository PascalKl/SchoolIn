package com.example.schoolin;

public class educationalbackground {

    private long id;

    private String education;

    public educationalbackground(long id, String education) {
        this.id = id;
        this.education = education;
    }

    //Get- & Set Methods for id

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //
    //Get- & Set Methods for education

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
    //
}
