package planetarium;

import java.util.LinkedList;

public class Stella extends CorpoCeleste{
	private LinkedList<Pianeta> pianeti = new LinkedList<Pianeta>();
	
	
	public Stella(String _nome, String _codice, int _peso, Punto _punto) {
		super(_nome, _codice, _peso, _punto);

	}
	
	
	public void aggiungiPian() {}
	
	/**
     * @param int codice
     */
	public void rimuoviPian(int codice) {}
	
	/**
     * @param int codice
     * @return
     */
	public Pianeta getPianeta(int codice) {
		return null;
	}
	
	
}
