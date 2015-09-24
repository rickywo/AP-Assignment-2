package gui;

import model.Book;
import model.LibraryBook;
import model.Log;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private static final String YES = "Yes";
    private static final String NO = "No";

	public static final String TAB_TITLE = RETURN_BOOK;

    // Controller
    private LibraryController controller;

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

    private ListSelectionListener listSelectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList) e.getSource();
            if (list.getValueIsAdjusting()) {
                // get Book number from list and pass to controller for getting
                // book instance

                LibraryBook b = controller.getBookByID(list.getSelectedValue().toString());
                System.out.println(b.getBookNumber());
                displayBookDetails(b);
            }
        }
    };
	/**
	 * Create the panel.
	 */
	public ReturnBook(LibraryController controller) {
        this.controller = controller;
        initContent();
	}

    private void initContent() {
        add(select_book_label);
        initBooklist();
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

    private void initBooklist() {
        add(book_list);
        book_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        book_list.setListData(controller.getBooksIDArray());
        book_list.addListSelectionListener(listSelectionListener);
    }

    private void displayBookDetails(LibraryBook book) {
        Book b = (Book)book;
        book_number_value.setText(b.getBookNumber());
        book_title_value.setText(b.getTitle());
        book_author_value.setText(b.getAuthor());
        book_availability_value.setText(b.isAvailable() ? YES : NO);
    }

}
