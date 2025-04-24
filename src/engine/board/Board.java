package engine.board;

import java.util.ArrayList;

import engine.GameManager;
import exception.CannotFieldException;
import exception.IllegalDestroyException;
import exception.IllegalMovementException;
import exception.IllegalSwapException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;


@SuppressWarnings("unused")
public class Board implements BoardManager {
    private final ArrayList<Cell> track;
    private final ArrayList<SafeZone> safeZones;
	private final GameManager gameManager;
    private int splitDistance;

    public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
        this.track = new ArrayList<>();
        this.safeZones = new ArrayList<>();
        this.gameManager = gameManager;
        
        for (int i = 0; i < 100; i++) {
            this.track.add(new Cell(CellType.NORMAL));
            
            if (i % 25 == 0) 
                this.track.get(i).setCellType(CellType.BASE);
            
            else if ((i+2) % 25 == 0) 
                this.track.get(i).setCellType(CellType.ENTRY);
        }

        for(int i = 0; i < 8; i++)
            this.assignTrapCell();

        for (int i = 0; i < 4; i++)
            this.safeZones.add(new SafeZone(colourOrder.get(i)));

        splitDistance = 3;
    }

    public ArrayList<Cell> getTrack() {
        return this.track;
    }

    public ArrayList<SafeZone> getSafeZones() {
        return this.safeZones;
    }
    
    @Override
    public int getSplitDistance() {
        return this.splitDistance;
    }

    public void setSplitDistance(int splitDistance) {
        this.splitDistance = splitDistance;
    }
   
    private void assignTrapCell() {
        int randIndex = -1;
        
        do
            randIndex = (int)(Math.random() * 100); 
        while(this.track.get(randIndex).getCellType() != CellType.NORMAL || this.track.get(randIndex).isTrap());
        
        this.track.get(randIndex).setTrap(true);
    }
    

private ArrayList<Cell> getSafeZone(Colour colour)
{
	for(int i=0;i<safeZones.size();i++)
	{
		SafeZone tmp=safeZones.get(i);
		if(tmp.getColour()==colour)
			return tmp.getCells();
	}
	return null;
}

private int getPositionInPath(ArrayList<Cell> path, Marble marble)
{
	for(int i=0;i<path.size();i++)
	{
		Cell tmp=path.get(i);
		if(tmp.getMarble()!=null && tmp.getMarble()==marble)
			return i;
	}
	return -1;
}

private int getBasePosition(Colour colour)
{
//	if(this.safeZones.get(0).getColour()==colour)
//		return 0;
//	if(this.safeZones.get(1).getColour()==colour)
//		return 25;
//	if(this.safeZones.get(2).getColour()==colour)
//		return 50;
//	if(this.safeZones.get(0).getColour()==colour)
//		return 75;
//	return -1;
	for(int i=0; i<4; i++) if(safeZones.get(i).getColour()==colour) return i*25;
	
	return -1;
}

private int getEntryPosition(Colour colour)
{
//	if(this.safeZones.get(0).getColour()==colour)
//		return 98;   //it was 99 which is wrong
//	if(this.safeZones.get(1).getColour()==colour)
//		return 23;
//	if(this.safeZones.get(2).getColour()==colour)
//		return 48;
//	if(this.safeZones.get(3).getColour()==colour) // 3 was 1
//		return 73;
//	return -1;
	int basePosition = getBasePosition(colour);
	if(basePosition==-1) return -1;
	return (basePosition - 2 + 100)%100;
}

private ArrayList<Cell> validateSteps(Marble marble, int steps) throws
IllegalMovementException
{
	
	boolean onTrack=true;
	int pos=this.getPositionInPath(this.track, marble);
	if(pos==-1)
	{
		pos=this.getPositionInPath(this.getSafeZone(marble.getColour()), marble);
		onTrack=false;
	}
	if (pos==-1)
		throw  new IllegalMovementException("Marble cannot be moved");
	
	int EntryPos=this.getEntryPosition(marble.getColour());
	ArrayList<Cell> safeZone=this.getSafeZone(marble.getColour());
	ArrayList<Cell> path=new ArrayList<Cell>();
	
	if(onTrack)
	{
		if((this.gameManager.getActivePlayerColour()==marble.getColour()) && (pos+steps-EntryPos)>4 ) // make sure that we are moving our marble
		{
			throw new IllegalMovementException("Rank is too High");
		}
		
		if(steps==-4)
		{
			for(int i=0;i<=4;i++) //i=1
			{
				int index = (pos-i+100)%100;
				path.add(track.get(index));
			}
		}
		else
		{
			if(this.gameManager.getActivePlayerColour()!=marble.getColour())
    		{
    			
    			for(int i=0;i<=steps;i++) //i=1
    			{
    				int nextPos=(pos+i)%100;
    				path.add(track.get(nextPos));
    			}
    		}
			else
			{
				for(int i=0;i<=steps;i++) //i=1
    			{
    				int nextPos=pos+i;
    				if(nextPos<=EntryPos)
    				{
    					path.add(track.get(nextPos));
    				}
    				else
    				{
    					int safeZoneIndex=nextPos-EntryPos-1;
    					path.add(safeZone.get(safeZoneIndex));
    				}
    			}
			}
		}	
	}
	else
	{
		if(steps==-4)
			throw new IllegalMovementException("Moving Backwards in a SafeZone is Prohibted");
		if(pos+steps>3)
			throw new IllegalMovementException("Rank is too High");
		else
		{
			for(int i=0;i<=steps;i++) //i=1
			{
				int newPos=pos+i;
				path.add(safeZone.get(newPos));
			}
		}
	}
	
	return path;
}

