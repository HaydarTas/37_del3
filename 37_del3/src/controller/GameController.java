
package controller;

import java.awt.Color;
import desktop_codebehind.Car;
//import desktop_fields.*;
import desktop_fields.Street;
import desktop_resources.GUI;
import entity.*;


public class GameController {
	
	Player player1 = new Player();
	Player player2 = new Player();
	
	Dicebox box = new Dicebox();
	
	Field[] field = new Field[22];

	public static void main(String[] args) {
		new GameController().run();
	}

	public void run() {
		setupGame();
		setupGUI();
		
		while (true) {
			playerTurn(player1);
			if (player1.getBalance() >= 3000) {
				GUI.showMessage("spiller "+ player1.getName() +" vinder spillet med " + player1.getBalance() + "point");
				System.out.println("spiller "+player1.getName()+" vinder spillet med " + player1.getBalance() + "point");
				break;
			}
			else if (player2.isBankrupt()) {
				GUI.showMessage("spiller "+player1.getName()+" vinder spillet da spiller "+player2.getName()+" gik konkurs. ");
				System.out.println("spiller "+player1.getName()+" vinder spillet da spiller "+player2.getName()+" gik konkurs. ");
				break;
			}

			playerTurn(player2);
			if (player2.getBalance() >= 3000) {				
				GUI.showMessage("spiller "+ player2.getName() +" vinder spillet med " + player2.getBalance() + "point");
				System.out.println("spiller "+player2.getName()+" vinder spillet med " + player2.getBalance() + "point");

				
				break;
			}
			else if (player1.isBankrupt()) {
				GUI.showMessage("spiller "+player2.getName()+" vinder spillet da spiller "+player1.getName()+" gik konkurs. ");
				System.out.println("spiller "+player2.getName()+" vinder spillet da spiller "+player1.getName()+" gik konkurs. ");

				break;
			}
		}

	}

	private void playerTurn(Player player) {
		int roll = box.roll();
		int points = field[roll].getPoints();
		String fieldname = field[roll].getName();
		boolean check_account = player.addToBalance(points);
		
		if(check_account == true){
			GUI.setBalance(player.getName(), player.getBalance());			
		}
		else{
			player.setBankrupt();
		}

		System.out.println("spiller" + player.getName() + "  har slÃ¥et: " + roll + " han fik: " + points
				+ " og han har landet på felt: " + field + ", saldo:" + player.getBalance());
		GUI.removeAllCars(player.getName());
		GUI.setCar(roll-1, player.getName());
		//Suspend excecution for 200 ms
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void setupGame() {
		field[0] = new Territory("Tribe encampment", 250);
		field[1] = new Territory("Crater", 250);
		field[2] = new Territory("Mountain", -100);
		field[3] = new Territory("Cold desert", 100);
		field[4] = new Territory("Black cave", -20);
		field[5] = new Territory("The werewall", 180);
		field[6] = new Territory("Mountain village", 0);
		field[7] = new Territory("South Citadel", -70);
		field[8] = new Territory("Palads gates", 60);
		field[9] = new Territory("Tower", -80);
		field[10] = new Territory("Castle", -50);
		field[11] = new Refuge("Walled city", 650);
		field[12] = new Refuge("Monastery", 0);
		field[13] = new LaborCamp("Huts in the mountain", 0);
		field[14] = new LaborCamp("The pit", 0);
		field[15] = new Tax("Goldmine", 0);
		field[16] = new Tax("Caravan", 0);
		field[17] = new Fleet("Second Sail", 0);
		field[18] = new Fleet("Sea Grover", 0);
		field[19] = new Fleet("The Buccanees", 0);
		field[20] = new Fleet("Privateer armade", 0);

	}

	private void setupGUI() {

		desktop_fields.Field[] fields = new desktop_fields.Field[22];

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

		player1.setName("Christian");
		player2.setName("Ronni");

		GUI.create(fields);

		Car car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.RED)
			

		.build();
		GUI.addPlayer(player1.getName(), 1000, car);

		Car car2 = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.black)
				.build();
		GUI.addPlayer(player2.getName(), 1000, car2);
	}
}
