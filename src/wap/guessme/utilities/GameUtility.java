package wap.guessme.utilities; 

import java.sql.Date;  //test
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * 
 */

/**
 * @author romiezaw
 *
 */
public class GameUtility {

	public static String getPresentTime() {
		LocalDateTime datetime1 = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = datetime1.format(format);
		System.out.println(datetime1);
		System.out.println(formatDateTime);
		return formatDateTime;
	}

	public static void main(String args[]) {
		// define the range

		// generate random number from 0001 to 9999
		/*
		int number = (int) (Math.random() * range) + min;
		System.out.println(number);

		int[] secretArr = new int[4]; // Convert the number into int[]
		StringBuilder builder = new StringBuilder(); // to allow leading zeros
		for (int i = 0; i < 4; i++) {
			int divider = (int) (Math.pow(10, (3 - i)));
			secretArr[i] = (int) number / divider;
			System.out.println("no : " + secretArr[i]);
			builder.append(secretArr[i]);
			number = number % divider;
		}*/
		/*
		 * IntStream.range(0000, 9999) .forEach( i -> System.out.println(i); );
		 */
		//IntStream stream = IntStream.generate(() -> (int) (Math.random() * 10000)).limit(1);
		//stream.forEach(System.out::println);

		
		/*
		 * 
		 * java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
				"^(   [\\d](?!\\1)[\\d](?!\\2)(?!\\1)[\\d](?!\\3)(?!\\2)(?!\\1)[\\d]    )$");
		for (String ssn : input) {
			if (ssn.matches("^(\\d{3}-?\\d{2}-?\\d{4})$")) {
				System.out.println("Found good SSN: " + ssn);
			}
		}
		""
		*/
		/*java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
				"^(([\\d])(?!\\1([\\d])(?!\\2([\\d])(?!\\3[\\d]))))$");
		java.util.regex.Matcher matcher = pattern.matcher("9732");
		*/

		//System.out.println(matcher.matches());
		
		//IntStream stream = IntStream.generate(() -> (int) (Math.random() * 10000)).limit(1);
		//stream.forEach(System.out::println);
		
		/*
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
				"(?:([0-9])(?!.*\\1)){4}"
				);
		java.util.regex.Matcher matcher = pattern.matcher("9923");
		System.out.println(matcher.matches());
		java.util.regex.Matcher matcher1 = pattern.matcher("9123");
		System.out.println(matcher1.matches());
		java.util.regex.Matcher matcher2 = pattern.matcher("1123");
		System.out.println(matcher2.matches());
		java.util.regex.Matcher matcher3 = pattern.matcher("1023");
		System.out.println(matcher3.matches());
		java.util.regex.Matcher matcher4 = pattern.matcher("0923");
		System.out.println(matcher4.matches());
		java.util.regex.Matcher matcher5 = pattern.matcher("7553");
		System.out.println(matcher5.matches());
		java.util.regex.Matcher matcher6 = pattern.matcher("3023");
		System.out.println(matcher6.matches());
		*/
		
		int max = 9999;
		int min = 0001;
		int range = max - min + 1;
		int number = 0;
		Matcher matcher;
		Pattern pattern = java.util.regex.Pattern.compile("(?:([0-9])(?!.*\\1)){4}");
		
		do {
			number = (int) (Math.random() * range) + min;
			System.out.println(number);
			matcher = pattern.matcher(String.valueOf(number));
			System.out.println(matcher.matches());
			
		}while(!matcher.matches());
		
		//return number;
	}
	

}