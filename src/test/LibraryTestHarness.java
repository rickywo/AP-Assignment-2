package test;
import java.util.Scanner;

import model.LibraryModel;
import model.LibrarySystem;
import model.LoanException;
import model.Log;

public class LibraryTestHarness
{
   private static final LibraryModel library = new LibrarySystem();
   private static final Scanner sc = new Scanner(System.in);
   private static final String [] features = 
                                   {"Add New Member", 
                                    "Display Member Details",
                                    "Display Item Details",
                                    "Borrow Book",
                                    "Return Book",
                                    "Test Item/Member functionality",
                                    "Exit Program"};
   
   private static final String [] options = 
                                   {"A", "B", "C", "D", "E", "T", "X"};
   
   
   public static void main(String[] args)
   {
      
      String input;
      
      // '\0' is an ASCII nul character
      char option = '\0'; 
      
      
      loadBooks();
      
      loadMembers();
      
      initRelations();
      do
      {
         System.out.println();
         System.out.println("***** Library System Console Menu ***** ");
         System.out.println();
         for (int i = 0; i < features.length; i++)
         {
            System.out.printf("%-35s - %s\n", features[i], options[i]);
         }
         System.out.println();
         System.out.print("Enter your selection: ");
         input = sc.nextLine();
         if (input.length() != 1)
         {
            System.out.println("Error - invalid menu selection!");
         }
         else
         {
            option = input.charAt(0);
            option = Character.toUpperCase(option);
            
            switch(option)
            {
               case 'A':
                  addNewMember();
                  break;

               case 'B':
                  displayMembers();
                  break;

               case 'C':
                  displayBooks();
                  break;
                  
               case 'D':
            	   borrowBook();
            	   break;
            	   
               case 'E':
            	   returnBook();
            	   break;

               case 'T':
                  testFunctionality();
                  break;

               default:
                  System.out.println("Error Invaid menu selection!");
            
           
              
            }
         }
         
      } while (option != 'X');
      
      saveBooks();
      saveMembers();

   }
   
   public static void addNewMember()
   {
       boolean memberAdded = library.addMember();
       
       if (!memberAdded)
       {
          System.out.println("Duplicate member ID found - " +
                             "member not added to system!");
       }
       else
       {
          System.out.println("New member added to system succesfully");
       }
   }

 
   public static void displayMembers()
   {
      library.displayAllMembers();     

   }
   
   public static void displayBooks()
   {
      library.displayAllBooks();      
   }
   
   public static void borrowBook() {
	   String memberID, bookID;
	   System.out.print("Enter member ID :");
	   memberID = (sc.nextLine()).trim();
	   System.out.print("Enter book ID :");
	   bookID = (sc.nextLine()).trim();
	   try {
			if(library.borrowBook(memberID, bookID)) {
				System.out.println("Book borrow succesfully");
			} else {
				System.out.println("Book borrow failed");
			}
		} catch (LoanException e) {
			Log.e(e.getMessage());
		}
   }
   
   public static void returnBook() {
	   String memberID, bookID;
	   int day;
	   System.out.print("Enter member ID :");
	   memberID = (sc.nextLine()).trim();
	   System.out.print("Enter book ID :");
	   bookID = (sc.nextLine()).trim();
	   System.out.print("Enter holding days :");
	   day = sc.nextInt();
	   try {
			if(library.returnBook(memberID, bookID, day)) {
				System.out.println("Book return succesfully");
			} else {
				System.out.println("Book return failed");
			}
		} catch (LoanException e) {
			Log.e(e.getMessage());
		}
   }

   public static void initRelations()
   {
      System.out.println("Loading relations...");
      
      ((LibrarySystem)library).initRelations();
      
      System.out.println();
      System.out.println("Relations loading complete.");
      
   }
   
   public static void loadBooks()
   {
      System.out.println("Loading book data into system...");
      
      library.loadAllBooks();
      
      System.out.println();
      System.out.println("Book data loading complete.");
      
   }

   
   public static void loadMembers()
   {
      
      System.out.println("Loading member data into system...");
      
      library.loadAllMembers();
      
      System.out.println();
      System.out.println("Member data loading complete.");
   }

  
   public static void saveBooks()
   {
      System.out.println("Saving book data out to file");
      
      library.saveAllBooks();
      
      System.out.println();
      System.out.println("Saving of book data to file complete.");

   }

   
   public static void saveMembers()
   {
      System.out.println("Saving member data out to file");
      

 	   library.saveAllMembers();

      
      System.out.println();
      System.out.println("Saving of member data to file complete.");


   }
   
   private static void testFunctionality()
   {
      String input;
      char option = '\0';
      

      
      do
      {
         System.out.println();
         System.out.println("*** Borrowing Tests Menu ***");
         System.out.println();
       
         System.out.println("Test Simple Borrowing (Student)       - A");
         System.out.println("Test Borrowing Contraints (Student)   - B");         
         System.out.println("Test Simple Borrowing (Staff)         - C");
         System.out.println("Test Borrowing Constraints (Staff)    - D");
         System.out.println("Test All                              - E");
         System.out.println("Return to main menu                   - X");
         System.out.println();
         
         System.out.print("Enter your selection: ");
         input = sc.nextLine();
         System.out.println();
         
         if (input.length() != 1)
         {
            System.out.println("Error - invalid menu selection!");
         }
         else
         {
            option = input.charAt(0);
            option = Character.toUpperCase(option);
            
            switch(option)
            {
               case 'A':
                  LibraryTests.testStudentBorrowingBasic();
                  break;

               case 'B':
                  LibraryTests.testStudentBorrowingConstraints();
                  break;

               case 'C':
                  LibraryTests.testStaffBorrowingBasic();
                  break;

               case 'D':
                  LibraryTests.testStaffBorrowingConstraints();
                  break;

               case 'E':
                  LibraryTests.testStudentBorrowingBasic();
                  LibraryTests.testStudentBorrowingConstraints();
                  LibraryTests.testStaffBorrowingBasic();
                  LibraryTests.testStaffBorrowingConstraints();
                  break;
                  
               case 'X':
                  System.out.println();
                  System.out.println("Returning to main menu...");
                  break;

               default:
                  System.out.println("Error Invaid menu selection!");
            
           
              
            }
         }
      } while (option != 'X');
      
   }

}
