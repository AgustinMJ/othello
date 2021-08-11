package main.persistencia.classes;

import main.utils.UsuariJaExistex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * La classe PersistenciaUsuari és l'encarregada de llegir i escriure els fitxers dels usuaris
 * @author Carles Ureta Boza
 */

public class PersistenciaUsuari {
    final static String path = "./dades/usuaris/";
    /**
     * Constructora per defecte
     */
    public PersistenciaUsuari() {}


    /**
     * Comprova si existeix un usuari amb el nom donat.
     * @param nom Nom de l'usuari.
     * @return Boolean indicant si existeix el usuari o no.
     */
    public  boolean existeixUsuari(String nom) {
        Path path = Paths.get("./dades/usuaris/" + nom + ".txt");
        if(Files.exists(path)) {

            return true;

        }
        return false;
    }

    /**
     * Crea un Usuari amb un nom i una contrasenya determinats.
     * @param nom nom del usuari a crear
     * @param contrasenya contrasenya del usuari a crear
     */
    public  void creaUsuari(String nom, String contrasenya) throws IOException, UsuariJaExistex {
        String filePath = path + nom + ".txt";

        if(existeixUsuari(nom)) throw new UsuariJaExistex();

        File f = new File(filePath);
        f.createNewFile();

        FileWriter fw = new FileWriter(filePath);
        fw.write(nom+"\n"+contrasenya);
        fw.close();
    }

    /**
     * Comprova si la contrasenya és la correcte per l'usuari donat.
     * @param nom Nom de l'usuari.
     * @param contrasenya Contrasenya de a comprovar.
     * @return Boolean indicant si l'usuari i la contrasenya són correctes.
     */
    public  boolean autenticaUsuari(String nom, String contrasenya) {
        if (existeixUsuari(nom)) {
            try {
                File file = new File(path + nom + ".txt");
                Scanner sc = new Scanner(file);
                String nomUsuari = sc.nextLine();
                String contrasenyaUsuari = sc.nextLine();
                if (contrasenyaUsuari.equals(contrasenya)) return true;

            }catch (IOException e) {
            }

        }
        return false;
    }

    /**
     * Elimina l'usuari que li passem per paràmetre del nostre sistema
     * @param nom nom del usuari a eliminar
     */

    public  void eliminaUsuari(String nom) {
        if (existeixUsuari(nom)) {
            String filePath = path + nom + ".txt";
            File f = new File(filePath);
            f.delete();
        }
    }

    /**
     * Canvia la contrasenya del usuari per una contrasenya nova.
     * @param nom nom del usuari al que li canviem la contrasneya.
     * @param contrasenya contrasenya nova a instaurar.
     */
    public  void canviaContrasenya(String nom, String contrasenya) {
        if (existeixUsuari(nom)) {
            String filePath = path + nom + ".txt";
            File f = new File(filePath);
            f.delete();
            try {
                f.createNewFile();
            } catch (IOException ioException) {
            }

            try {
                FileWriter fw = new FileWriter(filePath);
                fw.write(nom+"\n"+contrasenya);
                fw.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Demana a la persistència la llista amb els moms dels usuaris del sistema
     * @return Una llista amb els noms dels usuaris
     */

    public  List<String> getUsuaris() throws FileNotFoundException {
        List<String> usuaris = new ArrayList<>();
        File f = new File (path);
        File[] fUsuaris = f.listFiles();
        Arrays.asList(fUsuaris).sort(Comparator.comparing(File::getName));

        for (int i = 0; i < fUsuaris.length; ++i) {
            File file = fUsuaris[i];
            Scanner sc = new Scanner(file);
            String nomUsuari = sc.nextLine();
            usuaris.add(nomUsuari);
        }

return usuaris;
    }
}
