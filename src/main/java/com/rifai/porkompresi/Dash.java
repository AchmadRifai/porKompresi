/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rifai.porkompresi;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import util.Work;

/**
 *
 * @author ai
 */
public class Dash extends javax.swing.JFrame {

    /**
     * Creates new form Dash
     */
    public Dash() {
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        aj = new javax.swing.JButton();
        j = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Por Kompresi Dekompresi");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setRollover(true);

        aj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eUsjmVRJ.jpg"))); // NOI18N
        aj.setText("Add Jobs");
        aj.setFocusable(false);
        aj.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aj.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        aj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajActionPerformed(evt);
            }
        });
        jToolBar1.add(aj);

        j.setText("Jalan");
        j.setEnabled(false);
        j.setFocusable(false);
        j.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        j.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActionPerformed(evt);
            }
        });
        jToolBar1.add(j);

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File Asal", "File Tujuan", "Tanggal Kompresi", "Waktu Kompresi", "Efektifitas", "Telah Diproses"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //Work.f.delete();
    }//GEN-LAST:event_formWindowClosing

    private void jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActionPerformed
        new Thread(this::runProses).start();
        aj.setEnabled(false);
        j.setEnabled(false);
    }//GEN-LAST:event_jActionPerformed

    private void ajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajActionPerformed
        addJobs();
    }//GEN-LAST:event_ajActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(!util.Work.f.exists())try {
            util.Work.initFile();
        } catch (ParserConfigurationException | TransformerException ex) {
            util.Work.hindar(ex);
        }muat();
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aj;
    private javax.swing.JButton j;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblHistory;
    // End of variables declaration//GEN-END:variables

    private void muat() {
        try {
            java.util.List<beans.Jobs>l=Work.getJobs();
            javax.swing.table.DefaultTableModel m=(javax.swing.table.DefaultTableModel) tblHistory.getModel();
            for(int x=m.getRowCount()-1;x>=0;x--)m.removeRow(x);
            boolean b=false;
            for(beans.Jobs j:l){
                m.addRow(new Object[]{j.getAsal(),j.getKe(),j.getTgl(),j.getWaktu(),j.getEfek(),j.isTerproses()});
                b=b||!j.isTerproses();
            }j.setEnabled(b);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Work.hindar(ex);
        }
    }

    private void addJobs() {
        com.rifai.porkompresi.AddJobs a=new com.rifai.porkompresi.AddJobs(this, true);
        a.setVisible(true);
        while(a.isVisible()){}
        this.formWindowOpened(null);
    }

    private void runProses() {
        try {
            java.util.List<beans.Jobs>l=Work.getJobs();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Work.hindar(ex);
        }
    }
}