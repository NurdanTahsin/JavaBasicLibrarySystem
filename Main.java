import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem librarySystem = new LibrarySystem();

        while (true) {
            System.out.println("=== Kütüphane Yönetim Sistemi ===");
            System.out.println("1) Görevli Ekle");
            System.out.println("2) Görevli Listele");
            System.out.println("3) Görevli Girişi");
            System.out.println("4) Görevli Sil");
            System.out.println("5) Çıkış");
            System.out.print("Seçiminiz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    librarySystem.addLibrarian(scanner);
                    break;
                case 2:
                    librarySystem.listLibrarians();
                    break;
                case 3:
                    if (librarySystem.librarianLogin(scanner)) {
                        librarySystem.showMainMenu(scanner);
                    }
                    break;
                case 4:
                    librarySystem.removeLibrarian(scanner);
                    break;
                case 5:
                    System.out.println("Sistemden çıkılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }
}
