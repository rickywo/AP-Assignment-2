package model;

import java.util.ArrayList;

public class Student extends Member {
	static final String
			MEMBERTYPE = "Member Type:",
			STUDENT = "Student",
			FINESOWING = "Fine Owning";
	static final int BOOKLIMIT = 2,
			TEXTBOOKLIMIT = 1;

	private double finesOwing;

	public Student(String id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
		// TODO Auto-generated constructor stub
		finesOwing = 0;
	}

	@Override
	public void borrowBook(LibraryBook b) throws LoanException {
		// TODO Auto-generated method stub
		ArrayList<LibraryBook> booklist;
		booklist = this.getBooklist();
		
		if(this.finesOwing > 0) {
			throw new LoanException(Error.FINES_OWED.toString());
		} else if(isReachLimit(b)) {
			throw new LoanException(Error.STUDENT_LIMIT_EXCEEDED.toString());
		} else {
			// Handling exception of illegally borrowing book 
			try {
				b.borrowBook(this);
			} catch (BookException be) {
				throw new LoanException(be.getMessage( ));
			}
			booklist.add(b);
		}
	}

	@Override
	public void returnBook(String bookNumber, int days) throws LoanException {
		// TODO Auto-generated method stub
		ArrayList<LibraryBook> booklist;
		LibraryBook b = getBookfromBorrowed(bookNumber);
		booklist = this.getBooklist();
		if(b == null) {
			throw new LoanException(Error.NOT_BORROWED.toString());
		} else {
			b.returnBook();
			booklist.remove(b);
			if(days > b.getLoanPeriod()) {
				String err ;
				err = String.format (
						"Book overdue- fine: $%.02f, fines owing: $%.02f", 
						5.0, this.finesOwing
					);
				this.finesOwing += 5.0;
				throw new LoanException(err);
			}
		}
	}
	
	public double getFinesOwing() {
		return finesOwing;
	}

	public void setFinesOwing(double finesOwing) {
		this.finesOwing = finesOwing;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("%s: %s\n", MEMBERTYPE, STUDENT);
		super.print();
		System.out.printf("%s: $%.1f\n",FINESOWING);
		
	}

	public double payFine(double amount) {
		double change = 0;
		if(amount > finesOwing) {
			change = amount - finesOwing;
			finesOwing = 0;
		} else {
			finesOwing -= amount;
		}
		return change;
	}
	
	public boolean isReachLimit(LibraryBook t) {
		boolean isReached;
		int bookCount = 0, textbookCount = 0;
		ArrayList<LibraryBook> booklist = getBorrowedBooks();
		bookCount = booklist.size();
		// count textbook borrowed
		for(LibraryBook b: booklist) {
			if(b instanceof Textbook) textbookCount ++;
		}
		// count total book borrowed
		if(bookCount >= BOOKLIMIT) {
			// Reach the limit of borrowing book
			isReached = true;
		} else if(t instanceof Textbook && textbookCount >= TEXTBOOKLIMIT) {
			// Reach the limit of borrowing book if book to be borrowed if Textbook 
			isReached = true;
		} else {
			isReached = false;
		}
		return isReached;
	}
}
