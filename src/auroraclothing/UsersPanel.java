/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class UsersPanel extends JPanel {

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

    private final Color BORDER =
            new Color(218, 212, 195);


    // =========================================================
    // COMPONENTS
    // =========================================================

    private JTable usersTable;

    private DefaultTableModel tableModel;

    private TableRowSorter<DefaultTableModel> sorter;

    private JTextField txtSearch;

    private JComboBox<String> cmbRoleFilter;

    private JComboBox<String> cmbStatusFilter;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UsersPanel() {

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

        loadUsers();
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
                        "User Management"
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
                        "Manage AURORA system users, roles and account access."
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
        // ADD USER BUTTON
        // =====================================================

        JButton btnAddUser =
                createPrimaryButton(
                        "＋  Add User"
                );

        btnAddUser.setPreferredSize(
                new Dimension(
                        135,
                        42
                )
        );


        btnAddUser.addActionListener(
                e -> showAddUserDialog()
        );


        top.add(
                btnAddUser,
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
                createUsersHero();

        hero.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        hero.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        135
                )
        );


        content.add(hero);

        content.add(
                Box.createVerticalStrut(20)
        );


        // =====================================================
        // SEARCH + FILTER BAR
        // =====================================================

        JPanel controls =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        controls.setOpaque(false);

        controls.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        controls.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // =====================================================
        // SEARCH FIELD
        // =====================================================

        txtSearch =
                new JTextField();

        txtSearch.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        txtSearch.setForeground(TEXT);

        txtSearch.setBackground(IVORY);

        txtSearch.setToolTipText(
                "Search by name, username, role or status"
        );

        txtSearch.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        new EmptyBorder(
                                8,
                                14,
                                8,
                                14
                        )
                )
        );


        // =====================================================
        // FILTER PANEL
        // =====================================================

        JPanel filters =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        filters.setOpaque(false);


        cmbRoleFilter =
                new JComboBox<>();

        cmbRoleFilter.addItem(
                "All Roles"
        );

        cmbRoleFilter.setPreferredSize(
                new Dimension(
                        135,
                        40
                )
        );

        styleComboBox(
                cmbRoleFilter
        );


        cmbStatusFilter =
                new JComboBox<>(
                        new String[]{
                            "All Status",
                            "Active",
                            "Inactive"
                        }
                );

        cmbStatusFilter.setPreferredSize(
                new Dimension(
                        125,
                        40
                )
        );

        styleComboBox(
                cmbStatusFilter
        );


        filters.add(
                cmbRoleFilter
        );

        filters.add(
                cmbStatusFilter
        );


        controls.add(
                txtSearch,
                BorderLayout.CENTER
        );

        controls.add(
                filters,
                BorderLayout.EAST
        );


        content.add(
                controls
        );

        content.add(
                Box.createVerticalStrut(15)
        );


        // =====================================================
        // USERS TABLE
        // =====================================================

        String[] columns = {

            "USER ID",
            "FULL NAME",
            "USERNAME",
            "ROLE",
            "STATUS"
        };


        tableModel =
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


        usersTable =
                new JTable(
                        tableModel
                );


        usersTable.setRowHeight(
                42
        );

        usersTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        usersTable.setForeground(
                TEXT
        );

        usersTable.setBackground(
                IVORY
        );

        usersTable.setSelectionBackground(
                new Color(
                        223,
                        218,
                        196
                )
        );

        usersTable.setSelectionForeground(
                DEEP_GREEN
        );

        usersTable.setShowVerticalLines(
                false
        );

        usersTable.setGridColor(
                new Color(
                        225,
                        220,
                        205
                )
        );

        usersTable.setFillsViewportHeight(
                true
        );

        usersTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        // =====================================================
        // CENTER TABLE DATA
        // =====================================================

        DefaultTableCellRenderer center =
                new DefaultTableCellRenderer();

        center.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        for (
                int i = 0;
                i < usersTable.getColumnCount();
                i++
        ) {

            usersTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }


        // =====================================================
        // TABLE HEADER
        // =====================================================

        JTableHeader header =
                usersTable.getTableHeader();

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

        usersTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(70);

        usersTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(210);

        usersTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(160);

        usersTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(130);

        usersTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(100);


        sorter =
                new TableRowSorter<>(
                        tableModel
                );

        usersTable.setRowSorter(
                sorter
        );


        JScrollPane scroll =
                new JScrollPane(
                        usersTable
                );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        scroll
                .getViewport()
                .setBackground(
                        IVORY
                );

        scroll.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        content.add(
                scroll
        );

        content.add(
                Box.createVerticalStrut(15)
        );


        // =====================================================
        // ACTION BUTTONS
        // =====================================================

        JPanel actions =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        actions.setOpaque(false);

        actions.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        actions.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JButton btnEdit =
                createSecondaryButton(
                        "✎  Edit User"
                );


        JButton btnPassword =
                createSecondaryButton(
                        "⌘  Change Password"
                );


        JButton btnDelete =
                createDeleteButton(
                        "Delete User"
                );


        btnEdit.addActionListener(
                e -> editSelectedUser()
        );


        btnPassword.addActionListener(
                e -> changeSelectedPassword()
        );


        btnDelete.addActionListener(
                e -> deleteSelectedUser()
        );


        actions.add(
                btnEdit
        );

        actions.add(
                btnPassword
        );

        actions.add(
                btnDelete
        );


        content.add(
                actions
        );


        add(
                content,
                BorderLayout.CENTER
        );


        // =====================================================
        // SEARCH LISTENER
        // =====================================================

        txtSearch
                .getDocument()
                .addDocumentListener(

                        new DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    DocumentEvent e
                            ) {

                                applyFilters();
                            }


                            @Override
                            public void removeUpdate(
                                    DocumentEvent e
                            ) {

                                applyFilters();
                            }


                            @Override
                            public void changedUpdate(
                                    DocumentEvent e
                            ) {

                                applyFilters();
                            }
                        }
                );


        cmbRoleFilter.addActionListener(
                e -> applyFilters()
        );


        cmbStatusFilter.addActionListener(
                e -> applyFilters()
        );


        // =====================================================
        // DOUBLE CLICK = EDIT USER
        // =====================================================

        usersTable.addMouseListener(

                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (
                                e.getClickCount() == 2
                                && usersTable.getSelectedRow() != -1
                        ) {

                            editSelectedUser();
                        }
                    }
                }
        );
    }


    // =========================================================
    // LOAD USERS FROM DATABASE
    // =========================================================

    public final void loadUsers() {

        tableModel.setRowCount(
                0
        );


        List<Object[]> users =
                UserDAO.getAllUsers();


        for (Object[] user : users) {

            tableModel.addRow(
                    user
            );
        }


        refreshRoleFilter(
                users
        );


        applyFilters();
    }


    // =========================================================
    // REFRESH ROLE FILTER
    // =========================================================

    private void refreshRoleFilter(
            List<Object[]> users
    ) {

        String selected =
                cmbRoleFilter.getSelectedItem()
                        != null
                        ? cmbRoleFilter
                                .getSelectedItem()
                                .toString()
                        : "All Roles";


        cmbRoleFilter.removeAllItems();

        cmbRoleFilter.addItem(
                "All Roles"
        );


        for (Object[] user : users) {

            String role =
                    String.valueOf(
                            user[3]
                    );


            boolean exists =
                    false;


            for (
                    int i = 0;
                    i < cmbRoleFilter.getItemCount();
                    i++
            ) {

                if (
                        cmbRoleFilter
                                .getItemAt(i)
                                .equalsIgnoreCase(role)
                ) {

                    exists = true;

                    break;
                }
            }


            if (!exists) {

                cmbRoleFilter.addItem(
                        role
                );
            }
        }


        boolean selectedExists =
                false;


        for (
                int i = 0;
                i < cmbRoleFilter.getItemCount();
                i++
        ) {

            if (
                    cmbRoleFilter
                            .getItemAt(i)
                            .equalsIgnoreCase(selected)
            ) {

                cmbRoleFilter.setSelectedIndex(
                        i
                );

                selectedExists =
                        true;

                break;
            }
        }


        if (!selectedExists) {

            cmbRoleFilter.setSelectedIndex(
                    0
            );
        }
    }


    // =========================================================
    // SEARCH + FILTER
    // =========================================================

    private void applyFilters() {

        if (sorter == null) {

            return;
        }


        String searchText =
                txtSearch
                        .getText()
                        .trim()
                        .toLowerCase();


        String selectedRole =
                cmbRoleFilter.getSelectedItem()
                        != null
                        ? cmbRoleFilter
                                .getSelectedItem()
                                .toString()
                        : "All Roles";


        String selectedStatus =
                cmbStatusFilter.getSelectedItem()
                        != null
                        ? cmbStatusFilter
                                .getSelectedItem()
                                .toString()
                        : "All Status";


        sorter.setRowFilter(

                new RowFilter<DefaultTableModel, Integer>() {

                    @Override
                    public boolean include(
                            Entry<? extends DefaultTableModel,
                                    ? extends Integer> entry
                    ) {

                        String fullName =
                                entry
                                        .getStringValue(1)
                                        .toLowerCase();

                        String username =
                                entry
                                        .getStringValue(2)
                                        .toLowerCase();

                        String role =
                                entry
                                        .getStringValue(3);

                        String status =
                                entry
                                        .getStringValue(4);


                        boolean matchesSearch =

                                searchText.isEmpty()

                                || fullName.contains(
                                        searchText
                                )

                                || username.contains(
                                        searchText
                                )

                                || role
                                        .toLowerCase()
                                        .contains(
                                                searchText
                                        )

                                || status
                                        .toLowerCase()
                                        .contains(
                                                searchText
                                        );


                        boolean matchesRole =

                                selectedRole.equals(
                                        "All Roles"
                                )

                                || role.equalsIgnoreCase(
                                        selectedRole
                                );


                        boolean matchesStatus =

                                selectedStatus.equals(
                                        "All Status"
                                )

                                || status.equalsIgnoreCase(
                                        selectedStatus
                                );


                        return matchesSearch
                                && matchesRole
                                && matchesStatus;
                    }
                }
        );
    }


    // =========================================================
    // ADD USER
    // =========================================================

    private void showAddUserDialog() {

        JTextField txtFullName =
                createDialogTextField();


        JTextField txtUsername =
                createDialogTextField();


        JPasswordField txtPassword =
                createDialogPasswordField();


        JPasswordField txtConfirmPassword =
                createDialogPasswordField();


        JComboBox<String> cmbRole =
                createRoleCombo();


        JComboBox<String> cmbStatus =
                new JComboBox<>(
                        new String[]{
                            "Active",
                            "Inactive"
                        }
                );


        styleComboBox(
                cmbStatus
        );


        JPanel form =
                createUserFormPanel();


        addFormField(
                form,
                "Full Name",
                txtFullName
        );


        addFormField(
                form,
                "Username",
                txtUsername
        );


        addFormField(
                form,
                "Password",
                txtPassword
        );


        addFormField(
                form,
                "Confirm Password",
                txtConfirmPassword
        );


        addFormField(
                form,
                "Role",
                cmbRole
        );


        addFormField(
                form,
                "Status",
                cmbStatus
        );


        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        form,
                        "AURORA | Add New User",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (
                option != JOptionPane.OK_OPTION
        ) {

            return;
        }


        String fullName =
                txtFullName
                        .getText()
                        .trim();


        String username =
                txtUsername
                        .getText()
                        .trim();


        String password =
                new String(
                        txtPassword.getPassword()
                );


        String confirmPassword =
                new String(
                        txtConfirmPassword.getPassword()
                );


        String role =
                String.valueOf(
                        cmbRole.getSelectedItem()
                );


        String status =
                String.valueOf(
                        cmbStatus.getSelectedItem()
                );


        // =====================================================
        // VALIDATION
        // =====================================================

        if (
                fullName.isEmpty()
                || username.isEmpty()
                || password.isEmpty()
                || confirmPassword.isEmpty()
        ) {

            showWarning(
                    "Please complete all user information."
            );

            return;
        }


        if (
                username.contains(" ")
        ) {

            showWarning(
                    "Username cannot contain spaces."
            );

            return;
        }


        if (
                password.length() < 4
        ) {

            showWarning(
                    "Password must contain at least 4 characters."
            );

            return;
        }


        if (
                !password.equals(
                        confirmPassword
                )
        ) {

            showWarning(
                    "Password and Confirm Password do not match."
            );

            return;
        }


        if (
                UserDAO.usernameExists(
                        username
                )
        ) {

            showWarning(
                    "This username already exists.\n"
                    + "Please choose another username."
            );

            return;
        }


        boolean success =
                UserDAO.addUser(
                        fullName,
                        username,
                        password,
                        role,
                        status
                );


        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "User created successfully.",
                    "AURORA | User Created",
                    JOptionPane.INFORMATION_MESSAGE
            );


            loadUsers();

        } else {

            showError(
                    "The user could not be created."
            );
        }
    }


    // =========================================================
    // EDIT SELECTED USER
    // =========================================================

    private void editSelectedUser() {

        int viewRow =
                usersTable.getSelectedRow();


        if (viewRow == -1) {

            showWarning(
                    "Please select a user to edit."
            );

            return;
        }


        int row =
                usersTable.convertRowIndexToModel(
                        viewRow
                );


        int userId =
                ((Number)
                        tableModel.getValueAt(
                                row,
                                0
                        )
                ).intValue();


        Object[] user =
                UserDAO.getUserById(
                        userId
                );


        if (user == null) {

            showError(
                    "The selected user could not be loaded."
            );

            return;
        }


        JTextField txtFullName =
                createDialogTextField();


        JTextField txtUsername =
                createDialogTextField();


        txtFullName.setText(
                String.valueOf(
                        user[1]
                )
        );


        txtUsername.setText(
                String.valueOf(
                        user[2]
                )
        );


        JComboBox<String> cmbRole =
                createRoleCombo();


        String currentRole =
                String.valueOf(
                        user[3]
                );


        boolean roleFound =
                false;


        for (
                int i = 0;
                i < cmbRole.getItemCount();
                i++
        ) {

            if (
                    cmbRole
                            .getItemAt(i)
                            .equalsIgnoreCase(
                                    currentRole
                            )
            ) {

                cmbRole.setSelectedIndex(
                        i
                );

                roleFound = true;

                break;
            }
        }


        if (!roleFound) {

            cmbRole.addItem(
                    currentRole
            );

            cmbRole.setSelectedItem(
                    currentRole
            );
        }


        JComboBox<String> cmbStatus =
                new JComboBox<>(
                        new String[]{
                            "Active",
                            "Inactive"
                        }
                );


        styleComboBox(
                cmbStatus
        );


        cmbStatus.setSelectedItem(
                String.valueOf(
                        user[4]
                )
        );


        JPanel form =
                createUserFormPanel();


        JLabel idLabel =
                new JLabel(
                        String.valueOf(
                                userId
                        )
                );

        idLabel.setForeground(
                DEEP_GREEN
        );

        idLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );


        addFormField(
                form,
                "User ID",
                idLabel
        );


        addFormField(
                form,
                "Full Name",
                txtFullName
        );


        addFormField(
                form,
                "Username",
                txtUsername
        );


        addFormField(
                form,
                "Role",
                cmbRole
        );


        addFormField(
                form,
                "Status",
                cmbStatus
        );


        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        form,
                        "AURORA | Edit User",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (
                option != JOptionPane.OK_OPTION
        ) {

            return;
        }


        String fullName =
                txtFullName
                        .getText()
                        .trim();


        String username =
                txtUsername
                        .getText()
                        .trim();


        String role =
                String.valueOf(
                        cmbRole.getSelectedItem()
                );


        String status =
                String.valueOf(
                        cmbStatus.getSelectedItem()
                );


        if (
                fullName.isEmpty()
                || username.isEmpty()
        ) {

            showWarning(
                    "Full Name and Username are required."
            );

            return;
        }


        if (
                username.contains(" ")
        ) {

            showWarning(
                    "Username cannot contain spaces."
            );

            return;
        }


        if (
                UserDAO.usernameExistsForOtherUser(
                        username,
                        userId
                )
        ) {

            showWarning(
                    "Another user already uses this username."
            );

            return;
        }


        boolean success =
                UserDAO.updateUser(
                        userId,
                        fullName,
                        username,
                        role,
                        status
                );


        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "User updated successfully.",
                    "AURORA | User Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );


            loadUsers();

        } else {

            showError(
                    "The user could not be updated."
            );
        }
    }


    // =========================================================
    // CHANGE PASSWORD
    // =========================================================

    private void changeSelectedPassword() {

        int viewRow =
                usersTable.getSelectedRow();


        if (viewRow == -1) {

            showWarning(
                    "Please select a user first."
            );

            return;
        }


        int row =
                usersTable.convertRowIndexToModel(
                        viewRow
                );


        int userId =
                ((Number)
                        tableModel.getValueAt(
                                row,
                                0
                        )
                ).intValue();


        String fullName =
                String.valueOf(
                        tableModel.getValueAt(
                                row,
                                1
                        )
                );


        JPasswordField txtPassword =
                createDialogPasswordField();


        JPasswordField txtConfirm =
                createDialogPasswordField();


        JPanel form =
                createUserFormPanel();


        JLabel userLabel =
                new JLabel(
                        fullName
                );

        userLabel.setForeground(
                DEEP_GREEN
        );

        userLabel.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        14
                )
        );


        addFormField(
                form,
                "User",
                userLabel
        );


        addFormField(
                form,
                "New Password",
                txtPassword
        );


        addFormField(
                form,
                "Confirm Password",
                txtConfirm
        );


        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        form,
                        "AURORA | Change Password",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (
                option != JOptionPane.OK_OPTION
        ) {

            return;
        }


        String password =
                new String(
                        txtPassword.getPassword()
                );


        String confirm =
                new String(
                        txtConfirm.getPassword()
                );


        if (password.isEmpty()) {

            showWarning(
                    "Please enter a new password."
            );

            return;
        }


        if (
                password.length() < 4
        ) {

            showWarning(
                    "Password must contain at least 4 characters."
            );

            return;
        }


        if (
                !password.equals(
                        confirm
                )
        ) {

            showWarning(
                    "The passwords do not match."
            );

            return;
        }


        boolean success =
                UserDAO.updatePassword(
                        userId,
                        password
                );


        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password changed successfully.",
                    "AURORA | Password Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            showError(
                    "The password could not be changed."
            );
        }
    }


    // =========================================================
    // DELETE SELECTED USER
    // =========================================================

    private void deleteSelectedUser() {

        int viewRow =
                usersTable.getSelectedRow();


        if (viewRow == -1) {

            showWarning(
                    "Please select a user to delete."
            );

            return;
        }


        int row =
                usersTable.convertRowIndexToModel(
                        viewRow
                );


        int userId =
                ((Number)
                        tableModel.getValueAt(
                                row,
                                0
                        )
                ).intValue();


        String fullName =
                String.valueOf(
                        tableModel.getValueAt(
                                row,
                                1
                        )
                );


        String username =
                String.valueOf(
                        tableModel.getValueAt(
                                row,
                                2
                        )
                );


        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete this AURORA user?\n\n"
                        + fullName
                        + "\n@"
                        + username
                        + "\n\n"
                        + "This action cannot be undone.",
                        "AURORA | Delete User",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );


        if (
                confirm != JOptionPane.YES_OPTION
        ) {

            return;
        }


        boolean success =
                UserDAO.deleteUser(
                        userId
                );


        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "User deleted successfully.",
                    "AURORA | User Deleted",
                    JOptionPane.INFORMATION_MESSAGE
            );


            loadUsers();

        } else {

            showError(
                    "The user could not be deleted."
            );
        }
    }


