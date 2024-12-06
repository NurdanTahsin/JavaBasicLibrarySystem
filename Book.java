public class Book {
    private String title;
    private String author;
    private String isbn;
    private int totalStock;
    private int availableStock;

    public Book(String title, String author, String isbn, int stock) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalStock = stock;
        this.availableStock = stock;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getTotalStock() { return totalStock; }
    public int getAvailableStock() { return availableStock; }

    public void decreaseAvailableStock() { availableStock--; }
    public void increaseAvailableStock() { availableStock++; }
    public void updateStock(int change) {
        totalStock += change;
        availableStock += change;
    }
}
