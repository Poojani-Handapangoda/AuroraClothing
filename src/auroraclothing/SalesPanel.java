/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class SalesPanel extends JPanel {

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
    // MAIN SALES TABLE
    // =========================================================

    private DefaultTableModel salesTableModel;
    private JTable salesTable;
    private TableRowSorter<DefaultTableModel> tableSorter;

    // =========================================================
    // SEARCH
    // =========================================================

    private PlaceholderTextField searchField;
    private LuxurySearchBar searchBar;
    private JComboBox<String> salesFilter;

    // =========================================================
    // STATISTICS
    // =========================================================

    private JLabel totalSalesValue;
    private JLabel todaySalesValue;
    private JLabel totalOrdersValue;
    private JLabel averageOrderValue;

    // =========================================================
    // CUSTOMER LOOKUP
    // =========================================================

    private final Map<String, String> customerNames =
            new HashMap<>();

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public SalesPanel() {

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
    // CREATE UI
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
                        "Sales Management"
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
                        "Track AURORA sales, customer orders and payments."
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

        titleArea.add(
                Box.createVerticalStrut(4)
        );

        titleArea.add(subtitle);

        top.add(
                titleArea,
                BorderLayout.WEST
        );

        // =====================================================
        // NEW SALE BUTTON
        // =====================================================

        JButton newSale =
                createMainButton(
                        "+  New Sale"
                );

        newSale.setPreferredSize(
                new Dimension(
                        145,
                        42
                )
        );

        newSale.addActionListener(
                e -> showSaleDialog(null)
        );

        top.add(
                newSale,
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
        // HERO
        // =====================================================

        JPanel hero =
                createSalesHero();

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
        // STAT LABELS
        // =====================================================

        totalSalesValue =
                createValueLabel(
                        "LKR 0.00"
                );

        todaySalesValue =
                createValueLabel(
                        "LKR 0.00"
                );

        totalOrdersValue =
                createValueLabel(
                        "0"
                );

        averageOrderValue =
                createValueLabel(
                        "LKR 0.00"
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
                        "TOTAL SALES",
                        totalSalesValue,
                        "This month"
                )
        );

        stats.add(
                createStatCard(
                        "TODAY'S SALES",
                        todaySalesValue,
                        "Today's revenue"
                )
        );

        stats.add(
                createStatCard(
                        "TOTAL ORDERS",
                        totalOrdersValue,
                        "Recorded orders"
                )
        );

        stats.add(
                createStatCard(
                        "AVG. ORDER VALUE",
                        averageOrderValue,
                        "Per customer order"
                )
        );

        content.add(stats);

        content.add(
                Box.createVerticalStrut(20)
        );

        // =====================================================
        // SEARCH AREA
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

        searchField =
                new PlaceholderTextField(
                        "Search by order ID, customer, product, payment or status..."
                );

        searchField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        searchField.setForeground(TEXT);
        searchField.setCaretColor(DEEP_GREEN);
        searchField.setOpaque(false);

        searchField.setBorder(
                new EmptyBorder(
                        0,
                        4,
                        0,
                        4
                )
        );

        SearchIcon searchIcon =
                new SearchIcon();

        searchIcon.setPreferredSize(
                new Dimension(
                        42,
                        42
                )
        );

        JButton clearButton =
                new JButton("×");

        clearButton.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        21
                )
        );

        clearButton.setForeground(MUTED);

        clearButton.setOpaque(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);

        clearButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        clearButton.setPreferredSize(
                new Dimension(
                        42,
                        42
                )
        );

        clearButton.setVisible(false);

        searchBar =
                new LuxurySearchBar();

        searchBar.setLayout(
                new BorderLayout(
                        2,
                        0
                )
        );

        searchBar.setOpaque(false);

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
        // FILTER
        // =====================================================

        salesFilter =
                new JComboBox<>(
                        new String[]{
                            "All Sales",
                            "Completed",
                            "Processing",
                            "Pending"
                        }
                );

        salesFilter.setPreferredSize(
                new Dimension(
                        150,
                        44
                )
        );

        salesFilter.setBackground(IVORY);
        salesFilter.setForeground(DEEP_GREEN);

        salesFilter.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        salesFilter.setFocusable(false);

        searchArea.add(
                searchBar,
                BorderLayout.CENTER
        );

        searchArea.add(
                salesFilter,
                BorderLayout.EAST
        );

        content.add(searchArea);

        content.add(
                Box.createVerticalStrut(15)
        );

        // =====================================================
        // SALES TABLE
        // =====================================================

        String[] columns = {

            "ORDER ID",
            "CUSTOMER",
            "DATE",
            "ITEMS",
            "PAYMENT",
            "TOTAL",
            "STATUS"
        };

        salesTableModel =
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

        salesTable =
                new JTable(
                        salesTableModel
                );

        // =====================================================
        // CENTER TABLE
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (
                int i = 0;
                i < salesTable.getColumnCount();
                i++
        ) {

            salesTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        salesTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        salesTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(180);

        salesTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(100);

        salesTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(70);

        salesTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(100);

        salesTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(125);

        salesTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(100);

        // =====================================================
        // TABLE STYLE
        // =====================================================

        salesTable.setRowHeight(42);

        salesTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        salesTable.setForeground(TEXT);
        salesTable.setBackground(IVORY);

        salesTable.setSelectionBackground(
                new Color(
                        223,
                        218,
                        196
                )
        );

        salesTable.setSelectionForeground(
                DEEP_GREEN
        );

        salesTable.setShowVerticalLines(false);

        salesTable.setGridColor(
                new Color(
                        225,
                        220,
                        205
                )
        );

        salesTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JTableHeader header =
                salesTable.getTableHeader();

        header.setPreferredSize(
                new Dimension(
                        header.getWidth(),
                        42
                )
        );

        header.setBackground(DEEP_GREEN);
        header.setForeground(Color.WHITE);

        header.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        header.setReorderingAllowed(false);

        DefaultTableCellRenderer headerRenderer =
                (DefaultTableCellRenderer)
                header.getDefaultRenderer();

        headerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        // =====================================================
        // SORTER
        // =====================================================

        tableSorter =
                new TableRowSorter<>(
                        salesTableModel
                );

        salesTable.setRowSorter(
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

        salesFilter.addActionListener(
                e -> applyFilters()
        );

        clearButton.addActionListener(
                e -> {

                    searchField.setText("");

                    searchField
                            .requestFocusInWindow();
                }
        );

        searchField.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(
                            FocusEvent e
                    ) {

                        searchBar.repaint();
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e
                    ) {

                        searchBar.repaint();
                    }
                }
        );

        // =====================================================
        // DOUBLE CLICK EDIT
        // =====================================================

        salesTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (
                                e.getClickCount()
                                == 2
                        ) {

                            editSelectedSale();
                        }
                    }
                }
        );

        JScrollPane scroll =
                new JScrollPane(
                        salesTable
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

        scroll
                .getViewport()
                .setBackground(IVORY);

        scroll.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        scroll.setPreferredSize(
                new Dimension(
                        900,
                        220
                )
        );

        scroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        250
                )
        );

        content.add(scroll);

        content.add(
                Box.createVerticalStrut(12)
        );

        // =====================================================
        // EDIT + DELETE BUTTONS
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
                e -> editSelectedSale()
        );

        deleteButton.addActionListener(
                e -> deleteSelectedSale()
                
        );

        actionPanel.add(editButton);
        actionPanel.add(deleteButton);

        content.add(actionPanel);

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

        loadSales();
    }

    // =========================================================
    // LOAD SALES
    // =========================================================

    private void loadSales() {

        salesTableModel.setRowCount(0);

        loadCustomerNames();

        List<Object[]> sales =
                SalesDAO.getAllSales();

        BigDecimal monthRevenue =
                BigDecimal.ZERO;

        BigDecimal todayRevenue =
                BigDecimal.ZERO;

        BigDecimal allRevenue =
                BigDecimal.ZERO;

        int totalOrders = 0;

        LocalDate today =
                LocalDate.now();

        YearMonth currentMonth =
                YearMonth.from(today);

        for (
                Object[] sale :
                sales
        ) {

            String orderId =
                    String.valueOf(
                            sale[0]
                    );

            String customerId =
                    String.valueOf(
                            sale[1]
                    );

            Date saleDate =
                    sale[2] instanceof Date
                            ? (Date) sale[2]
                            : null;

            String payment =
                    String.valueOf(
                            sale[3]
                    );

            BigDecimal total =
                    sale[4] instanceof BigDecimal
                            ? (BigDecimal) sale[4]
                            : BigDecimal.ZERO;

            String status =
                    String.valueOf(
                            sale[5]
                    );

            String customerName =
                    customerNames.getOrDefault(
                            customerId,
                            "Unknown Customer"
                    );

            List<Object[]> items =
                    SalesItemDAO
                            .getItemsByOrderId(
                                    orderId
                            );

            int totalItemQuantity = 0;

            for (
                    Object[] item :
                    items
            ) {

                totalItemQuantity +=
                        ((Number) item[3])
                                .intValue();
            }

            salesTableModel.addRow(
                    new Object[]{
                        orderId,
                        customerId
                        + " - "
                        + customerName,
                        saleDate == null
                                ? ""
                                : saleDate.toString(),
                        totalItemQuantity,
                        payment,
                        formatMoney(total),
                        status
                    }
            );

            totalOrders++;

            allRevenue =
                    allRevenue.add(
                            total
                    );

            if (
                    saleDate != null
            ) {

                LocalDate localDate =
                        saleDate.toLocalDate();

                if (
                        YearMonth
                                .from(localDate)
                                .equals(currentMonth)
                ) {

                    monthRevenue =
                            monthRevenue.add(
                                    total
                            );
                }

                if (
                        localDate.equals(
                                today
                        )
                ) {

                    todayRevenue =
                            todayRevenue.add(
                                    total
                            );
                }
            }
        }

        BigDecimal average =
                totalOrders == 0
                        ? BigDecimal.ZERO
                        : allRevenue.divide(
                                BigDecimal.valueOf(
                                        totalOrders
                                ),
                                2,
                                RoundingMode.HALF_UP
                        );

        totalSalesValue.setText(
                formatMoney(
                        monthRevenue
                )
        );

        todaySalesValue.setText(
                formatMoney(
                        todayRevenue
                )
        );

        totalOrdersValue.setText(
                String.valueOf(
                        totalOrders
                )
        );

        averageOrderValue.setText(
                formatMoney(
                        average
                )
        );

        applyFilters();
    }

    // =========================================================
    // CUSTOMER NAMES
    // =========================================================

    private void loadCustomerNames() {

        customerNames.clear();

        List<Object[]> customers =
                CustomerDAO.getAllCustomers();

        for (
                Object[] customer :
                customers
        ) {

            if (
                    customer[0] != null
                    && customer[1] != null
            ) {

                customerNames.put(
                        customer[0].toString(),
                        customer[1].toString()
                );
            }
        }
    }

    // =========================================================
    // SEARCH + FILTER
    // =========================================================

    private void applyFilters() {

        if (
                tableSorter == null
                || searchField == null
                || salesFilter == null
        ) {

            return;
        }

        final String searchText =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        final String filter =
                String.valueOf(
                        salesFilter
                                .getSelectedItem()
                );

        tableSorter.setRowFilter(
                new RowFilter<
                        DefaultTableModel,
                        Integer>() {

                    @Override
                    public boolean include(
                            Entry<
                                    ? extends DefaultTableModel,
                                    ? extends Integer
                                    > entry
                    ) {

                        boolean searchMatch =
                                searchText.isEmpty();

                        if (!searchMatch) {

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

                                    searchMatch = true;
                                    break;
                                }
                            }
                        }

                        boolean filterMatch =
                                true;

                        if (
                                !"All Sales"
                                        .equals(filter)
                        ) {

                            String status =
                                    String.valueOf(
                                            entry.getValue(6)
                                    );

                            filterMatch =
                                    status.equalsIgnoreCase(
                                            filter
                                    );
                        }

                        return searchMatch
                                && filterMatch;
                    }
                }
        );
    }

    // =========================================================
    // EDIT SELECTED SALE
    // =========================================================

    private void editSelectedSale() {

        int selectedRow =
                salesTable.getSelectedRow();

        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a sale to edit.",
                    "AURORA | Select Sale",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
                salesTable
                        .convertRowIndexToModel(
                                selectedRow
                        );

        String orderId =
                salesTableModel
                        .getValueAt(
                                modelRow,
                                0
                        )
                        .toString();

        showSaleDialog(
                orderId
        );
    }

    // =========================================================
