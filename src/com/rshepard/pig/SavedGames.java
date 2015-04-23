/**
 * @author Richard Shepard
 * @version Apr 19, 2015
 */
package com.rshepard.pig;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedGames implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Game> savedGames;
	
	public SavedGames() {
		savedGames = new ArrayList<>();
	}
	
	public void saveGame(Game game) {
		savedGames.add(game);
	}
	
	public ArrayList<Game> getGames() {
		return savedGames;
	}

}
