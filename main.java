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
			
			{{' ',' ',' '},
			{' ',' ',' '},
			{' ',' ',' '}},
		};
		multiplayergame(board);
	}
	public static void printboard(char [][][] board){
		System.out.println("_______");
		for (int m = 0; m<3; m++){
			System.out.print("|");
			for (int n = 0; n<3; n++){
				System.out.print(board[9][m][n]);
				System.out.print("|");
			}
			System.out.println();
			System.out.println("_______");
		}
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
				if (innerboardwon(board, innerboard)&&(board[9][innerboard/3][innerboard%3]=='a')){
					board[9][innerboard/3][innerboard%3] = player;
				}
				if (player == 'x'){
					player = 'o';
				}else{
					player = 'x';
				}
				if (board[9][innerboard/3][innerboard%3]=='a'){
					board[9][innerboard/3][innerboard%3]=' ';
				}else if(board[9][innerboard/3][innerboard%3]=='O'){
					board[9][innerboard/3][innerboard%3]='o';
				}else if(board[9][innerboard/3][innerboard%3]=='X'){
					board[9][innerboard/3][innerboard%3]='x';
				}
				innerboard = nextboard;
			}else{
				System.out.println("Invalid Choice.");
			}
			if (board[9][innerboard/3][innerboard%3]==' '||board[9][innerboard/3][innerboard%3]=='a'){
				board[9][innerboard/3][innerboard%3]='a';
			}else{
				board[9][innerboard/3][innerboard%3]= Character.toUpperCase(board[9][innerboard/3][innerboard%3]);
			}
			printboard(board);
			if (innerboardwon(board, 9) || innerboardfull(board,9)){
				ongoing = false;
			}
		}while (ongoing);
		System.out.print("Game End.");
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
	public static boolean innerboardwon(char[][][] board, int innerboard){
		boolean won = false;
		for (int i = 0; i<3; i++){
			if (Character.toLowerCase(board[innerboard][i][0])==Character.toLowerCase(board[innerboard][i][1])&&Character.toLowerCase(board[innerboard][i][1])==Character.toLowerCase(board[innerboard][i][2])&&board[innerboard][i][0]!=' '){
				won = true;
			}
			if (Character.toLowerCase(board[innerboard][0][i])==Character.toLowerCase(board[innerboard][1][i])&&Character.toLowerCase(board[innerboard][1][i])==Character.toLowerCase(board[innerboard][2][i])&&board[innerboard][0][i]!=' '){
				won = true;
			}
		}
		if (Character.toLowerCase(board[innerboard][0][0])==Character.toLowerCase(board[innerboard][1][1])&&Character.toLowerCase(board[innerboard][1][1])==Character.toLowerCase(board[innerboard][2][2])&&board[innerboard][0][0]!=' '){
			won = true;
		}
		if (Character.toLowerCase(board[innerboard][0][2])==Character.toLowerCase(board[innerboard][1][1])&&Character.toLowerCase(board[innerboard][1][1])==Character.toLowerCase(board[innerboard][2][0])&&board[innerboard][0][2]!=' '){
			won = true;
		}
		return won;
	}
}
