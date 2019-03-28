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

		double vA[] = SistemaStellare.getDistanza(this, codiceA);
		double vB[] = SistemaStellare.getDistanza(this, codiceB);
		double d = vA[0];
		double e = vA[1];
		double d0 = vB[0];
		double e0 = vB[1];
		if(codiceA.equals(codiceB)) return false;
		if((returnClass(codiceA).equals("stella")&& returnClass(codiceB).equals("pianeta"))|| //stella e pianeta
			returnClass(codiceA).equals("pianeta")&& returnClass(codiceB).equals("stella")) {
				return false;
		}else if(returnClass(codiceA).equals("pianeta")&&returnClass(codiceB).equals("pianeta")) {
			return false; 
		}else {
			if(returnClass(codiceA).equals("pianeta") && returnClass(codiceB).equals("satellite")) { //satB appartenente 
				for(Pianeta pianeta: stella.getPianeti()) {										   //pianetaA
					if(pianeta.getCodice().equals(codiceA)) {
						if(pianeta.cercaSatellite(codiceB)!=null) {
							return false;
						}
					}
				}
			}
			if(returnClass(codiceA).equals("satellite")&&returnClass(codiceB).equals("pianeta")) {//satA appartenente 
				for(Pianeta pianeta: stella.getPianeti()) {										   //pianetaB
					if(pianeta.getCodice().equals(codiceB)) {
						if(pianeta.cercaSatellite(codiceA)!=null) {
							return false;
						}
					}
				}	
			}
			if(returnClass(codiceA).equals("satellite")&&returnClass(codiceB).equals("satellite")) {
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
	
	public String returnClass(String codice) {
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
	public String rotta(String codiceA,String codiceB) {
		String rotta = "";
		if(SistemaStellare.presenteCorpo(this, codiceA) && SistemaStellare.presenteCorpo(this, codiceB)) {
			if(codiceA.equals(codiceB)) {
				rotta = "Sono lo stesso corpo";
				return rotta;
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
	public static boolean presenteCorpo(SistemaStellare ss, String codice) {
		Stella stella = ss.getStella();
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
	
	public static double[] getDistanza(SistemaStellare ss, String codice) {
		Stella stella = ss.getStella();
		double[]c = new double[2];
		double d = 0, e = 0;
		boolean memo = false;
		if(presenteCorpo(ss, codice)) {
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
}
