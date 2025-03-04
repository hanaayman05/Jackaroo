package engine.board;

import model.Colour;

public class SafeZone {
	
	private final Colour colour;
	private final Cell[] cells= new Cell[4];
	
	public SafeZone(Colour colour)
	{
		this.colour=colour;
		for(int i=0 ; i<cells.length ; i++)
		{
			cells[i]=new Cell(CellType.SAFE);
		}
		
	}

	public Colour getColour() {
		return colour;
	}

	public Cell[] getCells() {
		return cells;
	}
	

}
