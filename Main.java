
import java.util.Scanner;

public class Main extends Object
{
  public static final String ALPHABET = "0123456789 abcdefghijklmnopqrstuvwxyz";
  public static final int MODULUS = ALPHABET.length();


  //postcondition: if num == 0, return 0, otherwise return the multiplicative
  //               inverse of num (mod MODULUS)
  //postcondition: the return value is larger than -1 and less than MODULUS
  public static int inverse(int num)
  {
    for (int x = 1; x < 37; x++){
            if (((num%37) * (x%37)) % 37 == 1)
            {return x;
              }
      }
   return 1;      //fix this using trial and error in a loop
  }

  
  //postcondition: calculate the result of a*x+b (mod MODULUS)
  //postcondition: returned int value is from 0 to MODULUS-1
  public static int compute(int a, int x, int b)
    {
      int y = ((a*x)+b) % 37;
        while(y<0){
          y+= 37;
        }

      return y; 
  }


  
  //precondition: 0 <= x < MODULUS
  //postcondtion: return the letter in ALPHABET whose index is x
  public static char getLetterFromIndex(int x)
  {
    return ALPHABET.charAt(x);  //fix this
  }


  
  //precondition: let is a single character that can be found in ALPHABET
  //postcondition: return the index of let in ALPHABET
  public static int getNumberFromLetter(char let)
  {
    return ALPHABET.indexOf(let);  //fix this
  }

  //precondition: 0 < a < MODULUS, 0 <= b < MODULULS
  //precondition: s != null and contians only letters from ALPHABET
  //postcondition: return an encrypted string using y = a*x + b
  public static String encrypt(int a, int b, String s)
  {
    String enc = "";

    for (int x = 0; x<s.length(); x++){
       
      enc += (ALPHABET.charAt(compute(a,ALPHABET.indexOf(s.charAt(x)),b)));
      
    }

    return enc;
  }

  //precondition: 0 < a < MODULUS, 0 <= b < MODULULS
  //precondition: s != null and contians only letters from ALPHABET
  //postcondition: return an decrypted string using the **inverse** of y = a*x + b
  // (x-b)(inv a) = y
  public static String decrypt(int a, int b, String s)
  {
    String enc = "";
   for (int x = 0; x<s.length(); x++){ 
     int temp = (inverse(a)*((ALPHABET.indexOf(s.charAt(x)))-b)) % 37;
        while(temp<0){
          temp+= 37;
        }

     enc+= ALPHABET.charAt(temp);
     
     }
    

    return enc;
  }

  
  public static void main(String[] args) 
  {
    System.out.println(Main.inverse(7));
    Scanner in = new Scanner(System.in);
    
    System.out.println("The encrytping function is y = A*x + B");
    System.out.print("\n  Enter an integer (from 0 to "+(MODULUS-1)+") for \"A\": ");
    int A = in.nextInt() % MODULUS;
    if (A < 0) A += MODULUS;
    System.out.print("  Enter an integer (from 0 to "+(MODULUS-1)+") for \"B\": ");
    int B = in.nextInt() % MODULUS;
    if (B < 0) B += MODULUS;
    System.out.println("\nThe encrypting function you chose is "+A+"x + "+B);
    System.out.println("\n==========================================");
    
    while (true)
    {
      in = new Scanner(System.in);  // reset the Scanner to clear out the cobwebs
    
      System.out.print("\nEnter text (use only lowercase letters, numbers, and spaces).\n> ");
      String text = in.nextLine();
      System.out.print("Type 1 for encrypt, 2 for decrypt: ");
      String choice = in.next();
      if (choice.toLowerCase().trim().equals("e") || Integer.valueOf(choice) == 1) 
      {
        System.out.println(encrypt(A, B, text));
      }
      else if (choice.toLowerCase().trim().equals("d") || Integer.valueOf(choice) == 2)
      {
        System.out.println(Main.decrypt(A, B, text));
      }
      else
      {
        System.out.println("Invalid choice");
      }
    }
  }
}