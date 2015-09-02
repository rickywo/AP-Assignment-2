package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemberModel {
	static final String BOOK = "Book",
			TEXTBOOK = "Textbook",
			STA = "STA",
			STU = "STU",
			NULL = "null",
			TRUE = "true",
			FALSE = "false",
			ITEM = "Item";
	static final int ID_INDEX = 0,
			TITLE_INDEX 	= 1,
			PHONENO_INDEX 	= 2,
			CLASS_SPEC 		= 3;
			
	
	private static MemberModel singleton;
	private Map<String, Member> memberMap;
	private Map<String, ArrayList<String>> entitledMap;
	
	public static MemberModel getSingleton() {
		if(singleton == null) { singleton = new MemberModel();}
		return singleton;
	}
	private MemberModel() {
		// init memberMap
		memberMap = new HashMap<>();
		// init entitledMap (1 to many relation between memberID and BookNumber)
		entitledMap = new HashMap<>();
		boolean readFlag = false;
		ObjectInputStream oin = null;
		Member tm;
		String[] sa = new String[3];
		try {
			oin = new ObjectInputStream(new FileInputStream("members1.dat"));
			readFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// read in from file
		while(readFlag) {
			try{
				// Read a member data from inputstream
				// Structure for reading
		        // __________________________________________________________
		        // |String|String|String|Boolean or Double|ArrayList<String>|
		        // ----------------------------------------------------------
		        
				sa[ID_INDEX] 	= oin.readUTF();
				sa[TITLE_INDEX] = oin.readUTF();
				sa[PHONENO_INDEX] = oin.readUTF();
				if (sa[ID_INDEX].indexOf("STA") != -1) {
					tm = new Staff(sa[ID_INDEX], sa[TITLE_INDEX],
							sa[PHONENO_INDEX]);
					((Staff)tm).setBookOverdue(oin.readBoolean());
				} else {
					tm = new Student(sa[ID_INDEX], sa[TITLE_INDEX],
							sa[PHONENO_INDEX]);
					((Student)tm).setFinesOwing(oin.readDouble());
				}
				// Raw data map without relationship to book
				memberMap.put(tm.getMemberID(), tm);
				// Map for storing relation
				entitledMap.put(tm.getMemberID(), (ArrayList<String>) oin.readObject());
			} catch (EOFException e) {
				Log.e(e.getMessage());
				readFlag = false;
			} catch (Exception e) {
				Log.e(e.getMessage());
			} 
		}
		try {
			oin.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Member> getMemberMap() {
		return memberMap;
	}
	
	public Map<String, ArrayList<String>> getEntitledMap() {
		return entitledMap;
	}
	
	/** save
	 *  To save members' information into a binary file
	 * 
	 */
	public void save() {
        ObjectOutputStream oout = null;
        Member tm;
        ArrayList<String> booksBorrowed = new ArrayList<String>();
		try {
	        oout = new ObjectOutputStream(new FileOutputStream("members1.dat"));
	        // Loop through memberMap and process each entry
	        // Structure for writing
	        // __________________________________________________________
	        // |String|String|String|Boolean or Double|ArrayList<String>|
	        // ----------------------------------------------------------
	        
	        for (Map.Entry<String, Member> entry : memberMap.entrySet()) {
				tm = entry.getValue();
				oout.writeUTF(tm.getMemberID());
				oout.writeUTF(tm.getName());
				oout.writeUTF(tm.getPhoneNumber());
				if(tm instanceof Staff) {
					oout.writeBoolean(((Staff)tm).isBookOverdue());
				}
				else {
					oout.writeDouble(((Student)tm).getFinesOwing());
				}
				for(LibraryBook b: tm.getBooklist()) {
					booksBorrowed.add(b.getBookNumber());
				}
				oout.writeObject(booksBorrowed);
			}
		} catch (Exception e) {
			Log.e(e.getMessage());
		} finally {
			try {
				oout.close();
			} catch(IOException e) {
				Log.e(e.getMessage());
			}
			
		}
	}
	
	public void mapBorrower(Map<String, Member> memberList) {
		
	}
}
