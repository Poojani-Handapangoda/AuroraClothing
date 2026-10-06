/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package auroraclothing;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Dashboard extends JFrame {

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
    private final Color LIGHT_GOLD = new Color(226, 205, 148);

    private JPanel pageContainer;

    private Image heroImage;

    // NEW
    private Image sidebarImage;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Dashboard() {

        setTitle("AURORA Clothing | Dashboard");

        setSize(1200, 720);

        setMinimumSize(
                new Dimension(
                        1100,
                        650
                )
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setLayout(
                new BorderLayout()
        );

        getContentPane().setBackground(CREAM);

        // =====================================================
        // LOAD DASHBOARD HERO IMAGE
        // =====================================================

        java.net.URL heroURL =
                getClass().getResource(
                        "/auroraclothing/images/aurora-dashboard-hero.png"
                );

        if (heroURL != null) {

            heroImage =
                    new ImageIcon(heroURL)
                            .getImage();

        } else {

            System.out.println(
                    "aurora-dashboard-hero.png not found."
            );
        }

        // =====================================================
        // LOAD SIDEBAR BACKGROUND IMAGE
        // =====================================================

        java.net.URL sidebarURL =
                getClass().getResource(
                        "/auroraclothing/images/aurora-sidebar-bg.png"
                );

        if (sidebarURL != null) {

            sidebarImage =
                    new ImageIcon(sidebarURL)
                            .getImage();

        } else {

            System.out.println(
                    "aurora-sidebar-bg.png not found."
            );
        }

        createUI();
    }

    // =========================================================
    // CREATE MAIN UI
    // =========================================================

    private void createUI() {

        JPanel sidebar =
                createSidebar();

        getContentPane().add(
                sidebar,
                BorderLayout.WEST
        );

        pageContainer =
                new JPanel(
                        new BorderLayout()
                );

        pageContainer.setBackground(CREAM);

        getContentPane().add(
                pageContainer,
                BorderLayout.CENTER
        );

        showDashboard();
    }

    // =========================================================
    // PAGE SWITCHING
    // =========================================================

    private void showDashboard() {

        pageContainer.removeAll();

        pageContainer.add(
                createDashboardPage(),
                BorderLayout.CENTER
        );

        pageContainer.revalidate();
        pageContainer.repaint();
    }

    private void showProducts() {

        pageContainer.removeAll();

        pageContainer.add(
                new ProductsPanel(),
                BorderLayout.CENTER
        );

        pageContainer.revalidate();
        pageContainer.repaint();
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        /*
         * The sidebar itself paints the photograph.
         *
         * The image uses COVER scaling:
         * - no stretching
         * - aspect ratio preserved
         * - fills entire sidebar
         */

        JPanel sidebar =
                new JPanel() {

                    @Override
                    protected void paintComponent(Graphics g) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        // HIGH QUALITY IMAGE RENDERING
                        g2.setRenderingHint(
                                RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BICUBIC
                        );

                        g2.setRenderingHint(
                                RenderingHints.KEY_RENDERING,
                                RenderingHints.VALUE_RENDER_QUALITY
                        );

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int w = getWidth();
                        int h = getHeight();

                        // -----------------------------------------
                        // GREEN FALLBACK BACKGROUND
                        // -----------------------------------------

                        g2.setColor(DARK_GREEN);

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );

                        // -----------------------------------------
                        // SIDEBAR IMAGE
                        // -----------------------------------------

                        if (sidebarImage != null) {

                            int imageWidth =
                                    sidebarImage.getWidth(null);

                            int imageHeight =
                                    sidebarImage.getHeight(null);

                            if (
                                    imageWidth > 0
                                    && imageHeight > 0
                            ) {

                                /*
                                 * COVER scaling.
                                 *
                                 * Because the source picture is vertical,
                                 * this should fit the sidebar beautifully.
                                 */

                                double scale =
                                        Math.max(
                                                (double) w / imageWidth,
                                                (double) h / imageHeight
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
                                        sidebarImage,
                                        x,
                                        y,
                                        scaledWidth,
                                        scaledHeight,
                                        null
                                );
                            }
                        }

                        // -----------------------------------------
                        // LUXURY DARK GREEN OVERLAY
                        // -----------------------------------------
                        /*
                         * This is VERY important.
                         *
                         * It keeps the photograph visible while making
                         * menu labels easy to read.
                         */

                        g2.setColor(
                                new Color(
                                        22,
                                        36,
                                        24,
                                        100
                                )
                        );

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );

                        // -----------------------------------------
                        // LEFT / RIGHT SOFT DEPTH
                        // -----------------------------------------

                        GradientPaint sideShade =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                20,
                                                32,
                                                20,
                                                80
                                        ),

                                        w,
                                        0,
                                        new Color(
                                                20,
                                                32,
                                                20,
                                                15
                                        )
                                );

                        g2.setPaint(sideShade);

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );

                        // -----------------------------------------
                        // SUBTLE GOLD RIGHT EDGE
                        // -----------------------------------------

                        g2.setColor(
                                new Color(
                                        201,
                                        164,
                                        76,
                                        90
                                )
                        );

                        g2.fillRect(
                                w - 1,
                                0,
                                1,
                                h
                        );

                        g2.dispose();
                    }
                };

        sidebar.setPreferredSize(
                new Dimension(
                        235,
                        0
                )
        );

        sidebar.setBackground(DEEP_GREEN);

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                new EmptyBorder(
                        24,
                        22,
                        22,
                        22
                )
        );

        // =====================================================
        // BRANDING
        // =====================================================

        JPanel branding =
                new JPanel();

        branding.setOpaque(false);

        branding.setLayout(
                new BoxLayout(
                        branding,
                        BoxLayout.Y_AXIS
                )
        );

        branding.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        branding.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        180
                )
        );

        // =====================================================
        // LOGO
        // =====================================================

        JLabel logo =
                new JLabel();

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        java.net.URL logoURL =
                getClass().getResource(
                        "/auroraclothing/images/aurora-logo.png"
                );

        if (logoURL != null) {

            ImageIcon original =
                    new ImageIcon(logoURL);

            Image scaled =
                    original.getImage()
                            .getScaledInstance(
                                    72,
                                    72,
                                    Image.SCALE_SMOOTH
                            );

            logo.setIcon(
                    new ImageIcon(scaled)
            );
        }

        branding.add(logo);

        branding.add(
                Box.createVerticalStrut(8)
        );

        // =====================================================
        // AURORA
        // =====================================================

        JLabel aurora =
                new JLabel(
                        "A U R O R A"
                );

        aurora.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        21
                )
        );

        aurora.setForeground(GOLD);

        aurora.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        branding.add(aurora);

        branding.add(
                Box.createVerticalStrut(3)
        );

        // =====================================================
        // CLOTHING
        // =====================================================

        JLabel clothing =
                new JLabel(
                        "C L O T H I N G"
                );

        clothing.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        clothing.setForeground(
                new Color(
                        245,
                        241,
                        225
                )
        );

        clothing.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        branding.add(clothing);

        branding.add(
                Box.createVerticalStrut(14)
        );

        // =====================================================
        // GOLD LINE
        // =====================================================

        JPanel goldLine =
                new JPanel();

        goldLine.setBackground(GOLD);

        goldLine.setPreferredSize(
                new Dimension(
                        55,
                        2
                )
        );

        goldLine.setMaximumSize(
                new Dimension(
                        55,
                        2
                )
        );

        goldLine.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        branding.add(goldLine);

        sidebar.add(branding);

        sidebar.add(
                Box.createVerticalStrut(18)
        );

        // =====================================================
        // DASHBOARD
        // =====================================================

        JButton dashboardButton =
                createNavButton(
                        "◇",
                        "Dashboard"
                );

        dashboardButton.addActionListener(
                e -> showDashboard()
        );

        sidebar.add(dashboardButton);

        sidebar.add(
                Box.createVerticalStrut(7)
        );

        // =====================================================
        // PRODUCTS
        // =====================================================

        JButton productsButton =
                createNavButton(
                        "♢",
                        "Products"
                );

        productsButton.addActionListener(
                e -> showProducts()
        );

        sidebar.add(productsButton);

        sidebar.add(
                Box.createVerticalStrut(7)
        );

        // =====================================================
        // INVENTORY
        // =====================================================

        JButton inventoryButton =
                createNavButton(
                        "▣",
                        "Inventory"
                );

        inventoryButton.addActionListener(
                e -> {

                    pageContainer.removeAll();

                    pageContainer.add(
                            new InventoryPanel(),
                            BorderLayout.CENTER
                    );

                    pageContainer.revalidate();
                    pageContainer.repaint();
                }
        );

        sidebar.add(inventoryButton);

        sidebar.add(
                Box.createVerticalStrut(7)
        );

        // =====================================================
        // CUSTOMERS
        // =====================================================

        JButton customersButton =
                createNavButton(
                        "♙",
                        "Customers"
                );

        customersButton.addActionListener(
                e -> {

                    pageContainer.removeAll();

                    pageContainer.add(
                            new CustomersPanel(),
                            BorderLayout.CENTER
                    );

                    pageContainer.revalidate();
                    pageContainer.repaint();
                }
        );

        sidebar.add(customersButton);

        sidebar.add(
                Box.createVerticalStrut(7)
        );

        // =====================================================
        // SALES
        // =====================================================

        JButton salesButton =
                createNavButton(
                        "◆",
                        "Sales"
                );

        salesButton.addActionListener(
                e -> {

                    pageContainer.removeAll();

                    pageContainer.add(
                            new SalesPanel(),
                            BorderLayout.CENTER
                    );

                    pageContainer.revalidate();
                    pageContainer.repaint();
                }
        );

        sidebar.add(salesButton);

        sidebar.add(
                Box.createVerticalStrut(7)
        );
