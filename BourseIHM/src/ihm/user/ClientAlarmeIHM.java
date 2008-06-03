/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ihm.user;

import BourseCorba._ClientAlarmeImplBase;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author faure
 */
class ClientAlarmeIHM extends _ClientAlarmeImplBase{
    private JFrame owner;
    public ClientAlarmeIHM (JFrame owner){
        this.owner = owner ;
    }
    public void notifie(final int idTitre, final double nouveauCours) {
        JOptionPane.showMessageDialog(owner,
               "L'alarme positionnée sur le titre d'id " + idTitre + " \n"+
               "a désormais le cours : " + nouveauCours, 
               "Une alarme a été déclenchée",
               JOptionPane.INFORMATION_MESSAGE);
    }
}
