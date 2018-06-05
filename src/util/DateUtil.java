package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	static long millisecondsOfOneDay = 1000*60*60*24;
	
	//把java.util.Date的日期类型转化成java.sql.Date的日期类型
	//因为JDBC的statement中插入的日期类型是sql.Date的所以要进行转换
	public static java.sql.Date util2sql(java.util.Date d){
		return new java.sql.Date(d.getTime());
	}
	//获取今天，并且把时分秒毫秒置0 因为通过日期控件获取的日期就是没有时分秒和毫秒的 
	public static Date today() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		return c.getTime();
	}
	
	//获取月初 用calendar获取本月第一天.统计消费的时候 查看本月的消费数据 其实就是从数据库
	//把这个月第一天到最后一天的数据查出来，在进行统计
	public static Date monthBegin() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE, 1);//这个月第一天
		
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		
		return c.getTime();
		
	}
	//获取月末
	
	public static Date monthEnd(){
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date());
		c.set(Calendar.HOUR,0);
		c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    
	    c.set(Calendar.DATE, 1);//这个月的第一天
	    c.add(Calendar.MONTH,1);//加一个月 下一个月的第一天
	    c.add(Calendar.DATE, -1);//下个月的第一天减去一天 就是这个月的最后一天
	    
	    return c.getTime();
	}
	
	//获取本月一共有多少天
	public static int thisMonthTotalDay() {
		long lastDayMilliSeconds = monthEnd().getTime();
		long firstDayMilliSeconds = monthBegin().getTime();
		
		return (int)((lastDayMilliSeconds - firstDayMilliSeconds)/millisecondsOfOneDay)+1;
		
	}
	
	//本月还剩多少天
	 public static int thisMonthLeftDay(){
	        long lastDayMilliSeconds = monthEnd().getTime();
	        long toDayMilliSeconds = today().getTime();
	        return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecondsOfOneDay) +1;
	    }  
	 public static void main(String[] args) {
		 System.out.println(DateUtil.monthEnd());
	 }
}
