package ro.unibuc.fmi;

import java.util.*;

public class Banca {
    private String codBanca;
    Set<Client> clientiLista = new HashSet<>();
    List<Angajat> angajatiLista = new ArrayList<>();

    public Banca(String codBanca) {
        this.codBanca = codBanca;
    }

    public void addClientInList(Client client) {
        clientiLista.add(client);
        System.out.println("Clientul a fost adaugat!");
    }

    public void removeClient(Client client) {
        clientiLista.remove(client);
    }

    public void afiseazaClienti() {
        System.out.println("Se afiseaza clientii banicii");
        for (Client client : clientiLista) {
            System.out.println("Client :" + client.getNume() + " cu cnp-ul " + client.cnp);

        }
    }


    public void afiseazaAngajati() {
        System.out.println("Angajatii bancii sunt : ");
        Collections.sort(angajatiLista);
        for (Angajat angajat : angajatiLista) {
            System.out.println("Angajatul: " + angajat.nume + " " + angajat.prenume + " are codul " + angajat.codAngajat);
        }
    }

    public void addAngajatInLista(Angajat angajat) {

        angajatiLista.add(angajat);
        System.out.println("Angajatul a fost adaugat !");
    }

    public void setAngajatiLista(List<Angajat> angajatiLista) {
        this.angajatiLista = angajatiLista;
    }

    public void setClientiLista(List<Client> clienti) {
        for (Client client : clienti) {
            clientiLista.add(client);
        }

    }

    public Set<Client> getClientList() {
        return clientiLista;
    }

    public String getCodBanca() {
        return codBanca;
    }

    public List<Angajat> getAngajatList() {
        return angajatiLista;
    }


}
