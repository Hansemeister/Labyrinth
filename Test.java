import java.util.Scanner;
import java.io.*;

public class Test {

    public static void main(String[] args) {
        /*Labyrint lab = new Labyrint(5,5);
        lab.leggTilLabyrint(5,5);
        lab.printLabyrint(5,5);
        //Rute[][] arrayet = lab.hentArray();
        lab.hentArray()[3][3].finnNaboer();
        lab.hentArray()[3][3].printNaboer();
*/
        File filen = new File("Textfil.txt");
        Labyrint labyrinten = null;
        try{
            labyrinten = Labyrint.lesFraFil(filen);
        } catch (FileNotFoundException e) {
            System.out.println("oida daarlig fil!");
            System.exit(1);
        }
        labyrinten.printLabyrint();
        System.out.println("\n\n");

        labyrinten.finnUtveiFra(1,3);

        // System.out.print(labyrinten.hentArray()[2][0].tilTegn());
        // System.out.print(labyrinten.hentArray()[2][1].tilTegn());
        // System.out.print(labyrinten.hentArray()[2][2].tilTegn());
        // System.out.print(labyrinten.hentArray()[2][3].tilTegn());
        // System.out.print(labyrinten.hentArray()[2][4].tilTegn());
        /*
        System.out.println("\nrader");
        for (int i = 0; i < 13; i++){
            System.out.print(labyrinten.hentArray()[0][i].tilTegn());
        } */
        /*
        System.out.print("tester: ");System.out.println(labyrinten.hentArray()[11][12].tilTegn());
        labyrinten.hentArray()[12][11].printNaboer();
        labyrinten.gaa(labyrinten.hentArray()[12][11]);



        System.out.print("tester: " );System.out.println(labyrinten.hentArray()[0][1].tilTegn());
        labyrinten.hentArray()[0][1].printNaboer();
        labyrinten.gaa(labyrinten.hentArray()[0][1]);
        */

    }
}
