/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedButton extends JButton {

    private final Color champagne = new Color(190, 151, 99);
    private final Color champagneHover = new Color(174, 135, 86);
    private final Color warmWhite = new Color(255, 253, 249);

    private boolean hovering = false;

    public RoundedButton() {

        super("SIGN IN   →");

        setFont(
                new Font("SansSerif", Font.BOLD, 11)
        );

        setForeground(warmWhite);

        // Remove default Swing button appearance
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        setHorizontalAlignment(SwingConstants.CENTER);

        // Mouse hover effect
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Normal / hover colour
        if (hovering) {
            g2.setColor(champagneHover);
        } else {
            g2.setColor(champagne);
        }

        // Rounded background
        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                18,
                18
        );

        g2.dispose();

        // Draw button text
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 42);
    }
}
