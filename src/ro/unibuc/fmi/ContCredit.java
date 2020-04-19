package ro.unibuc.fmi;

public class ContCredit extends ContBancar implements Operatiuni {

    Float comision;

    public ContCredit(Float suma, String numarCont, String valuta, String cnpDetinator, Integer tip, Float comision) {
        super(suma, numarCont, valuta, cnpDetinator, tip);
        this.comision = comision;
    }

    public Float getComision() {
        return comision;
    }

    @Override
    public Float depunere(Float sumaDepusa) {
        suma = suma - sumaDepusa;
        return suma;
    }

    @Override
    public Float extragere(Float sumaExtrasa) {

        suma = suma + sumaExtrasa;
        return suma;
    }


    @Override
    public Float getSumaTotala() {
        Float sumaTotala = suma;
        sumaTotala = sumaTotala - comision;
        return sumaTotala;
    }
}
