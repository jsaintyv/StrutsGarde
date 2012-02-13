package strutsbase.util;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public static String getLongFrenchDate(GregorianCalendar gcDay) {
		String dayOfWeekName = "";
		switch (gcDay.get(GregorianCalendar.DAY_OF_WEEK)) {
		case 2:
			dayOfWeekName = "Lundi";
			break;
		case 3:
			dayOfWeekName = "Mardi";
			break;
		case 4:
			dayOfWeekName = "Mercredi";
			break;
		case 5:
			dayOfWeekName = "Jeudi";
			break;
		case 6:
			dayOfWeekName = "Vendredi";
			break;
		case 7:
			dayOfWeekName = "Samedi";
			break;
		case 1:
			dayOfWeekName = "Dimanche";
			break;
		default:
			break;
		}
		return dayOfWeekName + " "
				+ completeWithZero(2,gcDay.get(GregorianCalendar.DAY_OF_MONTH)) + "/"
				+ completeWithZero(2,(gcDay.get(GregorianCalendar.MONTH) + 1)) + "/"
				+ completeWithZero(4,gcDay.get(GregorianCalendar.YEAR));
	}

	public static String getHour(GregorianCalendar gc) {
		
		return gc.get(GregorianCalendar.HOUR_OF_DAY) + "h" + gc.get(GregorianCalendar.MINUTE);
	}
	
	public static String completeWithZero(int lenght, long cid) {
		return completeWithZero(lenght, "" + cid);
	}
	
	public static String completeWithZero(int lenght, String cid) {
		int compteur = cid.length();
		compteur = lenght - compteur;
		while (compteur > 0) {
			cid = "0" + cid;
			compteur--;
		}
		return cid;
	}
	
	public static String getInHour(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);

		String hour = completeWithZero(2,
				String.valueOf(gc.get(GregorianCalendar.HOUR_OF_DAY)));
		String minute = completeWithZero(2,
				String.valueOf(gc.get(GregorianCalendar.MINUTE)));
		return hour + "h" + minute + "mn";
	}
	
	public static String getInDay(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);

		String jour = completeWithZero(2,
				String.valueOf(gc.get(GregorianCalendar.DAY_OF_MONTH)));
		String mois = completeWithZero(2,
				String.valueOf(gc.get(GregorianCalendar.MONTH) + 1));
		return jour + "/" + mois;
	}
}