//private void validatePath(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalMovementException
//{
//	for(int i=1;i<fullPath.size();i++)  // i was equal zero path starts from marble current position
//	{
//		
//		Cell tmp=fullPath.get(i);
//		if(tmp.isTrap())
//		{
//			throw new IllegalMovementException("A player cannot bypass a Trap");
//		}
//		if(tmp.getMarble()!=null )
//		{
//			if(tmp.getMarble().getColour()==marble.getColour()) //self_bloackage
//			{
//				throw new IllegalMovementException("A player cannot bypass or destroy his own marble");
//			}
//			if(!destroy && i!=fullPath.size()-1)  // opponent marble and card is not king (path blockage)
//			{
//				throw new IllegalMovementException("Movement is invalid if there is more than "
//						+ "one marble owned by any other player blocking the path.");
//			}
//			if(tmp.getCellType()==CellType.ENTRY)  //Safe Zone Entry
//			{
//				throw new IllegalMovementException("A marble cannot enter its Safe Zone "
//						+ "if any marble is stationed at its player’s Safe Zone Entry");
//			}
//			
//			if(tmp.getCellType()==CellType.BASE && this.getBasePosition(tmp.getMarble().getColour())==i ) //Base Cell Blockage
//			{
//				throw new IllegalMovementException(" A marble movement is blocked "
//						+ "if another player marble is in its player Base cell");
//			}
//		}
//	}
//}

