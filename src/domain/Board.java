package domain;

import java.awt.Color;
import java.util.*;



import java.io.Serializable;

public class Board implements Serializable{
	
	//Attributes
	private static final long serialVersionUID = 1L;
	private Color colorPar;
	private Color colorImpar;
	private int nRows;
	private int nColumns;
	private Box[][] boxes;
	private int nBoxesSpecials;
	private int nStairs;
	private int nSnakes;
	private final String[] SPECIALSBOXES = {"Mortal", "SaltarinaN", "SaltarinaInversaN", "Avance", "Retroceso", "Preguntona"};
	private final String[] POSIBLESTRANSFORMERSNAKESTAIR = {"Dual","Weak"};
	private boolean isSnakeStairsTransform;
	
	private Map<Integer, int[]> positionsToCoordenates;
	//Constructor
	public Board(int x, int y, int nStairs, int nSnakes, int nBoxesSpecial, Map<Integer, int[]> positionsToCoordenates, Color colorPar, Color colorImpar, boolean canSnakeStairTransf) {
		this.colorImpar = colorImpar;
		this.colorPar = colorPar;
		boxes = new Box[x][y];
		setnColumns(y);
		setnRows(x);
		setnBoxesSpecials(nBoxesSpecial);
		setnSnakes(nSnakes);
		setnStairs(nStairs);
		this.positionsToCoordenates = positionsToCoordenates;
		
		if(canSnakeStairTransf) {
			isSnakeStairsTransform = true;
			putSnakeTransormer();
			putStairsTransormer();
		}else {
			putSnakes();
			putStairs();
		}
		
		putSpecialBoxes();
		putBoxes();
	}
	
	
	
	//Metodos
	
	
	
	public void print() {
		for(int i = 0; i<nRows ; i++) {
			String[] fila = new String[nColumns];
			int[] filaNumerada = new int[nColumns];
			for(int j =0; j<nColumns;j++) {
				fila[j] = this.boxes[i][j].print();
				int[] coord = {i,j};
				filaNumerada[j] = getPosition(coord);
			}
			System.out.println(Arrays.toString(fila));
			System.out.println(Arrays.toString(filaNumerada));
		}
	}
	public void printStairsSnakes() {
		for(int i = 0; i<nRows ; i++) {
			for(int j =0; j<nColumns;j++) {
				Box box = boxes[i][j];
				if(box instanceof SnakeStair) {
					SnakeStair s = (SnakeStair) box;
					System.out.println(s.print() + " pos: " + s.getPosition());
					System.out.println("Start: " + getPosition(s.getStart()) + " End: " + getPosition(s.getEnd()));
				}
			}
		}
	}
	
	/**
	 * Dada la posicion de una casilla retorna la posicion a la que lleva la pieza esta casilla.
	 * @param pos posicion de la casilla
	 * @return int[3] 
	 */
	
