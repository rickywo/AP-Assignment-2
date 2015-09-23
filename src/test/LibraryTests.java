package test;

import java.util.ArrayList;

import model.LibraryBook;
import model.LibraryMember;
import model.LoanException;
import model.Book;
import model.Textbook;
import model.Staff;
import model.Student;

public class LibraryTests
{
   
   private static LibraryBook book1, book2, book3, book4, book5,
                              book6, book7, book8, book9, book10;
   
   private static LibraryMember member1, member2;
   
   public static void createTestData()
   {
      member1 = new Student("STU-001", "Daryl Teo", "9444-4444");
      member2 = new Staff("STA-002", "William Raffe", "9455-6677");
      
      book1 = new Book("WIN-001", "The Wind In the Willows", "Grahame, K.");
      book2 = new Book("WAR-002", "War and Peace", "Tolstoy, L.");
      book3 = new Book("GRE-003", "The Great Hunt", "Jordan, R.");
      book4 = new Book("CRU-004", "The Crucible", "Miller, A.");
      book5 = new Book("GAT-005", "The Gathering Storm", "Jordan, R.");
      book6 = new Book("GIR-006", "The Girl with the Dragon Tattoo", 
                       "Larsen, S.");
      book7 = new Textbook("JAV-001", "Introduction to Java Programming", 
                           "Liang, D.", "COSC1073 COSC1284 COSC2531 COSC1295");
      book8 = new Textbook("DBC-001", "Fundamentals of Database Systems", 
                           "Elmasri, R. & Navathe, S.", "ISYS1057");
      book9 = new Textbook("ALG-001", "Introduction to the Design and Analysis of Algorithms", 
                           "Levitin, A.", "COSC1285");
      book10 = new Textbook("AIN-001", 
                            "Artificial Intelligence - A Modern Approach.", 
                            "Russell, S. & Norvig, P.", "COSC1127");
   }
   
   
   public static void testStudentBorrowingBasic()
   {
      boolean result = true;
      createTestData();
      
      System.out.println();
      System.out.println("Test 1 - Basic Student Borrowing functionality");
      System.out.println("----------------------------------------------");
      

      try
      {
         System.out.print("Calling borrowIem() method - ");
         member1.borrowBook(book1);
         System.out.println("call successful.");
         
         System.out.println();
         System.out.print("Checking availability - ");
         if(book1.isAvailable() == false)
            System.out.println("updated correctly.");
         else
         {
            System.out.println("not updated correctly!");
            result = false;
         }
         
         
         
         System.out.println();
         System.out.print("Checking link to borrower - ");
         
         String id = book1.getBorrowerID();
         if(member1.getMemberID().equals(id))
            System.out.println("updated correctly.");
         else
         {
            System.out.println("not updated correctly!");
            result = false;
         }
         
         System.out.println();
         System.out.print("Checking if book added to borrowers book list - ");
         
         
         ArrayList <LibraryBook> borrowedBooks = member1.getBorrowedBooks();
                  
         if(borrowedBooks.contains(book1))
            System.out.println("updated correctly.");
         else
         {
            System.out.println("not updated correctly!");
            result = false;
         }    
         
         System.out.println();
         System.out.print("Attempting to borrow same book again (should fail) - ");
         try
         {
            member2.borrowBook(book1);
            System.out.println("successful!");
            result = false;
         }
         catch (LoanException e)
         {
            String error = e.getMessage();
            if (error.indexOf("Book has already been borrowed") != -1)
            {
               System.out.println("failed.");
               
            }
            else
            {
               System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
               result = false;
            }
         }
      }
      catch (LoanException e)
      {
        System.out.println("call failed: " + e.getMessage());
      }
      
      System.out.println();
      if (result == false)
      {
         System.out.println("(FAIL)");
      }
      else
      {
         System.out.println("(PASS)");
      }
      System.out.println();
   }
   
