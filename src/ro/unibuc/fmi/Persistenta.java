package ro.unibuc.fmi;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.LogRecord;

public class Persistenta {

    private static Persistenta singleIntance = null;

    private Persistenta() {
    }

    public static Persistenta getInstance() {
        if (singleIntance == null)
            singleIntance = new Persistenta();

        return singleIntance;

    }

    public static <T> List<T> read(T object) {

        if (object instanceof Angajat) {
            List<T> arrayOfT = new ArrayList<>();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("angajati.csv"))) {
                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    String[] dateAngajati = currentLine.split(",");
                    Angajat angajat = new Angajat(dateAngajati[0], dateAngajati[1], dateAngajati[2], dateAngajati[3]);
                    arrayOfT.add((T) angajat);
                }
                return arrayOfT;

            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
                return null;
            }
        } else if (object instanceof Client) {
            List<T> arrayOfT = new ArrayList<>();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("clienti.csv"))) {
                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    String[] dateClienti = currentLine.split(",");
                    List<ContBancar> conturi = new ArrayList<>();
                    List<Plata> plati = new ArrayList<>();

                    try (BufferedReader bufferedReaderConturi = new BufferedReader(new FileReader("conturi.csv"))) {
                        String currentLineConturi;
                        while ((currentLineConturi = bufferedReaderConturi.readLine()) != null) {
                            String[] dateConturi = currentLineConturi.split(",");
                            if (dateClienti[2].equals(dateConturi[3])) {
                                if (Integer.parseInt(dateConturi[4]) == 1) {
                                    ContEconomii cont = new ContEconomii(Float.parseFloat(dateConturi[0]), dateConturi[1], dateConturi[2], dateConturi[3], Integer.parseInt(dateConturi[4]), Float.parseFloat(dateConturi[5]));
                                    conturi.add(cont);
                                } else if (Integer.parseInt(dateConturi[4]) == 2) {
                                    ContCredit cont = new ContCredit(Float.parseFloat(dateConturi[0]), dateConturi[1], dateConturi[2], dateConturi[3], Integer.parseInt(dateConturi[4]), Float.parseFloat(dateConturi[5]));
                                    conturi.add(cont);

                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                        return null;
                    }

                    try (BufferedReader bufferedReaderPlati = new BufferedReader(new FileReader("plati.csv"))) {
                        String currentLinePlati;
                        while ((currentLinePlati = bufferedReaderPlati.readLine()) != null) {
                            String[] datePlati = currentLinePlati.split(",");
                            if (dateClienti[2].equals(datePlati[0])) {

                                if (Integer.parseInt(datePlati[3]) == 1) {
                                    Plata plata = new Plata(datePlati[0], datePlati[1], Float.parseFloat(datePlati[2]), Integer.parseInt(datePlati[3]));
                                    plati.add(plata);

                                } else if (Integer.parseInt(datePlati[3]) == 2) {
                                    PlataFacturi plata = new PlataFacturi(datePlati[0], datePlati[1], Float.parseFloat(datePlati[2]), Integer.parseInt(datePlati[3]), datePlati[4], datePlati[5], datePlati[6]);
                                    plati.add(plata);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                        return null;
                    }

                    Client client = new Client(dateClienti[0], dateClienti[1], dateClienti[2], conturi, plati);
                    arrayOfT.add((T) client);

                }

                return arrayOfT;


            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
                return null;
            }
        }
        return null;

    }

    public static <T> void writeToFile(T object, Banca banca) throws IOException {
        Set<Client> listaClienti = new HashSet<>(banca.getClientList());
        if (object instanceof Client) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("clienti.csv"))) {
                for (Client client : listaClienti) {
                    bufferedWriter.write(client.getNume() + "," + client.getAdresa() + "," + client.getCnp());
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        } else if (object instanceof Angajat) {
            List<Angajat> listaAngajati = new ArrayList<>(banca.getAngajatList());
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("angajati.csv"))) {
                for (Angajat angajat : listaAngajati) {
                    bufferedWriter.write(angajat.getNume() + "," + angajat.getPrenume() + "," + angajat.getCodAngajat() + "," + angajat.getCnp());
                    bufferedWriter.newLine();

                }

            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        } else if (object instanceof ContBancar) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("conturi.csv"))) {
                for (Client client : listaClienti) {
                    List<ContBancar> conturi = new ArrayList<>(client.getConturiClient());
                    for (ContBancar cont : conturi) {
                        if (cont.getTip() == 1) {
                            ContEconomii contEconomii = (ContEconomii) cont;
                            bufferedWriter.write(contEconomii.getSuma() + "," + contEconomii.getNumarCont() + "," + contEconomii.getValuta() + "," + contEconomii.getCnpDetinator() + "," + contEconomii.getTip() + "," + ((ContEconomii) cont).getDobanda());
                            bufferedWriter.newLine();
                        } else if (cont.getTip() == 2) {
                            ContCredit contCredit = (ContCredit) cont;
                            bufferedWriter.write(contCredit.getSuma() + "," + contCredit.getNumarCont() + "," + contCredit.getValuta() + "," + contCredit.getCnpDetinator() + "," + contCredit.getTip() + "," + contCredit.getComision());
                            bufferedWriter.newLine();
                        }
                    }

                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        } else if (object instanceof Plata) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("plati.csv"))) {
                for (Client client : listaClienti) {
                    List<Plata> plati = new ArrayList<>(client.getPlatiClient());
                    for (Plata plata : plati) {
                        if (plata.getTip() == 1) {
                            bufferedWriter.write(plata.getCnpClient() + "," + plata.getNrPlata() + "," + plata.getSuma() + "," + plata.getTip());
                            bufferedWriter.newLine();
                        } else if (plata.getTip() == 2) {
                            PlataFacturi plataFacturi = (PlataFacturi) plata;
                            bufferedWriter.write(plataFacturi.getCnpClient() + "," + plataFacturi.getNrPlata() + "," + plataFacturi.getSuma() + "," + plataFacturi.getTip() + "," + plataFacturi.getSerieFactura() + "," + plataFacturi.getFurnizor() + "," + plataFacturi.getIdClient());
                            bufferedWriter.newLine();
                        }

                    }

                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        }

    }


}





