package planetarium;

import java.util.ArrayList;
import java.util.LinkedList;

public class Stella extends CorpoCeleste{
	private LinkedList<Pianeta> pianeti;
	
	
	public Stella(String _nome, String _codice, int _peso, Punto _punto) {
		super(_nome, _codice, _peso, _punto);
		pianeti = new LinkedList<Pianeta>();
	}
	
	
	public boolean aggiungiPianeta(Pianeta pianeta) {
		pianeti.add(pianeta);
		return true;
	}
	
	/**
     * @param codice
     * @return
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
     * @param codice
     * @return
     */
	public Pianeta cercaPianeta(String codice) {
		for(Pianeta pianeta: pianeti) {
    		if(pianeta.codice.equals(codice))
    			return pianeta;
    	}
		return null;
	}
	
	public LinkedList<Pianeta> getPianeti() {
		return pianeti;
	}


	public int getNumPianeti() {
		if(pianeti == null) return 0;
		else return pianeti.size();
	}
}
