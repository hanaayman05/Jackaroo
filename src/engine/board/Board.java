package engine.board;

import java.util.*;

import model.Colour;
import engine.GameManager;

public class Board { // implementing gameManager interface
	
	private final GameManager gameManager;
	private final Cell[] track;
	private final SafeZone[] safeZones;
	private int splitDistance;
	
	public Board(ArrayList<Colour> colourOrder, GameManager gameManager)
	{
		this.gameManager=gameManager;
		track=new Cell[100];
		safeZones=new SafeZone[4];
		splitDistance=3;
		for(int i=0;i<100;i++) //set type of each cell
		{
			if(i==0 || i==25 || i==50 || i==75)
				track[i]=new Cell(CellType.BASE);
			else
				if(i==23 || i==48 || i==73 || i==98)
					track[i]=new Cell(CellType.ENTRY);
				else
					track[i]=new Cell(CellType.NORMAL);
		}
		
		for(int i=0;i<8;i++) //creating 8 traps
		{
			 assignTrapCell() ;
		}
		
		for(int i=0;i<4;i++) //Create 4 SafeZones and add them to the safeZones with the given colour order.
		{
			safeZones[i]=new SafeZone(colourOrder.get(i));  //msh mt2kda
		}
	
		
	}
	void assignTrapCell() //don't know if leave default access modifier or set it to public
	{
		Random random = new Random();
		int i=random.nextInt(100);
		if(track[i].getCellType()==CellType.NORMAL && !track[i].isTrap()) //msh mot2kda mn el compare
		{
			track[i].setTrap(true);
			return;
		}
		else
		{
			assignTrapCell();
		}
	}
	public int getSplitDistance() { // READ (through a relevant interface method): mfhmtsh el kalam dah 
		return splitDistance;
	}
	public void setSplitDistance(int splitDistance) {
		this.splitDistance = splitDistance;
	}
	public Cell[] getTrack() {
		return track;
	}
	public SafeZone[] getSafeZones() {
		return safeZones;
	}

}
