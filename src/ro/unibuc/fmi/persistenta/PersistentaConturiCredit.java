package ro.unibuc.fmi.persistenta;

import ro.unibuc.fmi.entitati.ContCredit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistentaConturiCredit {
    private static PersistentaConturiCredit instance = null;

    private PersistentaConturiCredit() {
    }

    public static PersistentaConturiCredit getInstance() {
        if (instance == null) {
            instance = new PersistentaConturiCredit();
        }
        return instance;
    }

    public List<ContCredit> readConturiCredit() {

        List<ContCredit> conturiCredit = new ArrayList<>();
        try (BufferedReader bufferedReaderConturi = new BufferedReader(new FileReader("conturi.csv"))) {
            String currentLineConturi;
            while ((currentLineConturi = bufferedReaderConturi.readLine()) != null) {
                String[] dateConturi = currentLineConturi.split(",");
                ContCredit cont = new ContCredit(dateConturi[0],
                        dateConturi[1], dateConturi[2], Integer.parseInt(dateConturi[3]),
                        Float.parseFloat(dateConturi[4]), Float.parseFloat(dateConturi[5]));
                conturiCredit.add(cont);


            }
            return conturiCredit;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return null;
        }
    }

    public void writeConturiCredit(List<ContCredit> listaConturi) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("conturi.csv"))) {
            for (ContCredit contCredit : listaConturi) {
                bufferedWriter.write(contCredit.getNumarCont() + "," + contCredit.getCnpDetinator()
                        + "," + contCredit.getValuta() + "," + contCredit.getTip() + ","
                        + contCredit.getSuma() + "," + contCredit.getComision());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }
}

