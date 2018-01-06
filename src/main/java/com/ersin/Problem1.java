package com.ersin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Problem1 {

	final static String RESULT_FILE  = "ResultFile_1.txt";

	public static void main(String[] args) {

		// Get the input file name.
		Scanner sc = new Scanner(System.in);
		System.out.print("Please, enter the input file name: ");
		String inputFileName = sc.nextLine();
		sc.close();

		Map<String, Double> priceTicks = new LinkedHashMap<String, Double>();

		try ( BufferedReader in = new BufferedReader(new FileReader(inputFileName)) ) {
			String str = "";
			String CUSIP = "";
			while ((str = in.readLine()) != null) {
				if (!StringUtils.isBlank(str)) {
					// CUSIP.
					if (!NumberUtils.isParsable(str)) {
						CUSIP = str;
						if (!priceTicks.containsKey(CUSIP)) {
							priceTicks.put(CUSIP, null);
							continue;
						}
					}
					// Price.
					if (NumberUtils.isParsable(str)) {
						try {
							priceTicks.put(CUSIP, Double.parseDouble(str));
						} catch (Exception e) {
							System.out.println("Invalid price: " + CUSIP + "=" + str);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Decimal formatter
		DecimalFormat decimalFormat = new DecimalFormat("###,###,###.######");

		// Print results to screen.
		System.out.println("\nClosing (or latest) price for each CUSIP follows:\n");
		priceTicks.forEach( (k, v) -> System.out.println(k + " = " + decimalFormat.format(v)) );

		// Print results to file.
		try ( PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(RESULT_FILE))) ) {
			priceTicks.forEach( (k, v) -> out.println(k + " = " + decimalFormat.format(v)) );
			System.out.print("\nResult file name: " + RESULT_FILE);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}