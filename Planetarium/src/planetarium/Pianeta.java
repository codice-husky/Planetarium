package planetarium;
import java.util.*;

public class Pianeta extends Corpo_celeste {
	private int distStella;
	private LinkedList<Satellite> satelliti;

    public Pianeta() {}

    /**
     * @return
     */
    public void aggiungiSat() {}

    /**
     * @param codice 
     */
    public void rimuoviSat(int codice){}

    /**
     * @param codice
     * @return
     */
    public Satellite getSat(int codice){return null;}

    /**
     * @param dist 
     */
    public void setDistanzaStella(int dist) {
    	this.distStella = dist; 
    }

    /**
     * @return 
     */
    public int getDistanzaStella() {
    	return distStella;
    }

}
