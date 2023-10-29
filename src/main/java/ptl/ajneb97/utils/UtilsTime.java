package ptl.ajneb97.utils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;

import ptl.ajneb97.managers.MensajesManager;

public class UtilsTime {

	public static String getTime(long segundos,MensajesManager msgManager) {
		int day = (int) TimeUnit.SECONDS.toDays(segundos);
		long hours = TimeUnit.SECONDS.toHours(segundos) - (day * 24L);
		long minute = TimeUnit.SECONDS.toMinutes(segundos) -
				(TimeUnit.SECONDS.toHours(segundos)* 60);
		long second = TimeUnit.SECONDS.toSeconds(segundos) -
				(TimeUnit.SECONDS.toMinutes(segundos) *60);
		if(day == 0) {
			return hours + ":" + minute + ":" + second;
		}
		return day + ":" + hours + ":" + minute + ":" + second;
	}
	
	//Devuelve los millis del proximo reinicio de tiempo
	public static long getNextResetMillis(String resetTimeHour) {
		long currentMillis = System.currentTimeMillis();
		
		//Bukkit.getConsoleSender().sendMessage("reset time: "+resetTimeHour);
		String[] sep = resetTimeHour.split(":");
		String hour = sep[0];
		if(hour.startsWith("0")) {
			hour = hour.charAt(1)+"";
		}
		String minute = sep[1];
		if(minute.startsWith("0")) {
			minute = minute.charAt(1)+"";
		}
		
		Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(currentMillis);
	    calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
	    calendar.set(Calendar.MINUTE, Integer.valueOf(minute));
	    calendar.set(Calendar.SECOND, 0);
	    
	    if(calendar.getTimeInMillis() >= currentMillis) {
	    	//Aun no llega a la hora de reinicio en el dia
	    	//Bukkit.getConsoleSender().sendMessage("Hora reinicio: "+hour+":"+minute+"   | Aun no pasa");
	    	return calendar.getTimeInMillis();
	    }else {
	    	//La hora de reinicio ya paso en el dia
	    	//Bukkit.getConsoleSender().sendMessage("Hora reinicio: "+hour+":"+minute+"   | Ya paso");
	    	calendar.add(Calendar.DAY_OF_YEAR, 1);
	    	//Bukkit.getConsoleSender().sendMessage("Nueva fecha: "+calendar.toString());
	    	return calendar.getTimeInMillis();
	    }
	}
}
