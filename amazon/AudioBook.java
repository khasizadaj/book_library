package amazon;

public class AudioBook implements Book {

	public String asin;
	public String author;
	public String title;
	public int pageCount;
	public int publishingYear;

	public String narrator;

	// constructor
	public AudioBook(String asin,
			String author,
			String title,
			int pageCount,
			int publishingYear,
			String narrator) {
		this.asin = asin;
		this.author = author;
		this.title = title;
		this.narrator = narrator;
		this.pageCount = pageCount;
		this.publishingYear = publishingYear;
	}

	public void start() {
		System.out.println("Listening to the book ...");

		this.display();
	}

	public void display() {
		System.out.println("---");
		System.out.println("ASIN: %s".formatted(this.asin));
		System.out.println("Author: %s".formatted(this.author));
		System.out.println("Title: %s".formatted(this.title));
		System.out.println("Page count: %s".formatted(this.pageCount));
		System.out.println("Publishing year: %s".formatted(this.publishingYear));
		System.out.println("Narrator: %s".formatted(this.narrator));
		System.out.println("\n" + this.getProviderMessage());
		System.out.println("---");
	}

	public Book update(String newDetails) {
		String[] detailsArray = newDetails.split(",");

		this.asin = detailsArray[0].strip();
		this.author = detailsArray[1].strip();
		this.title = detailsArray[2].strip();
		this.pageCount = Integer.parseInt(detailsArray[3].strip());
		this.publishingYear = Integer.parseInt(detailsArray[4].strip());
		this.narrator = detailsArray[5].strip();

		return this;
	}

	public String toString() {
		return "%s, %s, %s, %s, %s, %s".formatted(this.asin, this.author, this.title, this.pageCount,
				this.publishingYear, this.narrator);
	}

	public boolean checkIdentifier(String identifier) {
		return this.asin.toLowerCase().equals(identifier.toLowerCase());
	}
}
