import java.util.ArrayList;

/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a
 * bookshelf of books kept in non-decreasing order by height, with the
 * restriction that single books can only be added or removed from one of the
 * two *ends* of the bookshelf to complete a higher level pick or put operation.
 * Pick or put operations are performed with minimum number of such adds or
 * removes.
 * 
 * @param sortedBookshelf    Object reference for the Bookshelf class.
 * @param tempBookshelf      ArrayList to store the books removed temporarily.
 * @param callsToRemoveLast  Stores the number of calls to the RemoveLast()
 *                           method done so far.
 * @param callsToRemoveFront Stores the number of calls to the RemoveFront()
 *                           method done so far.
 * @param callsToAddFront    Stores the number of calls to the AddFront() method
 *                           done so far.
 * @param callsToAddLast     Stores the number of calls to the AddLast() method
 *                           done so far.
 * @param curretNumCalls     Stores the number of calls to the Bookshelf
 *                           mutators for current operation.
 */
public class BookshelfKeeper {

	/**
	 * Representation invariant: The books in a BookshelfKeeper object are stored in
	 * non-decreasing order.
	 */
	// <instance variables>
	ArrayList<Integer> tempBookShelf;
	Bookshelf sortedBookshelf;
	private int callsToRemoveLast;
	private int callsToRemoveFront;
	private int callsToAddFront;
	private int callsToAddLast;
	private int currentNumCalls;

	/**
	 * Creates a BookShelfKeeper object with an empty bookshelf
	 */
	public BookshelfKeeper() {
		sortedBookshelf = new Bookshelf();
		tempBookShelf = new ArrayList<Integer>();
		callsToRemoveLast = 0;
		callsToRemoveFront = 0;
		callsToAddFront = 0;
		callsToAddLast = 0;
		currentNumCalls = 0;
		assert isValidBookshelfKeeper();
	}

	/**
	 * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
	 * Note: method does not make a defensive copy of the bookshelf.
	 *
	 * PRE: sortedBookshelf.isSorted() is true.
	 */
	public BookshelfKeeper(Bookshelf sortedBookshelf) {
		this.sortedBookshelf = sortedBookshelf;
		tempBookShelf = new ArrayList<Integer>();
		callsToRemoveLast = 0;
		callsToRemoveFront = 0;
		callsToAddFront = 0;
		callsToAddLast = 0;
		currentNumCalls = 0;
		assert isValidBookshelfKeeper();
	}

	/**
	 * Removes a book from the specified position in the bookshelf and keeps
	 * bookshelf sorted after picking up the book.
	 * 
	 * Returns the number of calls to mutators on the contained bookshelf used to
	 * complete this operation. This must be the minimum number to complete the
	 * operation.
	 * 
	 * PRE: 0 <= position < getNumBooks()
	 */
	public int pickPos(int position) {
		if (position >= sortedBookshelf.size() / 2) { // comparing with middle value to determine the minimum number of
														// operations.
			int numCalls = helperPickFromRight(position);
			assert isValidBookshelfKeeper();
			return (numCalls);
		} else {
			int numCalls = helperPickFromLeft(position);
			assert isValidBookshelfKeeper();
			return (numCalls);
		}

	}

	/**
	 * Inserts book with specified height into the shelf. Keeps the contained
	 * bookshelf sorted after the insertion.
	 * 
	 * Returns the number of calls to mutators on the contained bookshelf used to
	 * complete this operation. This must be the minimum number to complete the
	 * operation.
	 * 
	 * PRE: height > 0
	 */
	public int putHeight(int height) {
		int midVal = sortedBookshelf.size() / 2;
		int numCalls;

		if (getNumBooks() == 0) {
			addFromLeft(height);
			numCalls = currentNumCalls++;
			assert isValidBookshelfKeeper();
			return (numCalls);
		} else if (height >= sortedBookshelf.getHeight(midVal)) {
			numCalls = helperPutHeightFromRight(height);
			assert isValidBookshelfKeeper();
			return (numCalls);
		} else {
			numCalls = helperPutHeightFromLeft(height);
			assert isValidBookshelfKeeper();
			return (numCalls);
		}
	}

	/**
	 * Returns the total number of calls made to mutators on the contained bookshelf
	 * so far, i.e., all the ones done to perform all of the pick and put operations
	 * that have been requested up to now.
	 */
	public int getTotalOperations() {
		int totalCalls = callsToAddFront + callsToAddLast + callsToRemoveFront + callsToRemoveLast;
		assert isValidBookshelfKeeper();
		return totalCalls;
	}

