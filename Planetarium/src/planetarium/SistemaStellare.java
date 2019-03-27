package planetarium;

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
     * @return 
     */
	public Punto getCentroMassa() {
		//metodo per calcolo centro massa
		return null;
	}
	/** 
     * @param codiceA
     * @param codiceB
     * @return 
     */
	public boolean collisione(int codiceA,int codiceB) {
		//metodo per vedere se si scontrano
		return false;
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
		String perc = "Stella : "+ stella.getCodice();
		if(stella.getCodice().equals(codice)) return perc;
		else {
			perc.concat(" > Pianeta: ");
			for(Pianeta pianeta: stella.getPianeti()) {
				String sPian = pianeta.getCodice();
				if(pianeta.getCodice().equals(codice)) {
					perc.concat(sPian);
					return perc;
				}else {
					sPian.concat(" > Satellite: ");
					for(Satellite satellite: pianeta.getSatelliti()) {
						String sSat = satellite.getCodice();
						if(satellite.getCodice().equals(codice)) {
							perc.concat(sPian);
							perc.concat(sSat);
							return perc;
						}
					}
				}
			}
		}
		perc = "Non è stato trovato un pianeta con quel codice";
		return perc;
	}
	
}
