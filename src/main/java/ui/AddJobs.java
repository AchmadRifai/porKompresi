/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author ai
 */
public class AddJobs extends javax.swing.JDialog {

    /**
     * Creates new form AddJobs
     */
    public AddJobs(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        buka = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        mode = new javax.swing.JComboBox<>();
        simpan = new javax.swing.JButton();
        s = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("???");

        buka.setText("BUKA");
        buka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukaActionPerformed(evt);
            }
        });

        jLabel2.setText("???");

        mode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kompres", "dekompres" }));

        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        s.setText("OK");
        s.setEnabled(false);
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buka))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simpan))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(s, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buka))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(simpan))
                .addGap(18, 18, 18)
                .addComponent(mode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        javax.swing.JOptionPane.showMessageDialog(rootPane, "Nothing to do!");
    }//GEN-LAST:event_formWindowClosing

    private void bukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bukaActionPerformed
        javax.swing.JFileChooser fc=new javax.swing.JFileChooser(System.getProperty("user.home"));
        if(!"kompres".equals(mode.getItemAt(mode.getSelectedIndex()))){
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PZ file(*pz)", "pz"));
        }fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        if(javax.swing.JFileChooser.APPROVE_OPTION==fc.showOpenDialog(rootPane))jLabel1.setText(fc.getSelectedFile().getAbsolutePath());
        refresh();
    }//GEN-LAST:event_bukaActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        javax.swing.JFileChooser fc=new javax.swing.JFileChooser(System.getProperty("user.home"));
        if("kompres".equals(mode.getItemAt(mode.getSelectedIndex()))){
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PZ file(*pz)", "pz"));
        }fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        if(javax.swing.JFileChooser.APPROVE_OPTION==fc.showSaveDialog(rootPane))jLabel2.setText(fc.getSelectedFile().getAbsolutePath());
        refresh2();
    }//GEN-LAST:event_simpanActionPerformed

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        beans.Jobs j;
        if(!"???".equals(jLabel2.getText()))j=new beans.Jobs(mode(), jLabel1.getText(), jLabel2.getText());
        else j=new beans.Jobs(mode(), jLabel1.getText());
        if(j.oleh())try {
            util.Work.addJobs(j);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Logger.getLogger(AddJobs.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_sActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buka;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> mode;
    private javax.swing.JButton s;
    private javax.swing.JButton simpan;
    // End of variables declaration//GEN-END:variables

    @SuppressWarnings("UnnecessaryReturnStatement")
    private void refresh() {
        if("???".equals(jLabel1.getText()))return;
        buka.setEnabled(false);
        s.setEnabled(!buka.isEnabled());
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private void refresh2() {
        if("???".equals(jLabel2.getText()))return;
        simpan.setEnabled(false);
        s.setEnabled(!simpan.isEnabled());
    }

    private int mode() {
        if("kompres".equals(mode.getItemAt(mode.getSelectedIndex())))return beans.Jobs.KOMPRES;
        return beans.Jobs.DEKOMPRES;
    }
}