// =========================================================
// USERS HERO BANNER
// =========================================================

private JPanel createUsersHero() {

    ImageIcon imageIcon = null;


    try {

        java.net.URL imageURL =
                getClass().getResource(
                        "/auroraclothing/images/users-banner.png"
                );


        if (imageURL != null) {

            imageIcon =
                    new ImageIcon(
                            imageURL
                    );

        } else {

            System.out.println(
                    "users-banner.png not found."
            );
        }

    } catch (Exception e) {

        System.out.println(
                "Could not load users banner."
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


                    // =========================================
                    // HIGH QUALITY RENDERING
                    // =========================================

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


                    // =========================================
                    // ROUNDED HERO SHAPE
                    // =========================================

                    java.awt.geom.RoundRectangle2D rounded =
                            new java.awt.geom.RoundRectangle2D.Double(
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


                    // =========================================
                    // BASE GREEN
                    // =========================================

                    g2.setColor(
                            DARK_GREEN
                    );

                    g2.fillRect(
                            0,
                            0,
                            w,
                            h
                    );


                    // =========================================
                    // DRAW USERS IMAGE
                    // =========================================

                    if (bannerImage != null) {

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


                    // =========================================
                    // DARK GREEN LEFT BLEND
                    // Makes hero text easy to read
                    // =========================================

                    GradientPaint leftBlend =
                            new GradientPaint(

                                    0,
                                    0,

                                    new Color(
                                            35,
                                            49,
                                            35,
                                            245
                                    ),

                                    (int) (
                                            w * 0.62
                                    ),

                                    0,

                                    new Color(
                                            35,
                                            49,
                                            35,
                                            0
                                    )
                            );


                    g2.setPaint(
                            leftBlend
                    );


                    g2.fillRect(
                            0,
                            0,
                            w,
                            h
                    );


                    // =========================================
                    // SUBTLE BOTTOM SHADOW
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


                    // =========================================
                    // GOLD BORDER
                    // =========================================

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
                    20,
                    26,
                    20,
                    26
            )
    );


    hero.setPreferredSize(
            new Dimension(
                    900,
                    135
            )
    );


    // =========================================================
    // HERO TEXT
    // =========================================================

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
                    "AURORA ACCESS CONTROL"
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
                    "People Behind AURORA"
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
                    "Manage secure access, responsibilities and user accounts."
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
            Box.createVerticalStrut(4)
    );

    heroText.add(
            mainTitle
    );

    heroText.add(
            Box.createVerticalStrut(4)
    );

    heroText.add(
            description
    );

    heroText.add(
            Box.createVerticalStrut(6)
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
    // USER FORM PANEL
    // =========================================================

    private JPanel createUserFormPanel() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );


        panel.setBackground(
                IVORY
        );


        panel.setBorder(
                new EmptyBorder(
                        16,
                        18,
                        16,
                        18
                )
        );


        return panel;
    }


    // =========================================================
    // ADD FIELD TO FORM
    // =========================================================

    private void addFormField(
            JPanel panel,
            String labelText,
            JComponent component
    ) {

        GridBagConstraints gbc =
                new GridBagConstraints();


        int row =
                panel.getComponentCount()
                / 2;


        JLabel label =
                new JLabel(
                        labelText
                );


        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );


        label.setForeground(
                MUTED
        );


        gbc.gridx =
                0;

        gbc.gridy =
                row;

        gbc.weightx =
                0;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        7,
                        0,
                        7,
                        15
                );


        panel.add(
                label,
                gbc
        );


        gbc.gridx =
                1;

        gbc.weightx =
                1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        7,
                        0,
                        7,
                        0
                );


        component.setPreferredSize(
                new Dimension(
                        250,
                        34
                )
        );


        panel.add(
                component,
                gbc
        );
    }


    // =========================================================
    // DIALOG TEXT FIELD
    // =========================================================

    private JTextField createDialogTextField() {

        JTextField field =
                new JTextField();


        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );


        field.setForeground(
                TEXT
        );


        field.setBackground(
                Color.WHITE
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        new EmptyBorder(
                                6,
                                10,
                                6,
                                10
                        )
                )
        );


        return field;
    }


    // =========================================================
    // PASSWORD FIELD
    // =========================================================

    private JPasswordField createDialogPasswordField() {

        JPasswordField field =
                new JPasswordField();


        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );


        field.setForeground(
                TEXT
        );


        field.setBackground(
                Color.WHITE
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        new EmptyBorder(
                                6,
                                10,
                                6,
                                10
                        )
                )
        );


        return field;
    }


    // =========================================================
    // ROLE COMBO
    // =========================================================

    private JComboBox<String> createRoleCombo() {

        JComboBox<String> combo =
                new JComboBox<>(
                        new String[]{
                            "Administrator",
                            "Manager",
                            "Staff"
                        }
                );


        styleComboBox(
                combo
        );


        return combo;
    }


    // =========================================================
    // COMBO STYLE
    // =========================================================

    private void styleComboBox(
            JComboBox<String> combo
    ) {

        combo.setBackground(
                IVORY
        );


        combo.setForeground(
                DEEP_GREEN
        );


        combo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );


        combo.setFocusable(
                false
        );
    }


    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private JButton createPrimaryButton(
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
                        GOLD
                )
        );


        button.setPreferredSize(
                new Dimension(
                        145,
                        38
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


        button.setBackground(
                new Color(
                        111,
                        66,
                        59
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


        button.setPreferredSize(
                new Dimension(
                        120,
                        38
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
    // WARNING
    // =========================================================

    private void showWarning(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "AURORA | Attention",
                JOptionPane.WARNING_MESSAGE
        );
    }


    // =========================================================
    // ERROR
    // =========================================================

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "AURORA | Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}