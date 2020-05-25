package ro.unibuc.fmi.entitati;

public class PlataFacturi extends Plata {
    private String serieFactura;
    private String furnizor;
    private String idClient;


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
    @Override
    public String toString() {
        return "PlataFactura{" +
                "cnpClient='" + cnpClient + '\'' +
                ", nrPlata='" + nrPlata + '\'' +
                ", suma='" + suma + '\'' +
                ", tip= '" + tip + '\'' +
                ", serieFactura='" + serieFactura + '\'' +
                ", furnizor='" + furnizor + '\'' +
                ", idClient = '" + idClient + '\'' +

                '}';
    }
}
