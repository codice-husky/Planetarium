package planetarium;

public class Satellite extends CorpoCeleste{
	
	/**
	 * Classe Satellite contenente i parametri di un generico corpo celeste.
	 * I satelliti non hanno informazioni aggiuntive
	 * @author Codice Husky
	 * @param _nome Nome del satellite
	 * @param _codice Codice del satellite
	 * @param _peso Peso del satellite
	 * @param _punto Coordinate del satellite
	 */
	public Satellite(String _nome, String _codice, int _peso, Punto _punto) {
		super(_nome, _codice, _peso, _punto);
	}
	
}