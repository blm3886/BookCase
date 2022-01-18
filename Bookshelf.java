import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

public class Bookshelf {

    /**
      Representation invariant:
      -the height of the book is always a positive value.
      <put rep. invar. comment here>

   */
   
   // <add instance variables here>
    ArrayList<Integer> bookHeightList; 

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      bookHeightList = new ArrayList<Integer>();   
      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      this.bookHeightList = new ArrayList<>(pileOfBooks);
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
       //assert height > 0 :"Height of the book must be more than 0";
       bookHeightList.add(0, height);
       assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      //assert height > 0 :"Height of the book must be more than 0";
	   bookHeightList.add(height);
       assert isValidBookshelf();
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
	  int frontBook = bookHeightList.remove(0); 
      assert isValidBookshelf();
      return frontBook; 
      
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
	  int lastBook = bookHeightList.remove(bookHeightList.size()-1);
      assert isValidBookshelf();
	  return lastBook;       
   }

   /*
    * Gets the height of the book at the given position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      //assert position >= 0 && position < bookHeightList.size() :"Position should be  0 <= position < this.size() ";
      int bookHeight = bookHeightList.get(position);
	  assert isValidBookshelf();
      return bookHeight;   
      
   }

   /**
    *
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      int sizeBookshelf = bookHeightList.size();
      assert isValidBookshelf();
      return sizeBookshelf;       
   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
	  String currentBookList = bookHeightList.toString();
      assert isValidBookshelf();
	  return currentBookList;   
  
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      for(int i=0; i< bookHeightList.size()-1;i++) {
    	  if(bookHeightList.get(i) > bookHeightList.get(i+1)){
             assert isValidBookshelf();
             return false;
    	  }
      }
      assert isValidBookshelf();
      return true;  
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
	      if(bookHeightList.size() >= 0) {
	    	 for(int n:bookHeightList){
                if(n <= 0){
                return false;
                }
             }
          }
      return true;	        
   }

}