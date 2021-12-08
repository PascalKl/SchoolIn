package com.example.schoolin;


public class School {
    private String schoolName;
    private String location;
    private String education;

    public School(String schoolName, String location, String education) {
        this.schoolName = schoolName;
        this.location = location;
        this.education = education;
    }
    public School(){

    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}

