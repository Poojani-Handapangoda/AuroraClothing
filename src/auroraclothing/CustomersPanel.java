/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class CustomersPanel extends JPanel {

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
    // TABLE
    // =========================================================

    private DefaultTableModel customerTableModel;
    private JTable customerTable;
    private TableRowSorter<DefaultTableModel> tableSorter;

    // =========================================================
    // SEARCH
    // =========================================================

    private PlaceholderTextField searchField;
    private LuxurySearchBar searchBar;
    private JComboBox<String> customerFilter;

    // =========================================================
    // STATISTICS
    // =========================================================

    private JLabel totalCustomersValue;
    private JLabel activeCustomersValue;
    private JLabel inactiveCustomersValue;
    private JLabel newCustomersValue;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public CustomersPanel() {

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

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JPanel titleArea = new JPanel();
        titleArea.setOpaque(false);

        titleArea.setLayout(
                new BoxLayout(
                        titleArea,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("Customer Management");

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
                        "Manage AURORA customers and build meaningful relationships."
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
        // ADD CUSTOMER BUTTON
        // =====================================================

        JButton addCustomerButton =
                createMainButton(
                        "+  Add Customer"
                );

        addCustomerButton.setPreferredSize(
                new Dimension(
                        150,
                        42
                )
        );

        addCustomerButton.addActionListener(
                e -> showCustomerDialog(null)
        );

        top.add(
                addCustomerButton,
                BorderLayout.EAST
        );

        add(
                top,
                BorderLayout.NORTH
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        JPanel content = new JPanel();

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
                createCustomerHero();

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

        totalCustomersValue =
                createValueLabel("0");

        activeCustomersValue =
                createValueLabel("0");

        inactiveCustomersValue =
                createValueLabel("0");

        newCustomersValue =
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
                        "TOTAL CUSTOMERS",
                        totalCustomersValue,
                        "Registered customers"
                )
        );

        stats.add(
                createStatCard(
                        "ACTIVE CUSTOMERS",
                        activeCustomersValue,
                        "Currently active"
                )
        );

        stats.add(
                createStatCard(
                        "INACTIVE CUSTOMERS",
                        inactiveCustomersValue,
                        "Currently inactive"
                )
        );

        stats.add(
                createStatCard(
                        "NEW THIS MONTH",
                        newCustomersValue,
                        "Growing community"
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

        // =====================================================
        // SEARCH FIELD
        // =====================================================

        searchField =
                new PlaceholderTextField(
                        "Search by customer ID, name, phone or email..."
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
        // LUXURY SEARCH BAR
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
        // CUSTOMER FILTER
        // =====================================================

        customerFilter =
                new JComboBox<>(
                        new String[]{
                            "All Customers",
                            "Active",
                            "Inactive",
                            "New Customers"
                        }
                );

        customerFilter.setPreferredSize(
                new Dimension(
                        155,
                        44
                )
        );

        customerFilter.setBackground(IVORY);
        customerFilter.setForeground(DEEP_GREEN);

        customerFilter.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        customerFilter.setFocusable(false);

        searchArea.add(
                searchBar,
                BorderLayout.CENTER
        );

        searchArea.add(
                customerFilter,
                BorderLayout.EAST
        );

        content.add(searchArea);

        content.add(
                Box.createVerticalStrut(15)
        );

        // =====================================================
        // CUSTOMER TABLE
        // =====================================================

        String[] columns = {

            "CUSTOMER ID",
            "CUSTOMER NAME",
            "PHONE",
            "EMAIL",
            "CREATED AT",
            "STATUS"
        };

        customerTableModel =
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

        customerTable =
                new JTable(
                        customerTableModel
                );

        // =====================================================
        // CENTER ALIGN
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (
                int i = 0;
                i < customerTable.getColumnCount();
                i++
        ) {

            customerTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }

        // =====================================================
        // TABLE STYLE
        // =====================================================

        customerTable.setRowHeight(42);

        customerTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        customerTable.setForeground(TEXT);
        customerTable.setBackground(IVORY);

        customerTable.setSelectionBackground(
                new Color(
                        223,
                        218,
                        196
                )
        );

        customerTable.setSelectionForeground(
                DEEP_GREEN
        );

        customerTable.setShowVerticalLines(false);

        customerTable.setGridColor(
                new Color(
                        225,
                        220,
                        205
                )
        );

        customerTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // =====================================================
        // HEADER
        // =====================================================

        JTableHeader header =
                customerTable.getTableHeader();

        DefaultTableCellRenderer headerRenderer =
                (DefaultTableCellRenderer)
                header.getDefaultRenderer();

        headerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

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

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        customerTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        customerTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(150);

        customerTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(120);

        customerTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(190);

        customerTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(110);

        customerTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(90);

        // =====================================================
        // TABLE SORTER
        // =====================================================

        tableSorter =
                new TableRowSorter<>(
                        customerTableModel
                );

        customerTable.setRowSorter(
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

        customerFilter.addActionListener(
                e -> applyFilters()
        );

        clearButton.addActionListener(
                e -> {

                    searchField.setText("");
                    searchField.requestFocusInWindow();
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
        // DOUBLE CLICK = EDIT
        // =====================================================

        customerTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (e.getClickCount() == 2) {

                            editSelectedCustomer();
                        }
                    }
                }
        );

        // =====================================================
        // TABLE SCROLL
        // =====================================================

        JScrollPane scroll =
                new JScrollPane(
                        customerTable
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
        // CRUD ACTION BUTTONS
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
                e -> editSelectedCustomer()
        );

        deleteButton.addActionListener(
                e -> deleteSelectedCustomer()
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

        // =====================================================
        // LOAD REAL DATABASE DATA
        // =====================================================

        loadCustomers();
    }

    // =========================================================
    // LOAD CUSTOMERS FROM MYSQL
    // =========================================================

    private void loadCustomers() {

        customerTableModel.setRowCount(0);

        List<Object[]> customers =
                CustomerDAO.getAllCustomers();

        int total = 0;
        int active = 0;
        int inactive = 0;
        int newThisMonth = 0;

        SimpleDateFormat displayFormat =
                new SimpleDateFormat(
                        "yyyy-MM-dd"
                );

        SimpleDateFormat monthFormat =
                new SimpleDateFormat(
                        "yyyy-MM"
                );

        String currentMonth =
                monthFormat.format(
                        new Date()
                );

        for (
                Object[] customer :
                customers
        ) {

            String customerId =
                    String.valueOf(
                            customer[0]
                    );

            String customerName =
                    String.valueOf(
                            customer[1]
                    );

            String phone =
                    customer[2] == null
                            ? ""
                            : String.valueOf(
                                    customer[2]
                            );

            String email =
                    customer[3] == null
                            ? ""
                            : String.valueOf(
                                    customer[3]
                            );

            String status =
                    customer[4] == null
                            ? ""
                            : String.valueOf(
                                    customer[4]
                            );

            Timestamp createdAt =
                    customer[5] instanceof Timestamp
                            ? (Timestamp) customer[5]
                            : null;

            String createdText =
                    createdAt == null
                            ? ""
                            : displayFormat.format(
                                    createdAt
                            );

            Object[] row = {

                customerId,
                customerName,
                phone,
                email,
                createdText,
                status
            };

            customerTableModel.addRow(
                    row
            );

            total++;

            if (
                    status.equalsIgnoreCase(
                            "Active"
                    )
            ) {

                active++;

            } else if (
                    status.equalsIgnoreCase(
                            "Inactive"
                    )
            ) {

                inactive++;
            }

            if (
                    createdAt != null
                    && monthFormat
                            .format(createdAt)
                            .equals(currentMonth)
            ) {

                newThisMonth++;
            }
        }

        totalCustomersValue.setText(
                String.valueOf(total)
        );

        activeCustomersValue.setText(
                String.valueOf(active)
        );

        inactiveCustomersValue.setText(
                String.valueOf(inactive)
        );

        newCustomersValue.setText(
                String.valueOf(newThisMonth)
        );

        applyFilters();
    }

    // =========================================================
    // SEARCH + FILTER
    // =========================================================

    private void applyFilters() {

        if (
                tableSorter == null
                || searchField == null
                || customerFilter == null
        ) {

            return;
        }

        final String searchText =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        final String selectedFilter =
                String.valueOf(
                        customerFilter
                                .getSelectedItem()
                );

        final String currentMonth =
                new SimpleDateFormat(
                        "yyyy-MM"
                )
                        .format(
                                new Date()
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

                        // =====================================
                        // SEARCH
                        // =====================================

                        boolean searchMatches =
                                searchText.isEmpty();

                        if (!searchMatches) {

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

                                    searchMatches = true;
                                    break;
                                }
                            }
                        }

                        // =====================================
                        // FILTER
                        // =====================================

                        boolean filterMatches = true;

                        String rowStatus =
                                String.valueOf(
                                        entry.getValue(5)
                                );

                        String createdAt =
                                String.valueOf(
                                        entry.getValue(4)
                                );

                        switch (
                                selectedFilter
                        ) {

                            case "Active" ->

                                filterMatches =
                                        rowStatus
                                                .equalsIgnoreCase(
                                                        "Active"
                                                );

                            case "Inactive" ->

                                filterMatches =
                                        rowStatus
                                                .equalsIgnoreCase(
                                                        "Inactive"
                                                );

                            case "New Customers" ->

                                filterMatches =
                                        createdAt.startsWith(
                                                currentMonth
                                        );

                            default ->

                                filterMatches = true;
                        }

                        return searchMatches
                                && filterMatches;
                    }
                }
        );
    }

    // =========================================================
    // EDIT SELECTED CUSTOMER
    // =========================================================

    private void editSelectedCustomer() {

        int selectedRow =
                customerTable
                        .getSelectedRow();

        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer to edit.",
                    "AURORA | Select Customer",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
                customerTable
                        .convertRowIndexToModel(
                                selectedRow
                        );

        String customerId =
                customerTableModel
                        .getValueAt(
                                modelRow,
                                0
                        )
                        .toString();

        showCustomerDialog(
                customerId
        );
    }

    // =========================================================
    // DELETE SELECTED CUSTOMER
    // =========================================================

    private void deleteSelectedCustomer() {
        
    // =========================================================
    // SECURITY - STAFF CANNOT DELETE CUSTOMERS
    // =========================================================

    if (!UserSession.canManageSensitiveRecords()) {

        JOptionPane.showMessageDialog(
                this,
                "You do not have permission to delete customers.\n"
                + "Please contact an Administrator or Manager.",
                "AURORA | Access Denied",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

        int selectedRow =
                customerTable
                        .getSelectedRow();

        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer to delete.",
                    "AURORA | Select Customer",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
                customerTable
                        .convertRowIndexToModel(
                                selectedRow
                        );

        String customerId =
                customerTableModel
                        .getValueAt(
                                modelRow,
                                0
                        )
                        .toString();

        String customerName =
                customerTableModel
                        .getValueAt(
                                modelRow,
                                1
                        )
                        .toString();

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete customer "
                        + customerName
                        + " (" + customerId + ")?"
                        + "\n\nThis action cannot be undone.",
                        "AURORA | Delete Customer",
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
                CustomerDAO.deleteCustomer(
                        customerId
                );

        if (deleted) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer deleted successfully.",
                    "AURORA | Customer Deleted",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadCustomers();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "The customer could not be deleted.",
                    "AURORA | Delete Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // ADD / EDIT CUSTOMER DIALOG
    // =========================================================

    private void showCustomerDialog(
            String customerIdToEdit
    ) {

        boolean editing =
                customerIdToEdit != null;

        Object[] existingCustomer =
                null;

        if (editing) {

            existingCustomer =
                    CustomerDAO.getCustomerById(
                            customerIdToEdit
                    );

            if (
                    existingCustomer == null
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "The selected customer could not be loaded.",
                        "AURORA | Customer Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
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
                                ? "AURORA | Edit Customer"
                                : "AURORA | Add Customer",
                        Dialog.ModalityType.APPLICATION_MODAL
                );

        dialog.setSize(
                530,
                610
        );

        dialog.setResizable(false);

        dialog.setLocationRelativeTo(
                this
        );

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
                        "A U R O R A   C O M M U N I T Y"
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
                                ? "Edit Customer"
                                : "Add New Customer"
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
                                ? "Update customer information with care."
                                : "Welcome someone new to the AURORA community."
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

        dialogHeader.add(smallTitle);
        dialogHeader.add(Box.createVerticalStrut(5));
        dialogHeader.add(dialogTitle);
        dialogHeader.add(Box.createVerticalStrut(4));
        dialogHeader.add(dialogSubtitle);
        dialogHeader.add(Box.createVerticalStrut(12));
        dialogHeader.add(goldLine);

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
        // CUSTOMER ID
        // =====================================================

        form.add(
                createFormLabel(
                        "Customer ID"
                ),
                gbc
        );

        gbc.gridy++;

        JTextField txtCustomerId =
                createFormField();

        form.add(
                txtCustomerId,
                gbc
        );

        // =====================================================
        // CUSTOMER NAME
        // =====================================================

        gbc.gridy++;

        form.add(
                createFormLabel(
                        "Customer Name"
                ),
                gbc
        );

        gbc.gridy++;

        JTextField txtCustomerName =
                createFormField();

        form.add(
                txtCustomerName,
                gbc
        );

        // =====================================================
        // PHONE
        // =====================================================

        gbc.gridy++;

        form.add(
                createFormLabel(
                        "Phone Number"
                ),
                gbc
        );

        gbc.gridy++;

        JTextField txtPhone =
                createFormField();

        form.add(
                txtPhone,
                gbc
        );

        // =====================================================
        // EMAIL
        // =====================================================

        gbc.gridy++;

        form.add(
                createFormLabel(
                        "Email Address"
                ),
                gbc
        );

        gbc.gridy++;

        JTextField txtEmail =
                createFormField();

        form.add(
                txtEmail,
                gbc
        );

        // =====================================================
        // STATUS
        // =====================================================

        gbc.gridy++;

        form.add(
                createFormLabel(
                        "Customer Status"
                ),
                gbc
        );

        gbc.gridy++;

        JComboBox<String> cmbStatus =
                new JComboBox<>(
                        new String[]{
                            "Active",
                            "Inactive"
                        }
                );

        cmbStatus.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        cmbStatus.setBackground(IVORY);
        cmbStatus.setForeground(TEXT);

        cmbStatus.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        cmbStatus.setFocusable(false);

        form.add(
                cmbStatus,
                gbc
        );

        background.add(
                form,
                BorderLayout.CENTER
        );

        // =====================================================
        // LOAD EXISTING DATA
        // =====================================================

        if (editing) {

            txtCustomerId.setText(
                    String.valueOf(
                            existingCustomer[0]
                    )
            );

            txtCustomerName.setText(
                    String.valueOf(
                            existingCustomer[1]
                    )
            );

            txtPhone.setText(
                    existingCustomer[2] == null
                            ? ""
                            : String.valueOf(
                                    existingCustomer[2]
                            )
            );

            txtEmail.setText(
                    existingCustomer[3] == null
                            ? ""
                            : String.valueOf(
                                    existingCustomer[3]
                            )
            );

            cmbStatus.setSelectedItem(
                    String.valueOf(
                            existingCustomer[4]
                    )
            );

            // Customer ID should never change after creation
            txtCustomerId.setEditable(false);

            txtCustomerId.setBackground(
                    new Color(
                            232,
                            230,
                            220
                    )
            );
        }

        // =====================================================
        // BUTTONS
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
                                : "Add Customer"
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

        buttons.add(cancelButton);
        buttons.add(saveButton);

        background.add(
                buttons,
                BorderLayout.SOUTH
        );

        cancelButton.addActionListener(
                e -> dialog.dispose()
        );

        // =====================================================
        // SAVE
        // =====================================================

        saveButton.addActionListener(
                e -> {

                    String customerId =
                            txtCustomerId
                                    .getText()
                                    .trim()
                                    .toUpperCase();

                    String customerName =
                            txtCustomerName
                                    .getText()
                                    .trim();

                    String phone =
                            txtPhone
                                    .getText()
                                    .trim();

                    String email =
                            txtEmail
                                    .getText()
                                    .trim();

                    String status =
                            String.valueOf(
                                    cmbStatus
                                            .getSelectedItem()
                            );

                    // =========================================
                    // EMPTY VALIDATION
                    // =========================================

                    if (
                            customerId.isEmpty()
                            || customerName.isEmpty()
                            || phone.isEmpty()
                            || email.isEmpty()
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please complete all customer details.",
                                "AURORA | Missing Information",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    // =========================================
                    // CUSTOMER ID FORMAT
                    // =========================================

                    if (
                            !customerId.matches(
                                    "[A-Za-z0-9_-]+"
                            )
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Customer ID can contain only letters, numbers, - and _.",
                                "AURORA | Invalid Customer ID",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    // =========================================
                    // NAME VALIDATION
                    // =========================================

                    if (
                            customerName.length() < 2
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please enter a valid customer name.",
                                "AURORA | Invalid Name",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    // =========================================
                    // PHONE VALIDATION
                    // =========================================

                    String phoneDigits =
                            phone.replaceAll(
                                    "[^0-9]",
                                    ""
                            );

                    if (
                            phoneDigits.length() < 7
                            || phoneDigits.length() > 15
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please enter a valid phone number.",
                                "AURORA | Invalid Phone",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    // =========================================
                    // EMAIL VALIDATION
                    // =========================================

                    if (
                            !email.matches(
                                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
                            )
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please enter a valid email address.",
                                "AURORA | Invalid Email",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    // =========================================
                    // DUPLICATE CUSTOMER ID
                    // =========================================

                    if (
                            !editing
                            && CustomerDAO.customerExists(
                                    customerId
                            )
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "This Customer ID already exists.",
                                "AURORA | Duplicate Customer",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    // =========================================
                    // DUPLICATE PHONE
                    // =========================================

                    if (!editing) {

                        if (
                                CustomerDAO.phoneExists(
                                        phone
                                )
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,
                                    "This phone number is already registered to another customer.",
                                    "AURORA | Duplicate Phone",
                                    JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }

                    } else {

                        if (
                                CustomerDAO
                                        .phoneExistsForAnotherCustomer(
                                                phone,
                                                customerId
                                        )
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,
                                    "This phone number belongs to another customer.",
                                    "AURORA | Duplicate Phone",
                                    JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }
                    }

                    // =========================================
                    // DUPLICATE EMAIL
                    // =========================================

                    if (!editing) {

                        if (
                                CustomerDAO.emailExists(
                                        email
                                )
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,
                                    "This email address is already registered.",
                                    "AURORA | Duplicate Email",
                                    JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }

                    } else {

                        if (
                                CustomerDAO
                                        .emailExistsForAnotherCustomer(
                                                email,
                                                customerId
                                        )
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,
                                    "This email address belongs to another customer.",
                                    "AURORA | Duplicate Email",
                                    JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }
                    }

                    // =========================================
                    // DATABASE SAVE
                    // =========================================

                    boolean success;

                    if (editing) {

                        success =
                                CustomerDAO.updateCustomer(
                                        customerId,
                                        customerName,
                                        phone,
                                        email,
                                        status
                                );

                    } else {

                        success =
                                CustomerDAO.addCustomer(
                                        customerId,
                                        customerName,
                                        phone,
                                        email,
                                        status
                                );
                    }

                    // =========================================
                    // RESULT
                    // =========================================

                    if (success) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                editing
                                        ? "Customer updated successfully."
                                        : "Customer added successfully.",
                                editing
                                        ? "AURORA | Customer Updated"
                                        : "AURORA | Customer Added",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        loadCustomers();

                        dialog.dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                dialog,
                                editing
                                        ? "The customer could not be updated."
                                        : "The customer could not be added.",
                                "AURORA | Database Error",
                                JOptionPane.ERROR_MESSAGE
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
    // CUSTOMER HERO
    // =========================================================

    private JPanel createCustomerHero() {

        ImageIcon imageIcon = null;

        try {

            java.net.URL imageURL =
                    getClass()
                            .getResource(
                                    "/auroraclothing/images/customers-banner.png"
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
                        "customers-banner.png not found."
                );
            }

        } catch (
                Exception e
        ) {

            System.out.println(
                    "Could not load customers banner."
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

                        // =====================================
                        // ROUNDED SHAPE
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
                        // GREEN BASE
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
                        // IMAGE
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
                        "AURORA COMMUNITY"
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
                        "Every Customer Matters"
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
                        "Build meaningful relationships behind every AURORA purchase."
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
        inside.add(Box.createVerticalStrut(4));
        inside.add(valueLabel);
        inside.add(Box.createVerticalStrut(2));
        inside.add(subtitleLabel);

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
                        135,
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

            // Shadow
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

            // Ivory body
            g2.setColor(IVORY);

            g2.fillRoundRect(
                    0,
                    0,
                    w - 3,
                    h - 3,
                    24,
                    24
            );

            // Border
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
    // PLACEHOLDER TEXT FIELD
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
    // LUXURY STAT CARD
    // =========================================================

    private class LuxuryCard
            extends JPanel {

        public LuxuryCard() {

            setOpaque(false);

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

            int w = getWidth();
            int h = getHeight();

            // Shadow
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

            // Ivory body
            g2.setColor(IVORY);

            g2.fillRoundRect(
                    0,
                    0,
                    w - 5,
                    h - 5,
                    22,
                    22
            );

            // Gold border
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

            super.paintComponent(g);
        }
    }
}