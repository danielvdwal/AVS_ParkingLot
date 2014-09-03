package de.fh_koeln.avs.imageprocessor.view;

import de.fh_koeln.avs.imageprocessor.IImageProcessorController;
import de.fh_koeln.avs.imageprocessor.ImageProcessorController;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ImageProcessorView extends javax.swing.JFrame {

    private final IImageProcessorController imageProcessorController;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /**
     * Creates new form ImageProcessorView
     */
    public ImageProcessorView() {
        initComponents();
        this.imageProcessorController = new ImageProcessorController();
    }

    private class GetRawImageRunnable implements Runnable {

        @Override
        public void run() {
            synchronized (imageProcessorController) {
                imageProcessorController.getRawImage();
            }
        }
    }

    private class ClusterConnectionRunnable implements Runnable {

        @Override
        public void run() {
            boolean buttonActive;
            synchronized (clusterToggleButton) {
                buttonActive = clusterToggleButton.isSelected();
            }
            if (buttonActive) {
                boolean connected;
                synchronized (imageProcessorController) {
                    connected = imageProcessorController.connectToCluster();
                }
                synchronized (clusterToggleButton) {
                    if (connected) {
                        clusterToggleButton.setText("Cluster: on");
                        drawLinesButton.setEnabled(true);
                        executorService.scheduleAtFixedRate(new GetRawImageRunnable(), 0, 40, TimeUnit.MILLISECONDS);
                    } else {
                        clusterToggleButton.setSelected(false);
                    }
                }
            } else {
                boolean disconnected;
                synchronized (imageProcessorController) {
                    executorService.shutdown();
                    executorService = Executors.newScheduledThreadPool(1);
                    disconnected = imageProcessorController.disconnectFromCluster();
                }
                synchronized (clusterToggleButton) {
                    if (disconnected) {
                        clusterToggleButton.setText("Cluster: off");
                    } else {
                        clusterToggleButton.setSelected(true);
                    }
                }
            }
            synchronized (clusterToggleButton) {
                clusterToggleButton.setEnabled(true);
            }
        }
    }

    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     *
     * @param srcImg - source image to scale
     * @param w - desired width
     * @param h - desired height
     * @return - the new resized image
     */
    private ImageIcon getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return new ImageIcon(resizedImg);
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
        drawLinesButton = new javax.swing.JButton();
        thresholdLabel = new javax.swing.JLabel();
        thresholdSpinner = new javax.swing.JSpinner();
        minLineSizeLabel = new javax.swing.JLabel();
        minLineSizeSpinner = new javax.swing.JSpinner();
        lineGapSpinner = new javax.swing.JSpinner();
        lineGapLabel = new javax.swing.JLabel();
        previewPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Controls"));

        clusterToggleButton.setText("Cluster: off");
        clusterToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clusterToggleButtonActionPerformed(evt);
            }
        });

        drawLinesButton.setText("Draw lines");
        drawLinesButton.setEnabled(false);
        drawLinesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawLinesButtonActionPerformed(evt);
            }
        });

        thresholdLabel.setText("Threshold");

        thresholdSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(47), Integer.valueOf(0), null, Integer.valueOf(1)));

        minLineSizeLabel.setText("Min Line Size");

        minLineSizeSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(168), Integer.valueOf(0), null, Integer.valueOf(1)));

        lineGapSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(82), Integer.valueOf(0), null, Integer.valueOf(1)));

        lineGapLabel.setText("Line Gap");

        processButton.setText("Process");
        processButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clusterToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(drawLinesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thresholdSpinner)
                    .addComponent(minLineSizeSpinner)
                    .addComponent(lineGapSpinner)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(thresholdLabel)
                            .addComponent(minLineSizeLabel)
                            .addComponent(lineGapLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(processButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addComponent(clusterToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drawLinesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thresholdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minLineSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minLineSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineGapLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineGapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(processButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        previewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Image preview"));

        javax.swing.GroupLayout previewPanelLayout = new javax.swing.GroupLayout(previewPanel);
        previewPanel.setLayout(previewPanelLayout);
        previewPanelLayout.setHorizontalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cannyPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(firstChunkPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contoursPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        previewPanelLayout.setVerticalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(preview, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstChunkPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cannyPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contoursPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clusterToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clusterToggleButtonActionPerformed
        synchronized (clusterToggleButton) {
            clusterToggleButton.setEnabled(false);
        }
        new Thread(new ClusterConnectionRunnable()).start();
    }//GEN-LAST:event_clusterToggleButtonActionPerformed

    private void drawLinesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawLinesButtonActionPerformed
        synchronized (imageProcessorController) {
            imageProcessorController.drawLines((int) thresholdSpinner.getValue(), (int) minLineSizeSpinner.getValue(), (int) lineGapSpinner.getValue());
            BufferedImage previewImage = imageProcessorController.getImageWithLines();
            preview.setIcon(getScaledImage(previewImage, preview.getHeight(), preview.getHeight()));
            
            BufferedImage cannyImage = imageProcessorController.getCannyImage();
            cannyPreview.setIcon(getScaledImage(cannyImage, cannyPreview.getHeight(), cannyPreview.getHeight()));
        }
    }//GEN-LAST:event_drawLinesButtonActionPerformed

    private void processButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButtonActionPerformed
        synchronized (imageProcessorController) {
            imageProcessorController.processImage();
            BufferedImage firstChunkImage = imageProcessorController.getFirstChunkImage();
            firstChunkPreview.setIcon(getScaledImage(firstChunkImage, firstChunkPreview.getHeight(), firstChunkPreview.getHeight()));
            BufferedImage contoursImage = imageProcessorController.getContoursImage();
            contoursPreview.setIcon(getScaledImage(contoursImage, contoursPreview.getHeight(), contoursPreview.getHeight()));
        }
    }//GEN-LAST:event_processButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ImageProcessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageProcessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageProcessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageProcessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ImageProcessorView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JLabel cannyPreview = new javax.swing.JLabel();
    private final javax.swing.JToggleButton clusterToggleButton = new javax.swing.JToggleButton();
    private final javax.swing.JLabel contoursPreview = new javax.swing.JLabel();
    private javax.swing.JPanel controlPanel;
    private javax.swing.JButton drawLinesButton;
    private final javax.swing.JLabel firstChunkPreview = new javax.swing.JLabel();
    private javax.swing.JLabel lineGapLabel;
    private javax.swing.JSpinner lineGapSpinner;
    private javax.swing.JLabel minLineSizeLabel;
    private javax.swing.JSpinner minLineSizeSpinner;
    private final javax.swing.JLabel preview = new javax.swing.JLabel();
    private javax.swing.JPanel previewPanel;
    private final javax.swing.JButton processButton = new javax.swing.JButton();
    private javax.swing.JLabel thresholdLabel;
    private javax.swing.JSpinner thresholdSpinner;
    // End of variables declaration//GEN-END:variables
}
