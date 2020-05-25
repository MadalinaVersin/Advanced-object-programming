package ro.unibuc.fmi.entitati;

public class Angajat implements Comparable<Angajat> {
    private String nume;
    private String prenume;
    private String codAngajat;
    private String cnp;
    private String codBanca;

    public Angajat(String cnp, String codBanca, String nume, String prenume, String codAngajat) {
        this.codBanca = codBanca;
        this.nume = nume;
        this.prenume = prenume;
        this.codAngajat = codAngajat;
        this.cnp = cnp;


    }

    public Angajat() {
        this.nume = null;
        this.prenume = null;
        this.codAngajat = null;
        this.cnp = null;
        this.codBanca = null;

    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setCodAngajat(String codAngajat) {
        this.codAngajat = codAngajat;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setCodBanca(String codBanca) {
        this.codBanca = codBanca;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCodAngajat() {
        return codAngajat;
    }

    public String getCnp() {
        return cnp;
    }

    public String getCodBanca() {
        return codBanca;
    }

    //folosita pentru a sorta angajatii in ordinea alfabetica;
    @Override
    public int compareTo(Angajat o) {
        return nume.compareTo(o.getNume());
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "cnp='" + cnp + '\'' +
                ", codBanca='" + codBanca + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume = '" + prenume + '\'' +
                ", codAngajat = '" + codAngajat + '\'' +
                '}';
    }
}
