/**
 * 
 */
package domain;
import java.io.Serializable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author Camacho-Mesa
 *
 */
public class PoobStairs implements Serializable{
	
	//Attributes
	private static final long serialVersionUID = 1L;
	private int nRows;
	private int nColumns;
	private int nSnakes;
	private int nStairs;
	private double percentageSpecialBox;

	private Player player1;
	private Player player2;
	private Dice dice;

	private boolean canSnakeStairsTransformer;
	private double percentageModifier;
	private Board board;
	
	private Map<Integer, int[]> positionsToCoordenates;
	
	//Constructors
		public PoobStairs (int nRows, int nColumns, int nSnakes, int nStairs, double percentageSpecialBox, String namePlayer1, String namePlayer2,
				Color colorPlayer1, Color colorPlayer2, int probModifier, boolean canSnakeStairTransformer) throws PoobStairsException{
			
			if(namePlayer1 == null || namePlayer2== null || colorPlayer1== null || colorPlayer2 == null ) throw new PoobStairsException(PoobStairsException.EMPTYSPACE);
			if(nRows <=0 || nColumns<=0 || nSnakes<0 || nStairs<0 || percentageSpecialBox<0)throw new PoobStairsException(PoobStairsException.INVALIDVALUE);
			if(nRows*nColumns <= nSnakes*2+nStairs*2)throw new PoobStairsException(PoobStairsException.INVALIDVALUE);
			this.positionsToCoordenates = new TreeMap<Integer, int[]>();
			setPercentageModifier(probModifier);
			setnColumns(nColumns);
			setnRows(nRows);
			setnSnakes(nSnakes);
			setnStairs(nStairs);
			
			setPositionToCoordenate();
			
//			printPositionCoordenates();
			
			
			double x = ((double)nRows*(double)nColumns)/100.0;
			int nBoxesSpecials = (int) (x*percentageSpecialBox);
			
			this.board = new Board(nRows, nColumns, nStairs, nSnakes, nBoxesSpecials, this.positionsToCoordenates, new Color(1,2,3), new Color(1,2,6),canSnakeStairTransformer);
			
			this.player1 = new HumanoPlayer(namePlayer1, colorPlayer1);
			this.player2 = new HumanoPlayer(namePlayer2, colorPlayer2);
			dice = new Dice((int) this.percentageModifier);
		}
	

	
		//Constructors
		public PoobStairs (int nRows, int nColumns, int nSnakes, int nStairs, double percentageSpecialBox, String namePlayer1, 
				Color colorPlayer1,  int probModifier, boolean canSnakeStairTransformer, int typeMachine) throws PoobStairsException{
			
			if(namePlayer1 == null || colorPlayer1== null ) throw new PoobStairsException(PoobStairsException.EMPTYSPACE);
			if(nRows <=0 || nColumns<=0 || nSnakes<0 || nStairs<0 || percentageSpecialBox<0)throw new PoobStairsException(PoobStairsException.INVALIDVALUE);
			if(nRows*nColumns <= nSnakes*2+nStairs*2)throw new PoobStairsException(PoobStairsException.INVALIDVALUE);
			this.positionsToCoordenates = new TreeMap<Integer, int[]>();
			setPercentageModifier(probModifier);
			setnColumns(nColumns);
			setnRows(nRows);
			setnSnakes(nSnakes);
			setnStairs(nStairs);
			
			setPositionToCoordenate();
			
//			printPositionCoordenates();
			
			
			double x = ((double)nRows*(double)nColumns)/100.0;
			int nBoxesSpecials = (int) (x*percentageSpecialBox);
			
			this.board = new Board(nRows, nColumns, nStairs, nSnakes, nBoxesSpecials, this.positionsToCoordenates, new Color(1,2,3), new Color(1,2,6),canSnakeStairTransformer);
			
			this.player1 = new HumanoPlayer(namePlayer1, colorPlayer1);

			this.player2 = typeMachine == 1 ? new MachinePlayerAprentice("Machine Aprendice", Color.black): new MachinePlayerBegginer("Machine Begginer", Color.ORANGE);
			
			dice = new Dice((int) this.percentageModifier);
		}
	
	

	

	
	//Main
	public static void main(String [] args) throws PoobStairsException {
		PoobStairs game = new PoobStairs(5, 5, 5, 5, 0.0,"","",new Color(1,2,3),new Color(0,9,8),60,true);
//		int[] coord = {2,3};
//		System.out.println(game.getPosition(coord));
//		System.out.println(Arrays.toString(game.getCoordenate(16)));
		Board b = game.getBoard();
//		b.printColor();
		b.print();
		b.printStairsSnakes();	
		game.movePlayer(1, 5);
		game.player1.print();
		b.printStairsSnakes();	
		game.movePlayer(2, 5);
		game.player2.print();
		b.printStairsSnakes();	
		
		game.movePlayer(1, 5);
		game.player1.print();
		b.printStairsSnakes();	
		game.movePlayer(2, 5);
		game.player2.print();
		b.printStairsSnakes();	
		
		

	}
	
