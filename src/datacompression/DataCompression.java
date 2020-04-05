/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacompression;
import java.util.*;
/**
 *
 * @author Mnassar Alyami
 */
//  Implementation as described in the paper (pages 2-5)

public class DataCompression {

    /**
     * @param args the command line arguments
     */

	//Lexicographic Sort
	public static String[] Lexicographic (String [] listOfWords){
		for(int i = 0; i < listOfWords.length-1; ++i) {
			for (int j = i + 1; j < listOfWords.length; ++j) {
				if (listOfWords[i].compareTo(listOfWords[j]) > 0) {
					String t = listOfWords[i];
					listOfWords[i] = listOfWords[j];
					listOfWords[j] = t;
				}
			}
		}
		return listOfWords;
	}
	
	static int [] listOfT ;
	static int I;
	static int N;
	//Methods to Print the lists of different types
	public static void print(String [] list) {
		
		for(int i = 0; i < list.length ; i++) {
			System.out.println(list[i]);
		}
		System.out.println();
	}
	
	public static void print(char [] list) {
		System.out.print("[ ");
		for(int i = 0; i < list.length ; i++) {
			System.out.print(list[i]+" ");
		}
		System.out.print("]");
		System.out.println();
		System.out.println();
	}
	
	public static void print(int [] list) {
		System.out.print("[ ");
		for(int i = 0; i < list.length ; i++) {
			System.out.print(list[i]+" ");
		}
		System.out.print("]");
		System.out.println();
		System.out.println();
	}
	
	
	//Rotation of the String
	static String leftrotate(String str, int d) 
    { 
            String ans = str.substring(d) + str.substring(0, d); 
            return ans; 
    } 
	
	//Recursive method to calculate victor T, as in D3
	public static int value(int i) {
		if(i == 0) {
			return I;
		}else {
			return listOfT[value(i-1)];
		}
	}
      
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner input = new Scanner(System.in);

		System.out.print("Enter the String 'S' : ");
		String S = input.next();
		
		//String S = "abraca";
		
		N = S.length();
		// M Matrix
		String [] M = new String[S.length()];
		
		// C1, Cyclic Rotation of all String S, to Form M
		M[0] = S;
		for(int i = 1 ; i < N ; i++) {
			M[i] = M[i-1].substring(1) + M[i-1].substring(0, 1);
		}
		
		//Lexicographic Sort of the M
		M = Lexicographic(M);
		
		System.out.println("Lexicographic Sort of M is");
		print(M);
		
		//C2, ﬁnd last characters of rotations 
                
                //To check where is the actual String is in the Lexicographic sort
		I = 0;
		//List of last characters of all, Lexicographic sort
		char[] L = new char[N];
                //Loop to find L and I
		for(int i = 0; i < N ; i++) {
			L[i] = M[i].charAt(N-1);
			if(M[i].equals(S)) {
				I = i;
			}
		}
		
		System.out.println("List of Last Characters is : ");
		print(L);
		
		//D1
		//List of first characters of all, Lexicographic sort
		char [] F = new char[N];
		for(int i = 0; i < N ; i++) {
			F[i] = M[i].charAt(0);
		}
		
		System.out.println("List of First Characters is : ");
		print(F);
		
		String [] MPrime = new String[N];
		for(int i = 0 ; i < N; i++) {
			MPrime[i] = leftrotate(M[i], M[i].length() - 1);
		}

		System.out.println("M' is ");
		print(MPrime);


		//Calculating T
		int [] T = new int[N];
		//Comparing the value from MPrime to the M, and store the 
		//index of each element of MPrime from M to T
		for(int i = 0; i<N ; i++) {
			for(int j = 0; j < N; j++) {
				if(MPrime[i].equals(M[j])) {
					T[i] =j;
				}
			}
		}
		System.out.println("List of T:");
		print(T);
		

		listOfT = T;
		String output = "";
		for(int i = 0; i < N; i++) {
			output = (L[value(i)]) + output;
		}
		
		System.out.println("Final Output is : " +output.toString());
        
    }
    
}
