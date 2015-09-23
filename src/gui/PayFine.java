package gui;

import model.Log;

import javax.swing.*;
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
    private ActionListener mListener = new ActionListener (){

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO register new member into library system
            Log.d(PAY_FINE);
        }

    };
	/**
	 * Create the panel.
	 */
	public PayFine() {
        initContent();
	}

    public void initContent() {
        add(select_member_label);
        add(member_list);
        add(member_id_label);
        add(member_id_value);
        add(member_name_label);
        add(member_name_value);
        add(fines_owing_label);
        add(fines_owing_value);
        add(amount_paid_label);
        add(amount_paid_textfield);
        add(pay_button);
    }

}
