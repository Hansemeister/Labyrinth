import java.util.Iterator;

public interface Liste<T> extends Iterable<T> { //custom Liste som tar imot T som type
    public int stoerrelse();

    public void leggTil(int pos, T x); // Posisjon/type
    public void leggTil(T x); //legg til i slutten FIFO
    public void sett(int pos, T x); // legg til i spesifikk index
    public T hent(int pos); //hent noe fra en spesifikk index
    public T fjern(int pos); //fjern noe fra en spesifikk index
    public T fjern(); //fjern første elementet i listen

    //Iterator<T> iterator(); Denne overskrives i subklassen Lenkeliste

}
