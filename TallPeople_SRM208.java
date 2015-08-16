
/**
 * http://community.topcoder.com/stat?c=problem_statement&pm=2923&rd=5854
 * I am solving this at 19:47 on 9th June
 * I am right now in my house at pittsburgh and its wonderful!
 */

public class TallPeople_SRM208 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String people[] = {"1 2", "4 5", "3 6"};
		getPeople(people);
		
	}
	
	public static int[] getPeople(String[] people) {
		int[] selected = new int[2];
		int col = people[0].split(" ").length;
		int row = people.length;
		
		int[][] peopl = new int[row][col];
		for(int i=0; i < people.length; i++)
		{
			for(int j=0; j < col; j++)
			{
				peopl[i][j] = Integer.parseInt(people[i].split(" ")[j]);
			}
		}
		
		int tallInShort;
		tallInShort = Integer.MIN_VALUE;
		
		for(int i=0; i < people.length; i++)
		{
			int shortInRow = peopl[i][0];
			for(int j=0; j < people[i].split(" ").length; j++)
			{
				if(shortInRow > peopl[i][j])
				{
					shortInRow = peopl[i][j];
				}
			}
			
			if(shortInRow > tallInShort)
			{
				tallInShort = shortInRow;
			}
		}
		///////////////////////////////
		
		int shortInTall;
		shortInTall = Integer.MAX_VALUE;
		
		for(int j=0; j < col; j++)
		{
			int tallInCol = peopl[0][j];
			for(int i=0; i < row; i++)
			{
				if(tallInCol < peopl[i][j])
				{
					tallInCol = peopl[i][j];
				}
			}
			
			if(tallInCol < shortInTall)
			{
				shortInTall = tallInCol;
			}
		}
		selected[0] = tallInShort;
		selected[1] = shortInTall;
		
		return selected;
	}

}
