import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class DataLogger {

	public void logStockAlertData  (ArrayList<Stock> stockList, int run, int hour, int minute) throws IOException
	{
		String filename = "runData_"+ run + "_"  + hour + "_" + minute +".csv";
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
	
	public void EndofDayReport(ArrayList<Stock> stockList) throws IOException
	{
		String filename = "final_report.csv";
		String headerline = "Ticker,Closing_price, rank_3_run, rank_3_price,rank_5_run, rank_5_price";
		ArrayList<String> data = new ArrayList<String>();
		data.add(headerline);
		String lineData = null;
		for(int i = 0; i<stockList.size(); i++)
		{
			if(stockList.get(i).stockAlert == 1  && stockList.get(i).reached_rank_3 == 1)
			{
				lineData = stockList.get(i).Ticker+ ","+stockList.get(i).currentValue+","+stockList.get(i).run_rank_3+","+stockList.get(i).price_rank_3;
				if(stockList.get(i).reached_rank_5 == 1)
				{
					lineData = lineData + "," + stockList.get(i).run_rank_5+","+stockList.get(i).price_rank_5;
				}
				data.add(lineData);
			}
		}
		Path file = Paths.get(filename);
		Files.write(file,data,Charset.forName("UTF-8"));
	}
}
