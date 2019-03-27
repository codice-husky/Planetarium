package planetarium;
import java.util.*;

public class Pianeta extends CorpoCeleste {
	private LinkedList<Satellite> satelliti;

	public Pianeta(String _nome, String _codice, int _peso, Punto _punto) {
		super(_nome, _codice, _peso, _punto);
		satelliti = new LinkedList<Satellite>();
	}

    public LinkedList<Satellite> getSatelliti() {
		return satelliti;
	}

	/**
     * @return
     */
    public boolean aggiungiSatellite(Satellite satellite) {
    	satelliti.add(satellite);
    	return true;
    }

    /**
     * @param codice 
     * @return
     */
    public boolean rimuoviSatelliti(String codice){
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
    public Satellite cercaSatellite(String codice){
    	for(Satellite satellite: satelliti) {
    		if(satellite.codice.equals(codice))
    			return satellite;
    	}
    	return null;
    }
    
}
