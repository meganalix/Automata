import java.util.ArrayList;
import java.util.HashMap;


public class state {
	private HashMap<Character, state> trans;//transitions besides epsilon
	private ArrayList<state> epsilon;//epsilon jumps
	
	public state(){
		this.trans = new HashMap<Character, state>();
		this.epsilon = new ArrayList<state>();
	
	}
	public state check(char c)//checks if contains state
	{
		if(this.trans.containsKey(c) == true)
			return(this.trans.get(c));//returns state held at that location
		return(null);
	}
	public ArrayList<state> epsret()//returns array list of epsilon jumps
	{
		return(this.epsilon);
	}
	public void addstate(char n, state q)//adds state to trans our hash map
	{
		this.trans.put(n, q);
	}
	public void addepsilon(state q)//adds epsilon jump to array list 
	{
		this.epsilon.add(q);
	}
}
