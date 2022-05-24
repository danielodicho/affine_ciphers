clear

javac *.java 2> CompileErrors.txt

if 
  grep -q "error" CompileErrors.txt  
then 
  head CompileErrors.txt
  echo 
  echo "Fix the **first** syntax error listed above and re-run the app..."
  echo
  echo "<<See the file named CompileErrors.txt for the full list of errors>>"
  echo 
  echo
else 
  echo "Compiled successfully: running your app..."
  echo
  java Main
fi 