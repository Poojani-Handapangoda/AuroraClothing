/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

public class GoldCurvePanel extends JPanel {

    public GoldCurvePanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // AURORA antique gold
        g2.setColor(new Color(201, 164, 76));

        // Thin elegant luxury line
        g2.setStroke(
                new BasicStroke(
                        1.8f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        int w = getWidth();
        int h = getHeight();

        Path2D.Double curve = new Path2D.Double();

        // Start near bottom-left
        curve.moveTo(0, h * 0.82);

        // First gentle rise
        curve.curveTo(
                w * 0.20, h * 0.82,
                w * 0.28, h * 0.67,
                w * 0.43, h * 0.62
        );

        // Main luxury sweep upward
        curve.curveTo(
                w * 0.60, h * 0.55,
                w * 0.70, h * 0.25,
                w, h * 0.10
        );

        g2.draw(curve);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(260, 90);
    }
}