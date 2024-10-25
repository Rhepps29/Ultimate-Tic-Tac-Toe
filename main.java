class main {
	public static void main(String[] args) {
		String[][][] board = new String[][][]{
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
			
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
			
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
			
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
			
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
			
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
			
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
			
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
			
			{{" "," "," "},
			{" "," "," "},
			{" "," "," "}},
		};
		printboard(board);
	}
	public static void printboard(String [][][] board){
		System.out.println("_____________");
		for(int l = 0; l<9; l=l+3){
			for (int k = 0; k<3; k++){
				System.out.print("|");
				for (int i = 0; i<3; i++){
					for (int j = 0; j<3; j++){
						System.out.print(board[i+l][k][j]);
					}
					System.out.print("|");
				}
				System.out.println();
			}
			System.out.println("_____________");
		}
	}
}