// =====================================================
// USERS - ADMIN / MANAGER ACCESS ONLY
// =====================================================

String currentRole =
        UserSession.getRole();


if (
        currentRole != null
        &&
        (
            currentRole.equalsIgnoreCase("Admin")
            ||
        currentRole.equalsIgnoreCase("Administrator")
            ||
            currentRole.equalsIgnoreCase("Manager")
        )
) {

    JButton usersButton =
            createNavButton(
                    "♚",
                    "Users"
            );


    usersButton.addActionListener(
            e -> {

                pageContainer.removeAll();


                pageContainer.add(
                        new UsersPanel(),
                        BorderLayout.CENTER
                );


                pageContainer.revalidate();
                pageContainer.repaint();
            }
    );


    sidebar.add(
            usersButton
    );


    sidebar.add(
            Box.createVerticalStrut(7)
    );
}
       // =====================================================
// REPORTS - ADMIN / MANAGER ACCESS ONLY
// =====================================================

boolean canViewReports =
        UserSession.getRole() != null
        &&
        (
            UserSession.getRole()
                    .trim()
                    .equalsIgnoreCase("Admin")

            ||

            UserSession.getRole()
                    .trim()
                    .equalsIgnoreCase("Administrator")

            ||

            UserSession.getRole()
                    .trim()
                    .equalsIgnoreCase("Manager")
        );


