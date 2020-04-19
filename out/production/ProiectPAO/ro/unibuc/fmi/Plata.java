package ro.unibuc.fmi;

public class Plata implements Tranzactie {
    protected String cnpClient;
    protected String nrPlata;
    protected Float suma;
    Integer tip;

    public Plata(String cnpClient, String nrPlata, Float suma, Integer tip) {
        this.cnpClient = cnpClient;
        this.nrPlata = nrPlata;
        this.suma = suma;
        this.tip = tip;

    }

    public Plata() {
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
    public void afiseazaTranzactia() {
        System.out.print("S-a efectuat plata cu numarul " + "de catre clientul " + cnpClient);

    }


}
