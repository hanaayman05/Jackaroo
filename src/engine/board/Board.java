package engine.board;

import java.util.*;

import model.Colour;
import engine.GameManager;

public class Board { // implementing gameManager interface
	
	private final GameManager gameManager;
	private final ArrayList<Cell> track;
	private final ArrayList<SafeZone> safeZones;
	private int splitDistance;
	
	public Board(ArrayList<Colour> colourOrder, GameManager gameManager)
	{
		this.gameManager=gameManager;
		track=new ArrayList<>();
		safeZones=new ArrayList<>();
		splitDistance=3;
		
		for(int i=0;i<100;i++) //set type of each cell
		{
			if(i==0 || i==25 || i==50 || i==75)
				track.add(new Cell(CellType.BASE));
			else
				if(i==23 || i==48 || i==73 || i==98)
					track.add(new Cell(CellType.ENTRY));
				else
					track.add(new Cell(CellType.NORMAL));
		}
		
		for(int i=0;i<8;i++) //creating 8 traps
		{
			 assignTrapCell() ;
		}
		
		for(int i=0;i<4;i++) //Create 4 SafeZones and add them to the safeZones with the given colour order.
		{
			safeZones.add(new SafeZone(colourOrder.get(i)));  //msh mt2kda
		}
	
		
	}
	public void assignTrapCell() 
	{
		Random random = new Random();
		int i=random.nextInt(100);
		if(track.get(i).getCellType()==CellType.NORMAL && !track.get(i).isTrap()) 
		{
			track.get(i).setTrap(true);
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
	public ArrayList<Cell> getTrack() {
		return track;
	}
	public ArrayList<SafeZone> getSafeZones() {
		return safeZones;
	}

}
