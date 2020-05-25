package ro.unibuc.fmi.entitati;

public class Client {
    private String nume;
    private String prenume;
    private String adresa;
    private String cnp;
    private String condBanca;

    public Client(String cnp, String codBanca, String nume, String prenume, String adresa) {
        this.condBanca = codBanca;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.cnp = cnp;
    }

    public Client() {
        this.nume = null;
        this.adresa = null;
        this.cnp = null;

    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setCondBanca(String condBanca) {
        this.condBanca = condBanca;
    }

    public String getCondBanca() {
        return condBanca;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCnp() {
        return cnp;
    }



    @Override
    public String toString() {
        return "Client{" +
                "Cnp='" + cnp + '\'' +
                ", codBanca='" + condBanca + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa ='" + adresa + '\'' +
                '}';
    }

}
