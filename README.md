# BookCase

## A JAVA OOP project involving system design to organize a bookshelf.
- Books in the bookshlef are stored in non decreasing order of heights.
- Books can books can only be added or removed from the ends.
- User can perform two operations pick index(index of the book which has to be picked from the Bookshelf) 
or Put height (the height of the new bookwhich has to be inserted in the Bookshlef at 
 appropriate location).
- The BookShlef Keeper must performed the pick out put operation specified by the user
  such that there is minumum number of operations.
 
 ##### Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.


 ##### BookshelfKeeperProg  
 * Main program that prompts for input from user. Calls the methods in Bookshelf
 * and BookshelfKeeper class to maintain books. Checks and allows only valid
 * operations to be performed on the Bookshelf.
 


 ##### Class BookshelfKeeper
 * Enables users to perform efficient putPos or pickHeight operation on a
 * bookshelf of books kept in non-decreasing order by height, with the
 * restriction that single books can only be added or removed from one of the
 * two *ends* of the bookshelf to complete a higher level pick or put operation.
 * Pick or put operations are performed with minimum number of such adds or
 * removes.
