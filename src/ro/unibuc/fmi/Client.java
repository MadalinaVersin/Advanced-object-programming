package ro.unibuc.fmi;

import java.util.ArrayList;
import java.util.List;

public class Client{
    String nume;
    String cnp;
    String  adresa;
    List<ContBancar> conturiClient = new ArrayList<>();
    List<Plata> platiClient = new ArrayList<>();


    public Client(String nume, String adresa, String cnp) {
        this.nume = nume;
        this.adresa = adresa;
        this.cnp = cnp;

    }

    public void adaugaCont(ContBancar cont) {
        conturiClient.add(cont);
        System.out.println("Contul a fost adaugat cu succes la persoana  " + nume);

    }

    public void adaugaPlata(Plata plata) {

        platiClient.add(plata);
        System.out.println("Plata a fost inregistrata !");
    }

    public String getAdresa() {
        return adresa;
    }

    public String getNume() {
        return nume;
    }

    public String getCnp() {
        return cnp;
    }


    public List<ContBancar> getConturiClient() {
        return conturiClient;
    }

    @Override
    public int hashCode() {
        return 1;
    }
    @Override
    public boolean equals(Object obj) {
        return cnp.equals(((Client)obj).getCnp());

    }
    public void afiseazaPlatiClient() {
        if (platiClient.size() != 0) {
            System.out.println("Clientul cu numele "+ nume + "a efectualt platile: ");
            for (Plata plata: platiClient) {
                System.out.println(plata.nrPlata);
            }
        }
    }

    public void afiseazaConturiClient() {
        if (conturiClient.size() != 0) {
            System.out.println("Clientul " + nume + "are contul cu numarul");
            for (ContBancar cont : conturiClient) {
                System.out.println(cont.numarCont);


            }
        }
        else {
            System.out.println("Clientul nu are deschis niciun cont! ");
        }
    }
}
