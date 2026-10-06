/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SettingsPanel extends JPanel {

    // =========================================================
    // AURORA LUXURY PALETTE
    // =========================================================
    private final Color CREAM = new Color(244, 241, 232);
    private final Color IVORY = new Color(252, 250, 244);

    private final Color DEEP_GREEN = new Color(58, 72, 50);
    private final Color DARK_GREEN = new Color(40, 53, 37);
    private final Color SOFT_GREEN = new Color(101, 116, 88);

    private final Color GOLD = new Color(201, 164, 76);
    private final Color PALE_GOLD = new Color(239, 229, 198);

    private final Color TEXT = new Color(48, 47, 42);
    private final Color MUTED = new Color(123, 119, 108);
    private final Color LINE = new Color(222, 217, 202);


    public SettingsPanel() {

        setLayout(new BorderLayout());
        setBackground(CREAM);

        createUI();
    }


    // =========================================================
    // MAIN UI
    // =========================================================
    private void createUI() {

        JPanel page =
                new JPanel(new GridBagLayout());

        page.setBackground(CREAM);

        page.setBorder(
                new EmptyBorder(
                        28,
                        36,
                        30,
                        36
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;


        // =====================================================
        // TITLE
        // =====================================================

        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        22,
                        0
                );

        page.add(
                createPageHeader(),
                gbc
        );


        // =====================================================
        // ACCOUNT HERO
        // =====================================================

        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        28,
                        0
                );

        page.add(
                createAccountHero(),
                gbc
        );


        // =====================================================
        // ACCOUNT
        // =====================================================

        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        8,
                        0
                );

        page.add(
                createSectionTitle(
                        "ACCOUNT",
                        "Personal information and access"
                ),
                gbc
        );


        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        27,
                        0
                );

        page.add(
                createAccountSection(),
                gbc
        );


        // =====================================================
        // SECURITY
        // =====================================================

        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        8,
                        0
                );

        page.add(
                createSectionTitle(
                        "SECURITY & PRIVACY",
                        "Keep your AURORA account protected"
                ),
                gbc
        );


        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        27,
                        0
                );

        page.add(
                createSecuritySection(),
                gbc
        );


        // =====================================================
        // PREFERENCES
        // =====================================================

        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        8,
                        0
                );

        page.add(
                createSectionTitle(
                        "PREFERENCES",
                        "Personalize your workspace"
                ),
                gbc
        );


        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        27,
                        0
                );

        page.add(
                createPreferencesSection(),
                gbc
        );


        // =====================================================
        // SUPPORT
        // =====================================================

        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        8,
                        0
                );

        page.add(
                createSectionTitle(
                        "SUPPORT & INFORMATION",
                        "Help, application details and support"
                ),
                gbc
        );


        gbc.gridy++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        25,
                        0
                );

        page.add(
                createSupportSection(),
                gbc
        );


        // =====================================================
        // FOOTER
        // =====================================================

        gbc.gridy++;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        10,
                        0
                );

        page.add(
                createFooter(),
                gbc
        );


        // PUSH EVERYTHING TO TOP
        gbc.gridy++;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel spacer =
                new JPanel();

        spacer.setOpaque(false);

        page.add(
                spacer,
                gbc
        );


        // =====================================================
        // SCROLL
        // =====================================================

        JScrollPane scroll =
                new JScrollPane(page);

        scroll.setBorder(null);
        scroll.setOpaque(false);

        scroll.getViewport()
                .setBackground(CREAM);

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(
                scroll,
                BorderLayout.CENTER
        );
    }


    // =========================================================
    // PAGE HEADER
    // =========================================================
    private JPanel createPageHeader() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);


        JPanel left =
                new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Settings"
                );

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        31
                )
        );

        title.setForeground(
                DEEP_GREEN
        );

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel subtitle =
                new JLabel(
                        "Manage your account, security and AURORA workspace preferences."
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        subtitle.setForeground(
                MUTED
        );

        subtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        left.add(title);

        left.add(
                Box.createVerticalStrut(4)
        );

        left.add(subtitle);


        panel.add(
                left,
                BorderLayout.WEST
        );


        JLabel status =
                new JLabel(
                        "  ●  AURORA SECURE  "
                );

        status.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        status.setForeground(
                DEEP_GREEN
        );

        status.setOpaque(true);

        status.setBackground(
                PALE_GOLD
        );

        status.setBorder(
                new EmptyBorder(
                        7,
                        10,
                        7,
                        10
                )
        );


        JPanel statusArea =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                5
                        )
                );

        statusArea.setOpaque(false);
        statusArea.add(status);

        panel.add(
                statusArea,
                BorderLayout.EAST
        );

        return panel;
    }


    // =========================================================
    // ACCOUNT HERO
    // =========================================================
    private JPanel createAccountHero() {

        ImageIcon bannerIcon;

        try {

            bannerIcon =
                    new ImageIcon(
                            getClass().getResource(
                                    "/auroraclothing/images/settings-banner.png"
                            )
                    );

        } catch (Exception e) {

            bannerIcon = null;

            System.out.println(
                    "Settings banner image not found."
            );
        }


        final Image bannerImage =
                bannerIcon != null
                        ? bannerIcon.getImage()
                        : null;


        JPanel hero =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                ) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );


                        int w = getWidth();
                        int h = getHeight();


                        Shape rounded =
                                new java.awt.geom.RoundRectangle2D.Double(
                                        0,
                                        0,
                                        w,
                                        h,
                                        28,
                                        28
                                );

                        g2.setClip(rounded);


                        g2.setColor(
                                DARK_GREEN
                        );

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
                                                (double) w / imageWidth,
                                                (double) h / imageHeight
                                        );

                                int scaledWidth =
                                        (int) (
                                                imageWidth * scale
                                        );

                                int scaledHeight =
                                        (int) (
                                                imageHeight * scale
                                        );

                                int x =
                                        (w - scaledWidth) / 2;

                                int y =
                                        (h - scaledHeight) / 2;


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


                        GradientPaint overlay =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                35,
                                                49,
                                                32,
                                                225
                                        ),
                                        (int) (w * 0.65),
                                        0,
                                        new Color(
                                                35,
                                                49,
                                                32,
                                                30
                                        )
                                );

                        g2.setPaint(overlay);

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );


                        g2.setColor(
                                new Color(
                                        201,
                                        164,
                                        76,
                                        130
                                )
                        );

                        g2.drawRoundRect(
                                0,
                                0,
                                w - 1,
                                h - 1,
                                28,
                                28
                        );

                        g2.dispose();
                    }
                };


        hero.setOpaque(false);

        hero.setBorder(
                new EmptyBorder(
                        20,
                        24,
                        20,
                        24
                )
        );

        hero.setPreferredSize(
                new Dimension(
                        900,
                        145
                )
        );


        // =====================================================
        // PROFILE ICON
        // =====================================================

        String fullName =
                UserSession.getFullName() == null
                        ? "AURORA User"
                        : UserSession.getFullName();


        String firstLetter =
                fullName.isBlank()
                        ? "A"
                        : fullName
                                .substring(0, 1)
                                .toUpperCase();


        JLabel profileIcon =
                new JLabel(
                        firstLetter,
                        SwingConstants.CENTER
                );

        profileIcon.setPreferredSize(
                new Dimension(
                        68,
                        68
                )
        );

        profileIcon.setMinimumSize(
                new Dimension(
                        68,
                        68
                )
        );

        profileIcon.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        28
                )
        );

        profileIcon.setForeground(
                DEEP_GREEN
        );

        profileIcon.setOpaque(true);

        profileIcon.setBackground(
                PALE_GOLD
        );

        profileIcon.setBorder(
                BorderFactory.createLineBorder(
                        GOLD,
                        2
                )
        );


        // =====================================================
        // REAL LOGGED-IN USER INFORMATION
        // =====================================================

        JPanel information =
                new JPanel();

        information.setOpaque(false);

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel overline =
                new JLabel(
                        "YOUR AURORA WORKSPACE"
                );

        overline.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        overline.setForeground(
                GOLD
        );


        JLabel user =
                new JLabel(
                        fullName
                );

        user.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        23
                )
        );

        user.setForeground(
                IVORY
        );


        String username =
                UserSession.getUsername() == null
                        ? "user"
                        : UserSession.getUsername();


        String role =
                UserSession.getRole() == null
                        ? "User"
                        : UserSession.getRole();


        JLabel details =
                new JLabel(
                        role
                        + "  •  @"
                        + username
                );

        details.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        details.setForeground(
                new Color(
                        225,
                        225,
                        216
                )
        );


        JLabel roleMessage =
                new JLabel(
                        "Your workspace adapts to your assigned access level."
                );

        roleMessage.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        roleMessage.setForeground(
                new Color(
                        195,
                        198,
                        184
                )
        );


        information.add(overline);

        information.add(
                Box.createVerticalStrut(4)
        );

        information.add(user);

        information.add(
                Box.createVerticalStrut(3)
        );

        information.add(details);

        information.add(
                Box.createVerticalStrut(6)
        );

        information.add(roleMessage);


        JPanel leftArea =
                new JPanel(
                        new BorderLayout(
                                18,
                                0
                        )
                );

        leftArea.setOpaque(false);

        leftArea.add(
                profileIcon,
                BorderLayout.WEST
        );

        leftArea.add(
                information,
                BorderLayout.CENTER
        );


        JButton edit =
                createGoldButton(
                        "Edit Profile"
                );


        // We'll connect real database profile editing separately.
        edit.addActionListener(
        e -> showEditProfileDialog()
);

        JPanel editArea =
                new JPanel(
                        new GridBagLayout()
                );

        editArea.setOpaque(false);

        editArea.add(edit);


        hero.add(
                leftArea,
                BorderLayout.CENTER
        );

        hero.add(
                editArea,
                BorderLayout.EAST
        );


        return hero;
    }


    // =========================================================
    // SECTION TITLE
    // =========================================================
    private JPanel createSectionTitle(
            String title,
            String description
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);


        JPanel text =
                new JPanel();

        text.setOpaque(false);

        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel heading =
                new JLabel(title);

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        heading.setForeground(
                GOLD
        );


        JLabel desc =
                new JLabel(
                        description
                );

        desc.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        desc.setForeground(
                MUTED
        );


        text.add(heading);

        text.add(
                Box.createVerticalStrut(2)
        );

        text.add(desc);


        panel.add(
                text,
                BorderLayout.WEST
        );


        return panel;
    }


    // =========================================================
    // ACCOUNT SECTION
    // =========================================================
    private JPanel createAccountSection() {

        JPanel group =
                createGroup();


        JPanel personalInformation =
                createRow(
                        "P",
                        "Personal Information",
                        "View your current AURORA account information.",
                        "View Profile",
                        true
                );


        makeClickable(
                personalInformation,
                this::showProfileDialog
        );


        group.add(
                personalInformation
        );


        group.add(
                createLine()
        );


        String role =
                UserSession.getRole() == null
                        ? "User"
                        : UserSession.getRole();


        group.add(
                createRow(
                        "R",
                        "Account Role",
                        "Access is based on your assigned AURORA role.",
                        role,
                        false
                )
        );


        return group;
    }


    // =========================================================
    // SECURITY SECTION
    // =========================================================
    private JPanel createSecuritySection() {

        JPanel group =
                createGroup();


        JPanel password =
                createRow(
                        "●",
                        "Change Password",
                        "Update your password to keep your account protected.",
                        "",
                        true
                );


        makeClickable(
                password,
                this::showChangePasswordDialog
        );


        group.add(password);

        group.add(
                createLine()
        );


        group.add(
                createRow(
                        "✓",
                        "Login Security",
                        "Your account protection status.",
                        "Protected",
                        false
                )
        );


        group.add(
                createLine()
        );


        JPanel permissions =
                createRow(
                        "◈",
                        "Privacy & Permissions",
                        "Review your current AURORA access permissions.",
                        "",
                        true
                );


        makeClickable(
                permissions,
                this::showPermissionsDialog
        );


        group.add(
                permissions
        );


        return group;
    }


    // =========================================================
    // PREFERENCES
    // =========================================================
    private JPanel createPreferencesSection() {

        JPanel group =
                createGroup();


        JPanel appearance =
                createRow(
                        "✦",
                        "Appearance",
                        "AURORA's luxury workspace appearance.",
                        "Luxury Light",
                        true
                );


        makeClickable(
                appearance,
                this::showAppearanceDialog
        );


        group.add(
                appearance
        );


        group.add(
                createLine()
        );


        JPanel language =
                createRow(
                        "A",
                        "Language",
                        "Choose your preferred application language.",
                        "English",
                        true
                );


        makeClickable(
                language,
                this::showLanguageDialog
        );


        group.add(
                language
        );


        group.add(
                createLine()
        );


        JPanel notification =
                createCustomRightRow(
                        "N",
                        "Notifications",
                        "Receive important AURORA updates.",
                        createToggle()
                );


        group.add(
                notification
        );


        return group;
    }


    // =========================================================
    // SUPPORT
    // =========================================================
    private JPanel createSupportSection() {

        JPanel group =
                createGroup();


        JPanel help =
                createRow(
                        "?",
                        "Help & User Guide",
                        "Learn how to use the AURORA management system.",
                        "",
                        true
                );


        makeClickable(
                help,
                this::showHelpDialog
        );


        group.add(help);

        group.add(
                createLine()
        );


        JPanel about =
                createRow(
                        "i",
                        "About AURORA Clothing",
                        "Learn more about AURORA and this application.",
                        "",
                        true
                );


        makeClickable(
                about,
                this::showAboutDialog
        );


        group.add(about);

        group.add(
                createLine()
        );


        group.add(
                createRow(
                        "V",
                        "Application Version",
                        "Current system release.",
                        "Version 1.0",
                        false
                )
        );


        return group;
    }
        // =========================================================
    // GROUP CARD
    // =========================================================
    private JPanel createGroup() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                IVORY
        );

        panel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                LINE,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                4,
                                4,
                                4,
                                4
                        )
                )
        );

        panel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return panel;
    }


    // =========================================================
    // SETTINGS ROW
    // =========================================================
    private JPanel createRow(
            String iconText,
            String titleText,
            String descriptionText,
            String valueText,
            boolean showArrow
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                14,
                                0
                        )
                );

        row.setBackground(
                IVORY
        );

        row.setBorder(
                new EmptyBorder(
                        15,
                        18,
                        15,
                        18
                )
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        74
                )
        );

        row.setPreferredSize(
                new Dimension(
                        850,
                        74
                )
        );


        // =====================================================
        // ICON
        // =====================================================

        JLabel icon =
                createRowIcon(
                        iconText
                );


        row.add(
                icon,
                BorderLayout.WEST
        );


        // =====================================================
        // TEXT AREA
        // =====================================================

        JPanel textArea =
                new JPanel();

        textArea.setOpaque(false);

        textArea.setLayout(
                new BoxLayout(
                        textArea,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        titleText
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        title.setForeground(
                TEXT
        );

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel description =
                new JLabel(
                        descriptionText
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        description.setForeground(
                MUTED
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        textArea.add(title);

        textArea.add(
                Box.createVerticalStrut(4)
        );

        textArea.add(description);


        row.add(
                textArea,
                BorderLayout.CENTER
        );


        // =====================================================
        // RIGHT SIDE
        // =====================================================

        JPanel right =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                7,
                                0
                        )
                );

        right.setOpaque(false);


        if (
                valueText != null
                && !valueText.isBlank()
        ) {

            JLabel value =
                    new JLabel(
                            valueText
                    );

            value.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            9
                    )
            );

            value.setForeground(
                    DEEP_GREEN
            );

            right.add(value);
        }


        if (showArrow) {

            JLabel arrow =
                    new JLabel(
                            "›"
                    );

            arrow.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            22
                    )
            );

            arrow.setForeground(
                    GOLD
            );

            right.add(arrow);
        }


        row.add(
                right,
                BorderLayout.EAST
        );


        return row;
    }


    // =========================================================
    // ROW WITH CUSTOM RIGHT COMPONENT
    // =========================================================
    private JPanel createCustomRightRow(
            String iconText,
            String titleText,
            String descriptionText,
            JComponent rightComponent
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                14,
                                0
                        )
                );

        row.setBackground(
                IVORY
        );

        row.setBorder(
                new EmptyBorder(
                        15,
                        18,
                        15,
                        18
                )
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        74
                )
        );

        row.setPreferredSize(
                new Dimension(
                        850,
                        74
                )
        );


        JLabel icon =
                createRowIcon(
                        iconText
                );


        row.add(
                icon,
                BorderLayout.WEST
        );


        JPanel textArea =
                new JPanel();

        textArea.setOpaque(false);

        textArea.setLayout(
                new BoxLayout(
                        textArea,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        titleText
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        title.setForeground(
                TEXT
        );

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel description =
                new JLabel(
                        descriptionText
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        description.setForeground(
                MUTED
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        textArea.add(title);

        textArea.add(
                Box.createVerticalStrut(4)
        );

        textArea.add(description);


        row.add(
                textArea,
                BorderLayout.CENTER
        );


        JPanel right =
                new JPanel(
                        new GridBagLayout()
                );

        right.setOpaque(false);

        right.add(
                rightComponent
        );


        row.add(
                right,
                BorderLayout.EAST
        );


        return row;
    }


    // =========================================================
    // ROW ICON
    // =========================================================
    private JLabel createRowIcon(
            String text
    ) {

        JLabel icon =
                new JLabel(
                        text,
                        SwingConstants.CENTER
                );

        icon.setPreferredSize(
                new Dimension(
                        36,
                        36
                )
        );

        icon.setMinimumSize(
                new Dimension(
                        36,
                        36
                )
        );

        icon.setMaximumSize(
                new Dimension(
                        36,
                        36
                )
        );

        icon.setOpaque(true);

        icon.setBackground(
                PALE_GOLD
        );

        icon.setForeground(
                DEEP_GREEN
        );

        icon.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        14
                )
        );

        icon.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                201,
                                150
                        ),
                        1
                )
        );


        return icon;
    }


    // =========================================================
    // DIVIDER LINE
    // =========================================================
    private JSeparator createLine() {

        JSeparator line =
                new JSeparator();

        line.setForeground(
                LINE
        );

        line.setBackground(
                LINE
        );

        line.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        1
                )
        );

        return line;
    }


    // =========================================================
    // CLICKABLE ROW
    // =========================================================
    private void makeClickable(
            JPanel panel,
            Runnable action
    ) {

        panel.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        MouseAdapter listener =
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        panel.setBackground(
                                new Color(
                                        248,
                                        246,
                                        237
                                )
                        );

                        panel.repaint();
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        panel.setBackground(
                                IVORY
                        );

                        panel.repaint();
                    }


                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (
                                SwingUtilities
                                        .isLeftMouseButton(e)
                        ) {

                            action.run();
                        }
                    }
                };


        addMouseListenerRecursively(
                panel,
                listener
        );
    }


    // =========================================================
    // MAKE CHILD COMPONENTS CLICKABLE TOO
    // =========================================================
    private void addMouseListenerRecursively(
            Component component,
            MouseAdapter listener
    ) {

        component.addMouseListener(
                listener
        );


        if (
                component instanceof Container
        ) {

            Container container =
                    (Container) component;


            for (
                    Component child
                    : container.getComponents()
            ) {

                addMouseListenerRecursively(
                        child,
                        listener
                );
            }
        }
    }


    // =========================================================
    // GOLD BUTTON
    // =========================================================
    private JButton createGoldButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        button.setForeground(
                DARK_GREEN
        );

        button.setBackground(
                GOLD
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setOpaque(
                true
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        9,
                        17,
                        9,
                        17
                )
        );


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(
                                        216,
                                        181,
                                        92
                                )
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                GOLD
                        );
                    }
                }
        );


        return button;
    }


    // =========================================================
    // NOTIFICATION TOGGLE
    // =========================================================
    private JToggleButton createToggle() {

        JToggleButton toggle =
                new JToggleButton(
                        "ON",
                        true
                );

        toggle.setPreferredSize(
                new Dimension(
                        58,
                        28
                )
        );

        toggle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        toggle.setForeground(
                IVORY
        );

        toggle.setBackground(
                DEEP_GREEN
        );

        toggle.setFocusPainted(
                false
        );

        toggle.setBorderPainted(
                false
        );

        toggle.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        toggle.addActionListener(
                e -> {

                    if (
                            toggle.isSelected()
                    ) {

                        toggle.setText(
                                "ON"
                        );

                        toggle.setForeground(
                                IVORY
                        );

                        toggle.setBackground(
                                DEEP_GREEN
                        );

                    } else {

                        toggle.setText(
                                "OFF"
                        );

                        toggle.setForeground(
                                MUTED
                        );

                        toggle.setBackground(
                                new Color(
                                        226,
                                        222,
                                        211
                                )
                        );
                    }
                }
        );


        return toggle;
    }


    // =========================================================
    // SMALL INFORMATION CHIP
    // =========================================================
    private JLabel createInfoChip(
            String text
    ) {

        JLabel chip =
                new JLabel(
                        "  " + text + "  "
                );

        chip.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        chip.setForeground(
                DEEP_GREEN
        );

        chip.setOpaque(
                true
        );

        chip.setBackground(
                PALE_GOLD
        );

        chip.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        222,
                                        205,
                                        157
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                4,
                                6,
                                4,
                                6
                        )
                )
        );


        return chip;
    }
