package test;

import java.util.Scanner;

import model.LibraryModel;
import model.LibraryBook;
import model.LibraryMember;
import model.LibrarySystem;
import model.LoanException;
import model.Student;
import model.Textbook;

public class LibraryTestHarness
{
   private static final LibraryModel library = new LibrarySystem();
   private static final Scanner sc = new Scanner(System.in);
   private static final String[] features =
   { "Add New Member",
            "Display Member Details",
            "Display Book Details",
            "Borrow Book",
            "Return Book",
            "Pay Fine",
            "Test Book/Member functionality",
            "Exit Program" };

   private static final String[] options =
   { "A", "B", "C", "D", "E", "F", "G", "X" };

   public static void main(String[] args)
   {

      String input;

      // '\0' is an ASCII nul character
      char option = '\0';

      loadBooks();

      loadMembers();

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

            switch (option)
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
                  
               case 'F':
                  payFine();
                  break;

               case 'G':
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

   private static void payFine()
   {
      System.out.print("Enter member ID: ");
      String memberID = sc.nextLine();
      
      LibraryMember member = library.getMember(memberID);
      
      if (member == null)
         System.out.println("Error - member " + memberID + " not found.");
      else if (!(member instanceof Student))
         System.out.println("Error - member " + memberID + " is not a student.");
      else
      {
         System.out.print("Enter amount: ");
         double amount = sc.nextDouble();
         
         Student s = (Student)member;
         
         double change = s.payFine(amount);
         
         if (s.getFinesOwing() > 0)
            System.out.printf("Remaining fines owing for student %s: $%.2f\n", memberID, change);
         else
         {
            System.out.printf("Fine for student %s paid in full", memberID);
            if (change > 0)
               System.out.printf(" - change due: $%.2f\n", change);
            else
               System.out.println();
         }
      }
   }

   private static void borrowBook()
   {

      System.out.print("Enter book number: ");
      String bookNumber = sc.nextLine();

      System.out.print("Enter member ID: ");
      String memberID = sc.nextLine();

      try
      {
         boolean result = library.borrowBook(memberID, bookNumber);

         if (result == true)
            System.out.println("Book " + bookNumber +
                               " borrowed successfully by " + memberID + ".");
         else
            System.out.println("Error - either book " + bookNumber +
                               " or member " + memberID + " not found.");
      }
      catch (LoanException e)
      {
         System.out.println(e.getMessage());
      }

   }

   private static void returnBook()
   {
      String memberID, bookNumber;
      int days;

      System.out.print("Enter book number: ");
      bookNumber = sc.nextLine();

      System.out.print("Enter member ID: ");
      memberID = sc.nextLine();
      
      System.out.print("Enter days on loan: ");
      days = sc.nextInt();
      
      // clear trailing newline
      sc.nextLine();

      try
      {
         boolean result = library.returnBook(memberID, bookNumber, days);

         if (result == true)
            System.out.println("Book " + bookNumber +
                               " returned successfully by " + memberID + ".");
         else
            System.out.println("Error - either book " + bookNumber +
                               " or member " + memberID + " not found.");
      }
      catch (LoanException e)
      {
         System.out.println(e.getMessage());
      }

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

            switch (option)
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