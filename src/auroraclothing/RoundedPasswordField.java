/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedPasswordField extends JPanel {

    private final JPasswordField passwordField;
    private final JButton eyeButton;

    private boolean passwordVisible = false;
    private boolean showingPlaceholder = true;

    private final String placeholder = "Enter your password";

    // AURORA COLOURS
    private final Color cream = new Color(241, 237, 227);
    private final Color espresso = new Color(49, 43, 39);
    private final Color warmGrey = new Color(113, 108, 101);
    private final Color borderColor = new Color(225, 222, 215);
    private final Color champagne = new Color(190, 151, 99);


    public RoundedPasswordField() {

        setOpaque(false);

        setLayout(
                new BorderLayout(8, 0)
        );

        setBorder(
                new EmptyBorder(0, 12, 0, 8)
        );


        // =========================
        // LOCK ICON
        // =========================
        JLabel lockIcon = new JLabel("🔒");

        lockIcon.setForeground(warmGrey);

        lockIcon.setFont(
                new Font("SansSerif", Font.PLAIN, 13)
        );

        add(lockIcon, BorderLayout.WEST);


        // =========================
        // PASSWORD FIELD
        // =========================
        passwordField = new JPasswordField();

        passwordField.setBorder(null);
        passwordField.setOpaque(false);

        // Prevent default Swing white background
        passwordField.setBackground(
                new Color(0, 0, 0, 0)
        );

        passwordField.setFont(
                new Font("SansSerif", Font.PLAIN, 12)
        );

        add(passwordField, BorderLayout.CENTER);


        // =========================
        // EYE BUTTON
        // =========================
        eyeButton = new JButton("◉");

        eyeButton.setBorder(null);

        eyeButton.setFocusPainted(false);

        eyeButton.setContentAreaFilled(false);

        // Prevent white square behind eye
        eyeButton.setOpaque(false);

        eyeButton.setForeground(warmGrey);

        eyeButton.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
        );

        eyeButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        add(eyeButton, BorderLayout.EAST);


        // Show placeholder initially
        showPlaceholder();


        // =========================
        // PLACEHOLDER BEHAVIOUR
        // =========================
        passwordField.addFocusListener(
                new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                if (showingPlaceholder) {

                    passwordField.setText("");

                    passwordField.setEchoChar('\u2022');

                    passwordField.setForeground(espresso);

                    showingPlaceholder = false;
                    passwordVisible = false;

                    eyeButton.setText("◉");
                }

                repaint();
            }


            @Override
            public void focusLost(FocusEvent e) {

                if (passwordField.getPassword().length == 0) {

                    showPlaceholder();
                }

                repaint();
            }
        });


        // =========================
        // SHOW / HIDE PASSWORD
        // =========================
        eyeButton.addActionListener(e -> {

            if (showingPlaceholder) {

                passwordField.requestFocusInWindow();

                return;
            }


            passwordVisible = !passwordVisible;


            if (passwordVisible) {

                // SHOW PASSWORD
                passwordField.setEchoChar((char) 0);

                eyeButton.setText("○");

            } else {

                // HIDE PASSWORD
                passwordField.setEchoChar('\u2022');

                eyeButton.setText("◉");
            }


            passwordField.requestFocusInWindow();

            repaint();
        });
    }


    // =========================
    // PLACEHOLDER
    // =========================
    private void showPlaceholder() {

        showingPlaceholder = true;
        passwordVisible = false;

        passwordField.setEchoChar((char) 0);

        passwordField.setText(placeholder);

        passwordField.setForeground(warmGrey);

        eyeButton.setText("◉");
    }


    // =========================
    // GET REAL PASSWORD
    // =========================
    public String getPasswordText() {

        if (showingPlaceholder) {

            return "";
        }

        return new String(
                passwordField.getPassword()
        );
    }
    // =========================
// CLEAR PASSWORD
// =========================
public void clearPassword() {

    showPlaceholder();
}


    // =========================
    // ROUNDED DESIGN
    // =========================
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g.create();


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


        // Champagne border while typing
        if (passwordField.hasFocus()) {

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
  