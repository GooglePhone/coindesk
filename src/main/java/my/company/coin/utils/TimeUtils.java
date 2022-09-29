package my.company.coin.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String format(Timestamp ts) {
		Date d = new Date(ts.getTime());
		return df.format(d);
	}
}
