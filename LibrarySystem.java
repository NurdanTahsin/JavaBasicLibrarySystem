import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    private ArrayList<Librarian> librarians = new ArrayList<>();
    private Library library = new Library();

    public void addLibrarian(Scanner scanner) {
        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();
        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username)) {
                System.out.println("Bu kullanıcı adı zaten kullanılıyor!");
                return;
            }
        }

        System.out.print("Şifre: ");
        String password = scanner.nextLine();
        librarians.add(new Librarian(username, password));
        System.out.println("Görevli başarıyla eklendi.");
    }

    public void listLibrarians() {
        if (librarians.isEmpty()) {
            System.out.println("Henüz görevli eklenmemiş.");
            return;
        }

        System.out.println("Görevliler:");
        for (Librarian librarian : librarians) {
            System.out.println("- " + librarian.getUsername());
        }
    }

    public boolean librarianLogin(Scanner scanner) {
        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username) && librarian.verifyPassword(password)) {
                System.out.println("Giriş başarılı.");
                return true;
            }
        }

        System.out.println("Geçersiz kullanıcı adı veya şifre.");
        return false;
    }

    public void removeLibrarian(Scanner scanner) {
        System.out.print("Silmek istediğiniz görevlinin kullanıcı adı: ");
        String username = scanner.nextLine();

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username)) {
                librarians.remove(librarian);
                System.out.println("Görevli başarıyla silindi.");
                return;
            }
        }

        System.out.println("Bu kullanıcı adıyla bir görevli bulunamadı.");
    }

    public void showMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("=== Ana Menü ===");
            System.out.println("1) Kitapları Listele");
            System.out.println("2) Kitap Ekle");
            System.out.println("3) Kitap Sil");
            System.out.println("4) Ödünç Ver");
            System.out.println("5) İade Al");
            System.out.println("6) Ödünç Verilen Kitapları Listele");
            System.out.println("7) Stok Güncelle");
            System.out.println("8) Raporlama");
            System.out.println("9) Çıkış");
            System.out.print("Seçiminiz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.listBooks();
                    break;
                case 2:
                    library.addBook(scanner);
                    break;
                case 3:
                    library.removeBook(scanner);
                    break;
                case 4:
                    library.lendBook(scanner);
                    break;
                case 5:
                    library.returnBook(scanner);
                    break;
                case 6:
                    library.listLentBooks();
                    break;
                case 7:
                    library.updateStock(scanner);
                    break;
                case 8:
                    library.showReport();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }
}