if (canViewReports) {

    JButton reportsButton =
            createNavButton(
                    "▤",
                    "Reports"
            );


    reportsButton.addActionListener(
            e -> {

                pageContainer.removeAll();

                pageContainer.add(
                        new ReportsPanel(),
                        BorderLayout.CENTER
                );

                pageContainer.revalidate();
                pageContainer.repaint();
            }
    );


    sidebar.add(
            reportsButton
    );


    sidebar.add(
            Box.createVerticalStrut(7)
    );
}

        // =====================================================
        // SETTINGS
        // =====================================================

        JButton settingsButton =
                createNavButton(
                        "⚙",
                        "Settings"
                );

        settingsButton.addActionListener(
                e -> {

                    pageContainer.removeAll();

                    pageContainer.add(
                            new SettingsPanel(),
                            BorderLayout.CENTER
                    );

                    pageContainer.revalidate();
                    pageContainer.repaint();
                }
        );

        sidebar.add(settingsButton);

        sidebar.add(
                Box.createVerticalStrut(7)
        );

        // =====================================================
        // SIGN OUT
        // =====================================================

        JButton signOutButton =
                createNavButton(
                        "↪",
                        "Sign Out"
                );

        signOutButton.addActionListener(
                e -> {

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to sign out of AURORA?",
                                    "AURORA | Sign Out",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );

if (
        choice
        == JOptionPane.YES_OPTION
) {

    // Clear the currently logged-in user
    UserSession.clearSession();

    // Close Dashboard
    dispose();

    // Open Login again
    new LoginFormNew()
            .setVisible(true);
}                }
        );

        sidebar.add(signOutButton);

        sidebar.add(
                Box.createVerticalGlue()
        );

        // =====================================================
        // BOTTOM BRAND MESSAGE
        // =====================================================

        JPanel bottomBrand =
                new JPanel();

        bottomBrand.setOpaque(false);

        bottomBrand.setLayout(
                new BoxLayout(
                        bottomBrand,
                        BoxLayout.Y_AXIS
                )
        );

        bottomBrand.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        bottomBrand.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        90
                )
        );

        JPanel bottomGoldLine =
                new JPanel();

        bottomGoldLine.setBackground(GOLD);

        bottomGoldLine.setMaximumSize(
                new Dimension(
                        55,
                        1
                )
        );

        bottomGoldLine.setPreferredSize(
                new Dimension(
                        55,
                        1
                )
        );

        bottomGoldLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        bottomBrand.add(bottomGoldLine);

        bottomBrand.add(
                Box.createVerticalStrut(8)
        );

        JLabel moreThan =
                new JLabel(
                        "MORE THAN CLOTHING"
                );

        moreThan.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        moreThan.setForeground(GOLD);

        moreThan.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        bottomBrand.add(moreThan);

        bottomBrand.add(
                Box.createVerticalStrut(3)
        );

        JLabel lifestyle =
                new JLabel(
                        "A — LIFESTYLE"
                );

        lifestyle.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        11
                )
        );

        lifestyle.setForeground(
                new Color(
                        245,
                        241,
                        225
                )
        );

        lifestyle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        bottomBrand.add(lifestyle);

        sidebar.add(bottomBrand);

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        return sidebar;
    }

    // =========================================================
    // NAVIGATION BUTTON
    // =========================================================

    private JButton createNavButton(
            String icon,
            String text
    ) {

        JButton button =
                new JButton(
                        "   "
                        + icon
                        + "     "
                        + text
                );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        44
                )
        );

        button.setPreferredSize(
                new Dimension(
                        190,
                        44
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        /*
         * IMPORTANT:
         * transparent normally so the sidebar image is visible.
         */

        button.setContentAreaFilled(false);

        button.setOpaque(false);

        button.setForeground(
                new Color(
                        248,
                        245,
                        232
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setContentAreaFilled(true);

                        button.setOpaque(true);

                        button.setBackground(
                                new Color(
                                        91,
                                        116,
                                        101,
                                        220
                                )
                        );

                        button.setForeground(
                                Color.WHITE
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setContentAreaFilled(false);

                        button.setOpaque(false);

                        button.setForeground(
                                new Color(
                                        248,
                                        245,
                                        232
                                )
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // DASHBOARD PAGE
    // =========================================================

    private JPanel createDashboardPage() {

        JPanel main =
                new JPanel(
                        new BorderLayout()
                );

        main.setBackground(CREAM);

        main.setBorder(
                new EmptyBorder(
                        25,
                        32,
                        25,
                        32
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JPanel heading =
                new JPanel();

        heading.setOpaque(false);

        heading.setLayout(
                new BoxLayout(
                        heading,
                        BoxLayout.Y_AXIS
                )
        );

        String fullName =
        UserSession.getFullName();

String firstName =
        fullName != null && !fullName.trim().isEmpty()
                ? fullName.trim().split("\\s+")[0]
                : "User";


JLabel greeting =
        new JLabel(
              getGreeting() + " , " + firstName
        );

        greeting.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        30
                )
        );

        greeting.setForeground(DEEP_GREEN);

        JLabel subtitle =
                new JLabel(
                        "Welcome back to your AURORA workspace."
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(MUTED);

        heading.add(greeting);

        heading.add(
                Box.createVerticalStrut(4)
        );

        heading.add(subtitle);

        header.add(
                heading,
                BorderLayout.WEST
        );

        // =====================================================
        // ADMIN PROFILE
        // =====================================================

        JPanel profile =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                8
                        )
                );

        profile.setOpaque(false);

        JLabel notification =
                new JLabel("●");

        notification.setForeground(GOLD);

        JLabel admin =
                new JLabel(
                        "Admin  ▾"
                );

        admin.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        admin.setForeground(DEEP_GREEN);

        profile.add(notification);
        profile.add(admin);

        header.add(
                profile,
                BorderLayout.EAST
        );

        main.add(
                header,
                BorderLayout.NORTH
        );

        // =====================================================
        // DASHBOARD CONTENT
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
                        25,
                        0,
                        0,
                        0
                )
        );

        // =====================================================
        // HERO
        // =====================================================

        JPanel hero =
                createHeroCard();

        hero.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        hero.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        145
                )
        );

        content.add(hero);

        content.add(
                Box.createVerticalStrut(20)
        );

        // =====================================================
        // BUSINESS OVERVIEW
        // =====================================================

        JLabel overview =
                new JLabel(
                        "Business Overview"
                );

        overview.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        overview.setForeground(TEXT);

        overview.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        content.add(overview);

        content.add(
                Box.createVerticalStrut(12)
        );

        // =====================================================
        // STAT CARDS
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

        stats.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        stats.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        145
                )
        );
        // =====================================================
