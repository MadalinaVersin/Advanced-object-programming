package ro.unibuc.fmi.persistenta;
import ro.unibuc.fmi.connection.DataBaseConnection;
import ro.unibuc.fmi.entitati.Banca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersistentaBanci {
    private static PersistentaBanci instance;


    private static final String INSERT_STATEMENT = "INSERT INTO banci (codBanca, denumireBanca) VALUES (?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM banci WHERE codBanca = ?";
    private static final String UPDATE_STATEMENT = "UPDATE banci SET denumireBanca = ? WHERE codBanca = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM banci WHERE codBanca=?";

    private PersistentaBanci() {
    }

    public static PersistentaBanci getInstance() {
        if (instance == null) {
            instance = new PersistentaBanci();
        }

        return instance;
    }

    public Banca saveBanca(Banca banca) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(2, banca.getCodBanca());
            statement.setString(1, banca.getDenumireBanca());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new bank was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new bank: " + e.getMessage());
            return new Banca();
        }
        return banca;
    }

    public Banca findBanca(String codBanca) {
        Banca banca = new Banca();
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setString(1, codBanca);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find bank: Bank was not found!");
                    return banca;
                }

                System.out.println("Bank was found!");
                banca.setCodBanca(result.getString("CodBanca"));
                banca.setDenumireBanca(result.getString("DenumireBanca"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return banca;
    }

    public Banca updateBanca(Banca banca) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setString(2, banca.getCodBanca());
            statement.setString(1, banca.getDenumireBanca());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Bank was updated successfully!");
                return banca;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update bank: " + e.getMessage());
            return new Banca();
        }

        System.out.println("Something went wrong when trying to update bankr: Bank was not found!");
        return new Banca();
    }

    public boolean deleteBanca(String codBanca) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, codBanca);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Bank was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete bank: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete bank: Bank was not found!");
        return false;
    }

}
