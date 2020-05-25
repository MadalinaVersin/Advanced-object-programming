package ro.unibuc.fmi.servicii;
import ro.unibuc.fmi.entitati.ContEconomii;
import ro.unibuc.fmi.persistenta.PersistentaConturiEconomii;

import java.util.List;

public class ServiciuConturiEconomii {
    private static ServiciuConturiEconomii instance;

    private final PersistentaConturiEconomii persistentaConturiEconomii = PersistentaConturiEconomii.getInstance();

    private ServiciuConturiEconomii() {
    }

    public static ServiciuConturiEconomii getInstance() {
        if (instance == null) {
            instance = new ServiciuConturiEconomii();
        }

        return instance;
    }

    public ContEconomii saveConturiEconomii(String numarCont, String cnpDetinator, String valuta, Integer tip, Float suma, Float dobanda) {
        ContEconomii contEconomii = new ContEconomii();
        contEconomii.setNumarCont(numarCont);
        contEconomii.setCnpDetinator(cnpDetinator);
        contEconomii.setValuta(valuta);
        contEconomii.setTip(tip);
        contEconomii.setSuma(suma);
        contEconomii.setDobanda(dobanda);


        return persistentaConturiEconomii.saveContEconomii(contEconomii);
    }

    public ContEconomii findContEconomii(String numarCont) {
        return persistentaConturiEconomii.findContEconomii(numarCont);
    }

    public List<ContEconomii> findAllConturiEconomii(String cnpDetinator) {
        return persistentaConturiEconomii.findAllConturiEconomii(cnpDetinator);
    }

    public ContEconomii updateContEconomii(ContEconomii contEconomii) {
        return persistentaConturiEconomii.updateContEconomii(contEconomii);
    }

    public boolean deleteContEconomii(ContEconomii contEconomii) {
        return persistentaConturiEconomii.deleteContEconomii(contEconomii.getNumarCont());
    }


}
