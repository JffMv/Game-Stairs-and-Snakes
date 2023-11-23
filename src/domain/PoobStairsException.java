package domain;

@SuppressWarnings("serial")
public class PoobStairsException extends Exception{
	
	public static final String EMPTYSPACE = "Empty field!";
	public static final String INVALIDVALUE = "Invalid value(s)!";
	public static final String BOXPREGUNTONA = "BOX PREGUNTONA";
	public PoobStairsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
