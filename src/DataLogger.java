import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class DataLogger {
	
	public void logStockAlertData  (ArrayList<Stock> stockList, int run, int hour, int minute) throws IOException
	{
		String filename = "out_files/runData_"+ run + "_"  + hour + "_" + minute +".csv";
		String headerLine = "Ticker,run,hour,minute,intRank, perRank, lastPrice , currentPrice ,alertVal";
		ArrayList<String> data= new ArrayList<String>();
		data.add(headerLine);
		for(int i =0; i<stockList.size();i++)
		{
			String curdata ="";
			curdata = stockList.get(i).Ticker + ","+ run+","+ hour+ ","  + minute +"," + stockList.get(i).stockRank +"," + stockList.get(i).stockPerRank+","+stockList.get(i).lastValue+","+stockList.get(i).currentValue+","+stockList.get(i).stockAlert;
			data.add(curdata);
		}
		Path file = Paths.get(filename);
		Files.write(file,data,Charset.forName("UTF-8"));
	}
	
	public void EndofDayReport(ArrayList<Stock> stockList)
	{
		
	}
}
