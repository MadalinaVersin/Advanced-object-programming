package ro.unibuc.fmi.persistenta;
import ro.unibuc.fmi.connection.DataBaseConnection;
import ro.unibuc.fmi.entitati.ContEconomii;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersistentaConturiEconomii {
    private static PersistentaConturiEconomii instance;

    private static final String INSERT_STATEMENT = "INSERT INTO conturieconomii (numarCont, cnpDetinator, valuta, tip, suma, dobanda) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM conturieconomii WHERE  numarCont= ?";
    private static final String SELECT_ALL_ACCOUNTS_FROM_A_CUSTOMER = "SELECT * FROM conturieconomii WHERE  cnpDetinator = ?";
    private static final String UPDATE_STATEMENT = "UPDATE conturiEconomii SET suma = ? WHERE numarCont = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM conturiEconomii WHERE numarCont= ? ";


    private PersistentaConturiEconomii() {
    }

    public static PersistentaConturiEconomii getInstance() {
        if (instance == null) {
            instance = new PersistentaConturiEconomii();
        }

        return instance;
    }

    public ContEconomii saveContEconomii(ContEconomii contEconomii) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, contEconomii.getNumarCont());
            statement.setString(2, contEconomii.getCnpDetinator());
            statement.setString(3, contEconomii.getValuta());
            statement.setInt(4, contEconomii.getTip());
            statement.setFloat(5, contEconomii.getSuma());
            statement.setFloat(6, contEconomii.getDobanda());


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new bank account was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new bank account: " + e.getMessage());
            return new ContEconomii();
        }
        return contEconomii;
    }

    public ContEconomii findContEconomii(String numarCont) {
        ContEconomii contEconomii = new ContEconomii();
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setString(1, numarCont);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find bank account: Bank account was not found!");
                    return contEconomii;
                }

                System.out.println("Bank account was found!");
                contEconomii.setNumarCont(result.getString("NumarCont"));
                contEconomii.setCnpDetinator(result.getString("CnpDetinator"));
                contEconomii.setValuta(result.getString("Valuta"));
                contEconomii.setTip(result.getInt("Tip"));
                contEconomii.setSuma(result.getFloat("Suma"));
                contEconomii.setDobanda(result.getFloat("Dobanda"));

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find bank account: " + e.getMessage());
        }
        return contEconomii;
    }

    public List<ContEconomii> findAllConturiEconomii(String cnpDetinator) {
        List<ContEconomii> conturiEconomii = new ArrayList<>();
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(SELECT_ALL_ACCOUNTS_FROM_A_CUSTOMER)) {
            statement.setString(1, cnpDetinator);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    ContEconomii contEconomii = new ContEconomii();

                    contEconomii.setNumarCont(result.getString("NumarCont"));
                    contEconomii.setCnpDetinator(result.getString("CnpDetinator"));
                    contEconomii.setValuta(result.getString("Valuta"));
                    contEconomii.setTip(result.getInt("Tip"));
                    contEconomii.setSuma(result.getFloat("Suma"));
                    contEconomii.setDobanda(result.getFloat("Dobanda"));
                    conturiEconomii.add(contEconomii);

                }

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return conturiEconomii;
    }


    public ContEconomii updateContEconomii(ContEconomii contEconomii) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setFloat(1, contEconomii.getSuma());
            statement.setString(2, contEconomii.getNumarCont());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Bank account was updated successfully!");
                return contEconomii;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update bank account: " + e.getMessage());
            return new ContEconomii();
        }

        System.out.println("Something went wrong when trying to update bank account: Bank account was not found!");
        return new ContEconomii();
    }

    public boolean deleteContEconomii(String numarCont) {
        try (PreparedStatement statement = DataBaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, numarCont);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Bank account was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete bank account: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete bank account: Bank account was not found!");
        return false;
    }


}
