package wap.guessme.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author titin
 * desc: just to wrap some common needed things.
 *
 */
public class AppHelper {
	public static SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static String internalServerErrorMessage = "Internal Server Error! Please call Romie/Temesgen/Titin @mum.edu!";
	
	public static String getDateNow() {
		Date x = new Date();
		return f.format(new Date());
	}
	public static String getDateNow(Date d) {
		return f.format(d);
	}
	//public static String dateString = 

}
