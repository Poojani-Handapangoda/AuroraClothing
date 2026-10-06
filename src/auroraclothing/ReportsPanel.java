/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ReportsPanel extends JPanel {

    // =========================================================
    // AURORA COLORS
    // =========================================================

    private final Color DEEP_GREEN = new Color(60, 74, 52);
    private final Color DARK_GREEN = new Color(42, 55, 39);
    private final Color SOFT_GREEN = new Color(91, 116, 101);

    private final Color GOLD = new Color(201, 164, 76);
    private final Color CREAM = new Color(238, 235, 224);
    private final Color IVORY = new Color(250, 248, 242);

    private final Color TEXT = new Color(55, 52, 46);
    private final Color MUTED = new Color(125, 122, 113);


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ReportsPanel() {

        setLayout(new BorderLayout());
        setBackground(CREAM);

        setBorder(
                new EmptyBorder(
                        28,
                        32,
                        28,
                        32
                )
        );

        createUI();
    }


    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        // =====================================================
        // HEADER
        // =====================================================

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);


        JPanel titleArea =
                new JPanel();

        titleArea.setOpaque(false);

        titleArea.setLayout(
                new BoxLayout(
                        titleArea,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Reports & Analytics"
                );

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(DEEP_GREEN);


        JLabel subtitle =
                new JLabel(
                        "Business insights and performance overview for AURORA Clothing."
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(MUTED);


        titleArea.add(title);
        titleArea.add(Box.createVerticalStrut(4));
        titleArea.add(subtitle);


        top.add(
                titleArea,
                BorderLayout.WEST
        );


        // =====================================================
        // PERIOD FILTER
        // =====================================================

        JComboBox<String> period =
                new JComboBox<>(
                        new String[]{
                            "This Month",
                            "Last Month",
                            "Last 3 Months",
                            "This Year"
                        }
                );

        period.setPreferredSize(
                new Dimension(
                        145,
                        40
                )
        );

        period.setBackground(IVORY);
        period.setForeground(DEEP_GREEN);

        period.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );


        top.add(
                period,
                BorderLayout.EAST
        );


        add(
                top,
                BorderLayout.NORTH
        );


        // =====================================================
        // CONTENT
        // =====================================================

        JPanel content =
                new JPanel();

        content.setOpaque(false);

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.setBorder(
                new EmptyBorder(
                        22,
                        0,
                        0,
                        0
                )
        );


        // =====================================================
        // REPORTS HERO
        // =====================================================

        JPanel hero =
                createReportsHero();

        hero.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        hero.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        165
                )
        );

        content.add(hero);

        content.add(
                Box.createVerticalStrut(20)
        );


        // =====================================================
        // REPORT INFORMATION
        // =====================================================

        JPanel stats =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        stats.setOpaque(false);

        stats.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        105
                )
        );

        stats.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        /*
         * These cards describe what the reporting module provides.
         * We removed the old fake numeric values such as LKR 125K,
         * 218 orders and Classic Tee.
         */

        stats.add(
                createStatCard(
                        "SALES REPORTING",
                        "LIVE",
                        "Connected to MySQL"
                )
        );

        stats.add(
                createStatCard(
                        "DATA SOURCE",
                        "4 TABLES",
                        "Integrated sales data"
                )
        );

        stats.add(
                createStatCard(
                        "REPORT ENGINE",
                        "JASPER",
                        "JasperReports 7"
                )
        );

        stats.add(
                createStatCard(
                        "REPORT STATUS",
                        "READY",
                        "Generate live report"
                )
        );


        content.add(stats);

        content.add(
                Box.createVerticalStrut(22)
        );


        // =====================================================
        // BUSINESS REPORTS TITLE
        // =====================================================

        JLabel reportTitle =
                new JLabel(
                        "Business Reports"
                );

        reportTitle.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        18
                )
        );

        reportTitle.setForeground(DEEP_GREEN);

        reportTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        content.add(reportTitle);

        content.add(
                Box.createVerticalStrut(12)
        );


        // =====================================================
        // REPORT CARDS
        // =====================================================

        JPanel reports =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        reports.setOpaque(false);

        reports.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        150
                )
        );

        reports.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // =====================================================
        // SALES REPORT - REAL JASPER REPORT
        // =====================================================

        JPanel salesReportCard =
                createReportCard(
                        "↗",
                        "Sales Report",
                        "View revenue, orders and sales performance.",
                        true
                );

        reports.add(
                salesReportCard
        );


        // =====================================================
        // INVENTORY REPORT
        // =====================================================

        reports.add(
                createReportCard(
                        "▣",
                        "Inventory Report",
                        "Review stock levels and inventory movement.",
                        false
                )
        );


        // =====================================================
        // CUSTOMER REPORT
        // =====================================================

        reports.add(
                createReportCard(
                        "♙",
                        "Customer Report",
                        "Analyse customers and purchase activity.",
                        false
                )
        );


        content.add(reports);

        content.add(
                Box.createVerticalStrut(22)
        );


        // =====================================================
        // REPORT SYSTEM INFORMATION
        // =====================================================

        JPanel performance =
                createPerformancePanel();

        performance.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        performance.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        190
                )
        );

        content.add(performance);


        content.add(
                Box.createVerticalStrut(20)
        );


        // =====================================================
        // SCROLL
        // =====================================================

        JScrollPane scroll =
                new JScrollPane(content);

        scroll.setBorder(null);
        scroll.setOpaque(false);

        scroll.getViewport()
                .setOpaque(false);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(14);


        add(
                scroll,
                BorderLayout.CENTER
        );
    }


    // =========================================================
    // REPORTS HERO
    // =========================================================

    private JPanel createReportsHero() {

        ImageIcon imageIcon = null;

        try {

            java.net.URL imageURL =
                    getClass().getResource(
                            "/auroraclothing/images/reports-banner.png"
                    );

            if (imageURL != null) {

                imageIcon =
                        new ImageIcon(
                                imageURL
                        );

            } else {

                System.out.println(
                        "reports-banner.png not found."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Could not load reports banner."
            );
        }


        final Image bannerImage =
                imageIcon != null
                        ? imageIcon.getImage()
                        : null;


        JPanel hero =
                new JPanel(
                        new BorderLayout()
                ) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();


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


                        Shape rounded =
                                new RoundRectangle2D.Double(
                                        0,
                                        0,
                                        w,
                                        h,
                                        26,
                                        26
                                );

                        g2.setClip(rounded);


                        g2.setColor(DARK_GREEN);

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );


                        if (bannerImage != null) {

                            int imageWidth =
                                    bannerImage.getWidth(null);

                            int imageHeight =
                                    bannerImage.getHeight(null);


                            if (
                                    imageWidth > 0
                                    && imageHeight > 0
                            ) {

                                double scale =
                                        Math.max(
                                                (double) w
                                                / imageWidth,

                                                (double) h
                                                / imageHeight
                                        );


                                int scaledWidth =
                                        (int) (
                                                imageWidth
                                                * scale
                                        );

                                int scaledHeight =
                                        (int) (
                                                imageHeight
                                                * scale
                                        );


                                int x =
                                        (w - scaledWidth)
                                        / 2;

                                int y =
                                        (h - scaledHeight)
                                        / 2;


                                g2.drawImage(
                                        bannerImage,
                                        x,
                                        y,
                                        scaledWidth,
                                        scaledHeight,
                                        null
                                );
                            }
                        }


                        GradientPaint blend =
                                new GradientPaint(
                                        0,
                                        0,

                                        new Color(
                                                42,
                                                55,
                                                39,
                                                245
                                        ),

                                        (int) (
                                                w * 0.63
                                        ),

                                        0,

                                        new Color(
                                                42,
                                                55,
                                                39,
                                                0
                                        )
                                );


                        g2.setPaint(blend);

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );


                        GradientPaint lowerShade =
                                new GradientPaint(
                                        0,
                                        (int) (
                                                h * 0.45
                                        ),

                                        new Color(
                                                20,
                                                30,
                                                20,
                                                0
                                        ),

                                        0,
                                        h,

                                        new Color(
                                                20,
                                                30,
                                                20,
                                                65
                                        )
                                );


                        g2.setPaint(lowerShade);

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );


                        g2.setClip(null);

                        g2.setColor(
                                new Color(
                                        201,
                                        164,
                                        76,
                                        150
                                )
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        1.2f
                                )
                        );

                        g2.drawRoundRect(
                                0,
                                0,
                                w - 1,
                                h - 1,
                                26,
                                26
                        );


                        g2.dispose();
                    }
                };


        hero.setOpaque(false);

        hero.setBorder(
                new EmptyBorder(
                        22,
                        26,
                        22,
                        26
                )
        );

        hero.setPreferredSize(
                new Dimension(
                        900,
                        165
                )
        );


        JPanel heroText =
                new JPanel();

        heroText.setOpaque(false);

        heroText.setLayout(
                new BoxLayout(
                        heroText,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel smallTitle =
                new JLabel(
                        "AURORA INSIGHTS"
                );

        smallTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        smallTitle.setForeground(GOLD);

        smallTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel mainTitle =
                new JLabel(
                        "Insights Behind the Style"
                );

        mainTitle.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        25
                )
        );

        mainTitle.setForeground(
                Color.WHITE
        );

        mainTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel description =
                new JLabel(
                        "Transform AURORA data into smarter business decisions."
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        description.setForeground(
                new Color(
                        224,
                        224,
                        214
                )
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel accent =
                new JLabel(
                        "━━━━  ✦"
                );

        accent.setFont(
                new Font(
                        "Serif",
                        Font.PLAIN,
                        10
                )
        );

        accent.setForeground(GOLD);

        accent.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        heroText.add(
                Box.createVerticalGlue()
        );

        heroText.add(smallTitle);

        heroText.add(
                Box.createVerticalStrut(5)
        );

        heroText.add(mainTitle);

        heroText.add(
                Box.createVerticalStrut(5)
        );

        heroText.add(description);

        heroText.add(
                Box.createVerticalStrut(8)
        );

        heroText.add(accent);

        heroText.add(
                Box.createVerticalGlue()
        );


        hero.add(
                heroText,
                BorderLayout.WEST
        );


        return hero;
    }


    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            String value,
            String subtitle
    ) {

        LuxuryCard card =
                new LuxuryCard();

        card.setLayout(
                new BorderLayout()
        );


        JPanel inside =
                new JPanel();

        inside.setOpaque(false);

        inside.setLayout(
                new BoxLayout(
                        inside,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        titleLabel.setForeground(GOLD);


        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        22
                )
        );

        valueLabel.setForeground(DEEP_GREEN);


        JLabel subtitleLabel =
                new JLabel(subtitle);

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        subtitleLabel.setForeground(SOFT_GREEN);


        inside.add(titleLabel);

        inside.add(
                Box.createVerticalStrut(5)
        );

        inside.add(valueLabel);

        inside.add(
                Box.createVerticalStrut(3)
        );

        inside.add(subtitleLabel);


        card.add(
                inside,
                BorderLayout.CENTER
        );


        return card;
    }


    // =========================================================
    // REPORT CARD
    // =========================================================

    private JPanel createReportCard(
            String icon,
            String title,
            String description,
            boolean salesReport
    ) {

        LuxuryCard card =
                new LuxuryCard();

        card.setLayout(
                new BorderLayout()
        );


        JPanel inside =
                new JPanel();

        inside.setOpaque(false);

        inside.setLayout(
                new BoxLayout(
                        inside,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        20
                )
        );

        iconLabel.setForeground(GOLD);


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        16
                )
        );

        titleLabel.setForeground(DEEP_GREEN);


        JLabel descriptionLabel =
                new JLabel(
                        "<html>"
                        + description
                        + "</html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        descriptionLabel.setForeground(MUTED);


        JButton generate =
                new JButton(
                        "Generate Report  →"
                );

        generate.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        generate.setForeground(DEEP_GREEN);
        generate.setBackground(IVORY);
        generate.setFocusPainted(false);

        generate.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        0,
                        1,
                        0,
                        GOLD
                )
        );

        generate.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        generate.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // =====================================================
        // SALES REPORT ACTION
        // =====================================================

        if (salesReport) {

            generate.addActionListener(
                    e -> JasperReportService.showSalesReport()
            );

            card.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            card.addMouseListener(
                    new MouseAdapter() {

                        @Override
                        public void mouseClicked(
                                MouseEvent e
                        ) {

                            // Prevent double opening when the button itself
                            // is clicked.
                            if (
                                    !SwingUtilities.isDescendingFrom(
                                            e.getComponent(),
                                            generate
                                    )
                            ) {

                                JasperReportService.showSalesReport();
                            }
                        }
                    }
            );

        } else {

            generate.addActionListener(
                    e -> JOptionPane.showMessageDialog(
                            this,
                            title
                            + " will be available in the reporting module.",
                            "AURORA Reports",
                            JOptionPane.INFORMATION_MESSAGE
                    )
            );
        }


        inside.add(iconLabel);

        inside.add(
                Box.createVerticalStrut(5)
        );

        inside.add(titleLabel);

        inside.add(
                Box.createVerticalStrut(5)
        );

        inside.add(descriptionLabel);

        inside.add(
                Box.createVerticalGlue()
        );

        inside.add(
                Box.createVerticalStrut(8)
        );

        inside.add(generate);


        card.add(
                inside,
                BorderLayout.CENTER
        );


        return card;
    }


    // =========================================================
    // REPORT SYSTEM INFORMATION
    // =========================================================

    private JPanel createPerformancePanel() {

        LuxuryCard card =
                new LuxuryCard();

        card.setLayout(
                new BorderLayout()
        );


        JPanel heading =
                new JPanel(
                        new BorderLayout()
                );

        heading.setOpaque(false);


        JLabel title =
                new JLabel(
                        "Reporting System"
                );

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        17
                )
        );

        title.setForeground(DEEP_GREEN);


        JLabel status =
                new JLabel(
                        "●  Connected"
                );

        status.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        status.setForeground(SOFT_GREEN);


        heading.add(
                title,
                BorderLayout.WEST
        );

        heading.add(
                status,
                BorderLayout.EAST
        );


        card.add(
                heading,
                BorderLayout.NORTH
        );


        JPanel bars =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                10
                        )
                );

        bars.setOpaque(false);

        bars.setBorder(
                new EmptyBorder(
                        18,
                        0,
                        0,
                        0
                )
        );


        bars.add(
                createInfoRow(
                        "Database",
                        "AURORA MySQL"
                )
        );

        bars.add(
                createInfoRow(
                        "Report Engine",
                        "JasperReports 7.0.8"
                )
        );

        bars.add(
                createInfoRow(
                        "Sales Report",
                        "Ready to Generate"
                )
        );


        card.add(
                bars,
                BorderLayout.CENTER
        );


        return card;
    }


    // =========================================================
    // INFORMATION ROW
    // =========================================================

    private JPanel createInfoRow(
            String text,
            String value
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        row.setOpaque(false);


        JLabel label =
                new JLabel(text);

        label.setPreferredSize(
                new Dimension(
                        130,
                        20
                )
        );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        label.setForeground(TEXT);


        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        valueLabel.setForeground(DEEP_GREEN);


        row.add(
                label,
                BorderLayout.WEST
        );

        row.add(
                valueLabel,
                BorderLayout.CENTER
        );


        return row;
    }
}