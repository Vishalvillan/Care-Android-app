package com.example.care;

import java.sql.Time;

public class Person {
    String Name;
    String SubTitle;
    String SubTime;
    String Em;

    public Person(String name, String subTitle,String t,String email) {
        Name = name;
        SubTitle = subTitle;
        SubTime=t;
        Em=email;
    }
    public String getEm() {
        return Em;
    }

    public void setEm(String em) {
        Em = em;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }
    public String getTime() {
        return SubTime;
    }

    public void setTime(String t) {
        SubTime=t;
    }
}
