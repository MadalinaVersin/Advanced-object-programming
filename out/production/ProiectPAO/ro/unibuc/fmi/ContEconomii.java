package ro.unibuc.fmi;

public class ContEconomii extends ContBancar implements Operatiuni {
    Float dobanda;

    public ContEconomii(Float suma, String numarCont, String valuta, String cnpDetinator, Integer tip, Float dobanda) {
        super(suma, numarCont, valuta, cnpDetinator, tip);
        this.dobanda = dobanda;
    }

    public Float getDobanda() {
        return dobanda;
    }

    @Override
    public Float depunere(Float sumaDepusa) {
        suma = suma + sumaDepusa;

        return suma;
    }

    @Override
    public Float extragere(Float sumaExtrasa) {

        suma = suma - sumaExtrasa;
        return suma;
    }

    @Override
    public Float getSumaTotala() {
        Float sumaTotala = suma;
        sumaTotala = sumaTotala + dobanda;
        return sumaTotala;
    }
}
