 /**
 * @(#)MyKeyListener.java
 *
 *
 * @author 
 * @version 1.00 2018/5/19
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import java.util.Arrays;

public class MyKeyListener implements KeyListener{

    	Board board;
    	
    	public MyKeyListener(Board b){
    		board =b;
    	}
  
		/*input: KeyEvent
		 *based on which arrow key was pressed, calls corresponding arrow key method
		 *output: none
		 */
        public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_RIGHT ) 
			{
 	           rightKey();
 	           board.repaint();
			} 
		 	else if (e.getKeyCode() == KeyEvent.VK_LEFT ) 
		 	{
          	   leftKey();
          	   board.repaint();
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) 
    		{
          		upKey();
          		board.repaint();
   			} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) 
    		{
           		downKey();
           		board.repaint();
    		}
		}
		 /*
		  *method that has to be implemented from the KeyListener interface. Serves no purpose.
		  */
		 @Override
         public void keyTyped(KeyEvent e) {
         }
		
		 /*
		  *method that has to be implemented from the KeyListener interface. Serves no purpose.
		  */
         @Override
         public void keyReleased(KeyEvent e) {
         }
         
         /*input:none
          *makes a move for the user by moving and merging all Blocks in the Board to the right. If a valid move was made
          *and data was changed as a result, makes a random low-level tile appear on the board
          *output:none
          */
         public void rightKey(){
         	Board copy = board.makeCopy();
        	board.makeMove("r");
         	if(!board.compare(copy))
         		board.randomMove();
         }
         
         /*input:none
          *makes a move for the user by moving and merging all Blocks in the Board to the left. If a valid move was made
          *and data was changed as a result, makes a random low-level tile appear on the board
          *output:none
          */
         public void leftKey(){
         	Board copy = board.makeCopy();
         	board.makeMove("l");
         	if(!board.compare(copy))
         		board.randomMove();
         }
         
         /*input:none
          *makes a move for the user by moving and merging all Blocks in the Board up. If a valid move was made
          *and data was changed as a result, makes a random low-level tile appear on the board
          *output:none
          */
         public void upKey(){
         	Board copy = board.makeCopy();
         	board.makeMove("u");
         	if(!board.compare(copy))
         		board.randomMove();
         }
         
         /*input:none
          *makes a move for the user by moving and merging all Blocks in the Board down. If a valid move was made
          *and data was changed as a result, makes a random low-level tile appear on the board
          *output:none
          */
         public void downKey(){
         	Board copy = board.makeCopy();
         	board.makeMove("d");
         	if(!board.compare(copy))
         		board.randomMove();

         }
    
	}