import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

public class DFA  {
	 static String fileName;
	 static String test;
	 static String ans;
	 
	
	public DFA ( String fileName, String test) {
		this.fileName = fileName;
		this.test = test;
	}
	
	
	
	public void getFinal() throws FileNotFoundException {
	 //Assigning File to a scanner
		String head ="src/";
		String line = ".txt";
		File file = new File(head+fileName+line);
    	Scanner input2 = new Scanner(file);
    	DFA1 dfa = new DFA1(input2);
       // String testString = test.next();
    	StringBuilder input = new StringBuilder(test);//input from commandline
    	
    	
    	String currState = dfa.getStart();
    	
    	while (input.length() > 0) {
        	boolean findState = false;
        	int TFLocation = 0;
        	
        	for (int i = 0; i < dfa.getNumTF() && findState == false; i++) {
            	if (dfa.getTransFuncL().get(i)[0] == input.charAt(0)) {
                	if (dfa.getTransFuncL().get(i)[1] == currState.charAt(0)) {
                    	findState = true;
                    	TFLocation = i;
                	}
            	}
        	}
        	
        	char currStateChar = currState.charAt(0);
        	System.out.print(currState);
        	currState = currState.replace(currStateChar, dfa.getTransFuncL().get(TFLocation)[2]);
        	System.out.println(currState);
        	input.deleteCharAt(0);
    	}
    	
    	for (int i = 0; i < dfa.getAcceptL().size(); i++) {
        	if (currState.charAt(0) == dfa.getAcceptL().get(i)) {
            	ans =("Your string passed the DFA!");
        	} else {
            	ans =("Sorry, your string didn't work with the DFA.\nShutting down...");
        	}
    	}
    	//input2.close();
    	//in.close();
	}

	public static String getAns() {
		return ans;
	}
}


class DFA1  {
	// Data Members
	private ArrayList<Character> alphabetL;
	private ArrayList<Character> statesL;
	private ArrayList<Character> acceptL;
	private ArrayList<Character[]> transFuncL;
	private String start;
	// Number of transition functions
	private int numTF;
	
	// Constructor
	// Use StringBuilder for transFuncin order to delete individual characters
	public DFA1(Scanner file) {
    	String alphabet = null;
    	String states = null;
    	String accept = null;
    	String transFuncAsString = null;
    	StringBuilder transFunc = new StringBuilder("");
    	// Assigns data from the text file to appropriate variables
    	
    	alphabet = file.nextLine().replaceAll("[^A-Za-z0-9]+","");
    	states = file.nextLine().replaceAll("[^A-Za-z0-9]+","");
    	start = file.nextLine().replaceAll("[^A-Za-z0-9]+","");
    	accept = file.nextLine().replaceAll("[^A-Za-z0-9]+","");
    	
    	while (file.hasNext()) {
        	transFuncAsString = file.nextLine();
        	transFunc.append(transFuncAsString.replaceAll("[^A-Za-z0-9]+",""));
        	numTF++;
    	}
    	// Instantiate and populate alphabet list
    	alphabetL = new ArrayList<>();
    	for (int i = 0; i < alphabet.length(); i++) {
        	alphabetL.add(alphabet.charAt(i));
    	}
    	// Instantiate and populate state list
    	statesL = new ArrayList<>();
    	for (int i = 0; i < states.length(); i++) {
        	statesL.add(states.charAt(i));
    	}
    	// Instantiate and populate accept list
    	acceptL = new ArrayList<>();
    	for (int i = 0; i < accept.length(); i++) {
        	acceptL.add(accept.charAt(i));
    	}
    	// Set start variable
    	setStart(start);
    	// Instantiate and populate transition function list
    	transFuncL = new ArrayList<>(numTF);
    	// (a, 0) -> b == ["0", "a", "b"] Determine character in demo string first at
    	// [0] (ex. 1000101), then match to current state at [1], finally access the
    	// next state in [2]
    	// Repeat for loop for number of rules
    	for (int i = 0; i < numTF; i++) {
        	Character[] rule = new Character[3];
        	rule[0] = transFunc.charAt(1);
        	rule[1] = transFunc.charAt(0);
        	rule[2] = transFunc.charAt(2);
        	// Delete Char to reuse loop
        	for (int j = 0; j < 3; j++) {
            	transFunc.deleteCharAt(0);
        	}
        	// Add function(as array) to whole list
        	transFuncL.add(i, rule);
    	}
	}
	
	/************** ACCESSORS/MUTATORS **************************/
	public ArrayList<Character> getAlphabetL() {
    	return alphabetL;
	}
	public void setAlphabetL(ArrayList<Character> alphabetL) {
    	this.alphabetL = alphabetL;
	}
	public ArrayList<Character> getStatesL() {
    	return statesL;
	}
	public void setStatesL(ArrayList<Character> statesL) {
    	this.statesL = statesL;
	}
	public ArrayList<Character> getAcceptL() {
    	return acceptL;
	}
	public void setAcceptL(ArrayList<Character> acceptL) {
    	this.acceptL = acceptL;
	}
	public ArrayList<Character[]> getTransFuncL() {
    	return transFuncL;
	}
	public void setTransFuncL(ArrayList<Character[]> transFuncL) {
    	this.transFuncL = transFuncL;
	}
	public String getStart() {
    	return start;
	}
	public void setStart(String start) {
    	this.start = start;
	}
	public int getNumTF() {
    	return numTF;
	}
	public void setNumTF(int numTF) {
    	this.numTF = numTF;
	}
	/************** ACCESSORS/MUTATORS **************************/
}


