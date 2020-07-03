
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;

public class Block extends JComponent
{
    //private int value;
	private Color color;
	private int level; //number 0-7 used to indicate what "level" the block is, corresponds to a color 
	
	private Color[] sequence = new Color[] {Color.lightGray, Color.red, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue, Color.magenta, Color.white, Color.black,};
	
	public Block()
	{
		level=0;
		color = sequence[level];
	}
	
	public Block(int Level)
	{
		level = Level;
		color = sequence[Level];
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void levelUp()
	{
		level++;
		color = sequence[level];
	}
	
	public void setLevel(int Level)
	{
		level = Level;
		color = sequence[level];
	}

}