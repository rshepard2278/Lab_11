/**
 * @author Richard Shepard
 * @version Apr 19, 2015
 */
package com.rshepard.pig;

import java.io.Serializable;

public class Dice implements Serializable {

	/**
	 * 
	 */
	public Dice() {
		
	}
	
	public static int roll() {
		return (int)(Math.random() * 6) + 1;
	}

}