	//Metodos
	
	public int win() {
		if (player1.getPiece().getPosition() == nRows*nColumns) return 1;
		if (player2.getPiece().getPosition() == nRows*nColumns) return 2;
		return 0;
	}
	
	public void print(ArrayList<int[]> a) {
		for(int[] p:a) {
			System.out.println(Arrays.toString(p));
		}
	}
	
	

	
	/**
	 * Metodo que te retorna los posibles movimientos que puede obtar el jugador 1 al lanzar el dado
	 * @return ArrayList<int[]> en la primera posicion un array de dos posiciones {#dadoObtenido, #modificadot} luego son arrays de una posicion que representan los posibles movimientos
	 */
	public ArrayList<int[]> playWithOptionPlayer1(){
		return playWithOption(player1,player2);
	}
	/**
	 * Metodo que te retorna los posibles movimientos que puede obtar el jugador 1 al lanzar el dado
	 * @return ArrayList<int[]> en la primera posicion un array de dos posiciones {#dadoObtenido, #modificadot} luego son arrays de una posicion que representan los posibles movimientos
	 */

	
	/**
	 * Metodo que te retorna los posibles movimientos que puede obtar el jugador 2 al lanzar el dado
	 * @return ArrayList<int[]> en la primera posicion un array de dos posiciones {#dadoObtenido, #modificadot} luego son arrays de una posicion que representan los posibles movimientos
	 */
	public ArrayList<int[]> playWithOptionPlayer2(){
		return playWithOption(player2, player1);
	}
	
	
	private ArrayList<int[]> playWithOption(Player player, Player otherPlayer){
		
		int pos = player.getPiece().getPosition();

		ArrayList<int[]> result = new  ArrayList<int[]>();
		
		int[] dadoResult = dice.playDiceWithModifier();
		result.add(dadoResult);
		
		if( pos + dadoResult[0] > nColumns*nRows)return result;//Esto es en caso de que saco un numero que al mover la ficha se pasa de la casilla final del tablero\
		
		int m = dadoResult[0];
		result.add(new int[] {m});
		
		//En caso de sacar un modificador de avance o retroceso:
		if(dadoResult[1] == 1 || dadoResult[1] == 2) {
			int n =(dadoResult[1] == 1 )? m+1:m-1;
			result.add(new int[] {n});
			player.isMultiply();
		}
		if (dadoResult[1]== 3) {
			int position = (nRows*nColumns)-otherPlayer.getPiece().getPosition();
			if(position<10 && player.isMultiply()) {
				player.setMultiply(false);
				Random random = new Random();
				int p = random.nextInt(6) +1;
				int n = m *p ;
				if (n < nRows*nColumns) {
					result.add(new int[] {n});
				}
				player.setMultiply(false);
				
			}else {
				dadoResult[1]= 0;
				result.set(0, dadoResult);
			}
			
		}
		if (dadoResult[1]== 4) {
			result.add(new int[] {-5});
		}
		
		int k;
		if(dadoResult[1] == 2) {
			k=m-1;
		}else {
			k=m;
		}
		
		for(int i = 1; i<k; i++){
			int newPos = i+pos;
			Box nextBox = board.getBox(newPos);
			
			if(!(nextBox instanceof NormalBox || nextBox instanceof SnakeStair)) {
				
				result.add(new int[] {i});
		
			}
		}
		
		return result;
	}
	/**
	 * hace el cambio de pieza entre jugadores (usado en el modificador de cambio de fichas)
	 * @param player
	 * @param otherPlayer
	 * @param nJugador
	 * @return
	 */
	public ArrayList<int[]> modifierChangePiece(Player player, Player otherPlayer,int nJugador) {
		ArrayList<int[]> positionsPlayers = new ArrayList<int[]>();
		Piece copy = player.getPiece();
		player.setPiece(otherPlayer.getPiece());
		otherPlayer.setPiece(copy);
		positionsPlayers.add( getCoordenate(player.getPiece().getPosition()));
		positionsPlayers.add( getCoordenate(otherPlayer.getPiece().getPosition()));
		return positionsPlayers;
		
	}
	
