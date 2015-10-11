package gui;

import model.LibraryMember;
import model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayFine extends JPanel implements ListViewPanel {

    private static final String PAY_FINE = "Pay Fine";
    private static final String MEMBER_ID = "Member ID";
    private static final String MEMBER_NAME = "Member Name";
    private static final String FINES_OWING = "Fines Owing";
    private static final String AMOUNT_PAID = "Amount paid";
    private static final String SELECT_MEMBER = "Select member";

    public static final String TAB_TITLE = PAY_FINE;

    // Controller
    private LibraryController controller;
    // private variables
    private JPanel west = new JPanel();
    private JPanel center = new JPanel();
    private JScrollPane scrollPane = new JScrollPane();

    private JList member_list = new JList();
    private JLabel select_member_label = new JLabel(SELECT_MEMBER, JLabel.LEFT);
    // Member ID components
    private JLabel member_id_label = new JLabel(MEMBER_ID);
    private JLabel member_id_value = new JLabel();
    // Member name components
    private JLabel member_name_label = new JLabel(MEMBER_NAME);
    private JLabel member_name_value = new JLabel();
    // Fines owing components
    private JLabel fines_owing_label = new JLabel(FINES_OWING);
    private JLabel fines_owing_value = new JLabel();
    // Amount paid components
    private JLabel amount_paid_label = new JLabel(AMOUNT_PAID);
    private JTextField amount_paid_textfield = new JTextField();

    private JButton pay_button = new JButton(PAY_FINE);
    private ActionListener mListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO register new member into library system
            try {
                controller.payFine(member_list.getSelectedValue().toString(),
                        Double.parseDouble(amount_paid_textfield.getText()));
            } catch (NumberFormatException ne) {
                showDialog("Input format error, please check \"" +AMOUNT_PAID+"\" entered.");
            }
            displayMemberDetails();
        }

    };

    private ListSelectionListener listSelectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (member_list.getValueIsAdjusting()) {
                // get Book number from list and pass to controller for getting
                // book instance
                displayMemberDetails();
                pay_button.setEnabled(true);
            }
        }
    };

    /**
     * Create the panel.
     */
    public PayFine(LibraryController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 0));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        initContent();
    }

    private void displayMemberDetails() {
        LibraryMember m = controller.getMemberByID(member_list.getSelectedValue().toString());
        Student s = (Student) m;
        member_id_value.setText(s.getMemberID());
        member_name_value.setText(s.getName());
        fines_owing_value.setText(String.valueOf(s.getFinesOwing()));
    }

    public void initContent() {
        initMemberlist();
        initWestView();
        initCenterView();
        add(west, BorderLayout.LINE_START);
        add(center, BorderLayout.CENTER);
    }

    private void initWestView() {
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        west.add(select_member_label);
        west.add(scrollPane);
    }

    private void initCenterView() {
        center.setLayout(new GridLayout(0, 2, 10, 0));
        // Member ID components
        center.add(member_id_label);
        center.add(member_id_value);
        // Member name components
        center.add(member_name_label);
        center.add(member_name_value);
        // Fines owing components
        center.add(fines_owing_label);
        center.add(fines_owing_value);
        // Amount paid components
        center.add(amount_paid_label);
        center.add(amount_paid_textfield);
        center.add(new JPanel()); // Dummy item
        // Pay button
        center.add(pay_button);
        pay_button.setEnabled(false);
        pay_button.addActionListener(mListener);
    }


    private void initMemberlist() {
        scrollPane.setViewportView(member_list);
        member_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        member_list.addListSelectionListener(listSelectionListener);
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void loadData() {
        member_list.setListData(controller.getStudentsIDArray());
    }


}
