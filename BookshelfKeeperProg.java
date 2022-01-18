
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main program that prompts for input from user. Calls the methods in Bookshelf
 * and BookshelfKeeper class to maintain books. Checks and allows only valid
 * operations to be performed on the Bookshelf.
 * 
 */
public class BookshelfKeeperProg {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Please enter initial arrangement of books followed by new line:");
		ArrayList<Integer> pileOfBooks = setPileOfBooks(in);

		if (isValidBookShlef(pileOfBooks)) {

			BookshelfKeeper bookKeeper = new BookshelfKeeper(new Bookshelf(pileOfBooks));
			System.out.println(bookKeeper.toString());
			System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
			getOperations(in, bookKeeper);
		}
		System.out.println("Exiting Program.");
	}

	/**
	 * Static method that converts to input to integers and stored them in a
	 * ArrayList
	 * 
	 * @param in   the scanner object to receive input from console.
	 * @param line stores the input line.
	 * @return Returns a ArrayList of books.
	 */
	private static ArrayList<Integer> setPileOfBooks(Scanner in) {
		ArrayList<Integer> pilesOfBooks = new ArrayList<Integer>();
		String line = in.nextLine();
		if (line.isEmpty()) {
			return pilesOfBooks;
		}
		Scanner scanString = new Scanner(line);
		while (scanString.hasNextInt()) {
			pilesOfBooks.add(scanString.nextInt());

		}
		scanString.close();
		return (pilesOfBooks);
	}

	/**
	 * Static method that accepts the input operations pick, put and end. Checks if
	 * the input operations are valid and calls methods to perform the required
	 * operation.
	 * 
	 * @param in   the scanner object to receive input from console.
	 * @param line stores the input line.
	 * 
	 */
	private static void getOperations(Scanner in, BookshelfKeeper bookKeeper) {
		boolean operationStatus = true;

		while (operationStatus) {
			String line = in.nextLine();
			Scanner scanString = new Scanner(line);
			String operation = scanString.next();
			int value = 0;
			if (scanString.hasNextInt()) {
				value = scanString.nextInt();
			}
			scanString.close();
			if (isValidOperation(operation)) {
				if (operation.compareToIgnoreCase("pick") == 0 && isValidPickOperation(value, bookKeeper)) {
					bookKeeper.pickPos(value);
					System.out.println(bookKeeper.toString());
				} else if (operation.compareToIgnoreCase("put") == 0 && isValidHeight(value)) {
					bookKeeper.putHeight(value);
					System.out.println(bookKeeper.toString());
				} else if (operation.compareToIgnoreCase("end") == 0) {
					operationStatus = false;
				}
			}
		}
	}

	/**
	 * Static method to check if the bookshelf entered by the user is a valid one.
	 * 
	 * @param inputArray The initial bookshelf that is created.
	 * @return true if bookshelf is valid.
	 */
	private static boolean isValidBookShlef(ArrayList<Integer> inputArray) {
		for (int i = 0; i < inputArray.size() - 1; i++) {
			if (inputArray.get(i) > inputArray.get(i + 1)) {
				System.out.println("ERROR: Heights must be specified in non-decreasing order.");
				return false;
			}
		}

		for (int height : inputArray) {
			if (height < 0) {
				System.out.println("ERROR: Height of a book must be positive.");
				return false;
			}
		}
		return true;
	}

	/**
	 * Static method to check if operation that has to be performed is a valid one.
	 * 
	 * @param currentOperation The operation entered by the user.
	 * @return true if the currentOperation is valid.
	 */
	private static boolean isValidOperation(String currentOperation) {
		if (currentOperation.compareToIgnoreCase("pick") != 0 && currentOperation.compareToIgnoreCase("put") != 0
				&& currentOperation.compareToIgnoreCase("end") != 0) {
			System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
			return false;
		}
		return true;

	}

	/**
	 * Static method to check if the specified pick operation can be performed on
	 * the sortedBookShlef.
	 * 
	 * @param value      the position of the pick operation specified by the user.
	 * @param bookKeeper the BookshlefKeeper object on which the pick validity is
	 *                   checked.
	 * @return true if the operation is valid.
	 */
	private static boolean isValidPickOperation(int value, BookshelfKeeper bookKeeper) {
		if (!isValidHeight(value)) {
			return false;
		}
		if (value >= bookKeeper.getNumBooks()) {
			System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
			return false;
		}
		return true;
	}

	/**
	 * Static method to check if the specified put operation can be performed on the
	 * sortedBookShlef.
	 * 
	 * @param value the height of the book that has to be placed inside the
	 *              sortedBookShlef.
	 * @return true if the operation is valid.
	 */

	private static boolean isValidHeight(int value) {
		if (value < 0) {
			System.out.println("ERROR:Height of a book must be positive.");
			return false;
		}
		return true;
	}

}