	public int[] powerOfBox(int pos) {
		int x = getCoordenate(pos)[0],y = getCoordenate(pos)[1];
		Box box = boxes[x][y];
		
		int[] result = new int[2];
		
		
		if (box instanceof NormalBox) {
			result[0] = x;
			result[1] = y;
			
			return result;
		}
		
		if (box instanceof SnakeStair) {
			SnakeStair s = (SnakeStair) box;
			if(box instanceof WeekSnakeStair) {
				
				return powerWeakSnakeStair(s);
				
			}else if (box instanceof DualSnakeStair&& box.isActive()) {
				int[] activeOtherBox = ((DualSnakeStair) boxes[x][y]).power();
				
				return activeOtherBox;
				 
				 
			}else if(box instanceof DualSnakeStair && (!box.isActive())) {
				return ((SnakeStair) box).getStart();
			}
			else {
				result[0] = s.getEnd()[0];
				result[1] = s.getEnd()[1];
			}
			
			
			
			return result;
		}
		
		if (box instanceof SaltarinaNBox) {
			int n = nRows*nColumns-box.getPosition();
			int min = (6>n)?n-2:6;
			((SaltarinaNBox) box).setJump(min);
			int jump = ((SaltarinaNBox) box).getJump();
			result[0] = getCoordenate(pos+jump)[0];
			result[1] = getCoordenate(pos+jump)[1];
			
			return result;
		}
		
		if(box instanceof SaltarinaInversaNBox) {
			
			((SaltarinaInversaNBox) box).setJump((6>pos) ? pos-2:6);
			int jump = ((SaltarinaInversaNBox) box).getJump();
			result[0] =(getCoordenate(pos-jump-1)[0] < 0) ?getCoordenate(pos-jump-1)[0]:nColumns-1;
			result[1] = (getCoordenate(pos-jump-1)[1] < 0)?getCoordenate(pos-jump-1)[1]:nRows-1;
		
			return result;
		}
		if(box instanceof MortalBox) {
			result[0] = getCoordenate(1)[0];
			result[1] = getCoordenate(1)[1];
			
			return result;
		}
		if(box instanceof AvanceBox) {
			result[0] = avanceStair(pos)[0];
			result[1] = avanceStair(pos)[1];
			
			return result;
		}
		if(box instanceof RetrocesoBox) {
			result[0] = retrocesoSnake(pos)[0];
			result[1] = retrocesoSnake(pos)[1];
			
			return result;
		}
		
		return result;
		
	}
	
	private int[] powerWeakSnakeStair(SnakeStair s) {
		int[] end = s.getEnd();
		int x1 = end[0],y1 = end[1],x2 = s.getStart()[0],y2=s.getStart()[1];
		int x =(int)(((double)x1+(double)x2)/2.0);
		int y =(int)(((double)y1+(double)y2)/2.0);
		int[] newEnd = {x,y};
		s.setEnd(newEnd);
		if(x1==x2) {
			s.setEnd(new int [] {x2,y2});
			boxes[x2][y2] = new NormalBox(new Color(1,2,3), s.getCoordenate(),s.getPosition());
			return new int [] {x2,y2};
		}else {
			return end;
		}
 	}
	
	public int[] retrocesoSnake(int pos) {
		for(int  i = pos-1; 0<i; i--) {
			if( (getBox(i) instanceof SnakeStair) && ((SnakeStair)getBox(i)).isSnake()&& (getBox(i).isActive()))return getCoordenate(i);
		}
		return new int[]{nRows-1,nColumns-1};
	}
	
	public int[] avanceStair(int pos) {
		
		for(int i = pos + 1; i<nColumns*nRows; i++) {
			if((getBox(i) instanceof SnakeStair) && !((SnakeStair)getBox(i)).isSnake()&& (getBox(i).isActive())) return getCoordenate(i);
		}
		return new int[]{nRows-1,nColumns-1};
		
	}
	
	
	
	private int[] generateRandomCoord() {
		int x =(int) Math.floor(Math.random()*this.nRows);
		int y = (int) Math.floor(Math.random()*this.nColumns);
		int[] pos = {x,y};
		if(pos[0] == 0 && pos[1] == 0 || pos[0] == 0 && pos[1] == nColumns-1)return generateRandomCoord();
		return pos;
	}
	
	private String generateRandomSelctionForSpecialBox() {
		int n = (int)Math.floor(Math.random()*this.SPECIALSBOXES.length);
		return this.SPECIALSBOXES[n];
	}
	
	private String generateRandomSelctionForTransformerSnakeStair() {
		int n = (int)Math.floor(Math.random()*this.POSIBLESTRANSFORMERSNAKESTAIR.length);
		return this.POSIBLESTRANSFORMERSNAKESTAIR[n];
	}
	
	public void putStairs() {
		for(int i = 0; i<this.nStairs; i++) {
			boolean flag = true;
			while(flag) {
				int[] coord = generateRandomCoord();
				int x=coord[0], y=coord[1];
				if(x!=0 && this.boxes[x][y] == null && !isFinal(x, y)) {
					Color color = ((x+y)%2==0)?colorPar:colorImpar;
					boxes[x][y] = new NormalSnakeStair(color, coord, false, nColumns, nRows, getPosition(coord));
					flag = false;
				}
			}
		}
	}
	
