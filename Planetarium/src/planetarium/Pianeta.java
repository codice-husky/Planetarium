package planetarium;
import java.util.*;

public class Pianeta extends CorpoCeleste {
	private int distStella;
	private LinkedList<Satellite> satelliti;

	public Pianeta(String _nome, String _codice, int _peso, Punto _punto) {
		super(_nome, _codice, _peso, _punto);
	}

    /**
     * @return
     */
    public void aggiungiSat() {}

    /**
     * @param codice 
     */
    public void rimuoviSat(int codice){}

    /**
     * @param codice
     * @return
     */
    public Satellite getSat(int codice){return null;}

    /**
     * @param dist 
     */
    public void setDistanzaStella(int dist) {
    	this.distStella = dist; 
    }

    /**
     * @return 
     */
    public int getDistanzaStella() {
    	return distStella;
    }

}