   public static void testStaffBorrowingBasic()
   {
      boolean result = true;
      createTestData();
      
      System.out.println();
      System.out.println("Test 3 - Basic Staff Borrowing functionality");
      System.out.println("--------------------------------------------");
      
      try
      {
         System.out.println();
         System.out.print("Calling borrowIem() method - ");
         member2.borrowBook(book1);
         System.out.println("call successful.");
         
         System.out.println();
         System.out.print("Checking availability - ");
         
         if(book1.isAvailable() == false)
            System.out.println("updated correctly.");
         else
         {
            System.out.println("not updated correctly!");
            result = false;
         }
         
         System.out.println();
         System.out.print("Checking link to borrower - ");
         
         String id = book1.getBorrowerID();
         if(member2.getMemberID().equals(id))
            System.out.println("updated correctly.");
         else
         {
            System.out.println("not updated correctly!");
            result = false;
         }
         
         System.out.println();
         System.out.print("Checking if book added to borrowers book list - ");
         
         ArrayList <LibraryBook> borrowedBooks = member2.getBorrowedBooks();
         
         
         if(borrowedBooks.contains(book1))
            System.out.println("updated correctly.");
         else
         {
            System.out.println("not updated correctly!");
            result = false;
         }
         
         System.out.println();
         System.out.print("Attempting to borrow same book again (should fail) - ");
         try
         {
            member2.borrowBook(book1);
            System.out.println("successful!");
            result = false;
         }
         catch (LoanException e)
         {
            String error = e.getMessage();
            if (error.indexOf("Book has already been borrowed") != -1)
            {
               System.out.println("failed.");
               
            }
            else
            {
               System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
               result = false;
            }
         }
        
      }
      catch (LoanException e)
      {
        System.out.println("call failed: " + e.getMessage());
      }
      
      System.out.println();
      if (result == false)
      {
         System.out.println("(FAIL)");
      }
      else
      {
         System.out.println("(PASS)");
      }
      System.out.println();
   }
   