// =========================================================
// EDIT PROFILE
// =========================================================
private void showEditProfileDialog() {

    String currentName =
            UserSession.getFullName() == null
                    ? ""
                    : UserSession.getFullName();

    String currentUsername =
            UserSession.getUsername() == null
                    ? ""
                    : UserSession.getUsername();


    JTextField fullNameField =
            new JTextField(currentName);

    JTextField usernameField =
            new JTextField(currentUsername);


    JPanel form =
            new JPanel(
                    new GridLayout(
                            2,
                            2,
                            10,
                            12
                    )
            );

    form.setBorder(
            new EmptyBorder(
                    10,
                    10,
                    10,
                    10
            )
    );


    form.add(
            new JLabel("Full Name:")
    );

    form.add(fullNameField);


    form.add(
            new JLabel("Username:")
    );

    form.add(usernameField);


    int result =
            JOptionPane.showConfirmDialog(
                    this,
                    form,
                    "AURORA | Edit Profile",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );


    if (
            result
            != JOptionPane.OK_OPTION
    ) {

        return;
    }


    String newFullName =
            fullNameField
                    .getText()
                    .trim();

    String newUsername =
            usernameField
                    .getText()
                    .trim();


    // =====================================================
    // VALIDATION
    // =====================================================

    if (
            newFullName.isBlank()
            || newUsername.isBlank()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Full name and username cannot be empty.",
                "AURORA | Validation",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }


    // =====================================================
    // UPDATE DATABASE
    // =====================================================

    boolean updated =
            UserDAO.updateOwnProfile(
                    UserSession.getUserId(),
                    newFullName,
                    newUsername
            );


    if (updated) {

        JOptionPane.showMessageDialog(
                this,
                "Your profile has been updated successfully.\n\n"
                + "Please sign out and sign in again "
                + "to refresh your account information.",
                "AURORA | Profile Updated",
                JOptionPane.INFORMATION_MESSAGE
        );

    } else {

        JOptionPane.showMessageDialog(
                this,
                "The profile could not be updated.\n"
                + "The username may already be in use.",
                "AURORA | Update Failed",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

    // =========================================================
    // PROFILE DIALOG
    // =========================================================
    private void showProfileDialog() {

        String fullName =
                UserSession.getFullName() == null
                        ? "Not Available"
                        : UserSession.getFullName();


        String username =
                UserSession.getUsername() == null
                        ? "Not Available"
                        : UserSession.getUsername();


        String role =
                UserSession.getRole() == null
                        ? "Not Available"
                        : UserSession.getRole();


        JPanel profile =
                new JPanel();

        profile.setLayout(
                new BoxLayout(
                        profile,
                        BoxLayout.Y_AXIS
                )
        );

        profile.setBorder(
                new EmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );


        JLabel heading =
                new JLabel(
                        "AURORA ACCOUNT"
                );

        heading.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        18
                )
        );

        heading.setForeground(
                DEEP_GREEN
        );


        JLabel nameLabel =
                new JLabel(
                        "Full Name:  "
                        + fullName
                );


        JLabel usernameLabel =
                new JLabel(
                        "Username:  "
                        + username
                );


        JLabel roleLabel =
                new JLabel(
                        "Role:  "
                        + role
                );


        JLabel statusLabel =
                new JLabel(
                        "Status:  Active"
                );


        Font informationFont =
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                );


        nameLabel.setFont(
                informationFont
        );

        usernameLabel.setFont(
                informationFont
        );

        roleLabel.setFont(
                informationFont
        );

        statusLabel.setFont(
                informationFont
        );


        profile.add(heading);

        profile.add(
                Box.createVerticalStrut(14)
        );

        profile.add(nameLabel);

        profile.add(
                Box.createVerticalStrut(7)
        );

        profile.add(usernameLabel);

        profile.add(
                Box.createVerticalStrut(7)
        );

        profile.add(roleLabel);

        profile.add(
                Box.createVerticalStrut(7)
        );

        profile.add(statusLabel);


        JOptionPane.showMessageDialog(
                this,
                profile,
                "AURORA | My Profile",
                JOptionPane.PLAIN_MESSAGE
        );
    }


    // =========================================================
    // PRIVACY & PERMISSIONS
    // =========================================================
    private void showPermissionsDialog() {

        String role =
                UserSession.getRole() == null
                        ? "User"
                        : UserSession.getRole();


        String permissions;


        if (
                UserSession
                        .canManageSensitiveRecords()
        ) {

            permissions =
                    "Dashboard                 ✓ Allowed\n"
                    + "Products                  ✓ Allowed\n"
                    + "Inventory                 ✓ Allowed\n"
                    + "Customers                 ✓ Allowed\n"
                    + "Sales                     ✓ Allowed\n"
                    + "Reports                   ✓ Allowed\n"
                    + "User Management           ✓ Allowed\n"
                    + "Delete Records            ✓ Allowed";

        } else {

            permissions =
                    "Dashboard                 ✓ Allowed\n"
                    + "Products                  ✓ Allowed\n"
                    + "Inventory                 ✓ Allowed\n"
                    + "Customers                 ✓ Allowed\n"
                    + "Sales                     ✓ Allowed\n"
                    + "Reports                   ✕ Restricted\n"
                    + "User Management           ✕ Restricted\n"
                    + "Delete Records            ✕ Restricted";
        }


        JTextArea permissionText =
                new JTextArea(
                        permissions
                );

        permissionText.setEditable(
                false
        );

        permissionText.setOpaque(
                false
        );

        permissionText.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        11
                )
        );

        permissionText.setForeground(
                TEXT
        );


        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel roleLabel =
                new JLabel(
                        "Current Role: "
                        + role
                );

        roleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        roleLabel.setForeground(
                DEEP_GREEN
        );


        panel.add(roleLabel);

        panel.add(
                Box.createVerticalStrut(12)
        );

        panel.add(permissionText);


        JOptionPane.showMessageDialog(
                this,
                panel,
                "AURORA | Privacy & Permissions",
                JOptionPane.PLAIN_MESSAGE
        );
    }


    // =========================================================
    // APPEARANCE
    // =========================================================
    private void showAppearanceDialog() {

        JOptionPane.showMessageDialog(
                this,
                "Current Theme: Luxury Light\n\n"
                + "AURORA uses its signature ivory, "
                + "green and champagne-gold workspace.\n\n"
                + "Additional themes are not enabled "
                + "in Version 1.0.",
                "AURORA | Appearance",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================================================
    // LANGUAGE
    // =========================================================
    private void showLanguageDialog() {

        JOptionPane.showMessageDialog(
                this,
                "Current Language: English\n\n"
                + "English is currently the supported "
                + "application language in AURORA Version 1.0.",
                "AURORA | Language",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================================================
    // HELP & USER GUIDE
    // =========================================================
    private void showHelpDialog() {

        JTextArea guide =
                new JTextArea(
                        "AURORA QUICK USER GUIDE\n\n"
                        + "Dashboard  - View the business overview\n"
                        + "Products   - Manage clothing products\n"
                        + "Inventory  - View and update stock\n"
                        + "Customers  - Manage customer information\n"
                        + "Sales      - Create and manage sales\n"
                        + "Reports    - View business reports\n"
                        + "Users      - Manage system accounts\n"
                        + "Settings   - Manage your account and security\n\n"
                        + "Available features depend on "
                        + "the logged-in user's role."
                );

        guide.setEditable(
                false
        );

        guide.setOpaque(
                false
        );

        guide.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        guide.setForeground(
                TEXT
        );


        JOptionPane.showMessageDialog(
                this,
                guide,
                "AURORA | Help & User Guide",
                JOptionPane.PLAIN_MESSAGE
        );
    }


    // =========================================================
    // ABOUT AURORA
    // =========================================================
    private void showAboutDialog() {

        JOptionPane.showMessageDialog(
                this,
                "A U R O R A   C L O T H I N G\n\n"
                + "More than clothing - A lifestyle\n\n"
                + "AURORA Clothing Management System\n"
                + "Version 1.0\n\n"
                + "Centralized management for products, "
                + "inventory, customers, sales, users "
                + "and business reports.",
                "About AURORA Clothing",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
        // =========================================================
    // CHANGE PASSWORD
    // =========================================================
    private void showChangePasswordDialog() {

        JPanel form =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                12
                        )
                );


        JPasswordField current =
                new JPasswordField();


        JPasswordField newPassword =
                new JPasswordField();


        JPasswordField confirm =
                new JPasswordField();


        form.add(
                new JLabel(
                        "Current Password:"
                )
        );

        form.add(current);


        form.add(
                new JLabel(
                        "New Password:"
                )
        );

        form.add(newPassword);


        form.add(
                new JLabel(
                        "Confirm Password:"
                )
        );

        form.add(confirm);


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        form,
                        "AURORA | Change Password",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (
                result
                != JOptionPane.OK_OPTION
        ) {

            return;
        }


        String oldPass =
                new String(
                        current.getPassword()
                );


        String newPass =
                new String(
                        newPassword.getPassword()
                );


        String confirmPass =
                new String(
                        confirm.getPassword()
                );


        // =====================================================
        // EMPTY FIELD VALIDATION
        // =====================================================

        if (
                oldPass.isBlank()
                || newPass.isBlank()
                || confirmPass.isBlank()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all password fields.",
                    "AURORA Security",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // CONFIRM PASSWORD VALIDATION
        // =====================================================

        if (
                !newPass.equals(
                        confirmPass
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "New password and confirmation do not match.",
                    "AURORA Security",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // VERIFY CURRENT PASSWORD
        // =====================================================

        Object[] authenticatedUser =
                UserDAO.authenticateUser(
                        UserSession.getUsername(),
                        oldPass
                );


        if (
                authenticatedUser == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "The current password you entered is incorrect.",
                    "AURORA | Incorrect Password",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // PREVENT SAME PASSWORD
        // =====================================================

        if (
                oldPass.equals(
                        newPass
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your new password must be different "
                    + "from your current password.",
                    "AURORA | Change Password",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // UPDATE PASSWORD IN MYSQL
        // =====================================================

        boolean changed =
                UserDAO.changePassword(
                        UserSession.getUserId(),
                        newPass
                );


        if (changed) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your password has been changed successfully.",
                    "AURORA | Password Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "The password could not be changed.\n"
                    + "Please try again.",
                    "AURORA | Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // FOOTER
    // =========================================================
    private JPanel createFooter() {

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setOpaque(false);

        footer.setBorder(
                new EmptyBorder(
                        10,
                        4,
                        0,
                        4
                )
        );


        JLabel left =
                new JLabel(
                        "AURORA CLOTHING  •  Management System"
                );

        left.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        8
                )
        );

        left.setForeground(
                MUTED
        );


        JLabel right =
                new JLabel(
                        "SECURE WORKSPACE  •  VERSION 1.0"
                );

        right.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        right.setForeground(
                GOLD
        );


        footer.add(
                left,
                BorderLayout.WEST
        );

        footer.add(
                right,
                BorderLayout.EAST
        );


        return footer;
    }


    // =========================================================
    // END OF SETTINGS PANEL
    // =========================================================
}