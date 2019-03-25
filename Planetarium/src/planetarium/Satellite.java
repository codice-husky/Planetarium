package planetarium;

public class Satellite extends CorpoCeleste{
	int distPianeta;
	
	public Satellite(String _nome, String _codice, int _peso, Punto _punto) {
		super(_nome, _codice, _peso, _punto);
	}
	
	/** 
     * @param dist 
     */
	public void setDistanzaPianeta(int dist) {
		this.distPianeta = dist;
	}
	/** 
     * @return 
     */
	public int getDistanzaPianeta() {
		return distPianeta;
	}
}
