package de.fh_koeln.avs.dashboard.view;

import de.fh_koeln.avs.dashboard.DashboardController;
import de.fh_koeln.avs.dashboard.IDashboardController;
import de.fh_koeln.avs.global.ImageUtils;
import de.fh_koeln.avs.global.ROI;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Daniel van der Wal
 */
public class DashboardView extends javax.swing.JFrame {

    private ScheduledExecutorService imageCaptureService = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService clusterConnectionService = Executors.newSingleThreadScheduledExecutor();
    private final Runnable clusterConnectionRunnable;
    private final IDashboardController dashboardController;
    private final Runnable imageCaptureRunnable;
    private String selectedCam;
    private BufferedImage displayedImage;
    private final ROIPanel roiPanel = new ROIPanel();
    private final JLabel imageLabel = new JLabel();

    /**
     * Creates new form DashboardView
     */
    public DashboardView() {
        initComponents();
        this.clusterConnectionRunnable = new ClusterConnectTask();
        this.dashboardController = new DashboardController();
        this.imageCaptureRunnable = new CaptureTask();
        this.camList.setModel(new DefaultListModel());
        this.camList.addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting() == false) {
                if (camList.getSelectedValue() != null) {
                    selectedCam = camList.getSelectedValue().toString();
                } else {
                    selectedCam = "";
                }
                imageCaptureService.shutdown();
                imageCaptureService = Executors.newSingleThreadScheduledExecutor();
                imageCaptureService.scheduleAtFixedRate(imageCaptureRunnable, 0, 1, TimeUnit.SECONDS);
            }
        });

        capturedImageWrapper.setLayout(null);

        roiPanel.setOpaque(false);
        roiPanel.setSize(new Dimension(640, 480));
        capturedImageWrapper.add(roiPanel);

        imageLabel.setSize(new Dimension(640, 480));
        capturedImageWrapper.add(imageLabel);

        capturedImageWrapper.setSize(new Dimension(640, 480));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        camList = new javax.swing.JList();
        imagePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(815, 600));
        setMinimumSize(new java.awt.Dimension(815, 600));

        controlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Controls"));
        controlPanel.setMaximumSize(new java.awt.Dimension(300, 518));
        controlPanel.setMinimumSize(new java.awt.Dimension(300, 518));

        clusterToggleButton.setText("Cluster: on");
        clusterToggleButton.setActionCommand("");
        clusterToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clusterToggleButtonActionPerformed(evt);
            }
        });

        camList.setMaximumSize(new java.awt.Dimension(240, 500));
        camList.setPreferredSize(new java.awt.Dimension(240, 500));
        jScrollPane1.setViewportView(camList);

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clusterToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clusterToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
        );

        imagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Parking Lot"));

        capturedImageWrapper.setMinimumSize(new java.awt.Dimension(640, 480));
        capturedImageWrapper.setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout capturedImageWrapperLayout = new javax.swing.GroupLayout(capturedImageWrapper);
        capturedImageWrapper.setLayout(capturedImageWrapperLayout);
        capturedImageWrapperLayout.setHorizontalGroup(
            capturedImageWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        capturedImageWrapperLayout.setVerticalGroup(
            capturedImageWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(capturedImageWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(capturedImageWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clusterToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clusterToggleButtonActionPerformed
        synchronized (clusterToggleButton) {
            clusterToggleButton.setEnabled(false);
        }
        clusterConnectionService.schedule(clusterConnectionRunnable, 0, TimeUnit.MILLISECONDS);

    }//GEN-LAST:event_clusterToggleButtonActionPerformed

    private class CaptureTask implements Runnable {

        @Override
        public void run() {
            if (!selectedCam.isEmpty()) {
                displayedImage = dashboardController.getRawImage(selectedCam);
                imageLabel.setIcon(ImageUtils.getScaledImage(displayedImage, 640, 480));
                Map<Integer, ROI> rois = dashboardController.getROIs(selectedCam);
                roiPanel.setROIs(rois);
            }
        }
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
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList camList;
    private final javax.swing.JPanel capturedImageWrapper = new javax.swing.JPanel();
    private final javax.swing.JToggleButton clusterToggleButton = new javax.swing.JToggleButton();
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private class ClusterConnectTask implements Runnable {

        @Override
        public void run() {
            boolean buttonActive = clusterToggleButton.isSelected();
            try {
                if (buttonActive) {
                    boolean connected = dashboardController.connectToCluster();
                    if (connected) {
                        clusterToggleButton.setText("Cluster: on");

                        DefaultListModel dlm = (DefaultListModel) camList.getModel();
                        Collection<String> names = dashboardController.getImageCapturerNames();
                        names.stream().forEach((name) -> {
                            dlm.addElement(name);
                        });

                    } else {
                        clusterToggleButton.setSelected(false);
                    }
                } else {
                    boolean disconnected = dashboardController.disconnectFromCluster();
                    if (disconnected) {
                        clusterToggleButton.setText("Cluster: off");

                        imageCaptureService.shutdown();
                        imageCaptureService = Executors.newSingleThreadScheduledExecutor();

                        imageLabel.setIcon(null);
                        displayedImage = null;
                        DefaultListModel dlm = (DefaultListModel) camList.getModel();
                        dlm.clear();
                        
                        //Clear ROIs
                        roiPanel.setROIs(null);
                        
                    } else {
                        clusterToggleButton.setSelected(true);
                    }
                }
            } finally {
                clusterToggleButton.setEnabled(true);
            }
        }
    }
}
