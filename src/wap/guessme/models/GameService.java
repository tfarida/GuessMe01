
/**
 * @author romiezaw
 *
 */

package wap.guessme.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//Written by Romie on March 19/22 2018 - Game Service class for game control

public class GameService {
	private int numberToGuess;
	private int secretNo;
	int[] secretArr;
	private int myGuess;

	public GameService() {

	}

	public int getMyGuess() {
		return myGuess;
	}

	public void setMyGuess(int myGuess) {
		this.myGuess = myGuess;
	}

	public int getNumberToGuess() {
		return numberToGuess;
	}

	public void setNumberToGuess(int numberToGuess) {
		this.numberToGuess = numberToGuess;
		int number = numberToGuess;						// convert to int[] - while setting the number.
		secretArr = new int[4];				 			// Convert the number into int[]
		StringBuilder builder = new StringBuilder(); 	// to allow leading zeros such as 0987
		for (int i = 0; i < 4; i++) {
			int divider = (int) (Math.pow(10, (3 - i)));
			secretArr[i] = (int) number / divider;
			System.out.println("Secret No : " + secretArr[i]);
			builder.append(secretArr[i]);
			number = number % divider;
		}
	}

	public int getSecretNo() {
		return secretNo;
	}

	public void setSecretNo(int secretNo) {
		this.secretNo = secretNo;
	}

	public JSONObject solveIt() {

		int[] numberArr = secretArr;

		Map<String, List<Integer>> numberMap = new HashMap<>();
		int totalDigitCounter = 0;
		int positionCounter = 0;
		int number = myGuess;

		int[] userSelectedDigits = new int[4]; 				// Convert the number into int[]
		List<Integer> matchingArr = new ArrayList<>(); 		// int[] for matching pair
		StringBuilder builder = new StringBuilder(); 		// to allow leading zeros
		for (int i = 0; i < 4; i++) {
			int divider = (int) (Math.pow(10, (3 - i)));
			userSelectedDigits[i] = (int) number / divider;
			builder.append(userSelectedDigits[i]);
			number = number % divider;
		}

		int index = -1; 										// Counter to control the loop and get the position of digit
		for (int digit : userSelectedDigits) {
			index++;
			boolean found = IntStream.of(numberArr).anyMatch(savedDigit -> savedDigit == digit); // Find a digit																					// original number
			if (found) { 									// If found, check the position
				totalDigitCounter++;
				for (int j = 0; j < numberArr.length; j++) {
					if (digit == numberArr[j]) {
						if (index == j)
							positionCounter++;
					}
				}
			}
		}
		matchingArr.add(totalDigitCounter);
		matchingArr.add(positionCounter);
		numberMap.put(builder.toString(), matchingArr);
		return toJson(numberMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONObject toJson(Map<String, List<Integer>> map) {
		JSONObject jsonObject = new JSONObject();
		for (String key : map.keySet()) {
			List<Integer> list = map.get(key);
			if (list instanceof List) {
				jsonObject.put("number", key);
				jsonObject.put("match", toJson((List) list));
			}
		}
		return jsonObject;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONArray toJson(List list) {
		JSONArray jsonArray = new JSONArray();
		for (Object obj : list) {
			if (obj instanceof List) {
				jsonArray.add(toJson((List) obj));
			} else {
				jsonArray.add(obj);
			}
		}
		return jsonArray;
	}

	//Generate 4 digits non-duplicate digit Random No. 
	public static int generateRandomNo() {	
		
		int max = 9999;
		int min = 0001;
		int range = max - min + 1;
		int number = 0;
		Matcher matcher;
		//Regex to check if each digits are unique
		Pattern pattern = java.util.regex.Pattern.compile("(?:([0-9])(?!.*\\1)){4}");
		//Eg : if generated no is 9882 , regenerate again until it is unique
		do {
			number = (int) (Math.random() * range) + min;
			System.out.println(number);
			matcher = pattern.matcher(String.valueOf(number));
			System.out.println(matcher.matches());
			
		}while(!matcher.matches());			//if not matching, generate again
		
		return number;
	}
}
