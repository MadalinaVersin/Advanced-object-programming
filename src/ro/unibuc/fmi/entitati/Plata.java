package ro.unibuc.fmi.entitati;

public class Plata {
    protected String cnpClient;
    protected String nrPlata;
    protected Float suma;
    protected Integer tip;

    public Plata(String cnpClient, String nrPlata, Float suma, Integer tip) {
        this.cnpClient = cnpClient;
        this.nrPlata = nrPlata;
        this.suma = suma;
        this.tip = tip;

    }

    public String getCnpClient() {
        return cnpClient;
    }

    public String getNrPlata() {
        return nrPlata;
    }

    public Float getSuma() {
        return suma;
    }

    public Integer getTip() {
        return tip;
    }


    @Override
    public String toString() {
        return "Plata{" +
                "cnpClient='" + cnpClient + '\'' +
                ", nrPlata='" + nrPlata + '\'' +
                ", suma='" + suma + '\'' +
                ", tip = '" + tip + '\'' +
                '}';
    }


}