// LIVE DASHBOARD DATA
// =====================================================

int totalProducts =
        DashboardDAO.getTotalProducts();

int totalInventory =
        DashboardDAO.getTotalInventoryQuantity();

int totalCustomers =
        DashboardDAO.getTotalCustomers();

java.math.BigDecimal totalRevenue =
        DashboardDAO.getTotalSalesRevenue();

        stats.add(
                createStatCard(
                        "◇",
                        "PRODUCTS",
                       String.valueOf(totalProducts),
                        "12% this month"
                )
        );

        stats.add(
                createStatCard(
                        "▣",
                        "INVENTORY",
                       String.format("%,d", totalInventory),
                        "Healthy stock"
                )
        );

        stats.add(
                createStatCard(
                        "♙",
                        "CUSTOMERS",
                       String.valueOf(totalCustomers),
                        "8% this month"
                )
        );

        stats.add(
                createStatCard(
                        "↗",
                        "SALES",
                        "LKR " + String.format("%,.0f", totalRevenue),
                        "18% this month"
                )
        );

        content.add(stats);

        content.add(
                Box.createVerticalStrut(20)
        );

        // =====================================================
        // LOWER CARDS
        // =====================================================

        JPanel lower =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                18,
                                0
                        )
                );

        lower.setOpaque(false);

        lower.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        lower.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        205
                )
        );

        lower.add(
                createRecentActivityCard()
        );

        lower.add(
                createInventoryCard()
        );

        content.add(lower);

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
        // Hide the ugly scrollbar but keep smooth mouse-wheel scrolling