		public ArrayList<int[]> playWithOptionTest(Player player, Player otherPlayer, int [] dice){
//				otherPlayer.getPiece().setPosition(nColumns*nRows-2);
				int otherPos =( nRows * nColumns )-otherPlayer.getPiece().getPosition();
				int pos = player.getPiece().getPosition();
		
				ArrayList<int[]> result = new  ArrayList<int[]>();
				
				int[] dadoResult = dice;
				result.add(dadoResult);
				
				if( pos + dadoResult[0] > nColumns*nRows)return result;//Esto es en caso de que saco un numero que al mover la ficha se pasa de la casilla final del tablero\
			
			int m = dadoResult[0];
			result.add(new int[] {m});
			
			//En caso de sacar un modificador de avance o retroceso:
			if(dadoResult[1] == 1 || dadoResult[1] == 2) {
				int n =(dadoResult[1] == 1 )? m+1:m-1;
				result.add(new int[] {n});
			}
			if (dadoResult[1]== 3) {

				//int position = (nRows*nColumns)-otherPlayer.getPiece().getPosition();
				if(otherPos<10 && player.isMultiply()) {
					player.setMultiply(false);
					Random random = new Random();
					int p = random.nextInt(6) +1;
					int n = m *p ;
					if (n < nRows*nColumns) {
						result.add(new int[] {n});
					}
					player.setMultiply(false);
					
					
				}else {
					dadoResult[1]= 0;
					
					result.set(0, dadoResult);
					
				}
				
			}
			
			int k;
			if(dadoResult[1] == 2) {
				k=m-1;
			}else {
				k=m;
			}
			
			for(int i = 1; i<k; i++){
				int newPos = i+pos;
				Box nextBox = board.getBox(newPos);
				
				if(!(nextBox instanceof NormalBox || nextBox instanceof SnakeStair)) {
					
					result.add(new int[] {i});
			
				}
			}
			
			return result;
		}
		/**
		 * usa la recursión para moverse en caso de que caiga varias veces en una casilla especial.
		 * @param box
		 * @param array
		 * @param player
		 * @return
		 * @throws PoobStairsException
		 */
		private ArrayList<int[]> movePieceTo(Box box, ArrayList<int[]> array,Player player ) throws PoobStairsException{
			
			modifyDatesPlayer(box, player);
			movePiece(player, box.getPosition()); // update position
			array.add(box.getCoordenate());
			if(box instanceof PreguntonaBox)throw new  PoobStairsExceptionWithArray(PoobStairsException.BOXPREGUNTONA,array);
			
			
			if((box instanceof NormalBox)||(!(box.isActive()))) {
				if(box instanceof SnakeStair) {
					int [] cordenada = ((SnakeStair) box).getEnd();
					if(board.getBox(cordenada).isActive() && (((SnakeStair)board.getBox(cordenada)).isSnake())) {
						
					}else {
						board.setActivePairDual(box.getCoordenate());

					}
				}
				
				return(array);
			}
			Box box_ = board.getBox(board.powerOfBox(box.getPosition()));
			return movePieceTo(box_,array,player);
		}
	