	public void putSnakes() {
		for(int i = 0; i<this.nSnakes; i++) {
			boolean flag = true;
			while(flag) {
				int[] coord = generateRandomCoord();
				int x=coord[0], y=coord[1];
				if(x!= nRows-1 && this.boxes[x][y] == null && !isFinal(x, y)) {
					Color color = ((x+y)%2==0)?colorPar:colorImpar;
					boxes[x][y] = new NormalSnakeStair(color, coord, true, nColumns, nRows, getPosition(coord));
					flag = false;
				}
			}
		}
	}
	
	private void putStairsTransormer() {
		for(int i = 0; i<this.nStairs; i++) {
			boolean flag = true;
			while(flag) {
				int[] coord = generateRandomCoord();
				int x=coord[0], y=coord[1];
				if(x!=0 && this.boxes[x][y] == null && !isFinal(x, y)) {
					String tipeStair = generateRandomSelctionForTransformerSnakeStair();
					Color color = ((x+y)%2==0)?colorPar:colorImpar;
					Color colorAux = ((x+y)%2==0)?colorImpar:colorPar;
					if(tipeStair.equals("Dual")) {
						boxes[x][y] = new DualSnakeStair(color, coord, false, nColumns, nRows, getPosition(coord), true);
						int [] aux =((SnakeStair)boxes[x][y]).getEnd();
						boxes[aux[0]][aux[1]]= new DualSnakeStair(colorAux, aux, true, nColumns, nRows, getPosition(aux),false);
						((SnakeStair)boxes[aux[0]][aux[1]]).setEnd(coord);
					}else if(tipeStair.equals("Weak")) {
						boxes[x][y] = new WeekSnakeStair(color, coord, false, nColumns, nRows, getPosition(coord));
					}
					
					if(boxes[x][y]!=null)flag = false;
				}
			}
		}
	}
	
	private void putSnakeTransormer() {
		for(int i = 0; i<this.nStairs; i++) {
			boolean flag = true;
			while(flag) {
				int[] coord = generateRandomCoord();
				int x=coord[0], y=coord[1];
				if(x!= nRows-1 && this.boxes[x][y] == null && !isFinal(x, y))  {
					String tipeStair = generateRandomSelctionForTransformerSnakeStair();
					Color color = ((x+y)%2==0)?colorPar:colorImpar;
					Color colorAux = ((x+y)%2==0)?colorImpar:colorPar;
					if(tipeStair.equals("Dual")) {
						boxes[x][y] = new DualSnakeStair(color, coord, true, nColumns, nRows, getPosition(coord), true);
						int [] aux =((SnakeStair)boxes[x][y]).getEnd();
						boxes[aux[0]][aux[1]]= new DualSnakeStair(colorAux, aux, false, nColumns, nRows, getPosition(aux),false);
						((SnakeStair)boxes[aux[0]][aux[1]]).setEnd(coord);
					}else if(tipeStair.equals("Weak")) {
						boxes[x][y] = new WeekSnakeStair(color, coord, true, nColumns, nRows, getPosition(coord));
					}
					
					if(boxes[x][y]!=null)flag = false;
				}
			}
		}
	}
	
