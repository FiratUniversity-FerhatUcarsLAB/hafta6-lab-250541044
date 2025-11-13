Ad-Soyad:Eren Bilge Koçak
Ödev : 6
FAKÜLTESİ:Teknoloji Fakültesi
DERS:ALGORİTMA
  
import java.util.Scanner;

public class sinemaBileti {


    public static boolean isWeekend(int gun) {
        switch (gun) {
            case 6:
                return true;
            default:
                return false;
        }
    }



    public static boolean isMatinee(int saat) {

        return (saat < 12) ? true : false;
    }


    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (haftaSonu) {
            if (matine) {
                return 55.0;
            } else {
                return 85.0;
            }
        } else {
            if (matine) {
                return 45.0;
            } else {
                return 65.0;
            }
        }
    }


    public static double calculateDiscountRate(int yas, int meslek, int gun) {

        if (yas < 12) {
            return 0.25;
        }
        if (yas >= 65) {
            return 0.30;
        }


        switch (meslek) {
            case 1:

                if (gun == 5 || isWeekend(gun)) {
                    return 0.15;
                } else {
                    return 0.20;
                }

            case 2:
                if (gun == 3) {
                    return 0.35;
                }
                break;

            case 3:
            default:
                break;
        }

        return 0.0;
    }


    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 2: // 3D
                return 25.0;
            case 3: // IMAX
                return 35.0;
            case 4: // 4DX
                return 50.0;
            default: // 1 (2D) veya geçersiz
                return 0.0;
        }
    }


    public static double calculateFinalPrice(double basePrice, double discountRate, double formatExtra) {
        double discountAmount = basePrice * discountRate;
        double discountedPrice = basePrice - discountAmount;
        return discountedPrice + formatExtra;
    }


    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {


        double basePrice = calculateBasePrice(gun, saat);


        double discountRate = calculateDiscountRate(yas, meslek, gun);
        double discountAmount = basePrice * discountRate;


        double formatExtra = getFormatExtra(filmTuru);


        double finalPrice = calculateFinalPrice(basePrice, discountRate, formatExtra);


        System.out.println("\n=== SİNEMA BİLETİ RAPORU ===");
        System.out.printf("Temel Fiyat       : %.2f TL\n", basePrice);
        System.out.printf("İndirim Tutarı    : -%.2f TL (%%%d)\n", discountAmount, (int)(discountRate * 100));
        System.out.printf("Film Format Ekstra : +%.2f TL\n", formatExtra);
        System.out.println("---------------------------------");
        System.out.printf("TOPLAM ÖDENECEK   : %.2f TL\n", finalPrice);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- AKILLI SİNEMA BİLET SİSTEMİ ---");

        // Girdilerin alınması (Basit doğrulama varsayıldı)
        System.out.print("Gün (1=Pzt, 2=Salı, ..., 7=Pazar): ");
        int gun = scanner.nextInt();

        System.out.print("Saat (8-23 arası, örn: 14): ");
        int saat = scanner.nextInt();

        System.out.print("Yaşınızı giriniz: ");
        int yas = scanner.nextInt();

        System.out.print("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = scanner.nextInt();

        System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = scanner.nextInt();


        generateTicketInfo(gun, saat, yas, meslek, filmTuru);

        scanner.close();
    }
}
