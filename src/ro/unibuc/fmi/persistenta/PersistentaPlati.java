package ro.unibuc.fmi.persistenta;
import ro.unibuc.fmi.entitati.PlataFacturi;
import ro.unibuc.fmi.entitati.Plata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistentaPlati {

    private static PersistentaPlati instance = null;

    private PersistentaPlati() {

    }

    public static PersistentaPlati getInstance() {
        if (instance == null) {
            instance = new PersistentaPlati();
        }
        return instance;
    }

    public List<Plata> readPlati() {
        List<Plata> listaPlati = new ArrayList<>();
        try (BufferedReader bufferedReaderPlati = new BufferedReader(new FileReader("plati.csv"))) {
            String currentLinePlati;
            while ((currentLinePlati = bufferedReaderPlati.readLine()) != null) {
                String[] datePlati = currentLinePlati.split(",");
                if (Integer.parseInt(datePlati[3]) == 1) {
                    Plata plata = new Plata(datePlati[0], datePlati[1], Float.parseFloat(datePlati[2]), Integer.parseInt(datePlati[3]));
                    listaPlati.add(plata);

                } else if (Integer.parseInt(datePlati[3]) == 2) {
                    PlataFacturi plata = new PlataFacturi(datePlati[0], datePlati[1], Float.parseFloat(datePlati[2]), Integer.parseInt(datePlati[3]), datePlati[4], datePlati[5], datePlati[6]);
                    listaPlati.add(plata);
                }
            }
            return listaPlati;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return null;
        }
    }

    public void writePlati(List<Plata> listaPlati) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("plati.csv"))) {
            for (Plata plata : listaPlati) {
                if (plata.getTip() == 1) {
                    bufferedWriter.write(plata.getCnpClient() + "," + plata.getNrPlata() + "," + plata.getSuma() + "," + plata.getTip());
                    bufferedWriter.newLine();
                } else if (plata.getTip() == 2) {
                    PlataFacturi plataFacturi = (PlataFacturi) plata;
                    bufferedWriter.write(plataFacturi.getCnpClient() + "," + plataFacturi.getNrPlata() + "," +
                            plataFacturi.getSuma() + "," + plataFacturi.getTip() + "," + plataFacturi.getSerieFactura()
                            + "," + plataFacturi.getFurnizor() + "," + plataFacturi.getIdClient());
                    bufferedWriter.newLine();
                }

            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }
}
