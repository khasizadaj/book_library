/*
AMAZON LIBRARY CLI APP

Implementationof AmazonLibrary logic with a bit more advanced Java concepts.

====

Java concepts used:
- Classes
- Objects

*/

package amazon;

import java.util.ArrayList;
import java.util.Scanner;

public class AmazonLibraryV2 {
	static ArrayList<Book> LIBRARY = new ArrayList<Book>();
	static Scanner SCANNER = new Scanner(System.in);

	// ASIN, Author, Title, Page count, Publishing year
	static Integer BOOK_ELEMENTS_COUNT = 5;

	public void run() {
		Book firstBook = new Book("FB1", "First Author", "The Very First Book", 222, 2022);
		LIBRARY.add(firstBook);

		Boolean will_continue = true;
		while (will_continue) {
			System.out.print("What do you want to do?: ");
			String action = SCANNER.nextLine().toLowerCase();
			Boolean status = performAction(action);

			if (status == true) {
				System.out.print("Do you wanna perform new action in library again? [Y(y)/N(n)]: ");
				String decision = SCANNER.nextLine();

				if (decision.toLowerCase().equals("n")) {
					will_continue = false;
				}
			} else {
				System.out.println("You can provide these action words: create, read, update, delete");
			}
		}

	}

	public Boolean performAction(String action) {
		Book book;
		Boolean result = true;

		switch (action) {
			case "create":
				book = createBook();
				LIBRARY.add(book);
				System.out.println("New book added:");
				printBook(book);
				break;
			case "read":
				book = retrieveBook();
				if (book == null) {
					System.out.println("There is no such book in the library.");
				} else {
					printBook(book);
					System.out.println("Reading: ".formatted(book.content));
				}

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
				System.out.println("There is no such action. Please, check your input and try again.");
				result = false;
				break;
		}
		return result;
	}

	public Book createBook() {
		System.out.print("ASIN: ");
		String asin = SCANNER.nextLine();
		System.out.print("Author: ");
		String author = SCANNER.nextLine();
		System.out.print("Title: ");
		String title = SCANNER.nextLine();
		System.out.print("Page count: ");
		int pageCount = SCANNER.nextInt();

		// throw away the \n not consumed by nextInt()
		SCANNER.nextLine();

		System.out.print("Year published: ");
		int publishingYear = SCANNER.nextInt();

		// throw away the \n not consumed by nextInt()
		SCANNER.nextLine();

		return new Book(asin, author, title, pageCount, publishingYear);
	}

	public static String getIdentifier() {
		String identifier = SCANNER.nextLine();

		// TODO Identifier check should be added
		return identifier;
	}

	public Book retrieveBook() {

		System.out.print("Please, provide ASIN: ");
		String identifier = getIdentifier().toLowerCase();

		Book retrievedBook = null;
		for (Book book : LIBRARY) {
			String bookIdentifier = book.asin.toLowerCase();
			if (bookIdentifier.equals(identifier)) {
				retrievedBook = book;
			}
		}

		return retrievedBook;
	}

	public Book deleteBook() {
		System.out.print("ASIN: ");
		String identifier = getIdentifier();

		Book result = null;
		for (int i = 0; i < LIBRARY.size(); i++) {
			Book book = LIBRARY.get(i);

			if (book.asin.equals(identifier)) {
				try {
					result = (Book) book.clone();
				} catch (CloneNotSupportedException exc) {
					System.out.println("Element cannot be cloned.");
				}
				LIBRARY.remove(i);
				break;
			}

		}
		return result;
	}

	public Book updateBook() {
		Book book = retrieveBook();

		System.out.println("Current book details");
		printBook(book);

		System.out.println("Copy the line below and modify book details as you like.");
		String bookDetails = book.toString();
		System.out.println(bookDetails);
		System.out.print("Add your updated book details: ");

		String newBookDetails = SCANNER.nextLine();

		if (bookDetails.equals(newBookDetails) != true) {
			book.update(newBookDetails);
		} else
			System.out.println("Book details were not updated. It is the same.");

		return book;
	}

	public static void printBook(Book book) {
		System.out.println("ASIN: %s".formatted(book.asin));
		System.out.println("Author: %s".formatted(book.author));
		System.out.println("Title: %s".formatted(book.title));
		System.out.println("Page count: %s".formatted(book.pageCount));
		System.out.println("Publishing year: %s".formatted(book.publishingYear));
	}
}
