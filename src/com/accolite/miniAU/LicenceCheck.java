package com.accolite.miniAU;



import java.time.LocalDate;

public class LicenceCheck extends LicenceData
{
	String la ;
	LocalDate laChangeDate;
	LocalDate laExpiryDate;
	char laStatus;
	
	@Override
	public String toString()
	{
		return "" + pr + "," + l_id + "," + state
				+"," + res + "," +lic_class
				+ "," + change_date +  "," +  expiredate + "," + status + ","+ la + "," + laChangeDate + ","
				+ laExpiryDate + "," 
				+ laStatus + "\n";
	}
	
}