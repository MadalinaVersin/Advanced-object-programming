package ro.unibuc.fmi;

public class ContBancar {
    protected String numarCont;
    protected Float suma;
    protected String valuta;
    protected String cnpDetinator;
    protected Integer tip;

    public ContBancar(Float suma, String numarCont, String valuta, String cnpDetinator, Integer tip) {
        this.suma = suma;
        this.numarCont = numarCont;
        this.valuta = valuta;
        this.cnpDetinator = cnpDetinator;
        this.tip = tip;
    }

    public ContBancar() {
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

    public String getCnpDetinator() {
        return cnpDetinator;
    }

    public Integer getTip() {
        return tip;
    }
}
