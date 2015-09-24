package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


public class LibrarySystem implements LibraryModel {
	static final String STU = "STU",
			STA = "STA",
			BOOK = "Book",
			TEXTBOOK = "Textbook",
			NULL = "null",
			ITEM = "Item";
	static final int PAGE_ITEM_LIMIT = 3,
			STUDENT_TYPE_NUM = 1,
			STAFF_TYPE_NUM = 2;
	// Models
	BookModel bookModel;
	MemberModel memberModel;
	// Reference of Lists
	public Map<String, LibraryBook> bookMap;
	public Map<String, Member> memberMap;
	// Variables
	Scanner scan = new Scanner(System.in);
	
	
	
	public LibrarySystem() {
	}

	@Override
	public boolean addMember() {
		String id, name, phone;
		
		int type; // 1 for Student, 2 for Staff
		System.out.print("Please Chose a Type (1)Student (2)Staff: ");
		type = scanInt(2);
		id = String.format("%s-",(type == 1)?STU:STA);
		System.out.printf("Enter 3 digits of member ID : %s", id);
		id += scanString();
		if (memberMap.containsKey(id)) {
			System.out.println("Member is exist.");
			hold();
			return false;
		}
		System.out.print("Please enter a name: ");
		name = scanString();
		if(name.length() == 0) {
			System.out.println("Empty String is entered.");
			return false;
		}
		System.out.print("Please enter a contact phone number: ");
		phone = scanString();
		if(phone.length() == 0) {
			System.out.println("Empty String is entered.");
			return false;
		}
		if(type == STAFF_TYPE_NUM) {
			memberMap.put(id,new Staff(id, name, phone));
		} else {
			memberMap.put(id,new Student(id, name, phone));
		}
	    return true;
	}

    public void addMember(LibraryMember member) {
        if(member instanceof Staff) {
            memberMap.put(member.getMemberID(),
                    new Staff(member.getMemberID(),
                            ((Staff) member).getName(),
                            ((Staff) member).getPhoneNumber()));
        } else {
            memberMap.put(member.getMemberID(),
                    new Student(member.getMemberID(),
                            ((Student)member).getName(),
                            ((Student)member).getPhoneNumber()));
        }
    }

	@Override
	public LibraryMember getMember(String memberID) {
		try {
			return memberMap.get(memberID);
		} catch (Exception e){
			Log.e(e.getMessage());
			return null;
		}
	}

	@Override
	public void displayAllMembers() {
		printMap(memberMap);

	}

	@Override
	public LibraryBook getBook(String bookNumber) {
		try {
			return bookMap.get(bookNumber);
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public boolean borrowBook(String memberID, String bookNumber)
			throws LoanException {
		try	{
			getMember(memberID).borrowBook(getBook(bookNumber));
		} catch (LoanException e) {
			Log.e(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean returnBook(String memberID, String bookNumber, int daysBorrowed)
			throws LoanException {
		try	{
			getMember(memberID).returnBook(bookNumber, daysBorrowed);
		} catch (LoanException e) {
			Log.e(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void displayAllBooks() {
		printMap(bookMap);
	}
	
	@Override
	public void loadAllBooks() {
		bookModel = BookModel.getSingleton();
		// Get books as a Map from BookModel
		// Raw data without borrowing relation
		bookMap = bookModel.getBookMap();
	}

	@Override
	public void loadAllMembers() {
		memberModel = MemberModel.getSingleton();
		// Get members as a Map from MemberModel
		// Raw data without borrowing relation
		memberMap = memberModel.getMemberMap();
	}
	
	
	/** Assign all relations between books and its borrow
	 *  also make connection between Members and books
	 *  borrowed. This function must run after loadAllBooks
	 *  and loadAllMembers.
	 */
	public void initRelations() {
		LibraryBook tb;
		LibraryMember tm;
		
		// BookNumer and Borrower's ID Map
		// <BookNumber, MemberID>
		Map<String, String> bMap = bookModel.getBorrowMap();
		for (Map.Entry<String, String> entry : bMap.entrySet()) {
	    	try{
	        	tb = getBook(entry.getKey());
	        	tm = getMember(entry.getValue());
                tm.borrowBook(tb);
	        } catch (Exception e) {
	        	Log.e(e.getMessage());
	        }
	    	
		}
	}

	@Override
	public void saveAllBooks() {
		bookModel.save();
	}

	@Override
	public void saveAllMembers() {
		memberModel.save();
	}
	
	private void hold() {
		System.out.print("Press enter to continue...");
		scan.nextLine();
	}
	
	private int scanInt(int op) {
		int num = -1;
		// validate input
		do {
			String s;
			try {
				s = scan.nextLine();
				num = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				Log.e(e.getMessage());
				continue;
			}
		} while (num < 1 || num >= op + 1);
		return num;
	}
	
	private String scanString() {
		boolean flag = true;
		String result = "";
		// validate input
		do {
			String s;
			try {
				result = scan.nextLine();
				result = result.trim();
				if(result.length() == 0) {
					Log.w("Please re-enter a valid string:");
				} else {
					flag = false;
				}
			} catch (Exception e) {
				Log.e(e.getMessage());
				continue;
			}
		} while (flag);
		return result;
	}
	
	/**
	 * @param mp
	 * To print objects in Map structure
	 * T must has implemented Printable interface
	 * Print PAGE_ITEM_LIMIT items each time
	 * then waiting key press to continue printing
	 */
	public <T> void printMap(Map<String, T> mp) {
		int i = 0, count = 0;
	    
	    for (Map.Entry<String, T> entry : mp.entrySet()) {
	    	try{
	        	System.out.printf("[ %s %2d]\n", ITEM, count + 1);
	        	((Printable) entry.getValue()).print();
	        	System.out.println();
	        } catch (Exception e) {
	        	Log.e(e.getMessage());
	        }
	        count ++ ;
	        i ++;
	        if(i == PAGE_ITEM_LIMIT) {
	        	i = 0;
	        	hold();
	        }
		}
		hold();
	}
}
