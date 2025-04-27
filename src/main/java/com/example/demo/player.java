package com.example.demo;

class player {
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int jerseyNumber;
    private int weeklySalary;

    public player(String name, String country, int age, double height, String club, String position, int jerseyNumber,
                  int weeklySalary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.weeklySalary = weeklySalary;
    }

    public player(String playerName) {
        this.name = name;
        this.country = "Unknown"; // Set default values
        this.age = 0;
        this.height = 0.0;
        this.club = "Unknown";
        this.position = "Unknown";
        this.jerseyNumber = 0;
        this.weeklySalary = 0;
    }


    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public String getclub() {
        return club;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setWeeklySalary(int weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public String getPosition() {
        return position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getWeeklySalary() {
        return weeklySalary;
    }
    public String toString() {
        String s ="Name: " + getName()
                + System.lineSeparator() + "Country: " + getCountry() + System.lineSeparator() + "age: " + getAge()
                + System.lineSeparator() + "height(in metres): " + getHeight()
                + System.lineSeparator() + "club: " + getclub() + System.lineSeparator() + "position: " + getPosition()
                + System.lineSeparator() + "jersey Number: " + getJerseyNumber()
                + System.lineSeparator() + "Weekly salary: " + getWeeklySalary() ;

        return s;
    }
}