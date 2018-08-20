/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author ai
 */
public class Jobs {
    public static final int KOMPRES=1;
    public static final int DEKOMPRES=2;

    private int mode;
    private String asal,ke;
    private org.joda.time.DateTime tgl;
    private java.time.LocalTime durasi;
    private float rasio;

    public Jobs(int mode, String asal) {
        this.mode = mode;
        this.asal = asal;
        if(mode==Jobs.KOMPRES)ke=asal+".pz";
        else ke=asal.substring(0, asal.length()-3);
        tgl=org.joda.time.DateTime.now();
        durasi=java.time.LocalTime.of(0, 0, 0, 0);
        rasio=0;
    }

    public Jobs(int mode, String asal, String ke) {
        this.mode = mode;
        this.asal = asal;
        this.ke = ke;
        tgl=org.joda.time.DateTime.now();
        durasi=java.time.LocalTime.of(0, 0, 0, 0);
        rasio=0;
    }

    public boolean oleh(){
        java.io.File f1=new java.io.File(asal),f2=new java.io.File(ke);
        return f1.exists()&&!f2.exists();
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getKe() {
        return ke;
    }

    public void setKe(String ke) {
        this.ke = ke;
    }

    public org.joda.time.DateTime getTgl() {
        return tgl;
    }

    public void setTgl(org.joda.time.DateTime tgl) {
        this.tgl = tgl;
    }

    public java.time.LocalTime getDurasi() {
        return durasi;
    }

    public void setDurasi(java.time.LocalTime durasi) {
        this.durasi = durasi;
    }

    public float getRasio() {
        return rasio;
    }

    public void setRasio(float rasio) {
        this.rasio = rasio;
    }
}