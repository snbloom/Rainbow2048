 /*
 *AP COMP SCI FINAL PROJECT
 *By: Sarah Bloom
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

 
public class GameRunner
{
	
	public static void main (String args[])
	{
		
		//CREATING THE FRAME AND KEYLISTENER FOR THE GAME
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    	frame.setTitle("Rainbow 2048");
    	frame.setSize(800,800);
 
		//CREATING THE BOARD OBJECT FOR THE GAME
		Board board = new Board();
		frame.add(board);
		frame.setVisible(true);
		MyKeyListener keyListener = new MyKeyListener(board);
		frame.addKeyListener(keyListener);
			
			
		board.randomMove();
		
		//THE GAME LOOP	
		boolean gameOverLose = board.gameOverLose();
		boolean gameOverWin = board.gameOverWin();
		while(gameOverLose==false&&gameOverWin==false)
		{
			gameOverLose = board.gameOverLose();
			gameOverWin = board.gameOverWin();
		}
		frame.removeKeyListener(keyListener);
		if(gameOverLose)
			JOptionPane.showMessageDialog(null,"GAME OVER: YOU LOSE");
		else if(gameOverWin)
			JOptionPane.showMessageDialog(null, "GAME OVER: YOU WIN");
		

		System.out.println("ran through entire program");
	}
}