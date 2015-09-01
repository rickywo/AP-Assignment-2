package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class LibrarySystem implements LibraryModel {
	static final String BOOK = "Book",
			TEXTBOOK = "Textbook",
			NULL = "null",
			ITEM = "Item";
	static final int PAGE_ITEM_LIMIT = 3;
	BookModel bookModel = BookModel.getSingleton();;
	Map<String, LibraryBook> bookMap;
	Map<String, Member> memberMap = new HashMap<String, Member>();
	
	public LibrarySystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addMember() {
		// TODO Auto-generated method stub
		// if(memberMap.containsKey())
		return false;
	}

	@Override
	public LibraryMember getMember(String memberID) {
		// TODO Auto-generated method stub
		try {
			return memberMap.get(memberID);
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public void displayAllMembers() {
		// TODO Auto-generated method stub
		printMap(memberMap);

	}

	@Override
	public LibraryBook getBook(String bookNumber) {
		// TODO Auto-generated method stub
		try {
			return bookMap.get(bookNumber);
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public void borrowBook(String memberID, String bookNumber)
			throws LoanException {
		// TODO Auto-generated method stub

	}

	@Override
	public void returnBook(String memberID, String bookNumber, int daysBorrowed)
			throws LoanException {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayAllBooks() {
		// TODO Auto-generated method stub
		printMap(bookMap);
	}
	
	@Override
	public void loadAllBooks() {
		bookMap = bookModel.getBookMap();
	}

	@Override
	public void loadAllMembers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAllBooks() {
		// TODO Auto-generated method stub
		bookModel.save();
	}

	@Override
	public void saveAllMembers() {
		// TODO Auto-generated method stub

	}
	
	private LibraryMember getBorrowerByID(String bID) {
		return null;
	}
	
	private void debug(String msg) {
		System.out.printf("[DEBUG] %s\n", msg);
	}
	
	private static void hold() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Press enter to continue...");
		scan.nextLine();
	}
	
	public static <T> void printMap(Map<String, T> mp) {
		java.util.Iterator<Entry<String, T>> it = mp.entrySet().iterator();
	    int i = 0, count = 0;
		while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        try{
	        	System.out.printf("[ %s %2d]\n", ITEM, count + 1);
	        	((Printable) pair.getValue()).print();
	        	System.out.println();
	        } catch (Exception e) {
	        	//e.printStackTrace();
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
