/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class ProductsPanel extends JPanel {
    private final ProductController productController =
        new ProductController();

    // =========================================================
    // AURORA COLORS
    // =========================================================

    private final Color DEEP_GREEN =
            new Color(60, 74, 52);

    private final Color DARK_GREEN =
            new Color(42, 55, 39);

    private final Color SOFT_GREEN =
            new Color(91, 116, 101);

    private final Color GOLD =
            new Color(201, 164, 76);

    private final Color PALE_GOLD =
            new Color(239, 229, 198);

    private final Color CREAM =
            new Color(238, 235, 224);

    private final Color IVORY =
            new Color(250, 248, 242);

    private final Color TEXT =
            new Color(55, 52, 46);

    private final Color MUTED =
            new Color(125, 122, 113);


    // =========================================================
    // TABLE COMPONENTS
    // =========================================================

    private DefaultTableModel productTableModel;

    private JTable productTable;

    private TableRowSorter<DefaultTableModel> tableSorter;


    // =========================================================
    // SEARCH COMPONENTS
    // =========================================================

    private PlaceholderTextField searchField;

    private LuxurySearchBar searchBar;


    // =========================================================
    // STATISTIC LABELS
    // =========================================================

    private JLabel totalProductsValue;

    private JLabel inStockValue;

    private JLabel lowStockValue;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ProductsPanel() {

        setLayout(
                new BorderLayout()
        );

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
    // CREATE MAIN UI
    // =========================================================

    private void createUI() {

        // =====================================================
        // TOP AREA
        // =====================================================

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);


        // =====================================================
        // TITLE AREA
        // =====================================================

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
                        "Product Management"
                );

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                DEEP_GREEN
        );


        JLabel subtitle =
                new JLabel(
                        "Manage your AURORA clothing collection."
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(
                MUTED
        );


        titleArea.add(title);

        titleArea.add(
                Box.createVerticalStrut(4)
        );

        titleArea.add(subtitle);


        top.add(
                titleArea,
                BorderLayout.WEST
        );


        // =====================================================
        // ADD PRODUCT BUTTON
        // =====================================================

        JButton addButton =
                createMainButton(
                        "+  Add Product"
                );

        addButton.setPreferredSize(
                new Dimension(
                        145,
                        42
                )
        );

        addButton.addActionListener(
                e -> showProductDialog(null)
        );


        top.add(
                addButton,
                BorderLayout.EAST
        );


        add(
                top,
                BorderLayout.NORTH
        );


        // =====================================================
        // MAIN CONTENT
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
        // HERO BANNER
        // =====================================================

        JPanel hero =
                createProductsHero();

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
        // STAT CARDS
        // =====================================================

        totalProductsValue =
                createValueLabel("0");

        inStockValue =
                createValueLabel("0");

        lowStockValue =
                createValueLabel("0");


        JPanel stats =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                16,
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


        stats.add(
                createSmallCard(
                        "TOTAL PRODUCTS",
                        totalProductsValue,
                        "Complete collection"
                )
        );


        stats.add(
                createSmallCard(
                        "IN STOCK",
                        inStockValue,
                        "Available products"
                )
        );


        stats.add(
                createSmallCard(
                        "LOW STOCK",
                        lowStockValue,
                        "Requires attention"
                )
        );


        content.add(stats);

        content.add(
                Box.createVerticalStrut(20)
        );


        // =====================================================
        // NEW LUXURY SEARCH AREA
        // =====================================================

        JPanel searchArea =
                new JPanel(
                        new BorderLayout(
                                14,
                                0
                        )
                );

        searchArea.setOpaque(false);

        searchArea.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        searchArea.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // =====================================================
        // SEARCH FIELD WITH PLACEHOLDER
        // =====================================================

        searchField =
                new PlaceholderTextField(
                        "Search products by ID, name, category or status..."
                );

        searchField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        searchField.setForeground(TEXT);

        searchField.setCaretColor(
                DEEP_GREEN
        );

        searchField.setOpaque(false);

        searchField.setBorder(
                new EmptyBorder(
                        0,
                        4,
                        0,
                        4
                )
        );


        // =====================================================
        // SEARCH ICON
        // =====================================================

        SearchIcon searchIcon =
                new SearchIcon();

        searchIcon.setPreferredSize(
                new Dimension(
                        42,
                        42
                )
        );


        // =====================================================
        // CLEAR SEARCH BUTTON
        // =====================================================

        JButton clearSearchButton =
                new JButton("×");

        clearSearchButton.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        21
                )
        );

        clearSearchButton.setForeground(
                MUTED
        );

        clearSearchButton.setOpaque(false);

        clearSearchButton.setContentAreaFilled(
                false
        );

        clearSearchButton.setBorderPainted(
                false
        );

        clearSearchButton.setFocusPainted(
                false
        );

        clearSearchButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        clearSearchButton.setToolTipText(
                "Clear search"
        );

        clearSearchButton.setPreferredSize(
                new Dimension(
                        42,
                        42
                )
        );

        clearSearchButton.setVisible(
                false
        );


        // =====================================================
        // ROUNDED SEARCH BAR
        // =====================================================

        searchBar =
                new LuxurySearchBar();

        searchBar.setLayout(
                new BorderLayout(
                        2,
                        0
                )
        );

        searchBar.setOpaque(false);

        searchBar.setPreferredSize(
                new Dimension(
                        650,
                        46
                )
        );


        searchBar.add(
                searchIcon,
                BorderLayout.WEST
        );

        searchBar.add(
                searchField,
                BorderLayout.CENTER
        );

        searchBar.add(
                clearSearchButton,
                BorderLayout.EAST
        );


        // =====================================================
        // AURORA COLLECTION LABEL
        // =====================================================

        JPanel collectionPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                14
                        )
                );

        collectionPanel.setOpaque(false);


        JLabel collection =
                new JLabel(
                        "AURORA COLLECTION   ✦"
                );

        collection.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        collection.setForeground(
                GOLD
        );


        collectionPanel.add(
                collection
        );


        searchArea.add(
                searchBar,
                BorderLayout.CENTER
        );

        searchArea.add(
                collectionPanel,
                BorderLayout.EAST
        );


        content.add(searchArea);

        content.add(
                Box.createVerticalStrut(15)
        );


        // =====================================================
        // PRODUCT TABLE
        // =====================================================

        String[] columns = {

            "PRODUCT ID",
            "PRODUCT",
            "CATEGORY",
            "PRICE",
            "STOCK",
            "STATUS"
        };


        productTableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        productTable =
                new JTable(
                        productTableModel
                );


        // =====================================================
        // TABLE CENTER ALIGNMENT
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        for (
                int i = 0;
                i < productTable.getColumnCount();
                i++
        ) {

            productTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }


        // =====================================================
        // TABLE STYLE
        // =====================================================

        productTable.setRowHeight(42);

        productTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        productTable.setForeground(TEXT);

        productTable.setBackground(IVORY);

        productTable.setSelectionBackground(
                new Color(
                        223,
                        218,
                        196
                )
        );

        productTable.setSelectionForeground(
                DEEP_GREEN
        );

        productTable.setShowVerticalLines(false);

        productTable.setGridColor(
                new Color(
                        225,
                        220,
                        205
                )
        );

        productTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        // =====================================================
        // TABLE HEADER
        // =====================================================

        JTableHeader header =
                productTable.getTableHeader();

        header.setPreferredSize(
                new Dimension(
                        header.getWidth(),
                        42
                )
        );

        header.setBackground(
                DEEP_GREEN
        );

        header.setForeground(
                Color.WHITE
        );

        header.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        header.setReorderingAllowed(
                false
        );


        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        productTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        productTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(180);

        productTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(130);

        productTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(120);

        productTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(80);

        productTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(110);


        // =====================================================
        // LIVE SEARCH FILTER
        // =====================================================

        tableSorter =
                new TableRowSorter<>(
                        productTableModel
                );

        productTable.setRowSorter(
                tableSorter
        );


        searchField
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            private void updateSearch() {

                                filterProducts();

                                clearSearchButton.setVisible(
                                        !searchField
                                                .getText()
                                                .trim()
                                                .isEmpty()
                                );

                                searchBar.revalidate();

                                searchBar.repaint();
                            }


                            @Override
                            public void insertUpdate(
                                    DocumentEvent e
                            ) {

                                updateSearch();
                            }


                            @Override
                            public void removeUpdate(
                                    DocumentEvent e
                            ) {

                                updateSearch();
                            }


                            @Override
                            public void changedUpdate(
                                    DocumentEvent e
                            ) {

                                updateSearch();
                            }
                        }
                );


        // =====================================================
        // GOLD FOCUS EFFECT
        // =====================================================

        searchField.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(
                            FocusEvent e
                    ) {

                        searchBar.repaint();

                        searchField.repaint();
                    }


                    @Override
                    public void focusLost(
                            FocusEvent e
                    ) {

                        searchBar.repaint();

                        searchField.repaint();
                    }
                }
        );


        // =====================================================
        // CLEAR SEARCH
        // =====================================================

        clearSearchButton.addActionListener(
                e -> {

                    searchField.setText("");

                    searchField.requestFocusInWindow();
                }
        );


        // =====================================================
        // TABLE SCROLL
        // =====================================================

        JScrollPane scroll =
                new JScrollPane(
                        productTable
                );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                218,
                                212,
                                195
                        )
                )
        );

        scroll.getViewport()
                .setBackground(IVORY);

        scroll.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        scroll.setPreferredSize(
                new Dimension(
                        900,
                        230
                )
        );

        scroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        260
                )
        );


        content.add(scroll);

        content.add(
                Box.createVerticalStrut(12)
        );


        // =====================================================
        // EDIT / DELETE BUTTON AREA
        // =====================================================

        JPanel actionPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        actionPanel.setOpaque(false);

        actionPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        actionPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );


        JButton editButton =
                createSecondaryButton(
                        "✎  Edit Selected"
                );


        JButton deleteButton =
                createDeleteButton(
                        "Delete Selected"
                );


        editButton.addActionListener(
                e -> editSelectedProduct()
        );


        deleteButton.addActionListener(
                e -> deleteSelectedProduct()
        );


        actionPanel.add(
                editButton
        );

        actionPanel.add(
                deleteButton
        );


        content.add(
                actionPanel
        );


        // =====================================================
        // PAGE SCROLL
        // =====================================================

        JScrollPane pageScroll =
                new JScrollPane(
                        content
                );

        pageScroll.setBorder(null);

        pageScroll.setOpaque(false);

        pageScroll.getViewport()
                .setOpaque(false);

        pageScroll.getVerticalScrollBar()
                .setUnitIncrement(14);


        add(
                pageScroll,
                BorderLayout.CENTER
        );


        // =====================================================
        // LOAD REAL MYSQL PRODUCTS
        // =====================================================

        loadProducts();
    }


    // =========================================================
    // LOAD PRODUCTS FROM MYSQL
    // =========================================================

    private void loadProducts() {

        productTableModel.setRowCount(0);


        List<Object[]> products =
                productController.getAllProducts();


        int total = 0;

        int inStock = 0;

        int lowStock = 0;


        for (
                Object[] product :
                products
        ) {

            String status =
                    String.valueOf(
                            product[5]
                    );


            double price =
                    ((Number) product[3])
                            .doubleValue();


            Object[] displayRow = {

                product[0],

                product[1],

                product[2],

                String.format(
                        "LKR %,.2f",
                        price
                ),

                product[4],

                product[5]
            };


            productTableModel.addRow(
                    displayRow
            );


            total++;


            if (
                    status.equalsIgnoreCase(
                            "In Stock"
                    )
            ) {

                inStock++;

            } else if (
                    status.equalsIgnoreCase(
                            "Low Stock"
                    )
            ) {

                lowStock++;
            }
        }


        totalProductsValue.setText(
                String.valueOf(total)
        );


        inStockValue.setText(
                String.valueOf(inStock)
        );


        lowStockValue.setText(
                String.valueOf(lowStock)
        );
    }


    // =========================================================
    // SEARCH PRODUCTS
    // =========================================================

    private void filterProducts() {

        if (
                tableSorter == null
                || searchField == null
        ) {

            return;
        }


        String text =
                searchField
                        .getText()
                        .trim();


        if (
                text.isEmpty()
        ) {

            tableSorter.setRowFilter(
                    null
            );

        } else {

            tableSorter.setRowFilter(

                    RowFilter.regexFilter(
                            "(?i)"
                            + Pattern.quote(text)
                    )
            );
        }
    }


    // =========================================================
    // EDIT SELECTED PRODUCT
    // =========================================================

    private void editSelectedProduct() {

        int selectedRow =
                productTable
                        .getSelectedRow();


        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a product to edit.",
                    "AURORA | Select Product",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int modelRow =
                productTable
                        .convertRowIndexToModel(
                                selectedRow
                        );


        String productId =
                productTableModel
                        .getValueAt(
                                modelRow,
                                0
                        )
                        .toString();


        showProductDialog(
                productId
        );
    }


    // =========================================================
    // DELETE SELECTED PRODUCT
    // =========================================================

    private void deleteSelectedProduct() {
        // =========================================================
// SECURITY - STAFF CANNOT DELETE PRODUCTS
// =========================================================

if (!UserSession.canManageSensitiveRecords()) {

    JOptionPane.showMessageDialog(
            this,
            "You do not have permission to delete products.\n"
            + "Please contact an Administrator or Manager.",
            "AURORA | Access Denied",
            JOptionPane.WARNING_MESSAGE
    );

    return;
}

        int selectedRow =
                productTable
                        .getSelectedRow();


        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a product to delete.",
                    "AURORA | Select Product",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int modelRow =
                productTable
                        .convertRowIndexToModel(
                                selectedRow
                        );


        String productId =
                productTableModel
                        .getValueAt(
                                modelRow,
                                0
                        )
                        .toString();


        String productName =
                productTableModel
                        .getValueAt(
                                modelRow,
                                1
                        )
                        .toString();


        int choice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Delete "
                        + productName
                        + " ("
                        + productId
                        + ")?\n\n"
                        + "This action cannot be undone.",

                        "AURORA | Delete Product",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.WARNING_MESSAGE
                );


        if (
                choice
                != JOptionPane.YES_OPTION
        ) {

            return;
        }


        boolean deleted =
                productController.deleteProduct(
                        productId
                );


        if (
                deleted
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Product deleted successfully.",
                    "AURORA | Product Deleted",
                    JOptionPane.INFORMATION_MESSAGE
            );


            loadProducts();

        } else {

            JOptionPane.showMessageDialog(
                    this,

                    "The product could not be deleted.\n"
                    + "It may already be connected to a sales record.",

                    "AURORA | Delete Failed",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // ADD / EDIT PRODUCT DIALOG
    // =========================================================

    private void showProductDialog(
            String productIdToEdit
    ) {

        boolean editing =
                productIdToEdit != null;


        Object[] existingProduct =
                null;


        if (
                editing
        ) {

            existingProduct =
                    productController.getProductById(
                            productIdToEdit
                    );


            if (
                    existingProduct == null
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "The selected product could not be loaded.",
                        "AURORA | Product Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }


        JDialog dialog =
                new JDialog(

                        SwingUtilities
                                .getWindowAncestor(
                                        this
                                ),

                        editing
                                ? "AURORA | Edit Product"
                                : "AURORA | Add Product",

                        Dialog.ModalityType
                                .APPLICATION_MODAL
                );


        dialog.setSize(
                520,
                610
        );


        dialog.setResizable(
                false
        );


        dialog.setLocationRelativeTo(
                this
        );


        // =====================================================
        // BACKGROUND
        // =====================================================

        JPanel background =
                new JPanel(
                        new BorderLayout()
                );


        background.setBackground(
                CREAM
        );


        background.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );


        // =====================================================
        // HEADER
        // =====================================================

        JPanel dialogHeader =
                new JPanel();


        dialogHeader.setOpaque(
                false
        );


        dialogHeader.setLayout(
                new BoxLayout(
                        dialogHeader,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel smallTitle =
                new JLabel(
                        "A U R O R A   C L O T H I N G"
                );


        smallTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );


        smallTitle.setForeground(
                GOLD
        );


        JLabel dialogTitle =
                new JLabel(

                        editing
                                ? "Edit Product"
                                : "Add New Product"
                );


        dialogTitle.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        27
                )
        );


        dialogTitle.setForeground(
                DEEP_GREEN
        );


        JLabel dialogSubtitle =
                new JLabel(

                        editing

                                ? "Refine the selected AURORA piece."

                                : "Add a new piece to the AURORA collection."
                );


        dialogSubtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );


        dialogSubtitle.setForeground(
                MUTED
        );


        JPanel goldLine =
                new JPanel();


        goldLine.setBackground(
                GOLD
        );


        goldLine.setPreferredSize(
                new Dimension(
                        80,
                        2
                )
        );


        goldLine.setMaximumSize(
                new Dimension(
                        80,
                        2
                )
        );


        goldLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        dialogHeader.add(
                smallTitle
        );


        dialogHeader.add(
                Box.createVerticalStrut(5)
        );


        dialogHeader.add(
                dialogTitle
        );


        dialogHeader.add(
                Box.createVerticalStrut(4)
        );


        dialogHeader.add(
                dialogSubtitle
        );


        dialogHeader.add(
                Box.createVerticalStrut(12)
        );


        dialogHeader.add(
                goldLine
        );


        background.add(
                dialogHeader,
                BorderLayout.NORTH
        );


        // =====================================================
        // FORM
        // =====================================================

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );


        form.setOpaque(
                false
        );


        form.setBorder(
                new EmptyBorder(
                        18,
                        0,
                        12,
                        0
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();


        gbc.gridx = 0;

        gbc.gridy = 0;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        gbc.insets =
                new Insets(
                        4,
                        0,
                        4,
                        0
                );


        // =====================================================
        // PRODUCT ID
        // =====================================================

        form.add(
                createFormLabel(
                        "Product ID"
                ),
                gbc
        );


        gbc.gridy++;


        JTextField txtProductId =
                createFormField();


        form.add(
                txtProductId,
                gbc
        );


        // =====================================================
        // PRODUCT NAME
        // =====================================================

        gbc.gridy++;


        form.add(
                createFormLabel(
                        "Product Name"
                ),
                gbc
        );


        gbc.gridy++;


        JTextField txtProductName =
                createFormField();


        form.add(
                txtProductName,
                gbc
        );


        // =====================================================
        // CATEGORY
        // =====================================================

        gbc.gridy++;


        form.add(
                createFormLabel(
                        "Category"
                ),
                gbc
        );


        gbc.gridy++;


        JComboBox<String> cmbCategory =
                new JComboBox<>(
                        new String[]{

                            "Casual",

                            "Women",

                            "Men",

                            "Sportswear",

                            "Kids",

                            "Other"
                        }
                );


        styleComboBox(
                cmbCategory
        );


        form.add(
                cmbCategory,
                gbc
        );


        // =====================================================
        // PRICE + STOCK
        // =====================================================

        gbc.gridy++;


        JPanel priceStockPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                0
                        )
                );


        priceStockPanel.setOpaque(
                false
        );


        JPanel pricePanel =
                createMiniFormPanel();


        JLabel lblPrice =
                createFormLabel(
                        "Price (LKR)"
                );


        JTextField txtPrice =
                createFormField();


        pricePanel.add(
                lblPrice
        );


        pricePanel.add(
                Box.createVerticalStrut(5)
        );


        pricePanel.add(
                txtPrice
        );


        JPanel stockPanel =
                createMiniFormPanel();


        JLabel lblStock =
                createFormLabel(
                        "Stock"
                );


        JTextField txtStock =
                createFormField();


        stockPanel.add(
                lblStock
        );


        stockPanel.add(
                Box.createVerticalStrut(5)
        );


        stockPanel.add(
                txtStock
        );


        priceStockPanel.add(
                pricePanel
        );


        priceStockPanel.add(
                stockPanel
        );


        form.add(
                priceStockPanel,
                gbc
        );


        // =====================================================
        // STATUS
        // =====================================================

        gbc.gridy++;


        form.add(
                createFormLabel(
                        "Status"
                ),
                gbc
        );


        gbc.gridy++;


        JComboBox<String> cmbStatus =
                new JComboBox<>(
                        new String[]{

                            "In Stock",

                            "Low Stock",

                            "Out of Stock"
                        }
                );


        styleComboBox(
                cmbStatus
        );


        form.add(
                cmbStatus,
                gbc
        );


        background.add(
                form,
                BorderLayout.CENTER
        );


        // =====================================================
        // LOAD EXISTING PRODUCT WHEN EDITING
        // =====================================================

        if (
                editing
        ) {

            txtProductId.setText(
                    String.valueOf(
                            existingProduct[0]
                    )
            );


            txtProductName.setText(
                    String.valueOf(
                            existingProduct[1]
                    )
            );


            cmbCategory.setSelectedItem(
                    String.valueOf(
                            existingProduct[2]
                    )
            );


            txtPrice.setText(
                    String.valueOf(
                            existingProduct[3]
                    )
            );


            txtStock.setText(
                    String.valueOf(
                            existingProduct[4]
                    )
            );


            cmbStatus.setSelectedItem(
                    String.valueOf(
                            existingProduct[5]
                    )
            );


            txtProductId.setEditable(
                    false
            );


            txtProductId.setBackground(
                    new Color(
                            232,
                            230,
                            220
                    )
            );
        }


        // =====================================================
        // BUTTON AREA
        // =====================================================

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );


        buttons.setOpaque(
                false
        );


        JButton cancelButton =
                createSecondaryButton(
                        "Cancel"
                );


        JButton saveButton =
                createMainButton(

                        editing

                                ? "Save Changes"

                                : "Add Product"
                );


        cancelButton.setPreferredSize(
                new Dimension(
                        110,
                        40
                )
        );


        saveButton.setPreferredSize(
                new Dimension(
                        135,
                        40
                )
        );


        buttons.add(
                cancelButton
        );


        buttons.add(
                saveButton
        );


        background.add(
                buttons,
                BorderLayout.SOUTH
        );


        // =====================================================
        // CANCEL
        // =====================================================

        cancelButton.addActionListener(
                e -> dialog.dispose()
        );


        // =====================================================
        // SAVE PRODUCT
        // =====================================================

        saveButton.addActionListener(
                e -> {

                    String productId =
                            txtProductId
                                    .getText()
                                    .trim()
                                    .toUpperCase();


                    String productName =
                            txtProductName
                                    .getText()
                                    .trim();


                    String category =
                            String.valueOf(
                                    cmbCategory
                                            .getSelectedItem()
                            );


                    String priceText =
                            txtPrice
                                    .getText()
                                    .trim();


                    String stockText =
                            txtStock
                                    .getText()
                                    .trim();


                    String status =
                            String.valueOf(
                                    cmbStatus
                                            .getSelectedItem()
                            );


                    // =========================================
                    // REQUIRED FIELDS
                    // =========================================

                    if (
                            productId.isEmpty()
                            || productName.isEmpty()
                            || priceText.isEmpty()
                            || stockText.isEmpty()
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "Please complete all product details.",

                                "AURORA | Missing Information",

                                JOptionPane.WARNING_MESSAGE
                        );


                        return;
                    }


                    // =========================================
                    // PRODUCT ID LENGTH
                    // =========================================

                    if (
                            productId.length() > 10
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "Product ID must contain 10 characters or fewer.",

                                "AURORA | Invalid Product ID",

                                JOptionPane.WARNING_MESSAGE
                        );


                        return;
                    }


                    // =========================================
                    // DUPLICATE CHECK FOR ADD ONLY
                    // =========================================

                    if (
                            !editing

                            && productController.productExists(
                                    productId
                            )
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "This Product ID already exists.",

                                "AURORA | Duplicate Product",

                                JOptionPane.WARNING_MESSAGE
                        );


                        return;
                    }


                    try {

                        double price =
                                Double.parseDouble(
                                        priceText
                                );


                        int stock =
                                Integer.parseInt(
                                        stockText
                                );


                        // =====================================
                        // PRICE VALIDATION
                        // =====================================

                        if (
                                price <= 0
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,

                                    "Price must be greater than 0.",

                                    "AURORA | Invalid Price",

                                    JOptionPane.WARNING_MESSAGE
                            );


                            return;
                        }


                        // =====================================
                        // STOCK VALIDATION
                        // =====================================

                        if (
                                stock < 0
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,

                                    "Stock cannot be negative.",

                                    "AURORA | Invalid Stock",

                                    JOptionPane.WARNING_MESSAGE
                            );


                            return;
                        }


                        boolean success;


                        // =====================================
                        // UPDATE
                        // =====================================

                        if (editing) {

    Product product =
            new Product(
                    productId,
                    productName,
                    category,
                    price,
                    stock,
                    status
            );

    success =
            productController.updateProduct(product);

}

                        // =====================================
                        // CREATE
                        // =====================================

                      else {

    Product product =
            new Product(
                    productId,
                    productName,
                    category,
                    price,
                    stock,
                    status
            );

    success =
            productController.addProduct(product);
}

                        // =====================================
                        // SUCCESS
                        // =====================================

                        if (
                                success
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,

                                    editing

                                            ? "Product updated successfully."

                                            : "Product added successfully to the AURORA collection.",

                                    editing

                                            ? "AURORA | Product Updated"

                                            : "AURORA | Product Added",

                                    JOptionPane.INFORMATION_MESSAGE
                            );


                            // REFRESH TABLE
                            loadProducts();


                            dialog.dispose();

                        } else {

                            JOptionPane.showMessageDialog(
                                    dialog,

                                    editing

                                            ? "The product could not be updated."

                                            : "The product could not be added.",

                                    "AURORA | Database Error",

                                    JOptionPane.ERROR_MESSAGE
                            );
                        }


                    } catch (
                            NumberFormatException ex
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "Enter valid numbers for Price and Stock.",

                                "AURORA | Invalid Number",

                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
        );


        dialog.setContentPane(
                background
        );


        dialog.setVisible(
                true
        );
    }


    // =========================================================
    // PRODUCTS HERO
    // =========================================================

    private JPanel createProductsHero() {

        ImageIcon imageIcon =
                null;


        try {

            java.net.URL imageURL =
                    getClass()
                            .getResource(
                                    "/auroraclothing/images/products-banner.png"
                            );


            if (
                    imageURL != null
            ) {

                imageIcon =
                        new ImageIcon(
                                imageURL
                        );

            } else {

                System.out.println(
                        "products-banner.png not found."
                );
            }


        } catch (
                Exception e
        ) {

            System.out.println(
                    "Could not load products banner."
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


                        int w =
                                getWidth();


                        int h =
                                getHeight();


                        Shape rounded =
                                new RoundRectangle2D.Double(
                                        0,
                                        0,
                                        w,
                                        h,
                                        26,
                                        26
                                );


                        g2.setClip(
                                rounded
                        );


                        // BASE GREEN
                        g2.setColor(
                                DARK_GREEN
                        );


                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );


                        // =====================================
                        // DRAW HIGH QUALITY IMAGE
                        // =====================================

                        if (
                                bannerImage != null
                        ) {

                            int imageWidth =
                                    bannerImage.getWidth(
                                            null
                                    );


                            int imageHeight =
                                    bannerImage.getHeight(
                                            null
                                    );


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
                                        (
                                                w
                                                - scaledWidth
                                        )
                                        / 2;


                                int y =
                                        (
                                                h
                                                - scaledHeight
                                        )
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


                        // =====================================
                        // SMOOTH GREEN LEFT BLEND
                        // =====================================

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


                        g2.setPaint(
                                blend
                        );


                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );


                        // =====================================
                        // LOWER SHADE
                        // =====================================

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


                        g2.setPaint(
                                lowerShade
                        );


                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );


                        // =====================================
                        // GOLD BORDER
                        // =====================================

                        g2.setClip(
                                null
                        );


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


        hero.setOpaque(
                false
        );


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


        // =====================================================
        // HERO TEXT
        // =====================================================

        JPanel heroText =
                new JPanel();


        heroText.setOpaque(
                false
        );


        heroText.setLayout(
                new BoxLayout(
                        heroText,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel smallTitle =
                new JLabel(
                        "AURORA COLLECTION"
                );


        smallTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );


        smallTitle.setForeground(
                GOLD
        );


        smallTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel mainTitle =
                new JLabel(
                        "Curate the Collection"
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
                        "Manage every AURORA piece with style, precision and confidence."
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


        accent.setForeground(
                GOLD
        );


        accent.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        heroText.add(
                Box.createVerticalGlue()
        );


        heroText.add(
                smallTitle
        );


        heroText.add(
                Box.createVerticalStrut(5)
        );


        heroText.add(
                mainTitle
        );


        heroText.add(
                Box.createVerticalStrut(5)
        );


        heroText.add(
                description
        );


        heroText.add(
                Box.createVerticalStrut(8)
        );


        heroText.add(
                accent
        );


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
    // STAT VALUE LABEL
    // =========================================================

    private JLabel createValueLabel(
            String value
    ) {

        JLabel valueLabel =
                new JLabel(
                        value
                );


        valueLabel.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        25
                )
        );


        valueLabel.setForeground(
                DEEP_GREEN
        );


        return valueLabel;
    }


    // =========================================================
    // SMALL STAT CARD
    // =========================================================

    private JPanel createSmallCard(
            String title,
            JLabel valueLabel,
            String subtitle
    ) {

        LuxuryCard card =
                new LuxuryCard();


        card.setLayout(
                new BorderLayout()
        );


        JPanel inside =
                new JPanel();


        inside.setOpaque(
                false
        );


        inside.setLayout(
                new BoxLayout(
                        inside,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );


        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );


        titleLabel.setForeground(
                GOLD
        );


        JLabel subtitleLabel =
                new JLabel(
                        subtitle
                );


        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );


        subtitleLabel.setForeground(
                MUTED
        );


        inside.add(
                titleLabel
        );


        inside.add(
                Box.createVerticalStrut(4)
        );


        inside.add(
                valueLabel
        );


        inside.add(
                Box.createVerticalStrut(2)
        );


        inside.add(
                subtitleLabel
        );


        card.add(
                inside,
                BorderLayout.CENTER
        );


        return card;
    }


    // =========================================================
    // FORM LABEL
    // =========================================================

    private JLabel createFormLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );


        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );


        label.setForeground(
                DEEP_GREEN
        );


        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        return label;
    }


    // =========================================================
    // FORM TEXT FIELD
    // =========================================================

    private JTextField createFormField() {

        JTextField field =
                new JTextField();


        field.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );


        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        38
                )
        );


        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );


        field.setForeground(
                TEXT
        );


        field.setBackground(
                IVORY
        );


        field.setCaretColor(
                DEEP_GREEN
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        205,
                                        199,
                                        180
                                )
                        ),

                        new EmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );


        return field;
    }


    // =========================================================
    // MINI FORM PANEL
    // =========================================================

    private JPanel createMiniFormPanel() {

        JPanel panel =
                new JPanel();


        panel.setOpaque(
                false
        );


        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );


        return panel;
    }


    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            JComboBox<String> combo
    ) {

        combo.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );


        combo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );


        combo.setBackground(
                IVORY
        );


        combo.setForeground(
                TEXT
        );


        combo.setFocusable(
                false
        );
    }


    // =========================================================
    // MAIN GREEN BUTTON
    // =========================================================

    private JButton createMainButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setBackground(
                DEEP_GREEN
        );


        button.setForeground(
                Color.WHITE
        );


        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );


        button.setFocusPainted(
                false
        );


        button.setBorderPainted(
                false
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private JButton createSecondaryButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setPreferredSize(
                new Dimension(
                        135,
                        38
                )
        );


        button.setBackground(
                IVORY
        );


        button.setForeground(
                DEEP_GREEN
        );


        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );


        button.setFocusPainted(
                false
        );


        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                205,
                                199,
                                180
                        )
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =========================================================
    // DELETE BUTTON
    // =========================================================

    private JButton createDeleteButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setPreferredSize(
                new Dimension(
                        135,
                        38
                )
        );


        button.setBackground(
                new Color(
                        115,
                        66,
                        58
                )
        );


        button.setForeground(
                Color.WHITE
        );


        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );


        button.setFocusPainted(
                false
        );


        button.setBorderPainted(
                false
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =========================================================
    // LUXURY SEARCH BAR
    // =========================================================

    private class LuxurySearchBar
            extends JPanel {


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


            int w =
                    getWidth();


            int h =
                    getHeight();


            // =================================================
            // SOFT SHADOW
            // =================================================

            g2.setColor(
                    new Color(
                            60,
                            55,
                            42,
                            18
                    )
            );


            g2.fillRoundRect(
                    2,
                    3,
                    w - 4,
                    h - 4,
                    24,
                    24
            );


            // =================================================
            // IVORY BODY
            // =================================================

            g2.setColor(
                    IVORY
            );


            g2.fillRoundRect(
                    0,
                    0,
                    w - 3,
                    h - 3,
                    24,
                    24
            );


            // =================================================
            // GOLD BORDER
            // =================================================

            if (
                    searchField != null
                    && searchField.hasFocus()
            ) {

                g2.setColor(
                        GOLD
                );


                g2.setStroke(
                        new BasicStroke(
                                1.6f
                        )
                );

            } else {

                g2.setColor(
                        new Color(
                                201,
                                164,
                                76,
                                110
                        )
                );


                g2.setStroke(
                        new BasicStroke(
                                1.0f
                        )
                );
            }


            g2.drawRoundRect(
                    0,
                    0,
                    w - 4,
                    h - 4,
                    24,
                    24
            );


            g2.dispose();


            super.paintComponent(
                    g
            );
        }
    }


    // =========================================================
    // SEARCH ICON
    // =========================================================

    private class SearchIcon
            extends JComponent {


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


            g2.setColor(
                    GOLD
            );


            g2.setStroke(
                    new BasicStroke(
                            2.0f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );


            // Magnifying glass circle
            g2.drawOval(
                    14,
                    13,
                    12,
                    12
            );


            // Magnifying glass handle
            g2.drawLine(
                    24,
                    24,
                    30,
                    30
            );


            g2.dispose();
        }
    }


    // =========================================================
    // PLACEHOLDER SEARCH FIELD
    // =========================================================

    private class PlaceholderTextField
            extends JTextField {


        private final String placeholder;


        public PlaceholderTextField(
                String placeholder
        ) {

            this.placeholder =
                    placeholder;
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(
                    g
            );


            if (
                    getText().isEmpty()
                    && !hasFocus()
            ) {

                Graphics2D g2 =
                        (Graphics2D) g.create();


                g2.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                );


                g2.setFont(
                        getFont()
                );


                g2.setColor(
                        new Color(
                                145,
                                140,
                                128
                        )
                );


                FontMetrics fm =
                        g2.getFontMetrics();


                int y =
                        (
                                getHeight()
                                - fm.getHeight()
                        )
                        / 2
                        + fm.getAscent();


                g2.drawString(
                        placeholder,
                        getInsets().left,
                        y
                );


                g2.dispose();
            }
        }
    }


    // =========================================================
    // LUXURY CARD
    // =========================================================

    private class LuxuryCard
            extends JPanel {


        public LuxuryCard() {

            setOpaque(
                    false
            );


            setBorder(
                    new EmptyBorder(
                            15,
                            18,
                            15,
                            18
                    )
            );
        }


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


            int w =
                    getWidth();


            int h =
                    getHeight();


            // SUBTLE SHADOW
            g2.setColor(
                    new Color(
                            70,
                            65,
                            50,
                            18
                    )
            );


            g2.fillRoundRect(
                    3,
                    4,
                    w - 5,
                    h - 5,
                    22,
                    22
            );


            // IVORY CARD
            g2.setColor(
                    IVORY
            );


            g2.fillRoundRect(
                    0,
                    0,
                    w - 5,
                    h - 5,
                    22,
                    22
            );


            // GOLD BORDER
            g2.setColor(
                    new Color(
                            201,
                            164,
                            76,
                            80
                    )
            );


            g2.drawRoundRect(
                    0,
                    0,
                    w - 6,
                    h - 6,
                    22,
                    22
            );


            g2.dispose();


            super.paintComponent(
                    g
            );
        }
    }
}