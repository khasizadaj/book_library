package amazon;

public interface Book {

	String provider = "Amazon";

	public String toString();

	public Book update(String newDetails);

	public void start();

	public void display();

	public boolean checkIdentifier(String identifier);

	default String getProviderMessage() {
		return "This book is provided by \"%s\".".formatted(Book.provider);
	}
}