scroll.getVerticalScrollBar()
        .setPreferredSize(
                new Dimension(0, 0)
        );
        

        main.add(
                scroll,
                BorderLayout.CENTER
        );

        return main;
    }

    // =========================================================
    // DASHBOARD HERO CARD
    // =========================================================

    private JPanel createHeroCard() {

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
                                new java.awt.geom.RoundRectangle2D.Double(
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

                        // =========================================
                        // HERO IMAGE
                        // =========================================

                        if (heroImage != null) {

                            int imageWidth =
                                    heroImage.getWidth(null);

                            int imageHeight =
                                    heroImage.getHeight(null);

                            if (
                                    imageWidth > 0
                                    && imageHeight > 0
                            ) {

                                double scale =
                                        Math.max(
                                                (double) w / imageWidth,
                                                (double) h / imageHeight
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
                                        heroImage,
                                        x,
                                        y,
                                        scaledWidth,
                                        scaledHeight,
                                        null
                                );
                            }
                        }

                        // =========================================
                        // LEFT GREEN BLEND
                        // =========================================

                        GradientPaint blend =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                42,
                                                55,
                                                39,
                                                248
                                        ),

                                        (int) (
                                                w * 0.64
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

                        // =========================================
                        // LOWER SHADE
                        // =========================================

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
                                                60
                                        )
                                );

                        g2.setPaint(lowerShade);

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );

                        // =========================================
                        // GOLD BORDER
                        // =========================================

                        g2.setClip(null);

                        g2.setColor(
                                new Color(
                                        201,
                                        164,
                                        76,
                                        155
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
                        20,
                        26,
                        20,
                        26
                )
        );

        hero.setPreferredSize(
                new Dimension(
                        900,
                        145
                )
        );

        // =====================================================
        // HERO WORDS
        // =====================================================

        JPanel words =
                new JPanel();

        words.setOpaque(false);

        words.setLayout(
                new BoxLayout(
                        words,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel small =
                new JLabel(
                        "AURORA BUSINESS OVERVIEW"
                );

        small.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        small.setForeground(GOLD);

        small.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel title =
                new JLabel(
                        "More than clothing."
                );

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        25
                )
        );

        title.setForeground(Color.WHITE);

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel description =
                new JLabel(
                        "Smarter management for a growing fashion business."
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

        words.add(
                Box.createVerticalGlue()
        );

        words.add(small);

        words.add(
                Box.createVerticalStrut(5)
        );

        words.add(title);

        words.add(
                Box.createVerticalStrut(5)
        );

        words.add(description);

        words.add(
                Box.createVerticalStrut(8)
        );

        words.add(accent);

        words.add(
                Box.createVerticalGlue()
        );

        hero.add(
                words,
                BorderLayout.WEST
        );

        return hero;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String icon,
            String title,
            String number,
            String footer
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
                        22
                )
        );

        iconLabel.setForeground(GOLD);

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        titleLabel.setForeground(MUTED);

        JLabel value =
                new JLabel(number);

        value.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        24
                )
        );

        value.setForeground(DEEP_GREEN);

        JLabel footerLabel =
                new JLabel(
                        "↗  "
                        + footer
                );

        footerLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        footerLabel.setForeground(
                SOFT_GREEN
        );

        inside.add(iconLabel);

        inside.add(
                Box.createVerticalStrut(6)
        );

        inside.add(titleLabel);

        inside.add(
                Box.createVerticalStrut(4)
        );

        inside.add(value);

        inside.add(
                Box.createVerticalStrut(5)
        );

        inside.add(footerLabel);

        card.add(
                inside,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // RECENT ACTIVITY
    // =========================================================

    private JPanel createRecentActivityCard() {

        LuxuryCard card =
                new LuxuryCard();

        card.setLayout(
                new BorderLayout()
        );

        JLabel title =
                new JLabel(
                        "Recent Activity"
                );

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        17
                )
        );

        title.setForeground(DEEP_GREEN);

        card.add(
                title,
                BorderLayout.NORTH
        );

        JPanel list =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                8
                        )
                );

        list.setOpaque(false);

        list.setBorder(
                new EmptyBorder(
                        14,
                        0,
                        0,
                        0
                )
        );
