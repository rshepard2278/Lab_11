/**
 * @author Richard Shepard
 * @version Apr 21, 2015
 */
package com.rshepard.pig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class GameFileManager {

	private static final String SAVED_GAME_FILE = "savedGames";
	private static final String GAME_ID_FILE = "idBank";
	private ArrayList<Game> savedGames;
	private int gameIdBank;

	public GameFileManager() {
		try {
			loadGames();
			loadBank();
		} catch (FileNotFoundException e) {
			System.out.println("No previous games found. Creating saved Game files \"" + SAVED_GAME_FILE + "\"");
			e.printStackTrace();
		}
	}
	
	private void loadBank() {
		try {
			File source = new File(GAME_ID_FILE);
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(source));
			gameIdBank = (Integer) is.readObject();
			is.close();
		} catch (Exception e) {
			gameIdBank = 1000;
		}
	}

	private void loadGames() throws FileNotFoundException {
		try {
			File source = new File(SAVED_GAME_FILE);
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(source));
			savedGames = (ArrayList<Game>) is.readObject();
			is.close();
		} catch (Exception e) {
			savedGames = null;
		}
	}
	
	public boolean checkForSavedGames() {
		boolean hasGames = false;
		if(savedGames == null || savedGames.size() <= 0) {
			hasGames = false;
		} else {
			hasGames = true;
		}
		return hasGames;
	}
	
	public ArrayList<Game> getGames() {
		return savedGames;
	}

	public void saveToFile(Game game) throws IOException {
		if(savedGames == null) {
			savedGames = new ArrayList<>();
		}
		if(game.getGameId() == 0) {
			game.setGameId(gameIdBank);
			gameIdBank++;
			saveIdToFile();
			savedGames.add(game);
		} else {
			for(int i = 0; i < savedGames.size(); i++) {
				if(game.getGameId() == savedGames.get(i).getGameId()){
					savedGames.remove(i);
					savedGames.add(game);
				}
			}
		}
		System.out.println("Saving Game: " + game.getTitle());
		save();
		new StartInterFace();
	}
	
	private void save() throws IOException {
		File destination = new File(SAVED_GAME_FILE);
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
				destination));
		os.writeObject(savedGames);
		os.close();
	}
	
	public void saveIdToFile() throws IOException {
		File destination = new File(GAME_ID_FILE);
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
				destination));
		os.writeObject(gameIdBank);
		os.close();
	}

	public void printGames() {
		if(savedGames != null) {
			for(Game g : savedGames) {
				System.out.println(g.getTitle() + " ID# " + g.getGameId());
			}
		} else {
			System.out.println("SavedGames == null");
		}
	}
	
	public void deleteGame(Game game) {
		for(int i = 0; i < savedGames.size(); i++) {
			System.out.println("Checking for match " + game.getGameId() + " and " + savedGames.get(i).getGameId());
			if(game.getGameId() == savedGames.get(i).getGameId()) {
				savedGames.remove(i);
			}
		}
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new StartInterFace();
	}

}
