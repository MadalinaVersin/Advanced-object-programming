package ro.unibuc.fmi.persistenta;
import ro.unibuc.fmi.connection.DataBaseConnection;
import ro.unibuc.fmi.entitati.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersistentaClienti {

    private static PersistentaClienti instance;


    private static final String INSERT_STATEMENT = "INSERT INTO clienti (cnp, codBanca, nume, prenume, adresa) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM clienti  WHERE cnp = ?";
    private static final String SELECT_ALL_FROM_A_BANK = "SELECT * FROM clienti  WHERE codBanca = ?";
    private static final String UPDATE_STATEMENT = "UPDATE clienti SET adresa = ? WHERE cnp = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM clienti WHERE cnp=?";


    private PersistentaClienti() {
    }

    public static PersistentaClienti getInstance() {
        if (instance == null) {
            instance = new PersistentaClienti();
        }

        return instance;
    }

    public Client saveClient(Client client) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, client.getCnp());
            statement.setString(2, client.getCondBanca());
            statement.setString(3, client.getNume());
            statement.setString(4, client.getPrenume());
            statement.setString(5, client.getAdresa());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return new Client();
        }
        return client;
    }

    public Client findClient(String cnp) {
        Client client = new Client();
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setString(1, cnp);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return client;
                }

                System.out.println("User was found!");
                client.setCnp(result.getString("CNP"));
                client.setCondBanca(result.getString("CodBanca"));
                client.setNume(result.getString("Nume"));
                client.setPrenume(result.getString("Prenume"));
                client.setAdresa(result.getString("Adresa"));


            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return client;
    }

    public List<Client> findAllClienti(String codBanca) {
        List<Client> clienti = new ArrayList<>();
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(SELECT_ALL_FROM_A_BANK)) {
            statement.setString(1, codBanca);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Client client = new Client();
                    client.setCnp(result.getString("CNP"));
                    client.setCondBanca(result.getString("CodBanca"));
                    client.setNume(result.getString("Nume"));
                    client.setPrenume(result.getString("Prenume"));
                    client.setAdresa(result.getString("Adresa"));
                    clienti.add(client);
                }

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return clienti;
    }

    public Client updateClient(Client client) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setString(2, client.getCnp());
            statement.setString(1, client.getAdresa());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User was updated successfully!");
                return client;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update user: " + e.getMessage());
            return new Client();
        }

        System.out.println("Something went wrong when trying to update user: User was not found!");
        return new Client();
    }

    public boolean deleteClient(String cnp) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, cnp);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete user: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete user: User was not found!");
        return false;
    }


}
