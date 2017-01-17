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
public class DataRL {
    private byte b;
    private int c;

    public DataRL(byte b) {
        this.b = b;
        c=1;
    }

    public void add(){
        c++;
    }

    public boolean isPenuh(){
        return 258==c;
    }

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public boolean oleh(){
        return c>3;
    }
}