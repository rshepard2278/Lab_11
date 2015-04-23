/**
 * @author Richard Shepard
 * @version Apr 21, 2015
 */
package com.rshepard.pig;

import java.util.ArrayList;

public class StartInterFace {

	private GameFileManager gameManager;
	private ArrayList<Player> playerList;
	private ArrayList<Game> games;

	
	public StartInterFace() {
		gameManager = new GameFileManager();
		playerList = new ArrayList<>();
		games = new ArrayList<>();
		printSelection();
	}
	
	private void printSelection() {
		System.out.println("Pig Dice Game");
		System.out.println("=============================");
		System.out.println("\t'p' to play");
		System.out.println("\t'l' to load game");
		System.out.println("\t'q to quit");
		System.out.print("\nPlease Make a Selection: ");
		processInput(TextIO.getlnChar());
	}

	private void processInput(char input) {
		switch (input) {
		case 'p':
			System.out.println("Please input players names followed by a space");
			String player = "";
			while(!TextIO.eoln()) {
				player = TextIO.getWord();
				playerList.add(new Player(player));
			}
			new Game(playerList);
			break;
		case 'l':
			if(gameManager.checkForSavedGames()) {
				games = gameManager.getGames();
				printGames();
			} else {
				System.out.println("Saved games not found");
				printSelection();
			}
			break;
		case 'q':
			System.out.println("Thanks for playing....Goodbye");
			System.exit(0);
		default: 
			System.out.println("Apparently you don't know how to use a keyboard....Goodbye");
			System.exit(0);
		}
	}
	
	private void printGames() {
		Game selectedGame = null;
		System.out.println("\nGames:");
		System.out.println("===================");
		for(int i = 0; i < games.size(); i++) {
			selectedGame = games.get(i);
			System.out.println(i + ") " + selectedGame.getTitle() + " #" + selectedGame.getGameId());
		}
		System.out.print("\nPlease select a game to load");
		int selection = TextIO.getInt();
		if(selection >=0 && selection < games.size()) {
			games.get(selection).play();
		} else {
			System.out.println("Invalid Selection. Try again....");
			printGames();
		}
	}
	
}