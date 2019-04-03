package planetarium;

import java.util.ArrayList;
import java.util.LinkedList;

public class SistemaStellare {
	String nome;
	Stella stella = null;
	
	public SistemaStellare(String nome) {
		this.nome = nome;
	}
	
	public boolean aggiungiStella(Stella _stella) {
		if(stella == null) {
			stella = _stella;
			return true;
		} else return false;
	}
	public boolean rimuoviStella() {
		if(stella != null) {
			stella = null;
			return true;
		} else return false;
	}
	

	public Stella getStella() {
		return stella;
	}

	/** 
	 * Calcola il centro di massa di un sistema stellare
     * @return Coordinate del centro di massa
     */
	public Punto calcolaCentroMassa(){
	    int massa = stella.getPeso();
	    Punto punto = stella.getCord();
	    double x = (punto.getX() * stella.getPeso());
	    double y = (punto.getY() * stella.getPeso());
	    
	    for(Pianeta pianeta: stella.getPianeti()){
	        punto = pianeta.getCord();
	        massa += pianeta.getPeso();
	        x += (punto.getX() * pianeta.getPeso());
	        y += (punto.getY() * pianeta.getPeso());
	        for(Satellite satellite: pianeta.getSatelliti()){
	            punto = satellite.getCord();
	            massa += satellite.getPeso();
	            x += (punto.getX() * satellite.getPeso());
	            y += (punto.getY() * satellite.getPeso());
	        }
	    }
	    x /= massa;
	    y /= massa;
	    Punto centroMassa = new Punto(x,y);
	    return centroMassa;
	}
	/** 
	 * Controlla se due corpi celesti collidono
     * @param codiceA Codice del primo corpo celeste
     * @param codiceB Codice del secondo corpo celeste
     * @return true se collidono, false se non collidono
     */
	public boolean collisione(String codiceA,String codiceB) {

		double vA[] = getDistanza(codiceA);
		double vB[] = getDistanza(codiceB);
		double d = vA[0];
		double e = vA[1];
		double d0 = vB[0];
		double e0 = vB[1];
		if(codiceA.equals(codiceB)) return false;
		if((getStringDaCodice(codiceA).equals("stella")&& getStringDaCodice(codiceB).equals("pianeta"))|| //stella e pianeta
				getStringDaCodice(codiceA).equals("pianeta")&& getStringDaCodice(codiceB).equals("stella")) {
				return false;
		}else if(getStringDaCodice(codiceA).equals("pianeta") && getStringDaCodice(codiceB).equals("pianeta")) {
			Pianeta pA = stella.cercaPianeta(codiceA);
			Pianeta pB = stella.cercaPianeta(codiceB);
			if(pA.distanzaDa(stella) == pB.distanzaDa(stella)) {
				return true;
			}
			return false; 
		}else {
			if(getStringDaCodice(codiceA).equals("pianeta") && getStringDaCodice(codiceB).equals("satellite")) { //satB appartenente 
				for(Pianeta pianeta: stella.getPianeti()) {										   //pianetaA
					if(pianeta.getCodice().equals(codiceA)) {
						if(pianeta.cercaSatellite(codiceB)!=null) {
							return false;
						}
					}
				}
			}
			if(getStringDaCodice(codiceA).equals("satellite") && getStringDaCodice(codiceB).equals("pianeta")) {//satA appartenente 
				for(Pianeta pianeta: stella.getPianeti()) {										   //pianetaB
					if(pianeta.getCodice().equals(codiceB)) {
						if(pianeta.cercaSatellite(codiceA)!=null) {
							return false;
						}
					}
				}	
			}
			if(getStringDaCodice(codiceA).equals("satellite")&&getStringDaCodice(codiceB).equals("satellite")) {
				for(Pianeta pianeta: stella.getPianeti()) {
					boolean a = false, b = false;
					for(Satellite satellite: pianeta.getSatelliti()) {
						if(satellite.getCodice().equals(codiceA)) {
							a = true;
						}
						if(satellite.getCodice().equals(codiceB)) {
							b = true;
						}
					}
					if(a == true && b == true) {
						return false;
					}
				}
			}
			if(d0 > d) {
				if((d0-e0)<=(d+e)) {
					System.out.println("qui");
					return true;
				}
			}else {
				if((d0+e0)>=(d-e)) {
					System.out.println("qua");
					return true;
				}
			}
		}
		return false;
	
	}
	
