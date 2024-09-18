package org.example;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class App {

    private static final int TIMMAR_I_DYGNET = 24;
    private static final int[] elPriser = new int[TIMMAR_I_DYGNET];
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.forLanguageTag("sv-SE"));

    public static void main(String[] args) {
        System.out.println("Hello There!");
        String val;

        boolean fungerarKoden = true;
        while (fungerarKoden) {
            printMeny();
            val = scanner.nextLine();

            switch (val) {
                case "1" :
                    angeElpriser();
                    break;
                case "2" :
                    visaMinMaxMedel();
                    break;
                case "3" :
                    sorteraOchVisaPriser();
                    break;
                case "4" :
                    hittaBastaLaddningsTid();
                    break;
                case "e" :
                case "E" :
                 {System.out.println("Program avslutade."); }
                    fungerarKoden = false;
                    break;
                default :
                    System.out.println("Ogiltigt val. Försök igen!");
            }
        }
    }

    private static void hittaBastaLaddningsTid(){
        int bastaStartTimme = 0;
        int lagstaTotal = Integer.MAX_VALUE;

        for (int i = 0; i<= TIMMAR_I_DYGNET-4; i++){
            int totalPris = elPriser[i]+ elPriser[i+1] + elPriser[i+2] + elPriser[i+3];
            if (totalPris < lagstaTotal){
                lagstaTotal = totalPris;
                bastaStartTimme = i;
            }
        }
        double medelPris = lagstaTotal / 4.0;
        System.out.printf(Locale.forLanguageTag("sv-SE"), "Bästa tiden att börja ladda: %02d-%02d, genomsnittligt pris: %.2f öre\n",
                bastaStartTimme, (bastaStartTimme + 4) %TIMMAR_I_DYGNET, medelPris);
    }

    private static void sorteraOchVisaPriser(){
        int[] sorteradePriser = Arrays.copyOf(elPriser, TIMMAR_I_DYGNET);
        boolean[] timmeAnvand = new boolean[TIMMAR_I_DYGNET];
        Arrays.sort(sorteradePriser);

        System.out.println("Priserna sorterade från högsta till lägsta: ");
        for(int i = TIMMAR_I_DYGNET - 1; i >= 0; i--){
            int timme = hittaTimmeForPrisUnik(sorteradePriser[i], timmeAnvand);
            System.out.printf(Locale.forLanguageTag("sv-SE"), "%02d - %02d : %d öre\n", timme, (timme+1)% TIMMAR_I_DYGNET, sorteradePriser[i]);
        }
    }

    private static int hittaTimmeForPrisUnik(int pris, boolean[] timmeAnvand){
        for(int i = 0; i<elPriser.length; i++){
            if(elPriser[i] == pris && !timmeAnvand[i]){
                timmeAnvand[i] = true;
                return i;
            }
        }
    return -1; }


    private static void visaMinMaxMedel(){
        int minPris = elPriser[0];
        int maxPris = elPriser[0];
        int total = 0;
        int minTimme = 0;
        int maxTimme = 0;

        for (int i = 0; i < TIMMAR_I_DYGNET; i++) {
            if (elPriser[i] < minPris) {
                minPris = elPriser[i];
                minTimme = i;
            }
            if (elPriser[i] > maxPris) {
                maxPris = elPriser[i];
                maxTimme = i;
            }
            total += elPriser[i];
        }
        double medelPrice = total / (double) TIMMAR_I_DYGNET;
        System.out.printf(Locale.forLanguageTag("sv-SE"),"Minimipris: %d öre under timmen %02d-%02d\n", minPris, minPris, (minTimme + 1) % TIMMAR_I_DYGNET);
        System.out.printf(Locale.forLanguageTag("sv-SE"),"Maximipris: %d öre under timmen %02d-%02d\n", maxPris, maxTimme, (maxTimme + 1) % TIMMAR_I_DYGNET);
        System.out.printf(Locale.forLanguageTag("sv-SE"),"Genomsnittligt pris: %.2f öre\n", medelPrice);
    }

    private static void angeElpriser() {
        System.out.println("1. Inmatning");
        System.out.println("Ange priser i öre per kWh för varje timme under dygnet:");
        for (int i = 0; i < TIMMAR_I_DYGNET; i++) {
            System.out.println("Elpris för timme " + i + "-" + (i + 1) + " i öre: ");
            elPriser[i] = scanner.nextInt();
        }
        scanner.nextLine();
    }
    private static void printMeny () {
        System.out.println("""
                Elpriser
                ===========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                Välj ett av alternativ:
                """);
    }
}


