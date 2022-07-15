/*
AMAZON LIBRARY CLI APP

It will be small library app that will implement CRUD operations
using simple Java functionalities. It will be able to do these
below:
- Prompth user to choose operation type
- Create new book to the library which will have these details:
	- ASIN - Amazon Service Identification Number
	- Author
	- Title
	- Page count
	- Publishing year
- Retrieve needed book using DOI of the book
- Update book details
- Delete book from the library

====

Java concepts used:
- Scanner
- ArrayList
- Array
- Loops
- Switch
- Methods

*/

package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AmazonLibrary {
	static ArrayList<String[]> LIBRARY = new ArrayList<String[]>();
	static Scanner SCANNER = new Scanner(System.in);

	// ASIN, Author, Title, Page count, Publishing year
	static Integer BOOK_ELEMENTS_COUNT = 5;

	public static void run() {
		String[] book = {"JL1", "Luiza", "Happiness", "202", "2022"};
		LIBRARY.add(book);
		Boolean tbc = true;
		while (tbc) {
			System.out.print("What do you want to do?: ");
			String action = SCANNER.nextLine().toLowerCase();
			Boolean status = performAction(action);

			if (status == true) {
				System.out.print("Do you wanna do it again? [Y(y)/N(n)]: ");
				String decision = SCANNER.nextLine();
				
				if (decision.toLowerCase().equals("n")) {
					tbc = false;
				}
			} 
			else {
				System.out.println("You can provide these action words: create, read, update, delete");
			}	
		}

	}

	static Boolean performAction(String action) {
		String[] book;
		Boolean result = true;
		
		switch (action) {
			case "create":
				book = createBook();
				LIBRARY.add(book);
				printLine("New book added:");
				printBook(book);
				break;
			case "read":
				book = retrieveBook();
				if (book == null) {
					printLine("There is no such book in the library.");
				} else
					printBook(book);
				break;
			case "update":
				book = updateBook();
				printBook(book);
				break;
			case "delete":
				book = deleteBook();
				if (book != null) {
					System.out.print("Deleted book is: ");
					printBook(book);
				}
				break;
			default:
				printLine("There is no such action. Please, check your input and try again.");
				result = false;
				break;
		}
		return result;
	}
	
	static String[] createBook() {
		System.out.print("ASIN: ");
		String asin = SCANNER.nextLine();
		System.out.print("Author: ");
		String author = SCANNER.nextLine();
		System.out.print("Title: ");
		String title = SCANNER.nextLine();
		System.out.print("Page count: ");
		String pageCount = SCANNER.nextLine();
		System.out.print("Year published: ");
		String publishedYear = SCANNER.nextLine();

		String[] book = { asin, author, title, pageCount, publishedYear };

		return book;
	}

	static String getIdentifier() {
		String identifier = SCANNER.nextLine();

		// TODO Identifier check should be added
		return identifier;
	}

	static String[] retrieveBook() {

		System.out.print("Please, provide ASIN: ");
		String identifier = getIdentifier().toLowerCase();

		String[] retrievedBook = null;
		for (String[] book : LIBRARY) {
			String bookIdentifier = book[0].toLowerCase();
			if (bookIdentifier.equals(identifier)) {
				retrievedBook = book;
			}
		}

		return retrievedBook;
	}

	static String[] deleteBook() {
		System.out.print("ASIN: ");
		String identifier = getIdentifier();

		String[] result = null;
		for (int i = 0; i < LIBRARY.size(); i++) {
			String[] book = LIBRARY.get(i);

			if (book[0].equals(identifier)) {
				result = LIBRARY.get(i).clone();
				LIBRARY.remove(i);
				break;
			}

		}
		return result;
	}

	static String[] updateBook() {
		String[] book = retrieveBook();

		System.out.println("Current book details");
		printBook(book);

		System.out.println("Copy the line below and modify book details as you like.");
		String bookDetails = Arrays.toString(book).substring(1, Arrays.toString(book).length() - 1);
		System.out.println(bookDetails);
		System.out.print("Add your updated book details: ");

		String newBookDetails = SCANNER.nextLine();

		if (bookDetails.equals(newBookDetails) != true) {
			book = newBookDetails.split(",");
		} else
			System.out.println("Book details were not updated. It is the same.");

		return book;
	}

	static void printBook(String[] book) {
		System.out.println("ASIN: %s".formatted(book[0]));
		System.out.println("Author: %s".formatted(book[1]));
		System.out.println("Title: %s".formatted(book[2]));
		System.out.println("Page count: %s".formatted(book[3]));
		System.out.println("Publishing year: %s".formatted(book[4]));
	}

	static void printLine(String message) {
		System.out.println(message);
	}
}
