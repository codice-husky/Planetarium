package planetarium;
import java.util.ArrayList;
import java.util.Scanner;

class InputData {
	String nome;
	String codice;
	int peso;
	Punto coordinate;
	
	InputData(String testo, Scanner sc) {
		System.out.println("Inserisci il nome del"+testo+": ");
		nome = sc.nextLine();
		
		System.out.println("Vuoi assegnare un codice manualmente? S/[N]: ");
		boolean codiceMan = sc.nextLine().equals("S")? true : false;
		if(codiceMan) {
			System.out.println("Inserisci il codice del"+testo+": ");
			codice = sc.nextLine();
		} else codice = "SRND" + Math.floor(Math.random()*100000);
		
		System.out.println("Inserisci il peso del "+testo +": ");
		peso = Integer.parseInt(sc.nextLine());
		
		System.out.println("Inserisci le coordinate del"+ testo+ " (x y): ");
		int coordX = sc.nextInt();
		int coordY = sc.nextInt();
		sc.nextLine();
		coordinate = new Punto(coordX, coordY);
	}
}

public class Planetarium {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Inserisci il nome del sistema solare: ");
		String nome = sc.nextLine();
		SistemaStellare ss = new SistemaStellare(nome);
		//CREAZIONE AUTOMATICA STELLA, PIANETA, SATELLITE
		System.out.println("SONO STATI CREATI AUTOMATICAMENTE UNA STELLA"
				+ " 2 PIANETI E 3 SATELLITI ");
		ss.aggiungiStella(new Stella("Sole","0001",30,new Punto(0,0)));
		ss.stella.aggiungiPianeta(new Pianeta("Pianeta 1","0002",5,new Punto(0,-3)));
		ss.stella.aggiungiPianeta(new Pianeta("Pianeta 2","0003",7,new Punto(3,3)));
		ss.stella.cercaPianeta("0002").aggiungiSatellite(new Satellite("Luna 1","0004",1,new Punto(-1,-4)));
		ss.stella.cercaPianeta("0003").aggiungiSatellite(new Satellite("Luna 2","0005",2,new Punto(2,3)));
		ss.stella.cercaPianeta("0003").aggiungiSatellite(new Satellite("Luna 3","0006",1,new Punto(4,4)));
		//--------------------------------------
		String cmnd;
		System.out.println("Digita aiuto per ricevere aiuto");
		boolean end = false;
		while(end == false) {
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
			case "percorso":
				String memo = getCodice();
				String ris = ss.percorso(memo);
				System.out.println(ris);
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
			default:
				System.out.println("Comando non riconosciuto!");
				break;
			}
		}
		
		sc.close();
	}
	/**
     * @return
     */
	
	public static void aiuto() {
		System.out.println("aiuto: per ricevere aiuto");
		System.out.println("aggiungi stella: per aggiungere una nuova stella");
		System.out.println("rimuovi stella: per rimuovere una stella");
		System.out.println("aggiungi pianeta: per aggiungere un pianeta");
		System.out.println("rimuovi pianeta: per rimuovere un pianeta");
		System.out.println("aggiungi satellite: per aggiungere un satellite");
		System.out.println("rimuovi satellite: per rimuovere un satellite");
		System.out.println("calcola rotta: per calcolare la rotta tra 2 corpi celesti");
		System.out.println("collidono: per vedere se 2 corpi potrebbero collidere");
	}
	
	public static boolean aggiungiStella(SistemaStellare ss) {
		InputData inputStella = new InputData("la stella", sc);
		return ss.aggiungiStella(new Stella(inputStella.nome, inputStella.codice, inputStella.peso, inputStella.coordinate));
	}
	
	public static boolean aggiungiPianeta(SistemaStellare ss) {
		if(ss.getStella() == null) {
			System.out.println("Bisogna prima aggiungere una stella!");
			return false;
		} else {		
			InputData inputPianeta = new InputData(" pianeta", sc);
			while(ss.presenteCorpo(inputPianeta.codice)==true) {
				System.out.println("\nE' gia presente corpo celeste con quel codice!\n");
				inputPianeta = new InputData(" pianeta", sc);
			}
			return ss.getStella().aggiungiPianeta(new Pianeta(inputPianeta.nome, inputPianeta.codice, inputPianeta.peso, inputPianeta.coordinate));
		}
	}
	
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
			InputData inputSatellite = new InputData(" satellite", sc);			
			while(ss.presenteCorpo(inputSatellite.codice) == true) {
				System.out.println("\nE' gia presente corpo celeste con quel codice!\n");
				inputSatellite = new InputData(" pianeta", sc);
			}	
			return pianeta.aggiungiSatellite(new Satellite(inputSatellite.nome, inputSatellite.codice, inputSatellite.peso, inputSatellite.coordinate));
		}
	}
	
	public static String getCodice() {
		System.out.println("Inserisci il codice: ");
		return (sc.nextLine());
	}
	
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
	
	public static void centroMassa(SistemaStellare ss) {
		Punto centro = ss.calcolaMassa();
		String x = ""+(Math.round(centro.getX()*1000.0)/1000.0);
		String y = ""+(Math.round(centro.getY()*1000.0)/1000.0);
		System.out.println(String.format("Le cordinate del centro di massa sono (%s, %s)",x,y));
	}
	
	public static void schedaCorpo(SistemaStellare ss) {
		String codice = getCodice();
		if(ss.presenteCorpo(codice)) {
			
		}else {
			System.out.println("Non esiste un corpo celeste con quel nome");
		}
	}
}
