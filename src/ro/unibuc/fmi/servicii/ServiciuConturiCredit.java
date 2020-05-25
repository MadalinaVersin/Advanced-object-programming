package ro.unibuc.fmi.servicii;

import ro.unibuc.fmi.entitati.ContCredit;
import ro.unibuc.fmi.persistenta.PersistentaConturiCredit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ServiciuConturiCredit {
    private static ServiciuConturiCredit instance = null;
    private final PersistentaConturiCredit persistentaConturiCredit = PersistentaConturiCredit.getInstance();
    private List<ContCredit> listaContCredit = persistentaConturiCredit.readConturiCredit();

    private ServiciuConturiCredit() {
    }

    public static ServiciuConturiCredit getInstance() {
        if (instance == null) {
            instance = new ServiciuConturiCredit();
        }
        return instance;
    }

    public void writeConturiCredit() {
        persistentaConturiCredit.writeConturiCredit(listaContCredit);
    }

    public void adaugaContCredit() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Se citeste CNP-ul persoanei care detine contul : ");
        String cnpDetinator = reader.readLine();

        System.out.println("Se citeste numarul contului: ");
        String nrCont = reader.readLine();

        System.out.println("Se citeste valuta contului: ");
        String valuta = reader.readLine();

        System.out.println("Se citeste suma din cont: ");
        Float suma = Float.parseFloat(reader.readLine());

        System.out.println("Se citeste tipul contului: ");
        int tip = Integer.parseInt(reader.readLine());

        System.out.println("Se citeste dobanda contului: ");
        Float dobanda = Float.parseFloat(reader.readLine());

        ContCredit plataNoua = new ContCredit(nrCont, cnpDetinator, valuta, tip, suma, dobanda);
        listaContCredit.add(plataNoua);
    }


    public void afiseazaConturiCredit(String cnpClient) {
        for (ContCredit cont : listaContCredit) {
            if (cont.getCnpDetinator().equals(cnpClient)) {
                System.out.println(cont);
            }
        }
    }
}
