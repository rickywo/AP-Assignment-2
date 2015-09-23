package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LibraryMainUI extends JFrame {
	private final static int PANEL_COUNT = 5;
	private JPanel panel1; // Register Member
	private JPanel panel2; // Borrow Book
	private JPanel panel3; // Return Book
	private JPanel panel4; // Pay Fine
	private JPanel panel5; // Browse Book

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryMainUI frame = new LibraryMainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibraryMainUI() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