   public static void testStudentBorrowingConstraints()
   {
      boolean result = true;

      System.out.println();
      System.out.println("Test 2 - Student Borrowing Constraints");
      System.out.println("--------------------------------------");
      
      System.out.print("Borrowing up to student book limit - ");
      createTestData();
      
      try
      {
         member1.borrowBook(book1);
         member1.borrowBook(book2);
         System.out.println("successful.");
      }
      catch (LoanException e)
      {
         String message = e.getMessage();
         
         if (message.indexOf("book limit exceeded") != -1)
         {
            System.out.println("failed - limit incorrect (should be 2)");
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
         }
         result = false;
      }
      
      System.out.println();
      System.out.print("Exceeding student Book limit (should fail) - ");
      
      try
      {
         member1.borrowBook(book3);
         System.out.println("succeeded - limit incorrect (should be 2)");
         result = false;
      }
      catch (LoanException e)
      {
         String message = e.getMessage();
         
         if (message.indexOf("book limit exceeded") != -1)
         {
            System.out.println("failed.");
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
            result = false;
         }
      }
      
      System.out.println();
      System.out.print("Exceeding student Textbook limit (should fail) - ");
      
      createTestData();
      
      try
      {
         member1.borrowBook(book7);
         
         try
         {
            member1.borrowBook(book8);
            System.out.println("succeeded - Textbook limit incorrect (should be 1)");
            result = false;
         }
         catch (LoanException e)
         {
            String message = e.getMessage();
            
            if (message.indexOf("textbook limit exceeded") != -1)
            {
               System.out.println("failed.");
            }
            else
            {
               System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
               result = false;
            }
         }
        
      }
      catch (LoanException e)
      {
         String message = e.getMessage();
         
         if (message.indexOf("book limit exceeded") != -1)
         {
            System.out.println("failed - limit incorrect (should be 1)");
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");

         }
         
         result = false;
      } 
      

      System.out.println();
      System.out.print("Checking Book fine calculation for student - ");
      
      createTestData();
      
      try
      {
         member1.borrowBook(book1);
         member1.borrowBook(book2);
         member1.returnBook(book1.getBookNumber(), 15);         
      }
      catch (LoanException e)
      { 
         String message = e.getMessage();
         
         Student student = (Student) member1;
         
         if (message.indexOf("Book overdue") != -1)
         {
           
            if (student.getFinesOwing() == 5.0)
            {
               System.out.println("ok.");
               
               System.out.println();
               System.out.print("Checking second Book fine calculation for student - ");
               
               try
               {
                  member1.returnBook(book2.getBookNumber(), 22);   
               }
               catch (LoanException e2)
               { 
                  message = e2.getMessage();
                  
                  if (message.indexOf("Book overdue") != -1)
                  {
                     if (student.getFinesOwing() == 15.0)
                     {
                        System.out.println("ok.");
                     }
                     else if (student.getFinesOwing() == 10.0)
                     {
                        System.out.println("incorrect!\n (you are overwriting the existing fine with the new fine instead of adding it)");
                        result = false;
                     }
                     else
                     {
                        System.out.println("incorrect! (check Book fine calculation)");
                        result = false;
                     }
                  }
                  else
                  {
                     System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
                     result = false;
                  }
               }
            }
           
            else
            {
               System.out.println("incorrect! (check Book fine calculation)");
               result = false;
            }   
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
            result = false;
         }
      } 
      
      System.out.println();
      System.out.print("Checking Textbook fine calculation for student - ");
      
      createTestData();
      
      try
      {
         member1.borrowBook(book7);
         member1.returnBook(book7.getBookNumber(), 7);         
      }
      catch (LoanException e)
      { 
         String message = e.getMessage();
         
         Student student = (Student) member1;
         
         if (message.indexOf("fines owing") != -1)
         {
            if (student.getFinesOwing() == 10.0)
            {
               System.out.println("ok.");
            }
           
            else
            {
               System.out.println("incorrect! (check Book fine calculation)");
               result = false;
            }   
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
            result = false;
         }
      } 

      
      System.out.println();
      System.out.print("Borrowing Book for student with fines owing (should fail) - ");
      
      createTestData();
      
      try
      {
         member1.borrowBook(book1);
         member1.borrowBook(book7);
         member1.returnBook(book1.getBookNumber(), 15);
    
      }
      catch (LoanException e)
      { 
         
         String message = e.getMessage();
         
         Student student = (Student) member1;
         
         if (message.indexOf("Book overdue") != -1)
         {
            if (student.getFinesOwing() != 5.0)
            {
               System.out.println("failed. (fine calculation/recording not working)");
               result = false;
            }
         
            else
            {
               try
               {
                  member1.borrowBook(book2);
                  System.out.println("succeeded.");
                  result = false;
               }
               catch (LoanException e2)
               {
                  String message2 = e2.getMessage();

                  if (message2.indexOf("Fines owing") != -1)
                  {
                     System.out.println("failed.");
                  }
                  else
                  {
                     System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
                     result = false;
                  }
               }
            }
         }
         else
         {
            System.out.println("B. failed due to some other issue: \n\t(" + e.getMessage() + ")");
            result = false;  
         }
      } 
      
      System.out.println();
      if (result == false)
      {
         System.out.println("(FAIL)");
      }
      else
      {
         System.out.println("(PASS)");
      }
      System.out.println();
      
   }
   
