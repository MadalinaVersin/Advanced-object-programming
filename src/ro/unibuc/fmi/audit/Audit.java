package ro.unibuc.fmi.audit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Audit {
    private static Audit instance = null;

    private Audit() {
    }

    public static Audit getInstance() {
        if (instance == null) {
            instance = new Audit();
        }
        return instance;
    }

    public void audit(String nume, Date data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }


    }
}
