package ro.unibuc.fmi;

public class Plata implements  Tranzactie{
    protected String angajat;
    protected String data;
    protected String ora;
    protected String nrPlata;

    public Plata(String angajat, String data, String ora , String nrPlata) {
        this.angajat = angajat;
        this.data = data;
        this.ora = ora;
        this.nrPlata = nrPlata;

    }

    @Override
    public void afiseazaTranzactia() {
        System.out.print("S-a efectuatplata  in data de "+data+"ora"+ora+" "+"de clientul");

    }


}
