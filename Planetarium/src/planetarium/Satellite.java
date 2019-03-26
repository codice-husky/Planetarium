package planetarium;

public class Satellite extends CorpoCeleste{
	String distPianeta;
	
	public Satellite(String _nome, String _codice, int _peso, Punto _punto, String _distPianeta) {
		super(_nome, _codice, _peso, _punto);
		distPianeta = _distPianeta;
	}
	
	/** 
     * @param dist 
     */
	public void setDistanzaPianeta(String dist) {
		this.distPianeta = dist;
	}
	/** 
     * @return 
     */
	public String getDistanzaPianeta() {
		return distPianeta;
	}
}
