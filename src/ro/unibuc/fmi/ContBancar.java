package ro.unibuc.fmi;

public class ContBancar {
    protected String numarCont;
    protected Float suma;
    protected String valuta;

    public ContBancar(Float suma, String numarCont, String valuta) {
        this.suma = suma;
        this.numarCont = numarCont;
        this.valuta = valuta;
    }

    public String getNumarCont() {
        return numarCont;
    }

    public Float getSuma() {
        return suma;
    }

    public String getValuta() {
        return valuta;
    }

}
