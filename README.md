name: Daniel Wu

Files: All Non-terminal class .java files

Comments: I spend so long on the parsing that I wasn't able to complete all the semantic checking. Note that there is no indenting when printing.

Description: The parser parses through the program by having a separate parser for each non-terminal. In each of these non-terminal parsers, they only parse through that non-terminal and call other parsers if they have other non-terminals nested in them. 
In each non-terminal class, there are class level variables that are stored while parsing so that the printer knows which specific case in each non-terminal it is. 

Testing: To test the parser, I ran the tester script and fixed any errors for the first test case. After making the first test case parse/print correctly, I moved onto the next test case with errors. I used System.out.println(S.currentToken() + "expected this token here") extensively to test where my parsing errors were.

Bugs: Not all the semantic checks were implemented.
