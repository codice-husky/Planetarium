package planetarium;

/**
 * Classe Punto utilizzata per rappresentare un punto su un piano cartesiano
 * @author Codice Husky
 *
 */
public class Punto {
	double cordX,cordY;
	
	/**
	 * Costruttore del punto
	 * @param x Posizione x
	 * @param y Posizione y
	*/
	public Punto(double x, double y) {
		setX(x);
		setY(y);
	}
	/** 
	 * Cambia il valore della X
     * @param x Nuovo valore della X
     */
	public void setX(double x) {
		this.cordX = x;
	}
	/** 
	 * Ritorna il valore di X
     * @return Valore di X
     */
	public double getX() {
		return cordX;
	}
	/** 
	 * Cambia il valore della Y
     * @param y Nuovo valore della Y
     */
	public void setY(double y) {
		this.cordY = y;
	}
	/** 
	 * Ritorna il valore di Y
     * @return Valore di Y
     */
	public double getY() {
		return cordY;
	}
}
