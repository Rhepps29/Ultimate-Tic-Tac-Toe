import java.util.*;

class main {
	public static void main(String[] args) {
		char[][][] board = new char[][][]{
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
		};
		multiplayergame(board);
	}
	public static void printboard(char [][][] board){
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
	public static void multiplayergame(char [][][] board){
		Scanner input = new Scanner(System.in);
		char player = 'x';
		printboard(board);
		System.out.println("[1][2][3]");
		System.out.println("[4][5][6]");
		System.out.println("[7][8][9]");
		System.out.print("Pick the initial board: ");
		int innerboard = input.nextInt()-1;
		boolean ongoing = true;
		do{
			if (innerboardfull(board, innerboard)){
				System.out.println("[1][2][3]");
				System.out.println("[4][5][6]");
				System.out.println("[7][8][9]");
				System.out.print("Pick your board: ");
				innerboard = input.nextInt()-1;
			}
			System.out.print("Pick row (1-3): ");
			int rowchoice = input.nextInt();
			System.out.print("Pick column (1-3): ");
			int colchoice = input.nextInt();
			int nextboard = (rowchoice-1)*3+(colchoice-1);
			if (board[innerboard][rowchoice-1][colchoice-1] == ' '){
				board[innerboard][rowchoice-1][colchoice-1] = player;
				if (player == 'x'){
					player = 'o';
				}else{
					player = 'x';
				}
				innerboard = nextboard;
			}else{
				System.out.println("Invalid Choice.");
			}
			printboard(board);
		}while (ongoing);
	}
	public static boolean innerboardfull(char[][][] board, int innerboard){
		boolean full = false;
		int count = 0;
		for (int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if (board[innerboard][i][j] != ' '){
					count++;
				}
			}
		}
		if (count == 9){
			full = true;
		}
		return full;
	}
}