// DELETE SELECTED SALE
// =========================================================

private void deleteSelectedSale() {
    // =========================================================
// SECURITY - STAFF CANNOT DELETE SALES
// =========================================================

if (!UserSession.canManageSensitiveRecords()) {

    JOptionPane.showMessageDialog(
            this,
            "You do not have permission to delete sales.\n"
            + "Please contact an Administrator or Manager.",
            "AURORA | Access Denied",
            JOptionPane.WARNING_MESSAGE
    );

    return;
}

    int selectedRow =
            salesTable.getSelectedRow();

    if (selectedRow == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a sale to delete.",
                "AURORA | Select Sale",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    int modelRow =
            salesTable.convertRowIndexToModel(
                    selectedRow
            );

    String orderId =
            salesTableModel
                    .getValueAt(
                            modelRow,
                            0
                    )
                    .toString();

    int choice =
            JOptionPane.showConfirmDialog(
                    this,
                    "Delete order "
                    + orderId
                    + " and all of its items?",
                    "AURORA | Delete Sale",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

    if (choice != JOptionPane.YES_OPTION) {

        return;
    }


    // =====================================================
    // STEP 1 - RESTORE STOCK IF THIS SALE USED INVENTORY
    // =====================================================

    boolean stockWasApplied =
            SalesDAO.isStockApplied(
                    orderId
            );

    if (stockWasApplied) {

        boolean restored =
                restoreStockForSale(
                        orderId
                );

        if (!restored) {

            JOptionPane.showMessageDialog(
                    this,
                    "The sale could not be deleted because "
                    + "its stock could not be restored.",
                    "AURORA | Stock Restore Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }
    }


    // =====================================================
    // STEP 2 - DELETE SALE ITEMS
    // =====================================================

    boolean itemsDeleted =
            SalesItemDAO.deleteItemsByOrderId(
                    orderId
            );

    if (!itemsDeleted) {

        JOptionPane.showMessageDialog(
                this,
                "The order items could not be deleted.",
                "AURORA | Delete Failed",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }


    // =====================================================
    // STEP 3 - DELETE MAIN SALE
    // =====================================================

    boolean saleDeleted =
            SalesDAO.deleteSale(
                    orderId
            );

    if (saleDeleted) {

        JOptionPane.showMessageDialog(
                this,
                stockWasApplied
                        ? "Sales order deleted successfully.\nStock has been restored."
                        : "Sales order deleted successfully.",
                "AURORA | Sale Deleted",
                JOptionPane.INFORMATION_MESSAGE
        );

        loadSales();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "The sales order could not be deleted.",
                "AURORA | Delete Failed",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    // =========================================================
    // NEW / EDIT SALE DIALOG
    // =========================================================

    private void showSaleDialog(
            String orderIdToEdit
    ) {

        boolean editing =
                orderIdToEdit != null;

        Object[] existingSale =
                null;

        if (editing) {

            existingSale =
                    SalesDAO.getSaleById(
                            orderIdToEdit
                    );

            if (
                    existingSale == null
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "The selected sale could not be loaded.",
                        "AURORA | Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        // =====================================================
        // LOAD CUSTOMERS
        // =====================================================

        List<Object[]> customers =
                CustomerDAO.getAllCustomers();

        if (
                customers.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please add a customer before creating a sale.",
                    "AURORA | Customer Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // LOAD PRODUCTS
        // =====================================================

        List<Object[]> products =
                ProductDAO.getAllProducts();

        if (
                products.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please add products before creating a sale.",
                    "AURORA | Products Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // CART
        // =====================================================

        List<CartItem> cart =
                new ArrayList<>();

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
                                ? "AURORA | Edit Sale"
                                : "AURORA | New Sale",
                        Dialog.ModalityType.APPLICATION_MODAL
                );

        dialog.setSize(
                820,
                720
        );

        dialog.setMinimumSize(
                new Dimension(
                        760,
                        650
                )
        );

        dialog.setLocationRelativeTo(
                this
        );

        JPanel background =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        background.setBackground(
                CREAM
        );

        background.setBorder(
                new EmptyBorder(
                        22,
                        28,
                        22,
                        28
                )
        );

        // =====================================================
        // HEADER
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
                        "A U R O R A   S A L E S"
                );

        smallTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        smallTitle.setForeground(GOLD);

        JLabel dialogTitle =
                new JLabel(
                        editing
                                ? "Edit Sales Order"
                                : "Create New Sale"
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
                        "Select products and AURORA will calculate the order total automatically."
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

        dialogHeader.add(smallTitle);

        dialogHeader.add(
                Box.createVerticalStrut(4)
        );

        dialogHeader.add(dialogTitle);

        dialogHeader.add(
                Box.createVerticalStrut(3)
        );

        dialogHeader.add(dialogSubtitle);

        background.add(
                dialogHeader,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER
        // =====================================================

        JPanel center =
                new JPanel();

        center.setOpaque(false);

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        // =====================================================
        // ORDER INFORMATION
        // =====================================================

        JPanel orderForm =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                12,
                                6
                        )
                );

        orderForm.setOpaque(false);

        JPanel orderIdPanel =
                createFieldPanel(
                        "Order ID"
                );

        JTextField txtOrderId =
                createFormField();

        orderIdPanel.add(
                txtOrderId,
                BorderLayout.CENTER
        );

        JPanel customerPanel =
                createFieldPanel(
                        "Customer"
                );

        JComboBox<CustomerOption> cmbCustomer =
                new JComboBox<>();

        for (
                Object[] customer :
                customers
        ) {

            cmbCustomer.addItem(
                    new CustomerOption(
                            String.valueOf(
                                    customer[0]
                            ),
                            String.valueOf(
                                    customer[1]
                            )
                    )
            );
        }

        styleComboBox(
                cmbCustomer
        );

        customerPanel.add(
                cmbCustomer,
                BorderLayout.CENTER
        );

        JPanel datePanel =
                createFieldPanel(
                        "Sale Date"
                );

        JTextField txtSaleDate =
                createFormField();

        txtSaleDate.setText(
                LocalDate
                        .now()
                        .toString()
        );

        datePanel.add(
                txtSaleDate,
                BorderLayout.CENTER
        );

        JPanel paymentPanel =
                createFieldPanel(
                        "Payment Method"
                );

        JComboBox<String> cmbPayment =
                new JComboBox<>(
                        new String[]{
                            "Cash",
                            "Card"
                        }
                );

        styleComboBox(
                cmbPayment
        );

        paymentPanel.add(
                cmbPayment,
                BorderLayout.CENTER
        );

        JPanel statusPanel =
                createFieldPanel(
                        "Order Status"
                );

        JComboBox<String> cmbStatus =
                new JComboBox<>(
                        new String[]{
                            "Completed",
                            "Processing",
                            "Pending"
                        }
                );

        styleComboBox(
                cmbStatus
        );

        statusPanel.add(
                cmbStatus,
                BorderLayout.CENTER
        );

        JPanel blank =
                new JPanel();

        blank.setOpaque(false);

        orderForm.add(orderIdPanel);
        orderForm.add(customerPanel);
        orderForm.add(datePanel);

        orderForm.add(paymentPanel);
        orderForm.add(statusPanel);
        orderForm.add(blank);

        center.add(orderForm);

        center.add(
                Box.createVerticalStrut(18)
        );

        // =====================================================
        // PRODUCT SECTION TITLE
        // =====================================================

        JLabel productsTitle =
                new JLabel(
                        "ORDER ITEMS"
                );

        productsTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        productsTitle.setForeground(GOLD);

        productsTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        center.add(productsTitle);

        center.add(
                Box.createVerticalStrut(8)
        );

        // =====================================================
        // PRODUCT SELECTOR
        // =====================================================

        JPanel productSelector =
                new JPanel(
                        new GridBagLayout()
                );

        productSelector.setOpaque(false);

        productSelector.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        productSelector.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        80
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        4,
                        10
                );

        gbc.anchor =
                GridBagConstraints.WEST;

        JLabel productLabel =
                createFormLabel(
                        "Product"
                );

        JLabel priceTitle =
                createFormLabel(
                        "Unit Price"
                );

        JLabel stockTitle =
                createFormLabel(
                        "Available"
                );

        JLabel qtyTitle =
                createFormLabel(
                        "Quantity"
                );

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        productSelector.add(
                productLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0;

        productSelector.add(
                priceTitle,
                gbc
        );

        gbc.gridx = 2;

        productSelector.add(
                stockTitle,
                gbc
        );

        gbc.gridx = 3;

        productSelector.add(
                qtyTitle,
                gbc
        );

        // =====================================================
        // PRODUCT COMBO
        // =====================================================

        JComboBox<ProductOption> cmbProduct =
                new JComboBox<>();

        for (
                Object[] product :
                products
        ) {

            String productId =
                    String.valueOf(
                            product[0]
                    );

            String productName =
                    String.valueOf(
                            product[1]
                    );

            BigDecimal price =
                    BigDecimal.valueOf(
                            ((Number) product[3])
                                    .doubleValue()
                    );

          int stock =
        InventoryDAO.getAvailableStock(
                productId
        );

if (stock < 0) {

    stock = 0;
}

            String status =
                    String.valueOf(
                            product[5]
                    );

            cmbProduct.addItem(
                    new ProductOption(
                            productId,
                            productName,
                            price,
                            stock,
                            status
                    )
            );
        }

        styleComboBox(
                cmbProduct
        );

        JLabel lblPrice =
                new JLabel(
                        "LKR 0.00"
                );

        lblPrice.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        lblPrice.setForeground(
                DEEP_GREEN
        );

        JLabel lblStock =
                new JLabel("0");

        lblStock.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        lblStock.setForeground(
                SOFT_GREEN
        );

        JSpinner quantitySpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                1,
                                1,
                                9999,
                                1
                        )
                );

        quantitySpinner.setPreferredSize(
                new Dimension(
                        75,
                        36
                )
        );

        JButton addItemButton =
                createMainButton(
                        "+ Add Item"
                );

        addItemButton.setPreferredSize(
                new Dimension(
                        110,
                        36
                )
        );

        gbc.gridy = 1;

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        productSelector.add(
                cmbProduct,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0;

        productSelector.add(
                lblPrice,
                gbc
        );

        gbc.gridx = 2;

        productSelector.add(
                lblStock,
                gbc
        );

        gbc.gridx = 3;

        productSelector.add(
                quantitySpinner,
                gbc
        );

        gbc.gridx = 4;
        gbc.insets =
                new Insets(
                        0,
                        5,
                        0,
                        0
                );

        productSelector.add(
                addItemButton,
                gbc
        );

        center.add(productSelector);

        center.add(
                Box.createVerticalStrut(10)
        );

        // =====================================================
        // ORDER ITEMS TABLE
        // =====================================================

        String[] itemColumns = {

            "PRODUCT ID",
            "PRODUCT",
            "QTY",
            "UNIT PRICE",
            "SUBTOTAL"
        };

        DefaultTableModel itemModel =
                new DefaultTableModel(
                        itemColumns,
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

        JTable itemTable =
                new JTable(
                        itemModel
                );

        itemTable.setRowHeight(34);
        itemTable.setBackground(IVORY);
        itemTable.setForeground(TEXT);

        itemTable.setSelectionBackground(
                new Color(
                        223,
                        218,
                        196
                )
        );

        itemTable.setSelectionForeground(
                DEEP_GREEN
        );

        itemTable.setShowVerticalLines(false);

        itemTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        DefaultTableCellRenderer itemCenter =
                new DefaultTableCellRenderer();

        itemCenter.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (
                int i = 0;
                i < itemTable.getColumnCount();
                i++
        ) {

            itemTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            itemCenter
                    );
        }

        JTableHeader itemHeader =
                itemTable.getTableHeader();

        itemHeader.setBackground(
                DEEP_GREEN
        );

        itemHeader.setForeground(
                Color.WHITE
        );

        itemHeader.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        itemHeader.setPreferredSize(
                new Dimension(
                        itemHeader.getWidth(),
                        34
                )
        );

        JScrollPane itemScroll =
                new JScrollPane(
                        itemTable
                );

        itemScroll.setPreferredSize(
                new Dimension(
                        700,
                        145
                )
        );

        itemScroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        145
                )
        );

        itemScroll.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        itemScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                218,
                                212,
                                195
                        )
                )
        );

        center.add(itemScroll);

        center.add(
                Box.createVerticalStrut(8)
        );

        // =====================================================
        // REMOVE ITEM + GRAND TOTAL
        // =====================================================

        JPanel totalPanel =
                new JPanel(
                        new BorderLayout()
                );

        totalPanel.setOpaque(false);

        totalPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JButton removeItemButton =
                createSecondaryButton(
                        "Remove Item"
                );

        removeItemButton.setPreferredSize(
                new Dimension(
                        125,
                        35
                )
        );

        JPanel grandTotalPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        grandTotalPanel.setOpaque(false);

        JLabel grandTotalTitle =
                new JLabel(
                        "GRAND TOTAL"
                );

        grandTotalTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        grandTotalTitle.setForeground(
                GOLD
        );

        JLabel grandTotalValue =
                new JLabel(
                        "LKR 0.00"
                );

        grandTotalValue.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        23
                )
        );

        grandTotalValue.setForeground(
                DEEP_GREEN
        );

        grandTotalPanel.add(
                grandTotalTitle
        );

        grandTotalPanel.add(
                grandTotalValue
        );

        totalPanel.add(
                removeItemButton,
                BorderLayout.WEST
        );

        totalPanel.add(
                grandTotalPanel,
                BorderLayout.EAST
        );

        center.add(totalPanel);

        background.add(
                center,
                BorderLayout.CENTER
        );

        // =====================================================
        // PRODUCT INFO UPDATE
        // =====================================================

        Runnable updateProductInfo =
                () -> {

                    ProductOption product =
                            (ProductOption)
                            cmbProduct
                                    .getSelectedItem();

                    if (
                            product != null
                    ) {

                        lblPrice.setText(
                                formatMoney(
                                        product.price
                                )
                        );

                        lblStock.setText(
                                String.valueOf(
                                        product.stock
                                )
                        );
                    }
                };

        cmbProduct.addActionListener(
                e -> updateProductInfo.run()
        );

        updateProductInfo.run();

        // =====================================================
        // REFRESH CART TABLE
        // =====================================================

        Runnable refreshCart =
                () -> {

                    itemModel.setRowCount(0);

                    BigDecimal grandTotal =
                            BigDecimal.ZERO;

                    for (
                            CartItem item :
                            cart
                    ) {

                        itemModel.addRow(
                                new Object[]{
                                    item.productId,
                                    item.productName,
                                    item.quantity,
                                    formatMoney(
                                            item.unitPrice
                                    ),
                                    formatMoney(
                                            item.subtotal
                                    )
                                }
                        );

                        grandTotal =
                                grandTotal.add(
                                        item.subtotal
                                );
                    }

                    grandTotalValue.setText(
                            formatMoney(
                                    grandTotal
                            )
                    );
                };

        // =====================================================
        // ADD PRODUCT TO CART
        // =====================================================

        addItemButton.addActionListener(
                e -> {

                    ProductOption product =
                            (ProductOption)
                            cmbProduct
                                    .getSelectedItem();

                    if (
                            product == null
                    ) {

                        return;
                    }

                    int quantity =
                            ((Number)
                            quantitySpinner
                                    .getValue())
                                    .intValue();

                    CartItem existingItem =
                            null;

                    for (
                            CartItem item :
                            cart
                    ) {

                        if (
                                item.productId
                                        .equals(
                                                product.productId
                                        )
                        ) {

                            existingItem =
                                    item;

                            break;
                        }
                    }

                    int finalQuantity =
                            quantity;

                    if (
                            existingItem != null
                    ) {

                        finalQuantity +=
                                existingItem.quantity;
                    }

                    // =========================================
                    // STOCK VALIDATION
                    // =========================================

                    if (
                            finalQuantity
                            > product.stock
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Only "
                                + product.stock
                                + " units of "
                                + product.productName
                                + " are currently available.",
                                "AURORA | Insufficient Stock",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    if (
                            product.stock <= 0
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "This product is currently out of stock.",
                                "AURORA | Out of Stock",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    if (
                            existingItem != null
                    ) {

                        existingItem.quantity =
                                finalQuantity;

                        existingItem.subtotal =
                                existingItem.unitPrice
                                        .multiply(
                                                BigDecimal.valueOf(
                                                        finalQuantity
                                                )
                                        );

                    } else {

                        cart.add(
                                new CartItem(
                                        product.productId,
                                        product.productName,
                                        quantity,
                                        product.price
                                )
                        );
                    }

                    refreshCart.run();

                    quantitySpinner.setValue(
                            1
                    );
                }
        );

        // =====================================================
        // REMOVE CART ITEM
        // =====================================================

        removeItemButton.addActionListener(
                e -> {

                    int selected =
                            itemTable
                                    .getSelectedRow();

                    if (
                            selected == -1
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please select an item to remove.",
                                "AURORA | Select Item",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    cart.remove(
                            selected
                    );

                    refreshCart.run();
                }
        );

        // =====================================================
        // LOAD EXISTING ORDER WHEN EDITING
        // =====================================================

        if (editing) {

            txtOrderId.setText(
                    String.valueOf(
                            existingSale[0]
                    )
            );

            txtOrderId.setEditable(false);

            txtOrderId.setBackground(
                    new Color(
                            232,
                            230,
                            220
                    )
            );

            selectCustomer(
                    cmbCustomer,
                    String.valueOf(
                            existingSale[1]
                    )
            );

            if (
                    existingSale[2] != null
            ) {

                txtSaleDate.setText(
                        existingSale[2]
                                .toString()
                );
            }

            cmbPayment.setSelectedItem(
                    String.valueOf(
                            existingSale[3]
                    )
            );

            cmbStatus.setSelectedItem(
                    String.valueOf(
                            existingSale[5]
                    )
            );

            List<Object[]> oldItems =
                    SalesItemDAO
                            .getItemsByOrderId(
                                    orderIdToEdit
                            );

            for (
                    Object[] item :
                    oldItems
            ) {

                String productId =
                        String.valueOf(
                                item[2]
                        );

                int quantity =
                        ((Number) item[3])
                                .intValue();

                BigDecimal unitPrice =
                        (BigDecimal) item[4];

                Object[] product =
                        ProductDAO
                                .getProductById(
                                        productId
                                );

                String productName =
                        product != null
                                ? String.valueOf(
                                        product[1]
                                )
                                : productId;

                cart.add(
                        new CartItem(
                                productId,
                                productName,
                                quantity,
                                unitPrice
                        )
                );
            }

            refreshCart.run();
        }

        // =====================================================
        // BOTTOM BUTTONS
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
                                : "Create Sale"
                );

        cancelButton.setPreferredSize(
                new Dimension(
                        110,
                        40
                )
        );

        saveButton.setPreferredSize(
                new Dimension(
                        140,
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

        cancelButton.addActionListener(
                e -> dialog.dispose()
        );
// =====================================================
// SAVE SALE
// =====================================================

saveButton.addActionListener(
        e -> {

            String orderId =
                    txtOrderId
                            .getText()
                            .trim()
                            .toUpperCase();

            CustomerOption customer =
                    (CustomerOption)
                    cmbCustomer.getSelectedItem();

            String dateText =
                    txtSaleDate
                            .getText()
                            .trim();

            String payment =
                    String.valueOf(
                            cmbPayment.getSelectedItem()
                    );

            String status =
                    String.valueOf(
                            cmbStatus.getSelectedItem()
                    );


            // =================================================
            // BASIC VALIDATION
            // =================================================

            if (
                    orderId.isEmpty()
                    || customer == null
                    || dateText.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Please complete all order information.",
                        "AURORA | Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            if (cart.isEmpty()) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Please add at least one product to the order.",
                        "AURORA | No Products",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            if (
                    !orderId.matches(
                            "[A-Za-z0-9#_-]+"
                    )
            ) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Please enter a valid Order ID.",
                        "AURORA | Invalid Order ID",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =================================================
            // DATE VALIDATION
            // =================================================

            Date saleDate;

            try {

                saleDate =
                        Date.valueOf(
                                dateText
                        );

            } catch (
                    IllegalArgumentException ex
            ) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Enter the date as YYYY-MM-DD.\n"
                        + "Example: 2026-10-04",
                        "AURORA | Invalid Date",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =================================================
            // DUPLICATE ORDER CHECK
            // =================================================

            if (
                    !editing
                    && SalesDAO.orderExists(
                            orderId
                    )
            ) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "This Order ID already exists.",
                        "AURORA | Duplicate Order",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =================================================
            // CALCULATE GRAND TOTAL
            // =================================================

            BigDecimal total =
                    BigDecimal.ZERO;

            for (CartItem item : cart) {

                total =
                        total.add(
                                item.subtotal
                        );
            }


            // =================================================
            // REMEMBER OLD STOCK STATE WHEN EDITING
            // =================================================

            boolean oldStockWasApplied =
                    false;

            List<CartItem> originalCart =
                    new ArrayList<>();


            if (editing) {

                oldStockWasApplied =
                        SalesDAO.isStockApplied(
                                orderId
                        );


                List<Object[]> oldItems =
                        SalesItemDAO.getItemsByOrderId(
                                orderId
                        );


                for (Object[] oldItem : oldItems) {

                    String oldProductId =
                            String.valueOf(
                                    oldItem[2]
                            );

                    int oldQuantity =
                            ((Number) oldItem[3])
                                    .intValue();

                    BigDecimal oldUnitPrice =
                            (BigDecimal) oldItem[4];


                    Object[] product =
                            ProductDAO.getProductById(
                                    oldProductId
                            );


                    String oldProductName =
                            product != null
                                    ? String.valueOf(
                                            product[1]
                                    )
                                    : oldProductId;


                    originalCart.add(
                            new CartItem(
                                    oldProductId,
                                    oldProductName,
                                    oldQuantity,
                                    oldUnitPrice
                            )
                    );
                }
            }


            // =================================================
            // IF EDITING A COMPLETED/APPLIED SALE,
            // RESTORE ITS OLD STOCK FIRST
            // =================================================

            if (
                    editing
                    && oldStockWasApplied
            ) {

                boolean restored =
                        restoreStockForSale(
                                orderId
                        );


                if (!restored) {

                    JOptionPane.showMessageDialog(
                            dialog,
                            "The old stock could not be restored.\n"
                            + "The sale was not changed.",
                            "AURORA | Inventory Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }
            }


            // =================================================
            // COMPLETED ORDERS REQUIRE ENOUGH INVENTORY
            // =================================================

            boolean completed =
                    status.equalsIgnoreCase(
                            "Completed"
                    );


            if (
                    completed
                    && !hasEnoughInventory(
                            cart
                    )
            ) {

                // Restore the ORIGINAL completed order's
                // stock deduction if we temporarily restored it.

                if (
                        editing
                        && oldStockWasApplied
                ) {

                    applyStockForSale(
                            orderId,
                            originalCart
                    );
                }

                return;
            }


            // =================================================
            // SAVE / UPDATE MAIN SALE
            // =================================================

            boolean saleSuccess;


            if (editing) {

                saleSuccess =
                        SalesDAO.updateSale(
                                orderId,
                                customer.customerId,
                                saleDate,
                                payment,
                                total,
                                status
                        );

            } else {

                saleSuccess =
                        SalesDAO.addSale(
                                orderId,
                                customer.customerId,
                                saleDate,
                                payment,
                                total,
                                status
                        );
            }


            if (!saleSuccess) {

                // Put old stock back into its original state
                // if editing failed.

                if (
                        editing
                        && oldStockWasApplied
                ) {

                    applyStockForSale(
                            orderId,
                            originalCart
                    );
                }


                JOptionPane.showMessageDialog(
                        dialog,
                        "The sales order could not be saved.",
                        "AURORA | Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // =================================================
            // EDITING - REMOVE OLD SALE ITEMS
            // =================================================

            if (editing) {

                boolean removed =
                        SalesItemDAO.deleteItemsByOrderId(
                                orderId
                        );


                if (!removed) {

                    JOptionPane.showMessageDialog(
                            dialog,
                            "The existing sales items "
                            + "could not be updated.",
                            "AURORA | Database Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }
            }


            // =================================================
            // SAVE NEW SALE ITEMS
            // =================================================

            boolean allItemsSaved =
                    true;


            for (CartItem item : cart) {

                boolean saved =
                        SalesItemDAO.addSalesItem(
                                orderId,
                                item.productId,
                                item.quantity,
                                item.unitPrice,
                                item.subtotal
                        );


                if (!saved) {

                    allItemsSaved =
                            false;

                    break;
                }
            }


            // =================================================
            // HANDLE ITEM SAVE FAILURE
            // =================================================

            if (!allItemsSaved) {

                SalesItemDAO.deleteItemsByOrderId(
                        orderId
                );


                if (!editing) {

                    SalesDAO.deleteSale(
                            orderId
                    );
                }


                JOptionPane.showMessageDialog(
                        dialog,
                        "One or more order items "
                        + "could not be saved.",
                        "AURORA | Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // =================================================
            // COMPLETED SALE -> REDUCE INVENTORY
            // =================================================

            if (completed) {

                boolean stockSuccess =
                        applyStockForSale(
                                orderId,
                                cart
                        );


                if (!stockSuccess) {

                    JOptionPane.showMessageDialog(
                            dialog,
                            "The sale was saved, but inventory "
                            + "could not be updated.\n"
                            + "Please check the available stock.",
                            "AURORA | Inventory Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    loadSales();

                    return;
                }
            }


            // =================================================
            // SUCCESS
            // =================================================

            JOptionPane.showMessageDialog(
                    dialog,
                    editing
                            ? "Sales order updated successfully."
                            : completed
                                    ? "Sale created successfully.\n"
                                      + "Inventory has been updated."
                                    : "Sale created successfully.\n"
                                      + "Inventory was not changed because "
                                      + "the order is not completed.",
                    editing
                            ? "AURORA | Sale Updated"
                            : "AURORA | Sale Created",
                    JOptionPane.INFORMATION_MESSAGE
            );


            dialog.dispose();

            loadSales();
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
    // SELECT CUSTOMER
    // =========================================================

    private void selectCustomer(
            JComboBox<CustomerOption> combo,
            String customerId
    ) {

        for (
                int i = 0;
                i < combo.getItemCount();
                i++
        ) {

            CustomerOption option =
                    combo.getItemAt(i);

            if (
                    option.customerId
                            .equals(
                                    customerId
                            )
            ) {

                combo.setSelectedIndex(i);

                return;
            }
        }
    }

    // =========================================================
    // FIELD PANEL
    // =========================================================

    private JPanel createFieldPanel(
            String title
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                4
                        )
                );

        panel.setOpaque(false);

        panel.add(
                createFormLabel(
                        title
                ),
                BorderLayout.NORTH
        );

        return panel;
    }

    // =========================================================
    // FORM LABEL
    // =========================================================

    private JLabel createFormLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

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
                        36
                )
        );

        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        field.setForeground(TEXT);
        field.setBackground(IVORY);
        field.setCaretColor(DEEP_GREEN);

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
                                7,
                                10,
                                7,
                                10
                        )
                )
        );

        return field;
    }

    // =========================================================
    // COMBO STYLE
    // =========================================================

    private void styleComboBox(
            JComboBox<?> combo
    ) {

        combo.setPreferredSize(
                new Dimension(
                        100,
                        36
                )
        );

        combo.setBackground(IVORY);
        combo.setForeground(TEXT);

        combo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        combo.setFocusable(false);
    }

    // =========================================================
    // MONEY FORMAT
    // =========================================================

    private String formatMoney(
            BigDecimal amount
    ) {

        if (
                amount == null
        ) {

            amount =
                    BigDecimal.ZERO;
        }

        DecimalFormat format =
                new DecimalFormat(
                        "#,##0.00"
                );

        return "LKR "
                + format.format(
                        amount
                );
    }

    // =========================================================
    // HERO
    // =========================================================

    private JPanel createSalesHero() {

        ImageIcon imageIcon = null;

        try {

            java.net.URL imageURL =
                    getClass()
                            .getResource(
                                    "/auroraclothing/images/sales-banner.png"
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
                        "sales-banner.png not found."
                );
            }

        } catch (
                Exception e
        ) {

            System.out.println(
                    "Could not load sales banner."
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

                        g2.setColor(
                                DARK_GREEN
                        );

                        g2.fillRect(
                                0,
                                0,
                                w,
                                h
                        );

                        if (
                                bannerImage != null
                        ) {

                            int iw =
                                    bannerImage
                                            .getWidth(null);

                            int ih =
                                    bannerImage
                                            .getHeight(null);

                            if (
                                    iw > 0
                                    && ih > 0
                            ) {

                                double scale =
                                        Math.max(
                                                (double) w / iw,
                                                (double) h / ih
                                        );

                                int sw =
                                        (int) (
                                                iw * scale
                                        );

                                int sh =
                                        (int) (
                                                ih * scale
                                        );

                                int x =
                                        (w - sw) / 2;

                                int y =
                                        (h - sh) / 2;

                                g2.drawImage(
                                        bannerImage,
                                        x,
                                        y,
                                        sw,
                                        sh,
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

                        g2.setPaint(
                                blend
                        );

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

                        g2.setPaint(
                                lowerShade
                        );

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
                        "AURORA SALES"
                );

        smallTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        smallTitle.setForeground(GOLD);

        JLabel mainTitle =
                new JLabel(
                        "Style Meets Success"
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

        JLabel description =
                new JLabel(
                        "Turn every AURORA purchase into measurable growth."
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

        JLabel accent =
                new JLabel(
                        "━━━━  ✦"
                );

        accent.setForeground(GOLD);

        smallTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        mainTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

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
    // STAT VALUE
    // =========================================================

    private JLabel createValueLabel(
            String value
    ) {

        JLabel label =
                new JLabel(value);

        label.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        22
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
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        titleLabel.setForeground(GOLD);

        JLabel subtitleLabel =
                new JLabel(subtitle);

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

        inside.add(titleLabel);

        inside.add(
                Box.createVerticalStrut(4)
        );

        inside.add(valueLabel);

        inside.add(
                Box.createVerticalStrut(2)
        );

        inside.add(subtitleLabel);

        card.add(
                inside,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // MAIN BUTTON
    // =========================================================

    private JButton createMainButton(
            String text
    ) {

        JButton button =
                new JButton(text);

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

        button.setFocusPainted(false);
        button.setBorderPainted(false);

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
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        140,
                        38
                )
        );

        button.setBackground(IVORY);
        button.setForeground(DEEP_GREEN);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        button.setFocusPainted(false);

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
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        140,
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

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // CUSTOMER OPTION
    // =========================================================

    private static class CustomerOption {

        private final String customerId;
        private final String customerName;

        public CustomerOption(
                String customerId,
                String customerName
        ) {

            this.customerId =
                    customerId;

            this.customerName =
                    customerName;
        }

        @Override
        public String toString() {

            return customerId
                    + " - "
                    + customerName;
        }
    }

    // =========================================================
    // PRODUCT OPTION
    // =========================================================

    private static class ProductOption {

        private final String productId;
        private final String productName;
        private final BigDecimal price;
        private final int stock;
        private final String status;

        public ProductOption(
                String productId,
                String productName,
                BigDecimal price,
                int stock,
                String status
        ) {

            this.productId =
                    productId;

            this.productName =
                    productName;

            this.price =
                    price;

            this.stock =
                    stock;

            this.status =
                    status;
        }

        @Override
        public String toString() {

            return productId
                    + " - "
                    + productName;
        }
    }

    // =========================================================
    // CART ITEM
    // =========================================================

    private static class CartItem {

        private final String productId;
        private final String productName;

        private int quantity;

        private final BigDecimal unitPrice;

        private BigDecimal subtotal;

        public CartItem(
                String productId,
                String productName,
                int quantity,
                BigDecimal unitPrice
        ) {

            this.productId =
                    productId;

            this.productName =
                    productName;

            this.quantity =
                    quantity;

            this.unitPrice =
                    unitPrice;

            this.subtotal =
                    unitPrice.multiply(
                            BigDecimal.valueOf(
                                    quantity
                            )
                    );
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

            int w = getWidth();
            int h = getHeight();

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

            g2.setColor(IVORY);

            g2.fillRoundRect(
                    0,
                    0,
                    w - 3,
                    h - 3,
                    24,
                    24
            );

            if (
                    searchField != null
                    && searchField.hasFocus()
            ) {

                g2.setColor(GOLD);

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

            super.paintComponent(g);
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

            g2.setColor(GOLD);

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
    // PLACEHOLDER FIELD
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

            super.paintComponent(g);

            if (
                    getText().isEmpty()
                    && !hasFocus()
            ) {

                Graphics2D g2 =
                        (Graphics2D) g.create();

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
                        (getHeight()
                        - fm.getHeight())
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
// CHECK WHETHER CART HAS ENOUGH INVENTORY
// =========================================================

private boolean hasEnoughInventory(
        List<CartItem> cart
) {

    for (CartItem item : cart) {

        int available =
                InventoryDAO.getAvailableStock(
                        item.productId
                );

        if (available < item.quantity) {

            JOptionPane.showMessageDialog(
                    this,
                    item.productName
                    + " does not have enough stock.\n\n"
                    + "Available: "
                    + Math.max(available, 0)
                    + "\nRequired: "
                    + item.quantity,
                    "AURORA | Insufficient Stock",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }
    }

    return true;
}


// =========================================================
// APPLY STOCK FOR COMPLETED SALE
// =========================================================

private boolean applyStockForSale(
        String orderId,
        List<CartItem> cart
) {

    // -----------------------------------------
    // NEVER APPLY TWICE
    // -----------------------------------------

    if (SalesDAO.isStockApplied(orderId)) {

        return true;
    }


    // -----------------------------------------
    // CHECK ALL PRODUCTS FIRST
    // -----------------------------------------

    if (!hasEnoughInventory(cart)) {

        return false;
    }


    List<CartItem> reducedItems =
            new ArrayList<>();


    // -----------------------------------------
    // REDUCE STOCK
    // -----------------------------------------

    for (CartItem item : cart) {

        boolean success =
                InventoryDAO.reduceStockForSale(
                        item.productId,
                        item.quantity
                );

        if (!success) {

            // ---------------------------------
            // ROLLBACK ALREADY REDUCED ITEMS
            // ---------------------------------

            for (CartItem reduced : reducedItems) {

                InventoryDAO.restoreStockFromSale(
                        reduced.productId,
                        reduced.quantity
                );
            }

            return false;
        }

        reducedItems.add(item);
    }


    // -----------------------------------------
    // MARK ORDER AS APPLIED
    // -----------------------------------------

    boolean marked =
            SalesDAO.markStockApplied(
                    orderId
            );


    if (!marked) {

        // -------------------------------------
        // RESTORE EVERYTHING IF FLAG FAILED
        // -------------------------------------

        for (CartItem item : reducedItems) {

            InventoryDAO.restoreStockFromSale(
                    item.productId,
                    item.quantity
            );
        }

        return false;
    }


    return true;
}


// =========================================================
// RESTORE STOCK FOR AN EXISTING COMPLETED ORDER
// =========================================================

private boolean restoreStockForSale(
        String orderId
) {

    // If inventory was never reduced,
    // there is nothing to restore.
    if (!SalesDAO.isStockApplied(orderId)) {

        return true;
    }


    // Get the products BEFORE deleting sale_items.
    List<Object[]> oldItems =
            SalesItemDAO.getItemsByOrderId(
                    orderId
            );


    // IMPORTANT:
    // Never report success if no sale items were found.
    if (
            oldItems == null
            || oldItems.isEmpty()
    ) {

        System.out.println(
                "STOCK RESTORE FAILED: "
                + "No sale items found for "
                + orderId
        );

        return false;
    }


    List<Object[]> restoredItems =
            new ArrayList<>();


    for (Object[] item : oldItems) {

        String productId =
                String.valueOf(
                        item[2]
                );

        int quantity =
                ((Number) item[3])
                        .intValue();


        System.out.println(
                "RESTORING STOCK: "
                + productId
                + " + "
                + quantity
        );


        boolean restored =
                InventoryDAO.restoreStockFromSale(
                        productId,
                        quantity
                );


        if (!restored) {

            // Undo anything already restored
            // during this attempt.
            for (Object[] previous : restoredItems) {

                String previousProductId =
                        String.valueOf(
                                previous[2]
                        );

                int previousQuantity =
                        ((Number) previous[3])
                                .intValue();


                InventoryDAO.reduceStockForSale(
                        previousProductId,
                        previousQuantity
                );
            }


            System.out.println(
                    "STOCK RESTORE FAILED FOR: "
                    + productId
            );

            return false;
        }


        restoredItems.add(
                item
        );
    }


    // Only change stock_applied AFTER
    // every product was restored successfully.
    boolean flagUpdated =
            SalesDAO.markStockNotApplied(
                    orderId
            );


    if (!flagUpdated) {

        System.out.println(
                "COULD NOT RESET stock_applied FOR "
                + orderId
        );

        return false;
    }


    System.out.println(
            "STOCK RESTORED SUCCESSFULLY FOR ORDER: "
            + orderId
    );


    return true;
}
}