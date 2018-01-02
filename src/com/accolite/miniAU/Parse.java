package com.accolite.miniAU;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parse {
public static void main(String [] args) throws Exception 
{
	DocumentBuilderFactory dataFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dataBuild = dataFactory.newDocumentBuilder();
	BufferedWriter attach=null;
	BufferedWriter one=null;
	BufferedWriter two =null;
	File file1 = new File("C:\\Users\\Hyderabad\\workspace\\AdvanceJava\\res\\example.xml");
	
	
	Document doc = null;
	try 
	{
		doc = dataBuild.parse(file1);
	}
	catch(Exception e)
	{
		System.out.println("Reading File 1 Exception");
		System.exit(1);
	}

	
	
	try 
	{
	 attach = new BufferedWriter(new FileWriter("C:\\Users\\Hyderabad\\workspace\\AdvanceJava\\output\\Attach.csv"));
	 one = new BufferedWriter(new FileWriter("C:\\Users\\Hyderabad\\workspace\\AdvanceJava\\output\\licenses-invalid.csv"));
	 two = new BufferedWriter(new FileWriter("C:\\Users\\Hyderabad\\workspace\\AdvanceJava\\output\\invalid-license-file.csv"));
	}
	catch(FileNotFoundException e)
	{
		System.out.println("output file cant create");
		System.exit(1);
	}
	doc.getDocumentElement().normalize();
	
	
	attach.write("nipr,License ID,Jurisdiction,Resident,License Class,License Effective Date,License Expiry Date,License Status,License Line,License Line Effective Date,License Line Expiry Date,License Line Status\n");
	one.write("nipr,License ID,Jurisdiction,Resident,License Class,License Effective Date,License Expiry Date,License Status\n");
	two.write("nipr,License ID,Jurisdiction,Resident,License Class,License Effective Date,License Expiry Date,License Status,License Line,License Line Effective Date,License Line Expiry Date,License Line Status\n");


	NodeList lsNode = doc.getElementsByTagName("CSR_Producer");
	ArrayList<LicenceData> l1 = new ArrayList<LicenceData>();

	for (int j = 0; j < lsNode.getLength(); j++) {
		Node node = lsNode.item(j);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			NodeList licence = eElement.getElementsByTagName("License");
			for(int i =0;i<licence.getLength();i++)
			{
				LicenceData l = new LicenceData();
				try {
					l.pr = Integer.parseInt(eElement.getAttribute("NIPR_Number"));
				}
				catch(NumberFormatException e)
				{
					l.pr = 0;
				}
				Element lic = (Element)licence.item(i);
				
				try {
					l.l_id = Integer.parseInt(lic.getAttribute("License_Number"));
				}
				catch(NumberFormatException e)
				{
					l.l_id = 0;
				}
				l.state = lic.getAttribute("State_Code");
				try {
					l.change_date = LocalDate.parse(lic.getAttribute("License_Issue_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				}
				catch(DateTimeParseException e)
				{
					l.change_date = LocalDate.now();
				}
				try {
					l.expiredate = LocalDate.parse(lic.getAttribute("License_Expiration_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				}
				catch(DateTimeParseException e)
				{
					l.expiredate = l.change_date.plusYears(2);
				}
				l.lic_class = lic.getAttribute("License_Class");
				try {
					l.res = lic.getAttribute("Resident_Indicator").charAt(0);
				}
				catch(StringIndexOutOfBoundsException e)
				{
					l.res = ' ';
				}
				try {
				l.status = lic.getAttribute("License_Status").charAt(0);
				}
				catch(StringIndexOutOfBoundsException e)
				{
					l.status = ' ';
				}
				l1.add(l);
			
			}
		}
	
	}

	File file2 = new File("C:\\Users\\Hyderabad\\workspace\\AdvanceJava\\res\\example2.xml");
	Document doc2 = null;
	try {
		doc2 = dataBuild.parse(file2);
	}
	catch(FileNotFoundException e)
	{
		System.out.println("Input File not found");
	}
	doc2.getDocumentElement().normalize();



	NodeList listN = doc2.getElementsByTagName("CSR_Producer");
	ArrayList<LicenceData> l2 = new ArrayList<LicenceData>();

	for (int temp = 0; temp < listN.getLength(); temp++) {
		Node nNode = listN.item(temp);

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;

			NodeList licenses = eElement.getElementsByTagName("License");
			for(int i =0;i<licenses.getLength();i++)
			{
		
				Element lic = (Element)licenses.item(i);
		
				NodeList loa = lic.getElementsByTagName("LOA");
				for(int j =0;j<loa.getLength();j++)
				{
					LicenceCheck l = new LicenceCheck();
					try {
						l.pr = Integer.parseInt(eElement.getAttribute("NIPR_Number"));
					}
					catch(NumberFormatException e)
					{
						l.pr = 0;
					}
					
					try {
						l.l_id = Integer.parseInt(lic.getAttribute("License_Number"));
					}
					catch(NumberFormatException e)
					{
						l.l_id = 0;
					}
					l.state = lic.getAttribute("State_Code");
					try {
						l.change_date = LocalDate.parse(lic.getAttribute("License_Issue_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					}
					catch(DateTimeParseException e)
					{
						l.change_date = LocalDate.now();
					}
					try {
						l.expiredate = LocalDate.parse(lic.getAttribute("License_Expiration_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					}
					catch(DateTimeParseException e)
					{
						l.expiredate = l.change_date.plusYears(2);
					}
					l.lic_class = lic.getAttribute("License_Class");
					try {
						l.res = lic.getAttribute("Resident_Indicator").charAt(0);
					}
					catch(StringIndexOutOfBoundsException e)
					{
						l.res = ' ';
					}
					try {
					l.status = lic.getAttribute("License_Status").charAt(0);
					}
					catch(StringIndexOutOfBoundsException e)
					{
						l.status = ' ';
					}
					Element line = (Element) loa.item(j);
					l.la = line.getAttribute("LOA_Name");	
					try {
						l.laChangeDate = LocalDate.parse(line.getAttribute("LOA_Issue_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					
					}
					catch(DateTimeParseException e)
					{
						l.laChangeDate = LocalDate.now();
					}
					l.laExpiryDate = l.laChangeDate.plusYears(2);
					try {
						l.laStatus = line.getAttribute("LOA_Status").charAt(0);
					}
					catch(StringIndexOutOfBoundsException e)
					{
						l.laStatus = ' ';
					}
					if(l1.contains(l))
					{
						attach.write(l.toString());
					}
					else
					{
						two.write(l.toString());
					}
					l2.add(l);
				}
		
		
		
			}	
		}

	}


	for(LicenceData l:l1)
	{
		if (!l2.contains(l))
		{
			one.write(l.toString());
		}	
	}

	attach.close();
	one.close();
	two.close();
	System.out.println("All Done");
	}
}