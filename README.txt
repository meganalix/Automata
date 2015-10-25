Megan Ramirez
megan.ramirez@wsu.edu

I am submitting a java program that takes in a file of only postfix regular expressions(no numbers in front of them) and outputs to another 
file the regular expression and table of the equivalent NFA.

How to build my program
	1. Import the zip file into eclipse. To do this go to the File tab and click import. In the resulting pop up window choose existing project
	1. Choose an input file for my program containing your postfix regular expressions and set the path to that file as the first argument in run configuration
	2. Choose a name for a new or existing file for my programs output. As above make the path the second argument in run configuration. For both of these
	remember that '\' must be represented as "\\" so that my program can parse the path. For example src//test.txt  
	3. To set an argument go to the Run tab and click on run configuration
Files in Archive
Main.java
	The main program handles reading input from the test.txt file and writing the output to testOut.txt. It also manages the stack depending 
	on the regular expression.
NFA.java
	The NFA class creates an NFA that can build the table for the final NFA on the stack, create a union between two NFA's, create a 
	concatenation between two NFA's, and a Kleene closure.
state.java
	The state class creates a list of epsilon transitions and other transitions.
test.txt
	I used this file to hold the postfix regular expressions 
testOut.txt
	I used this file to output my NFA table to 