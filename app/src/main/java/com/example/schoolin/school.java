package com.example.schoolin;

public class school {
    private int id;
    private String name;
    private String location;
    private String description;
    private String website;
    private String education1;
    private String education2;
    private String education3;

    public school(int id, String name, String location, String description, String website, String education1, String education2, String education3) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.website = website;
        this.education1 = education1;
        this.education2 = education2;
        this.education3 = education3;
    }

    //to String is necessary for printing the contents of a class object

    @Override
    public String toString() {
        return
                "id= " + id +
                        ", Name= " + name +
                        ", Ort= " + location +
                        ", Profile= " + education1 + ", " + education2 + ", " + education3;
    }

    //Getter & Setters


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEducation1() {
        return education1;
    }

    public void setEducation1(String education1) {
        this.education1 = education1;
    }

    public String getEducation2() {
        return education2;
    }

    public void setEducation2(String education2) {
        this.education2 = education2;
    }

    public String getEducation3() {
        return education3;
    }

    public void setEducation3(String education3) {
        this.education3 = education3;
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
