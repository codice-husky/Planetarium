package planetarium;

public class Satellite extends Corpo_celeste{
	int distPianeta;
	
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
