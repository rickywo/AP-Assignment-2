/**
 * 
 */
package model;

/**
 * @author Ricky Wu
 *
 */
public class Textbook extends Book {
	
	private static final int LOANLIMIT = 2;
	private static final String TYPE = "Book Type",
			TEXTBOOK ="Textbook",
			COURSECODE = "Course code";
	private String courseCode;

	/**
	 * @param number
	 * @param title
	 * @param authour
	 * @param loanPeriod
	 */
	public Textbook(String number, String title, String authour, String courseCode) {
		super(number, title, authour, LOANLIMIT);
		// TODO Auto-generated constructor stub
		this.courseCode = courseCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf (
			"%s: %s\n%s: %s\n",
			TYPE, TEXTBOOK,
			COURSECODE, this.courseCode
		);
		super.print();
	}
}
