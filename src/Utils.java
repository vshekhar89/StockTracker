import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Utils {
	public ArrayList<Stock> extractStockDatafromFile(String fileName, double PRICELIMIT)
	{
		ArrayList fileData = 	new ArrayList();
		fileData = this.ReadFile(fileName);
		String[] stockData = new String[50]; 
		ArrayList<Stock> stockObjects = new ArrayList<Stock>();
		for(int i =0;i<fileData.size(); i++)
		{
			System.out.println(fileData.get(i));
			String fileLine = (String)fileData.get(i);
			stockData = splitStringonSeperator(fileLine,",");
			Stock newStockObj = new Stock(stockData);
			if(newStockObj.isPennyStock(PRICELIMIT) == 1)
			{
				stockObjects.add(newStockObj);
			}
			else
			{
				newStockObj = null;
			}
		}
		return stockObjects;
	}
	public ArrayList<String> ReadFile(String filename)
	{
		String line = null;
		ArrayList<String> fileData = new ArrayList<String>(); 
		try
		{
			FileReader filereader = new FileReader(filename);
			BufferedReader bufreader =  new BufferedReader(filereader);
			while((line = bufreader.readLine()) != null)
			{
				//System.out.println(line);
				fileData.add(line);
			}
			bufreader.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File not found");
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			System.out.println("Error reading file");
			ex.printStackTrace();
		}
		return fileData;
	}
	public String[] splitStringonSeperator(String iString, String seperator)
	{
		String[] tokenizedString = new String[50];
		tokenizedString = iString.split("\\s*\"\\s*");
		return tokenizedString;
	}
	

}
