package planetarium;
import java.util.Scanner;

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
	
	public static void aggiungiStella(SistemaStellare ss) {
		System.out.println("Inserisci il nome della stella: ");
		String nome = sc.nextLine();
		String codice;
		
		System.out.println("Vuoi assegnare un codice manualmente? S/[N]: ");
		boolean codiceMan = sc.nextLine().equals("S")? true : false;
		if(codiceMan) {
			System.out.println("Inserisci il codice della stella: ");
			codice = sc.nextLine();
		} else codice = "RND" + Math.floor(Math.random()*100000);
		
		System.out.println("Inserisci il peso della stella: ");
		int peso = Integer.parseInt(sc.nextLine());
		
		System.out.println("Inserisci le coordinate della stella (x,y): ");
		int coordX = sc.nextInt();
		int coordY = sc.nextInt();
		Punto punto = new Punto(coordX, coordY);
		
		ss.aggiungiStella(new Stella(nome, codice, peso, punto));
		
		
	}
}
