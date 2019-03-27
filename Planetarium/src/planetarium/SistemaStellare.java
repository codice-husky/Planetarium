package planetarium;

public class SistemaStellare {
	String nome;
	static Stella stella = null;
	
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
     * @return 
     */
	public Punto calcolaMassa(){
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
     * @param codiceA
     * @param codiceB
     * @return 
     */
	public boolean collisione(String codiceA,String codiceB) {
		//metodo per vedere se si scontrano
		return false;
	}
	public String rotta(String codiceA,String codiceB) {
		
		String rotta = "";
		if(presenteCorpo(codiceA) && presenteCorpo(codiceB)) {
			if(codiceA.equals(codiceB)) {
				rotta = "Sono lo stesso corpo";
				return rotta;
			}else {
				if(stella.getCodice().equals(codiceA)) {
					
				}
			}
		}else {
			rotta = "Almeno uno dei 2 codici non esiste";
		}
		return rotta;
	}
	/**
	 * @param codice 
     * @return 
     */
	public boolean presenteCorpo(String codice) {
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
     * @param codice
     * @return 
     */
	public String percorso(String codice){
		String perc = "Stella : "+ stella.getNome()+"("+stella.getCodice()+")";
		if(stella.getCodice().equals(codice)) return perc;
		else {
			perc = perc.concat("\n  Pianeta: ");
			for(Pianeta pianeta: stella.getPianeti()) {
				String sPian = pianeta.getNome()+"("+pianeta.getCodice()+")";
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
}
