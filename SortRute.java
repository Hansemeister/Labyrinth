class SortRute extends Rute {


    public SortRute(int kolonne, int rad, Rute[][] iArray){
        super(kolonne, rad, iArray);
    }


    @Override //returnerer emneknagg
    public char tilTegn(){

        return '#';
    }

}
