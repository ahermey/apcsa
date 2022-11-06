package finalProject;


import java.util.ArrayList;

import processing.core.PApplet;

public class FinalProject extends PApplet
{
    public static void main(String[] args)
    {
        PApplet.main("finalProject.FinalProject");
    }

    	int[][] randomSquares = new int[4][4];
    	int currentNumber = -1;
    	int currentRow = -1;
    	int currentColumn = -1;
    	int prevNumber = -1;
    	int prevRow = -1;
    	int prevColumn = -1;
    	int numberOfClicks = 0;
    	int totalPoints = 0;
    	int pick = -1;
    
    
    public void settings()
    {
        size(800, 800);
    }
    public void setup()
    {
    	background (4, 8, 12);
    	randomizeSquares(randomSquares);
    	squares(randomSquares);
    }
    public void draw()
    {
    	win();
    }
   
    public void win()
    {
    	if (totalPoints >= 79 && totalPoints <= 81 )
		{
       		
    		textSize(32);
    		text("the bad game™", 10, 30);
    		fill(random(256), random(0), random(0));
    		text("you finished the game", 225, 400); 
		}
    }
    public void randomizeSquares(int[][] squares)
    {
    	ArrayList<Integer> numbers = new ArrayList<Integer>();
    	for(int i = 1; i <= 8; i++)
    	{
    		numbers.add(Integer.valueOf(i));
    		numbers.add(Integer.valueOf(i));
    	}
    	
    	for(int i=0; i< squares.length; i++)
    	{
    		for(int n = 0; n < squares[i].length; n++)
    		{
    			int index = (int) (Math.random() * (numbers.size()-1));
    			squares[i][n] = numbers.get(index);
    			numbers.remove(index);
    		}
    	}    	
    }
    public void squares(int[][] squares)
    {
    	for(int i=0; i< squares.length; i++)
    	{
    		for(int n = 0; n < squares[i].length; n++)
    		{
    			fill(250, 40, 187);
    			rect(i*200,n*200, 200, 200);
    			textSize(24);
    			fill(250, 40, 187);
    			text(squares[i][n], i*200 + 100, n*200 + 100);
    		}
    	}
    }
    public void flipCard(int  xValue, int yValue, int[][] squares) 
    {
    	for(int  i = 0; i < squares.length; i++)
    	{
    		if(xValue > (i * 200) && xValue < (i * 200 + 200))
    		{
    			for(int n = 0; n < squares[i].length; n++)
       		 	{
    				if(yValue > (n * 200) && yValue < (n * 200 + 200))
    				{
    					if(squares[i][n] != -1)
    					{
        					fill(0);
        	    			rect(i*200,n*200, 200, 200);
        	    			textSize(24);
        	    			fill(250, 40, 187);
        	    			text(squares[i][n], i*200 + 100, n*200 + 100);
        	    			
        	    			numberOfClicks++;
        	    			
        	    			if(numberOfClicks == 1)
        	    			{
            	    			currentNumber = squares[i][n];
            	    			currentRow = i;
            	    			currentColumn = n;
        	    			}
        	    			else if(numberOfClicks == 2)
        	    			{
        	    				prevNumber = currentNumber;
        	    				prevRow = currentRow;
        	    				prevColumn = currentColumn;
        	    				currentNumber = squares[i][n];
            	    			currentRow = i;
            	    			currentColumn = n;
        	    			}	
    					}
    	    			
//    	    			if (squares[i][n] == pick)
//    	    			{
//    	    				pick = -1;
//    	    			}
//    	    			pick = squares[i][n];
//    	    			if (squares[i][n] != pick && pick != -1)
//    	    			{
//    	    				squares(squares);
//    	    				pick = -1;
//    	    			}
    	    		}
       		 	}
    		}
    	}
    }
    
    public void checkNumbers()
    {
    	if(numberOfClicks == 2)
    	{
    		if(currentNumber == prevNumber)
    		{
    			if((prevRow == currentRow && prevColumn == currentColumn))
    			{
    				fill(250, 40, 187);
        			rect(currentRow*200,currentColumn*200, 200, 200);
    			}
    			else if (checkIfMatchFound() == false)
    			{
    				fill(0);
        			rect(currentRow*200,currentColumn*200, 200, 200);
        			fill(0);
        			rect(prevRow*200,prevColumn*200, 200, 200);
            		totalPoints += 10;
        	       	System.out.println("Total Points:" + totalPoints);
        	       	removeElement();
    			}
    			else
    			{
    				fill(250, 40, 187);
        			rect(currentRow*200,currentColumn*200, 200, 200);
    				fill(250, 40, 187);
        			rect(prevRow*200,prevColumn*200, 200, 200);
    			}
    		}
    		else 
    		{
    			fill(250, 40, 187);
    			rect(currentRow*200,currentColumn*200, 200, 200);
    			fill(250, 40, 187);
    			rect(prevRow*200,prevColumn*200, 200, 200);
    		}
    		numberOfClicks = 0;
    	}
    }
    
    public boolean checkIfMatchFound()
    {
    	for(int i=0; i< randomSquares.length; i++)
    	{
    		for(int n = 0; n < randomSquares[i].length; n++)
    		{
    			if(randomSquares[i][n] == currentNumber)
    			{
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public void removeElement()
    {
    	for(int count = 1; count <= 2; count ++)
    	{
        	for(int i=0; i< randomSquares.length; i++)
        	{
        		for(int n = 0; n < randomSquares[i].length; n++)
        		{
        			if(currentNumber == randomSquares[i][n])
        			{
        				randomSquares[i][n] = -1;
        			}
        		}
        	}
    	}
    }
    
    public void mousePressed()
    {
        flipCard(mouseX, mouseY, randomSquares);
       	checkNumbers();
    }
}





