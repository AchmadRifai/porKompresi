/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rifai.porkompresi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ai
 */
public class InterJobs extends javax.swing.JInternalFrame {
private boolean wes;
private beans.Jobs j;
private Thread t;
    /**
     * Creates new form InterJobs
     */
    public InterJobs(beans.Jobs jo) {
        initComponents();
        j=jo;
        inisial();
        wes=false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        asal = new javax.swing.JTextField();
        ke = new javax.swing.JTextField();
        prog = new javax.swing.JProgressBar();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        bitSumber = new javax.swing.JTabbedPane();
        bitTarget = new javax.swing.JTabbedPane();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setText("Sumber");

        jLabel2.setText("Target");

        asal.setEditable(false);

        ke.setEditable(false);

        jTabbedPane1.addTab("SUMBER", bitSumber);
        jTabbedPane1.addTab("TARGET", bitTarget);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(asal)
                            .addComponent(ke))
                        .addGap(15, 15, 15))
                    .addComponent(jTabbedPane1)
                    .addComponent(prog, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(asal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(prog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if(t!=null)t.stop();
        java.io.File f=new java.io.File(ke.getText());
        if(!wes&&f.exists())f.delete();
    }//GEN-LAST:event_formInternalFrameClosing

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    in();
                } catch (IOException ex) {
                    Logger.getLogger(InterJobs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }//GEN-LAST:event_formInternalFrameOpened

    private void inisial() {
        asal.setText(j.getAsal());
        ke.setText(j.getKe());
        this.setTitle(judul()+" "+j.getAsal()+" jadi "+j.getKe());
        this.setToolTipText(judul()+" "+j.getAsal()+" jadi "+j.getKe());
        this.setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField asal;
    private javax.swing.JTabbedPane bitSumber;
    private javax.swing.JTabbedPane bitTarget;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField ke;
    private javax.swing.JProgressBar prog;
    // End of variables declaration//GEN-END:variables

    public void jalan(){
        t=new Thread(new Runnable() {
            @Override
            public void run() {
                if(beans.Jobs.KOMPRES==j.getMode())kompres();
                else dekompres();
            }
        });t.start();
    }

    private void kompres() {
    try {
        java.io.File f=new java.io.File(asal.getText());
        long s=f.length(),i=0;
        beans.DataRL d=null;
        for(byte b:java.nio.file.Files.readAllBytes(f.toPath())){
            if(d==null){
                d=new beans.DataRL(b);
            }else if(b!=d.getB()||d.isPenuh()){
                util.Work.saveComp(d,ke.getText());
                d=new beans.DataRL(b);
            }else d.add();
            i++;
            prog(i,s);
        }util.Work.saveComp(d,ke.getText());
        out();
    } catch (IOException ex) {
        Logger.getLogger(InterJobs.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void dekompres(){
    try {
        java.io.File f=new java.io.File(asal.getText());
        long s=f.length(),i=0;
        java.util.List<Byte>l=new java.util.LinkedList<Byte>();
        for(byte b:java.nio.file.Files.readAllBytes(f.toPath())){
            l.add(b);
            if(4==l.size())util.Work.Decompres(l,ke.getText());
            i++;
            prog(i,s);
        }if(!l.isEmpty())util.Work.lastCompres(l,ke.getText());
        out();
    } catch (IOException ex) {
        Logger.getLogger(InterJobs.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private String judul() {
        String s="";
        if(beans.Jobs.KOMPRES==j.getMode())s="Kompresi ";
        else s="Dekompresi ";
        return s;
    }

    private void in() throws IOException {
        int x=0,y=0;
        javax.swing.JTextArea t=null;
        for(byte b:java.nio.file.Files.readAllBytes(new java.io.File(asal.getText()).toPath())){
            if(y==0){
                t=new javax.swing.JTextArea("");
                t.setWrapStyleWord(true);
                t.setLineWrap(true);
                bitSumber.add(""+x, new javax.swing.JScrollPane(t));
            }y++;
            if(y==4000){
                y=0;
                x++;
            }t.setText(t.getText()+b+" ");
        }
    }

    private void prog(long i, long s) {
        int x=(int) (i*100/s);
        prog.setValue(x);
    }

    private void out() throws IOException {
        int x=0,y=0;
        javax.swing.JTextArea t=null;
        for(byte b:java.nio.file.Files.readAllBytes(new java.io.File(ke.getText()).toPath())){
            if(y==0){
                t=new javax.swing.JTextArea("");
                t.setWrapStyleWord(true);
                t.setLineWrap(true);
                bitTarget.add(""+x, new javax.swing.JScrollPane(t));
            }y++;
            if(y==4000){
                y=0;
                x++;
            }t.setText(t.getText()+b+" ");
        }
    }
}