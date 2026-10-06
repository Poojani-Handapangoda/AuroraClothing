/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LuxuryCard extends JPanel {

    private int radius = 24;

    public LuxuryCard() {

        setOpaque(false);

        setBackground(
                new Color(250, 248, 242)
        );

        setBorder(
                new EmptyBorder(18, 20, 18, 20)
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int w = getWidth();
        int h = getHeight();


        // =====================================
        // SOFT SHADOW
        // =====================================

        g2.setColor(
                new Color(60, 74, 52, 18)
        );

        g2.fill(
                new RoundRectangle2D.Double(
                        4,
                        6,
                        w - 8,
                        h - 8,
                        radius,
                        radius
                )
        );


        // =====================================
        // IVORY CARD
        // =====================================

        g2.setColor(
                new Color(250, 248, 242)
        );

        g2.fill(
                new RoundRectangle2D.Double(
                        1,
                        1,
                        w - 7,
                        h - 9,
                        radius,
                        radius
                )
        );


        // =====================================
        // VERY SUBTLE GOLD BORDER
        // =====================================

        g2.setColor(
                new Color(201, 164, 76, 80)
        );

        g2.setStroke(
                new BasicStroke(1f)
        );

        g2.draw(
                new RoundRectangle2D.Double(
                        1,
                        1,
                        w - 7,
                        h - 9,
                        radius,
                        radius
                )
        );

        g2.dispose();

        super.paintComponent(g);
    }
}
