/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Content;
import Models.MusicContent;
import Models.MediaCenter;
import Models.PlayerStatus;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Pedro Gomes
 */
public class MainForm extends javax.swing.JFrame {
    int musicShownPlaylist = 0;
    int progressStatus = 0;
    /**
     * Creates new form MainMenu
     */
    public MainForm() {
        
        initComponents();
        loadUser();
        drawPlaylistSection();
        listenForClicksOnProgressBar();
        cleanLook();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        utilizadorLabel = new javax.swing.JLabel();
        myMusicButton = new javax.swing.JButton();
        downloadButton = new javax.swing.JButton();
        uploadButton = new javax.swing.JButton();
        friendsListButton = new javax.swing.JButton();
        myMediaButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        musicTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        musicNameLabel = new javax.swing.JLabel();
        compositorLabel = new javax.swing.JLabel();
        skipBackButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        skipFwdButton = new javax.swing.JButton();
        soundVolumeButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(67, 104, 145));

        utilizadorLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        utilizadorLabel.setText("Utilizador:");

        myMusicButton.setBackground(new java.awt.Color(67, 104, 145));
        myMusicButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        myMusicButton.setText("Minhas Musicas");
        myMusicButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        myMusicButton.setName(""); // NOI18N
        myMusicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myMusicButtonActionPerformed(evt);
            }
        });

        downloadButton.setBackground(new java.awt.Color(67, 104, 145));
        downloadButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        downloadButton.setText("Download Conteudo");
        downloadButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        uploadButton.setBackground(new java.awt.Color(67, 104, 145));
        uploadButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        uploadButton.setText("Upload Conteudo");
        uploadButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        friendsListButton.setBackground(new java.awt.Color(67, 104, 145));
        friendsListButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        friendsListButton.setText("Ver lista de amigos");
        friendsListButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        myMediaButton.setBackground(new java.awt.Color(67, 104, 145));
        myMediaButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        myMediaButton.setText("Meus Videos");
        myMediaButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        myMediaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myMediaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(downloadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(utilizadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myMusicButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(friendsListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myMediaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(myMusicButton)
                .addGap(34, 34, 34)
                .addComponent(myMediaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(downloadButton)
                .addGap(33, 33, 33)
                .addComponent(uploadButton)
                .addGap(33, 33, 33)
                .addComponent(friendsListButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(utilizadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel2.setBackground(new java.awt.Color(67, 104, 145));

        jPanel4.setBackground(new java.awt.Color(54, 82, 114));

        musicTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Nome", "Artista", "Categoria", "Duracao"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(musicTable);
        if (musicTable.getColumnModel().getColumnCount() > 0) {
            musicTable.getColumnModel().getColumn(0).setResizable(false);
            musicTable.getColumnModel().getColumn(1).setResizable(false);
            musicTable.getColumnModel().getColumn(2).setResizable(false);
            musicTable.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(133, 136, 139));

        musicNameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        musicNameLabel.setText("Nome da Musica");

        compositorLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        compositorLabel.setText("Compositor");

        skipBackButton.setBackground(new java.awt.Color(133, 136, 139));
        skipBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_skip_previous_black_18dp.png"))); // NOI18N
        skipBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipBackButtonActionPerformed(evt);
            }
        });

        stopButton.setBackground(new java.awt.Color(133, 136, 139));
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_play_arrow_black_18dp.png"))); // NOI18N
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        skipFwdButton.setBackground(new java.awt.Color(133, 136, 139));
        skipFwdButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_skip_next_black_18dp.png"))); // NOI18N
        skipFwdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipFwdButtonActionPerformed(evt);
            }
        });

        soundVolumeButton.setBackground(new java.awt.Color(133, 136, 139));
        soundVolumeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_volume_up_black_18dp.png"))); // NOI18N
        soundVolumeButton.setText(" ");

        progressBar.setBackground(new java.awt.Color(230, 230, 230));
        progressBar.setForeground(new java.awt.Color(47, 72, 100));
        progressBar.setMaximum(1000);
        progressBar.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(compositorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(musicNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(skipBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(skipFwdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(soundVolumeButton)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(soundVolumeButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(musicNameLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(compositorLabel))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(skipBackButton)
                                .addComponent(stopButton)
                                .addComponent(skipFwdButton))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cleanLook(){
        try { 
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        }catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        this.myMusicButton.setOpaque(false);
        this.myMusicButton.setContentAreaFilled(false);
        this.myMusicButton.setBorderPainted(false);
        
        this.myMediaButton.setOpaque(false);
        this.myMediaButton.setContentAreaFilled(false);
        this.myMediaButton.setBorderPainted(false);
        
        this.downloadButton.setOpaque(false);
        this.downloadButton.setContentAreaFilled(false);
        this.downloadButton.setBorderPainted(false);
        
        this.uploadButton.setOpaque(false);
        this.uploadButton.setContentAreaFilled(false);
        this.uploadButton.setBorderPainted(false);
                
        this.friendsListButton.setOpaque(false);
        this.friendsListButton.setContentAreaFilled(false);
        this.friendsListButton.setBorderPainted(false);
    }
    
    private void myContentButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                                      
    
    private void listenForClicksOnProgressBar() {
        progressBar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MediaCenter mc = MediaCenter.getInstance();
                double dx = e.getX();
                double dwidth = progressBar.getWidth();
                double progression = (dx / dwidth);
                int milliseconds =(int) (progression * mc.getCurrentContent().getTamanho().toMillis());
                Duration duration = new Duration(milliseconds);
                mc.skip_to_time(duration);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                            }

            @Override
            public void mouseEntered(MouseEvent e) {
                            }

            @Override
            public void mouseExited(MouseEvent e) {
                           }
        });
    }

  
    
    private void loadUser(){
        MediaCenter mc = MediaCenter.getInstance();
        String nome = mc.getUser().getNome();
        this.utilizadorLabel.setText("Utilizador: "+nome);
        mc.readANDinit();
        updateMetaData();
    }
    
    
    public void updateMetaData(){
        MediaCenter mc = MediaCenter.getInstance();
        Content tmp = mc.getCurrentContent();
        if(tmp != null && (tmp instanceof MusicContent)) {
            this.musicNameLabel.setText(tmp.getNome());
            this.compositorLabel.setText(((MusicContent)tmp).getArtista());
        }
    }
    
    private void updateProgressBar() {
        MediaCenter mc = MediaCenter.getInstance();
        Content tmp = mc.getCurrentContent() ;
        int tmppd = 0;
            tmppd = (int)(tmp.getTamanho().toMillis());
            progressBar.setMaximum(tmppd);

        
        final int pd = tmppd;
        Runnable r = () -> {
            while(MediaCenter.getInstance().getStatus() == PlayerStatus.PLAYING) {
                progressBar.setValue((int)mc.getMusicProgress());
                
            }
            
        };
        Thread t = new Thread(r);
        t.start();
    } 
    
    
    private void drawPlaylistSection() {
        int i = 0;
        DefaultTableModel model = (DefaultTableModel) musicTable.getModel();
        List<List<String>> tmp = playlistToTable();
        for(List<String> tableData : tmp) {
            if(i>=musicShownPlaylist) {
            musicShownPlaylist++;
            model.addRow(tableData.stream().toArray());
            }
            i++;
        }
        MediaCenter mc = MediaCenter.getInstance();
        musicTable.addMouseListener(new MouseAdapter() {
             @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = musicTable.rowAtPoint(evt.getPoint());
                    int column = musicTable.columnAtPoint(evt.getPoint());
                    if(column == 2) {
                        
                    }
                    if(row>=0) {
                        mc.stopPlayer();
                        mc.play(row);
                        updateMusicInfo();
                        changeIconMusicStatus();
                    }

        }
        });
    } 
    
    private List<List<String>> playlistToTable() {
        List<Content> tmp = MediaCenter.getInstance().getUserContentList();
        List<List<String>> tmpList = new ArrayList<>();
        for(Content c : tmp) {
            if(c instanceof MusicContent) {
                MusicContent cmc = (MusicContent) c;
            List<String> stringList = new ArrayList<>();
            stringList.add(cmc.getNome());
            stringList.add(cmc.getArtista());
            stringList.add(String.valueOf(cmc.getCategoria()));
            stringList.add(DurationToGoodLookingString(cmc.getTamanho()));
            tmpList.add(stringList);
            }
        }
        return tmpList;
        
    }
    
    private String DurationToGoodLookingString(Duration d) {
        double milis  = d.toMillis();
        int minutes =(int) (milis/1000) / 60;
        int seconds = (int) (milis/1000) % 60;
        StringBuilder sb = new StringBuilder(String.valueOf(minutes));
        sb.append(":");
        sb.append(String.valueOf(seconds));
        return sb.toString();
        
        
    }
    
    private void updateMusicInfo() {
           updateMetaData();
           updateProgressBar();
    }
    
    private void changeIconMusicStatus() {
        MediaCenter mc = MediaCenter.getInstance();
        if(mc.getStatus() == PlayerStatus.PLAYING)
        this.stopButton.setIcon(new javax.swing.ImageIcon("src/Icons/baseline_stop_black_18dp.png"));
        else
        this.stopButton.setIcon(new javax.swing.ImageIcon("src/Icons/baseline_play_arrow_black_18dp.png"));

    }
    
    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
       MediaCenter mc = MediaCenter.getInstance();
       if(mc.getPlayListSize() == 0)return;
       if(mc.getStatus() != PlayerStatus.PLAYING ){
           mc.play();
           updateMusicInfo();
       }else {
           mc.pause();
       }
       changeIconMusicStatus();
       
    }//GEN-LAST:event_stopButtonActionPerformed

    private void skipFwdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipFwdButtonActionPerformed
        MediaCenter mc = MediaCenter.getInstance();
        if(mc.getPlayListSize() == 0)return;
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/baseline_play_arrow_black_18dp.png")));
        mc.skip_next_song();
        updateMetaData();
        
    }//GEN-LAST:event_skipFwdButtonActionPerformed

    private void skipBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipBackButtonActionPerformed
        MediaCenter mc = MediaCenter.getInstance();
        if(mc.getPlayListSize() == 0)return;
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/baseline_play_arrow_black_18dp.png")));
        mc.skip_previous_song();
        updateMetaData();
    }//GEN-LAST:event_skipBackButtonActionPerformed

    
    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        //new UploadForm().setVisible(true);
        MediaCenter mc = MediaCenter.getInstance();
        JFileChooser jfc = new JFileChooser();
        if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File f = jfc.getSelectedFile();
            try{    
                Files.copy(Paths.get(f.getPath()),Paths.get("Conteudo/" +f.getName())); 
                mc.addFile(f.getName());
                drawPlaylistSection();
                if(mc.getPlayListSize() == 1){
                    this.updateMetaData();
                }
                
            
            }catch(IOException e){
                System.out.println("ERRO A COPIAR FICHEIRO");
            }
        } 
    }//GEN-LAST:event_uploadButtonActionPerformed

    private void myMediaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myMediaButtonActionPerformed
        // TODO add your handling code here:
        String[] columnNames = {"Nome", "Categoria", "Duracao" };
        DefaultTableModel model = new DefaultTableModel(columnNames,0);
        
        
    }//GEN-LAST:event_myMediaButtonActionPerformed
    
    private void myMusicButtonActionPerformed(java.awt.event.ActionEvent evt){
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel compositorLabel;
    private javax.swing.JButton downloadButton;
    private javax.swing.JButton friendsListButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel musicNameLabel;
    private javax.swing.JTable musicTable;
    private javax.swing.JButton myMediaButton;
    private javax.swing.JButton myMusicButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton skipBackButton;
    private javax.swing.JButton skipFwdButton;
    private javax.swing.JButton soundVolumeButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JButton uploadButton;
    private javax.swing.JLabel utilizadorLabel;
    // End of variables declaration//GEN-END:variables
}
