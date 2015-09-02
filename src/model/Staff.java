package model;

import java.util.ArrayList;

public class Staff extends Member {
	
	static final String MEMBERTYPE = "Member Type",
			STAFF = "Staff",
			BOOKOVERDUE = "Book Overdue",
			TRUE = "True",
			FALSE = "False";
	
	static final int BOOKLIMIT = 4;
	
	private boolean bookOverdue;

	public Staff(String id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
		bookOverdue = false;
	}

	@Override
	public void borrowBook(LibraryBook b) throws LoanException {
		ArrayList<LibraryBook> booklist;
		booklist = this.getBooklist();
		if(isBookOverdue()) {
			throw new LoanException(Error.HAS_OVERDUE.toString());
		}
		if(isReachLimit(b)) {
			throw new LoanException(Error.STAFF_LIMIT_EXCEEDED.toString());
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
		booklist = this.getBooklist();
		if(b == null) {
			throw new LoanException(Error.NOT_BORROWED.toString());
		} else {
			b.returnBook();
			booklist.remove(b);
			if(booklist.size() > 0 && days > b.getLoanPeriod()) {
				setBookOverdue(true);
				throw new LoanException(Error.BOOK_OVERDUE.toString());
			}
			setBookOverdue(false);
		}

	}

	@Override
	public void print() {
		System.out.printf("%s: %s\n", MEMBERTYPE, STAFF);
		super.print();
		System.out.printf("%s: %s\n",BOOKOVERDUE, isBookOverdue()?TRUE:FALSE);
	}

	public boolean isBookOverdue() {
		return bookOverdue;
	}

	public void setBookOverdue(boolean bookOverdue) {
		this.bookOverdue = bookOverdue;
	}
	
	public boolean isReachLimit(LibraryBook t) {
		boolean isReached;
		ArrayList<LibraryBook> booklist = getBorrowedBooks();
		if(booklist.size() >= BOOKLIMIT) {
			// Reach the limit of borrowing book
			isReached = true;
		} else {
			isReached = false;
		}
		return isReached;
	}

}
