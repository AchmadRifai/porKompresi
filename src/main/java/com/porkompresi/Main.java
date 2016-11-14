/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.porkompresi;

/**
 *
 * @author ai
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//javax.swing.UIManager.setLookAndFeel(new com.jtattoo.plaf.mint.MintLookAndFeel());
                java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ui.Dash().setVisible(true);
            }
        });
    }
    
}
