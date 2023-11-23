package domain;

import java.util.ArrayList;

public class PoobStairsExceptionWithArray extends PoobStairsException {
    private ArrayList<int[]> array;
    
    public PoobStairsExceptionWithArray(String message, ArrayList<int[]>  array) {
        super(message);
        this.array = array;
    }

    public ArrayList<int[]>  getArray() {
        return array;
    }
}