/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import javax.swing.*;

public class RoundedCardPanel extends JPanel {

    private final Color cardColor = new Color(252, 250, 245);
    private final Color shadowColor = new Color(0, 0, 0, 28);

    public RoundedCardPanel() {

        setOpaque(false);
        
        setPreferredSize(new Dimension(420, 560));
setMinimumSize(new Dimension(360, 500));
setMaximumSize(new Dimension(500, 620));

        // Space reserved around the card for the shadow
        setBorder(
            BorderFactory.createEmptyBorder(10, 10, 14, 14)
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        int shadow = 7;
        int radius = 28;

        int width = getWidth() - 18;
        int height = getHeight() - 18;

        // SOFT SHADOW
        g2.setColor(shadowColor);

        g2.fillRoundRect(
            12,
            12,
            width,
            height,
            radius,
            radius
        );

        // WHITE / CREAM CARD
        g2.setColor(cardColor);

        g2.fillRoundRect(
            6,
            6,
            width,
            height,
            radius,
            radius
        );

        g2.dispose();

        super.paintComponent(g);
    }

    
}
