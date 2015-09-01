package model;

public interface LibraryBook
{
   // returns the book number for this LibraryBook
   String getBookNumber();
   
   // returns the borrower ID for this LibraryBook
   String getBorrowerID();
   
   // return the loan period
   int getLoanPeriod();
   // returns whether this LibraryBook is currently available or not
   boolean isAvailable();
   
   // attempts to borrow this LibraryBook for the specified member
   void borrowBook(LibraryMember member) throws BookException;
   
   // returns this LibraryBook
   void returnBook();

   // prints the details of this LibraryBook
   void print();
}
