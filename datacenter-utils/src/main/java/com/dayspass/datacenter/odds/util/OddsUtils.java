package com.dayspass.datacenter.odds.util;

public class OddsUtils {
	private static String [] oddsArry={"平手","平/半","半球","半/一","一球","一/球半","球半","球半/两","两球","两/两半","两球半","两半/三","三球","三/三半"
        ,"三球半","三半/四","四球","四/四半","四球半","四半/五","五球","五/五半","五球半","五半/六","六球"
        ,"六/六半","六球半","六半/七","七球","七/七半","七球半","七半/八","八球","八/八半","八球半"
        ,"八半/九","九球","九/九半","九球半","九半/十","十球"};
	private static String [] oddsNumArry={"0","0.25","0.5","0.75","1","1.25","1.5","1.75","2","2.25","2.5","2.75","3","3.25"
        ,"3.5","3.75","4","4.25","4.5","4.75","5","5.25","5.5","5.75","6"
        ,"6.25","6.5","6.75","7","7.25","7.5","7.75","8","8.25","8.5"
        ,"8.75","9","9.25","9.5","9.75","10"};
	
	public static String getOddsYzNum(String strOdds){
		boolean shourang=false;//是否受让
		if(strOdds.equals("平手")){
			return "0";
		}else if(strOdds.contains("受")){
			shourang=true;
			strOdds=strOdds.substring(1, strOdds.length());
		}
		for(int i=0;i<oddsArry.length;i++){
			if(oddsArry[i].equals(strOdds)){
				if(shourang){
					return "-"+oddsNumArry[i];
				}else{
					return oddsNumArry[i];
				}
			}
		}
		return "";
	}
	public static double getOddsDx(String strOdds){
		if(strOdds.contains("/")){
			String [] temp=strOdds.split("/");
			return (Double.valueOf(temp[0])+Double.valueOf(temp[1]))/2;
		}else{
			return Double.valueOf(strOdds);
		}
	}
	
	public static String getOddsArry(double odds){
		
		for(int i=0;i<oddsNumArry.length;i++)
		{
			if(Double.parseDouble(oddsNumArry[i]) == Math.abs(odds))
			{
				if(odds < 0)
				{
					return "受" + oddsArry[i];
				}
				return oddsArry[i];
			}
		}
		return "";
	}
	
	public static String getOddsArry_S(double odds){
		
		for(int i=0; i<oddsNumArry.length; i++)
		{
			if(Double.parseDouble(oddsNumArry[i]) == Math.abs(odds))
			{
				if(odds < 0)
				{
					return "受让" + oddsArry[i];
				} else if(odds == 0) {
					return oddsArry[i];		
				} else {
					return "让" + oddsArry[i];
				}
			}
		}
		return "";
	}
}
