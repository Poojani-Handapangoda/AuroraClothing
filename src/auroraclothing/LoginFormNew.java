/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package auroraclothing;

import java.util.prefs.Preferences;
/**
 *
 * @author IMANSA
 */
public class LoginFormNew extends javax.swing.JFrame {
    private final Preferences prefs =
        Preferences.userNodeForPackage(LoginFormNew.class);
    // =========================================================
// AURORA DASHBOARD COLOR PALETTE
// =========================================================

private final java.awt.Color DEEP_GREEN =
        new java.awt.Color(60, 74, 52);

private final java.awt.Color DARK_GREEN =
        new java.awt.Color(42, 55, 39);

private final java.awt.Color SOFT_GREEN =
        new java.awt.Color(91, 116, 101);

private final java.awt.Color GOLD =
        new java.awt.Color(201, 164, 76);

private final java.awt.Color LIGHT_GOLD =
        new java.awt.Color(226, 205, 148);

private final java.awt.Color CREAM =
        new java.awt.Color(238, 235, 224);

private final java.awt.Color IVORY =
        new java.awt.Color(250, 248, 242);

private final java.awt.Color TEXT =
        new java.awt.Color(55, 52, 46);

private final java.awt.Color MUTED =
        new java.awt.Color(125, 122, 113);
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginFormNew.class.getName());

    /**
     * Creates new form LoginFormNew
     */
    public LoginFormNew() {
        initComponents();
        styleLoginUI();
         loadBottomWave();
        loadFashionImage();
        loadAuroraLogo();
         setSize(1200,720);
    setLocationRelativeTo(null);
    
    // Load remembered username
    loadRememberedUser();
    
    }
     private void loadBottomWave() {

    java.net.URL imageURL = getClass().getResource(
            "/auroraclothing/images/gold-wave-bottom.png"
    );

    if (imageURL == null) {
        System.out.println("Gold bottom wave not found!");
        return;
    }

    javax.swing.ImageIcon originalIcon =
            new javax.swing.ImageIcon(imageURL);

    int targetWidth = 220;

    int targetHeight = (int) (
            (double) originalIcon.getIconHeight()
            / originalIcon.getIconWidth()
            * targetWidth
    );

    java.awt.Image scaledImage =
            originalIcon.getImage().getScaledInstance(
                    targetWidth,
                    targetHeight,
                    java.awt.Image.SCALE_SMOOTH
            );

    lblBottomWave.setIcon(
            new javax.swing.ImageIcon(scaledImage)
    );

    lblBottomWave.setText("");
}

    private void loadAuroraLogo() {

    java.net.URL logoURL = getClass().getResource(
            "/auroraclothing/images/aurora-logo.png"
    );

    if (logoURL == null) {
        System.out.println("AURORA logo not found!");
        return;
    }

    javax.swing.ImageIcon original =
            new javax.swing.ImageIcon(logoURL);

    java.awt.image.BufferedImage resized =
            new java.awt.image.BufferedImage(
                    85,
                    85,
                    java.awt.image.BufferedImage.TYPE_INT_ARGB
            );

    java.awt.Graphics2D g2 = resized.createGraphics();

    g2.setRenderingHint(
            java.awt.RenderingHints.KEY_INTERPOLATION,
            java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC
    );

    g2.setRenderingHint(
            java.awt.RenderingHints.KEY_RENDERING,
            java.awt.RenderingHints.VALUE_RENDER_QUALITY
    );

    g2.setRenderingHint(
            java.awt.RenderingHints.KEY_ANTIALIASING,
            java.awt.RenderingHints.VALUE_ANTIALIAS_ON
    );

    g2.drawImage(
            original.getImage(),
            0,
            0,
            85,
            85,
            null
    );

    g2.dispose();

    lblAuroraLogo.setIcon(
            new javax.swing.ImageIcon(resized)
    );

    lblAuroraLogo.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

}
    private void styleLoginUI() {

    // ============================================
    // PRODUCT ICON
    // ============================================

    lblProductIcon.setText("◇");

    lblProductIcon.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    lblProductIcon.setVerticalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    lblProductIcon.setFont(
            new java.awt.Font(
                    "Serif",
                    java.awt.Font.BOLD,
                    28
            )
    );

    // Antique Gold
    lblProductIcon.setForeground(
            new java.awt.Color(201, 164, 76)
    );

    // Luxury soft green
    lblProductIcon.setBackground(
            new java.awt.Color(91, 116, 101)
    );

    lblProductIcon.setOpaque(true);

    // Thin gold border
    lblProductIcon.setBorder(
            javax.swing.BorderFactory.createLineBorder(
                    new java.awt.Color(201, 164, 76),
                    1
            )
    );
    // ============================================
// INVENTORY ICON
// ============================================

lblInventoryIcon.setText("▣");

lblInventoryIcon.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
);

