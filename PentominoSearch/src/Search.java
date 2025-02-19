/**
 * @author Department of Data Science and Knowledge Engineering (DKE)
 * @version 2022.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class includes the methods to support the search of a solution.
 */
public class Search
{
    public static final int horizontalGridSize = 12;
    public static final int verticalGridSize = 5;
    
    public static List<Character> input = new ArrayList<>(Arrays.asList('F', 'N', 'P', 'T', 'Z', 'V', 'U', 'L', 'Y', 'W', 'I', 'X'));
    
    //Static UI class to display the board
    public static UI ui = new UI(horizontalGridSize, verticalGridSize, 50);

    private static boolean isBoardFilled(int[][] field)
    {
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field[i].length; j++)
            {
                if (field[i][j] == -1)
                {
                   return false; 
                }
            }
        }
        return true;
    }

    private static boolean hasIsolatedGroups(int[][] field)
    {
        boolean[][] visited = new boolean[horizontalGridSize][verticalGridSize];
    
        for (int i = 0; i < horizontalGridSize; i++)
        {
            for (int j = 0; j < verticalGridSize; j++)
            {
                if (field[i][j] == -1 && !visited[i][j])
                {
                    int groupSize = checkConnectivity(field, visited, i, j);
                    if (groupSize % 5 != 0)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int checkConnectivity(int[][] field, boolean[][] visited, int x, int y)
    {
        if (x < 0 || x >= horizontalGridSize || y < 0 || y >= verticalGridSize || visited[x][y] || field[x][y] != -1)
        {
            return 0;
        }

        visited[x][y] = true;

        int count = 1;
        count += checkConnectivity(field, visited, x + 1, y);
        count += checkConnectivity(field, visited, x - 1, y);
        count += checkConnectivity(field, visited, x, y + 1);
        count += checkConnectivity(field, visited, x, y - 1);

        return count;
    }

	/**
	 * Helper function which starts a basic search algorithm
	 */
    public static void search()
    {
        // Initialize an empty board
        int[][] field = new int[horizontalGridSize][verticalGridSize];

        for(int i = 0; i < field.length; i++)
        {
            Arrays.fill(field[i], -1);
        }
        // Sort the input based on number of mutations
        //input.sort(Comparator.comparingInt(p -> PentominoDatabase.data[characterToID(p)].length));
        // Start the basic search
        basicSearch(field);
    }

    private static boolean branchingMethod(int[][] field, List<Character> availablePieces)
    {
        if (availablePieces.isEmpty())
        {
            return !hasIsolatedGroups(field);
        }

        char currentChar = availablePieces.remove(0);
        int pentID = characterToID(currentChar);
    
        for (int mutation = 0; mutation < PentominoDatabase.data[pentID].length; mutation++)
        {
            int[][] pieceToPlace = PentominoDatabase.data[pentID][mutation];
            for (int x = 0; x <= horizontalGridSize - pieceToPlace.length; x++)
            {
                for (int y = 0; y <= verticalGridSize - pieceToPlace[0].length; y++)
                {
                    if (canPlace(field, pieceToPlace, x, y))
                    {
                        addPiece(field, pieceToPlace, pentID, x, y);
                        if (!hasIsolatedGroups(field) && branchingMethod(field, new ArrayList<>(availablePieces)))
                        {
                            return true;
                        }
                        removePiece(field, pieceToPlace, x, y);
                    }
                }
            }
        }
        return false;
    }

    private static boolean canPlace(int[][] field, int[][] piece, int x, int y)
    {
        for (int i = 0; i < piece.length && x + i < horizontalGridSize; i++)
        {
            for (int j = 0; j < piece[i].length && y + j < verticalGridSize; j++)
            {
                if (piece[i][j] == 1 && field[x + i][y + j] != -1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private static void removePiece(int[][] field, int[][] piece, int x, int y)
    {
        for (int i = 0; i < piece.length; i++)
        {
            for (int j = 0; j < piece[i].length; j++)
            {
                if (piece[i][j] == 1)
                {
                    field[x + i][y + j] = -1;
                }
            }
        }
        ui.setState(field);
    }
	
	/**
	 * Get as input the character representation of a pentomino and translate it into its corresponding numerical value (ID)
	 * @param character a character representating a pentomino
	 * @return	the corresponding ID (numerical value)
	 */
    private static int characterToID(char character) {
    	int pentID = -1; 
    	if (character == 'X') 
        {
    		pentID = 0;
    	} 
        else if (character == 'I') 
        {
    		pentID = 1;
    	} 
        else if (character == 'Z') 
        {
    		pentID = 2;
    	} 
        else if (character == 'T') 
        {
    		pentID = 3;
    	} 
        else if (character == 'U') 
        {
    		pentID = 4;
     	} 
        else if (character == 'V') 
        {
     		pentID = 5;
     	} 
        else if (character == 'W') 
        {
     		pentID = 6;
     	} 
        else if (character == 'Y') 
        {
     		pentID = 7;
    	} 
        else if (character == 'L') 
        {
    		pentID = 8;
    	} 
        else if (character == 'P')
        {
    		pentID = 9;
    	} 
        else if (character == 'N') 
        {
    		pentID = 10;
    	} 
        else if (character == 'F') 
        {
    		pentID = 11;
    	} 
    	return pentID;
    }
	
	/**
	 * Basic implementation of a search algorithm. It is not a bruto force algorithms (it does not check all the posssible combinations)
	 * but randomly takes possible combinations and positions to find a possible solution.
	 * The solution is not necessarily the most efficient one
	 * This algorithm can be very time-consuming
	 * @param field a matrix representing the board to be fulfilled with pentominoes
	 */
    private static void basicSearch(int[][] field)
    {
        if (!isBoardFilled(field))
        {
            if (branchingMethod(field, new ArrayList<>(input)))
            {
                ui.setState(field);
                System.out.println("Solution found");
            } 
            else
            {
                System.out.println("No solution found");
            }
        }
    }

	/**
	 * Adds a pentomino to the position on the field (overriding current board at that position)
	 * @param field a matrix representing the board to be fulfilled with pentominoes
	 * @param piece a matrix representing the pentomino to be placed in the board
	 * @param pieceID ID of the relevant pentomino
	 * @param x x position of the pentomino
	 * @param y y position of the pentomino
	 */
    public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y)
    {
        for(int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[i].length; j++) // loop over y position of pentomino
            {
                if (piece[i][j] == 1)
                {
                    // Add the ID of the pentomino to the board if the pentomino occupies this square
                    field[x + i][y + j] = pieceID;
                }
            }
        }
        ui.setState(field);
    }

	/**
	 * Main function. Needs to be executed to start the basic search algorithm
	 */
    public static void main(String[] args)
    {
        Collections.shuffle(input);
        search();
    }
}