// =====================================================
// LIVE RECENT SALES ACTIVITY
// =====================================================

java.util.List<Object[]> recentSales =
        DashboardDAO.getRecentSales();


if (recentSales.isEmpty()) {

    list.add(
            createActivityRow(
                    "—",
                    "No sales recorded yet",
                    "Waiting"
            )
    );

} else {

    for (Object[] sale : recentSales) {

        String orderId =
                String.valueOf(
                        sale[0]
                );

        String product =
                String.valueOf(
                        sale[1]
                );

        String status =
                String.valueOf(
                        sale[2]
                );


        list.add(
                createActivityRow(
                        orderId,
                        product,
                        status
                )
        );
    }
}

        card.add(
                list,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // ACTIVITY ROW
    // =========================================================

    private JPanel createActivityRow(
            String id,
            String product,
            String status
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setOpaque(false);

        JLabel left =
                new JLabel(
                        id
                        + "     "
                        + product
                );

        left.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        left.setForeground(TEXT);

        JLabel right =
                new JLabel(
                        "●  "
                        + status
                );

        right.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        right.setForeground(
                SOFT_GREEN
        );

        row.add(
                left,
                BorderLayout.WEST
        );

        row.add(
                right,
                BorderLayout.EAST
        );

        return row;
    }

    // =========================================================
    // INVENTORY OVERVIEW
    // =========================================================

    private JPanel createInventoryCard() {

        LuxuryCard card =
                new LuxuryCard();

        card.setLayout(
                new BorderLayout()
        );

        JLabel title =
                new JLabel(
                        "Inventory Overview"
                );

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        17
                )
        );

        title.setForeground(DEEP_GREEN);

        card.add(
                title,
                BorderLayout.NORTH
        );

        JPanel rows =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                10
                        )
                );

        rows.setOpaque(false);

        rows.setBorder(
                new EmptyBorder(
                        15,
                        0,
                        0,
                        0
                )
        );

      // =====================================================
