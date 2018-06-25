package com.selenium.models;

import com.selenium.enums.GenderEnum;

/**
 * Created by Dóra on 2018.06.25..
 */
public class BMI {
    private GenderEnum gender;
    private int age;
    private double height;
    private double weight;

    public BMI() {
    }

    public BMI(GenderEnum gender, int age, double height, double weight) {
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }


    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
