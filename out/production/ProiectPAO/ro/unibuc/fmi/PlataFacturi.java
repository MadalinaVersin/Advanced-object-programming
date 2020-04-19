package ro.unibuc.fmi;

public class PlataFacturi extends Plata {
    String serieFactura;
    String furnizor;
    String idClient;


    public PlataFacturi(String cnpClient, String nrPlata, Float suma, Integer tip, String serieFactura, String furnizor, String idClient) {
        super(cnpClient, nrPlata, suma, tip);
        this.serieFactura = serieFactura;
        this.furnizor = furnizor;
        this.idClient = idClient;
    }

    public String getSerieFactura() {
        return serieFactura;
    }

    public String getFurnizor() {
        return furnizor;
    }

    public String getIdClient() {
        return idClient;
    }
}
