/**
 * @author Richard Shepard
 * @version Apr 19, 2015
 */
package com.rshepard.pig;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedGames implements Serializable {

	private ArrayList<Game> savedGames;
	
	public SavedGames() {
		savedGames = new ArrayList<>();
	}
	
	public Game getGame(Game game) {
		return savedGames.get(savedGames.indexOf(game));
	}
	
	public void addGame(Game game) {
		savedGames.add(game);
	}
	
	public ArrayList<Game> listGames() {
		return savedGames;
	}

}
