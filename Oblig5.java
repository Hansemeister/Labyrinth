import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Oblig5 {
    public static void main(String[] args) {
        String filnavn = null;
        boolean detaljert = false;

        if (args.length > 0) {
            filnavn = args[0];
            try {
                if (args[1].equals("detaljert")){
                    detaljert = true;
                    System.out.println("Du har valgt en detaljert utskrift:");
                }
            } catch(Exception e) {}
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Oblig5 <labyrintfil>");
            return;
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        if (detaljert){
            l.printLabyrint();
            System.out.println("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte)");
        }
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {

            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);


                Liste<String> utveier = l.finnUtveiFra(startKol, startRad);

                if(detaljert){
                    if (utveier.stoerrelse() != 0) {
                        for (String s : utveier) {
                            System.out.println("UTVEI FUNNET: " + s);
                        }
                    } else {
                        System.out.println("Ingen utveier.");
                    }
                    utveier = null;

                    System.out.println();

                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }
            if (detaljert){
                System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            }

            ord = inn.nextLine().split(" ");
        }
    }
}
