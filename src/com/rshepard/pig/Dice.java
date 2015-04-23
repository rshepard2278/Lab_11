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
	private static final long serialVersionUID = -3169607304493447940L;

	/**
	 * 
	 */
	public Dice() {
		
	}
	
	public static int roll() {
		return (int)(Math.random() * 6) + 1;
	}

}
