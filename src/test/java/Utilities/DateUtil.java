package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String MM_DD_YYYY_WITH_SPERATOR = "MM/dd/yyyy";
	
	public static String getDateAsString(Date date,String dateFormat)
	{
		return new SimpleDateFormat(dateFormat).format(date);
	}
	
	public static String getDateAsString(String date,String dateFormat)
	{
		return new SimpleDateFormat(dateFormat).format(date);
	}
}