lblInventoryIcon.setVerticalAlignment(
        javax.swing.SwingConstants.CENTER
);

lblInventoryIcon.setFont(
        new java.awt.Font("SansSerif", java.awt.Font.BOLD, 25)
);

lblInventoryIcon.setForeground(
        new java.awt.Color(201, 164, 76)
);

lblInventoryIcon.setBackground(
        new java.awt.Color(91, 116, 101)
);

lblInventoryIcon.setOpaque(true);

lblInventoryIcon.setBorder(
        javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(201, 164, 76), 1
        )
);


// ============================================
// CUSTOMER ICON
// ============================================

lblCustomerIcon.setText("♙");

lblCustomerIcon.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
);

lblCustomerIcon.setVerticalAlignment(
        javax.swing.SwingConstants.CENTER
);

lblCustomerIcon.setFont(
        new java.awt.Font("Serif", java.awt.Font.BOLD, 27)
);

lblCustomerIcon.setForeground(
        new java.awt.Color(201, 164, 76)
);

lblCustomerIcon.setBackground(
        new java.awt.Color(91, 116, 101)
);

lblCustomerIcon.setOpaque(true);

lblCustomerIcon.setBorder(
        javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(201, 164, 76), 1
        )
);


// ============================================
// GROWTH ICON
// ============================================

lblGrowthIcon.setText("↗");

lblGrowthIcon.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
);

lblGrowthIcon.setVerticalAlignment(
        javax.swing.SwingConstants.CENTER
);

lblGrowthIcon.setFont(
        new java.awt.Font("SansSerif", java.awt.Font.BOLD, 27)
);

lblGrowthIcon.setForeground(
        new java.awt.Color(201, 164, 76)
);

lblGrowthIcon.setBackground(
        new java.awt.Color(91, 116, 101)
);

lblGrowthIcon.setOpaque(true);

lblGrowthIcon.setBorder(
        javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(201, 164, 76), 1
        )
);
}
    
    private void loadFashionImage() {

    java.net.URL url = getClass().getResource(
            "/auroraclothing/images/aurora-boutique.png"
    );

    if (url != null) {

        javax.swing.ImageIcon original =
                new javax.swing.ImageIcon(url);

        java.awt.Image scaled =
                original.getImage().getScaledInstance(
                        200,
                        550,
                        java.awt.Image.SCALE_SMOOTH
                );

       
    }
   
}
    
    private void loadRememberedUser() {

    boolean remember =
            prefs.getBoolean("rememberMe", false);

    if (remember) {

        String savedUsername =
                prefs.get("username", "");

        txtUsername.setText(savedUsername);
        chkRememberMe.setSelected(true);

    } else {

        chkRememberMe.setSelected(false);
    }
}