	public String getStringDaCodice(String codice) {
		if(stella.getCodice().equals(codice)) {
			return "stella";
		}else {
			for(Pianeta pianeta: stella.getPianeti()) {
				if(pianeta.getCodice().equals(codice)) {
					return "pianeta";
				}else {
					for(Satellite satellite: pianeta.getSatelliti()) {
						if(satellite.getCodice().equals(codice))
							return "satellite";
					}
				}
			}
		}
		return "";
	}
	
	/**
	 * Calcola la rotta necessaria per muoversi da un pianeta all'altro
	 * @param partenza Codice del pianeta di partenza
	 * @param arrivo Codice del pianeta di arrivo
	 * @param ss Sistema stellare di riferimento
	 * @return Stringa contenente percorso e distanza
	 */
	public String rotta(String partenza,String arrivo, SistemaStellare ss) {
		String rotta = "";
		if(presenteCorpoCodice(partenza) && presenteCorpoCodice(arrivo)) {
			if(partenza.equals(arrivo)) return "Sono lo stesso corpo";
		} else return "Almeno uno dei 2 codici non esiste";
		CorpoCeleste nextHopPartenza = CorpoCeleste.getCorpoFromCodice(ss, partenza);
		ArrayList<CorpoCeleste> parentPartenza = new ArrayList<CorpoCeleste>();
		do {
			parentPartenza.add(nextHopPartenza);
			nextHopPartenza = nextHopPartenza.getParent(ss);
		} while(nextHopPartenza != null);

		CorpoCeleste nextHopArrivo = CorpoCeleste.getCorpoFromCodice(ss, arrivo);
		ArrayList<CorpoCeleste> parentArrivo = new ArrayList<CorpoCeleste>();
		do {
			parentArrivo.add(nextHopArrivo);
			nextHopArrivo = nextHopArrivo.getParent(ss);
		} while(nextHopArrivo != null);
		
		
		int posIncontroPartenza = 0;
		int posIncontroArrivo = 0;
		ricercaCorrispondenza:
		for(CorpoCeleste ramoPartenza : parentPartenza) {
			posIncontroArrivo = 0;
			for(CorpoCeleste ramoArrivo : parentArrivo) {
				if(ramoPartenza.equals(ramoArrivo)) break ricercaCorrispondenza;
				posIncontroArrivo++;
			}
			posIncontroPartenza++;
		}
		
		double distanza = 0;
		LinkedList<CorpoCeleste> corpiFinali = new LinkedList<CorpoCeleste>();
		
		if(posIncontroPartenza != 0) {
			rotta = rotta.concat(parentPartenza.get(0).getNome() + " (Codice: "+parentPartenza.get(0).getCodice()+")");
			corpiFinali.add(parentPartenza.get(0));
		}
		for(int i=1; i<posIncontroPartenza; i++) {
			corpiFinali.add(parentPartenza.get(i));
			rotta = rotta.concat(" > " +parentPartenza.get(i).getNome() + " (Codice: "+parentPartenza.get(i).getCodice()+")");
		}
		
		if(posIncontroPartenza != 0) rotta = rotta.concat(" > ");
		rotta = rotta.concat(parentArrivo.get(posIncontroArrivo).getNome() + " (Codice: "+parentArrivo.get(posIncontroArrivo).getCodice()+")");
		corpiFinali.add(parentArrivo.get(posIncontroArrivo));
		for(int i=posIncontroArrivo-1; i>=0; i--) {
			corpiFinali.add(parentArrivo.get(i));
			rotta = rotta.concat(" > " + parentArrivo.get(i).getNome() + " (Codice: "+parentArrivo.get(i).getCodice()+")");
		}
		
		for(int i=0; i<corpiFinali.size()-1; i++) {
			distanza+=corpiFinali.get(i).distanzaDa(corpiFinali.get(i+1));
		}
		
		rotta = rotta.concat("\nDistanza: " + distanza);
		return rotta;
	}
	
		
	/**
	 * Controlla se � presente un corpo
	 * @param codice Codice del corpo da controllare
     * @return true se il corpo � presente, false se non � presente
     */
	public boolean presenteCorpoCodice(String codice) {
		Stella stella = getStella();
		if(stella.getCodice().equals(codice)) return true;
		else {
			for(Pianeta pianeta : stella.getPianeti()) {
	    		if(pianeta.getCodice().equals(codice)) return true;
	    		else {
	    			for(Satellite satellite : pianeta.getSatelliti()) {
	    				if(satellite.getCodice().equals(codice)) return true;
	    			}
	    		}
	    	}
			return false;
		}
	}
	/** 
	 * Calcola il percorso necessario per raggiugnere un corpo celeste a partire dalla stella
     * @param codice Codice del corpo celeste da ragigungere
     * @return Stringa contenente il percorso
     */
	public String percorso(String codice){
		String perc = "Stella : "+ stella.getNome()+"("+stella.getCodice()+")";
		if(stella.getCodice().equals(codice)) return perc;
		else {
			perc = perc.concat("\n  Pianeta: ");
			for(Pianeta pianeta: stella.getPianeti()) {
				String sPian = pianeta.getNome()+"(Codice: "+pianeta.getCodice()+")";
				if(pianeta.getCodice().equals(codice)) {
					perc = perc.concat(sPian);
					return perc;
				}else {
					sPian = sPian.concat("\n    Satellite: ");
					for(Satellite satellite: pianeta.getSatelliti()) {
						String sSat = satellite.getNome()+"("+satellite.getCodice()+")";
						if(satellite.getCodice().equals(codice)) {
							perc = perc.concat(sPian);
							perc = perc.concat(sSat);
							return perc;
						}
					}
				}
			}
		}
		perc = "Non è stato trovato nessun corpo celeste con quel codice";
		return perc;
	}
	
