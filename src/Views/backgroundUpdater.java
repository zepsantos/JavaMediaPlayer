/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.MediaCenter;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author josepgrs
 */
public class backgroundUpdater extends SwingWorker<Void, Void> {
    private MediaCenter mc ;
    private javax.swing.JProgressBar progressBar;
    private int statusPB = 0;

    public backgroundUpdater(JProgressBar progressBar) {
        this.progressBar = progressBar;
        this.mc = MediaCenter.getInstance();
    }
    
    

    @Override
    protected Void doInBackground() throws Exception {
               // if()
                return null;
            }

    @Override
    protected void process(List<Void> chunks) {
        super.process(chunks); 
       // progressBar.setValue(i);
        
    }

    @Override
    protected void done() {
        super.done(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
