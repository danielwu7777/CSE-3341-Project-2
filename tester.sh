#!/bin/bash

runner=$1

if test -f Main.py
then
	runner="python3.7 Main.py"
elif test -f Main.java
then
	echo "Attempting to compile java code..."
	javac *.java
	runner="java Main"
fi

score=0
error=0

for value in {1..26}
do
	echo ""
	echo "Running ${value}.code"
	${runner} Correct/${value}.code > Correct/${value}.student
	#Check for correct print
	tr -d '[:space:]' < Correct/${value}.student > temp1
	tr -d '[:space:]' < Correct/${value}.code > temp2
	echo "Comparing input and output"
	if cmp -s "temp1" "temp2"; then
		echo "Print looks good"
		score=$(($score + 1))
	else
		echo "Student output and expected output are different"
	fi
done

rm temp1
rm temp2

echo ""
echo "Running error cases:"
echo ""
echo "Running 01.error:"
${runner} Error/01.code
read -n 1 -p "Error is ++ in expression. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 02.error:"
${runner} Error/02.code
read -n 1 -p "Error is undeclared variable 'y' being used. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 03.error:"
${runner} Error/03.code
read -n 1 -p "Error is variable 'x' declared twice in the same scope. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 04.error:"
${runner} Error/04.code
read -n 1 -p "Error is } missing. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 05.error:"
${runner} Error/05.code
read -n 1 -p "Error is ASSIGN in condition. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 06.error:"
${runner} Error/06.code
read -n 1 -p "Error is { missing. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 07.error:"
${runner} Error/07.code
read -n 1 -p "Error is ids after end. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 08.error:"
${runner} Error/08.code
read -n 1 -p "Error is int variable used in 'id = share id' assignment. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 09.error:"
${runner} Error/09.code
read -n 1 -p "Error is missing semicolon. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""
echo "Running 10.error:"
${runner} Error/10.code
read -n 1 -p "Error is missing right parenthesis. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi


echo ""
echo ""
echo "Correct cases score out of 26:"
echo $score
echo "Error cases score out of 10:"
echo $error

echo "Done!"