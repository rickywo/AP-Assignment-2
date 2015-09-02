package model;

import java.util.ArrayList;

/**
 * @author Ricky Wu
 *
 */
public abstract class Member implements LibraryMember, Printable {
	static final String NAME = "Name",
			ID = "ID",
			PHONENUMBER ="Phone Number",
			ITEMS = "Item on loan";
	
	private String id, name, phoneNumber;
	private ArrayList<LibraryBook> booklist;

	/**
	 * @param id:	Member ID
	 * @param name:	Member Name
	 * @param phoneNumber:	Member Phone Number
	 */
	public Member(String id, String name, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		// Initiating ArrayList reference for borrowed books
		this.booklist = new ArrayList<>();
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<LibraryBook> getBooklist() {
		return booklist;
	}

	public void setBooklist(ArrayList<LibraryBook> booklist) {
		this.booklist = booklist;
	}

	@Override
	public String getMemberID() {
		return id;
	}
	
	public void setMemberID(String id) {
		this.id = id;
	}

	@Override
	public abstract void borrowBook(LibraryBook b) throws LoanException;

	@Override
	public abstract void returnBook(String bookNumber, int days) throws LoanException;

	@Override
	public ArrayList<LibraryBook> getBorrowedBooks() {
		return booklist;
	}

	/* Prints member object as following format:
	 * ID: id
	 * Name: name
	 * Phone Number: phone number
	 * @see model.LibraryMember#print()
	 */
	@Override
	public void print() {
		String member = String.format("%s: %s\n%s: %s\n%s: %s",
				ID, id,
				NAME, name,
				PHONENUMBER, phoneNumber);
		String books = "";
		
		System.out.printf("%s\n", member);
		// Printing out booklist
		System.out.printf("%s: ", ITEMS);
		
		for(LibraryBook book: booklist) {
			books += (book.getBookNumber()+" ");
		}
		System.out.printf("%s\n", books);
	}
	
	public abstract boolean isReachLimit(LibraryBook t);
	
	public LibraryBook getBookfromBorrowed(String bookID) {
		LibraryBook book = null;
		ArrayList<LibraryBook> booklist = getBorrowedBooks(); 
		for(LibraryBook b: booklist) {
			if(bookID.equals(b.getBookNumber())) {
				book = b;
			}
		}
		return book;
	}
}
