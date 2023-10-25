package com.example.hikemanagementapp;

public class ModelHike {

    private String id, name, location, parking, date, length, level, description, memberQuan, type;

    public ModelHike(String id, String name, String location, String parking, String date, String length, String level, String description, String memberQuan, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.parking = parking;
        this.date = date;
        this.length = length;
        this.level = level;
        this.description = description;
        this.memberQuan = memberQuan;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemberQuan() {
        return memberQuan;
    }

    public void setMemberQuan(String memberQuan) {
        this.memberQuan = memberQuan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
