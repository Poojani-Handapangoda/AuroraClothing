/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedTextField extends JPanel {

    private final JTextField textField;
    private boolean showingPlaceholder = true;

    private final String placeholder = "Enter your username";

    // AURORA COLOURS
    private final Color cream = new Color(241, 237, 227);
    private final Color espresso = new Color(49, 43, 39);
    private final Color warmGrey = new Color(113, 108, 101);
    private final Color borderColor = new Color(225, 222, 215);
    private final Color champagne = new Color(190, 151, 99);

    public RoundedTextField() {

        setOpaque(false);
        setLayout(new BorderLayout(8, 0));
        setBorder(new EmptyBorder(0, 13, 0, 12));

        // =========================
        // USER ICON
        // =========================
        JLabel userIcon = new JLabel("●");

        userIcon.setFont(
                new Font("SansSerif", Font.PLAIN, 9)
        );

        userIcon.setForeground(champagne);

        add(userIcon, BorderLayout.WEST);


        // =========================
        // USERNAME TEXT FIELD
        // =========================
        textField = new JTextField();

        textField.setBorder(null);
        textField.setOpaque(false);

        // Prevent default Swing white background
        textField.setBackground(
                new Color(0, 0, 0, 0)
        );

        textField.setFont(
                new Font("SansSerif", Font.PLAIN, 12)
        );

        add(textField, BorderLayout.CENTER);


        // Show placeholder initially
        showPlaceholder();


        // =========================
        // PLACEHOLDER BEHAVIOUR
        // =========================
        textField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                if (showingPlaceholder) {

                    textField.setText("");
                    textField.setForeground(espresso);

                    showingPlaceholder = false;
                }

                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (textField.getText().trim().isEmpty()) {
                    showPlaceholder();
                }

                repaint();
            }
        });
    }


    // =========================
    // PLACEHOLDER
    // =========================
    private void showPlaceholder() {

        showingPlaceholder = true;

        textField.setText(placeholder);
        textField.setForeground(warmGrey);
    }


    // =========================
    // GET REAL USERNAME
    // =========================
    public String getText() {

        if (showingPlaceholder) {
            return "";
        }

        return textField.getText().trim();
    }
    // =========================
// SET USERNAME
// =========================
public void setText(String text) {

    if (text == null || text.trim().isEmpty()) {
        showPlaceholder();
        return;
    }

    showingPlaceholder = false;
    textField.setText(text.trim());
    textField.setForeground(espresso);
}


    // =========================
    // ROUNDED DESIGN
    // =========================
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );


        // Pearl Cream background
        g2.setColor(cream);

        g2.fillRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                18,
                18
        );


        // Champagne border when selected
        if (textField.hasFocus()) {

            g2.setColor(champagne);

        } else {

            g2.setColor(borderColor);
        }


        g2.drawRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                18,
                18
        );

        g2.dispose();
    }


    // =========================
    // DEFAULT SIZE
    // =========================
    @Override
    public Dimension getPreferredSize() {

        return new Dimension(300, 40);
    }
}