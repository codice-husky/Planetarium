package planetarium;

public class Punto {
	int cordX,cordY;
	
	/**
	 * @param x
	 * @param y
	*/
	public Punto(int x, int y) {
		setX(x);
		setY(y);
	}
	/** 
     * @param x 
     */
	public void setX(int x) {
		this.cordX = x;
	}
	/** 
     * @return 
     */
	public int getX() {
		return cordX;
	}
	/** 
     * @param y
     */
	public void setY(int y) {
		this.cordY = y;
	}
	/** 
     * @return 
     */
	public int getY() {
		return cordY;
	}
}
