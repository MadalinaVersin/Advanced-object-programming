package ro.unibuc.fmi.persistenta;
import ro.unibuc.fmi.connection.DataBaseConnection;
import ro.unibuc.fmi.entitati.Angajat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersistentaAngajati {

    private static final String INSERT_STATEMENT = "INSERT INTO angajati (cnp,codBanca,nume,prenume,codAngajat ) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM angajati WHERE cnp = ?";
    private static final String SELECT_ALL_FROM_A_BANK = "SELECT * FROM angajati WHERE codBanca = ?";
    private static final String UPDATE_STATEMENT = "UPDATE angajati SET nume = ? WHERE cnp = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM angajati WHERE cnp=?";

    private static PersistentaAngajati instance = null;

    private PersistentaAngajati() {
    }

    public static PersistentaAngajati getInstance() {
        if (instance == null) {
            instance = new PersistentaAngajati();
        }
        return instance;
    }


    public Angajat saveAngajat(Angajat angajat) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, angajat.getCnp());
            statement.setString(2, angajat.getCodBanca());
            statement.setString(3, angajat.getNume());
            statement.setString(4, angajat.getPrenume());
            statement.setString(5, angajat.getCodAngajat());


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return new Angajat();
        }
        return angajat;
    }

    public Angajat findAngajat(String cnpAngajat) {
        Angajat angajat = new Angajat();
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setString(1, cnpAngajat);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find angajat: Angajat was not found!");
                    return angajat;
                }

                System.out.println("Angajat was found!");
                angajat.setCnp(result.getString("CNP"));
                angajat.setCodBanca(result.getString("CodBanca"));
                angajat.setNume(result.getString("Nume"));
                angajat.setPrenume(result.getString("Prenume"));
                angajat.setCodAngajat(result.getString("CodAngajat"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return angajat;
    }

    public List<Angajat> findAllAngajati(String codBanca) {
        List<Angajat> angajati = new ArrayList<>();
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(SELECT_ALL_FROM_A_BANK)) {
            statement.setString(1, codBanca);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Angajat angajat = new Angajat();

                    angajat.setCnp(result.getString("CNP"));
                    angajat.setCodBanca(result.getString("CodBanca"));
                    angajat.setNume(result.getString("Nume"));
                    angajat.setPrenume(result.getString("Prenume"));
                    angajat.setCodAngajat(result.getString("CodAngajat"));
                    angajati.add(angajat);

                }

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return angajati;
    }

    public Angajat updateAngajat(Angajat angajat) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setString(1, angajat.getNume());
            statement.setString(2, angajat.getCnp());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Angajat was updated successfully!");
                return angajat;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update angajat: " + e.getMessage());
            return new Angajat();
        }

        System.out.println("Something went wrong when trying to update angajat: Angajat was not found!");
        return new Angajat();
    }

    public boolean deleteAngajat(String cnpAngajat) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, cnpAngajat);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Angajat was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete Angajat: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete angajat: Angajat was not found!");
        return false;
    }


}
