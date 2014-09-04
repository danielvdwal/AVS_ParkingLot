package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
 * @author dvanderw
 */
public class TestFrame extends JFrame {

    private final JPanel controlPanel = new JPanel();
    private final JToggleButton clusterToggleButton = new JToggleButton();
    private final JButton drawLinesButton = new JButton();
    private final JLabel thresholdLabel = new JLabel();
    private final JSpinner thresholdSpinner = new JSpinner();
    private final JLabel minLineSizeLabel = new JLabel();
    private final JSpinner minLineSizeSpinner = new JSpinner();
    private final JLabel lineGapLabel = new JLabel();
    private final JSpinner lineGapSpinner = new JSpinner();
    private final JButton processButton = new JButton();
    private final JPanel previewPanel = new JPanel();
    private final JPanel previewWrapper = new JPanel();
    private final JLabel previewImage = new JLabel();
    private final ROIPanel roiPanel;

    private void clusterToggleButtonActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void drawLinesButtonActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void processButtonActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TestFrame() {
        super();
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(new File("parkinglot_0.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(TestFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controlPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1, true), "Controls"));

        clusterToggleButton.setText("Cluster: off");
        clusterToggleButton.addActionListener((ActionEvent evt) -> {
            clusterToggleButtonActionPerformed(evt);
        });

        drawLinesButton.setText("Draw lines");
        drawLinesButton.setEnabled(false);
        drawLinesButton.addActionListener((ActionEvent evt) -> {
            drawLinesButtonActionPerformed(evt);
        });

        thresholdLabel.setText("Threshold");

        thresholdSpinner.setModel(new SpinnerNumberModel(47, 0, null, 1));

        minLineSizeLabel.setText("Min Line Size");

        minLineSizeSpinner.setModel(new SpinnerNumberModel(168, 0, null, 1));

        lineGapSpinner.setModel(new SpinnerNumberModel(82, 0, null, 1));

        lineGapLabel.setText("Line Gap");

        processButton.setText("Process");
        processButton.addActionListener((ActionEvent evt) -> {
            processButtonActionPerformed(evt);
        });

        GroupLayout controlPanelLayout = new GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
                controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(clusterToggleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(drawLinesButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(controlPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(thresholdSpinner)
                                .addComponent(minLineSizeSpinner)
                                .addComponent(lineGapSpinner)
                                .addGroup(controlPanelLayout.createSequentialGroup()
                                        .addGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(thresholdLabel)
                                                .addComponent(minLineSizeLabel)
                                                .addComponent(lineGapLabel))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(processButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
                controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(clusterToggleButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawLinesButton)
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
                        .addComponent(processButton)
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
        previewImage.setIcon(getScaledImage(temp, 640, 480));
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

    private ImageIcon getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return new ImageIcon(resizedImg);
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TestFrame().setVisible(true);
        });
    }
}
