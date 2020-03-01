 abstract class Rute  {

     private int kolonne; //BORTOVER
     private int rad;     //NEDOVER
     private Rute[][] iArray; //referanse til arrayen som ruten ligger isom brukes til å finne naboer til ruten
     private Labyrint labyrint = null;
     private boolean detaljert = false; //brukeren kan skrive: "Oblig5 detaljert" for å få true
     private static Lenkeliste<String> lenkeliste;

     private Rute nord; private Rute soer;
     private Rute oest; private Rute vest;


     public Rute(int kolonne, int rad, Rute[][] iArray){
         this.kolonne = kolonne;
         this.rad = rad;
         this.iArray = iArray;
         this.lenkeliste = new Lenkeliste<String>();
     }

     public void finnUtvei(){
        // Rute dummyNode = new HvitRute(0,0, null); //dummyNode som kun brukes for å sette igang løkken. Vi vet at 0,0 er et hjørne
        // this.gaa(dummyNode, "");
     }

     //strengen "vei" skal inneholde hele den foreløpige ruten
     public boolean gaa(Rute forrige, String vei){
         //hvis denne ruten er langs ytterkanten og er en Aapning
         if ( (kolonne == 0 || kolonne == labyrint.hentKolonner()-1 || rad == 0 || rad == labyrint.hentRader()-1 )
         && this.tilTegn() == '.') {
             forrige = this;
             if (vei == ""){
                 System.out.println("WOODWO");
                 return true;
             }
             lenkeliste.leggTil(vei + "-->" + ("(" + kolonne + " " + rad + ")") ); //legger til strengen i en lenkeliste
             return true;
         }

         if (nord != forrige){
            if (gaaNord(this, vei) == true){}
         }
         if (soer != forrige){
            if (gaaSoer(this, vei) == true){}

         }
         if (oest != forrige){
            if (gaaOest(this, vei) == true){}
         }
         if (vest != forrige){
            if (gaaVest(this, vei) == true){}
         }
         return false;
     }

     private boolean gaaNord(Rute forrige, String streng){
         if (nord != null && nord.tilTegn() != '#' && nord  != forrige){
             if (streng == ""){
                 streng += "(" + kolonne + ", " + rad + ")";
                 return nord.gaa(this, streng);
             }
             streng += "-->(" + kolonne + ", " + rad + ")";
             return nord.gaa(this, streng);
         }
         return false;
     }

     private boolean gaaSoer(Rute forrige, String streng){
         if (soer != null && soer.tilTegn() != '#' && soer  != forrige){
            if (streng == ""){
                streng += "(" + kolonne + ", " + rad + ")";
                return soer.gaa(this, streng);
            }
            streng += "-->(" + kolonne + ", " + rad + ")";
            return soer.gaa(this, streng);
         }
         return false;
     }

     private boolean gaaOest(Rute forrige, String streng){
          if (oest != null && oest.tilTegn() != '#' && oest  != forrige){
              if (streng == ""){
                  streng += "(" + kolonne + ", " + rad + ")";
                  return oest.gaa(this, streng);
              }
              streng += "-->(" + kolonne + ", " + rad + ")";
              return oest.gaa(this, streng);
          }
          return false;
      }

      private boolean gaaVest(Rute forrige, String streng){
           if (vest != null && vest.tilTegn() != '#' && vest  != forrige){
               if (streng == ""){
                   streng += "(" + kolonne + ", " + rad + ")";
                   return vest.gaa(this, streng);
               }
               streng += "-->(" + kolonne + ", " + rad + ")";
               return vest.gaa(this, streng);
           }
           return false;
       }


     //denne metoden skal finne alle naboene til denne rutene
     //og sette instansevariablene N/S/Ø/V til disse rutene
     public void finnNaboer(){
         //finn nabo nord
         try{
             if (iArray[kolonne][rad-1] != null){nord = iArray[kolonne][rad-1];}
         } catch (ArrayIndexOutOfBoundsException e){}
           catch (NullPointerException e){}
             //finn nabo soer
         try{
             if (iArray[kolonne][rad+1] != null){soer = iArray[kolonne][rad+1];}
         } catch (ArrayIndexOutOfBoundsException e){}
              catch (NullPointerException e){}
             //finn nabo oest
         try{
             if (iArray[kolonne+1][rad] != null){oest = iArray[kolonne+1][rad];}
         } catch (ArrayIndexOutOfBoundsException e){}
              catch (NullPointerException e){}
             //finn nabo vest
         try{
             if (iArray[kolonne-1][rad] != null){vest = iArray[kolonne-1][rad];}
         } catch (ArrayIndexOutOfBoundsException e){}
              catch (NullPointerException e){}
         /* Her må jeg skyte inn med en liten fun-fact. Når jeg skrev denne
            kodebiten begynte jeg å lure: "Hvorfor sier vi øst før vi sier vest?"
            I ren kode ville det sett penere ut ved å ha skrevet det likt som vi skriver nord/soer
            slik at hvis nord/soer er [rad-+1] vil da vest/oest være [kolonne-+1],
            men vi skriver først øst så vest, så det blir [kolonne+1] og så [kolonne-1]

            Det viser seg at det egentlig ikke er noen grunn for at vi skriver det
            i denne rekkefølgen, men i min jakt på svar fant jeg ut at
            på kinesisk betyr ordet deres for "ting", dongxi, bokstavelig talt "østvest",
            som betyr at "ting" er alle greiene mellom østen og vesten
            kilde: https://www.reddit.com/r/NoStupidQuestions/comments/1whpqj/why_do_we_say_northsoutheastwest_and_not/
         */
     }

     //denne metoden lagrer labyrinten ruten er i som "labyrint"
     public void finnLabyrint(Labyrint labyrint){
         this.labyrint = labyrint;
     }

     public abstract char tilTegn();

     public int hentKolonne(){
         return kolonne;
     }
     public int hentRad(){
         return rad;
     }

     public Lenkeliste<String> hentLenkeliste(){
         return lenkeliste;
     }
     public void slettLenkeliste(){
         lenkeliste = new Lenkeliste<String>(); //tømmer lenkelisten så brukeren kan sende inn nye koordinater
     }

     public void printNaboer(){
         System.out.println("Naboer til rute: " + this.toString());
         System.out.println("Nabo nord: " + nord);
         System.out.println("Nabo soer: " + soer);
         System.out.println("Nabo oest: " + oest);
         System.out.println("Nabo vest: " + vest);
     }
     @Override
     public String toString(){
         return ("["+kolonne+"]" + "[" + rad + "] ");
     }

}//end Rute
