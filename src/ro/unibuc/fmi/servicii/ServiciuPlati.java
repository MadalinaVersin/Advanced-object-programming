package ro.unibuc.fmi.servicii;

import ro.unibuc.fmi.entitati.Plata;
import ro.unibuc.fmi.entitati.PlataFacturi;
import ro.unibuc.fmi.persistenta.PersistentaPlati;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ServiciuPlati {

    private static ServiciuPlati instance = null;
    private final PersistentaPlati persistentaPlati = PersistentaPlati.getInstance();
    private List<Plata> listaPlati = persistentaPlati.readPlati();

    private ServiciuPlati() {
    }

    public static ServiciuPlati getInstance() {
        if (instance == null) {
            instance = new ServiciuPlati();
        }
        return instance;
    }

    public void writePlati() {
        persistentaPlati.writePlati(listaPlati);
    }

    public void adaugaPlata() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Se citeste CNP-ul persoanei care efectueaza plata : ");
        String cnpClient = reader.readLine();

        System.out.println("Se citeste numarul platii: ");
        String nrPlata = reader.readLine();

        System.out.println("Se citeste suma platita: ");
        Float suma = Float.parseFloat(reader.readLine());

        System.out.println("Daca doriti sa efectuati o plata a unei facturi apasati tasta 2 altfel apasati 1");
        int tipPlata = Integer.parseInt(reader.readLine());

        if (tipPlata == 1) {
            Plata plataNoua = new Plata(cnpClient, nrPlata, suma, tipPlata);
            listaPlati.add(plataNoua);
        } else if (tipPlata == 2) {

            System.out.println("Se citeste seria facturii: ");
            String serieFactura = reader.readLine();

            System.out.println("Se citeste furnizorul: ");
            String furnizor = reader.readLine();

            System.out.println("Se citeste id-ul facturii: ");
            String idClient = reader.readLine();

            PlataFacturi plataNoua = new PlataFacturi(cnpClient, nrPlata, suma, tipPlata, serieFactura,
                    furnizor, idClient);
            listaPlati.add(plataNoua);
        }

    }

    public void afiseazaPati(String cnpClient) {
        for (Plata plata : listaPlati) {
            if (plata.getCnpClient().equals(cnpClient)) {
                if (plata.getTip() == 1) {
                    System.out.println(plata);
                }
                else{
                    PlataFacturi plataFctura = (PlataFacturi)plata;
                    System.out.println(plataFctura);

                }
            }
        }
    }

}