// LIVE INVENTORY OVERVIEW
// =====================================================

int inStock =
        DashboardDAO.getInStockCount();

int lowStock =
        DashboardDAO.getLowStockCount();

int outOfStock =
        DashboardDAO.getOutOfStockCount();


rows.add(
        createInventoryRow(
                "In Stock",
                String.valueOf(inStock)
        )
);

rows.add(
        createInventoryRow(
                "Low Stock",
                String.valueOf(lowStock)
        )
);

rows.add(
        createInventoryRow(
                "Out of Stock",
                String.valueOf(outOfStock)
        )
);

        card.add(
                rows,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // INVENTORY ROW
    // =========================================================

    private JPanel createInventoryRow(
            String name,
            String amount
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setOpaque(false);

        JLabel nameLabel =
                new JLabel(name);

        nameLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        nameLabel.setForeground(MUTED);

        JLabel amountLabel =
                new JLabel(amount);

        amountLabel.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        17
                )
        );

        amountLabel.setForeground(
                DEEP_GREEN
        );

        row.add(
                nameLabel,
                BorderLayout.WEST
        );

        row.add(
                amountLabel,
                BorderLayout.EAST
        );

        return row;
    }

    // =========================================================
    // DYNAMIC GREETING
    // =========================================================

    private String getGreeting() {

        int hour =
                LocalTime.now()
                        .getHour();

        if (
                hour >= 5
                && hour < 12
        ) {

            return "Good Morning";

        } else if (
                hour >= 12
                && hour < 17
        ) {

            return "Good Afternoon";

        } else if (
                hour >= 17
                && hour < 21
        ) {

            return "Good Evening";

        } else {

            return "Good Night";
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

   public static void main(
        String[] args
) {

    // =========================================================
    // SECURITY CHECK
    // =========================================================

    if (
            UserSession.getUsername() == null
            || UserSession.getUsername().trim().isEmpty()
    ) {

        // No logged-in user:
        // Start AURORA from the Login screen instead.
        LoginFormNew.main(args);

        return;
    }


    // =========================================================
    // OPEN DASHBOARD
    // =========================================================

    SwingUtilities.invokeLater(
            () -> {

                Dashboard dashboard =
                        new Dashboard();

                dashboard.setVisible(true);
            }
    );
}
}