
public class ShiftOperator {
	
	private final String test;
	
	public ShiftOperator(String value){
		this.test = value;
	}
	 /*
     * each winning line gets a bit
     */
    private static int[][] board_mask = {
        {1|8|64,2|8,4|8|128},
        {1|16,2|16|64|128,4|16},
        {1|32|128,2|32,4|32|64},
    };

    /*
     * each winning line gets an unset bit, all else
     * is set. (makes for easier masking later on)
     */
    static {
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
            	System.out.println("Before invertinh boardmask["+i+ "]["+j+"] - "+Integer.toBinaryString(board_mask[i][j]));
                board_mask[i][j] = ~board_mask[i][j];
        		System.out.println("boardmask["+i+ "]["+j+"] - "+Integer.toBinaryString(board_mask[i][j]));
            }
    }

    // enable quicker end-game check
    private static int[][][] wingamechecks = {
        { {0,0},{0,1},{0,2} },
        { {0,0},{1,0},{2,0} },
        { {0,0},{1,1},{2,2} },
        { {0,1},{1,1},{2,1} },
        { {0,2},{1,2},{2,2} },
        { {1,0},{1,1},{1,2} },
        { {2,0},{2,1},{2,2} },
        { {2,0},{1,1},{0,2} }
    };

    public static void main(String[] args) {
    	System.out.println("Length for wingame -- "+ wingamechecks.length);
		for (int i = 0; i < wingamechecks.length; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
	        		System.out.println("wingamechecks["+i+ "]["+j+"]["+k+"] - "+wingamechecks[i][j][k]);
				}
			}
		}
		
		
	}
}
