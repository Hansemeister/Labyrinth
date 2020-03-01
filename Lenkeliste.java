import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T> {

    protected Node start = null;

    public int stoerrelse(){
        Node p = start;
        int teller = 0;

        while (p != null) {
            teller ++;
            p = p.neste;
        }
        return teller;
    }

    // legg til Node type x, i index pos
    public void leggTil (int pos, T x)throws UgyldigListeIndeks{
        Node p = start;
        Node forrige = null;

        if (pos != 0){
            if (pos > this.stoerrelse() || pos < 0){
                throw new UgyldigListeIndeks(pos);
            }
        }
        //Hvis listen er tom, bruk leggTil()
        if (start == null){
            leggTil(x);
            return;
        }
        //Hvis vi vil legge til index 0
        if (pos == 0){
            Node ny = new Node(x);
            ny.neste = p;
            start = ny;
            return;
        }
        for(int i = 0; i < pos; i++){
            forrige = p;
            p = p.neste;
        }
        Node ny = new Node(x);
        if (forrige != null){
            forrige.neste = ny;
        }

        ny.neste = p;

    }

    //sett inn Node som siste elementet i listen
    public void leggTil (T x)throws UgyldigListeIndeks {
        Node p = start;
        //Hvis listen er tom, sett noden til start
        if (start == null){
            start = new Node(x); return;
        }
        //løkke helt til vi finner siste Node
        //setter dens neste til den nye Node
        while (p.neste != null){
            p = p.neste;
        }p.neste = new Node(x);

    }

    //erstatter en Node
    public void sett (int pos, T x) throws UgyldigListeIndeks{
        Node p = start;
        Node erstatning = null;
        Node forrige = null;

        //unntak for null-pointer feil
        if (pos > this.stoerrelse()-1 || pos < 0 || p == null){
            throw new UgyldigListeIndeks(-1);
        }

        //Hvis vi vil erstatte første Node
        if (pos == 0){
            erstatning = new Node (x);
            erstatning.neste = p.neste;
            start = erstatning;
            return;
        }
        //Hoved-loop, finner indexen vi skal endre, for å sette pekeren fra
        // den før på den nye, og den nye sin peker på den neste.
        for (int i = 0; i < pos; i++){
            forrige = p;
            p = p.neste;
        }
        erstatning = new Node (x);
        erstatning.neste = p.neste;
        forrige.neste = erstatning;

    }

    //return dataen til en Node
    public T hent (int pos)throws UgyldigListeIndeks{
        Node p = start;
        if (pos > this.stoerrelse() || pos < 0 || p == null){
            throw new UgyldigListeIndeks(pos);
        }


        for (int i = 0; i < pos; i++){
            p = p.neste;
        }
        if (p == null){throw new UgyldigListeIndeks(pos); }
        return p.data;
    }

    //fjerner Node i angitt plass
    public T fjern(int pos)throws UgyldigListeIndeks{
        Node p = start;
        Node forrige = null;
        Node fjernet = null;

        //posisjon er ikke en gyldig index-feilmelding
        if (pos > this.stoerrelse()-1 || pos < 0 || p == null){
            throw new UgyldigListeIndeks(-1);
        }
        //unntak hvis vi vil fjerne første Node
        if (pos == 0){
            fjernet = start;
            start = start.neste;
            return fjernet.data;
        }

        for (int i = 0; i < pos; i++){
            forrige = p;
            p = p.neste;

            //Hvis det ikke er noen noder etter den vi har fjernet
            if (p == null){ return forrige.data;}
        }
        fjernet = p;
        forrige.neste = p.neste;
        return fjernet.data;
    }

    //fjerner Node i første plass
    public T fjern()throws UgyldigListeIndeks{
        Node p = start;
        if (p == null){
            throw new UgyldigListeIndeks(-1);
        }
        Node fjernet = p;
        start = p.neste;
        return fjernet.data;
    }
    //protected klasse Node, har samme datatype T som lenkelisten
    protected class Node {
        Node neste = null;
        T data;
        //konstruktør i nodeklassen
        Node(T x) { data = x; }
    }//end Node

    //metode for å opprette en LenkelisteIterator
    @Override
    public Iterator<T> iterator(){
        return new LenkelisteIterator<T>(this);
    }

    //privat iterator for å iterere gjennom lenkelisten
    public class LenkelisteIterator<T> implements Iterator<T>{
        Liste<T> liste;
        int index = 0;

        @SuppressWarnings("unchecked")
        public LenkelisteIterator(Liste x){
            liste = x;
        }

        @Override //true dersom neste element finnes
        public boolean hasNext(){
            return index < liste.stoerrelse();
        }
        @Override // flytter "posisjon" pekeren til neste, returnerer data til noden i posisjonen
        public T next(){
            return liste.hent(index++);
        }

    }
}
