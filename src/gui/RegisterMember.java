package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.LibraryMember;
import model.Log;
import model.Staff;
import model.Student;

public class RegisterMember extends JPanel{

	private static final String REGISTER_MEMBER = "Register Member";
	private static final String HINT_MESSAGE = "Enter details of the new Member to be registered";
	private static final String MEMBER_ID = "Member ID";
	private static final String MEMBER_NAME = "Member Name";
	private static final String CONTACT_NUMBER = "Contact Number";
	private static final String STUDENT = "Student";
	private static final String STAFF = "Staff";

    public static final String TAB_TITLE = REGISTER_MEMBER;
	/**
	 * Create the panel.
	 */

    // Controller
    private LibraryController controller;
	private JLabel hint_label = new JLabel(REGISTER_MEMBER);
    // Member ID component
    private JLabel id_label = new JLabel(MEMBER_ID);
	private JTextField id_textfield = new JTextField();
    // Member name component
    private JLabel name_label = new JLabel(MEMBER_NAME);
	private JTextField name_textfield = new JTextField();
    // Member contact number component
    private JLabel contact_label = new JLabel(CONTACT_NUMBER);
	private JTextField contact_textfield = new JTextField();
    // Vocation select radio group
	private JRadioButton student_radiobutton = new JRadioButton(STUDENT);
	private JRadioButton staff_radiobutton =new JRadioButton(STAFF);
	private ButtonGroup bg = new ButtonGroup();
    // Register button component
	private JButton register_button = new JButton(REGISTER_MEMBER);
	private ActionListener mListener = new ActionListener (){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO register new member into library system
            doRegister();
			Log.d(REGISTER_MEMBER);
		}
		
	};
	
	public RegisterMember(LibraryController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initContent();
	}
	
	private void initContent() {
		bg.add(staff_radiobutton);
		bg.add(student_radiobutton);
        add(hint_label);
        add(id_label);
        add(id_textfield);
        add(name_label);
        add(name_textfield);
        add(contact_label);
        add(contact_textfield);
        add(student_radiobutton);
        add(staff_radiobutton);
        add(register_button);
        register_button.addActionListener(mListener);
	}

	private void doRegister() {
        LibraryMember m;
        String id = id_textfield.getText(),
                name = name_textfield.getText(),
                phone = contact_textfield.getText();
		if(bg.getSelection().equals(staff_radiobutton.getModel())) {
			m = new Staff(id, name, phone);
		} else {
            m = new Student(id, name, phone);
        }
        controller.registerMember(m);
	}

}
