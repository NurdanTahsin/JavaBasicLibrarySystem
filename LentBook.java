public class LentBook {
    private String borrower;
    private Book book;

    public LentBook(String borrower, Book book) {
        this.borrower = borrower;
        this.book = book;
    }

    public String getBorrower() { return borrower; }
    public Book getBook() { return book; }
}
