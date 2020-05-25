package ro.unibuc.fmi.connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DataBaseConnection {
    private static DataBaseConnection instance;

    private final Connection connection;


    private DataBaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/banca";
        String password = "root";
        String username = "root";
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
            createTables(instance.getConnection());
        }

        if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private static void createTables(Connection connection) {
        String delimiter = ";";
        try (Scanner scanner = new Scanner(new File("DataBase.sql"))) {
            scanner.useDelimiter(delimiter);
            while (scanner.hasNext()) {
                String rawStatement = scanner.next() + delimiter;
                if (rawStatement.trim().length() < 2) {
                    continue;
                }
                System.out.println("Executing statement: " + rawStatement);

                try (Statement currentStatement = connection.createStatement()) {
                    currentStatement.execute(rawStatement);
                    System.out.println("Successfully executed statement!");
                } catch (SQLException e) {
                    System.out.println("Failed to execute statement: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not load file: 'DataBase.sql'!");
        }
    }
}
