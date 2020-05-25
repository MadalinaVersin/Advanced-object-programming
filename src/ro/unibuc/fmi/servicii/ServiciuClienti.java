package ro.unibuc.fmi.servicii;
import ro.unibuc.fmi.entitati.Client;
import ro.unibuc.fmi.persistenta.PersistentaClienti;
import java.util.List;

public class ServiciuClienti {
    private static ServiciuClienti instance;

    private final PersistentaClienti persistentaClienti = PersistentaClienti.getInstance();

    private ServiciuClienti() {
    }

    public static ServiciuClienti getInstance() {
        if (instance == null) {
            instance = new ServiciuClienti();
        }

        return instance;
    }

    public Client saveClient(String cnp, String codBanca, String nume, String prenume, String adresa) {
        Client client = new Client();
        client.setCnp(cnp);
        client.setCondBanca(codBanca);
        client.setNume(nume);
        client.setPrenume(prenume);
        client.setAdresa(adresa);
        return persistentaClienti.saveClient(client);
    }

    public Client findClient(String cnp) {
        return persistentaClienti.findClient(cnp);
    }

    public List<Client> findAllClienti(String codBanca) {
        return persistentaClienti.findAllClienti(codBanca);
    }

    public Client updateClient(Client client) {
        return persistentaClienti.updateClient(client);
    }

    public boolean deleteClient(Client client) {
        return persistentaClienti.deleteClient(client.getCnp());
    }

}
