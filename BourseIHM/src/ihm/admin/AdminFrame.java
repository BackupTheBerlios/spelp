/*
 * Adminframe.java
 *
 * Created on 18 mai 2008, 15:01
 */

package ihm.admin;

import BourseCorba.Admin;
import BourseCorba.CompteAdmin;
import BourseCorba.ServerException;
import ihm.StartFrame;
import ihm.utils.Connexion;
import java.awt.FileDialog;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  Tristan
 */
public class AdminFrame extends javax.swing.JInternalFrame {
    private JFrame owner = null ;
    private Admin adminRef;
    /** Creates new form Adminframe */
    private AdminFrame(JFrame owner) {
        this.owner =  owner ;
        initComponents();
    }

    public AdminFrame(StartFrame aThis, Admin adminRef) {
        this.owner =  owner ;
        this.adminRef = adminRef ;
        initComponents();
        initTable();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jButton3.setText("jButton3");

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administration");

        jTable1.setModel(new AdminComtpesModel(new Object[][]{}, new String[]{"id","nom","cash"} ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("+ cash");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("- cash");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("nouvel utilisateur");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("nouveau compte");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(181, 181, 181))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    String o = JOptionPane.showInputDialog("Montant de départ ? ") ;
    try {
        Double i = Double.parseDouble(o);
        adminRef.creerCompte(i);
        initTable();   
    }
    catch (Exception e){
      
    }
    
    
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    AdminNewUser newUser = new AdminNewUser(owner, true);
    if (newUser.showNewUser() == Connexion.status.OK){
        try {
            int idUser = adminRef.insererUtilisateur(newUser.getNom().getText(), newUser.getPrenom().getText(), newUser.getLogin().getText(), new String(newUser.getMdp().getPassword()), 1);
            if (jTable1.getSelectedRow() != -1){
                   if (JOptionPane.showOptionDialog(owner,
                        "vous avez selectionne un compte voulez vous l'affecter à cet utilisateur ?",
                        "affecter utilisateur",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null,null) == JOptionPane.OK_OPTION){
                       int idCompte = Integer.valueOf(((String)jTable1.getValueAt(jTable1.getSelectedRow(),0)));
                       adminRef.affecterCompte(idUser,idCompte);
                   }
            }
            initTable();

        } catch (ServerException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    if (jTable1.getSelectedRow() != -1){
            try {
                Double value = getValue();
                if (value != null) {
                    int idCompte = Integer.valueOf((String) jTable1.getValueAt(jTable1.getSelectedRow(),0));
                    adminRef.ajoutCash(idCompte, value);
                    initTable();
                }
            } catch (ServerException ex) {
                Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    else {
        JOptionPane.showMessageDialog(owner,"Selectionnez un compte");
    }
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
     if (jTable1.getSelectedRow() != -1){
            try {
                Double value = getValue();
                if (value != null) {
                    int idCompte = Integer.valueOf((String) jTable1.getValueAt(jTable1.getSelectedRow(),0));
                    adminRef.diminuerCash(idCompte, value);
                    initTable();
                }
            } catch (ServerException ex) {
                Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    else {
        JOptionPane.showMessageDialog(owner,"Selectionnez un compte");
    }
}//GEN-LAST:event_jButton4ActionPerformed
private Double getValue () {
    String value = JOptionPane.showInputDialog("Cash ? ") ;
    if (value.isEmpty()) {
        return null ;
    }
    return Double.parseDouble(value);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void initTable() {
        CompteAdmin[] comptes = adminRef.getComptes();
        Object[][] model = new Object[comptes.length][3] ;
        for (int i = 0 ; i < comptes.length ; i ++){
            model[i][0] = comptes[i].id ;
            model[i][1] = comptes[i].nom ;
            model[i][2] = comptes[i].cash ;
        }
        jTable1.setModel(new AdminComtpesModel(model, new String[]{"id","nom","cash"} ));
    }
    private class AdminComtpesModel extends DefaultTableModel{
        
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Double.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        private AdminComtpesModel(Object[][] model, String[] string) {
           super(model, string);
        }

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
        
    }
}