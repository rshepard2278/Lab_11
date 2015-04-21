/**
 * @author Richard Shepard
 * @version Apr 19, 2015
 */
package com.rshepard.pig;

import java.io.Serializable;

public class Player implements Serializable {
	
	private String name;
	private int score;

	/**
	 * 
	 */
	public Player(String name) {
		this.name = name;
		score = 0;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	

	/**
	 * @param score the score to set
	 */
	public void addToScore(int score) {
		this.score += score;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
