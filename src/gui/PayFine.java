package gui;

import model.LibraryMember;
import model.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayFine extends JPanel {

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

    private JList member_list = new JList();
    private JLabel select_member_label = new JLabel(SELECT_MEMBER);
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
            controller.payFine(member_list.getSelectedValue().toString(),
                    Double.parseDouble(amount_paid_textfield.getText()));
        }

    };

    private ListSelectionListener listSelectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList) e.getSource();
            if (list.getValueIsAdjusting()) {
                // get Book number from list and pass to controller for getting
                // book instance

                LibraryMember m = controller.getMemberByID(list.getSelectedValue().toString());
                System.out.println(m.getMemberID());
                displayMemberDetails(m);
                pay_button.setEnabled(true);
            }
        }
    };

    /**
     * Create the panel.
     */
    public PayFine(LibraryController controller) {
        this.controller = controller;
        initContent();
    }

    private void displayMemberDetails(LibraryMember member) {
        Student s = (Student) member;
        member_id_value.setText(s.getMemberID());
        member_name_value.setText(s.getName());
        fines_owing_value.setText(String.valueOf(s.getFinesOwing()));
    }

    public void initContent() {
        add(select_member_label);
        initMemberlist();
        add(member_id_label);
        add(member_id_value);
        add(member_name_label);
        add(member_name_value);
        add(fines_owing_label);
        add(fines_owing_value);
        add(amount_paid_label);
        add(amount_paid_textfield);
        initButton();

    }


    private void initMemberlist() {
        add(member_list);
        member_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        member_list.setListData(controller.getStudentsIDArray());
        member_list.addListSelectionListener(listSelectionListener);
    }

    private void initButton() {
        add(pay_button);
        pay_button.setEnabled(false);
        pay_button.addActionListener(mListener);
    }
}
