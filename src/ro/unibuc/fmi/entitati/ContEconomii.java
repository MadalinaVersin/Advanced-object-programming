package ro.unibuc.fmi.entitati;

public class ContEconomii extends ContBancar{
    private Float dobanda;

    public ContEconomii(String numarCont, String cnpDetinator, String valuta, Integer tip, Float suma, Float dobanda) {
        super(numarCont, cnpDetinator, valuta, tip, suma);
        this.dobanda = dobanda;
    }

    public ContEconomii() {
        numarCont = null;
        cnpDetinator = null;
        valuta = null;
        tip = null;
        suma = null;
        dobanda = null;

    }

    public void setDobanda(Float dobanda) {
        this.dobanda = dobanda;
    }

    public Float getDobanda() {
        return dobanda;
    }

    @Override
    public String toString() {
        return "ContEconomii{" +
                "numarCont='" + numarCont + '\'' +
                ", cnpDetinator='" + cnpDetinator + '\'' +
                ", valuta ='" + valuta + '\'' +
                ", tip = '" + tip + '\'' +
                ", suma = '" + suma + '\'' +
                ", dobanda = '" + dobanda + '\'' +
                '}';
    }
}
