package main.domini.classes;

import main.utils.FilaEsTotal;
import main.utils.FilaNoTotal;
import main.utils.Tuple;

import java.util.LinkedList;

/**
 * La classe Files representa un conjunt files del ranking. Hereda de la classe LinkedList.
 *
 * @author Agustín Millán Jiménez
 */
public class Files<E> extends LinkedList<Fila> {
	/**
	 * Busca la fila corresponent al jugador amb el nom indicat.
	 * @param nom Nom del jugador.
	 * @return Un enter indicant la posició del jugador en el conjunt. Si no es troba la fila, retorna -1.
	 */
	public int getIndexByNom(String nom) {
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getJugador().getNom().equals(nom)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Busca la fila corresponent al jugador amb el nom indicat.
	 * @param nom Nom del jugador.
	 * @return La fila del jugador.
	 */
	public Fila getFilaByNom(String nom) {
		for(int i = 0; i < this.size(); i++) {
			Fila f = this.get(i);
			if(f.getJugador().getNom().equals(nom)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Inserta una fila en el conjunt.
	 * @param fila La fila que es vol insertar.
	 */
	public void insertFila(Fila fila) {
		boolean continua = true;
		if(this.isEmpty()) {
			this.add(fila);
			continua = false;
		}
		for(int i = this.size() - 1; i >= 0 && continua; i--) {
			if(this.get(i).getPuntuacio() >= fila.getPuntuacio()) {
				this.add(i, fila);
			} else {
				continua = false;
			}
		}
	}

	/**
	 * Intercanvia dos files del conjunt.
	 * @param i1 Index de la primera fila.
	 * @param i2 index de la segona fila.
	 */
	public void swap(int i1, int i2) {
		Fila aux = this.get(i1);
		this.set(i1, this.get(i2));
		this.set(i2, aux);
	}

	/**
	 * Reordena el conjunt de files.
	 * @param index Posició a partir de la qual s'ha de reordenar el conjunt.
	 * @return Un enter indicant la última posició que ha fet falta reordenar.
	 */
	public int reordena(int index) {
		for(int i = index - 1; i >= 0; i--) {
			if(this.get(i).getPuntuacio() < this.get(i + 1).getPuntuacio()) {
				swap(i, i + 1);
			} else {
				return i + 1;
			}
		}
		return 0;
	}

	/**
	 * Actualitza una fila del conjunt.
	 * @param jugador Jugador pertanyent a la fila que cal actualitzar
	 * @param accio Indica si el jugador ha guanyat, perdut o empatat.
	 * @return Una tupla d'enters indicant la posició antiga i la nova posició de la fila del jugador en el conjunt
	 */
	public Tuple<Integer, Integer> updateFila(Jugador jugador, Accions accio) throws FilaEsTotal {
		//Busca la fila del jugador
		int indexInicial = this.getIndexByNom(jugador.getNom());
		int index = indexInicial;

		//Si no existix cap fila la crea
		if(index == -1) {
			this.add(new Fila(jugador));
			index = this.size() - 1;
		}

		//Obtenim la fila
		Fila fila = this.get(index);

		//Apliquem l'acció que calgui
		switch(accio) {
			case VICTORIA:
				fila.victoria();
				break;
			case DERROTA:
				fila.derrota();
				break;
			case EMPAT:
				fila.empat();
				break;
		}

		//Reordenem el ranking i retornem els índex.
		return new Tuple(indexInicial, this.reordena(index));
	}
}
