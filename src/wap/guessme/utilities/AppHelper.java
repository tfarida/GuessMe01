package wap.guessme.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author titin desc: just to wrap some common needed things.
 *
 *
 *
 */
// Written by Titin on March 2018 - Helper class
public class AppHelper {
	public static SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static String internalServerErrorMessage = "Internal Server Error! Please call Romie/Temesgen/Titin @mum.edu!";

	public static String getDateNow() {
		return f.format(new Date());
	}

	public static String getDateNow(Date d) {
		return f.format(d);
	}

}
