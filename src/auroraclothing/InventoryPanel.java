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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class InventoryPanel extends JPanel {

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

    private DefaultTableModel inventoryTableModel;

    private JTable inventoryTable;

    private TableRowSorter<DefaultTableModel> tableSorter;


    // =========================================================
    // SEARCH COMPONENTS
    // =========================================================

    private PlaceholderTextField searchField;

    private LuxurySearchBar searchBar;

    private JComboBox<String> stockFilter;


    // =========================================================
    // STATISTIC LABELS
    // =========================================================

    private JLabel totalItemsValue;

    private JLabel inStockValue;

    private JLabel lowStockValue;

    private JLabel outOfStockValue;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public InventoryPanel() {

        setLayout(
                new BorderLayout()
        );

        setBackground(
                CREAM
        );

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
        // TOP HEADER
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
                        "Inventory Management"
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
                        "Monitor stock levels and manage AURORA inventory."
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
        // TOP BUTTONS
        // =====================================================

        JPanel topButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        topButtons.setOpaque(false);


        JButton addButton =
                createMainButton(
                        "+  Add Inventory"
                );

        addButton.setPreferredSize(
                new Dimension(
                        145,
                        42
                )
        );


        JButton updateButton =
                createMainButton(
                        "↻  Update Stock"
                );

        updateButton.setPreferredSize(
                new Dimension(
                        150,
                        42
                )
        );


        addButton.addActionListener(
                e -> showInventoryDialog(null)
        );


        updateButton.addActionListener(
                e -> editSelectedInventory()
        );


        topButtons.add(addButton);

        topButtons.add(updateButton);


        top.add(
                topButtons,
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
        // INVENTORY HERO
        // =====================================================

        JPanel hero =
                createInventoryHero();

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
        // STATISTIC LABELS
        // =====================================================

        totalItemsValue =
                createValueLabel("0");

        inStockValue =
                createValueLabel("0");

        lowStockValue =
                createValueLabel("0");

        outOfStockValue =
                createValueLabel("0");


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
                createStatCard(
                        "TOTAL ITEMS",
                        totalItemsValue,
                        "Current inventory"
                )
        );


        stats.add(
                createStatCard(
                        "IN STOCK",
                        inStockValue,
                        "Healthy stock"
                )
        );


        stats.add(
                createStatCard(
                        "LOW STOCK",
                        lowStockValue,
                        "Needs attention"
                )
        );


        stats.add(
                createStatCard(
                        "OUT OF STOCK",
                        outOfStockValue,
                        "Restock required"
                )
        );


        content.add(stats);

        content.add(
                Box.createVerticalStrut(20)
        );


        // =====================================================
        // LUXURY SEARCH / FILTER AREA
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
        // SEARCH FIELD
        // =====================================================

        searchField =
                new PlaceholderTextField(
                        "Search inventory by ID, product, category or status..."
                );

        searchField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        searchField.setForeground(
                TEXT
        );

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
        // CLEAR BUTTON
        // =====================================================

        JButton clearButton =
                new JButton("×");

        clearButton.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        21
                )
        );

        clearButton.setForeground(
                MUTED
        );

        clearButton.setOpaque(false);

        clearButton.setContentAreaFilled(
                false
        );

        clearButton.setBorderPainted(
                false
        );

        clearButton.setFocusPainted(
                false
        );

        clearButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        clearButton.setToolTipText(
                "Clear search"
        );

        clearButton.setPreferredSize(
                new Dimension(
                        42,
                        42
                )
        );

        clearButton.setVisible(false);


        // =====================================================
        // SEARCH BAR
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
                        600,
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
                clearButton,
                BorderLayout.EAST
        );


        // =====================================================
        // STOCK FILTER
        // =====================================================

        stockFilter =
                new JComboBox<>(
                        new String[]{
                            "All Stock",
                            "In Stock",
                            "Low Stock",
                            "Out of Stock"
                        }
                );

        stockFilter.setPreferredSize(
                new Dimension(
                        155,
                        44
                )
        );

        stockFilter.setBackground(
                IVORY
        );

        stockFilter.setForeground(
                DEEP_GREEN
        );

        stockFilter.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        stockFilter.setFocusable(false);


        searchArea.add(
                searchBar,
                BorderLayout.CENTER
        );

        searchArea.add(
                stockFilter,
                BorderLayout.EAST
        );


        content.add(searchArea);

        content.add(
                Box.createVerticalStrut(15)
        );


        // =====================================================
        // INVENTORY TABLE
        // =====================================================

        String[] columns = {

            "ITEM ID",
            "PRODUCT",
            "CATEGORY",
            "QUANTITY",
            "REORDER LEVEL",
            "STATUS"
        };


        inventoryTableModel =
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


        inventoryTable =
                new JTable(
                        inventoryTableModel
                );


        // =====================================================
        // CENTER ALIGN TABLE
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        for (
                int i = 0;
                i < inventoryTable.getColumnCount();
                i++
        ) {

            inventoryTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }


        // =====================================================
        // TABLE STYLE
        // =====================================================

        inventoryTable.setRowHeight(42);

        inventoryTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        inventoryTable.setForeground(
                TEXT
        );

        inventoryTable.setBackground(
                IVORY
        );

        inventoryTable.setSelectionBackground(
                new Color(
                        223,
                        218,
                        196
                )
        );

        inventoryTable.setSelectionForeground(
                DEEP_GREEN
        );

        inventoryTable.setShowVerticalLines(
                false
        );

        inventoryTable.setGridColor(
                new Color(
                        225,
                        220,
                        205
                )
        );

        inventoryTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        // =====================================================
        // TABLE HEADER
        // =====================================================

        JTableHeader header =
                inventoryTable.getTableHeader();

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

        inventoryTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        inventoryTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(170);

        inventoryTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(120);

        inventoryTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(90);

        inventoryTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(120);

        inventoryTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(110);


        // =====================================================
        // TABLE SORTER
        // =====================================================

        tableSorter =
                new TableRowSorter<>(
                        inventoryTableModel
                );

        inventoryTable.setRowSorter(
                tableSorter
        );


        // =====================================================
        // LIVE SEARCH
        // =====================================================

        searchField
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            private void update() {

                                applyFilters();

                                clearButton.setVisible(
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

                                update();
                            }


                            @Override
                            public void removeUpdate(
                                    DocumentEvent e
                            ) {

                                update();
                            }


                            @Override
                            public void changedUpdate(
                                    DocumentEvent e
                            ) {

                                update();
                            }
                        }
                );


        // =====================================================
        // FILTER ACTION
        // =====================================================

        stockFilter.addActionListener(
                e -> applyFilters()
        );


        // =====================================================
        // CLEAR SEARCH
        // =====================================================

        clearButton.addActionListener(
                e -> {

                    searchField.setText("");

                    searchField.requestFocusInWindow();
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
        // TABLE SCROLL
        // =====================================================

        JScrollPane scroll =
                new JScrollPane(
                        inventoryTable
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
                .setBackground(
                        IVORY
                );

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
        // BOTTOM ACTION BUTTONS
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
                e -> editSelectedInventory()
        );


        deleteButton.addActionListener(
                e -> deleteSelectedInventory()
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

        pageScroll
                .getViewport()
                .setOpaque(false);

        pageScroll
                .getVerticalScrollBar()
                .setUnitIncrement(14);


        add(
                pageScroll,
                BorderLayout.CENTER
        );


        // =====================================================
        // LOAD REAL MYSQL INVENTORY
        // =====================================================

        loadInventory();
    }


    // =========================================================
    // LOAD INVENTORY FROM MYSQL
    // =========================================================

    private void loadInventory() {

        inventoryTableModel.setRowCount(0);


        List<Object[]> inventoryList =
                InventoryDAO.getAllInventory();


        int totalQuantity = 0;

        int inStock = 0;

        int lowStock = 0;

        int outOfStock = 0;


        for (
                Object[] item :
                inventoryList
        ) {

            int quantity =
                    ((Number) item[4])
                            .intValue();


            String status =
                    String.valueOf(
                            item[6]
                    );


            // =========================================
            // TABLE DISPLAY
            // =========================================

            Object[] displayRow = {

                item[0],       // ITEM ID

                item[2],       // PRODUCT NAME

                item[3],       // CATEGORY

                item[4],       // QUANTITY

                item[5],       // REORDER LEVEL

                item[6]        // STATUS
            };


            inventoryTableModel.addRow(
                    displayRow
            );


            // =========================================
            // STATISTICS
            // =========================================

            totalQuantity +=
                    quantity;


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

            } else if (
                    status.equalsIgnoreCase(
                            "Out of Stock"
                    )
            ) {

                outOfStock++;
            }
        }


        totalItemsValue.setText(
                String.valueOf(
                        totalQuantity
                )
        );


        inStockValue.setText(
                String.valueOf(
                        inStock
                )
        );


        lowStockValue.setText(
                String.valueOf(
                        lowStock
                )
        );


        outOfStockValue.setText(
                String.valueOf(
                        outOfStock
                )
        );


        applyFilters();
    }


    // =========================================================
    // SEARCH + STATUS FILTER
    // =========================================================

    private void applyFilters() {

        if (
                tableSorter == null
                || searchField == null
                || stockFilter == null
        ) {

            return;
        }


        final String searchText =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();


        final String selectedStatus =
                String.valueOf(
                        stockFilter
                                .getSelectedItem()
                );


        tableSorter.setRowFilter(

                new RowFilter<
                        DefaultTableModel,
                        Integer
                        >() {

                    @Override
                    public boolean include(
                            Entry<
                                    ? extends DefaultTableModel,
                                    ? extends Integer
                                    > entry
                    ) {

                        // =====================================
                        // SEARCH MATCH
                        // =====================================

                        boolean searchMatches =
                                searchText.isEmpty();


                        if (
                                !searchMatches
                        ) {

                            for (
                                    int i = 0;
                                    i < entry.getValueCount();
                                    i++
                            ) {

                                String value =
                                        String.valueOf(
                                                entry.getValue(i)
                                        )
                                                .toLowerCase();


                                if (
                                        value.contains(
                                                searchText
                                        )
                                ) {

                                    searchMatches =
                                            true;

                                    break;
                                }
                            }
                        }


                        // =====================================
                        // STATUS MATCH
                        // =====================================

                        boolean statusMatches;


                        if (
                                selectedStatus.equals(
                                        "All Stock"
                                )
                        ) {

                            statusMatches =
                                    true;

                        } else {

                            String rowStatus =
                                    String.valueOf(
                                            entry.getValue(5)
                                    );


                            statusMatches =
                                    rowStatus.equalsIgnoreCase(
                                            selectedStatus
                                    );
                        }


                        return searchMatches
                                && statusMatches;
                    }
                }
        );
    }


    // =========================================================
    // EDIT SELECTED INVENTORY
    // =========================================================

    private void editSelectedInventory() {

        int selectedRow =
                inventoryTable
                        .getSelectedRow();


        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select an inventory item to update.",

                    "AURORA | Select Inventory",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int modelRow =
                inventoryTable
                        .convertRowIndexToModel(
                                selectedRow
                        );


        String itemId =
                inventoryTableModel
                        .getValueAt(
                                modelRow,
                                0
                        )
                        .toString();


        showInventoryDialog(
                itemId
        );
    }


    // =========================================================
    // DELETE SELECTED INVENTORY
    // =========================================================

    private void deleteSelectedInventory() {

        int selectedRow =
                inventoryTable
                        .getSelectedRow();


        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select an inventory item to delete.",

                    "AURORA | Select Inventory",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int modelRow =
                inventoryTable
                        .convertRowIndexToModel(
                                selectedRow
                        );


        String itemId =
                inventoryTableModel
                        .getValueAt(
                                modelRow,
                                0
                        )
                        .toString();


        String productName =
                inventoryTableModel
                        .getValueAt(
                                modelRow,
                                1
                        )
                        .toString();


        int choice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Delete inventory record "
                        + itemId
                        + " for "
                        + productName
                        + "?\n\n"
                        + "This action cannot be undone.",

                        "AURORA | Delete Inventory",

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
                InventoryDAO.deleteInventory(
                        itemId
                );


        if (
                deleted
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Inventory record deleted successfully.",

                    "AURORA | Inventory Deleted",

                    JOptionPane.INFORMATION_MESSAGE
            );


            loadInventory();

        } else {

            JOptionPane.showMessageDialog(
                    this,

                    "The inventory record could not be deleted.",

                    "AURORA | Delete Failed",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // ADD / EDIT INVENTORY DIALOG
    // =========================================================

    private void showInventoryDialog(
            String itemIdToEdit
    ) {

        boolean editing =
                itemIdToEdit != null;


        Object[] existingInventory =
                null;


        if (
                editing
        ) {

            existingInventory =
                    InventoryDAO.getInventoryById(
                            itemIdToEdit
                    );


            if (
                    existingInventory == null
            ) {

                JOptionPane.showMessageDialog(
                        this,

                        "The selected inventory record could not be loaded.",

                        "AURORA | Inventory Error",

                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }


        // =====================================================
        // LOAD PRODUCTS
        // =====================================================

        List<Object[]> products =
                InventoryDAO.getProducts();


        if (
                products.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "No products are available.\n"
                    + "Please add products before creating inventory records.",

                    "AURORA | No Products",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // DIALOG
        // =====================================================

        JDialog dialog =
                new JDialog(

                        SwingUtilities
                                .getWindowAncestor(
                                        this
                                ),

                        editing
                                ? "AURORA | Update Inventory"
                                : "AURORA | Add Inventory",

                        Dialog.ModalityType
                                .APPLICATION_MODAL
                );


        dialog.setSize(
                530,
                570
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
        // DIALOG HEADER
        // =====================================================

        JPanel dialogHeader =
                new JPanel();

        dialogHeader.setOpaque(false);

        dialogHeader.setLayout(
                new BoxLayout(
                        dialogHeader,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel smallTitle =
                new JLabel(
                        "A U R O R A   I N V E N T O R Y"
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
                                ? "Update Stock"
                                : "Add Inventory Item"
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
                                ? "Keep AURORA stock levels accurate and organized."
                                : "Add a product to the AURORA inventory."
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

        form.setOpaque(false);

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
        // ITEM ID
        // =====================================================

        form.add(
                createFormLabel(
                        "Item ID"
                ),
                gbc
        );


        gbc.gridy++;


        JTextField txtItemId =
                createFormField();


        form.add(
                txtItemId,
                gbc
        );


        // =====================================================
        // PRODUCT
        // =====================================================

        gbc.gridy++;


        form.add(
                createFormLabel(
                        "Product"
                ),
                gbc
        );


        gbc.gridy++;


        JComboBox<ProductItem> cmbProduct =
                new JComboBox<>();


        for (
                Object[] product :
                products
        ) {

            cmbProduct.addItem(
                    new ProductItem(

                            String.valueOf(
                                    product[0]
                            ),

                            String.valueOf(
                                    product[1]
                            ),

                            String.valueOf(
                                    product[2]
                            )
                    )
            );
        }


        cmbProduct.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        cmbProduct.setBackground(
                IVORY
        );

        cmbProduct.setForeground(
                TEXT
        );

        cmbProduct.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        cmbProduct.setFocusable(
                false
        );


        form.add(
                cmbProduct,
                gbc
        );


        // =====================================================
        // QUANTITY + REORDER LEVEL
        // =====================================================

        gbc.gridy++;


        JPanel quantityPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                0
                        )
                );

        quantityPanel.setOpaque(false);


        JPanel quantityBox =
                createMiniFormPanel();


        JLabel lblQuantity =
                createFormLabel(
                        "Quantity"
                );


        JTextField txtQuantity =
                createFormField();


        quantityBox.add(
                lblQuantity
        );

        quantityBox.add(
                Box.createVerticalStrut(5)
        );

        quantityBox.add(
                txtQuantity
        );


        JPanel reorderBox =
                createMiniFormPanel();


        JLabel lblReorder =
                createFormLabel(
                        "Reorder Level"
                );


        JTextField txtReorder =
                createFormField();


        reorderBox.add(
                lblReorder
        );

        reorderBox.add(
                Box.createVerticalStrut(5)
        );

        reorderBox.add(
                txtReorder
        );


        quantityPanel.add(
                quantityBox
        );

        quantityPanel.add(
                reorderBox
        );


        form.add(
                quantityPanel,
                gbc
        );


        // =====================================================
        // AUTOMATIC STATUS
        // =====================================================

        gbc.gridy++;


        form.add(
                createFormLabel(
                        "Automatic Stock Status"
                ),
                gbc
        );


        gbc.gridy++;


        JLabel statusPreview =
                new JLabel(
                        "Enter quantity and reorder level"
                );

        statusPreview.setOpaque(true);

        statusPreview.setBackground(
                IVORY
        );

        statusPreview.setForeground(
                MUTED
        );

        statusPreview.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        statusPreview.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        205,
                                        199,
                                        180
                                )
                        ),

                        new EmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );


        form.add(
                statusPreview,
                gbc
        );


        background.add(
                form,
                BorderLayout.CENTER
        );


        // =====================================================
        // AUTOMATIC STATUS PREVIEW
        // =====================================================

        DocumentListener statusListener =
                new DocumentListener() {

                    private void updateStatus() {

                        try {

                            int quantity =
                                    Integer.parseInt(
                                            txtQuantity
                                                    .getText()
                                                    .trim()
                                    );


                            int reorder =
                                    Integer.parseInt(
                                            txtReorder
                                                    .getText()
                                                    .trim()
                                    );


                            String status =
                                    InventoryDAO
                                            .calculateStatus(
                                                    quantity,
                                                    reorder
                                            );


                            statusPreview.setText(
                                    status
                            );


                            if (
                                    status.equals(
                                            "In Stock"
                                    )
                            ) {

                                statusPreview.setForeground(
                                        SOFT_GREEN
                                );

                            } else if (
                                    status.equals(
                                            "Low Stock"
                                    )
                            ) {

                                statusPreview.setForeground(
                                        GOLD
                                );

                            } else {

                                statusPreview.setForeground(
                                        new Color(
                                                145,
                                                70,
                                                60
                                        )
                                );
                            }


                        } catch (
                                NumberFormatException ex
                        ) {

                            statusPreview.setText(
                                    "Enter quantity and reorder level"
                            );

                            statusPreview.setForeground(
                                    MUTED
                            );
                        }
                    }


                    @Override
                    public void insertUpdate(
                            DocumentEvent e
                    ) {

                        updateStatus();
                    }


                    @Override
                    public void removeUpdate(
                            DocumentEvent e
                    ) {

                        updateStatus();
                    }


                    @Override
                    public void changedUpdate(
                            DocumentEvent e
                    ) {

                        updateStatus();
                    }
                };


        txtQuantity
                .getDocument()
                .addDocumentListener(
                        statusListener
                );


        txtReorder
                .getDocument()
                .addDocumentListener(
                        statusListener
                );


        // =====================================================
        // LOAD EXISTING VALUES WHEN EDITING
        // =====================================================

        final String originalProductId;


        if (
                editing
        ) {

            txtItemId.setText(
                    String.valueOf(
                            existingInventory[0]
                    )
            );


            originalProductId =
                    String.valueOf(
                            existingInventory[1]
                    );


            txtQuantity.setText(
                    String.valueOf(
                            existingInventory[4]
                    )
            );


            txtReorder.setText(
                    String.valueOf(
                            existingInventory[5]
                    )
            );


            txtItemId.setEditable(
                    false
            );

            txtItemId.setBackground(
                    new Color(
                            232,
                            230,
                            220
                    )
            );


            // =============================================
            // SELECT CURRENT PRODUCT
            // =============================================

            for (
                    int i = 0;
                    i < cmbProduct.getItemCount();
                    i++
            ) {

                ProductItem product =
                        cmbProduct.getItemAt(i);


                if (
                        product.getProductId()
                                .equals(
                                        originalProductId
                                )
                ) {

                    cmbProduct.setSelectedIndex(
                            i
                    );

                    break;
                }
            }

        } else {

            originalProductId =
                    null;
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

        buttons.setOpaque(false);


        JButton cancelButton =
                createSecondaryButton(
                        "Cancel"
                );


        JButton saveButton =
                createMainButton(

                        editing
                                ? "Save Changes"
                                : "Add Inventory"
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
        // SAVE INVENTORY
        // =====================================================

        saveButton.addActionListener(
                e -> {

                    String itemId =
                            txtItemId
                                    .getText()
                                    .trim()
                                    .toUpperCase();


                    ProductItem selectedProduct =
                            (ProductItem)
                            cmbProduct
                                    .getSelectedItem();


                    String quantityText =
                            txtQuantity
                                    .getText()
                                    .trim();


                    String reorderText =
                            txtReorder
                                    .getText()
                                    .trim();


                    // =========================================
                    // REQUIRED FIELDS
                    // =========================================

                    if (
                            itemId.isEmpty()
                            || selectedProduct == null
                            || quantityText.isEmpty()
                            || reorderText.isEmpty()
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "Please complete all inventory details.",

                                "AURORA | Missing Information",

                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }


                    // =========================================
                    // DUPLICATE ITEM ID
                    // =========================================

                    if (
                            !editing
                            && InventoryDAO
                                    .inventoryExists(
                                            itemId
                                    )
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "This Item ID already exists.",

                                "AURORA | Duplicate Item",

                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }


                    String selectedProductId =
                            selectedProduct
                                    .getProductId();


                    // =========================================
                    // PRODUCT ALREADY IN INVENTORY
                    // =========================================

                    if (
                            !editing
                            && InventoryDAO
                                    .productAlreadyInInventory(
                                            selectedProductId
                                    )
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "This product already has an inventory record.\n"
                                + "Select the existing row and use Update Stock instead.",

                                "AURORA | Product Already Added",

                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }


                    // =========================================
                    // EDITING + PRODUCT CHANGED
                    // =========================================

                    if (
                            editing
                            && !selectedProductId
                                    .equals(
                                            originalProductId
                                    )
                            && InventoryDAO
                                    .productAlreadyInInventory(
                                            selectedProductId
                                    )
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "The selected product already has another inventory record.",

                                "AURORA | Duplicate Product",

                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }


                    try {

                        int quantity =
                                Integer.parseInt(
                                        quantityText
                                );


                        int reorderLevel =
                                Integer.parseInt(
                                        reorderText
                                );


                        // =====================================
                        // QUANTITY VALIDATION
                        // =====================================

                        if (
                                quantity < 0
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,

                                    "Quantity cannot be negative.",

                                    "AURORA | Invalid Quantity",

                                    JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }


                        // =====================================
                        // REORDER LEVEL VALIDATION
                        // =====================================

                        if (
                                reorderLevel < 0
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,

                                    "Reorder level cannot be negative.",

                                    "AURORA | Invalid Reorder Level",

                                    JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }


                        // =====================================
                        // AUTOMATIC STATUS
                        // =====================================

                        String status =
                                InventoryDAO
                                        .calculateStatus(
                                                quantity,
                                                reorderLevel
                                        );


                        boolean success;


                        // =====================================
                        // UPDATE
                        // =====================================

                        if (
                                editing
                        ) {

                            success =
                                    InventoryDAO
                                            .updateInventory(

                                                    itemId,

                                                    selectedProductId,

                                                    quantity,

                                                    reorderLevel,

                                                    status
                                            );

                        }

                        // =====================================
                        // CREATE
                        // =====================================

                        else {

                            success =
                                    InventoryDAO
                                            .addInventory(

                                                    itemId,

                                                    selectedProductId,

                                                    quantity,

                                                    reorderLevel,

                                                    status
                                            );
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
                                            ? "Inventory updated successfully."
                                            : "Inventory item added successfully.",

                                    editing
                                            ? "AURORA | Inventory Updated"
                                            : "AURORA | Inventory Added",

                                    JOptionPane.INFORMATION_MESSAGE
                            );


                            loadInventory();


                            dialog.dispose();

                        } else {

                            JOptionPane.showMessageDialog(
                                    dialog,

                                    editing
                                            ? "The inventory record could not be updated."
                                            : "The inventory record could not be added.",

                                    "AURORA | Database Error",

                                    JOptionPane.ERROR_MESSAGE
                            );
                        }


                    } catch (
                            NumberFormatException ex
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,

                                "Quantity and Reorder Level must contain valid whole numbers.",

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
    // INVENTORY HERO BANNER
    // =========================================================

    private JPanel createInventoryHero() {

        ImageIcon imageIcon =
                null;


        try {

            java.net.URL imageURL =
                    getClass()
                            .getResource(
                                    "/auroraclothing/images/inventory-banner.png"
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
                        "inventory-banner.png not found."
                );
            }

        } catch (
                Exception e
        ) {

            System.out.println(
                    "Could not load inventory banner."
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


                        // =====================================
                        // HIGH QUALITY IMAGE RENDERING
                        // =====================================

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


                        // =====================================
                        // ROUNDED HERO SHAPE
                        // =====================================

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


                        // =====================================
                        // BASE GREEN
                        // =====================================

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
                        // DRAW IMAGE
                        // =====================================

                        if (
                                bannerImage != null
                        ) {

                            int imageWidth =
                                    bannerImage
                                            .getWidth(
                                                    null
                                            );


                            int imageHeight =
                                    bannerImage
                                            .getHeight(
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
                        // LEFT GREEN BLEND
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

        heroText.setOpaque(false);

        heroText.setLayout(
                new BoxLayout(
                        heroText,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel smallTitle =
                new JLabel(
                        "AURORA INVENTORY"
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
                        "Stock, Organized Beautifully"
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
                        "Keep every AURORA item organized, available and ready."
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
    // CREATE STAT VALUE LABEL
    // =========================================================

    private JLabel createValueLabel(
            String value
    ) {

        JLabel label =
                new JLabel(
                        value
                );

        label.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        24
                )
        );

        label.setForeground(
                DEEP_GREEN
        );

        return label;
    }


    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
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

        inside.setOpaque(false);

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
    // FORM FIELD
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

        panel.setOpaque(false);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        return panel;
    }


    // =========================================================
    // MAIN BUTTON
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
    // PRODUCT ITEM FOR COMBO BOX
    // =========================================================

    private static class ProductItem {

        private final String productId;

        private final String productName;

        private final String category;


        public ProductItem(
                String productId,
                String productName,
                String category
        ) {

            this.productId =
                    productId;

            this.productName =
                    productName;

            this.category =
                    category;
        }


        public String getProductId() {

            return productId;
        }


        @Override
        public String toString() {

            return productId
                    + "  —  "
                    + productName
                    + "  |  "
                    + category;
        }
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


            // =============================================
            // SOFT SHADOW
            // =============================================

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


            // =============================================
            // IVORY BODY
            // =============================================

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


            // =============================================
            // BORDER
            // =============================================

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


            g2.drawOval(
                    14,
                    13,
                    12,
                    12
            );


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


            // =============================================
            // SHADOW
            // =============================================

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


            // =============================================
            // IVORY CARD
            // =============================================

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


            // =============================================
            // GOLD BORDER
            // =============================================

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