class HvitRute extends Rute{

    public HvitRute(int kolonne, int rad, Rute[][] iArray){
        super(kolonne, rad, iArray);
    }

    public void finnUtvei(){
        Rute dummyNode = new HvitRute(0,0, null); //dummyNode som kun brukes for å sette igang løkken. Vi vet at 0,0 er et hjørne
        this.gaa(dummyNode, "");
    }

    @Override //returnerer punktum
    public char tilTegn(){

        return '.';
    }

}
