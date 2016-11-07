package com.example.myempire.lab_06;

public class Contact {

    private String ID = null;
    private String firstName = null;
    private String lastName = null;
    private String phone = null;

    public Contact(String ID, String FN, String LN, String phone){
        setID(ID);
        setFirstName(FN);
        setLastName(LN);
        setPhone(phone);
    }

    public void setID(String ID) { this.ID = ID; }

    public String getID(){ return ID; }

    public void setFirstName(String FN){ this.firstName = FN; }

    public String getFirstName() { return firstName; }

    public void setLastName(String LN){ this.lastName = LN; }

    public String getLastName() { return lastName; }

    public void setPhone(String phone){ this.phone = phone; }

    public String getPhone(){ return phone; }
}
