package ro.unibuc.fmi;
import ro.unibuc.fmi.entitati.Angajat;
import ro.unibuc.fmi.entitati.Banca;
import ro.unibuc.fmi.entitati.Client;
import ro.unibuc.fmi.entitati.ContEconomii;
import ro.unibuc.fmi.servicii.ServiciuAngajati;
import ro.unibuc.fmi.servicii.ServiciuBanca;
import ro.unibuc.fmi.servicii.ServiciuConturiEconomii;
import ro.unibuc.fmi.servicii.ServiciuConturiCredit;
import ro.unibuc.fmi.servicii.ServiciuClienti;
import ro.unibuc.fmi.servicii.ServiciuPlati;
import ro.unibuc.fmi.audit.Audit;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final ServiciuAngajati serviciuAngajati = ServiciuAngajati.getInstance();
    private static final ServiciuClienti serviciuClienti = ServiciuClienti.getInstance();
    private static final ServiciuBanca serviciuBanci = ServiciuBanca.getInstance();
    private static final ServiciuConturiEconomii serviciuContEconomii = ServiciuConturiEconomii.getInstance();
    private static final Audit serviciuAudit = Audit.getInstance();
    private static final ServiciuPlati serviciuPlati = ServiciuPlati.getInstance();
    private static final ServiciuConturiCredit serviciuConturiCredit = ServiciuConturiCredit.getInstance();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Apasati 1 pentru a adauga un angajat in baza de date: ");
        System.out.println("Apasati 2 pentru a gasi un angajat dupa CNP: ");
        System.out.println("Apasati 3 pentru a face update unui angajat (se face update doar la nume): ");
        System.out.println("Apasati 4 pentru a sterge un angajat: ");
        System.out.println("Apasati 5 pentru a adauga un client in baza de date: ");
        System.out.println("Apasati 6 pentru a gasi un client dupa cnp: ");
        System.out.println("Apasati 7 pentru a face update la adresa unui client : ");
        System.out.println("Apasati 8 pentru a sterge un client : ");
        System.out.println("Apasati 9 pentru a adauga o banca in baza de date: ");
        System.out.println("Apasati 10 pentru a gasi o banca dupa codul acesteia: ");
        System.out.println("Apasati 11 pentru a face update la numele unei banci: ");
        System.out.println("Apasati 12 pentru a sterge o banca din baza de date : ");
        System.out.println("Apasati 13 pentru a adauga un cont de economii  in baza de date: ");
        System.out.println("Apasati 14 pentru a gasi un cont de economi dupa numarul acestuia: ");
        System.out.println("Apasati 15 pentru a face update la suma unui cont de economii: ");
        System.out.println("Apasati 16 pentru a sterge un cont de economii din baza de date : ");
        System.out.println("Apasati 17 pentru a afisa toti angajatii unei banci : ");
        System.out.println("Apasati 18 pentru a afisa toti clientii unei banci : ");
        System.out.println("Apasati 19 pentru a afisa toate conturile de economii ale unei banci : ");
        System.out.println("Apasati 20 pentru a aefectua o plata :");
        System.out.println("Apasati 21 pentru a afisa platile efectuate de un client: ");
        System.out.println("Apasati 22 pentru a adauga un cont de credit: ");
        System.out.println("Apasati 23 pentru a afisa toate conturile de credit ale unui client: ");


        int optiune;
        optiune = 1;

        while (optiune != 0) {
            System.out.println("Se citeste optiunea dorita: ");
            optiune = Integer.parseInt(reader.readLine());

            switch (optiune) {
                case 1: {

                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va adauga un nou angajat!");
                    System.out.println("Introduceti CNP-ul angajatului: ");
                    String cnp = reader.readLine();
                    System.out.println("Introduceti codul bancii la care lucreaza angajatul: ");
                    String codBanca = reader.readLine();
                    System.out.println("Introduceti numele angajatului: ");
                    String nume = reader.readLine();
                    System.out.println("Introduceti prenumele angajatului: ");
                    String prenume = reader.readLine();
                    System.out.println("Introduceti codul angajatului:");
                    String codAngajat = reader.readLine();

                    Angajat angajatToSave = serviciuAngajati.saveAngajat(cnp, codBanca, nume,
                            prenume, codAngajat);

                    serviciuAudit.audit("Adauga angajat.", new Date());
                    System.out.println(angajatToSave);
                    break;
                }
                case 2: {

                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va cauta un angajat in baza de date: ");
                    System.out.println("Introduceti CNP-ul angajatului: ");
                    String cnp = reader.readLine();
                    Angajat angajatToFind = serviciuAngajati.findAngajat(cnp);
                    serviciuAudit.audit("Cauta angajat.", new Date());
                    System.out.println(angajatToFind);
                    break;

                }


                case 3: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va face update la numele angajatului!");
                    System.out.println("Introduceti CNP-ul angajatului: ");
                    String cnp = reader.readLine();
                    System.out.println("Introduceti noul nume al  angajatului: ");
                    String nume = reader.readLine();

                    Angajat angajatToUpdate = new Angajat(cnp, null, nume,
                            null, null);
                    angajatToUpdate = serviciuAngajati.updateAngajat(angajatToUpdate);
                    serviciuAudit.audit("Update angajat.", new Date());
                    System.out.println(angajatToUpdate);
                    break;

                }

                case 4: {

                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va sterge un angajat: ");
                    System.out.println("Introduceti CNP-ul angajatului: ");
                    String cnp = reader.readLine();

                    Angajat angajatToDelete = new Angajat(cnp, null, null, null, null);
                    boolean result = serviciuAngajati.deleteAngajat(angajatToDelete);
                    serviciuAudit.audit("Sterge angajat.", new Date());
                    System.out.println(result);
                    break;


                }

                case 5: {

                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va introduce un nou client in baza de date: ");
                    System.out.println("Introduceti CNP-ul clientului: ");
                    String cnp = reader.readLine();
                    System.out.println("Introduceti codul bancii la care este clientul: ");
                    String codBanca = reader.readLine();
                    System.out.println("Introduceti numele clientului: ");
                    String nume = reader.readLine();
                    System.out.println("Introduceti prenumele clientului: ");
                    String prenume = reader.readLine();
                    System.out.println("Introduceti adresa clientului:");
                    String adresa = reader.readLine();


                    Client clientToSave = serviciuClienti.saveClient(cnp, codBanca, nume,
                            prenume, adresa);
                    serviciuAudit.audit("Adauga client.", new Date());
                    System.out.println(clientToSave);
                    break;

                }


                case 6: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va cauta un client in baza de date: ");
                    System.out.println("Introduceti CNP-ul clientului: ");
                    String cnp = reader.readLine();
                    Client clientToFind = serviciuClienti.findClient(cnp);
                    serviciuAudit.audit("Cauta client.", new Date());
                    System.out.println(clientToFind);
                    break;

                }

                case 7: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va face update la adresa clientului!");
                    System.out.println("Introduceti CNP-ul clientului: ");
                    String cnp = reader.readLine();
                    System.out.println("Introduceti noua adresa a angajatului: ");
                    String adresa = reader.readLine();

                    Client clientToUpdate = new Client(cnp, null, null,
                            null, adresa);
                    clientToUpdate = serviciuClienti.updateClient(clientToUpdate);
                    serviciuAudit.audit("Update client.", new Date());
                    System.out.println(clientToUpdate);
                    break;

                }

                case 8: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va sterge un client: ");
                    System.out.println("Introduceti CNP-ul clientului: ");
                    String cnp = reader.readLine();

                    Client clientToDelete = new Client(cnp, null, null, null, null);
                    boolean result = serviciuClienti.deleteClient(clientToDelete);
                    System.out.println(result);
                    serviciuAudit.audit("Sterge client.", new Date());
                    break;

                }

                case 9: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va adauga o noua banca!");
                    System.out.println("Introduceti codul bancii: ");
                    String codBanca = reader.readLine();
                    System.out.println("Introduceti denumirea bancii: ");
                    String denumireBanca = reader.readLine();


                    Banca bancaToSave = serviciuBanci.saveBanca(codBanca, denumireBanca);
                    serviciuAudit.audit("Adauga banca.", new Date());
                    System.out.println(bancaToSave);
                    break;

                }


                case 10: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va cauta o banca  in baza de date: ");
                    System.out.println("Introduceti codul bancii: ");
                    String codBanca = reader.readLine();
                    Banca bancaToFind = serviciuBanci.findBanca(codBanca);
                    serviciuAudit.audit("Cauta banca.", new Date());
                    System.out.println(bancaToFind);
                    break;


                }

                case 11: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va face update la denumirea bancii!");
                    System.out.println("Introduceti codul bancii: ");
                    String cod = reader.readLine();
                    System.out.println("Introduceti noua denumire al  bancii: ");
                    String denumire = reader.readLine();

                    Banca bancaToUpdate = new Banca(cod, denumire);
                    bancaToUpdate = serviciuBanci.updateBanca(bancaToUpdate);
                    serviciuAudit.audit("Update banca.", new Date());
                    System.out.println(bancaToUpdate);
                    break;
                }
                case 12: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va sterge o banca: ");
                    System.out.println("Introduceti codul bancii: ");
                    String cod = reader.readLine();

                    Banca bancaToDelete = new Banca(cod, null);
                    boolean result = serviciuBanci.deleteBanca(bancaToDelete);
                    serviciuAudit.audit("Sterge banca.", new Date());
                    System.out.println(result);
                    break;
                }

                case 13: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va adauga un nou cont de economii!");
                    System.out.println("Introduceti numarul contului de economii: ");
                    String numarCont = reader.readLine();
                    System.out.println("Introduceti CNP-ul detinatorului: ");
                    String cnpDetinator = reader.readLine();
                    System.out.println("Introduceti valuta contului de economii: ");
                    String valuta = reader.readLine();
                    System.out.println("Introduceti tipul contului : ");
                    Integer tip = Integer.parseInt(reader.readLine());
                    System.out.println("Introduceti suma care se afla in cont: ");
                    Float suma = Float.parseFloat(reader.readLine());
                    System.out.println("Introduceti dobanda care se va aplica la suma :");
                    Float dobanda = Float.parseFloat(reader.readLine());

                    ContEconomii contEconomiiToSave = serviciuContEconomii.saveConturiEconomii(numarCont, cnpDetinator,
                            valuta, tip, suma, dobanda);
                    serviciuAudit.audit("Adauga un cont de economii.", new Date());
                    System.out.println(contEconomiiToSave);
                    break;

                }

                case 14: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va cauta un cont de economii  in baza de date: ");
                    System.out.println("Introduceti numarul contului de economii: ");
                    String numarCont = reader.readLine();
                    ContEconomii contEconomiiToFind = serviciuContEconomii.findContEconomii(numarCont);
                    serviciuAudit.audit("Cauta cont de economii.", new Date());
                    System.out.println(contEconomiiToFind);
                    break;


                }

                case 15: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va face update la suma din contul unei banci!");
                    System.out.println("Introduceti numarul contului: ");
                    String numarCont = reader.readLine();
                    System.out.println("Introduceti noua suma a contului: ");
                    Float suma = Float.parseFloat(reader.readLine());

                    ContEconomii contEconomiiToUpdate = new ContEconomii(numarCont, null, null,
                            null, suma, null);
                    contEconomiiToUpdate = serviciuContEconomii.updateContEconomii(contEconomiiToUpdate);
                    serviciuAudit.audit("Update cont economii.", new Date());
                    System.out.println(contEconomiiToUpdate);
                    break;
                }
                case 16: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va sterge un cont de economii: ");
                    System.out.println("Introduceti numarul contului: ");
                    String numarCont = reader.readLine();

                    ContEconomii contEconomiiToDelete = new ContEconomii(numarCont, null, null, null, null, null);
                    boolean result = serviciuContEconomii.deleteContEconomii(contEconomiiToDelete);
                    serviciuAudit.audit("Sterge cont economii.", new Date());
                    System.out.println(result);
                    break;

                }
                case 17: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se vor afisa toti angajatii unei banci!");
                    System.out.println("Introduceti codul bancii: ");
                    String codBanca = reader.readLine();
                    List<Angajat> allAngajati = serviciuAngajati.findAllAngajati(codBanca);
                    for (Angajat angajat : allAngajati) {
                        System.out.println(angajat);
                    }
                    serviciuAudit.audit("Afiseaza toti angajatii unei banci.", new Date());
                    break;
                }
                case 18: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se vor afisa toti clientii unei banci!");
                    System.out.println("Introduceti codul bancii: ");
                    String codBanca = reader.readLine();
                    List<Client> allClienti = serviciuClienti.findAllClienti(codBanca);
                    for (Client client : allClienti) {
                        System.out.println(client);
                    }
                    serviciuAudit.audit("Afiseaza toti clinetii unei banci.", new Date());
                    break;
                }
                case 19: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se vor afisa toate conturile de economii ale unui client!");
                    System.out.println("Introduceti CNP-Ul detinatorului : ");
                    String cnpDetinator = reader.readLine();
                    List<ContEconomii> allConturiEconomii = serviciuContEconomii.findAllConturiEconomii(cnpDetinator);
                    for (ContEconomii contEconomii : allConturiEconomii) {
                        System.out.println(contEconomii);
                    }
                    serviciuAudit.audit("Afiseaza toate conturile de economii ale unui client.", new Date());
                    break;
                }
                case 20: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va adauga o plata: ");
                    serviciuPlati.adaugaPlata();
                    serviciuAudit.audit("Adauga plata.", new Date());
                    break;
                }
                case 21: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se vor afisa platile efectuate de un client: ");
                    System.out.println("Se citeste CNP-ul clientului: ");
                    String cnp = reader.readLine();
                    serviciuPlati.afiseazaPati(cnp);
                    serviciuAudit.audit("Afisare plati.", new Date());
                    break;
                }
                case 22: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se va adauga un cont de credit: ");
                    serviciuConturiCredit.adaugaContCredit();
                    serviciuAudit.audit("Adauga cont credit", new Date());
                    break;
                }
                case 23: {
                    System.out.println("A fost aleasa optiunea: " + optiune);
                    System.out.println("Se vor afisa conturile de credit ale unui client: ");
                    System.out.println("Se citeste CNP-ul clientului: ");
                    String cnp = reader.readLine();
                    serviciuConturiCredit.afiseazaConturiCredit(cnp);
                    serviciuAudit.audit("Afiseaza conturi credit.", new Date());
                    break;
                }
                case 0: {
                    serviciuPlati.writePlati();
                    serviciuConturiCredit.writeConturiCredit();
                    break;

                }

            }


        }
    }
}
