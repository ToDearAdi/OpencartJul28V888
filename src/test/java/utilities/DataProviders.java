package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 

	{	
		@DataProvider(name="LoginData")
		public String [][] getData() throws IOException
		{
			String path=".\\testData\\Opencart_Login_Data.xlsx"; //Taking excel file from testData folder
			
			ExcelUtility xlutil=new ExcelUtility(path); //Creating an object for XLUtility
			
			int totalrows=xlutil.getRowCount("Sheet1");
			int totalcols=xlutil.getCellCount("Sheet1", 1);
			
			String logindata[][]=new String [totalrows][totalcols];// Created for two dimension array whihc can store 
			
			for(int i=1;i<=totalrows;i++) //1 //Read the data from excel file storing in two dimensional array
			{
				for(int j=0;j<totalcols;j++) //0 // "i" is 'rows' & "j" so 'cols'
				{
					logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j); //1, 0
				}
			}
			
			return logindata; //returning two dimensional array
		}
		
		//Data Provider #2
		
		//Data provider #3
		
		//Data Provider #4
	}
