package com.example.schoolin;

public class school {
    private int id;
    private String name;
    private String location;
    private String education;

    public school(int id, String name, String location, String schoolEducation) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.education = schoolEducation;
    }

    //to String is necessary for printing the contents of a class object

    @Override
    public String toString() {
        return
                "id= " + id +
                        ", Name= " + name +
                        ", Ort= " + location;
    }

    //getter & Setters

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getId() {
        return ""+id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
