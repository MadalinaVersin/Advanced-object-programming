package ro.unibuc.fmi.entitati;

public class ContBancar {
    protected String numarCont;
    protected Float suma;
    protected String valuta;
    protected String cnpDetinator;
    protected Integer tip;

    public ContBancar(String numarCont, String cnpDetinator, String valuta, Integer tip, Float suma) {
        this.suma = suma;
        this.numarCont = numarCont;
        this.valuta = valuta;
        this.cnpDetinator = cnpDetinator;
        this.tip = tip;
    }

    public ContBancar() {
    }


    public void setNumarCont(String numarCont) {
        this.numarCont = numarCont;
    }

    public void setSuma(Float suma) {
        this.suma = suma;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public void setCnpDetinator(String cnpDetinator) {
        this.cnpDetinator = cnpDetinator;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
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
