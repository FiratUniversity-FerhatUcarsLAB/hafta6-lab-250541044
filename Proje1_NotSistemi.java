Ad-Soyad:EREN BİLGE KOÇAK
ÖDEV:6
FAKÜLTESİ:TEKNOLOJİ FAKÜLTESİ
DERS:ALGORİTMA
import java.util.Scanner;

public class notSistemi {


    public static double calculateAverage(double vize, double finalNotu, double odev) {
        return (vize * 0.30) + (finalNotu * 0.40) + (odev * 0.30);
    }

    public static String isPassingGrade(double ortalama) {
        return ortalama >= 50 ? "GEÇTİ" : "KALDI";
    }

    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90) {
            return "A";
        } else if (ortalama >= 80) {
            return "B";
        } else if (ortalama >= 70) {
            return "C";
        } else if (ortalama >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static String isHonorList(double ortalama, double vize, double finalNotu, double odev) {
        if (ortalama >= 85 && vize >= 70 && finalNotu >= 70 && odev >= 70) {
            return "EVET";
        }
        return "YOK";
    }

    public static String hasRetakeRight(double ortalama) {
        if (ortalama >= 40 && ortalama < 50) {
            return "VAR";
        }
        return "YOK";
    }

    // Ana metot: Kullanıcıdan notları alır ve raporu oluşturur
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- ÖĞRENCİ NOT GİRİŞİ ---");

        System.out.print("Vize Notunu (0-100) giriniz: ");
        double vize = scanner.nextDouble();

        System.out.print("Final Notunu (0-100) giriniz: ");
        double finalNotu = scanner.nextDouble();

        System.out.print("Ödev Notunu (0-100) giriniz: ");
        double odev = scanner.nextDouble();

        // Not aralığı kontrolü
        if (vize < 0 || vize > 100 || finalNotu < 0 || finalNotu > 100 || odev < 0 || odev > 100) {
            System.err.println("\n--- HATA ---");
            System.err.println("Notlar 0 ile 100 arasında olmalıdır. Lütfen programı yeniden başlatın.");
            return;
        }

        // Hesaplamalar
        double ortalama = calculateAverage(vize, finalNotu, odev);
        String durum = isPassingGrade(ortalama);
        String harfNotu = getLetterGrade(ortalama);
        String onurListesi = isHonorList(ortalama, vize, finalNotu, odev);
        String butunleme = hasRetakeRight(ortalama);

        
        System.out.println("\n=== OGRENCI NOT RAPORU ===");
        System.out.println("Vize Notu : " + String.format("%.1f", vize));
        System.out.println("Final Notu : " + String.format("%.1f", finalNotu));
        System.out.println("Odev Notu : " + String.format("%.1f", odev));
        System.out.println("------------------------------");
        System.out.println("Ortalama : " + String.format("%.1f", ortalama));
        System.out.println("Harf Notu : " + harfNotu);
        System.out.println("Durum : " + durum);
        System.out.println("Onur Listesi : " + onurListesi);
        System.out.println("Butunleme : " + butunleme);

        scanner.close();
    }
}
