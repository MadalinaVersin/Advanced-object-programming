package ro.unibuc.fmi;

public class ContEconomii extends ContBancar implements Operatiuni{
    Float dobanda;

    public ContEconomii(Float suma, String numarCont, String valuta, Float dobanda) {
        super(suma, numarCont, valuta);
        this.dobanda = dobanda;
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
