package main.domini.classes;

import main.domini.classes.Color;
import main.domini.classes.ModeDeJoc;
import main.utils.PartidaAcabada;
import main.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe taulell representa un taulell de la partida.
 *
 * @author Carles Ureta Boza
 */
public class Taulell {
    Color[][] caselles;

    /**
     * Constructora que inicialitza el taulell.
     * @param caselles Matriu de colors que indica el color de cada casella.
     */
    public Taulell(Color[][] caselles) {
        this.caselles = caselles;
    }

    /**
     * Col·loca una fitxa en la casella indicada i actualitza les caselles determinades en funció del mode de joc. S'utilitza per configurar un taulell.
     * @param x Fila del taulell on es troba la casella on volem col·locar la fitxa.
     * @param y Columna del taulell on es troba la casella on volem col·locar la fitxa.
     * @param color Color de la fitxa a col·locar.
     * @param modeDeJoc Mode de joc.
     * @return Taulell resultant després d'executar el moviment.
     */
    public Taulell colocarFitxaModeCreacio(int x, int y, Color color, ModeDeJoc modeDeJoc){
        caselles[x][y] = color;
        Color colorInvers;
        if (color == Color.BLANC) colorInvers = Color.NEGRE;
        else colorInvers = Color.BLANC;
        if (color != Color.BUIT) {
            if (modeDeJoc == ModeDeJoc.VERTICAL || modeDeJoc == ModeDeJoc.CLASSIC) {
                if (x>1 && caselles[x-1][y]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x - 1;
                    while (!trobat && i >= 0){
                        if (caselles[i][y] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][y] == color)trobat = true;
                        --i;
                    }
                    if (trobat) {
                        i = x-1;

                        while (contador != 0) {
                            caselles[i][y]=color;
                            --contador;
                            --i;
                        }
                    }
                }
                if (x<6 && caselles[x+1][y]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x + 1;
                    while (!trobat && i <= 7){
                        if (caselles[i][y] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][y] == color)trobat = true;
                        ++i;
                    }
                    if (trobat) {
                        i = x+1;
                        while (contador != 0) {
                            caselles[i][y]=color;
                            --contador;
                            ++i;
                        }
                    }
                }
            }
            if (modeDeJoc == ModeDeJoc.HORITZONTAL || modeDeJoc == ModeDeJoc.CLASSIC) {
                if (y > 1 && caselles[x][y - 1] == colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = y - 1;
                    while (!trobat && i >= 0){
                        if (caselles[x][i] == colorInvers) {
                            contador = contador + 1;
                        } else if (caselles[x][i] == color) trobat = true;
                        --i;
                    }
                    if (trobat) {
                        i = y - 1;
                        while (contador != 0) {
                            caselles[x][i] = color;
                            --contador;
                            --i;
                        }
                    }
                }
                if (y < 6 && caselles[x][y + 1] == colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = y + 1;
                    while (!trobat && i <= 7){
                        if (caselles[x][i] == colorInvers) {
                            contador = contador + 1;
                        } else if (caselles[x][i] == color) trobat = true;
                        ++i;
                    }
                    if (trobat) {
                        i = y + 1;
                        while (contador != 0) {
                            caselles[x][i] = color;
                            --contador;
                            ++i;
                        }
                    }
                }
            }
            if (modeDeJoc == ModeDeJoc.DIAGONAL || modeDeJoc == ModeDeJoc.CLASSIC) {
                if (x>1 && y>1 && caselles[x-1][y-1]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x - 1;
                    int j = y - 1;
                    while (!trobat && i >= 0 && j >= 0){
                        if (caselles[i][j] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][j] == color)trobat = true;
                        --i;
                        --j;
                    }
                    if (trobat) {
                        i = x-1;
                        j = y-1;
                        while (contador != 0) {
                            caselles[i][j]=color;
                            --contador;
                            --i;
                            --j;
                        }
                    }
                }
                if (x<6 && y<6 && caselles[x+1][y+1]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x + 1;
                    int j = y + 1;
                    while (!trobat && i <= 7 && j<=7){
                        if (caselles[i][j] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][j] == color)trobat = true;
                        ++i;
                        ++j;
                    }
                    if (trobat) {
                        i = x+1;
                        j = y+1;
                        while (contador != 0) {
                            caselles[i][j]=color;
                            --contador;
                            ++i;
                            ++j;
                        }
                    }
                }

                if (x>1 && y<6 && caselles[x-1][y+1]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x - 1;
                    int j = y + 1;
                    while (!trobat && i >= 0 && j<=7){
                        if (caselles[i][j] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][j] == color)trobat = true;
                        --i;
                        ++j;
                    }
                    if (trobat) {
                        i = x-1;
                        j = y+1;
                        while (contador != 0) {
                            caselles[i][j]=color;
                            --contador;
                            --i;
                            ++j;
                        }
                    }
                }

                if (x<6 && y>1 && caselles[x+1][y-1]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x + 1;
                    int j = y - 1;
                    while (!trobat && i <= 7 && j >=0){
                        if (caselles[i][j] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][j] == color)trobat = true;
                        ++i;
                        --j;
                    }
                    if (trobat) {
                        i = x+1;
                        j = y-1;
                        while (contador != 0) {
                            caselles[i][j]=color;
                            --contador;
                            ++i;
                            --j;
                        }
                    }
                }
            }
        }

        return this;
    }

    /**
     * Col·loca una fitxa en la casella indicada i actualitza les caselles determinades en funció del mode de joc. S'utilitza per jugar una partida.
     * @param x Fila del taulell on es troba la casella on volem col·locar la fitxa.
     * @param y Columna del taulell on es troba la casella on volem col·locar la fitxa.
     * @param color Color de la fitxa a col·locar.
     * @param modeDeJoc Mode de joc de la partida.
     * @return Nombre de peces girades degut al moviment fet.
     */
    public int colocarFitxa(int x, int y, Color color, ModeDeJoc modeDeJoc) throws PartidaAcabada {

        caselles[x][y] = color;
        Color colorInvers;
        int fitxesGirades = 0;
        if (color == Color.BLANC) colorInvers = Color.NEGRE;
        else colorInvers = Color.BLANC;
        if (color != Color.BUIT) {
            if (modeDeJoc == ModeDeJoc.VERTICAL || modeDeJoc == ModeDeJoc.CLASSIC) {
                if (x>1 && caselles[x-1][y]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x - 1;
                    while (!trobat && i >= 0){
                        if (caselles[i][y] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][y] == color)trobat = true;
                        --i;
                    }
                    if (trobat) {
                        i = x-1;
                        fitxesGirades += contador;
                        while (contador != 0) {
                            caselles[i][y]=color;
                            --contador;
                            --i;
                        }
                    }
                }
                if (x<6 && caselles[x+1][y]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x + 1;
                    while (!trobat && i <= 7){
                        if (caselles[i][y] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][y] == color)trobat = true;
                        ++i;
                    }
                    if (trobat) {
                        i = x+1;
                        fitxesGirades += contador;
                        while (contador != 0) {
                            caselles[i][y]=color;
                            --contador;
                            ++i;
                        }
                    }
                }
            }
            if (modeDeJoc == ModeDeJoc.HORITZONTAL || modeDeJoc == ModeDeJoc.CLASSIC) {
                if (y > 1 && caselles[x][y - 1] == colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = y - 1;
                    while (!trobat && i >= 0){
                        if (caselles[x][i] == colorInvers) {
                            contador = contador + 1;
                        } else if (caselles[x][i] == color) trobat = true;
                        --i;
                    }
                    if (trobat) {
                        i = y - 1;
                        fitxesGirades += contador;
                        while (contador != 0) {
                            caselles[x][i] = color;
                            --contador;
                            --i;
                        }
                    }
                }
                if (y < 6 && caselles[x][y + 1] == colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = y + 1;
                    while (!trobat && i <= 7){
                        if (caselles[x][i] == colorInvers) {
                            contador = contador + 1;
                        } else if (caselles[x][i] == color) trobat = true;
                        ++i;
                    }
                    if (trobat) {
                        i = y + 1;
                        fitxesGirades += contador;
                        while (contador != 0) {
                            caselles[x][i] = color;
                            --contador;
                            ++i;
                        }
                    }
                }
            }
            if (modeDeJoc == ModeDeJoc.DIAGONAL || modeDeJoc == ModeDeJoc.CLASSIC) {
                if (x>1 && y>1 && caselles[x-1][y-1]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x - 1;
                    int j = y - 1;
                    while (!trobat && i >= 0 && j >= 0){
                        if (caselles[i][j] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][j] == color)trobat = true;
                        --i;
                        --j;
                    }
                    if (trobat) {
                        i = x-1;
                        j = y-1;
                        fitxesGirades += contador;
                        while (contador != 0) {
                            caselles[i][j]=color;
                            --contador;
                            --i;
                            --j;
                        }
                    }
                }
                if (x<6 && y<6 && caselles[x+1][y+1]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x + 1;
                    int j = y + 1;
                    while (!trobat && i <= 7 && j<=7){
                        if (caselles[i][j] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][j] == color)trobat = true;
                        ++i;
                        ++j;
                    }
                    if (trobat) {
                        i = x+1;
                        j = y+1;
                        fitxesGirades += contador;
                        while (contador != 0) {
                            caselles[i][j]=color;
                            --contador;
                            ++i;
                            ++j;
                        }
                    }
                }

                if (x>1 && y<6 && caselles[x-1][y+1]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x - 1;
                    int j = y + 1;
                    while (!trobat && i >= 0 && j<=7){
                        if (caselles[i][j] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][j] == color)trobat = true;
                        --i;
                        ++j;
                    }
                    if (trobat) {
                        i = x-1;
                        j = y+1;
                        fitxesGirades += contador;
                        while (contador != 0) {
                            caselles[i][j]=color;
                            --contador;
                            --i;
                            ++j;
                        }
                    }
                }

                if (x<6 && y>1 && caselles[x+1][y-1]==colorInvers) {
                    boolean trobat = false;
                    int contador = 0;
                    int i = x + 1;
                    int j = y - 1;
                    while (!trobat && i <= 7 && j >=0){
                        if (caselles[i][j] == colorInvers) {
                            contador = contador + 1;
                        }
                        else if (caselles[i][j] == color)trobat = true;
                        ++i;
                        --j;
                    }
                    if (trobat) {
                        i = x+1;
                        j = y-1;
                        fitxesGirades += contador;
                        while (contador != 0) {
                            caselles[i][j]=color;
                            --contador;
                            ++i;
                            --j;
                        }
                    }
                }
            }
        }

        return fitxesGirades;
    }

    /**
     * Busca una casella buida.
     * @param c Color del torn actual.
     * @param x_dir Direcció en l'eix horitzontal.
     * @param y_dir Direcció en l'eix vertical.
     * @param x_pos Fila de la casella des de la qual es busca.
     * @param y_pos Columna de la casella des de la qual es busca.
     * @param t Taulell en el qual es busca.
     * @return Tuple amb les posicions de la casella si és buida. -1, -1 si la casella no és buida.
     */
    private Tuple busca_buit(Color c, int x_dir, int y_dir, int x_pos, int y_pos, Taulell t) {
        Color enemy;
        if (c.name().equals("BLANC")) enemy = Color.NEGRE;
        else enemy = Color.BLANC;
        if (x_pos <= 7 && x_pos >= 0 && y_pos >= 0 && y_pos <=7 && t.getCasella(x_pos, y_pos).equals(Color.BUIT)) return new Tuple(x_pos, y_pos);
        if (x_pos <= 7 && x_pos >= 0 && y_pos >= 0 && y_pos <=7 && t.getCasella(x_pos, y_pos).equals(enemy)) return busca_buit(c, x_dir, y_dir, x_pos+x_dir, y_pos+y_dir, t);
        if (x_pos+x_dir < 0 || x_pos+x_dir > 7) return new Tuple(-1, -1);
        if (y_pos+y_dir < 0 || y_pos+y_dir > 7) return new Tuple(-1, -1);
        return new Tuple(-1, -1);
    }

    /**
     * Comprova si un moviment és vàlid.
     * @param c Color del torn actual.
     * @param x_dir Direcció en l'eix horitzontal.
     * @param y_dir Direcció en l'eix vertical.
     * @param x_pos Fila de la casella des de la qual es busca.
     * @param y_pos Columna de la casella des de la qual es busca.
     * @param t Taulell en el qual es busca.
     * @return Tuple amb la posició de la casella amb moviment vàlid. -1, -1 si el moviment no és vàlid.
     */
    private Tuple moviment_valid(Color c, int x_dir, int y_dir, int x_pos, int y_pos, Taulell t) {

        Color enemy;
        if (c.name().equals("BLANC")) enemy = Color.NEGRE;
        else enemy = Color.BLANC;
        if (x_pos+x_dir > 7 || x_pos+x_dir < 0) return new Tuple(-1, -1);
        if (y_pos+y_dir > 7 || y_pos+y_dir < 0) return new Tuple(-1, -1);
        if (t.getCasella(x_pos+x_dir, y_pos+y_dir).equals(enemy)) {
            return busca_buit(c, x_dir, y_dir, x_pos+2*x_dir, y_pos+2*y_dir, t);
        }
        else return new Tuple(-1, -1);
    }


    /**
     * Obté els possibles moviments.
     * @param c Color del torn.
     * @param gameMode Mode de joc de la partida.
     * @return Llists de tuples amb les posicions dels possibles moviments.
     */
    public List<Tuple<Integer, Integer>> getSuccessors(Color c, ModeDeJoc gameMode) {

        List<Tuple<Integer, Integer>> successors = new ArrayList<>();

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {

                if (getCasella(i, j).equals(c)) {
                    if (gameMode.name().equals("CLASSIC") || gameMode.name().equals("HORITZONTAL")) {
                        Tuple left = moviment_valid(c, 0, -1, i, j, this);
                        Tuple right = moviment_valid(c, 0, 1, i, j, this);
                        if ((Integer) left.x != -1) successors.add(left);
                        if ((Integer) right.x != -1) successors.add(right);;
                    }
                    if (gameMode.name().equals("CLASSIC") || gameMode.name().equals("VERTICAL")) {
                        Tuple up = moviment_valid(c, -1, 0, i, j, this);
                        Tuple down = moviment_valid(c, 1, 0, i, j, this);
                        if ((Integer) up.x != -1) successors.add(up);
                        if ((Integer) down.x != -1) successors.add(down);
                    }
                    if (gameMode.name().equals("CLASSIC") || gameMode.name().equals("DIAGONAL")) {
                        Tuple up_left = moviment_valid(c, -1, -1, i, j, this);
                        Tuple up_right = moviment_valid(c, -1, 1, i, j, this);
                        Tuple down_left = moviment_valid(c, 1, -1, i, j, this);
                        Tuple down_right = moviment_valid(c, 1, 1, i, j, this);
                        if ((Integer) up_left.x != -1) successors.add(up_left);
                        if ((Integer) down_left.x != -1) successors.add(down_left);
                        if ((Integer) up_right.x != -1) successors.add(up_right);
                        if ((Integer) down_right.x != -1) successors.add(down_right);;
                    }
                }
            }
        }
        return successors;
    }

    /**
     * Obté el color d'una casella del taulell.
     * @param x Fila de la casella que es vol obtenir.
     * @param y Columna de la casella que es vol obtenir.
     * @return Color de la casella donada.
     */
    public Color getCasella(int x, int y) {
        return caselles[x][y];
    }

    /**
     * Obté el nombre de peces d'un color en el taulell.
     * @param c Color.
     * @return Nombre de peces del color passat per paràmetre en el taulell.
     */
    public int getNumPeces(Color c) {
    	int nombrePeces = 0;
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
				if(this.caselles[i][j] == c) nombrePeces++;
		    }
	    }

    	return nombrePeces;
    }
}
