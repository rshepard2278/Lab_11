/**
 * @author Richard Shepard
 * @version Apr 19, 2015
 */
package com.rshepard.pig;

public class Test {

	/**
	 * 
	 */
	public Test() {
	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Game(new Player("Rick"), new Player("opponent")).play();
	}

}