	/**
	 * Returns the number of books on the contained bookshelf.
	 */
	public int getNumBooks() {
		int numBooks = sortedBookshelf.size();
		assert isValidBookshelfKeeper();
		return numBooks;
	}

	/**
	 * Returns string representation of this BookshelfKeeper. Returns a String
	 * containing height of all books present in the bookshelf in the order they are
	 * on the bookshelf, followed by the number of bookshelf mutator calls made to
	 * perform the last pick or put operation, followed by the total number of such
	 * calls made since we created this BookshelfKeeper.
	 * 
	 * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
	 * 
	 */
	public String toString() {
		String bookShelf = sortedBookshelf.toString() + " " + currentNumCalls + " " + getTotalOperations();
		assert isValidBookshelfKeeper();
		return bookShelf;
	}

	/**
	 * Returns true iff the BookshelfKeeper data is in a valid state. (See
	 * representation invariant comment for details.)
	 */
	private boolean isValidBookshelfKeeper() {
		return sortedBookshelf.isSorted();
	}

	/**
	 * Removes one book from the last position of the sortedBookshlef using
	 * removeLast() and stores the element in the temporary ArrayList. Updates the
	 * number of calls made to removeLast().
	 */
	private int removeFromRight() {
		int numCalls = 0;
		tempBookShelf.add(sortedBookshelf.removeLast());
		callsToRemoveLast += 1;
		numCalls++;
		assert isValidBookshelfKeeper();
		return (numCalls);
	}

	/**
	 * Removes one book from the first position of the sortedBookshlef using
	 * removeFront() and stores the element in the temporary ArrayList. Updates the
	 * number of calls made to removeFront().
	 */
	private int removeFromLeft() {
		int numCalls = 0;
		tempBookShelf.add(sortedBookshelf.removeFront());
		callsToRemoveFront += 1;
		numCalls++;
		assert isValidBookshelfKeeper();
		return (numCalls);
	}
	
	/**
	 * Permanently removes one book from the last position of the sortedBookshlef using
	 * removeLast()
	 * Updates the number of calls made to removeFront().
	 */
	private int deleteFromRight() {
		int numCalls = 0;
		sortedBookshelf.removeLast();
		callsToRemoveLast += 1;
		numCalls++;
		assert isValidBookshelfKeeper();
		return (numCalls);
	}
	
	/**
	 * Permanently removes one book from the first position of the sortedBookshlef using
	 * removeFront()
	 * Updates the number of calls made to removeFront().
	 */
	private int deleteFromLeft() {
		int numCalls = 0;
		sortedBookshelf.removeFront();
		callsToRemoveFront += 1;
		numCalls++;
		assert isValidBookshelfKeeper();
		return (numCalls);
	}

	/**
	 * Adds one book of the given height to the last position of the sortedBookshlef
	 * using addLast() Updates the number of calls made to addLast().
	 */
	private int addFromRight(int bookHeight) {
		int numCalls = 0;
		sortedBookshelf.addLast(bookHeight);
		callsToAddLast += 1;
		numCalls++;
		assert isValidBookshelfKeeper();
		return (numCalls);
	}

	/**
	 * Adds one book of the given height to the first position of the
	 * sortedBookshlef using addFront() Updates the number of calls made to
	 * addLast().
	 */
	private int addFromLeft(int height) {
		int numCalls = 0;
		sortedBookshelf.addFront(height);
		callsToAddFront += 1;
		numCalls++;
		assert isValidBookshelfKeeper();
		return (numCalls);
	}

	/**
	 * Adds the remaining books from the temporary ArrayList to the sortedBoookshelf
	 * from the right side in non-decreasing order.
	 * 
	 * @param lastIndex     Contains the index position occupied by the last book in
	 *                      tempBookShlef.
	 * @param numCurrentOps counts the number of calls made to the mutators.
	 */
	private int restoreTempFromRight() {
		int lastIndex = tempBookShelf.size() - 1;
		int numCurrentOps = 0;
		while (tempBookShelf.size() != 0) {
			numCurrentOps = numCurrentOps + addFromRight(tempBookShelf.remove(lastIndex));
			lastIndex--;
		}
		assert isValidBookshelfKeeper();
		return numCurrentOps;
	}

