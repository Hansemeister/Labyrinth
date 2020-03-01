import java.util.Scanner;
import java.io.*;

class Labyrint {
    private int kolonner;
    private int rader;
    private Lenkeliste<String> strengListe;
    private boolean detaljert = false;

    private Rute[][] ruteArray; //kolonner/rader


    private Labyrint(int kolonner, int rader, Rute[][] ruteArray){
        this.kolonner = kolonner;
        this.rader = rader;
        this.ruteArray = ruteArray;
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner scan = null;
        try{
            scan = new Scanner(fil);
        }catch(FileNotFoundException e){}

        //Her vet vi de to første tallene er kolonner x rader
        String innlest = scan.nextLine();
        String[] splittet = innlest.split(" ");
        int tempRader = Integer.parseInt(splittet[0]);
        int tempKolonner = Integer.parseInt(splittet[1]);
        int radTeller = 0;
        int kolonneTeller = 0;
        Rute tempRuteArray[][] = new Rute[tempKolonner][tempRader];
        //Rute tempRuteArray[][] = new Rute[tempRader][tempKolonner];
        Labyrint labyrinten = new Labyrint(tempKolonner, tempRader, tempRuteArray);//return labyrint

        //Går gjennom og ser om ruten er # eller .
        while (scan.hasNext()){
            innlest = scan.nextLine();
            splittet = innlest.split("");
            for (String i : splittet){

                //System.out.println("Kolonne: " + kolonneTeller + " Rad: " + radTeller);
                if (i.equals("#")){
                    //legg til SvartRute
                    SortRute ny = new SortRute(kolonneTeller, radTeller, tempRuteArray);
                    ny.finnLabyrint(labyrinten);
                    tempRuteArray[kolonneTeller][radTeller] = ny;
                    //tempRuteArray[radTeller][kolonneTeller] = ny;
                }

                if (i.equals (".")){
                    //Legg til HvitRute
                    HvitRute ny = new HvitRute(kolonneTeller, radTeller, tempRuteArray);
                    ny.finnLabyrint(labyrinten);
                    tempRuteArray[kolonneTeller][radTeller] = ny;

                }
                kolonneTeller++;
            } // end (for : )
            kolonneTeller = 0;
            radTeller++;
        } //end while


        //Sørge for at alle rutene kjenner til naboene sine:
        for (int i = 0; i < tempKolonner; i++) {
            for (int j = 0; j < tempRader; j++){
                //tempRuteArray[i][j].finnNaboer();
                tempRuteArray[i][j].finnNaboer();
            }
        }
        //labyrinten.printLabyrint();
        return labyrinten;
    } //end lesFraFil()

    public Lenkeliste<String> finnUtveiFra(int kol, int rad){
        if (kol >= kolonner || rad >= rader){
            System.out.println("Du har skrevet inn en posisjon som er utenfor rutenettet");
            System.out.println("husk at labyrinten du har valgt har: " + kolonner + " kolonner, og " + rad + " rader");
            System.out.println(" - Det vil si at du kan kun velge posisjoner mellom kolonner 0 - " + (kolonner-1) + ", og rader 0 - " + (rader-1));
            System.exit(0);
        }

        ruteArray[kol][rad].finnUtvei();
        strengListe = ruteArray[kol][rad].hentLenkeliste();
        ruteArray[kol][rad].slettLenkeliste(); //tømmer løsningslisten så brukeren kan skrive inn nye koordinater
        return strengListe;
    }

    public int hentKolonner(){ return kolonner;}
    public int hentRader() {return rader;}

    public Rute[][] hentArray(){
        return ruteArray;
    }

    public void leggTilLabyrint(int kolonner, int rader){//legger til ruter i en labyrint ( for testing)
        for (int i = 0; i < kolonner; i++) {
            for (int j = 0; j < rader; j++){
                Rute tempRute = new SortRute(i, j, ruteArray);
                ruteArray[i][j] = tempRute;
            }
        }
    }


    public void gjoerDetaljert(){ // gjør så vi skriver ut info underveis
        this.detaljert = true;
    }

    public void printLabyrint(){
        for (int rad = 0; rad < hentRader(); rad++) {
            for (int kolonne = 0; kolonne < hentKolonner(); kolonne++){
                System.out.print(ruteArray[kolonne][rad].tilTegn() + " ");

            }
            System.out.println(); //formatering, fungerer som linjeskift
        }
    }


}//end Labyrint
