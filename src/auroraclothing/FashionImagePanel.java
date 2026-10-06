/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FashionImagePanel extends JPanel {

    private BufferedImage fashionImage;

    public FashionImagePanel() {

        // Keep the unused part of our curved panel transparent
        // so pnlBranding's green shows through.
        setOpaque(false);

        try {

            fashionImage = ImageIO.read(
                    getClass().getResource(
                            "/auroraclothing/images/aurora-boutique.png"
                    )
            );

        } catch (IOException | IllegalArgumentException e) {

            System.out.println(
                    "AURORA boutique image could not be loaded."
            );
        }
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (fashionImage == null) {
            return;
        }


        Graphics2D g2 = (Graphics2D) g.create();


        // ==========================================
        // HIGH QUALITY DRAWING
        // ==========================================

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );

        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );


        int w = getWidth();
        int h = getHeight();


        // ==========================================
        // AURORA CURVED IMAGE SHAPE
        //
        // GREEN                     IMAGE
        //
        //      (────────────────────────
        //        (
        //           (
        //              (
        //                 |
        //                 |
        //                 |       IMAGE
        //                 |
        //                 |
        //                 |
        //                 |
        //                 |
        //
        // Image extends farther LEFT at the top,
        // then gently curves toward the RIGHT.
        // After the curve, the edge becomes vertical.
        // ==========================================

        Path2D.Double imageShape = new Path2D.Double();


        // Top-left starting point of photograph.
        // 5% means the image reaches almost completely
        // into the left side of this custom panel.
        imageShape.moveTo(
                w * 0.08,
                0
        );


        // Straight top edge.
        imageShape.lineTo(
                w,
                0
        );


        // Straight right edge.
        imageShape.lineTo(
                w,
                h
        );


        // Bottom edge ends at 45% of panel width.
        imageShape.lineTo(
                w * 0.10,
                h
        );


        // Straight vertical left edge of the lower image.
        // It travels upward until 38% of panel height.
        imageShape.lineTo(
                w * 0.10,
                h * 0.38
        );


        // ==========================================
        // MAIN CURVE
        //
        // Begins at:
        // 42% width / 48% height
        //
        // Ends at:
        // 8% width / top
        //
        // This creates the image bulging LEFT
        // into the green branding area.
        // ==========================================

        imageShape.curveTo(

                // Lower control point:
                // keeps the beginning almost vertical.
                w * 0.45,
                h * 0.09,

                // Upper control point:
                // pulls the image outward toward green.
                w * 0.30,
                h * 0.40,

                // Finish at top-left.
                w * 0.03,
                0
        );


        imageShape.closePath();


        // ==========================================
        // VERY IMPORTANT:
        // Clip photograph to curved shape.
        // This was the missing line before.
        // ==========================================

        g2.setClip(imageShape);


        // ==========================================
        // CROP IMAGE TO FILL PANEL
        // WITHOUT STRETCHING
        // ==========================================

        double imageRatio =
                (double) fashionImage.getWidth()
                / (double) fashionImage.getHeight();

        double panelRatio =
                (double) w
                / (double) h;


        int drawWidth;
        int drawHeight;

        int drawX;
        int drawY;


        if (imageRatio > panelRatio) {

            // Image is proportionally wider than panel.
            // Match height and crop excess width.

            drawHeight = h;

            drawWidth =
                    (int) Math.ceil(
                            h * imageRatio
                    );

            drawX =
                    (w - drawWidth) / 2;

            drawY = 0;

        } else {

            // Image is proportionally taller than panel.
            // Match width and crop excess height.

            drawWidth = w;

            drawHeight =
                    (int) Math.ceil(
                            w / imageRatio
                    );

            drawX = 0;

            drawY =
                    (h - drawHeight) / 2;
        }


        // ==========================================
        // DRAW THE BOUTIQUE IMAGE
        // ==========================================

        g2.drawImage(
                fashionImage,
                drawX,
                drawY,
                drawWidth,
                drawHeight,
                null
        );
        
        // ==========================================
// LUXURY GOLD CURVED OUTLINE
// ==========================================

// Remove clipping so the complete outline can be painted
g2.setClip(null);

g2.setColor(
        new java.awt.Color(201, 164, 76)
);

g2.setStroke(
        new java.awt.BasicStroke(
                2.0f,
                java.awt.BasicStroke.CAP_ROUND,
                java.awt.BasicStroke.JOIN_ROUND
        )
);

g2.draw(imageShape);


        g2.dispose();
    }


    // ==============================================
    // DEFAULT COMPONENT SIZE
    // ==============================================

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(
                250,
                550
        );
    }
}