	public void putSpecialBoxes() {
		for(int i = 0; i<this.nBoxesSpecials-nSnakes*2-nStairs*2-1; i++) {
			boolean flag = true;
			while(flag) {
				int[] coord = generateRandomCoord();
				int pos = getPosition(coord);
				int x=coord[0], y=coord[1];
				if(this.boxes[x][y] == null) {
					
					Color color = ((x+y)%2==0)?colorPar:colorImpar;
					String c = generateRandomSelctionForSpecialBox();
					
					if(!isFinal(x,y)) {
						if(c.equals("Mortal") && x!= nColumns-1 && y != 0) {
							this.boxes[x][y] = new MortalBox(color, coord, getPosition(coord));
						}
						else if(c.equals("SaltarinaN")) {
							this.boxes[x][y] = new SaltarinaNBox(color, coord, getPosition(coord));
						}
						else if(c.equals("SaltarinaInversaN") && x!= nColumns-1 && y != 0) {
							this.boxes[x][y] = new SaltarinaInversaNBox(color, coord, getPosition(coord));
						}
						else if(c.equals("Avance") && canBeAvance(pos)) {
							this.boxes[x][y] = new AvanceBox(color, coord, getPosition(coord));
						}
						else if(c.equals("Retroceso") && pos >1 && canBeRetroceso(pos)) {
							this.boxes[x][y] = new RetrocesoBox(color, coord, getPosition(coord));
						}else if(c.equals("Preguntona")) {
							this.boxes[x][y] = new PreguntonaBox(color, coord, getPosition(coord));
						}
					}
					if(this.boxes[x][y] != null)flag = false;
				}
			}
		}
	}
	
	public boolean isFinal(int x, int y) {
		for(int i = 0; i<nRows; i++) {
			for(int j = 0; j< nColumns; j++) {
				Box box = boxes[i][j];
				if(box instanceof SnakeStair) {
					SnakeStair s = (SnakeStair) box;
					int[] end = s.getEnd();
					if(end[0]==x & end[1]==y)return true;
					
				}
			}
		}
		return false;
	}
	
	
	public boolean canBeRetroceso(int pos) {
		for(int  i = pos-1; 0<i; i--) {
			if( (getBox(i) instanceof SnakeStair) && ((SnakeStair)getBox(i)).isSnake())return true;
		}
		return false;
	}
	
	
	public boolean canBeAvance(int pos) {
		for(int  i = pos+1; i<nColumns+nRows; i++) {
			if( (getBox(i) instanceof SnakeStair) && !((SnakeStair)getBox(i)).isSnake())return true;
		}
		return false;
	}

	public void putBoxes() {
		for(int i = 0; i<nRows; i++) {
			for(int j = 0; j < nColumns; j++) {
				if(boxes[i][j] == null) {
					int[] coord = {i,j};
					Color color = ((i+j)%2==0)?colorPar:colorImpar;
					boxes[i][j] = new NormalBox(color, coord, getPosition(coord));
				}
			}
		}
	}
	
	//Getters & setters
	
	public int getPosition(int[] coord) {
		for(Integer k: this.positionsToCoordenates.keySet()) {
			int[] coord_=this.positionsToCoordenates.get(k);
			if(coord[1] == coord_[1] && coord[0] == coord_[0]) return k;
		}
		return 0;
	}
	
	public int[] getCoordenate(int pos) {
		return this.positionsToCoordenates.get(pos);
	}
	
	public int getnRows() {
		return nRows;
	}


	public void setnRows(int nRows) {
		this.nRows = nRows;
	}


	public int getnColumns() {
		return nColumns;
	}


	public void setnColumns(int nColumns) {
		this.nColumns = nColumns;
	}

	public Box[][] getBoxes(){
		return this.boxes;
	}
	
	public Box getBox(int pos) {
		int[] coord = getCoordenate(pos);
		return boxes[coord[0]][coord[1]];
	}
	
	public Box getBox(int[] coord) {
		return boxes[coord[0]][coord[1]];
	}




	public int getnBoxesSpecials() {
		return nBoxesSpecials;
	}


	public void setnBoxesSpecials(int nBoxesSpecials) {
		this.nBoxesSpecials = nBoxesSpecials;
	}


	public int getnStairs() {
		return nStairs;
	}


	public void setnStairs(int nStairs) {
		this.nStairs = nStairs;
	}


	public int getnSnakes() {
		return nSnakes;
	}


	public void setnSnakes(int nSnakes) {
		this.nSnakes = nSnakes;
	}



	public boolean isSnakeStairsTransforms() {
		return isSnakeStairsTransform;
	}
	
    public void setActivePairDual(int[]activeOtherBox) {
    	int x_1= activeOtherBox[0];
    	int y_1= activeOtherBox[1];
    	boxes[x_1][y_1].setActive(true);
    }
    
}
