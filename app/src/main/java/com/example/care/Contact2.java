package com.example.care;

public class Contact2 {
    private  String name;
    private  String primname;
    public Contact2(String name)
    {
        this.name=name;
    }
    public Contact2(String name,String primname)
    {
        this.name=name;
        this.primname=primname;
    }
    public Contact2()
    {

    }


    public String getName() {
        return name;
    }
    public String getPrimName() {
        return primname;
    }
    public void setPrimName(String primname) {
        this.primname = primname;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setName(String name,String primname) {
        this.name = name;
        this.primname=primname;
    }
}