private void saveRememberedUser(String username) {

    if (chkRememberMe.isSelected()) {

        // Remember username only.
        // Password is intentionally NOT stored.
        prefs.put("username", username);
        prefs.putBoolean("rememberMe", true);

    } else {

        prefs.remove("username");
        prefs.putBoolean("rememberMe", false);
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        pnlBranding = new javax.swing.JPanel();
        fashionImagePanel1 = new auroraclothing.FashionImagePanel();
        lblAuroraLogo = new javax.swing.JLabel();
        lblAurora = new javax.swing.JLabel();
        lblClothing = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        lblProductTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblInventoryTitle = new javax.swing.JLabel();
        lblProductTitle2 = new javax.swing.JLabel();
        lblProductTitle3 = new javax.swing.JLabel();
        lblProductIcon = new javax.swing.JLabel();
        lblInventoryIcon = new javax.swing.JLabel();
        lblCustomerIcon = new javax.swing.JLabel();
        lblGrowthIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblBottomWave = new javax.swing.JLabel();
        roundedCardPanel1 = new auroraclothing.RoundedCardPanel();
        lblWelcomeBack = new javax.swing.JLabel();
        lblSignInTitle = new javax.swing.JLabel();
        lblSignInSubtitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new auroraclothing.RoundedPasswordField();
        txtUsername = new auroraclothing.RoundedTextField();
        roundedButton1 = new auroraclothing.RoundedButton();
        chkRememberMe = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AURORA | Sign In");
        setMinimumSize(new java.awt.Dimension(900, 500));
        setPreferredSize(new java.awt.Dimension(900, 550));
        setResizable(false);

        pnlBackground.setBackground(new java.awt.Color(238, 235, 224));

        pnlBranding.setBackground(new java.awt.Color(60, 74, 52));
        pnlBranding.setPreferredSize(new java.awt.Dimension(490, 650));

        javax.swing.GroupLayout fashionImagePanel1Layout = new javax.swing.GroupLayout(fashionImagePanel1);
        fashionImagePanel1.setLayout(fashionImagePanel1Layout);
        fashionImagePanel1Layout.setHorizontalGroup(
            fashionImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        fashionImagePanel1Layout.setVerticalGroup(
            fashionImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblAuroraLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAuroraLogo.setText("A");
        lblAuroraLogo.setPreferredSize(new java.awt.Dimension(80, 80));

        lblAurora.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        lblAurora.setForeground(new java.awt.Color(201, 164, 76));
        lblAurora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAurora.setText("A U R O R A");

        lblClothing.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblClothing.setForeground(new java.awt.Color(235, 229, 213));
        lblClothing.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClothing.setText("C L O T H I N G");

        lblTagline.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        lblTagline.setForeground(new java.awt.Color(238, 232, 216));
        lblTagline.setText("<html>Style meets<br>smarter management.</html>");

        lblProductTitle.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblProductTitle.setForeground(new java.awt.Color(245, 241, 230));
        lblProductTitle.setText("Manage Products");

        jSeparator1.setBackground(new java.awt.Color(201, 164, 76));
        jSeparator1.setForeground(new java.awt.Color(201, 164, 76));
        jSeparator1.setPreferredSize(new java.awt.Dimension(65, 2));

        jSeparator2.setBackground(new java.awt.Color(201, 164, 76));
        jSeparator2.setForeground(new java.awt.Color(201, 164, 76));
        jSeparator2.setPreferredSize(new java.awt.Dimension(65, 2));

        lblInventoryTitle.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblInventoryTitle.setForeground(new java.awt.Color(245, 241, 230));
        lblInventoryTitle.setText("Track Inventory");

        lblProductTitle2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblProductTitle2.setForeground(new java.awt.Color(245, 241, 230));
        lblProductTitle2.setText("Manage Customers");

        lblProductTitle3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblProductTitle3.setForeground(new java.awt.Color(245, 241, 230));
        lblProductTitle3.setText("Grow Your Business");

        lblProductIcon.setText(".");
        lblProductIcon.setPreferredSize(new java.awt.Dimension(37, 37));

        lblInventoryIcon.setText(".");
        lblInventoryIcon.setPreferredSize(new java.awt.Dimension(37, 37));

        lblCustomerIcon.setText(".");
        lblCustomerIcon.setPreferredSize(new java.awt.Dimension(37, 37));

        lblGrowthIcon.setText(".");
        lblGrowthIcon.setPreferredSize(new java.awt.Dimension(37, 37));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 253, 249));
        jLabel1.setText("Organize your collection");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 253, 249));
        jLabel2.setText("Stay always in control");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 253, 249));
        jLabel3.setText("Build stronger relationships");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 253, 249));
        jLabel4.setText("Make smarter decisions");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(225, 230, 220));
        jLabel5.setText("MORE THAN CLOTHING");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 9)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(201, 164, 76));
        jLabel6.setText("A  —  L I F E S T Y L E");

        lblBottomWave.setForeground(new java.awt.Color(255, 255, 255));
        lblBottomWave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBottomWave.setText(".");
        lblBottomWave.setPreferredSize(new java.awt.Dimension(220, 55));

        javax.swing.GroupLayout pnlBrandingLayout = new javax.swing.GroupLayout(pnlBranding);
        pnlBranding.setLayout(pnlBrandingLayout);
        pnlBrandingLayout.setHorizontalGroup(
            pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBrandingLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBrandingLayout.createSequentialGroup()
                        .addComponent(lblClothing)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBrandingLayout.createSequentialGroup()
                        .addComponent(lblAuroraLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBrandingLayout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lblBottomWave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBrandingLayout.createSequentialGroup()
                        .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAurora)
                            .addComponent(lblTagline, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBrandingLayout.createSequentialGroup()
                                .addComponent(lblProductIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProductTitle)
                                    .addComponent(jLabel1)))
                            .addGroup(pnlBrandingLayout.createSequentialGroup()
                                .addComponent(lblInventoryIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblInventoryTitle)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlBrandingLayout.createSequentialGroup()
                                .addComponent(lblCustomerIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProductTitle2)
                                    .addComponent(jLabel3)))
                            .addGroup(pnlBrandingLayout.createSequentialGroup()
                                .addComponent(lblGrowthIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProductTitle3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(12, 12, 12)))
                .addComponent(fashionImagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlBrandingLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblCustomerIcon, lblGrowthIcon, lblInventoryIcon, lblProductIcon});

        pnlBrandingLayout.setVerticalGroup(
            pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBrandingLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblAuroraLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAurora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClothing)
                .addGap(35, 35, 35)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTagline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblProductIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBrandingLayout.createSequentialGroup()
                        .addComponent(lblProductTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(26, 26, 26)
                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInventoryIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBrandingLayout.createSequentialGroup()
                        .addComponent(lblInventoryTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addGap(21, 21, 21)
                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCustomerIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBrandingLayout.createSequentialGroup()
                        .addComponent(lblProductTitle2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addGap(26, 26, 26)
                .addGroup(pnlBrandingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGrowthIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBrandingLayout.createSequentialGroup()
                        .addComponent(lblProductTitle3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addGap(29, 29, 29)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(lblBottomWave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(fashionImagePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlBrandingLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblCustomerIcon, lblGrowthIcon, lblInventoryIcon, lblProductIcon});

        roundedCardPanel1.setBackground(new java.awt.Color(242, 234, 225));

        lblWelcomeBack.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblWelcomeBack.setForeground(new java.awt.Color(113, 108, 101));
        lblWelcomeBack.setText("W E L C O M E   B A C K");

        lblSignInTitle.setFont(new java.awt.Font("Serif", 0, 38)); // NOI18N
        lblSignInTitle.setForeground(new java.awt.Color(49, 43, 39));
        lblSignInTitle.setText("Sign In");

        lblSignInSubtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSignInSubtitle.setForeground(new java.awt.Color(113, 108, 101));
        lblSignInSubtitle.setText("<html>Access your Aurora account to manage<br>your store, inventory and more.</html>");

        lblUsername.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(49, 43, 39));
        lblUsername.setText("Username");

        lblPassword.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(49, 43, 39));
        lblPassword.setText("Password");

        roundedButton1.addActionListener(this::roundedButton1ActionPerformed);

        chkRememberMe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        chkRememberMe.setForeground(new java.awt.Color(113, 108, 101));
        chkRememberMe.setText("Remember me");
        chkRememberMe.addActionListener(this::chkRememberMeActionPerformed);

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(190, 151, 99));
        jLabel21.setText("Forgot password?");

        jLabel22.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(113, 108, 101));
        jLabel22.setText("🔒  Secure staff access");

        javax.swing.GroupLayout roundedCardPanel1Layout = new javax.swing.GroupLayout(roundedCardPanel1);
        roundedCardPanel1.setLayout(roundedCardPanel1Layout);
        roundedCardPanel1Layout.setHorizontalGroup(
            roundedCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedCardPanel1Layout.createSequentialGroup()
                .addGroup(roundedCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedCardPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(roundedCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblWelcomeBack)
                            .addComponent(lblSignInTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSignInSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundedCardPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(roundedCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPassword)
                            .addComponent(lblUsername)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addGroup(roundedCardPanel1Layout.createSequentialGroup()
                                .addComponent(chkRememberMe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21))
                            .addComponent(roundedButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedCardPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        roundedCardPanel1Layout.setVerticalGroup(
            roundedCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedCardPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblWelcomeBack)
                .addGap(18, 18, 18)
                .addComponent(lblSignInTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSignInSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundedCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkRememberMe)
                    .addComponent(jLabel21))
                .addGap(27, 27, 27)
                .addComponent(roundedButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(pnlBranding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(roundedCardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBranding, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE))
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(roundedCardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkRememberMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRememberMeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRememberMeActionPerformed

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
        // TODO add your handling code here:
        // =========================================================
    // GET LOGIN DETAILS
    // =========================================================

    String username =
            txtUsername
                    .getText()
                    .trim();

    String password =
            new String(
                    txtPassword.getPasswordText()
            );


    // =========================================================
    // EMPTY FIELD VALIDATION
    // =========================================================

    if (
            username.isEmpty()
            || password.isEmpty()
    ) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Please enter your username and password.",
                "AURORA | Sign In",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        return;
    }


    // =========================================================
    // CHECK DATABASE
    // =========================================================

    Object[] user =
            UserDAO.authenticateUser(
                    username,
                    password
            );


    // =========================================================
    // INVALID USERNAME OR PASSWORD
    // =========================================================

    if (user == null) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Invalid username or password.",
                "AURORA | Sign In Failed",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );

      txtPassword.clearPassword();

        return;
    }


    // =========================================================
    // GET USER INFORMATION
    // =========================================================

    String fullName =
            String.valueOf(
                    user[1]
            );

    String role =
            String.valueOf(
                    user[3]
            );

    String status =
            String.valueOf(
                    user[4]
            );
    int userId =
        ((Number) user[0]).intValue();

String loggedUsername =
        String.valueOf(user[2]);

UserSession.setUser(
        userId,
        fullName,
        loggedUsername,
        role
);


    // =========================================================
    // CHECK ACCOUNT STATUS
    // =========================================================

    if (
            !status.equalsIgnoreCase(
                    "Active"
            )
    ) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "This account is inactive.\n"
                + "Please contact the system administrator.",
                "AURORA | Access Denied",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        txtPassword.clearPassword();

        return;
    }


    // =========================================================
    // LOGIN SUCCESSFUL
    // =========================================================
    saveRememberedUser(username);

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Welcome back, "
            + fullName
            + "!",
            "AURORA | Login Successful",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
    );


    System.out.println(
            "AURORA LOGIN SUCCESSFUL"
    );

    System.out.println(
            "USER: "
            + fullName
    );

    System.out.println(
            "ROLE: "
            + role
    );


  // =========================================================
// RESTORE AURORA DASHBOARD LOOK & FEEL
// =========================================================

try {

    javax.swing.UIManager.setLookAndFeel(
            javax.swing.UIManager
                    .getCrossPlatformLookAndFeelClassName()
    );

} catch (Exception e) {

    System.out.println(
            "Could not restore dashboard look and feel: "
            + e.getMessage()
    );
}
// =========================================================
// AURORA CLEAN SCROLLBARS
// Hide scrollbar strip but keep mouse-wheel scrolling
// =========================================================

javax.swing.UIManager.put(
        "ScrollBar.width",
        0
);


// =========================================================
// OPEN DASHBOARD
// =========================================================

Dashboard dashboard =
        new Dashboard();

dashboard.setVisible(
        true
);


// =========================================================
// CLOSE LOGIN
// =========================================================

this.dispose();
    }//GEN-LAST:event_roundedButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LoginFormNew().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkRememberMe;
    private auroraclothing.FashionImagePanel fashionImagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAurora;
    private javax.swing.JLabel lblAuroraLogo;
    private javax.swing.JLabel lblBottomWave;
    private javax.swing.JLabel lblClothing;
    private javax.swing.JLabel lblCustomerIcon;
    private javax.swing.JLabel lblGrowthIcon;
    private javax.swing.JLabel lblInventoryIcon;
    private javax.swing.JLabel lblInventoryTitle;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblProductIcon;
    private javax.swing.JLabel lblProductTitle;
    private javax.swing.JLabel lblProductTitle2;
    private javax.swing.JLabel lblProductTitle3;
    private javax.swing.JLabel lblSignInSubtitle;
    private javax.swing.JLabel lblSignInTitle;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblWelcomeBack;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlBranding;
    private auroraclothing.RoundedButton roundedButton1;
    private auroraclothing.RoundedCardPanel roundedCardPanel1;
    private auroraclothing.RoundedPasswordField txtPassword;
    private auroraclothing.RoundedTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
