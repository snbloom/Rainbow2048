
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Board extends JComponent
{
    private Block[][] game;
    
    private final int x = 85;
    private final int y = 85;
    private final int width = 600;
    
    private final int sepDist = 7;
    private int blockSize;
    
	
	public Board()
	{
		game = new Block[4][4];
		blockSize =((width-(sepDist*(game.length+1)))/game.length);
		for(int i=0; i<game.length; i++)
			for(int j=0; j<game[0].length; j++)
			{
				game[i][j] = new Block();
			}

	}
	
	public void paintComponent(Graphics g)
	{
    	blockSize =((width-(sepDist*(game.length+1)))/game.length);
    	
    	//drawing the board background
    	Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.fillRect(x,y,width,width);
       
       	//filling in the colors of the blocks
        for(int i=0; i<game.length; i++)
        {
        	for(int j=0; j<game[0].length; j++)
        	{
        		g2.setColor(game[j][i].getColor());
        		g2.fillRect((blockSize*i)+x+((i+1)*sepDist),(blockSize*j)+y+((j+1)*sepDist), blockSize, blockSize);
        	}
        }
	}
	
	public Block[][] getGame()
	{
		return game;
	}
	
	public Block getBlock(int r, int c)
	{
		return game[r][c];
	}
		
	public Block[] getColumn(int x)
	{
		 Block [] col = new Block[game.length];
		 for(int i=0; i<game.length;i++)
		 {
		 	col[i] = game[i][x];
		 }
		 
		 return col;
	}
	
	public void setBlock(int r, int c, int level)
	{
		game[r][c].setLevel(level);
	}
	
	public Board makeCopy()
    {
         	Board copy = new Board();
         	for(int i=0; i<game.length; i++)
     			for(int j=0; j<game[0].length; j++)
     			{
     				copy.setBlock(i, j, game[i][j].getLevel());
     			}
     			
     		return copy;
     }
     
     public boolean compare(Board test)
     {
     	boolean same = true;

		for(int i=0; i<test.getGame().length; i++)
			for(int j=0; j<test.getGame()[0].length; j++)
			{
				if(test.getGame()[i][j].getLevel()!=getGame()[i][j].getLevel())
					same = false;
			}
			
		return same;
     }
	

	//GAME METHODS!!!
	public boolean gameOverWin()
	{
		boolean win = false;
		for(int i=0; i<game.length; i++)
			for(int j=0; j<game.length; j++)
				if(game[i][j].getLevel()==9)
					win=true;
		return win;
	}
	public boolean gameOverLose()  
	{
		boolean done = true;
		for(int i=0; i<game.length; i++)
			for(int j=0; j<game[0].length; j++)
			{
				if(game[i][j].getLevel()==0)
					done = false;
			}
				
		if(done==true)
		{
			Board copy1 = this.makeCopy();
			Board copy2 = this.makeCopy();
			
			copy2.makeMove("u");
			if(!copy1.compare(copy2))
				return false;
				
			copy2.makeMove("d");
			if(!copy1.compare(copy2))
				return false;
			
			copy2.makeMove("r");	
			if(!copy1.compare(copy2))
				return false;
				
			copy2.makeMove("l");
			if(!copy1.compare(copy2))
				return false;
		}	
		
		return done;			
	}
	
	public void randomMove() //DONE
	{
		boolean done = gameOverLose();
		int r = (int)(Math.random()*4);
		int c = (int)(Math.random()*4);
		double prob = Math.random();
		
		if(done==false)
		{
			while(game[r][c].getLevel()!= 0)
			{
				r = (int)(Math.random()*4);
				c = (int)(Math.random()*4);
			}
			if(prob<= .3)
				game[r][c].setLevel(2);
			
			if(prob>.3)
				game[r][c].setLevel(1);
		}	
			
	}
	
	public void mergeRow(Block[] row, String direction)  //DONE! WHOOP WHOOP
	{
		int test;
		
		//merging in right/down direction
		if(direction=="r"||direction=="d")
		{
			test = row[row.length-1].getLevel();
			for(int i=row.length-2; i>=0; i--)
			{
				if(test!=0 && test==row[i].getLevel())
				{
					row[i+1].levelUp();
					for(int j=i; j>0; j--)
					{
						row[j].setLevel(row[j-1].getLevel());
					}
					row[0].setLevel(0);

				}
			test = row[i].getLevel();
			}
		}
		
		//merging in left/up direction
		if(direction=="l"||direction=="u")
		{
			test = row[0].getLevel();
			for(int i=1; i<row.length; i++)
			{
				if(test!=0 && test==row[i].getLevel())
				{
					row[i-1].levelUp();
					for(int j=i; j<row.length-1; j++)
						row[j].setLevel(row[j+1].getLevel());
					row[row.length-1].setLevel(0);
				}
				test = row[i].getLevel();
			}
		}
		
	}

	/*
	 *input: direction of merge
	 *
	 *modifies data in the 2d array to merge all blocks 
	 *of the same level for all rows/columns in the array
	 *
	 *output: none
	 */
	public void mergeAll(String direction)
	{
		if(direction=="u"||direction=="d")
		{
			for(int i=0; i<game.length; i++)
				mergeRow(getColumn(i), direction);
		}
			
		else if(direction=="r"||direction=="l")
		{
			for(int j=0; j<game.length; j++)
				mergeRow(game[j], direction);
		}
		
	}
	
	
	public void moveRow(Block[] row, String direction)	//DONE!!! FINALLY!!
	{
		if(direction=="r"||direction=="d")
		{
			int counter =0;
			for(int i=row.length-1; i>=0; i--)
			{
				if(row[i].getLevel()==0)
				{
					for(int j=i; j>0; j--)
						{row[j].setLevel(row[j-1].getLevel());}
					row[0].setLevel(0);
					counter++;
					if(counter<5)
						i++;
				}
			}
		}
		else if(direction=="l"||direction=="u")
		{
			int counter = 0;
			for(int i=0; i<row.length; i++)
			{
				if(row[i].getLevel()==0)
				{
					for(int j=i; j<row.length-1; j++)
						{row[j].setLevel(row[j+1].getLevel());}
					row[row.length-1].setLevel(0);
					counter++;
					if(counter<5)
						i--;
				}		
					
			}
		}
	}
	
	public void moveAll(String direction)
	{
		if(direction=="u"||direction=="d")
			for(int i=0; i<game.length; i++)
				moveRow(getColumn(i), direction);
		else if(direction=="r"||direction=="l")
			for(int j=0; j<game.length; j++)
				moveRow(game[j], direction);
	}
	
	
	public void makeMove(String direction)
	{
		moveAll(direction);
		mergeAll(direction);
	}
	


}