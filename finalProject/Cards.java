package finalProject;

import processing.core.PApplet;

public class Cards {
	private PApplet parent;
	int cordX, cordY;
	boolean pick;

	public Cards(PApplet p, int cX, int cY)
    {
        parent = p;
        cordX = cX;
        cordY = cY;
        
        pick = true;
    }
	public void drawSelf()
    {
    	if (pick = false)
    		parent.fill(255,255,255);
        	parent.rect(cordX, cordY, 50, 50);
        
    }
}
