package gui;

import model.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBook extends JPanel {
	private static final String RETURN_BOOK = "Return Book";
	private static final String SELECT_BOOK = "Select Book";
	private static final String BOOK_NUMBER = "Book Number";
	private static final String TITLE = "Title";
	private static final String AUTHOR = "Author";
	private static final String AVAILABLE = "Available";
	private static final String ENTER_MEMBER_ID = "Enter Member ID";

	public static final String TAB_TITLE = RETURN_BOOK;

    private JList book_list = new JList();
    private JLabel select_book_label = new JLabel(SELECT_BOOK);
    // Book Number components
    private JLabel book_number_label = new JLabel(BOOK_NUMBER);
    private JLabel book_number_value = new JLabel();
    // Book Title components
    private JLabel book_title_label = new JLabel(TITLE);
    private JLabel book_title_value = new JLabel();
    // Book Author components
    private JLabel book_author_label = new JLabel(AUTHOR);
    private JLabel book_author_value = new JLabel();
    // Availability components
    private JLabel book_availability_label = new JLabel(AVAILABLE);
    private JLabel book_availability_value = new JLabel();
    // Member ID components
    private JLabel member_id_label = new JLabel(ENTER_MEMBER_ID);
    private JTextField member_id_textfield = new JTextField();

    private JButton return_button = new JButton(RETURN_BOOK);
    private ActionListener mListener = new ActionListener (){

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO register new member into library system
            Log.d(RETURN_BOOK);
        }

    };
	/**
	 * Create the panel.
	 */
	public ReturnBook() {
        initContent();
	}

    private void initContent() {
        add(select_book_label);
        add(book_list);
        add(book_number_label);
        add(book_number_value);
        add(book_title_label);
        add(book_title_value);
        add(book_author_label);
        add(book_author_value);
        add(book_availability_label);
        add(book_availability_value);
        add(member_id_label);
        add(member_id_textfield);
        add(return_button);
    }

}
