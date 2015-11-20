
package controller;

import java.awt.Color;
import desktop_codebehind.Car;
import desktop_fields.*;
import desktop_fields.Street;
import desktop_resources.GUI;
import entity.Field;
import entity.Dicebox;
import entity.Player;

public class GameController {
	
	Player spiller1 = new Player();
	Player spiller2 = new Player();
	
	Dicebox baeger = new Dicebox();
	
	Field[] felter = new Field[13];

	public static void main(String[] args) {
		new GameController().run();
	}

	public void run() {

		setupGame();

		setupGUI();

		while (true) {

			playerTurn(spiller1);

			if (spiller1.getBalance() >= 3000) {
				GUI.showMessage("spiller "+ spiller1.getName() +" vinder spillet med " + spiller1.getBalance() + "point");
				System.out.println("spiller "+spiller1.getName()+" vinder spillet med " + spiller1.getBalance() + "point");
				break;
			}
			else if (spiller2.isBankrupt()) {
				GUI.showMessage("spiller "+spiller1.getName()+" vinder spillet da spiller "+spiller2.getName()+" gik konkurs. ");
				System.out.println("spiller "+spiller1.getName()+" vinder spillet da spiller "+spiller2.getName()+" gik konkurs. ");
				break;
			}

			playerTurn(spiller2);

			if (spiller2.getBalance() >= 3000) {				
				GUI.showMessage("spiller "+ spiller2.getName() +" vinder spillet med " + spiller2.getBalance() + "point");
				System.out.println("spiller "+spiller2.getName()+" vinder spillet med " + spiller2.getBalance() + "point");

				
				break;
			}
			else if (spiller1.isBankrupt()) {
				GUI.showMessage("spiller "+spiller2.getName()+" vinder spillet da spiller "+spiller1.getName()+" gik konkurs. ");
				System.out.println("spiller "+spiller2.getName()+" vinder spillet da spiller "+spiller1.getName()+" gik konkurs. ");

				break;
			}
		}

	}

	private void playerTurn(Player spiller) {

		int slag = baeger.roll();

		int point = felter[slag].getPoint();
		String felt = felter[slag].getfeltNavn();
		boolean saldo_tjek = spiller.addToBalance(point);
		
		if(saldo_tjek == true){
			GUI.setBalance(spiller.getName(), spiller.getBalance());			
		}
		else{
			spiller.setBankrupt();
		}

		System.out.println("spiller" + spiller.getName() + "  har slået: " + slag + " han fik: " + point
				+ " og han har landet på felt: " + felt + ", saldo:" + spiller.getBalance());
		GUI.removeAllCars(spiller.getName());
		GUI.setCar(slag-1, spiller.getName());
		//Suspend excecution for 200 ms
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setupGame() {
		felter[2] = new Field("tower", 250);
		felter[3] = new Field("Crater", -100);
		felter[4] = new Field("Palace gates", 100);
		felter[5] = new Field("Cold Desert", -20);
		felter[6] = new Field("Walled city", 180);
		felter[7] = new Field("Monastery", 0);
		felter[8] = new Field("Black cave", -70);
		felter[9] = new Field("Huts in the mountain", 60);
		felter[10] = new Field("The werewall(werewolf-wall)", -80);
		felter[11] = new Field("The pit", -50);
		felter[12] = new Field("Goldmine", 650);

	}

	private void setupGUI() {

		Field[] fields = new Field[11];

		fields[0] = new Street.Builder().setBgColor(Color.gray).setTitle("tower").build();

		fields[1] = new Street.Builder().setBgColor(Color.blue).setTitle("Crater").build();

		fields[2] = new Street.Builder().setBgColor(Color.white).setTitle("Palace gates").build();
		fields[3] = new Street.Builder().setBgColor(Color.gray).setTitle("Cold Desert").build();
		fields[4] = new Street.Builder().setBgColor(Color.green).setTitle("Walled city").build();
		fields[5] = new Street.Builder().setBgColor(Color.orange).setTitle("Monastery").build();
		fields[6] = new Street.Builder().setBgColor(Color.DARK_GRAY).setTitle("Black cave").build();
		fields[7] = new Street.Builder().setBgColor(Color.magenta).setTitle("Huts in the mountain").build();
		fields[8] = new Street.Builder().setBgColor(Color.CYAN).setTitle("The werewall(werewolf-wall)").build();
		fields[9] = new Street.Builder().setBgColor(Color.blue).setTitle("The pit").build();
		fields[10] = new Street.Builder().setBgColor(Color.CYAN).setTitle("Goldmine").build();

		spiller1.setName(" Christian");
		spiller2.setName(" Ronni");

		GUI.create(fields);

		Car car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.RED)
			

		.build();
		GUI.addPlayer(spiller1.getName(), 1000, car);

		Car car2 = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.black)
				.build();
		GUI.addPlayer(spiller2.getName(), 1000, car2);
	}
}
