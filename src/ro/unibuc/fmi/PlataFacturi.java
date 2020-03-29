package ro.unibuc.fmi;

public class PlataFacturi extends Plata {
    String serieFactura;
    String furnizor;
    String idClient;


    public PlataFacturi(String angajat, String data, String ora, String nrPlata, String serieFactura, String furnizor, String idClient) {
        super(angajat, data, ora, nrPlata);
        this.serieFactura = serieFactura;
        this.furnizor = furnizor;
        this.idClient = idClient;
    }
}