	public Object[][] movePlayerPreguntona(int nPlayer, int movimientos){
		
		//<ArrayList<int[]>,TreeMap<String, String[]>>
		return null;
	
	}
	/**
	 * 
	 * @param nPlayer numero del jugador que se quiere mover
	 * @param moves los moviminetos que se va a mover el jugador
	 * @return la sucesion de coordenada que se mueve el jugador 
	 */
	public ArrayList<int[]> movePlayer(int nPlayer, int moves) throws PoobStairsException {
		
		ArrayList<int[]> result = new  ArrayList<int[]>();
		
		Player player = (nPlayer ==1)?player1:player2; 
		int pos = player.getPos();
		if (pos + moves > nRows * nColumns) throw new PoobStairsException(PoobStairsException.INVALIDVALUE);
		for(int i = pos + 1; i<=pos + moves ; i++) {
			if(i == pos + moves) {
				return movePieceTo(board.getBox(i),result,(nPlayer==1)?player1:player2);
			}else {
				result.add(getCoordenate(i));
			}
		}
		return result;
	}
	
	
	private  ArrayList<int[]> MovePiecePlayer(Player player){
		
		 ArrayList<int[]> result = new  ArrayList<int[]>();
		 
		 int[] dadoResult = dice.playDiceWithModifier();//int[2] donde la primera es el numero del dado la segunda es el modificador
		 int[] firstPos = { dadoResult[0], dadoResult[1], 0};
		 result.add(firstPos);
		 int n;
		 if(dadoResult[1]!=0) {
			 n =(dadoResult[1] == 1 )? dadoResult[0]+1:dadoResult[0]-1;
		 }else {
			 n = dadoResult[0];//Resultado que saco en el lanzamiento
		 }
		 
		 int pos = player.getPiece().getPosition();//Posible nueva posicion de la ficha
		 
		 if( pos + n <= nColumns*nRows) {
			 
			 for(int i = 1; i<n+1;i++) {
				 
				 Box box = board.getBox(getCoordenate(pos+i));
				 int newPos = box.getPosition();
				 int[] newCoord = box.getCoordenate();
				 
				 movePiece(player, newPos);
				 
				 if(i==n) {
					 if(box instanceof NormalBox) {
						 result.add(board.powerOfBox(newPos));
						 return result;
					 }else {
						 int[] newAdd = {newCoord[0], newCoord[1], 0};
						 result.add(newAdd);
//						 int[] lastCoord = new int[3];
//						 lastCoord[0] = board.powerOfBox(newPos)[0];
//						 lastCoord[1] = board.powerOfBox(newPos)[1];
//						 lastCoord[2] = 0;
//						 result.add(lastCoord);
						 int[] lastCoord = board.powerOfBox(newPos);
						 result.add(lastCoord);
						 int[] newCoord_ = {lastCoord[0],lastCoord[1]};
						 movePiece(player, getPosition(newCoord_));
						 
						 if(board.getBox(new int[] {lastCoord[0],lastCoord[1]}) instanceof SnakeStair) {
							 int t = getPosition(new int[] {lastCoord[0],lastCoord[1]});
							 result.add(board.powerOfBox(t));
							 movePiece(player, getPosition(new int[] {board.powerOfBox(t)[0],board.powerOfBox(t)[1]}));
						 }
							
						 return result;
					}
				 }
				 
				 int[] newAdd_ = {newCoord[0],newCoord[1],box.getId()};
				 result.add(newAdd_);
//				 if(newAdd_[2]!=0) return result; 
			 }
		 }
		 return result; 
	}
	
	public void movePiece(Player player, int newPos) {
		player.getPiece().setPosition(newPos);
	}
	
	public void modifyDatesPlayer(Box box, Player player){
		
		if(!(box instanceof NormalBox)) {
			if(box instanceof SnakeStair) {
				if(((SnakeStair) box).isSnake()) {
					int nSnakeTake =player.getnSnakeTakes();
					player.setnSnakeTakes(nSnakeTake +1);
				}else {
					int nStairsTake =player.getnStairsTake();
					player.setnStairsTake(nStairsTake+1);
				}
			}else {
				int nSpecialBoxTakes = player.getnBoxesEspecialActive();
				player.setnBoxesEspecialActive(nSpecialBoxTakes+1);
			}
		}
//		player.getPiece().setPosition(board.powerOfBox(box.getPosition()));
	}
	
	
	//Getters & setters
	
	public HashMap<String, String> getPreguntas(){
		
		HashMap<String, String> preguntas = new HashMap<String, String>();
		preguntas.put("¿Van Gogh murio en el año 1890?", "Si");
		preguntas.put("¿Gabriel Garcia Marques escribio la novela -El Código Da Vinci-", "No");
		preguntas.put("¿Metalico es una palabra esdrujula?", "Si");
		preguntas.put("¿Ana Frank escribio -El diario de Ana Frank-?", "Si");
		
		
		return  preguntas;
	}
	
	public Player getPlayer1() {
		return this.player1;
	}
	public Player getPlayer2() {
		return this.player2;
	}
	public Color getColorPlayer1() {
		return player1.getColor();
	}
	public Color getColorPlayer2() {
		return player2.getColor();
	}
	/**
	 * 
	 * @return la informacion del jugador de la siguiente manera. Primero: Posicion pieza, Segundo: nSnakeTake, tercero; nStairsTake ,cuarto: NBoxesactive y quinto: maxima posicion alcanzada
	 * 
	 */
	public int[] getInformationPlayer1() {
		return player1.getInformation();
	}
	
