Ad-Soyad:Eren Bilge Koçak
Ödev : 6
FAKÜLTESİ:Teknoloji Fakültesi
DERS:ALGORİTMA
import java.util.Scanner;

public class RestoranSiparis {


    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0;  // Izgara Tavuk
            case 2: return 120.0; // Adana Kebap
            case 3: return 110.0; // Levrek
            case 4: return 65.0;  // Mantı
            default: return 0.0;
        }
    }


    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0; // Çorba
            case 2: return 45.0; // Humus
            case 3: return 55.0; // Sigara Böreği
            default: return 0.0;
        }
    }


    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0; // Kola
            case 2: return 12.0; // Ayran
            case 3: return 35.0; // Taze Meyve Suyu
            case 4: return 25.0; // Limonata
            default: return 0.0;
        }
    }


    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0; // Künefe
            case 2: return 55.0; // Baklava
            case 3: return 35.0; // Sütlaç
            default: return 0.0;
        }
    }


    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }


    public static boolean isHappyHour(int saat) {
        // 14:00 dahil, 17:00 dahil değil (17:00'den önce)
        return (saat >= 14 && saat < 17);
    }


    public static double calculateServiceTip(double sonTutar) {
        return sonTutar * 0.10;
    }


    public static void calculateFinalPriceAndPrint(double araToplam, double icecekFiyati,
                                                   boolean combo, boolean happyHour, boolean ogrenci) {

        double toplam = araToplam;
        double comboIndirimTutari = 0;
        double genelIndirimTutari = 0; // 200 TL üzeri indirim
        double hhIndirimTutari = 0;
        double ogrenciIndirimTutari = 0;

        System.out.println("\n=== SİPARİŞ FİŞİ ===");
        System.out.printf("Ara Toplam          : %.2f TL\n", araToplam);

        // --- İndirimlerin Kademeli Uygulanması (İç İçe If Mantığı) ---

        // 1. Combo VEYA 200 TL üzeri indirim
        if (combo) {
            comboIndirimTutari = araToplam * 0.15;
            toplam -= comboIndirimTutari;
            System.out.printf("Combo İndirimi (%%15)  : -%.2f TL\n", comboIndirimTutari);
        } else if (araToplam > 200) {
            genelIndirimTutari = araToplam * 0.10;
            toplam -= genelIndirimTutari;
            System.out.printf(">200 TL İndirimi (%%10) : -%.2f TL\n", genelIndirimTutari);
        }

        // 2. Happy Hour İndirimi (İçecek fiyatı üzerinden)
        if (happyHour && icecekFiyati > 0) {
            hhIndirimTutari = icecekFiyati * 0.20;
            toplam -= hhIndirimTutari;
            System.out.printf("Happy Hour (İçecek) : -%.2f TL\n", hhIndirimTutari);
        }

        // 3. Öğrenci İndirimi (Kalan tutar üzerinden)
        if (ogrenci) {
            ogrenciIndirimTutari = toplam * 0.10;
            toplam -= ogrenciIndirimTutari;
            System.out.printf("Öğrenci (H.İçi %%10) : -%.2f TL\n", ogrenciIndirimTutari);
        }

        // --- Son Hesaplama ---
        double bahsis = calculateServiceTip(toplam);

        System.out.println("-------------------------------------");
        System.out.printf("Toplam Tutar        : %.2f TL\n", toplam);
        System.out.printf("Bahşiş Önerisi (%%10) : %.2f TL\n", bahsis);
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- AKILLI RESTORAN SİPARİŞ SİSTEMİ ---");

        // 1. Girdilerin Alınması
        System.out.print("1. Ana Yemek (1-4, 0=İstemiyor): ");
        int anaYemekSecim = scanner.nextInt();

        System.out.print("2. Başlangıç (1-3, 0=İstemiyor): ");
        int baslangicSecim = scanner.nextInt();

        System.out.print("3. İçecek (1-4, 0=İstemiyor): ");
        int icecekSecim = scanner.nextInt();

        System.out.print("4. Tatlı (1-3, 0=İstemiyor): ");
        int tatliSecim = scanner.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = scanner.nextInt();

        System.out.print("Öğrenci misiniz? (E/H): ");
        String ogrenciGirdisi = scanner.next();

        System.out.print("Hangi Gün (1=Pzt, ..., 7=Paz): ");
        int gun = scanner.nextInt();

        // 2. Fiyatların ve Ara Toplamın Hesaplanması
        double anaYemekFiyati = getMainDishPrice(anaYemekSecim);
        double baslangicFiyati = getAppetizerPrice(baslangicSecim);
        double icecekFiyati = getDrinkPrice(icecekSecim);
        double tatliFiyati = getDessertPrice(tatliSecim);

        double araToplam = anaYemekFiyati + baslangicFiyati + icecekFiyati + tatliFiyati;

        // 3. Boolean (Durum) Değişkenlerinin Ayarlanması
        boolean anaVar = anaYemekFiyati > 0;
        boolean icecekVar = icecekFiyati > 0;
        boolean tatliVar = tatliFiyati > 0;

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);
        boolean happyHour = isHappyHour(saat);

        // Öğrenci indirimi sadece hafta içi (gun < 6) geçerli
        boolean ogrenciIndirimiGecerli = (ogrenciGirdisi.equalsIgnoreCase("E")) && (gun >= 1 && gun <= 5);

        // 4. Nihai Hesaplama ve Fiş Yazdırma
        calculateFinalPriceAndPrint(araToplam, icecekFiyati, combo, happyHour, ogrenciIndirimiGecerli);

        scanner.close();
    }
}

