/**
 * http://community.topcoder.com/stat?c=problem_statement&pm=2998&rd=5857
 * I am solving this at 22:36 on 10th June 2015
 * I got 499.57 in topcoder!
 */
// Used flood fill algorithm to solve this

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class grafixMask_SRM211 {

	/**
	 * @param args
	 */
	static int row = 400;
	static int col = 600;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] asd = {"50 300 199 300", "201 300 350 300", "200 50 200 299", "200 301 200 550"};
		//String[] asd = {"0 1 1 3", "2 1 4 1", "3 2 4 3", "2 3 3 3"};
		//String[] asd = {"0 3 4 4"};
		int[] a = sortedAreas(asd);
		System.out.println(a[0]);
		System.out.println(a[1]);
	}

	public static int[] sortedAreas(String[] rectangles)
	{
		
		List<Integer> holes = new ArrayList<Integer>();
		
		
		int board[][] = new int[row][col];
		
		//board = 0 - not visited, no wall
		//board = 7 - wall
		//board = 1 - visited, no wall
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {		
				board[i][j] = 0;
			}
		}
		
		// drawing all the given walls
		for (int i = 0; i < rectangles.length; i++) {
			int[] border = new int[4];
			border[0] = Integer.parseInt(rectangles[i].split(" ")[0]);
			border[1] = Integer.parseInt(rectangles[i].split(" ")[1]);
			border[2] = Integer.parseInt(rectangles[i].split(" ")[2]);
			border[3] = Integer.parseInt(rectangles[i].split(" ")[3]);
			
			for (int j = border[0]; j <= border[2]; j++) {
				for (int j2 = border[1]; j2 <= border[3]; j2++) {
					board[j][j2] = 7;
				}
			}
		}
		
		//finding a non zero, so that we can start crawling from there to find the hole
		int init[] = findNonZero(board);
		//do this until all the holes are found
		while(init[0] != -2)
		{
			//bfs that crawls in 4 direction and finds the size of the hole
			holes.add(bfsGrafix(board, init[0], init[1]));
			init = findNonZero(board);
		}
		
		//converting list to object array to integer array and sorting it.
		int[] list = new int[holes.size()];
		for(int i=0; i<holes.size(); i++){
			list[i] = (Integer) holes.toArray()[i];
		 }
		Arrays.sort(list);
		return list;
	}
	
	static int bfsGrafix(int[][] board, int initX, int initY)
	{
		int size = 0;
		
		int xMove[] = {  -1, 0, 0, 1 };   //4 possible positions to crawl from this point
		int yMove[] = {  0, 1, -1, 0 }; 
		
		Queue<int[]> myQueue = new LinkedList();
		int[] tmp = {initX, initY};
		board[initX][initY] = 1;		//initializing 1 for the cell i visited
		size++;
		myQueue.add(tmp);
		
		while(!myQueue.isEmpty())
		{
			int flood[] = myQueue.remove();	//getting the first element

			for(int i = 0; i < 4; i++)	
			{
				int x = flood[0]+xMove[i];
				int y = flood[1]+yMove[i];
				
				if(isInsideBoard(x, y, board))		//checking if the move is valid
				{
					int[] t = {x, y};
					board[x][y] = 1;
					myQueue.add(t);
					size++;
				}
			}
		}
		return size;
	}
	
	static boolean isInsideBoard(int x, int y, int board[][])
	{
		if((x >=0) && (x < row) && (y >=0) && (y < col))
		{
			if(board[x][y] == 0)
			{
				return true;
			}
		}
		return false;
	}
	
	static int[] findNonZero(int[][] board)
	{
		int[] found = {-2, -2};
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(board[i][j] == 0)
				{
					found[0] = i;
					found[1] = j;
					return found;
				}
			}
		}
		return found;
	}
	
	static void printBoard(int[][] board)
	{
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}
