package ro.unibuc.fmi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduceti codul bancii: ");
        String codBanca = reader.readLine();
        Persistenta persistenta = Persistenta.getInstance();
        Banca banca = new Banca(codBanca);
        Angajat angajat = new Angajat();
        Client client = new Client();
        ContBancar cont = new ContBancar();
        Plata plata = new Plata();


        BufferedReader anjazati = new BufferedReader(new FileReader("angajati.csv"));
        if (anjazati.readLine() != null) {
            banca.setAngajatiLista(Persistenta.read(angajat));
        }

        BufferedReader clienti = new BufferedReader(new FileReader("clienti.csv"));
        if (clienti.readLine() != null) {
            banca.setClientiLista(Persistenta.read(client));
        }


        Serviciu serviciu = new Serviciu();
        System.out.println("Apasati 1 pentru a adauga un client in lista bancii: ");
        System.out.println("Apasati 2 pentru a afisa toti clientii bancii: ");
        System.out.println("Apasati 3 pentru a adauga un angajat nou: ");
        System.out.println("Apasati 4 pentru a afisa toti angajztii bancii: ");
        System.out.println("Apasati 5 prntru a introduce un cont la o persoana (! deja existenta): ");
        System.out.println("Apasati 6 pentru a afisa conturile unui client: ");
        System.out.println("Apasati 7 pentru a adauga o plata unui client: ");
        System.out.println("Apasati 8 pentru a afisa platile facute de un client: ");
        System.out.println("Apasati 9 pentru a afisa suma dintr-un cont: ");
        System.out.println("Apasati 10 pentru a depune numerara intr-un cont: ");
        System.out.println("Apasati 11 pentru a extrage numerar dintr-un cont: ");
        System.out.println("Apasati 0 pentru a inchide! ");

        Integer optiune;
        optiune = 1;

        while (optiune != 0) {
            System.out.println("Se citeste optiunea dorita: ");
            optiune = Integer.parseInt(reader.readLine());

            if (optiune == 1) {

                System.out.println("A fost aleasa  optiunea: " + optiune);
                Serviciu.adaugaClient(banca);
            }

            if (optiune == 2) {

                System.out.println("A fost aleasa optiunea: " + optiune);
                Serviciu.afiseazaClientiBanca(banca);

            }


            if (optiune == 3) {

                System.out.println("A fost aleasa optiunea: " + optiune);
                Serviciu.adaugaAngajat(banca);

            }

            if (optiune == 4) {

                System.out.println("A fost aleasa optiunea: " + optiune);
                Serviciu.afiseazaAngajatiBanca(banca);


            }

            if (optiune == 5) {

                System.out.println("A fost aleasa optiunea: " + optiune);
                System.out.println("Se citeste cnp -ul clientului pentru care se creaza cont: ");
                String cnpClient = reader.readLine();
                serviciu.introduCont(banca, cnpClient);

            }


            if (optiune == 6) {
                System.out.println("A fost aleasa optiunea: " + optiune);
                System.out.println("Se citeste cnp-ul persoanei pentru care dorim afisarea conturilor: ");
                String cnpClient = reader.readLine();
                serviciu.afiseazaConturi(banca, cnpClient);

            }

            if (optiune == 7) {
                System.out.println("A fost aleasa optiunea: " + optiune);
                System.out.println("Se efectueaza o plata :");
                System.out.println("Se citeste cnp-ul clientului ");
                String cnpClient;
                cnpClient = reader.readLine();
                serviciu.adaugaPlata(banca, cnpClient);


            }

            if (optiune == 8) {
                System.out.println("A fost aleasa optiunea: " + optiune);

                System.out.println("Se citeste cnp ul persoanei careia dorim sa ii afisam platile:");
                String cnpClient = reader.readLine();

                serviciu.afiseazaPati(banca, cnpClient);
            }

            if (optiune == 9) {
                System.out.println("A fost aleasa optiunea: " + optiune);

                System.out.println("Se citeste cnp ul persoanei careia dorim sa ii afisam platile:");
                String cnpClient = reader.readLine();

                System.out.println("Se citeste numarul contului a carei suma dorim sa o aflam: ");
                String nrCont = reader.readLine();

                serviciu.afiseazaSuma(banca, cnpClient, nrCont);
            }

            if (optiune == 10) {
                System.out.println("A fost aleasa optiunea: " + optiune);

                System.out.println("Se citeste cnp ul persoanei careia dorim sa ii depunem numerar intr-un cont: ");
                String cnpClient = reader.readLine();

                System.out.println("Se citeste numarul contului in care dorim sa facem depunerea: ");
                String nrCont = reader.readLine();

                Serviciu.adaugaDepunere(banca, cnpClient, nrCont);


            }

            if (optiune == 11) {
                System.out.println("A fost aleasa optiunea: " + optiune);

                System.out.println("Se citeste cnp ul persoanei careia dorim sa ii retragem numerar dintr-un cont: ");
                String cnpClient = reader.readLine();

                System.out.println("Se citeste numarul contului in care dorim sa facem retragerea: ");
                String nrCont = reader.readLine();

                Serviciu.retragereNumerar(banca, cnpClient, nrCont);


            }

            if (optiune == 0) {
                Persistenta.writeToFile(angajat, banca);
                Persistenta.writeToFile(client, banca);
                Persistenta.writeToFile(cont, banca);
                Persistenta.writeToFile(plata, banca);

            }

        }


    }
}
