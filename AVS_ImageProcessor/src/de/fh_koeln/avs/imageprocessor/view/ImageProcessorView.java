package de.fh_koeln.avs.imageprocessor.view;

import de.fh_koeln.avs.global.ImageUtils;
import de.fh_koeln.avs.imageprocessor.IImageProcessorController;
import de.fh_koeln.avs.imageprocessor.ImageProcessorController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageProcessorView extends JFrame {

    private ScheduledExecutorService imageCaptureService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledExecutorService processImageService = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService clusterConnectionService = Executors.newSingleThreadScheduledExecutor();
    private final Runnable imageCaptureRunnable;
    private final Runnable processImageRunnable;
    private final Runnable clusterConnectionRunnable;
    private final IImageProcessorController imageProcessorController;

    private final JPanel controlPanel = new JPanel();
    private final JToggleButton clusterToggleButton = new JToggleButton();
    private final JLabel imageCapturerLabel = new JLabel();
    private final JComboBox<String> imageCapturerComboBox = new JComboBox<>();
    private final JButton drawROIsAutomaticallyButton = new JButton();
    private final JCheckBox horizontalLinesCheckBox = new JCheckBox();
    private final JLabel thresholdLabel = new JLabel();
    private final JSpinner thresholdSpinner = new JSpinner();
    private final JLabel minLineSizeLabel = new JLabel();
    private final JSpinner minLineSizeSpinner = new JSpinner();
    private final JLabel lineGapLabel = new JLabel();
    private final JSpinner lineGapSpinner = new JSpinner();
    private final JButton drawROIsManuallyButton = new JButton();
    private final JButton saveROIsButton = new JButton();
    private final JButton removeLastROIButton = new JButton();
    private final JButton removeAllROIsButton = new JButton();
    private final JToggleButton processToggleButton = new JToggleButton();
    private final JPanel previewPanel = new JPanel();
    private final JPanel previewWrapper = new JPanel();
    private final JLabel previewImage = new JLabel();
    private final ROIPanel roiPanel;

    private class ClusterConnectTask implements Runnable {

        @Override
        public void run() {
            synchronized (clusterToggleButton) {
                clusterToggleButton.setEnabled(false);
            }
            boolean buttonActive = clusterToggleButton.isSelected();
            if (buttonActive) {
                boolean connected = imageProcessorController.connectToCluster();
                if (connected) {
                    clusterToggleButton.setText("Cluster: on");
                    imageCapturerComboBox.setEnabled(true);
                    imageCapturerComboBox.removeAllItems();
                    imageProcessorController.getImageCapturerNames().stream().forEach((imageCapturerName) -> {
                        imageCapturerComboBox.addItem(imageCapturerName);
                    });
                } else {
                    clusterToggleButton.setSelected(false);
                }
            } else {
                boolean disconnected = imageProcessorController.disconnectFromCluster();
                if (disconnected) {
                    imageCapturerComboBox.setEnabled(false);
                    drawROIsAutomaticallyButton.setEnabled(false);
                    thresholdSpinner.setEnabled(false);
                    minLineSizeSpinner.setEnabled(false);
                    lineGapSpinner.setEnabled(false);
                    drawROIsManuallyButton.setEnabled(false);
                    saveROIsButton.setEnabled(false);
                    removeLastROIButton.setEnabled(false);
                    removeAllROIsButton.setEnabled(false);

                    clusterToggleButton.setText("Cluster: off");
                } else {
                    clusterToggleButton.setSelected(true);
                }
            }
            clusterToggleButton.setEnabled(true);
        }
    }

    private class CaptureDisplayTask implements Runnable {

        @Override
        public void run() {
            imageProcessorController.pullRawImage();
            BufferedImage displayedImage = imageProcessorController.getImage();
            previewImage.setIcon(ImageUtils.getScaledImage(displayedImage, 640, 480));
        }
    }

    private class ProcessImageTask implements Runnable {

        @Override
        public void run() {
            imageProcessorController.processImage();
            System.out.println(imageProcessorController.getProcessedImageChunksInformation());
        }
    }

    public ImageProcessorView() {
        super();
        this.imageCaptureRunnable = new CaptureDisplayTask();
        this.processImageRunnable = new ProcessImageTask();
        this.clusterConnectionRunnable = new ClusterConnectTask();
        this.imageProcessorController = new ImageProcessorController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controlPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1, true), "Controls"));

        clusterToggleButton.setText("Cluster: off");
        clusterToggleButton.addActionListener((ActionEvent evt) -> {
            clusterToggleButtonActionPerformed(evt);
        });

        imageCapturerLabel.setText("ImageCapturer:");
        imageCapturerComboBox.setEnabled(false);
        imageCapturerComboBox.addItemListener((ItemEvent e) -> {
            imageCapturerComboBoxItemChanged(e);
        });

        drawROIsAutomaticallyButton.setText("Draw ROIs (automatic)");
        drawROIsAutomaticallyButton.setEnabled(false);
        drawROIsAutomaticallyButton.addActionListener((ActionEvent evt) -> {
            drawROIsAutomaticallyButtonActionPerformed(evt);
        });

        horizontalLinesCheckBox.setText("Scan for horizontal lines.");
        horizontalLinesCheckBox.setEnabled(false);
        horizontalLinesCheckBox.setSelected(false);

        thresholdLabel.setText("Threshold");
        thresholdSpinner.setEnabled(false);
        thresholdSpinner.setModel(new SpinnerNumberModel(47, 0, null, 1));

        minLineSizeLabel.setText("Min Line Size");
        minLineSizeSpinner.setEnabled(false);
        minLineSizeSpinner.setModel(new SpinnerNumberModel(168, 0, null, 1));

        lineGapLabel.setText("Line Gap");
        lineGapSpinner.setEnabled(false);
        lineGapSpinner.setModel(new SpinnerNumberModel(82, 0, null, 1));

        drawROIsManuallyButton.setText("Draw ROIs (manual)");
        drawROIsManuallyButton.setEnabled(false);
        drawROIsManuallyButton.addActionListener((ActionEvent evt) -> {
            drawROIsManuallyButtonActionPerformed(evt);
        });

        saveROIsButton.setText("Save ROI");
        saveROIsButton.setEnabled(false);
        saveROIsButton.addActionListener((ActionEvent evt) -> {
            saveROIsButtonActionPerformed(evt);
        });

        removeLastROIButton.setText("Remove last ROI");
        removeLastROIButton.setEnabled(false);
        removeLastROIButton.addActionListener((ActionEvent evt) -> {
            removeLastROIButtonActionPerformed(evt);
        });

        removeAllROIsButton.setText("Remove all ROIs");
        removeAllROIsButton.setEnabled(false);
        removeAllROIsButton.addActionListener((ActionEvent evt) -> {
            removeAllROIsButtonActionPerformed(evt);
        });

        processToggleButton.setText("Start processing");
        processToggleButton.setEnabled(false);
        processToggleButton.addActionListener((ActionEvent evt) -> {
            processButtonActionPerformed(evt);
        });

        GroupLayout controlPanelLayout = new GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
                controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(clusterToggleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(imageCapturerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(imageCapturerComboBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(drawROIsAutomaticallyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(horizontalLinesCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(thresholdLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(thresholdSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(minLineSizeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(minLineSizeSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lineGapLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lineGapSpinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(drawROIsManuallyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveROIsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeLastROIButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeAllROIsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(processToggleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
                controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(clusterToggleButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageCapturerLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageCapturerComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawROIsAutomaticallyButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(horizontalLinesCheckBox)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thresholdLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thresholdSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minLineSizeLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minLineSizeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lineGapLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lineGapSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawROIsManuallyButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveROIsButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeLastROIButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeAllROIsButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(processToggleButton)
                        .addGap(0, 0, Short.MAX_VALUE))
        );

        previewPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1, true), "Image preview"));

        GroupLayout previewPanelLayout = new GroupLayout(previewPanel);
        previewPanel.setLayout(previewPanelLayout);
        previewPanelLayout.setHorizontalGroup(
                previewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(previewPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(previewWrapper, 640, 640, 640)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        previewPanelLayout.setVerticalGroup(
                previewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(previewPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(previewWrapper, 480, 480, 480)
                        .addContainerGap())
        );

        previewWrapper.setLayout(null);

        roiPanel = new ROIPanel(640, 480);
        roiPanel.setOpaque(false);
        roiPanel.setSize(new Dimension(640, 480));
        previewWrapper.add(roiPanel);

        previewImage.setSize(new Dimension(640, 480));
        previewWrapper.add(previewImage);

        previewWrapper.setSize(new Dimension(640, 480));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previewPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(previewPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );

        pack();
    }

    private void clusterToggleButtonActionPerformed(ActionEvent evt) {
        clusterConnectionService.schedule(clusterConnectionRunnable, 0, TimeUnit.MILLISECONDS);
    }

    private void imageCapturerComboBoxItemChanged(ItemEvent e) {
        String name = (String) e.getItem();
        imageProcessorController.setSelectedImageCapturerName(name);
        imageCaptureService.shutdown();
        imageCaptureService = Executors.newSingleThreadScheduledExecutor();
        imageCaptureService.scheduleAtFixedRate(imageCaptureRunnable, 0, 40, TimeUnit.MILLISECONDS);
        drawROIsAutomaticallyButton.setEnabled(true);
        horizontalLinesCheckBox.setEnabled(true);
        thresholdSpinner.setEnabled(true);
        minLineSizeSpinner.setEnabled(true);
        lineGapSpinner.setEnabled(true);
        drawROIsManuallyButton.setEnabled(true);
    }

    private void drawROIsAutomaticallyButtonActionPerformed(ActionEvent evt) {
        processToggleButton.setEnabled(true);
        roiPanel.deactivateMouseListeners();

        imageProcessorController.drawROIsAutomatically((int) thresholdSpinner.getValue(),
                (int) minLineSizeSpinner.getValue(), (int) lineGapSpinner.getValue(),
                horizontalLinesCheckBox.isSelected());
        roiPanel.setROIs(imageProcessorController.getROIs());
    }

    private void drawROIsManuallyButtonActionPerformed(ActionEvent evt) {
        processToggleButton.setEnabled(true);
        saveROIsButton.setEnabled(true);
        removeLastROIButton.setEnabled(true);
        removeAllROIsButton.setEnabled(true);
        roiPanel.activateMouseListeners();
    }

    private void saveROIsButtonActionPerformed(ActionEvent evt) {
        roiPanel.deactivateMouseListeners();
        imageProcessorController.setROIs(roiPanel.getAllROIs());
    }

    private void removeLastROIButtonActionPerformed(ActionEvent evt) {
        roiPanel.removeLastROI();
    }

    private void removeAllROIsButtonActionPerformed(ActionEvent evt) {
        roiPanel.removeAllROIs();
    }

    private void processButtonActionPerformed(ActionEvent evt) {
        roiPanel.deactivateMouseListeners();

        if (processToggleButton.isSelected()) {
            clusterToggleButton.setEnabled(false);
            imageCapturerComboBox.setEnabled(false);
            drawROIsAutomaticallyButton.setEnabled(false);
            horizontalLinesCheckBox.setEnabled(false);
            thresholdSpinner.setEnabled(false);
            minLineSizeSpinner.setEnabled(false);
            lineGapSpinner.setEnabled(false);
            drawROIsManuallyButton.setEnabled(false);
            saveROIsButton.setEnabled(false);
            removeLastROIButton.setEnabled(false);
            removeAllROIsButton.setEnabled(false);

            processToggleButton.setText("Stop processing");
            processImageService.scheduleAtFixedRate(processImageRunnable, 0, 40, TimeUnit.MILLISECONDS);
        } else {
            clusterToggleButton.setEnabled(true);
            imageCapturerComboBox.setEnabled(true);
            drawROIsAutomaticallyButton.setEnabled(true);
            horizontalLinesCheckBox.setEnabled(true);
            thresholdSpinner.setEnabled(true);
            minLineSizeSpinner.setEnabled(true);
            lineGapSpinner.setEnabled(true);
            drawROIsManuallyButton.setEnabled(true);

            processToggleButton.setText("Start processing");
            processImageService.shutdown();
            processImageService = Executors.newSingleThreadScheduledExecutor();
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageProcessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ImageProcessorView().setVisible(true);
        });
    }
}
