package amazon;

public class Book {
	public String asin;
	public String author;
	public String title;
	public int pageCount;
	public int publishingYear;
	public String content;

	public Book(String asin,
			String author,
			String title,
			int pageCount,
			int publishingYear) {
		this.asin = asin;
		this.author = author;
		this.title = title;
		this.pageCount = pageCount;
		this.publishingYear = publishingYear;

		this.content = "Content of the book.";
	}

	public String toString() {
		return "%s, %s, %s, %s, %s".formatted(this.asin, this.author, this.title, this.pageCount, this.publishingYear);
	}

	public Book update(String newDetails) {
		String[] detailsArray = newDetails.split(",");

		this.asin = detailsArray[0].strip();
		this.author = detailsArray[1].strip();
		this.title = detailsArray[2].strip();
		this.pageCount = Integer.parseInt(detailsArray[3].strip());
		this.publishingYear = Integer.parseInt(detailsArray[4].strip());

		return this;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
