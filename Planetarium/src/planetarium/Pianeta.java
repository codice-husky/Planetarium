package planetarium;
import java.util.*;


/**
 * Classe Pianeta contenente i parametri di un generico corpo celeste pi� i parametri specifici
 * @author Codice Husky
 *
 */
public class Pianeta extends CorpoCeleste {
	private LinkedList<Satellite> satelliti;

	/**
	 * Costruttore del pianeta
	 * @param _nome Nome del pianeta
	 * @param _codice Codice del pianeta
	 * @param _peso Peso del pianeta
	 * @param _punto Coordinate del pianeta
	 */
	public Pianeta(String _nome, String _codice, int _peso, Punto _punto) {
		super(_nome, _codice, _peso, _punto);
		satelliti = new LinkedList<Satellite>();
	}

	/**
	 * Ritorna la lista dei satelliti
	 * @return Lista dei satelliti
	 */
    public LinkedList<Satellite> getSatelliti() {
		return satelliti;
	}

	/**
	 * Aggiunge un satellite come figlio del pianeta
	 * @param satellite Satellite da aggiungere
     * @return Sempre true
     */
    public boolean aggiungiSatellite(Satellite satellite) {
    	satelliti.add(satellite);
    	return true;
    }

    /**
     * Rimuove un satellite dato il suo codice
     * @param codice Codice del satellite da rimuovere
     * @return True se trova e rimuove il satellite, false se non lo trova
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
     * Cerca un satellite dato il suo codice
     * @param codice Codice del satellite da cercare
     * @return Satellite cercato - null se non trovato
     */
    public Satellite cercaSatellite(String codice){
    	for(Satellite satellite: satelliti) {
    		if(satellite.codice.equals(codice))
    			return satellite;
    	}
    	return null;
    }
    
}
