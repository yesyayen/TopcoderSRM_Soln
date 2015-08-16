/**
 * https://www.topcoder.com/stat?c=problem_statement&pm=2915&rd=5853
 * I am solving this at 22:51 on 9th June 2015
 * I am right now in my house at pittsburgh and its wonderful!
 * and my brain is dried up after solving this!
 */
import java.util.LinkedList;
import java.util.Queue;


public class CaptureThemAll_SRM207 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//fastKnight("b1", "c3", "a3");
		//fastKnight("a1", "a2", "b2");
		fastKnight("a5", "b7", "e4");
	}

	public static int fastKnight(String knight, String rook, String queen)
	{
		int board[][] = new int[9][9];
		//creating board with all -1's
		for(int i = 1; i < 9; i++)
		{
			for(int j = 1; j < 9; j++)
			{
				board[i][j] = -1;
			}
		}

		Queue<int[]> myQueue = new LinkedList();	//solving this problem using BFS, so a Queue

		int[] k = new int[2];
		int[] r = new int[2];
		int[] q = new int[2];

		boolean foundQueen = false, foundRook = false;

		k[1] = knight.charAt(0) - 96;	//converting the A5, B2 to numbers. B - column and 1 - row
		k[0] = knight.charAt(1) - 48;

		r[1] = rook.charAt(0) - 96;
		r[0] = rook.charAt(1) - 48;

		q[1] = queen.charAt(0) - 96;
		q[0] = queen.charAt(1) - 48;

		System.out.println(k[0] + " " + k[1] + " " +r[0] + " " + r[1] + " " +q[0] + " " + q[1] + " ");

		int xMove[] = {  2, 1, -1, -2, -2, -1,  1,  2 };   //knight possible position
		int yMove[] = {  1, 2,  2,  1, -1, -2, -2, -1 };   // (x1, y1) (x2, y2)

		myQueue.add(k);
		board[k[0]][k[1]] = 0;		//starting position of the knight

		while(!myQueue.isEmpty())
		{	
			int kni[] = myQueue.remove();	//getting the first element

			for(int i = 0; i < 8; i++)	
			{
				int x = kni[0]+xMove[i];
				int y = kni[1]+yMove[i];
				
				if(isInsideBoard(x, y, board))		//checking if the knight move is valid
				{
					board[x][y] = board[kni[0]][kni[1]] + 1;	//updating the move count in the board
					if(!foundRook && (x == r[0] && y == r[1]))
					{
						foundRook = true;
						System.out.println("found rook in " + board[x][y] + "steps");
						System.out.println(x + " " + y);
						myQueue.clear();			//if rook found we clear the queue and we go to find queen
						int temp[] = {x, y};
						myQueue.add(temp);
						printBoard(board);
						break;
					}

					else if(!foundQueen && (x == q[0] && y == q[1]))
					{
						foundQueen = true;
						System.out.println("found queen in  " + board[x][y] + "steps");
						System.out.println(kni[0]+ " " +kni[1]);
						myQueue.clear();
						int temp[] = {x, y};
						myQueue.add(temp);
						printBoard(board);
						break;
					}
					else
					{
						int temp[] = {x, y};
						myQueue.add(temp);
					}
					if(foundRook && foundQueen)
					{
						myQueue.clear();
					}
				}
			}
		}
		return 1;
	}

	static boolean isInsideBoard(int x, int y, int board[][])
	{
		if((x >=1) && (x < 9) && (y >=1) && (y < 9))
		{
			return true;
		}
		return false;
	}
	
	static void printBoard(int[][] board)
	{
		System.out.println();
		for(int i = 8; i >= 1; i--)
		{
			for(int j = 1; j < 9; j++)
			{
				System.out.print(board[i][j] + "  ");
			}
			System.out.println();		
		}
		System.out.println("-----------------");
	}

}
