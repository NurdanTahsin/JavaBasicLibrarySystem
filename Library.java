import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<LentBook> lentBooks = new ArrayList<>();

    // Kitapları Listele
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("Kütüphanede henüz eklenmiş kitap bulunmuyor.");
            return;
        }

        System.out.println("=== Kitap Listesi ===");
        for (Book book : books) {
            System.out.println("Ad: " + book.getTitle() +
                    ", Yazar: " + book.getAuthor() +
                    ", ISBN: " + book.getIsbn() +
                    ", Toplam Stok: " + book.getTotalStock() +
                    ", Mevcut Stok: " + book.getAvailableStock());
        }
    }

    // Kitap Ekle
    public void addBook(Scanner scanner) {
        System.out.println("=== Kitap Ekle ===");

        System.out.print("Kitap adı: ");
        String title = scanner.nextLine();

        System.out.print("Yazar adı: ");
        String author = scanner.nextLine();

        System.out.print("ISBN numarası: ");
        String isbn = scanner.nextLine();

        // ISBN kontrolü
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                System.out.println("Bu ISBN ile bir kitap zaten mevcut! Lütfen stok güncelleme yapın.");
                return;
            }
        }

        System.out.print("Stok adedi: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Enter tuşu girdisini temizle

        // Yeni kitabı listeye ekle
        books.add(new Book(title, author, isbn, stock));
        System.out.println("Kitap başarıyla eklendi!");
    }

    // Kitap Sil
    public void removeBook(Scanner scanner) {
        System.out.println("=== Kitap Sil ===");

        System.out.print("Silinecek kitabın ISBN numarasını girin: ");
        String isbn = scanner.nextLine();

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                System.out.println("Kitap başarıyla silindi.");
                return;
            }
        }
        System.out.println("Girilen ISBN numarasına ait bir kitap bulunamadı.");
    }

    // Ödünç Kitap Ver
    public void lendBook(Scanner scanner) {
        System.out.println("=== Ödünç Ver ===");

        System.out.print("Ödünç almak isteyen kişinin adı: ");
        String borrower = scanner.nextLine();

        System.out.print("Kitabın ISBN numarasını girin: ");
        String isbn = scanner.nextLine();

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.getAvailableStock() > 0) {
                    book.decreaseAvailableStock();
                    lentBooks.add(new LentBook(borrower, book));
                    System.out.println("Kitap ödünç verildi.");
                } else {
                    System.out.println("Bu kitap ödünç verilemez. Stokta bulunmuyor.");
                }
                return;
            }
        }
        System.out.println("Girilen ISBN numarasına ait bir kitap bulunamadı.");
    }

    // İade İşlemi
    public void returnBook(Scanner scanner) {
        System.out.println("=== İade Al ===");

        System.out.print("İade eden kişinin adı: ");
        String borrower = scanner.nextLine();

        System.out.print("Kitabın ISBN numarasını girin: ");
        String isbn = scanner.nextLine();

        for (LentBook lentBook : lentBooks) {
            if (lentBook.getBorrower().equals(borrower) && lentBook.getBook().getIsbn().equals(isbn)) {
                lentBook.getBook().increaseAvailableStock();
                lentBooks.remove(lentBook);
                System.out.println("Kitap başarıyla iade alındı.");
                return;
            }
        }
        System.out.println("İade edilmek istenen kitap bulunamadı.");
    }

    // Ödünç Verilen Kitapları Listele
    public void listLentBooks() {
        if (lentBooks.isEmpty()) {
            System.out.println("Şu anda ödünç verilmiş kitap bulunmuyor.");
            return;
        }

        System.out.println("=== Ödünç Verilen Kitaplar ===");
        for (LentBook lentBook : lentBooks) {
            System.out.println("Kitap Adı: " + lentBook.getBook().getTitle() +
                    ", Yazar: " + lentBook.getBook().getAuthor() +
                    ", ISBN: " + lentBook.getBook().getIsbn() +
                    ", Ödünç Alan: " + lentBook.getBorrower());
        }
    }

    // Stok Güncelle
    public void updateStock(Scanner scanner) {
        System.out.println("=== Stok Güncelle ===");

        System.out.print("Kitabın ISBN numarasını girin: ");
        String isbn = scanner.nextLine();

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                System.out.print("Eklenecek/Çıkartılacak stok miktarı (negatif azaltır): ");
                int change = scanner.nextInt();
                scanner.nextLine();

                if (book.getAvailableStock() + change < 0) {
                    System.out.println("Hata: Mevcut stok negatif olamaz.");
                } else {
                    book.updateStock(change);
                    System.out.println("Stok başarıyla güncellendi.");
                }
                return;
            }
        }
        System.out.println("Girilen ISBN numarasına ait bir kitap bulunamadı.");
    }

    // Raporlama
    public void showReport() {
        System.out.println("=== Raporlama ===");
        int totalLent = lentBooks.size();
        int totalStock = books.stream().mapToInt(Book::getTotalStock).sum();

        System.out.println("Toplam kitap stoğu: " + totalStock);
        System.out.println("Ödünç verilmiş toplam kitap: " + totalLent);
    }
}
