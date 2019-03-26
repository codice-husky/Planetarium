package planetarium;
import java.util.Scanner;

class InputData {
	String nome;
	String codice;
	int peso;
	Punto coordinate;
	
	InputData(String testo, Scanner sc) {
		System.out.println("Inserisci il nome della stella: ");
		nome = sc.nextLine();
		
		System.out.println("Vuoi assegnare un codice manualmente? S/[N]: ");
		boolean codiceMan = sc.nextLine().equals("S")? true : false;
		if(codiceMan) {
			System.out.println("Inserisci il codice della stella: ");
			codice = sc.nextLine();
		} else codice = "SRND" + Math.floor(Math.random()*100000);
		
		System.out.println("Inserisci il peso della stella: ");
		peso = Integer.parseInt(sc.nextLine());
		
		System.out.println("Inserisci le coordinate della stella (x,y): ");
		int coordX = sc.nextInt();
		int coordY = sc.nextInt();
		coordinate = new Punto(coordX, coordY);
	}
}

public class Planetarium {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Inserisci il nome del sistema solare: ");
		String nome = sc.nextLine();
		SistemaStellare ss = new SistemaStellare(nome);
		String cmnd;
		System.out.println("Digita aiuto per ricevere aiuto");
		boolean end = false;
		while(end == false) {
			System.out.println("Che cosa vuoi fare? ");
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
		System.out.println("calcola rotta: per calcolare la rotta di un corpo celeste");
		System.out.println("collidono: per vedere se 2 corpi potrebbero collidere");
	}
	
	public static boolean aggiungiStella(SistemaStellare ss) {
		InputData inputStella = new InputData("la stella", sc);
		return ss.aggiungiStella(new Stella(inputStella.nome, inputStella.codice, inputStella.peso, inputStella.coordinate));
		
		
	}
	
	public static void aggiungiPianeta(SistemaStellare ss) {
		InputData inputPianeta = new InputData(" pianeta", sc);
		ss.getStella().aggiungiPianeta(new Pianeta(inputPianeta.nome, inputPianeta.codice, inputPianeta.peso, inputPianeta.coordinate));
	}
	
	public static void aggiungiSatellite(SistemaStellare ss) {
		if(ss.getStella() == null) {
			System.out.println("Bisogna prima aggiungere una stella!");
			return;
		} else if(ss.getStella().getNumPianeti() == 0) {
			System.out.println("Bisogna prima aggiungere almeno un pianeta!");
			return;
		} else {
			System.out.println("Inserisci il codice del pianeta: ");
			String codPianeta = sc.nextLine();
			Pianeta pianeta = ss.getStella().getPianeta(codPianeta);
			
			
			System.out.println("Inserisci il nome del satellite: ");
			String nome = sc.nextLine();
			String codice;
		
			System.out.println("Vuoi assegnare un codice manualmente? S/[N]: ");
			boolean codiceMan = sc.nextLine().equals("S")? true : false;
			if(codiceMan) {
				System.out.println("Inserisci il codice del saellite: ");
				codice = sc.nextLine();
			} else codice = "PRND" + Math.floor(Math.random()*100000);
			
			System.out.println("Inserisci il peso del pianeta: ");
			int peso = Integer.parseInt(sc.nextLine());
			
			System.out.println("Inserisci le coordinate del satellite (x,y): ");
			int coordX = sc.nextInt();
			int coordY = sc.nextInt();
			Punto punto = new Punto(coordX, coordY);
			
			pianeta.aggiungiSat(new Satellite(nome, codice, peso, punto));
		}
	}
}
