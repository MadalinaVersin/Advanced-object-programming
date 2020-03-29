package ro.unibuc.fmi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Serviciu {

    public static void adaugaClient(Banca banca) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Introduceti numele clientului: ");
        String numeClient = reader.readLine();

        System.out.println("Introduceti adresa clientului: ");
        String adresaClient = reader.readLine();

        System.out.println("Introduceti cnp client: ");
        String cnpClinet = reader.readLine();

        Client clientNou = new Client(numeClient, adresaClient, cnpClinet);
        banca.addClientInList(clientNou);


    }

    public static void afiseazaClientiBanca(Banca banca) {

        banca.afiseazaClienti();
    }

    public static void introduCont(Banca banca,String cnpClient) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (Client client: banca.getClientList()){


            if (client.cnp.equals(cnpClient)){
                System.out.println("Se creaza un cont: ");

                System.out.println("Pentru a crea un cont de economii introduceti 1 , iar pentru un cont de credit introduceti 2");
                Integer tipCont = Integer.parseInt(reader.readLine());

                System.out.println("Se citeste numarul contului bancar: ");
                String nrContNou = reader.readLine();

                System.out.println("Se citeste valuta contului respectiv (LEI/EURO): ");
                String valutaContNou = reader.readLine();

                System.out.println("Se citeste suma care este introdusa direct in cont: ");
                Float sumaContNou = Float.parseFloat(reader.readLine());

                if ( tipCont == 1) {

                    System.out.println("Se citeste dobanda care va fi aplicata sumei: ");
                    Float dobandaContNou = Float.parseFloat(reader.readLine());

                    ContEconomii contEconomiiNou = new ContEconomii(sumaContNou, nrContNou, valutaContNou, dobandaContNou);
                    client.adaugaCont(contEconomiiNou);

                }
                if ( tipCont == 2) {

                    System.out.println("Se citeste comisionul care va fi aplicat sumei: ");
                    Float comisionContNou = Float.parseFloat(reader.readLine());

                    ContCredit contCreditNou = new ContCredit(sumaContNou, nrContNou, valutaContNou, comisionContNou);
                    client.adaugaCont(contCreditNou);

                }

            }
            else{
                System.out.println("Clientul nu se afla in baza de date !");
            }
        }
    }

    public static void adaugaAngajat(Banca banca) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Introduceti numele angajatului: ");
        String numeAngajat = reader.readLine();

        System.out.println("Introduceti prenumele angajatului: ");
        String prenumeAngajat = reader.readLine();

        System.out.println("Introduceti cnp angajat: ");
        String cnpAngajat = reader.readLine();

        System.out.println("Introduceti codul angajatului: ");
        String codAngajat = reader.readLine();

        Angajat angajatNou = new Angajat(numeAngajat, prenumeAngajat, codAngajat, cnpAngajat);
        banca.addAngajatInLista(angajatNou);


    }

    public static void afiseazaAngajatiBanca(Banca banca) {

        banca.afiseazaAngajati();

    }

    public static void afiseazaConturi(Banca banca, String cnpClient) {

        for (Client client: banca.getClientList()){
            if (client.cnp.equals(cnpClient)) {
                client.afiseazaConturiClient();

            }

        }

    }

    public static void adaugaPlata(Banca banca, String cnpClient) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (Client client: banca.getClientList()) {


            if (client.cnp.equals(cnpClient)) {


                String numeOperator;
                System.out.println("Se citeste numele operatorului care efectueaza plata:  ");
                numeOperator = reader.readLine();

                System.out.println("Se citestse data: ");
                String data = reader.readLine();

                String ora;
                System.out.println("Se citeste ora: ");
                ora = reader.readLine();

                String nrPlata;
                System.out.println("Se citeste numarul platii: ");
                nrPlata = reader.readLine();

                System.out.println("Daca doriti sa efectuati o plata a unei facturi apasati tasta 1 altfel apasati 2");

                Integer tipPlata;
                tipPlata = Integer.parseInt(reader.readLine());

                if (tipPlata == 1) {

                    System.out.println("Se citeste seria facturii: ");
                    String serieFactura = reader.readLine();

                    System.out.println("Se citeste furnizorul: ");
                    String furnizor = reader.readLine();

                    System.out.println("Se citeste id-ul facturii: ");
                    String idClient = reader.readLine();

                    PlataFacturi plataNoua = new PlataFacturi(numeOperator, data, ora, nrPlata, serieFactura, furnizor, idClient);
                    client.adaugaPlata(plataNoua);
                }
                else {
                    Plata plataNoua = new Plata(numeOperator, data, ora, nrPlata);
                    client.adaugaPlata(plataNoua);
                }

            }
        }



    }

    public static void afiseazaPati(Banca banca, String cnpClient) {

        for (Client client : banca.getClientList()) {
            if (client.getCnp().equals(cnpClient)) {
                client.afiseazaPlatiClient();
            }
        }
    }

    public static void afiseazaSuma(Banca banca, String cnpClient, String nrCont) {

        for (Client client : banca.getClientList()) {
            if (client.getCnp().equals(cnpClient)) {

                for(ContBancar cont : client.getConturiClient()) {

                    if (cont.getNumarCont().equals(nrCont)) {

                        if (cont instanceof ContEconomii) {
                            ContEconomii contEconomii = (ContEconomii) cont;
                            System.out.println("Suma din acest cont de economii este: " + contEconomii.getSumaTotala());
                        }
                        else if (cont instanceof ContCredit) {
                            ContCredit contCredit = (ContCredit) cont;
                            System.out.println("Suma din acest cont de credit este: " + contCredit.getSumaTotala());

                        }
                    }
                }
            }
        }

    }


    public static void adaugaDepunere(Banca banca, String cnpClient, String nrCont) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (Client client : banca.getClientList()) {
            if (client.getCnp().equals(cnpClient)) {

                for(ContBancar cont : client.getConturiClient()) {

                    if (cont.getNumarCont().equals(nrCont)) {

                        if (cont instanceof ContEconomii) {
                            ContEconomii contEconomii = (ContEconomii) cont;
                            System.out.println("Acesta este un cont de economii ! ");

                            System.out.println("Se citeste suma care va fi depusa: ");
                            Float sumaDepusa = Float.parseFloat(reader.readLine());

                            System.out.println("Suma din cont, fara a fi adaugata dobanda : " + contEconomii.depunere(sumaDepusa));
                        }
                        else if (cont instanceof ContCredit) {
                            ContCredit contCredit = (ContCredit) cont;
                            System.out.println("Acesta este un cont de credit !");

                            System.out.println("Se citeste suma care va fi depusa: ");
                            Float sumaDepusa = Float.parseFloat(reader.readLine());

                            System.out.println("Suma din cont, fara a fi retras comisionul  : " + contCredit.depunere(sumaDepusa));



                        }
                    }
                }
            }
        }

    }

    public static void retragereNumerar(Banca banca, String cnpClient, String nrCont) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (Client client : banca.getClientList()) {
            if (client.getCnp().equals(cnpClient)) {

                for(ContBancar cont : client.getConturiClient()) {

                    if (cont.getNumarCont().equals(nrCont)) {

                        if (cont instanceof ContEconomii) {
                            ContEconomii contEconomii = (ContEconomii) cont;
                            System.out.println("Acesta este un cont de economii ");

                            System.out.println("Se citeste suma care va fi retrasa");
                            Float sumaretrasa = Float.parseFloat(reader.readLine());

                            System.out.println("Suma din cont, fara a fi adaugata dobanda  " + contEconomii.extragere(sumaretrasa));
                        }
                        else if (cont instanceof ContCredit) {
                            ContCredit contCredit = (ContCredit) cont;
                            System.out.println("Acesta este un cont de credit !");

                            System.out.println("Se citeste suma care va fi depusa: ");
                            Float sumaDepusa = Float.parseFloat(reader.readLine());

                            System.out.println("Suma din cont, fara a fi retras comisionul: " + contCredit.extragere(sumaDepusa));



                        }
                    }
                }
            }
        }

    }



}

