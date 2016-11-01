/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.inter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ai
 */
public class Jobs extends javax.swing.JInternalFrame {
private beans.Jobs j;
private Thread t;
    /**
     * Creates new form Jobs
     */
    public Jobs(beans.Jobs job) {
        initComponents();
        j=job;
        inisial();
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
        lakune = new javax.swing.JProgressBar();

        setClosable(true);
        setIconifiable(true);
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
            }
        });

        jLabel1.setText("Sumber");

        jLabel2.setText("Target");

        asal.setEditable(false);

        ke.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lakune, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ke)
                            .addComponent(asal))))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lakune, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

@SuppressWarnings("CallToThreadStopSuspendOrResumeManager")
    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if(t!=null)t.stop();
        java.io.File f=new java.io.File(ke.getText());
        if(f.exists())f.delete();
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField asal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField ke;
    private javax.swing.JProgressBar lakune;
    // End of variables declaration//GEN-END:variables

    private void inisial() {
        asal.setText(j.getAsal());
        ke.setText(j.getKe());
        this.setTitle(judule());
        this.setVisible(true);
    }

    private String judule() {
        String s;
        if(beans.Jobs.KOMPRES==j.getMode())s="KOMPRESI "+j.getAsal()+" ke "+j.getKe();
        else s="DEKOMPRES "+j.getAsal()+" ke "+j.getKe();
        return s;
    }

    public void jalan(){
        t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    laksana();
                } catch (IOException ex) {
                    Logger.getLogger(Jobs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });t.start();
    }

    private void laksana() throws IOException {
        if(beans.Jobs.KOMPRES==j.getMode())kompres();
        else dekompres();
        javax.swing.JOptionPane.showMessageDialog(rootPane, title+" berhasil");
    }

@SuppressWarnings("UnnecessaryContinue")
    private void kompres() throws IOException {
        java.io.File f=new java.io.File(asal.getText());
        long s=f.length(),p=0;
        beans.DataRL d=null;
        for(byte b:java.nio.file.Files.readAllBytes(f.toPath())){
            if(d!=null){
                if(b!=d.getB()||d.isPenuh()){
                    util.Work.simpanBit(d, ke.getText());
                    d=new beans.DataRL(b);
                }else d.add();
            }else d=new beans.DataRL(b);
            p++;
            progres(s,p);
        }util.Work.simpanBit(d, ke.getText());
    }

    private void dekompres() throws IOException {
        java.io.File f=new java.io.File(asal.getText());
        long s=f.length(),p=0;
        java.io.FileInputStream i=new java.io.FileInputStream(f);
        byte[]b=new byte[2];
        int r;
        while((r=i.read(b))!=-1){
            util.Work.kembali(b,ke.getText());
            p+=2;
            progres(s,p);
        }i.close();
    }

    private void progres(long s, long p) {
        long c=100*p;
        int i=(int) (c/s);
        lakune.setValue(i);
    }
}