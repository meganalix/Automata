import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class NFA {
	private state start;//our start state
	private ArrayList<state> finl;//our final states
	private ArrayList<state> states;//our list of states
	
	public NFA(char l)//accepts some character that rempresents the transistion between states
	{
		//initializes our lists of states and lists of final states
		this.states = new ArrayList<state>();
		this.finl = new ArrayList<state>();
		
		if(l == '0')//if it is  a  0 jump
		{
			state m = new state();
			this.start = m;
			this.states.add(m);
			return;
		}
		if(l == 'E')//if an epsilon jump
		{
			//initialize start(s) and final(f) states
			state s = new state();
			state f = new state();
			s.addepsilon(f);//creates epsilon jump from start state to final state
			this.start = s;//assigns start state to new start state
			this.states.add(s);//adds start state to states list for NFA
			this.states.add(f);//adds final state to states list for NFA
			this.finl.add(f);//assigns final state to new final state
			return;
		}
		
		//for a,b,c,d,e
		//initialize new start(s) and final(f) states
		state s = new state();
		state f = new state();
		s.addstate(l,f);//adds state
		this.start = s;//makes start state equal to newly created start state
		this.states.add(s);//add start state to states list
		this.states.add(f);// add final state to states list			
		this.finl.add(f);//makes new final state the final state
		return;
		
		
	}
	public void print(BufferedWriter table) throws IOException//prints out table
	{
		HashMap<state, String> namestates = new HashMap<state, String>();//initialize hashmap of the name states
		String temp; 
		String type;//formats starts and finals
		char[] alphabet = "abcde".toCharArray();//assigns alphabet
		for(state i: this.states)//add name states q0,q1,q2,q3......qn
			namestates.put(i,"q"+this.states.indexOf(i));
		
		table.write("___state___|_____a_____|_____b_____|_____c_____|_____d_____|_____e_____|_____E_____");//top of table 11 spaces for each column
		table.newLine();
		for(state i: this.states)//iterates through states
		{
			//assigns start and final states
			if((i == this.start)&&(this.finl.contains(i)))
				type = "SF";
			else if( i == this.start)
				type = "S ";
			else if (this.finl.contains(i))
				type = "F ";
			else
				type = "  ";
			table.write(String.format("%2s%9s|",type,namestates.get(i)));
			
			for(char c: alphabet)//add alphabet transitions on table
			{
				temp = "";
				if(i.check(c) != null)
					temp = namestates.get(i.check(c));
				table.write(String.format("%11s|",temp));
			}
			
			if(i.epsret().size()>0)//adds epsilon jumps to table
			{
				table.write(String.format("%s",namestates.get(i.epsret().get(0))));
				for(int z = 1; z < i.epsret().size(); z++ )
					table.write(String.format(", %s", namestates.get(i.epsret().get(z))));
				table.newLine();
			}
			else
			{
				table.write(String.format("%11s",""));
				table.newLine();
			}
			
			
				
		}
		
		
		
		
		
	}
	public void union(NFA next)
	{
		state newstart = new state();
		newstart.addepsilon(this.start);
		newstart.addepsilon(next.getstart());
		state newfinal = new state();
		for(state i: this.finl)
			i.addepsilon(newfinal);
		for(state i: next.getfinl())
			i.addepsilon(newfinal);
		this.start = newstart;
		this.states.add(newstart);
		this.states.add(newfinal);
		for(state i: next.getstates())
			this.states.add(i);
		this.finl.clear();
		this.finl.add(newfinal);
	}
	
	public void concat(NFA next)
	{
		for(state i: this.finl)//r1 finals epsilon to r2 start
			i.addepsilon(next.getstart());
		for(state i: next.getstates())//add states of r2
			this.states.add(i);
		this.finl = next.getfinl();//change r1 finals to r2 finals
	}
	
	public void kleene()
	{
		state sf = new state();//create new states sf
		sf.addepsilon(this.start);//sf epsilon to this.start.state
		for(state i: this.finl)//r1 finals epsilon to sf
			i.addepsilon(sf);
		this.start = sf;
		this.states.add(sf);
		this.finl.clear();
		this.finl.add(sf);
		
	}
	public state getstart()
	{
		return(this.start);
	}
	public ArrayList<state> getfinl()
	{
		return(this.finl);
	}
	public ArrayList<state> getstates()
	{
		return(this.states);
	}
	
	
}
