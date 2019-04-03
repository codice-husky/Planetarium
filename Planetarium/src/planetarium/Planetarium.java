package planetarium;
import java.util.Scanner;

/**
 * La classe InputData si occupa dell'inserimento dei nuovi corpi celesti 
 */
class InputData {
	String nome;
	String codice;
	int peso;
	Punto coordinate;
	/**
	 * Il costruttore setta i vari attributi necessari per la creazione
	 * di un nuovo corpo celeste
	 * @param testo Serve per visualizzare nella console se è una stella,
	 *              un pianeta o un satellite
	 * @param sc    E' l'oggetto di classe Scanner per gli input da tastiera  
	 * @param ss    E' il sistema stellare di riferimento
	 */
	InputData(String testo, Scanner sc,SistemaStellare ss) {
		boolean c = false;  //questa variabile viene semplicemente usata
		do {				//se per caso esiste già un corpo con nome/
			if(!c) {		//codice o coordinate uguali a quelle appena
			c = !c;		    //inserite; se è true
			}				//				 ||	 
			else {			//				 \/
				System.out.println("Esiste già un corpo con quel nome");
			}
			System.out.println("Inserisci il nome del"+testo+": ");
			nome = sc.nextLine();
		}while(ss.presenteCorpoNome(nome));
		c = false;
		do {
			if(!c)
				c = !c;
			else 
				System.out.println("Esiste già un corpo con quel codice");
			System.out.println("Vuoi assegnare un codice manualmente? S/[N]: ");
			boolean codiceMan = sc.nextLine().equals("S")? true : false;
			if(codiceMan) {
				System.out.println("Inserisci il codice del"+testo+": ");
				codice = sc.nextLine();
			} else codice = "SRND" + Math.floor(Math.random()*100000);
		}while(ss.presenteCorpoCodice(codice));
		System.out.println("Inserisci il peso del "+testo +": ");
		peso = Integer.parseInt(sc.nextLine());
		c = false;
		do {
			if(!c)
				c = !c;
			else
				System.out.println("Esiste già un corpo con quelle coordinate");
			System.out.println("Inserisci le coordinate del"+ testo+ " (x y): ");
			int coordX = sc.nextInt();
			int coordY = sc.nextInt();
			sc.nextLine();
			coordinate = new Punto(coordX, coordY);
		}while(ss.presenteCorpoPunto(coordinate));
	}
}
/**
 * La classe Planetarium è quella che contiene il main e quindi la gestione
 * dei comandi inseriti dall'utente e i metodi relativi
 * AD OGNI METODO VIENE PASSATO IL SISTEMA STELLARE PER UNA POSSIBILE
 * IMPLEMENTAZIONE DI PIU' SISTEMI
*/
public class Planetarium {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Inserisci il nome del sistema solare: ");
		String nome = sc.nextLine();
		SistemaStellare ss = new SistemaStellare(nome);
		//CREAZIONE AUTOMATICA STELLA, PIANETA, SATELLITE
		System.out.print("vuoi creare automaticamente un piccolo sistema? (S/N)");
		String crea = sc.nextLine();
		if(crea.equals("S")) {
			System.out.println("SONO STATI CREATI AUTOMATICAMENTE UNA STELLA"
					+ " 2 PIANETI E 3 SATELLITI ");
			ss.aggiungiStella(new Stella("Sole","1",30,new Punto(0,0)));
			ss.stella.aggiungiPianeta(new Pianeta("Pianeta 1","2",5,new Punto(3,0)));
			ss.stella.aggiungiPianeta(new Pianeta("Pianeta 2","3",7,new Punto(6,0)));
			ss.stella.cercaPianeta("2").aggiungiSatellite(new Satellite("Luna 1","4",1,new Punto(5,0)));
			ss.stella.cercaPianeta("3").aggiungiSatellite(new Satellite("Luna 2","5",2,new Punto(8,0)));
			ss.stella.cercaPianeta("3").aggiungiSatellite(new Satellite("Luna 3","6",1,new Punto(4,4)));
		}else {
			System.out.println("NON E' STATO CREATO IL SISTEMA");
		}
		System.out.println("--------------------------------------");
		String cmnd;
		System.out.println("Digita aiuto per ricevere aiuto");
		boolean end = true;
		while(end) {
			System.out.print("Che cosa vuoi fare? ");
			cmnd = sc.nextLine();
			switch(cmnd) {
			case "aiuto":
				aiuto();
				break;
			case "aggiungi stella":
				aggiungiStella(ss);
				break;
			case "aggiungi pianeta":
				aggiungiPianeta(ss);
				break;
			case "aggiungi satellite":
				aggiungiSatellite(ss);
				break;
			case "rimuovi stella":
				rStella(ss);
				break;
			case "rimuovi pianeta":
				rPianeta(ss);
				break;
			case "rimuovi satellite":
				rSatellite(ss);
				break;
			case "percorso":
				percorso(ss);
				break;
			case "visualizza sistema":
				printSistema(ss);
				break;
			case "centro massa":
				centroMassa(ss);
				break;
			case "scheda corpo":
				schedaCorpo(ss);
				break;
			case "calcola rotta":
				calcolaRotta(ss);
				break;
			case "collisione":
				collisione(ss);
				break;
			case "ricerca":
				ricerca(ss);
				break;
			case "esci":
				end = !end;
				System.out.println("\n Fine del programma");
			default:
				System.out.println("Comando non riconosciuto!");
				break;
			}
		}
		
