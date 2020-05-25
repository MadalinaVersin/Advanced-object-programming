package ro.unibuc.fmi.entitati;

public class Banca {
    private String codBanca;
    private String denumireBanca;

    public Banca() {
        this.codBanca = null;
        this.denumireBanca = null;

    }


    public Banca(String codBanca, String denumireBanca) {
        this.codBanca = codBanca;
        this.denumireBanca = denumireBanca;

    }

    public String getCodBanca() {
        return codBanca;
    }

    public String getDenumireBanca() {
        return denumireBanca;
    }

    public void setCodBanca(String codBanca) {
        this.codBanca = codBanca;
    }

    public void setDenumireBanca(String denumireBanca) {
        this.denumireBanca = denumireBanca;
    }

    @Override
    public String toString() {
        return "Banca{" +
                " codBanca='" + codBanca + '\'' +
                ", denumire='" + denumireBanca + '\'' +
                '}';
    }


}
