package planetarium;

import java.util.LinkedList;

/**
 * Classe Stella contenente i parametri di un generico corpo celeste più i parametri specifici
 * @author Codice Husky
 *
 */
public class Stella extends CorpoCeleste{
	private LinkedList<Pianeta> pianeti;
	
	/**
	 * Costruttore della stella
	 * @param _nome Nome della stella
	 * @param _codice Codice univoco della stella
	 * @param _peso Peso della stella
	 * @param _punto Punto della stella
	 */
	public Stella(String _nome, String _codice, int _peso, Punto _punto) {
		super(_nome, _codice, _peso, _punto);
		pianeti = new LinkedList<Pianeta>();
	}
	
	/**
	 * Aggiunge un pianeta come figlio della stella
	 * @param pianeta Pianeta da aggiungere
	 * @return Sempre true
	 */
	public boolean aggiungiPianeta(Pianeta pianeta) {
		pianeti.add(pianeta);
		return true;
	}
	
	/**
	 * Rimuove un pianeta dato il suo codice
     * @param codice Codice del pianeta da rimuovere
     * @return True se trova e rimuove il pianeta, false se non trova il pianeta
     */
	public boolean rimuoviPianeti(String codice) {
		boolean isRemove = false;
    	for(Pianeta pianeta: pianeti) {
    		if(pianeta.codice.equals(codice)){
    			pianeti.remove(pianeta);
    			isRemove = true;
    		}
    	}
		return isRemove;
	}
	
	/**
	 * Cerca un pianeta dato il suo codice
     * @param codice Codice del pianeta da cercare
     * @return Pianeta corrispondente - null se non trovato
     */
	public Pianeta cercaPianeta(String codice) {
		for(Pianeta pianeta: pianeti) {
    		if(pianeta.codice.equals(codice))
    			return pianeta;
    	}
		return null;
	}
	
	/**
	 * Ritorna la lista dei pianeti
	 * @return Lista dei pianeti
	 */
	public LinkedList<Pianeta> getPianeti() {
		return pianeti;
	}

	/**
	 * Ritorna il numero di pianeti presenti
	 * @return numero di pianeti presenti
	 */
	public int getNumPianeti() {
		if(pianeti == null) return 0;
		else return pianeti.size();
	}
}
