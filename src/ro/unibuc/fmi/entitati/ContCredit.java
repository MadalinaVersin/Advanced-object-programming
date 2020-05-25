package ro.unibuc.fmi.entitati;

public class ContCredit extends ContBancar {

    private Float comision;

    public ContCredit(String numarCont, String cnpDetinator, String valuta, Integer tip, Float suma, Float comision) {
        super(numarCont, cnpDetinator, valuta, tip, suma);
        this.comision = comision;
    }

    public Float getComision() {
        return comision;
    }


    @Override
    public String toString() {
        return "ContCredit{" +
                "numarCont='" + numarCont + '\'' +
                ", cnpDetinator='" + cnpDetinator + '\'' +
                ", valuta='" + valuta + '\'' +
                ", tip= '" + tip + '\'' +
                ", suma = '" + suma + '\'' +
                '}';
    }
}
