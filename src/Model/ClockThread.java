/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author NGOCDAI
 */
public class ClockThread extends Thread {

    private JLabel lbl;

    public ClockThread(JLabel lbl) {
        this.lbl = lbl;
    }

    public void run() {
        while (true) {
            try {
                Date date = new Date();
                String time = Utils.Utility.getTimeNow("HH:mm:ss aa");

                lbl.setText(time);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