   public static void testStaffBorrowingConstraints()
   {
      boolean result = true;

      System.out.println();
      System.out.println("Test 4 - Staff Borrowing Constraints");
      System.out.println("--------------------------------------");
      
      System.out.print("Borrowing up to staff book limit - ");
      createTestData();
      
      try
      {
         member2.borrowBook(book1);
         member2.borrowBook(book2);
         member2.borrowBook(book3);
         member2.borrowBook(book4);
         System.out.println("successful.");
      }
      catch (LoanException e)
      {
         String message = e.getMessage();
         
         if (message.indexOf("Book limit exceeded") != -1)
         {
            System.out.println("failed - limit incorrect (should be 4)");
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
         }
         result = false;
      }
      
      System.out.println();
      System.out.print("Exceeding staff Book limit (should fail) - ");
      
      try
      {
         member2.borrowBook(book5);
         System.out.println("succeeded - limit incorrect (should be 2)");
         result = false;
      }
      catch (LoanException e)
      {
         String message = e.getMessage();
         
         if (message.indexOf("Staff book limit exceeded") != -1)
         {
            System.out.println("failed.");
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
            result = false;
         }
      }
      
      System.out.println();
      System.out.print("Checking that there is no limit on Textbooks - ");
      
      createTestData();
      
      try
      {
         member2.borrowBook(book7);
         member2.borrowBook(book8);
         member2.borrowBook(book9);
         member2.borrowBook(book10);   
         System.out.println("ok.");
      }
      catch (LoanException e)
      {
         String message = e.getMessage();
         System.out.println("failed: \n\t(" + e.getMessage() + ")");
         result = false;
      } 
      

      System.out.println();
      System.out.print("Checking book overdue status setting for staff - ");
      
      createTestData();
      
      try
      {
         member2.borrowBook(book1);
         member2.borrowBook(book2);
         member2.returnBook(book1.getBookNumber(), 15); 
         System.out.println("failed. \n\t(exception not thrown in StaffMember returnBook() method)");
         result = false;
      }
      catch (LoanException e)
      { 
         String message = e.getMessage();
         
         Staff staff = (Staff) member2;
         
         if (message.indexOf("Book overdue") != -1)
         {
            if (staff.isBookOverdue() == true)
            {
               System.out.println("ok.");
            } 
            else
            {
               System.out.println("incorrect! (staff member should be marked as having overdue book)");
               result = false;
            }  
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
            result = false;
         }
      } 
      
      System.out.println();
      System.out.print("Checking overdue book restriction for staff (should fail) - ");
      
      createTestData();
      
      try
      {
         member2.borrowBook(book1);
         member2.borrowBook(book2);
         member2.borrowBook(book6);
         member2.borrowBook(book7);
         member2.returnBook(book7.getBookNumber(), 7); 
         System.out.println("failed. \n\t(exception not thrown in StaffMember returnBook() method)");
         result = false;
      }
      catch (LoanException e)
      { 
         String message = e.getMessage();
         
         Staff staff = (Staff) member2;
         
         if (message.indexOf("Book overdue") != -1)
         {
            if (staff.isBookOverdue() == true)
            {
               try
               {
                  member2.borrowBook(book3);
                  System.out.println("successful. \n\t(exception not thrown in StaffMember returnBook() method)");
                  result = false;
               }
               catch (LoanException e2)
               {
                  String message2 = e2.getMessage();
                  if (message2.indexOf("Staff member has recent overdue books") != -1)
                  {
                     System.out.println("ok.");
                  }
                  else
                  {
                     System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
                     result = false;
                  }                 
               }
            }   
            else
            {
               System.out.println("incorrect! (staff member should be marked as having overdue book)");
               result = false;
            }   
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
            result = false;
         }
      } 

      
      System.out.println();
      System.out.print("Checking if book overdue status is reset - ");
      
      try
      {
         member2.returnBook(book1.getBookNumber(), 7);     
         member2.returnBook(book2.getBookNumber(), 7);      
         member2.returnBook(book6.getBookNumber(), 2); 
         member2.borrowBook(book3);
         
         ArrayList <LibraryBook> books = member2.getBorrowedBooks();
         
         if (books.size() != 1)
         {
            System.out.println("failed. \n\t(books not being returned correctly)");
         }
         else
         {
            LibraryBook borrowedBook = books.get(0);
            if (! borrowedBook.getBookNumber().equals(book3.getBookNumber()))
            {
               System.out.println("failed. \n\t(books not being returned and/or borrowed correctly)");
            }
            else
            {
               System.out.println("ok");
            }
         }

      }
      catch (LoanException e)
      { 
         String message = e.getMessage();
         
         if (message.indexOf("Staff member has recent overdue books") != -1)
         {
            
            System.out.println("failed. (book overdue status not being reset)");
            result = false;
         }
         else
         {
            System.out.println("failed due to some other issue: \n\t(" + e.getMessage() + ")");
            result = false;  
         }
      } 
      
      System.out.println();
      if (result == false)
      {
         System.out.println("(FAIL)");
      }
      else
      {
         System.out.println("(PASS)");
      }
      System.out.println();
      
   }
}