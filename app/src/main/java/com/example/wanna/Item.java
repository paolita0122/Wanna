package com.example.wanna;

public class Item {
    String name;
    int image;
    int days;
    int persons;

    public Item(String name, int image, int days, int persons) {
        this.name = name;
        this.image = image;
        this.days = days;
        this.persons = persons;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getDays() {
        return days;
    }

    public int getPersons() {
        return persons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }
}