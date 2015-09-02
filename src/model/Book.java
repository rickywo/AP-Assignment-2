package model;

/**
 * @author Ricky Wu
 *
 */
public class Book implements LibraryBook, Printable {
	
	private static final String NULL = "null",
			DAYS 	= "Day(s)",
			TRUE 	= "true",
			FALSE 	= "false",
			TITLE 	= "Title",
			NUMBER 	= "Number",
			AUTHOR 	= "Author",
			PERIOD 	= "Loan Period",
			BORROWER 		= "Borrower ID",
			AVAILABILITY 	= "Availability";
	
	private static final int BASICPERIOD = 14;
			
			
	private String 	number, title, author;

	private int 	loanPeriod;
	private boolean availability;
	private LibraryMember 	borrower;

	
	public Book(String number, String title, String authour, int loanPeriod) {
		super();
		this.number 	= number;
		this.title 		= title;
		this.author 	= authour;
		this.loanPeriod = loanPeriod;
		this.availability = true;
		this.borrower 	= null;
	}
	
	

	public Book(String number, String title, String authour) {
		super();
		this.number 	= number;
		this.title 		= title;
		this.author 	= authour;
		this.loanPeriod = BASICPERIOD;
		this.availability = true;
		this.borrower 	= null;
		
	}



	@Override
	public String getBookNumber() {
		return number;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}

	@Override
	public String getBorrowerID() {
		String bID = NULL;
		if(this.borrower != null) {
			bID = this.borrower.getMemberID();
		}
		return bID;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	@Override
	public boolean isAvailable() {
		return availability;
	}

	@Override
	public void borrowBook(LibraryMember member) throws BookException {
		if(!isAvailable()) {
			throw new BookException(Error.HAS_BORROWED.toString());
		} else {
			setAvailability(false);
			this.borrower = member;
		}
	}

	@Override
	public void returnBook() {
		setAvailability(true);
		this.borrower = null;
	}

	public void setAvailability(boolean b) {
		this.availability = b;
	}
	
	
	/**
	 * @param LibraryMember Object
	 * Necessary for parsing the data from database
	 */
	public void setBorrower(LibraryMember m) {
		this.borrower = m;
	}
	
	@Override
	public void print() {
		String bookinfo = String.format (
				"%s: %s\n%s: %s\n%s: %s\n%s: %d %s\n%s: %s\n%s: %s",
				TITLE, title,
				NUMBER, number,
				AUTHOR, author,
				PERIOD, loanPeriod, DAYS,
				AVAILABILITY, isAvailable()?TRUE:FALSE,
				BORROWER, getBorrowerID() 
		);
		System.out.printf("%s\n", bookinfo);
	}

}
