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
    public boolean rimuoviSat(String codice){
    	boolean isRemove = false;
    	for(Satellite satellite: satelliti) {
    		if(satellite.codice.equals(codice)){
    			satelliti.remove(satellite);
    			isRemove = true;
    		}
    	}
    	return isRemove;
    }

    /**
     * @param codice
     * @return
     */
    public Satellite getSat(String codice){
    	for(Satellite satellite: satelliti) {
    		if(satellite.codice.equals(codice))
    			return satellite;
    	}
    	return null;
    }

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
