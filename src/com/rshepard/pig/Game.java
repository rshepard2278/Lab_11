/**
 * @author Richard Shepard
 * @version Apr 19, 2015
 */
package com.rshepard.pig;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
	
	private ArrayList<Player> players;
	
	private static final int MAX_SCORE = 100;

	/**
	 * 
	 */
	public Game(Player pOne, Player pTwo) {
		players = new ArrayList<>();
		pOne.setScore(0);
		pTwo.setScore(0);
		players.add(pOne);
		players.add(pTwo);
	}
	
	public Player play() {
		boolean hasWon = false;
		while(!hasWon) {
			roll();
			hasWon = checkWin();
		}
		return getWinner();
	}

	private void roll() {
		for(Player p : players) {
			int currentRoll = 0;
			int subtotal = 0;
			boolean hold = false;
			while(!hold) {
				System.out.println(p.getName() + "'s turn  Current Score: " + p.getScore());
				currentRoll = Dice.roll();
				if(currentRoll != 1) {
					System.out.println("Current Roll: " + currentRoll);
					System.out.println("Press \"h\" to hold or \"r\" to roll again");
					char answer = TextIO.getlnChar();
					if(answer == 'r') {
						subtotal += currentRoll;
						System.out.println("Running total: " + subtotal + " points");
					} else if(answer == 'h') {
						p.addToScore(subtotal);
						System.out.println("You chose to hold with a addition of " + subtotal + " for a grand total of " + p.getScore());
					}
				} else {
					System.out.println("You rolled a " + currentRoll + " and lose your turn.");
					
				}
			}
		}
	}
	
	private boolean checkWin() {
		boolean win = false;
		for(Player p : players) {
			if(p.getScore() > MAX_SCORE) {
				win = true;
			}
		}
		return win;
	}
	
	private Player getWinner() {
		Player winner = null;
		for(Player p : players) {
			if(p.getScore() > MAX_SCORE) {
				for(Player opp : players) {
					if(p.getScore() < opp.getScore()) {
						winner = p;
					}
				}
			}
		}
		return winner;
	}
}
