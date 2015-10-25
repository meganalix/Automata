import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter bw;
		BufferedReader br;
		String expression;
		NFA p1;
		NFA p2;
		Stack<NFA> stack = new Stack<NFA>();
		
		if(args.length != 2)
		{
			System.out.println("Only two arguments:source file and destination file");
			return;
		}
		try {
			br = new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.format("%s cannot be opened.\n",args[0]);
			return;
		}
		
		try {
			bw = new BufferedWriter(new FileWriter(args[1]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.format("%s cannot be opened.\n",args[1]);
			br.close();
			return;
		}
		
		while((expression = br.readLine()) != null)
		{
			for(char c: expression.toCharArray())
			{
				bw.append(c);
				switch(c)
				{
					case 'a':
					case 'b':
					case 'c':
					case 'd':
					case 'e':
					case 'E':
					case '0':
								stack.push(new NFA(c));
								break;
					case '+':
								p2 = stack.pop();
								p1 = stack.pop();
								p1.union(p2);
								stack.push(p1);
								break;
					case '*':
								p1 = stack.pop();
								p1.kleene();
								stack.push(p1);
								break;
					case '&':
								p2 = stack.pop();
								p1 = stack.pop();
								p1.concat(p2);
								stack.push(p1);
								break;
					default:
								System.out.println("Invalid character");
								return;
				}
			}
			bw.newLine();
			bw.newLine();
			stack.pop().print(bw);
			if(stack.isEmpty() == false)
			{
				System.out.println("Invalid Regular Expression");
				br.close();
				bw.close();
				return;
			}
			bw.newLine();
			bw.newLine();
			bw.newLine();
			bw.flush();
			
		}
		bw.close();
		br.close();
		
	}

}
