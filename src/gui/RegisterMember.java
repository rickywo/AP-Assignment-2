package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Log;

public class RegisterMember extends JPanel{
	private static final String REGISTER_MEMBER = "Register Member";
	private static final String HINT_MESSAGE = "Enter details of the new Member to be registered";
	private static final String MEMBER_ID = "Member ID";
	private static final String MEMBER_NAME = "Member Name";
	private static final String CONTACT_NUMBER = "Contact Number";
	private static final String STUDENT = "Student";
	private static final String STAFF = "Staff";
	/**
	 * Create the panel.
	 */
	private JLabel hint_label;
	private JTextField id_textfield;
	private JTextField name_textfield;
	private JTextField contact_textfield;
	private JRadioButton vocation_radiobutton;
	private JButton register_button;
	private ActionListener mListener = new ActionListener (){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Log.d(REGISTER_MEMBER);
		}
		
	};
	
	public RegisterMember() {

	}
	
	private void initContent() {
		hint_label = new JLabel(HINT_MESSAGE);
		id_textfield = new JTextField(MEMBER_ID);
		
	}

}
