import java.util.ArrayList;
import java.util.*;

/**
 * Tests Bookshelf with hard coded data
 * 
 *
 */

public class BookshelfTester {
	
    
	
	public static void main(String[] args) {
   
    ArrayList<Integer> bookHeight = new ArrayList<Integer>();
    bookHeight.add(20);
    bookHeight.add(5);
    bookHeight.add(9);   
   
    // ArrayList in non decreasing order.   
    ArrayList<Integer> bookHeight1 = new ArrayList<Integer>();
    bookHeight1.add(5);
    bookHeight1.add(9);
    bookHeight1.add(20);      
       
       
       
    Bookshelf book = new Bookshelf();
       //Checking the empty constructor.
       System.out.print("Books in the bookshelf at present [exp: <empty>]:"+ book.toString());
       System.out.println();
       
      //Adding books of height [20,5,9] to the ArrayList
       Bookshelf book1 = new Bookshelf(bookHeight);
       System.out.println("Books in the bookshelf at present[exp: <[20,5,9]>]"+book1.toString());
       System.out.println();
       
      //Adding elements to the front of the ArrayList.
       book1.addFront(12); //Adding book of height 12.
       System.out.println("Books in the bookshelf after adding a book to the start [exp: <[12,20,5,9]>]"+book1.toString());
       System.out.println();
       //book1.addFront(0); // Adding book of height 0.
       
      //Adding elements to the end of the ArrayList.
       book1.addLast(15); //Adding book of height 12.
       System.out.println("Books in the bookshelf after adding a book to the end [exp: <[12,20,5,9,15]>]:"+book1.toString());
       System.out.println();
       //book1.addLast(0); // Adding book of height 0.
       
              
       //Removing book form the start of the bookshelf 
       System.out.println("The height of the first book in the bookshelf [exp: <12>]:"+book1.removeFront());
       System.out.println("Books in the bookshelf after removing the first book [exp: <[20,5,9,15]>]:"+book1.toString());
       //book.removeFirst(); // Removing books from empty bookshelf
       System.out.println();

       //Removing book from the end of the bookshelf
       System.out.println("The height of the last book removed the bookshelf[exp: <[15]>]:"+book1.removeLast());
       System.out.println("Books in the bookshelf after removing the last book [exp: <[20,5,9]>]:"+book1.toString());
       //book.removeFirst(); // Removing books from empty bookshelf
       System.out.println();
       
       //Getting the height of the book at the specified index position.
       System.out.println("The height of the book at the given position [exp:<[9]>]:"+book1.getHeight(2));
       
       //Getting the total number of books in the bookshelf
       System.out.println("The number of books in the bookshelf [exp:<3>]:"+ book1.size());
       System.out.println("List of books in bookshelf(book1) at present:"+book1.toString());
       
       System.out.println("The number of books in the bookshelf [exp:<0>]:"+ book.size());
       System.out.println("List of books in bookshelf(book) at present:"+ book.toString());
       System.out.println();
       
       //Checking if books are in sorted order
       System.out.println("List of books in bookshelf(book1) at present:"+book1.toString());
       System.out.println("is the bookshlef (book1)in sorted order ?[exp: <false>]:"+book1.isSorted());
         
       //Cheking the isSorted() by adding ArrayList in non decreasing order.   
       Bookshelf book2 = new Bookshelf(bookHeight1);
       System.out.println("List of books in bookshelf(book2) at present:"+book2.toString());
       System.out.println("is the bookshlef in sorted order ?[exp: <true>]:"+book2.isSorted());
       System.out.println();
       
       //Adding negative value for height
       bookHeight1.add(-55);
       Bookshelf book3 = new Bookshelf(bookHeight1);
       System.out.println("List of books in the bookshelf [exp:<5,9,20,-55>]:"+ bookHeight1.toString());
     }
}
