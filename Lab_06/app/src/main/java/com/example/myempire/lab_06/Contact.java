package com.example.myempire.lab_06;

/**
 * Created by MyEmpire on 2016-11-04.
 */

public class Contact {

    private long ID = -1L;
    private String firstName = null;
    private String lastName = null;
    private String phone = null;

    public Contact(long ID, String FN, String LN, String phone){
        setID(ID);
        setFirstName(FN);
        setLastName(LN);
        setPhone(phone);
    }

    public void setID(long ID) { this.ID = ID; }

    public long getID(){ return ID; }

    public void setFirstName(String FN){ this.firstName = FN; }

    public String getFirstName() { return firstName; }

    public void setLastName(String LN){ this.lastName = LN; }

    public String getLastName() { return lastName; }

    public void setPhone(String phone){ this.phone = phone; }

    public String getPhone(){ return phone; }
}
