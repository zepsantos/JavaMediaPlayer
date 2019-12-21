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
public class backgroundUpdater extends SwingWorker<Void, Double> {
    private MediaCenter mc ;
    private javax.swing.JProgressBar progressBar;
    private double statusPB = 0;

    public backgroundUpdater(JProgressBar progressBar) {
        this.progressBar = progressBar;
        this.mc = MediaCenter.getInstance();
    }
    
    

    @Override
    protected Void doInBackground() throws Exception {
                statusPB = mc.getMusicProgress();
                publish(statusPB);
                return null;
            }

    @Override
    protected void process(List<Double> chunks) {
        super.process(chunks); 
        progressBar.setValue((int) chunks.get(chunks.size()-1).doubleValue());
        
    }

    @Override
    protected void done() {
        super.done(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
