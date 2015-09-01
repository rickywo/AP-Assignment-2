package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

public class BookModel {
	static final String BOOK = "Book",
			TEXTBOOK = "Textbook",
			NULL = "null",
			TRUE = "true",
			FALSE = "false",
			ITEM = "Item";
	
	private static BookModel singleton;
	private Map<String, LibraryBook> bookMap = new HashMap<>();
	private Map<String, String> borrowMap = new HashMap<>();
	
	public static BookModel getSingleton() {
		if(singleton == null) { singleton = new BookModel();}
		return singleton;
	}
	private BookModel() {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated method stub
		boolean eof = false;
		Queue<String> blockBuff = new LinkedList<String>();
		String  thisLine = null;
		FileReader fread 	= null;
		BufferedReader br	= null;
		try{
			fread = new FileReader("books1.txt");
			br = new BufferedReader(fread);
			LibraryBook tb;
			do {
				// Read each line until reach EOF
				thisLine = br.readLine();
				if(thisLine != null) {
					// Put String of every line in a queue
					// until meet next "Book" or "Textbook"
					if(blockBuff.size() > 1 &&
							(thisLine.equals(TEXTBOOK) || thisLine.equals(BOOK))) {
						tb = parseBookBlock2Obj(blockBuff);
						bookMap.put(tb.getBookNumber(), tb);
					}
					blockBuff.add(thisLine);
				} else {
					// Parse Last element here
					tb = parseBookBlock2Obj(blockBuff);
					bookMap.put(tb.getBookNumber(), tb);
					eof = true;
				}
			} while (!eof);
		} catch (FileNotFoundException e){
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			try {
				fread.close();
				br.close();
			} catch(IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
	private LibraryBook parseBookBlock2Obj(Queue<String> block) {
		LibraryBook b 	= null;
		String classTypeName = block.remove(), 
			bookID 	= block.remove(), 
			title 	= block.remove(),
			author 	= block.remove(), 
			loanPeriod 		= block.remove(), 
			availability 	= block.remove(),
			borrower = block.remove(),
			courseCode = null;
		
		if(classTypeName.equals(TEXTBOOK)) {
			try{ 
				courseCode = block.remove(); 
			} catch (Exception e){
				e.printStackTrace();
				courseCode = null;
			}
			b = new Textbook(bookID, title, author, courseCode);
		} else {
			b = new Book(bookID, title, author, Integer.parseInt(loanPeriod));	
		}
		((Book) b).setAvailability(Boolean.valueOf(availability));
		return b;
	}
	
	public Map<String, LibraryBook> getBookMap() {
		return bookMap;
	}
	
	public void save() {
		FileWriter writer = null;
		try {
			writer = new FileWriter("books1.txt");
			for (Map.Entry<String, LibraryBook> entry : bookMap.entrySet()) {
				//transBooktoString(entry.getValue());
				//System.out.printf("%s",transBooktoString(entry.getValue()));
				writer.write(transBooktoString(entry.getValue()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch(IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
	private String transBooktoString (LibraryBook book) {
		String bookObjString = new String();
		String nl = System.lineSeparator();
		String classTypeName, 
				bookID, 
				title,
				author, 
				loanPeriod, 
				availability,
				borrower,
				courseCode = null;
		
		boolean isTextbook = (book instanceof Textbook)? true:false;
		
		classTypeName = isTextbook ? TEXTBOOK:BOOK;
		bookID 	= book.getBookNumber();
		title 	= ((Book) book).getTitle();
		author 	= ((Book) book).getAuthor();
		loanPeriod = String.valueOf(book.getLoanPeriod());
		availability = (book.isAvailable())? TRUE:FALSE;
		borrower = book.getBorrowerID();
		bookObjString = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
					classTypeName, nl,
					bookID, nl,
					title, nl,
					author, nl,
					loanPeriod, nl,
					availability, nl,
					borrower, nl
				);
		
		if(isTextbook) {
			courseCode = ((Textbook)book).getCourseCode();
		}
		if(courseCode != null) {
			bookObjString = String.format("%s%s%s",bookObjString,courseCode,nl);
		}
		return bookObjString;
	}
	
	public void mapBorrower(Map<String, Member> memberList) {
		
	}
}
