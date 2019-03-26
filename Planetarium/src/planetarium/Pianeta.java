package planetarium;
import java.util.*;

public class Pianeta extends CorpoCeleste {
	private int distStella;
	private LinkedList<Satellite> satelliti;

	public Pianeta(String _nome, String _codice, int _peso, Punto _punto,int _distanzaStella) {
		super(_nome, _codice, _peso, _punto);
		distStella = _distanzaStella;
		satelliti = new LinkedList<Satellite>();
	}

    /**
     * @return
     */
    public void aggiungiSat(Satellite satellite) {
    	satelliti.add(satellite);
    }

    /**
     * @param codice 
     */
    public void rimuoviSat(String codice){}

    /**
     * @param codice
     * @return
     */
    public Satellite getSat(String codice){return null;}

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
