package com.accolite.miniAU;

import java.time.LocalDate;

public class LicenceData 
{
	int pr;
	int l_id;
	String state;
	LocalDate change_date;
	char res;
	String lic_class;
	LocalDate expiredate;
	char status;
	
	
	
	
	@Override
	public int hashCode() 
	{
		final int value = 31;
		int total = 1;
		total = value * total + ((change_date == null) ? 0 : change_date.hashCode());
		total = value * total + l_id;
		total = value * total + pr;
		total = value * total + ((state == null) ? 0 : state.hashCode());
		return total;
	}
	@Override
	public boolean equals(Object ob) 
	{
		LicenceData other = (LicenceData) ob;
		if (this == ob)
			return true;
		
		if (ob == null)
			return false;
		
		if (change_date == null) 
		{
		
			if (other.change_date != null)
				return false;
		} 
		else if (!change_date.equals(other.change_date))
			return false;
		if (l_id != other.l_id)
			return false;
		if (pr != other.pr)
			return false;
		if (state == null) 
		{
			if (other.state != null)
				return false;
		} 
		else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() 
	{
		return "" + pr + "," + l_id + "," + state
				+"," + res + "," +lic_class
				+ "," + change_date +  "," +  expiredate + "," + status + "\n";
	}
	
	
}