private void validatePath(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalMovementException {
	boolean AnotherMarble = false;
	
	for(int i=1; i<fullPath.size(); i++) { //starting from 1 to exclude the current position
		Cell cell = fullPath.get(i);
		
		//Path is clear
		if(cell.getMarble()==null) continue;
		
		//Self-Blocking
		if(cell.getMarble().getColour()== gameManager.getActivePlayerColour() && !destroy) 
			throw new IllegalMovementException("One of your marbles is blocking your path");
		
		//Path Blockage
		if((i+1)!=fullPath.size() && AnotherMarble && !destroy)
			throw new IllegalMovementException("More than one opponent's marbles are blocking your path");
		else
			AnotherMarble = true;
		
		//SafeZone Entry
		if(cell.getCellType()==CellType.ENTRY && (i+1)!=fullPath.size() && fullPath.get(i+1).getCellType()==CellType.SAFE && !destroy) {
				throw new IllegalMovementException("A marble is at your safe zone entry");
		}
		    		
		//Base Cell Blockage
		if(cell.getCellType()==CellType.BASE) {
			int BasePosition = getPositionInPath(track, cell.getMarble());
			
			if(getBasePosition(cell.getMarble().getColour())==BasePosition)
				throw new IllegalMovementException("Another player's marble is in its base cell");
		}
		
		//SafeZone Blockage 
		if(cell.getCellType()==CellType.SAFE)
			throw new IllegalMovementException("Cannot bypass or land on a marble in its safe zone");
	}
}

//private void move(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalDestroyException
//{
//	for(int i=1;i<fullPath.size();i++)
//	{
//		Cell tmp=fullPath.get(i);
//		if(tmp.isTrap())
//		{
//			this.gameManager.sendHome(marble);
//			tmp.setTrap(false);
//			this.assignTrapCell();
//			break;
//		}
//		if(tmp!=null)
//		{
//			if(tmp.getMarble().getColour()==this.gameManager.getActivePlayerColour())
//    		{
//    			throw new IllegalDestroyException("A player cannot destroy his own marbles");
//    		}
//			if( !destroy && i!=fullPath.size()-1 )
//    		{
//    			throw new IllegalDestroyException("A player cannot destroy another player's marble without a king");
//    		}
//			if(destroy || (!destroy && i==fullPath.size()-1))
//			{
//				this.gameManager.sendHome(tmp.getMarble());
//				tmp.setMarble(null);
//			}
//		}
//		tmp.setMarble(marble);
//        fullPath.get(i-1).setMarble(null);
//	}
//}

private void move(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalDestroyException {
	//remove the marble
	fullPath.get(0).setMarble(null);
	
	Cell target = fullPath.get(fullPath.size()-1);
	
	//Collision
	if(target.getMarble()!=null) {
		destroyMarble(target.getMarble());
	}
	target.setMarble(marble);
	
	//Special Card King --> send home??
	if(destroy)
		for(int i=1; i<fullPath.size()-1; i++) {
    		if(fullPath.get(i).getMarble()!=null) {
    			destroyMarble(fullPath.get(i).getMarble());
			}
		}
	
	//if the target cell is a trap cell
	if(target.isTrap()) {
		destroyMarble(marble); //destroy
		target.setTrap(false); //deactivate
		assignTrapCell(); //new trap
	}
}

private void validateSwap(Marble marble_1, Marble marble_2) throws IllegalSwapException
{
	int pos1=this.getPositionInPath(this.track, marble_1);
	int pos2=this.getPositionInPath(track, marble_2);
	if(pos1 ==-1 || pos2==-1)
	{
		throw new IllegalSwapException("Swapping is prohibited if either of the involved marbles are "
				+ "not on the general track");
	}
    int posBase1=this.getBasePosition(marble_1.getColour());
	int posBase2=this.getBasePosition(marble_2.getColour());
	
	if( (pos1==posBase1 && this.gameManager.getActivePlayerColour()!=marble_1.getColour()) ||
			(pos2==posBase2 && this.gameManager.getActivePlayerColour()!=marble_2.getColour()))    //opponents marble is in its base
	{
		throw new IllegalSwapException(" swapping is invalid if the other marble is positioned in its Base cell");
	}
}

//private void validateDestroy(int positionInPath) throws IllegalDestroyException   //feha haga 5alt
//{
//	if(positionInPath>=0 && positionInPath<=99)
//	{
//		if(track.get(positionInPath).getCellType()==CellType.BASE)
//    	{
//    		throw new IllegalDestroyException();
//    	}
//	}
//	else
//	{
//		throw new IllegalDestroyException();
//	}
//
//}

private void validateDestroy(int positionInPath) throws IllegalDestroyException {
	//let positionInPath be the position on the main track
	
	if(positionInPath==-1) 
		throw new IllegalDestroyException("The marble is not on the main track");
	
	Cell cell = track.get(positionInPath);
	
	if(cell.getMarble()!=null && positionInPath == getBasePosition(cell.getMarble().getColour()))
		throw new IllegalDestroyException("The marble is in its base cell");

}
private void validateFielding(Cell occupiedBaseCell) throws CannotFieldException
{
	if(occupiedBaseCell.getMarble()!=null)
	{
		if(this.gameManager.getActivePlayerColour()==occupiedBaseCell.getMarble().getColour())
		{
			throw new CannotFieldException();
		}
	}
}

private void validateSaving(int positionInSafeZone, int positionOnTrack) throws InvalidMarbleException
{
	if(positionInSafeZone !=-1 || positionOnTrack==-1)
	{
		throw new InvalidMarbleException();
	}
}

public void moveBy(Marble marble, int steps, boolean destroy) throws IllegalMovementException, IllegalDestroyException
{
	ArrayList<Cell> path=this.validateSteps(marble, steps);
	this.validatePath(marble, path, destroy);
	this.move(marble, path, destroy);
}

public void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException
{
	this.validateSwap(marble_1, marble_2);
	int pos1=this.getPositionInPath(track, marble_1);
	int pos2=this.getPositionInPath(track, marble_2);
	track.get(pos1).setMarble(marble_2);
	track.get(pos2).setMarble(marble_1);
}

public void destroyMarble(Marble marble) throws IllegalDestroyException
{
	int pos=this.getPositionInPath(track, marble);
	if(this.gameManager.getActivePlayerColour()!=marble.getColour())
	{
		this.validateDestroy(pos);
	}
	this.gameManager.sendHome(marble);
	track.get(pos).setMarble(null);  
}

public  void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException
{
	Cell base=track.get(this.getBasePosition(marble.getColour()));
	if(base.getMarble()!=null)
	{
		this.validateFielding(base);
		this.gameManager.sendHome((base.getMarble()));  //send home marble and did not call destroy method
	}
	base.setMarble(marble);
}

public  void sendToSafe(Marble marble) throws InvalidMarbleException
{
	ArrayList<Cell> safeZone=this.getSafeZone(marble.getColour());
	int positionOnTrack=this.getPositionInPath(track, marble);
	int positionInSafeZone=this.getPositionInPath(safeZone, marble);
	this.validateSaving(positionInSafeZone, positionOnTrack);
	for(int i=0;i<safeZone.size();i++)
	{
		if(safeZone.get(i).getMarble()==null)
		{
			safeZone.get(i).setMarble(marble);
			break;
		}
	}
	track.get(positionOnTrack).setMarble(null); //settting marble postion to null after sending it to base
}

public ArrayList<Marble> getActionableMarbles()
{
	ArrayList<Marble> res=new ArrayList<Marble>();
	Colour currentPlayerColor=this.gameManager.getActivePlayerColour();
	for(int i=0;i<track.size();i++)
	{
		if(track.get(i)!=null && track.get(i).getMarble().getColour()==currentPlayerColor)
		{
			res.add(track.get(i).getMarble());
		}
	}
	ArrayList<Cell> safeZone=this.getSafeZone(currentPlayerColor);
	for(int i=0;i<safeZone.size();i++)
	{
		if(safeZone.get(i)!=null )
		{
			res.add(safeZone.get(i).getMarble());
		}
	}
	return res;
}

}


    