	/**
	 * 
	 * @return la informacion del jugador de la siguiente manera. Primero: Posicion pieza, Segundo: nSnakeTake, tercero; nStairsTake ,cuarto: NBoxesactive y quinto: maxima posicion alcanzada
	 * 
	 */
	public int[] getInformationPlayer2() {
		return player2.getInformation();
	}
	
	
	public Box[][] getBoxes(){
		return this.board.getBoxes();
	}
	
	public void printPositionCoordenates() {
		for(int k = 1; k<nRows*nColumns + 1; k++) {
			System.out.println("Key: " + k + " Value: " + Arrays.toString(getCoordenate(k)));
			
		}
	}
	
	
	
	/**
	 * @param pos la posicion del tablero a la que se le quiere acceder la coordenada
	 * @return int[] representa la coordenada del tablero a la que se le asigna la posicon pos
	 */
	public int[] getCoordenate(int pos) {
		return this.positionsToCoordenates.get(pos);
	}
	
	public int getPosition(int[] coord) {
		for(Integer k: this.positionsToCoordenates.keySet()) {
			int[] coord_ = this.positionsToCoordenates.get(k);
			if(coord[0]==coord_[0] && coord[1]==coord_[1] ) return k;
		}
		return 0;
	}
	
	
	public void setPositionToCoordenate() {
		int paridad = 2;
		int pos = 1;
		for(int i = nRows - 1; i>-1; i--) {
			if(paridad % 2 == 0) {
				for(int j = 0; j<nColumns; j++) {
					int[] coord = {i,j};
					
					this.positionsToCoordenates.put(pos++,coord);
				}
			}else {
				for(int j = nColumns - 1; j>-1;j--) {
					int[] coord = {i,j};
					this.positionsToCoordenates.put(pos++,coord);
				}
			}
			paridad++;
		}
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public int getnRows() {
		return nRows;
	}


	public void setnRows(int nRows) {
		this.nRows = nRows;
	}


	/**
	 * @return the nColumns
	 */
	public int getnColumns() {
		return nColumns;
	}


	/**
	 * @param nColumns the nColumns to set
	 */
	public void setnColumns(int nColumns) {
		this.nColumns = nColumns;
	}


	/**
	 * @return the nSnakes
	 */
	public int getnSnakes() {
		return nSnakes;
	}


	/**
	 * @param nSnakes the nSnakes to set
	 */
	public void setnSnakes(int nSnakes) {
		this.nSnakes = nSnakes;
	}


	/**
	 * @return the nStairs
	 */
	public int getnStairs() {
		return nStairs;
	}


	/**
	 * @param nStairs the nStairs to set
	 */
	public void setnStairs(int nStairs) {
		this.nStairs = nStairs;
	}


	/**
	 * @return the percentageSpecialBox
	 */
	public double getPercentageSpecialBox() {
		return percentageSpecialBox;
	}


	/**
	 * @param percentageSpecialBox the percentageSpecialBox to set
	 */
	public void setPercentageSpecialBox(double percentageSpecialBox) {
		this.percentageSpecialBox = percentageSpecialBox;
	}


	/**
	 * @return the canSnakeStairsTransformer
	 */
	public boolean isCanSnakeStairsTransformer() {
		return canSnakeStairsTransformer;
	}


	/**
	 * @param canSnakeStairsTransformer the canSnakeStairsTransformer to set
	 */
	public void setCanSnakeStairsTransformer(boolean canSnakeStairsTransformer) {
		this.canSnakeStairsTransformer = canSnakeStairsTransformer;
	}


	/**
	 * @return the percentageModifier
	 */
	public double getPercentageModifier() {
		return percentageModifier;
	}


	/**
	 * @param percentageModifier the percentageModifier to set
	 */
	public void setPercentageModifier(double percentageModifier) {
		this.percentageModifier = percentageModifier;
	}

	/**
	 * @return the dice
	 */
	public Dice getDice() {
		return dice;
	}

	/**
	 * @param dice the dice to set
	 */
	public void setDice(Dice dice) {
		this.dice = dice;
	}
	/**
	 * dice si se esta usando transformadores.
	 * @return
	 */
	public boolean isSnakeStairsTransform() {
		return board.isSnakeStairsTransforms();
	}
	/**
	 * pasa el jugador que le diga 1 o 2
	 * @param i
	 * @return
	 */
	public Player getPlayer(int i) {
		Player player = i == 1 ? player1:player2;
		return player;
	}
	/**
	 * Me dice el nombre de la maquina que esta jugando.
	 * @return
	 */
	public String getNameMachine() {
		if (player2 instanceof MachinePlayer) {
			return player2.getName();
		}
		return "Isn´t machine";
	}
}
