import java.util.*;
import java.nio.channels.*;

class main {
	public static void main(String[] args) {
		System.out.println("Ultimate Tic Tac Toe");
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
		System.out.println("1. One-Player");
		System.out.println("2. Two-Player");
		System.out.println("3. Tutorial (Help)");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		int choice = input.nextInt();
		if (!(choice >= 3 || choice < 1)){
			multiplayergame(board, choice);	
		}
		System.out.print("HI");
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
	public static void multiplayergame(char [][][] board, int playercount){
		Scanner input = new Scanner(System.in);
		char player = 'x';
		printboard(board);
		int innerboard =  0;
		if ((playercount ==2) || (player == 'x')){
			System.out.println("[1][2][3]");
			System.out.println("[4][5][6]");
			System.out.println("[7][8][9]");
			System.out.print("Pick the initial board: ");
			innerboard = input.nextInt()-1;
		}else{
			Random rand =new Random();
			innerboard = rand.nextInt(9);
		}
		boolean ongoing = true;
		int[] aiarraychoice;
		aiarraychoice = new int[2];
		do{
			if (innerboardfull(board, innerboard)){
				if (player == 'x' || playercount == 2){
					System.out.println("[1][2][3]");
					System.out.println("[4][5][6]");
					System.out.println("[7][8][9]");
					System.out.print("Pick your board: ");
					innerboard = input.nextInt()-1;
				}else{
					//Yes, this is a lame way to do this, no I don't care.
					Random rand = new Random();
					innerboard = rand.nextInt(9);
				}
			}
			int rowchoice;
			int colchoice;
			if ((playercount ==2) || (player == 'x')){
				do {
					System.out.print("Pick row (1-3): ");
					rowchoice = input.nextInt();
				}while (!(rowchoice<4&&rowchoice>0));
				do {
					System.out.print("Pick column (1-3): ");
					colchoice = input.nextInt();
				}while (!(colchoice<4&&colchoice>0));
			}else{
				aiarraychoice = aipick(board, innerboard);
				rowchoice = aiarraychoice[0]+1;
				colchoice = aiarraychoice[1]+1;
			}
			int nextboard = (rowchoice-1)*3+(colchoice-1);
			System.out.print("\033[H\033[2J");  
			System.out.flush(); 
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
			if (playercount ==2){
				System.out.println("Player: "+player);}
			printboard(board);
			if (innerboardwon(board, 9) || innerboardfull(board,9)){
				if (innerboardfull(board,9) && !(innerboardwon(board,9))){
					player = '0';
				}
				ongoing = false;
			}
		}while (ongoing);
		if (player == 'x'){
			player = 'o';
		}else if (player == 'o'){
			player = 'x';
		}
		if (!(player=='0')){
			System.out.print("Game End. "+Character.toUpperCase(player) + " won.");}
		else{
			System.out.print("Game End. Tie");
		}
	}
	public static boolean innerboardfull(char[][][] board, int innerboard){
		boolean full = false;
		int count = 0;
		for (int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if (board[innerboard][i][j] != ' '){
					if (board[innerboard][i][j] != 'a'){
						count++;
					}
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
	public static int[] aipick(char[][][] board, int innerboard){
		char aiplayer ='o';
		char hplayer = 'x';
		int[] aiarraychoice = new int[] {0,0};
		int currentleader = -10000000;
		int[][] aiarrayweights = new int[][]
		{{0,0,0},
		{0,0,0},
		{0,0,0}};
		for (int i = 0; i<3; i++){
			for(int j = 0; j<3;j++){
				if (board[innerboard][i][j] != ' '){
					aiarrayweights[i][j]= -100000;
				}
			}
		}
		for (int i = 0; i<3;i++){
			if (board[innerboard][i][0]==board[innerboard][i][1] && board[innerboard][i][1]!= ' '){
				aiarrayweights[i][2] += 10;
			}
		}
		for (int i = 0; i<3;i++){
			if (board[innerboard][i][0]==board[innerboard][i][2] && board[innerboard][i][2]!= ' '){
				aiarrayweights[i][1] += 10;
			}
		}
		for (int i = 0; i<3;i++){
			if (board[innerboard][i][1]==board[innerboard][i][2] && board[innerboard][i][2]!= ' '){
				aiarrayweights[i][0] += 10;
			}
		}
		
		for (int i = 0; i<3;i++){
			if (board[innerboard][0][i]==board[innerboard][1][i]&& board[innerboard][1][i]!= ' '){
				aiarrayweights[2][i] += 10;
			}
		}
		for (int i = 0; i<3;i++){
			if (board[innerboard][0][i]==board[innerboard][2][i]&& board[innerboard][2][i]!= ' '){
				aiarrayweights[1][i] += 10;
			}
		}
		for (int i = 0; i<3;i++){
			if (board[innerboard][1][i]==board[innerboard][2][i]&& board[innerboard][1][i]!= ' '){
				aiarrayweights[0][i] += 10;
			}
		}
		if (board[innerboard][0][0]==board[innerboard][2][2]&& board[innerboard][0][0]!= ' '){
			aiarrayweights[1][1] += 10;
		}
		if (board[innerboard][0][2]==board[innerboard][2][0]&& board[innerboard][0][0]!= ' '){
			aiarrayweights[1][1] += 10;
		}
		if (board[innerboard][0][0]==board[innerboard][1][1]&& board[innerboard][0][0]!= ' '){
			aiarrayweights[2][2] += 10;
		}
		if (board[innerboard][2][2]==board[innerboard][1][1]&& board[innerboard][2][2]!= ' '){
			aiarrayweights[0][0] += 10;
		}
		if (board[innerboard][0][2]==board[innerboard][1][1]&& board[innerboard][0][2]!= ' '){
			aiarrayweights[2][0] += 10;
		}
		if (board[innerboard][2][0]==board[innerboard][1][1]&& board[innerboard][2][0]!= ' '){
			aiarrayweights[0][2] += 10;
		}
		for (int i = 0; i<3;i++){
			for (int j = 0; j<3; j++){
				if (board[9][i][j] != ' '){
					aiarrayweights[i][j]+= 9;
				}
			}
		}
		aiarrayweights[1][0]+=1;
		aiarrayweights[0][1]+=1;
		aiarrayweights[2][1]+=1;
		aiarrayweights[1][2]+=1;
		for (int i = 0; i<3;i++){
			if (board[9][i][0]==board[9][i][1]&& board[9][i][0]!= ' '){
				if (board[9][i][0]==hplayer){
					aiarrayweights[i][2] -= 20;
				}else{
					aiarrayweights[i][2] += 20;
				}
			}
		}
		for (int i = 0; i<3;i++){
			if (board[9][i][0]==board[9][i][2]&& board[9][i][0]!= ' '){
				if (board[9][i][0]==hplayer){
					aiarrayweights[i][1] -= 20;
				}else{
					aiarrayweights[i][1] += 20;
				}
			}
		}
		for (int i = 0; i<3;i++){
			if (board[9][i][1]==board[9][i][2]&& board[9][i][1]!= ' '){
				if (board[9][i][0]==hplayer){
					aiarrayweights[i][0] -= 20;
				}else{
					aiarrayweights[i][0] += 20;
				}
			}
		}
		
		for (int i = 0; i<3;i++){
			if (board[9][0][i]==board[9][1][i]&& board[9][0][i]!= ' '){
				if (board[9][i][0]==hplayer){
					aiarrayweights[2][i] -= 20;
				}else{
					aiarrayweights[2][i] += 20;
				}
			}
		}
		for (int i = 0; i<3;i++){
			if (board[9][0][i]==board[9][2][i]&& board[9][0][i]!= ' '){
				if (board[9][i][0]==hplayer){
					aiarrayweights[1][i] -= 20;
				}else{
					aiarrayweights[1][i] += 20;
				}
			}
		}
		for (int i = 0; i<3;i++){
			if (board[9][1][i]==board[9][2][i]&& board[9][1][i]!= ' '){
				if (board[9][i][0]==hplayer){
					aiarrayweights[0][i] -= 20;
				}else{
					aiarrayweights[0][i] += 20;
				}
			}
		}
		if (board[9][0][0]==board[9][2][2]&&board[9][2][2]!= ' '){
			if (board[9][0][0]==hplayer){
				aiarrayweights[1][1] -= 20;
			}else{
				aiarrayweights[1][1] += 20;
			}
		}
		if (board[9][0][2]==board[9][2][0]&&board[9][2][0]!= ' '){
			if (board[9][0][0]==hplayer){
				aiarrayweights[1][1] -= 20;
			}else{
				aiarrayweights[1][1] += 20;
			}
		}
		if (board[9][0][0]==board[9][1][1]&& board[9][1][1]!= ' '){
			if (board[9][0][0]==hplayer){
				aiarrayweights[2][2] -= 20;
			}else{
				aiarrayweights[2][2] += 20;
			}
		}
		if (board[9][2][2]==board[9][1][1]&& board[9][1][1]!= ' '){
			if (board[9][2][2]==hplayer){
				aiarrayweights[0][0] -= 20;
			}else{
				aiarrayweights[0][0] += 20;
			}
		}
		if (board[9][0][2]==board[9][1][1]&& board[9][1][1]!= ' '){
			if (board[9][1][1]==hplayer){
				aiarrayweights[2][0] -= 20;
			}else{
				aiarrayweights[2][0] += 20;
			}
		}
		if (board[9][2][0]==board[9][1][1]&& board[9][1][1]!= ' '){
			if (board[9][1][1]==hplayer){
				aiarrayweights[0][2] -= 20;
			}else{
				aiarrayweights[0][2] += 20;
			}
		}
		int boardscore[] =  new int[] {0,0,0,0,0,0,0,0,0,0};
		int bestboard = 0;
		for (int k = 0; k<9; k++){
			for (int i = 0; i<3; i++){
				for(int j = 0; j<3; j++){
					if (board[k][i][j] == hplayer){
						boardscore[k+1] --;
					}else if(board[k][i][j]==aiplayer){
						boardscore[k+1] ++;
					}
				}
			}
			if (boardscore[k+1]>boardscore[k]){
				bestboard = k;
			}
			if (boardscore[k]>0){
				aiarrayweights[k/3][k%3] +=1;
			}
		}
		aiarrayweights[bestboard/3][bestboard%3] += 2;
		for (int i = 0; i<3;i++){
			for (int j = 0; j<3; j++){
				if (aiarrayweights[i][j]>currentleader){
					currentleader = aiarrayweights[i][j];
					aiarraychoice[0]= i;
					aiarraychoice[1]=j;
				}else if (aiarrayweights[i][j]==currentleader){
					Random rand = new Random();
					if (rand.nextInt(2)==0){
						aiarraychoice[0]= i;
						aiarraychoice[1]= j;
					}
				}
			}
		}
		return aiarraychoice;
	}
}
