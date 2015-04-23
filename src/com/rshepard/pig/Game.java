/**
 * @author Richard Shepard
 * @version Apr 19, 2015
 */
package com.rshepard.pig;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5649245674384133623L;
	private ArrayList<Player> players;
	private String title;
	private int gameId;

	private static final int MAX_SCORE = 100;

	/**
	 * 
	 */
	public Game(ArrayList<Player> players) {
		this.players = players;
		Player name = null;
		gameId = 0;
		title = "";
		for (int i = 0; i < players.size(); i++) {
			name = players.get(i);
			if (i != 0) {
				title += " vs. " + name.getName();
			} else {
				title += name.getName();
			}
		}
		System.out.println("Game Title " + title);
		play();
	}

	public void play() {
		boolean hasWon = false;
		while (!hasWon) {
			roll();
			hasWon = checkWin();
		}
		System.out.println(getWinner().getName().toUpperCase() + " has Won");
		new GameFileManager().deleteGame(this);
	}

	private void roll() {
		for (Player p : players) {
			int currentRoll = 0;
			int subtotal = 0;
			boolean hold = false;
			while (!hold) {
				for(Player pScores : players) {
					System.out.println(pScores.getName() + ": " + pScores.getScore());
				}
				currentRoll = Dice.roll();
				if (currentRoll != 1) {
					subtotal += currentRoll;
					System.out.println(p.getName().toUpperCase() + ":");
					System.out.println("\tCurrent Roll: " + currentRoll);
					System.out.println("\tRunning Total: " + subtotal);
					System.out.println("\tPress \"h\" to hold and keep " + subtotal);
					System.out.println("\tPress \"r\" to roll again");
					System.out.println("\tPress \"s\" to quit and save ");
					System.out.print("\n\tPlease make a Selection");
					switch (TextIO.getlnChar()) {
					case 'r':
						System.out.println("Running total: " + subtotal
								+ " points");
						break;
					case 'h':
						p.addToScore(subtotal);
						System.out.println("You chose to hold with a addition of " + subtotal + " for a grand total of "
										+ p.getScore());
						hold = true;
						break;
					case 's':
						try {
							new GameFileManager().saveToFile(this);
						} catch (IOException e) {
							System.out.println("For some reason game was not saved");
							e.printStackTrace();
						}
						break;
					}
				} else {
					System.out.println(p.getName().toUpperCase() + " rolled a " + currentRoll);
					System.out.println("You lose your turn!!!");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					hold = true;
				}
			}
		}
	}

	private boolean checkWin() {
		boolean win = false;
		for (Player p : players) {
			if (p.getScore() >= MAX_SCORE) {
				win = true;
			}
		}
		return win;
	}

	private Player getWinner() {
		Player winner = null;
		int winCount = 0;
		for (Player p : players) {
			if (p.getScore() > MAX_SCORE) {
				winner = p;
				winCount++;
			}
		}
		if (winCount > 1) {
			winner = getCloserToTarget();
		}
		return winner;
	}

	private Player getCloserToTarget() {
		int highScore = 107;
		Player winner = null;
		for (Player p : players) {
			if (p.getScore() >= MAX_SCORE && p.getScore() < highScore) {
				highScore = p.getScore();
				winner = p;
			}
		}
		return winner;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the gameId
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

}
