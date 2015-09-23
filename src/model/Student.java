package model;

import java.util.ArrayList;

public class Student extends Member {
	static final String
			MEMBERTYPE = "Member Type",
			STUDENT = "Student",
			FINESOWING = "Fine Owning";
	static final int BOOKLIMIT = 2,
			TEXTBOOKLIMIT = 1;

	private double finesOwing;

	public Student(String id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
		finesOwing = 0;
	}

	@Override
	public void borrowBook(LibraryBook b) throws LoanException {
		ArrayList<LibraryBook> booklist;
		booklist = this.getBooklist();
		
		if(this.finesOwing > 0) {
			throw new LoanException(Error.FINES_OWED.toString());
		} else if(isReachLimit(b)) {
			throw new LoanException(Error.STUDENT_LIMIT_EXCEEDED.toString());
		} else if(isTextbookReachLimit(b)) {
			throw new LoanException(Error.STUDENT_TEXTBOOK_LIMIT_EXCEEDED.toString());
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
		ArrayList<LibraryBook> booklist;
		LibraryBook b = getBookfromBorrowed(bookNumber);
		int overdue_days = days - b.getLoanPeriod();
		booklist = this.getBooklist();
		if(b == null) {
			throw new LoanException(Error.NOT_BORROWED.toString());
		} else {
			b.returnBook();
			booklist.remove(b);
			if(overdue_days > 0) {
				// Calculate fine for overdue: $5 fine will applied if overdue within a week
				// ($5: 1-7 days, $10: 8-14 days etc.)
				
				double owing;
				if(b instanceof Textbook) {
					owing = overdue_days * 2;
				} else {
					owing = ((overdue_days / 7)+1) * 5.0;
				}
				this.finesOwing = this.finesOwing + owing;
				String err ;
				err = String.format (
						"Book overdue- fine: $%.02f, fines owing: $%.02f", 
						 owing, this.finesOwing
					);
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
		System.out.printf("%s: %s\n", MEMBERTYPE, STUDENT);
		super.print();
		System.out.printf("%s: $%.1f\n",FINESOWING, finesOwing);
		
	}

	public double payFine(double amount) {
		double change = 0;
		if(amount > finesOwing) {
			change = amount - finesOwing;
			finesOwing = 0;
		} else {
			finesOwing -= amount;
		}
		print();
		return change;
	}
	
	public boolean isReachLimit(LibraryBook t) {
		boolean isReached;
		int bookCount = 0;
		ArrayList<LibraryBook> booklist = getBorrowedBooks();
		bookCount = booklist.size();
		// count total book borrowed
		if(bookCount >= BOOKLIMIT) {
			// Reach the limit of borrowing book
			isReached = true;
		} else {
			isReached = false;
		}
		return isReached;
	}
	
	public boolean isTextbookReachLimit(LibraryBook t) {
		boolean isReached;
		int textbookCount = 0;
		ArrayList<LibraryBook> booklist = getBorrowedBooks();
		// count textbook borrowed
		for(LibraryBook b: booklist) {
			if(b instanceof Textbook) textbookCount ++;
		}
		// count total book borrowed
		if(t instanceof Textbook && textbookCount >= TEXTBOOKLIMIT) {
			// Reach the limit of borrowing book if book to be borrowed if Textbook 
			isReached = true;
		} else {
			isReached = false;
		}
		return isReached;
	}
}
