package com.zz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author asus
 *
 */
public class DateUtil {
	/**
	 * util date转Sql date
	 * @param udate
	 *@return
	 */
		public static java.sql.Date trancetoSqlDate(Date udate){
			//转成sqldate
			return new java.sql.Date(udate.getTime());
		}
		
	
	/**
	 * string 转utildate
	 * @param str
	 * @return
	 */
	public static Date tranceToDate(String str) {
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		Date d1=null;
		try {
			 d1=s.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d1;
	}
}
