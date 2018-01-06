package com.ersin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Given two sorted files, merges them to preserve sort order against the key-field. 
 * Key-field: assumed to be the first 8 alphanumeric characters of each line
 * 
 */

public class Problem2 {

	final static int KEY_LENGTH = 8;
	final static String RESULT_FILE  = "ResultFile_2.txt";

	public static void main(String[] args) {

		// Get the input file names.
		Scanner sc = new Scanner(System.in);
		System.out.print("Please, enter the 1st input file name: ");
		String inputFileName1 = sc.nextLine();
		System.out.print("Please, enter the 2nd input file name: ");
		String inputFileName2 = sc.nextLine();
		sc.close();

		try ( 	BufferedReader in1 = new BufferedReader(new FileReader(inputFileName1)); 
				BufferedReader in2 = new BufferedReader(new FileReader(inputFileName2)); 
				PrintWriter    out = new PrintWriter(new BufferedWriter(new FileWriter(RESULT_FILE))); ) 
		{
			String str1 = in1.readLine();
			String str2 = in2.readLine();

			System.out.println("\nMerged output preserving the sort order follows:\n");

			while(true) {

				// if both reached the end-of-file 
				if ( (str1 == null) && (str2 == null) ) {
					break;
				}

				// if only one has an entry
				if( (str1 != null) && (str2 == null) ){
					out.println(str1);
					System.out.println(str1);
					str1 = in1.readLine();
				}
				else if( (str1 == null) && (str2 != null) ){
					out.println(str2);
					System.out.println(str2);
					str2 = in2.readLine();
				}

				// if both have entries
				else if( (str1 != null) && (str2 != null) ){
					int comparison = str1.substring(0, KEY_LENGTH).compareToIgnoreCase(str2.substring(0, KEY_LENGTH));
					if( comparison < 0 ) {
						out.println(str1);
						System.out.println(str1);
						str1 = in1.readLine();
					}
					else if( comparison == 0 ) {
						out.println(str1);
						System.out.println(str1);
						str1 = in1.readLine();
						str2 = in2.readLine();
					}
					else if( comparison > 0 ) {
						out.println(str2);
						System.out.println(str2);
						str2 = in2.readLine();
					}
				}
			}
			
			System.out.print("\nResult file name: " + RESULT_FILE);
		} 
		catch (Exception e)  {
			e.printStackTrace();
		}
	}
}
