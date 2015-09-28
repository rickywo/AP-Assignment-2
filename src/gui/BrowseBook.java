package gui;

import model.Book;
import model.LibraryBook;
import model.Log;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BrowseBook extends JPanel {

	private static final String BROWSE_BOOK = "Browse Books";
	private static final String ENTER_KEYWORDS = "Enter keyword(s)";
	private static final String MATCHING_BOOKS = "Matching books";
	private static final String BOOK_NUMBER = "Book Number";
	private static final String TITLE = "Title";
	private static final String AUTHOR = "Author";
	private static final String AVAILABLE = "Available";
	private static final String BORROWER_ID = "Borrower ID";
    private static final String YES = "Yes";
    private static final String NO = "No";

    public static final String TAB_TITLE = BROWSE_BOOK;

    // Controller
    private LibraryController controller;

    private JPanel north = new JPanel();
    private JPanel center = new JPanel();
    private JPanel west = new JPanel();
    private JScrollPane scrollPane = new JScrollPane();

    // Enter Keyword components
    private JLabel enter_keyword_label = new JLabel(ENTER_KEYWORDS);
    private JTextField keyword_textfield = new JTextField();
    // Macing book components
    private JLabel matching_label = new JLabel(MATCHING_BOOKS, JLabel.LEFT);
    private JList matching_list = new JList();
    // Book number components
    private JLabel book_number_label = new JLabel(BOOK_NUMBER);
    private JLabel book_number_value = new JLabel();
    // Book title components
    private JLabel book_title_label = new JLabel(TITLE);
    private JLabel book_title_value = new JLabel();
    // Book Author components
    private JLabel book_author_label = new JLabel(AUTHOR);
    private JLabel book_author_value = new JLabel();
    // Availability components
    private JLabel book_availability_label = new JLabel(AVAILABLE);
    private JLabel book_availability_value = new JLabel();
    // Borrower ID components
    private JLabel borrower_id_label = new JLabel(BORROWER_ID);
    private JLabel borrower_id_value = new JLabel();

    private KeyListener mKeyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            //matching_list.setListData(controller.getBooksIDArrayByMatchingTitle(keyword_textfield.getText()));
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            //if(e.getKeyCode() == KeyEvent.VK_ENTER){
                matching_list.setListData(controller.getBooksIDArrayByMatchingTitle(keyword_textfield.getText()));
                Log.d(keyword_textfield.getText());
            //}
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
	public BrowseBook(LibraryController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        initContent();
	}

    private void initContent() {
        add(matching_label);
        initBooklist();
        initNorthView();
        initWestView();
        initCenterView();
        add(north, BorderLayout.PAGE_START);
        add(west, BorderLayout.LINE_START);
        add(center, BorderLayout.CENTER);

    }

    private void initNorthView() {
        north.setLayout(new GridBagLayout());
        GridBagConstraints bc = new GridBagConstraints();
        bc.gridx = 0;
        bc.gridy = 0;
        bc.gridwidth =1;
        bc.gridheight =1;
        north.add(enter_keyword_label, bc);
        bc.gridx = 1;
        bc.gridy = 0;
        bc.gridwidth =3;
        bc.gridheight =1;
        bc.fill = GridBagConstraints.HORIZONTAL;
        bc.weightx = 1;
        north.add(keyword_textfield, bc);
        keyword_textfield.addKeyListener(mKeyListener);
    }

    private void initWestView() {
        west.setLayout(new BoxLayout(west , BoxLayout.Y_AXIS));
        west.add(matching_label);
        west.add(scrollPane);
    }

    private void initCenterView() {
        center.setLayout(new GridLayout(0, 2, 10, 10));
        // Book number components
        center.add(book_number_label);
        center.add(book_number_value);
        // Book title components
        center.add(book_title_label);
        center.add(book_title_value);
        // Book author components
        center.add(book_author_label);
        center.add(book_author_value);
        // Book availability components
        center.add(book_availability_label);
        center.add(book_availability_value);
        // Borrow ID components
        center.add(borrower_id_label);
        center.add(borrower_id_value);
    }

    private void initBooklist() {
        scrollPane.setViewportView(matching_list);
        matching_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matching_list.setListData(controller.getBooksIDArray());
        matching_list.addListSelectionListener(listSelectionListener);
    }

    private void displayBookDetails(LibraryBook book) {
        Book b = (Book)book;
        book_number_value.setText(b.getBookNumber());
        book_title_value.setText(b.getTitle());
        book_author_value.setText(b.getAuthor());
        book_availability_value.setText(b.isAvailable() ? YES : NO);
        borrower_id_value.setText(b.getBorrowerID());
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

}
