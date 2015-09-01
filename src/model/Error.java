package model;

public enum Error {
	HAS_BORROWED("Book has already been borrowed!"),
	FINES_OWED("Fines owing must be paid before student can borrow!"), 
	STUDENT_LIMIT_EXCEEDED("Student book limit exceeded!"), 
	STAFF_LIMIT_EXCEEDED("Staff Book limit exceeded!"), 
	NOT_BORROWED("Book has not been borrowed by this member!"), 
	HAS_OVERDUE("Staff member has recent overdue books!"), 
	BOOK_OVERDUE("Book overdue - all other books must be returned before staff member can borrow!");

	private final String description;

	private Error(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}
}