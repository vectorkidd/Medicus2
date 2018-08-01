package com.vector.medicus1;

/**
 * Created by Khalid on 3/10/2018.
 */

public class Docdetails  {
    private int ddob,linum;
    private String dfname,dgender,speciality,hospital;

    public Docdetails( String dfname, int ddob,String dgender, String speciality,String hospital,int linum){

        this.dfname=dfname;
        this.ddob=ddob;
        this.dgender=dgender;
        this.speciality=speciality;
        this.hospital=hospital;
        this.linum=linum;

    }





    public String getDfname() {
        return dfname;
    }

    public void setDfname(String dfname) {
        this.dfname = dfname;
    }

    public int getDdob() {
        return ddob;
    }

    public void setDdob(int ddob) {
        this.ddob = ddob;
    }

    public String getDgender() {
        return dgender;
    }

    public void setDgender(String dgender) {
        this.dgender = dgender;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getLinum() {
        return linum;
    }

    public void setLinum(int linum) {
        this.linum = linum;
    }
}
