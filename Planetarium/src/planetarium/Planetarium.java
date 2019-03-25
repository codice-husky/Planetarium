package planetarium;
import java.util.Scanner;

public class Planetarium {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Inserisci il nome del sistema solare: ");
		String nome = sc.nextLine();
		Sistema_Stellare ss = new Sistema_Stellare(nome);
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
			}
		}
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
}
