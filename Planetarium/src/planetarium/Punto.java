package planetarium;

public class Punto {
	double cordX,cordY;
	
	/**
	 * @param x
	 * @param y
	*/
	public Punto(double x, double y) {
		setX(x);
		setY(y);
	}
	/** 
     * @param x 
     */
	public void setX(double x) {
		this.cordX = x;
	}
	/** 
     * @return 
     */
	public double getX() {
		return cordX;
	}
	/** 
     * @param y
     */
	public void setY(double y) {
		this.cordY = y;
	}
	/** 
     * @return 
     */
	public double getY() {
		return cordY;
	}
}
