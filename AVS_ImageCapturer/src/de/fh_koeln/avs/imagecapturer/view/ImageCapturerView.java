package de.fh_koeln.avs.imagecapturer.view;

import de.fh_koeln.avs.global.ImageUtils;
import de.fh_koeln.avs.imagecapturer.IImageCapturerController;
import de.fh_koeln.avs.imagecapturer.ImageCapturerController;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ImageCapturerView extends javax.swing.JFrame {

    private ScheduledExecutorService imageCaptureService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledExecutorService sendRawImageService = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService clusterConnectionService = Executors.newSingleThreadScheduledExecutor();
    private final Runnable imageCaptureRunnable;
    private final Runnable sendRawImageRunnable;
    private final Runnable clusterConnectionRunnable;
    private final IImageCapturerController imageCapturerController;

    /**
     * Creates new form ImageCaptureView
     */
    public ImageCapturerView() {
        initComponents();
        this.imageCaptureRunnable = new CaptureTask();
        this.sendRawImageRunnable = new SendRawImageTask();
        this.clusterConnectionRunnable = new ClusterConnectTask();
        this.imageCapturerController = new ImageCapturerController();
    }

    private class CaptureTask implements Runnable {

        @Override
        public void run() {
            BufferedImage displayedImage = imageCapturerController.nextFrame();
            capturedImage.setIcon(ImageUtils.getScaledImage(displayedImage, capturedImage.getWidth(), (int) ((double) (capturedImage.getWidth() * 3)) / 4));
        }
    }

    private class SendRawImageTask implements Runnable {

        @Override
        public void run() {
            imageCapturerController.sendRawImage();
        }
    }

    private class ClusterConnectTask implements Runnable {

        @Override
        public void run() {
            synchronized (clusterToggleButton) {
                clusterToggleButton.setEnabled(false);
            }
            boolean buttonActive = clusterToggleButton.isSelected();
            if (buttonActive) {
                boolean connected = imageCapturerController.connectToCluster();
                if (connected) {
                    clusterToggleButton.setText("Cluster: on");
                    streamToggleButton.setEnabled(true);
                } else {
                    clusterToggleButton.setSelected(false);
                }
            } else {
                boolean disconnected = imageCapturerController.disconnectFromCluster();
                if (disconnected) {
                    streamToggleButton.setEnabled(false);
                    clusterToggleButton.setText("Cluster: off");
                } else {
                    clusterToggleButton.setSelected(true);
                }
            }
            clusterToggleButton.setEnabled(true);
        }
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
        camIdLabel = new javax.swing.JLabel();
        camIdSpinner = new javax.swing.JSpinner();
        captureToggleButton = new javax.swing.JToggleButton();
        capturePanel = new javax.swing.JPanel();
        capturedImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(640, 480));

        controlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Controls"));
        controlPanel.setName(""); // NOI18N

        camIdLabel.setText("Camera Id:");

        camIdSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        captureToggleButton.setText("Start capture");
        captureToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureToggleButtonActionPerformed(evt);
            }
        });

        clusterToggleButton.setText("Cluster: off");
        clusterToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clusterToggleButtonActionPerformed(evt);
            }
        });

        streamToggleButton.setText("Start streaming");
        streamToggleButton.setEnabled(false);
        streamToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                streamToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(captureToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(clusterToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(camIdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(camIdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(streamToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(camIdLabel)
                    .addComponent(camIdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(captureToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clusterToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(streamToggleButton)
                .addContainerGap(312, Short.MAX_VALUE))
        );

        capturePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Capture"));
        capturePanel.setName(""); // NOI18N

        javax.swing.GroupLayout capturePanelLayout = new javax.swing.GroupLayout(capturePanel);
        capturePanel.setLayout(capturePanelLayout);
        capturePanelLayout.setHorizontalGroup(
            capturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, capturePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(capturedImage, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );
        capturePanelLayout.setVerticalGroup(
            capturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capturePanelLayout.createSequentialGroup()
                .addComponent(capturedImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(capturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(capturePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void captureToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureToggleButtonActionPerformed
        if (captureToggleButton.isSelected()) {
            int camId = (int) camIdSpinner.getValue();
            imageCapturerController.setCamId(camId);
            boolean cameraIsOn = imageCapturerController.openCapture();
            if (cameraIsOn) {
                captureToggleButton.setText("Stop capture");
                imageCaptureService.scheduleAtFixedRate(imageCaptureRunnable, 0, 40, TimeUnit.MILLISECONDS);
                clusterToggleButton.setEnabled(true);
                camIdSpinner.setEnabled(false);
            } else {
                captureToggleButton.setSelected(false);
            }
        } else {
            boolean cameraIsOff = imageCapturerController.closeCapture();
            if (cameraIsOff) {
                captureToggleButton.setText("Start capture");
                capturedImage.setIcon(null);
                imageCaptureService.shutdown();
                imageCaptureService = Executors.newSingleThreadScheduledExecutor();
                camIdSpinner.setEnabled(true);
            } else {
                captureToggleButton.setSelected(true);
            }
        }
    }//GEN-LAST:event_captureToggleButtonActionPerformed

    private void clusterToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clusterToggleButtonActionPerformed
        clusterConnectionService.schedule(clusterConnectionRunnable, 0, TimeUnit.MILLISECONDS);
    }//GEN-LAST:event_clusterToggleButtonActionPerformed

    private void streamToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_streamToggleButtonActionPerformed
        if (streamToggleButton.isSelected()) {
            clusterToggleButton.setEnabled(false);
            captureToggleButton.setEnabled(false);
            streamToggleButton.setText("Stop streaming");
            sendRawImageService.scheduleWithFixedDelay(sendRawImageRunnable, 0, 40, TimeUnit.MILLISECONDS);
        } else {
            clusterToggleButton.setEnabled(true);
            captureToggleButton.setEnabled(true);
            streamToggleButton.setText("Start streaming");
            sendRawImageService.shutdown();
            sendRawImageService = Executors.newSingleThreadScheduledExecutor();
        }
    }//GEN-LAST:event_streamToggleButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ImageCapturerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageCapturerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageCapturerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageCapturerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ImageCapturerView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel camIdLabel;
    private javax.swing.JSpinner camIdSpinner;
    private javax.swing.JPanel capturePanel;
    private javax.swing.JToggleButton captureToggleButton;
    private javax.swing.JLabel capturedImage;
    private final javax.swing.JToggleButton clusterToggleButton = new javax.swing.JToggleButton();
    private javax.swing.JPanel controlPanel;
    private final javax.swing.JToggleButton streamToggleButton = new javax.swing.JToggleButton();
    // End of variables declaration//GEN-END:variables
}
