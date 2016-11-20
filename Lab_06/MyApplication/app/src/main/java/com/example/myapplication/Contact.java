package com.example.myapplication;

public class Contact {

    private int ID = 0;
    private String firstName = null;
    private String lastName = null;
    private String phone = null;

    public Contact(int ID, String firstName, String lastName, String phone){

        setID(ID);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
    }

    public void setID(int ID) { this.ID = ID;}
    public void setFirstName(String firstName) {this.firstName = firstName; }
    public void setLastName(String lastName) {this.lastName = lastName; }
    public void setPhone(String phone) {this.phone = phone;}

    public int getID() {return ID;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhone() {return phone;}
}