		sc.close();
	}
	/**
	 * Visualizza tutti i comandi che il programma dispone
	 * */
	public static void aiuto() {
		System.out.println("aiuto: per ricevere aiuto");
		System.out.println("aggiungi stella: per aggiungere una nuova stella");
		System.out.println("aggiungi pianeta: per aggiungere un pianeta");
		System.out.println("aggiungi satellite: per aggiungere un satellite");
		System.out.println("rimuovi stella: per rimuovere una stella");
		System.out.println("rimuovi pianeta: per rimuovere un pianeta");
		System.out.println("rimuovi satellite: per rimuovere un satellite");
		System.out.println("percorso: visualizza graficamente il percorso necessario per \n"
				+          "          raggiungere un corpo (stella -> pianeta -> satellite");
		System.out.println("visualizza sistema: visualizza graficamente il sistema sotto forma\n"
				+          "                    di albero(facendo quindi la prima funzionalità\n"
				+ 		   "                    del terzo punto");
		System.out.println("centro massa: visualizza le coordinate del centro di massa del sistema");
		System.out.println("calcola rotta: mostra il percorso che c'è  tra 2 corpi celesti");
		System.out.println("collisione: per vedere se 2 corpi potrebbero collidere");
		System.out.println("ricerca: dice se esiste o meno un corpo e se è un satellite\n"
				+ 		   "         mostra anche il pianeta attorno a cui rotea (facendo quindi \n"
				+ 		   "         la seconda funzionalità del secondo punto)");
	}
	/**
	 * Metodo per l'aggiunta di una stella, restituisce true o false in base se 
	 * l'ha aggiunta o meno
	 * @param ss Sistema stellare di riferimento
	 * @return true se aggiunto,false se non lo ha fatto
	 * */
	public static boolean aggiungiStella(SistemaStellare ss) {
		InputData inputStella = new InputData("la stella", sc,ss);
		return ss.aggiungiStella(new Stella(inputStella.nome, inputStella.codice, inputStella.peso, inputStella.coordinate));
	}
	/**
	 * Metodo per la rimozione di una stella e di tutti i corpi ad esso associati
	 * @param ss Sistema stellare di riferimento
	 * */
	public static void rStella(SistemaStellare ss) {
		String risposta = "";
		while(!risposta.equals("Si")&& !risposta.equals("No")) {
			System.out.println("Se elimini la stella tutti i pianeti e i\n"
				+ "satelliti verranno rimossi!\n"
				+ "Sei sicuro? (Si/No)");
	    	risposta = sc.nextLine();
		}
		if(risposta.equals("Si")) {
			ss.rimuoviStella();	
		}else {
			System.out.println("La stella "+ss.getStella().getNome()+" non è stata rimossa");
		}
	}
	/**
	 * Metodo per l'aggiunta di un pianeta, restituisce true o false in base se 
	 * l'ha aggiunto o meno
	 * @param ss Sistema stellare di riferimento
	 * @return true se aggiunto, false se non lo ha fatto
	 * */
	public static boolean aggiungiPianeta(SistemaStellare ss) {
		if(ss.getStella() == null) {
			System.out.println("Bisogna prima aggiungere una stella!");
			return false;
		} else {		
			InputData inputPianeta = new InputData(" pianeta", sc,ss);
			while(ss.presenteCorpoCodice(inputPianeta.codice)==true) {
				System.out.println("\nE' gia presente corpo celeste con quel codice!\n");
				inputPianeta = new InputData(" pianeta", sc,ss);
			}
			return ss.getStella().aggiungiPianeta(new Pianeta(inputPianeta.nome, inputPianeta.codice, inputPianeta.peso, inputPianeta.coordinate));
		}
	}
	/** Metodo per la rimozione di un pianeta e dei satelliti ad esso associati
	 * @param ss Sistema stellare di riferimento
	*/
	public static void rPianeta(SistemaStellare ss) {
		String codice = getCodice(),risposta = "";
		while(!risposta.equals("Si")&& !risposta.equals("No")) {
			System.out.println("Se elimini il sistema i satelliti verranno rimossi!\n"
				+ "Sei sicuro? (Si/No)");
		   	risposta = sc.nextLine();
		}
		if(risposta.equals("Si")) {
				boolean esito = ss.getStella().rimuoviPianeti(codice);
				if(esito) {
					System.out.println("Pianeta rimosso con successo");
				}else {
					System.out.println("Non esiste un pianeta con quel codice");
				}
		}else {
			System.out.println("Il pianeta con codice "+codice+" non è stata rimosso");
		}
	}
	/** Metodo per l'aggiunta di un satellite, restituisce true o false in base se 
	 * l'ha aggiunto o meno
	 * @param ss Sistema stellare di riferimento
	 * @return true se aggiunto,false se non lo ha fatto
	*/
	public static boolean aggiungiSatellite(SistemaStellare ss) {
		if(ss.getStella() == null) {
			System.out.println("Bisogna prima aggiungere una stella!");
			return false;
		} else if(ss.getStella().getNumPianeti() == 0) {
			System.out.println("Bisogna prima aggiungere almeno un pianeta!");
			return false;
		} else {
			System.out.println("Inserisci il codice del pianeta: ");
			String codPianeta = sc.nextLine();
			Pianeta pianeta = ss.getStella().cercaPianeta(codPianeta);
			while(pianeta == null) {
				System.out.println("Inserisci il codice del pianeta: ");
				codPianeta = sc.nextLine();
				pianeta = ss.getStella().cercaPianeta(codPianeta);
			}
			
			InputData inputSatellite = new InputData(" satellite", sc,ss);			
			while(ss.presenteCorpoCodice(inputSatellite.codice) == true) {
				System.out.println("\nE' gia presente corpo celeste con quel codice!\n");
				inputSatellite = new InputData(" pianeta", sc,ss);
			}	
			return pianeta.aggiungiSatellite(new Satellite(inputSatellite.nome, inputSatellite.codice, inputSatellite.peso, inputSatellite.coordinate));
		}
	}
	/** Metodo per la rimozione di una satellite
	 * @param ss Sistema stellare di riferimento
	*/
	public static void rSatellite(SistemaStellare ss) {
		System.out.println("Inserisci il codice del pianeta del satellite: ");
		String codiceA = sc.nextLine();
		System.out.println("Inserisci il codice del satellite: ");
		String codiceB = sc.nextLine();
		Pianeta pianeta = ss.getStella().cercaPianeta(codiceA);
		if(pianeta != null) {
			boolean ris = pianeta.rimuoviSatelliti(codiceB);
			if(ris) {
				System.out.println("Satellite rimosso con successo!");
			}else {
				System.out.println("Non esiste un satellite con quel nome");
			}
		}	
	}
	
	/** Metodo utilizzato dagli altri metodi per la scrittura di un codice,
	 *  utile se nel metodo serve solo un codice, altrimenti si crea
	 *  ambiguità su quale codice è stato inserito
	 * @return il codice appena inserito
	*/
	public static String getCodice() {
		System.out.println("Inserisci il codice: ");
		return (sc.nextLine());
	}
	/**
	 * Visualizza il sistema stellare sotto forma di albero
	 * ES:
	 * Stella
	 *   Pianeta
	 *     Satellite
	 * @param ss Sistema stellare di riferimento
	*/
	public static void printSistema(SistemaStellare ss) {
		System.out.println("Stella "+ ss.stella.getNome());
		for(Pianeta pianeta: ss.stella.getPianeti()) {
			System.out.println(String.format("  Pianeta: %s(%s)", 
					pianeta.getNome(),pianeta.getCodice()));
			for(Satellite satellite: pianeta.getSatelliti()) {
				System.out.println(String.format("    Satellite: %s(%s)", 
						satellite.getNome(),satellite.getCodice()));
			}
		}
	}
	/**
	 * Visualizza le coordinate del centro di massa
	 * @param ss Sistema stellare di riferimento
	*/
	public static void centroMassa(SistemaStellare ss) {
		Punto centro = ss.calcolaCentroMassa();
		String x = ""+(Math.round(centro.getX()*1000.0)/1000.0);
		String y = ""+(Math.round(centro.getY()*1000.0)/1000.0);
		System.out.println(String.format("Le cordinate del centro di massa sono (%s, %s)",x,y));
	}
	/**
	 * Visualizza i dati di un corpo celeste dato il suo codice 
	 * (nome,codice,coordinate,peso) 
	 * @param ss Sistema stellare di riferimento
	*/
	public static void schedaCorpo(SistemaStellare ss) {
		String codice = getCodice();
		if(ss.presenteCorpoCodice(codice)) {
			CorpoCeleste cc = CorpoCeleste.getCorpoFromCodice(ss, codice);
			System.out.println(String.format("Nome corpo: %s\n"
					+ "Codice corpo: %s\n"
					+ "Peso: %d\n"
					+ "Coordinate: (%f,%f)", cc.getNome(),
											 cc.getCodice(),
											 cc.getPeso(),
											 cc.getCord().getX(),cc.getCord().getY()));
		}else {
			System.out.println("Non esiste un corpo celeste con quel nome");
		}
	}
	/**
	 * Visualizza graficamente il percorso necessario per raggiungerlo:
	 * ES per un satellite:
	 * Sole
	 *  Pianeta
	 *    Satellite
	 * @param ss Sistema stellare di riferimento
	*/
	public static void percorso(SistemaStellare ss) {
		String memo = getCodice();
		String ris = ss.percorso(memo);
		System.out.println(ris);

	}
	/**
	 * Dati 2 codici di corpi esistenti restituisce se potrebbero collidere
	 * @param ss Sistema stellare di riferimento 
	*/
	public static void collisione(SistemaStellare ss) {
		String codiceA = getCodice();
		String codiceB = getCodice();
		boolean collidono = ss.collisione(codiceA, codiceB);
		if(collidono) System.out.println("I due corpi collideranno");
		else System.out.println("I due corpi non collideranno");
	}
	/**
	 * Dati 2 codici di corpi celesti esistenti visualizza la rotta necessaria
	 * partendo dal primo corpo, per raggiungere il secondo
	 * @param ss Sistema stellare di riferimento 
	*/
	public static void calcolaRotta(SistemaStellare ss) {
		System.out.println("Inserisci il codice del corpo celeste di partenza: ");
		String cPartenza = sc.nextLine();
		
		System.out.println("Inserisci il codice del corpo celeste di arrivo: ");
		String cArrivo = sc.nextLine();
		
		
		System.out.println("Rotta: " + ss.rotta(cPartenza, cArrivo, ss));
	}
	/**
	 * Dato il codice di un corpo dice se esiste, inoltre se è un satellite
	 * dice anche intorno a quale pianeta rotea
	 * @param ss Sistema stellare di riferimento
	*/
	public static void ricerca(SistemaStellare ss) { 
		System.out.print("Inserire il codice del corpo: ");
		String codice = sc.nextLine();
		boolean ris = ss.presenteCorpoCodice(codice);
		if(ris) {
			System.out.println(String.format("Il corpo con codice %s esite", codice));
			if(ss.getStringDaCodice(codice).equals("satellite")) {
				for(Pianeta pianeta:ss.getStella().getPianeti()) {
					if(pianeta.cercaSatellite(codice) != null) {
						System.out.println(String.format("Il satellite di codice %s "
								+ "rotea intorno al pianeta %s",codice,pianeta.getNome()));
					}
				}
			}
		}else {
			System.out.println(String.format("Il corpo con codice %s non esite", codice));
		}
	}
}