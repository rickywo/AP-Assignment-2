package model;

import java.util.ArrayList;

public interface LibraryMember
{
   // returns the id of the LibraryMember
   String getMemberID();
   
   // attempts to borrow the specified Book for the LibraryMember 
   void borrowBook(LibraryBook b) throws LoanException;
   
   // attempts to return the Book with the specified book number for the LibraryMember
   void returnBook(String bookNumber, int days) throws LoanException;
   
   // returns the collection of Books that the member currently had borrowed
   ArrayList <LibraryBook> getBorrowedBooks();
   
   // prints details for this LibraryMember to screen
   void print();
}