	/**
	 * Adds the remaining books from the temporary ArrayList to the sortedBoookshelf
	 * from the left side in non-decreasing order.
	 * 
	 * @param lastIndex     Contains the index position occupied by the last book in
	 *                      tempBookShlef.
	 * @param numCurrentOps counts the number of calls made to the mutators.
	 */
	private int restoreTempFromLeft() {
		int lastIndex = tempBookShelf.size() - 1;
		int numCurrentOps = 0;
		while (tempBookShelf.size() != 0) {
			numCurrentOps = numCurrentOps + addFromLeft(tempBookShelf.remove(lastIndex));
			lastIndex--;
		}
		assert isValidBookshelfKeeper();
		return numCurrentOps;
	}

	/*
	 * * Removes the Book from the sortedBookshlef starting from Right till the
	 * specified position and restores the remaining books such that the sorted
	 * bookshelf retains its correct order.
	 * 
	 * Returns the number of calls to mutators on the contained bookshelf used to
	 * complete this operation.
	 * 
	 * @bookPos stores the position of the books in the bookshelf starting from the
	 * book at the right i.e last index.
	 */
	private int helperPickFromRight(int position) {
		currentNumCalls = 0;
		int bookPos = sortedBookshelf.size() - 1;
		while (bookPos > position) {
			bookPos--;
			currentNumCalls = currentNumCalls + removeFromRight();

		}
		currentNumCalls = currentNumCalls + deleteFromRight();

		if (tempBookShelf.size() != 0) {
			currentNumCalls = currentNumCalls + restoreTempFromRight();

		}
		assert isValidBookshelfKeeper();
		return (currentNumCalls);
	}

	/*
	 * * Removes the Book from the sortedBookshlef starting from left till the
	 * specified position is reached and restores the remaining books such that the
	 * sorted bookshelf retains its correct order.
	 * 
	 * Returns the number of calls to mutators on the contained bookshelf used to
	 * complete this operation.
	 * 
	 * @bookPos stores the position of the books in the bookshelf starting from the
	 * book at the left i.e 0th index.
	 */
	private int helperPickFromLeft(int position) {
		currentNumCalls = 0;
		int bookPos = 0;
		while (bookPos < position) {
			bookPos++;
			removeFromLeft();
			currentNumCalls++;
		}

		currentNumCalls = currentNumCalls + deleteFromLeft();

		if (tempBookShelf.size() != 0) {
			currentNumCalls = currentNumCalls + restoreTempFromLeft();
		}
		assert isValidBookshelfKeeper();
		return (currentNumCalls);
	}

	/*
	 * * Adds the book to the sortedBookshlef from the Right side.
	 * 
	 * Returns the number of calls to mutators on the contained bookshelf used to
	 * complete this operation.
	 * 
	 * @sortedBookPos contains the index position of the book starting from the last
	 * book.
	 * 
	 * @bookHeight contains the height of the book at the given position.
	 */
	private int helperPutHeightFromRight(int height) {
		int sortedBookPos = sortedBookshelf.size() - 1;
		int bookHeight = sortedBookshelf.getHeight(sortedBookPos);
		currentNumCalls = 0;

		while (height < bookHeight) {
			sortedBookPos--;
			bookHeight = sortedBookshelf.getHeight(sortedBookPos);
			currentNumCalls = currentNumCalls + removeFromRight();
		}
		currentNumCalls = currentNumCalls + addFromRight(height);
		if (tempBookShelf.size() != 0) {
			currentNumCalls = currentNumCalls + restoreTempFromRight();
		}
		assert isValidBookshelfKeeper();
		return currentNumCalls;
	}

	/*
	 * * Adds the book to the sortedBookshlef from the Left side.
	 * 
	 * Returns the number of calls to mutators on the contained bookshelf used to
	 * complete this operation.
	 * 
	 * @sortedBookPos contains the index position of the book starting from the
	 * index of first book.
	 * 
	 * @bookHeight contains the height of the book at the given position.
	 */
	private int helperPutHeightFromLeft(int height) {
		int sortedBookPos = 0;
		int bookHeight = sortedBookshelf.getHeight(sortedBookPos);
		currentNumCalls = 0;
		while (height > bookHeight) {
			sortedBookPos++;
			bookHeight = sortedBookshelf.getHeight(sortedBookPos);
			currentNumCalls = currentNumCalls + removeFromLeft();
		}
		currentNumCalls = currentNumCalls + addFromLeft(height);
		if (tempBookShelf.size() != 0) {
			currentNumCalls = currentNumCalls + restoreTempFromLeft();
		}
		assert isValidBookshelfKeeper();
		return (currentNumCalls);
	}
}