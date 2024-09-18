package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class App {

    private static final int TIMMAR_I_DYGNET = 24;
    private static final int[] elPriser = new int[TIMMAR_I_DYGNET];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello There!");
        String val;

        while (true) {
            printMeny();
            val = scanner.nextLine();

            switch (val) {
                case "1" -> angeElpriser();
                case "2" -> visaMinMaxMedel();
                case "3" -> sorteraOchVisaPriser();
                case "4" -> hittaBastaLaddningsTid();
                case "e", "E" -> System.out.println("Program avslutade");
                default -> System.out.println("Ogiltigt val. Försök igen!");
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
        System.out.printf("Bästa tiden att börja ladda: %02d-%02d, genomsnittligt pris: %.2f öre\n",
                bastaStartTimme, (bastaStartTimme + 4) %TIMMAR_I_DYGNET, medelPris);
    }

    private static void sorteraOchVisaPriser(){
        int[] sorteradePriser = Arrays.copyOf(elPriser, TIMMAR_I_DYGNET);
        Arrays.sort(sorteradePriser);

        System.out.println("Priserna sorterade från högsta till lägsta: ");
        for(int i = TIMMAR_I_DYGNET - 1; i >= 0; i--){
            int timme = hittaTimmeForPris(sorteradePriser[i]);
            System.out.printf("%02d - %02d : %d öre\n", timme, (timme+1)% TIMMAR_I_DYGNET, sorteradePriser[i]);
        }
    }

    private static int hittaTimmeForPris(int pris) {
        for (int i = 0; i < TIMMAR_I_DYGNET; i++) {
            if(elPriser[i] == pris){
                return i;
            }
        }
        return -1;
    }

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
        System.out.printf("Minimipris: %d öre under timmen %02d-%02d\n", minPris, minPris, (minPris + 1) % TIMMAR_I_DYGNET);
        System.out.printf("Maximipris: %d öre under timmen %02d-%02d\n", maxPris, maxTimme, (maxTimme + 1) % TIMMAR_I_DYGNET);
        System.out.printf("Genomsnittligt pris: %.2f öre\n", medelPrice);
    }

    private static void angeElpriser() {
        System.out.println("1. Inmatning");
        System.out.println("Ange priser i öre per kWh för varje timme under dygnet:");
        for(int i = 0; i < TIMMAR_I_DYGNET; i++) {
            System.out.println("Elpris för timme " + i + "-" + (i + 1) + " i öre: ");
            elPriser[i] = scanner.nextInt();
        }
    }
    private static void printMeny () {
        System.out.println("Elpriser\n" +
                "===========\n" +
                "1. Inmatning\n" +
                "2. Min, Max och Medel\n" +
                "3. Sortera\n" +
                "4. Bästa Laddningstid (4h)\n" +
                "e. Avsluta\n" +
                "Välj ett av alternativ:");
    }
}


