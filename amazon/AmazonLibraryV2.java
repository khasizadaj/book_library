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

	public void run() {
		AudioBook firstBook = new AudioBook("AB1", "AB First Author", "The Very First Book", 222, 2022, "Johnny Depp");
		PaperBook secondBook = new PaperBook("PB1", "PB First Author", "The Very Second Book", 202, 2022, 3456);
		LIBRARY.add(firstBook);
		LIBRARY.add(secondBook);

		boolean will_continue = true;
		while (will_continue) {
			System.out.print("What do you want to do?: ");
			String action = SCANNER.nextLine().toLowerCase();
			boolean status = performAction(action);

			if (status == true) {
				System.out.print("Do you wanna perform new action in library again? [Y(y)/N(n)]: ");
				String decision = SCANNER.nextLine();

				if (decision.toLowerCase().equals("n")) {
					will_continue = false;
				}
			} else {
				System.out.println("You can provide these action words: create, retrieve, update, delete");
			}
		}

	}

	public boolean performAction(String action) {
		Book book;
		boolean result = true;

		switch (action) {
			case "create":
				book = createBook();
				LIBRARY.add(book);
				System.out.println("New book added:");
				book.display();
				break;
			case "retrieve":
				book = retrieveBook();
				if (book == null) {
					System.out.println("There is no such book in the library.");
					performAction("retrieve");
				} else {
					book.start();
				}

				break;
			case "update":
				book = updateBook();
				book.display();
				;
				break;
			case "delete":
				book = deleteBook();
				if (book == null) {
					System.out.println("There is no such book in the library. Please, try again.");
					performAction("delete");
				} else {
					System.out.println("Deleted book is ... ");
					book.display();
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
		System.out.println("What type of book do you want to add? (type number/id): \n1. Paper book \n2. Audio book");
		System.out.print("Type your choice: ");
		int bookType = SCANNER.nextInt();

		// throw away the \n not consumed by nextInt()
		SCANNER.nextLine();

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

		Book book;
		if (bookType == 1) {
			System.out.print("Copy count: ");
			int copyCount = SCANNER.nextInt();

			// throw away the \n not consumed by nextInt()
			SCANNER.nextLine();

			book = new PaperBook(asin, author, title, pageCount, publishingYear, copyCount);
		} else {
			System.out.print("Narrator: ");
			String narrator = SCANNER.nextLine();
			book = new AudioBook(asin, author, title, pageCount, publishingYear, narrator);
		}
		return book;
	}

	public static String getIdentifier() {
		String identifier = SCANNER.nextLine();

		// TODO Identifier check should be added
		return identifier;
	}

	public Book retrieveBook() {

		System.out.print("Please, provide ASIN: ");
		String identifier = getIdentifier();

		Book retrievedBook = null;
		for (Book book : LIBRARY) {
			if (book.checkIdentifier(identifier)) {
				retrievedBook = book;
			}
		}

		return retrievedBook;
	}

	public Book updateBook() {
		Book book = retrieveBook();

		System.out.println("Current book details ...");
		book.display();

		System.out.println("Copy the line below and modify book details as you like.");
		String bookDetails = book.toString();
		System.out.println(bookDetails);
		System.out.print("Add your updated book details: ");

		String newBookDetails = SCANNER.nextLine();

		if (bookDetails.equals(newBookDetails)) {
			System.out.println("Book details were not updated. It is the same.");
			return book;
		}

		book.update(newBookDetails);
		return book;
	}

	public Book deleteBook() {
		Book book = retrieveBook();

		// Book result = null;
		// for (int i = 0; i < LIBRARY.size(); i++) {
		// Book currBook = LIBRARY.get(i);

		// if (book.checkIdentifier(currBook.asin)) {
		// try {
		// result = (PaperBook) book.clone();
		// } catch (CloneNotSupportedException exc) {
		// System.out.println("Element cannot be cloned.");
		// }
		// LIBRARY.remove(i);
		// break;
		// }

		// }
		LIBRARY.remove(book);

		return book;
	}

	// public static void printBook(PaperBook book) {
	// System.out.println("ASIN: %s".formatted(book.asin));
	// System.out.println("Author: %s".formatted(book.author));
	// System.out.println("Title: %s".formatted(book.title));
	// System.out.println("Page count: %s".formatted(book.pageCount));
	// System.out.println("Publishing year: %s".formatted(book.publishingYear));
	// }
}
