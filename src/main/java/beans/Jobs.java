/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import org.joda.time.DateTime;

/**
 *
 * @author ai
 */
public class Jobs {
    public static final int KOMPRES=1;
    public static final int DEKOMPRES=2;

    private int mode;
    private String asal,ke;
    private org.joda.time.DateTime tgl,waktu;
    private float efek;
    private boolean terproses;

    public DateTime getTgl() {
        return tgl;
    }

    public void setTgl(DateTime tgl) {
        this.tgl = tgl;
    }

    public DateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(DateTime waktu) {
        this.waktu = waktu;
    }

    public float getEfek() {
        return efek;
    }

    public void setEfek(float efek) {
        this.efek = efek;
    }

    public boolean isTerproses() {
        return terproses;
    }

    public void setTerproses(boolean terproses) {
        this.terproses = terproses;
    }

    public void cek(){
        if(terproses){
            org.joda.time.DateTime d=org.joda.time.DateTime.now();
            waktu=d.minus(tgl.getSecondOfDay());
            java.io.File f1=new java.io.File(asal),f2=new java.io.File(ke);
            efek=100*f1.length()/f2.length();
        }
    }

    public Jobs(int mode, String asal) {
        this.mode = mode;
        this.asal = asal;
        if(mode==Jobs.KOMPRES)ke=asal+".pz";
        else ke=asal.substring(0, asal.length()-3);
        terproses=false;
        efek=0;
        tgl=org.joda.time.DateTime.now();
        waktu=null;
    }

    public Jobs(int mode, String asal, String ke) {
        this.mode = mode;
        this.asal = asal;
        this.ke = ke;
        terproses=false;
        efek=0;
        tgl=org.joda.time.DateTime.now();
        waktu=null;
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
}