	private double[] getDistanza(String codice) {
		Stella stella = getStella();
		double[]c = new double[2];
		double d = 0, e = 0;
		boolean memo = false;
		if(presenteCorpoCodice(codice)) {
			for(Pianeta pianeta: stella.getPianeti()) {
				if(pianeta.getCodice().equals(codice) || memo == true) break;
				else {
					double x1 = Math.pow(pianeta.getCord().getX()-stella.getCord().getX(),2);
					double y1 = Math.pow(pianeta.getCord().getY()-stella.getCord().getY(),2);
					d = Math.sqrt(x1+y1);
					for(Satellite satellite: pianeta.getSatelliti()) {
						double x2 = Math.pow(satellite.getCord().getX()-pianeta.getCord().getX(),2);
						double y2 = Math.pow(satellite.getCord().getY()-pianeta.getCord().getY(),2);
						e = Math.sqrt(x2+y2);
						if(satellite.getCodice().equals(codice)) {
							memo = true;
							break;
						}
					}
				}
			}
		}
		c[0]=d;
		c[1] =e;
		return c;
	}
	
	/**
	 * Controlla se � presente un corpo dato il suo nome
	 * @param nome Nome del corpo da cercare
	 * @return true se presente, false se non presente
	 */
	public boolean presenteCorpoNome(String nome) {
		if(stella.getNome().equals(nome)) return true;
		for(Pianeta pianeta: stella.getPianeti()) {
			if(pianeta.getNome().equals(nome)) return true;
			for(Satellite satellite: pianeta.getSatelliti()) {
				if(satellite.getNome().equals(nome)) return true;
			}
		}
		return false;
	}
	
	/**
	 * Controlla se � presente un corpo date le sue coordinate
	 * @param punto Coordinate del corpo da cercare
	 * @return true se presente, false se non presente
	 */
	public boolean presenteCorpoPunto(Punto punto) {
		if(stella.getCord().getX() == punto.getX() &&
		   stella.getCord().getY() == punto.getY()) return true;
		for(Pianeta pianeta: stella.getPianeti()) {
			if(pianeta.getCord().getX() == punto.getX() &&
					   pianeta.getCord().getY() == punto.getY()) return true;
			for(Satellite satellite: pianeta.getSatelliti()) {
				if(satellite.getCord().getX() == punto.getX() &&
						   satellite.getCord().getY() == punto.getY()) return true;
			}
		}
		return false;
	}
}
