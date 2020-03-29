package ro.unibuc.fmi;

public class Angajat implements Comparable<Angajat> {
    String nume;
    String prenume;
    String codAngajat;
    String cnp;

    public Angajat(String nume, String prenume, String codAngajat, String cnp) {
        this.nume = nume;
        this.prenume = prenume;
        this.codAngajat = codAngajat;
        this.cnp = cnp;


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
    //folosita pentru a sorta angajatii in ordinea alfabetica;
    @Override
    public int compareTo(Angajat o) {
        return nume.compareTo(o.getNume());
    }
}
