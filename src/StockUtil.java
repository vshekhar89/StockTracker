import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import javax.print.attribute.standard.DateTimeAtCompleted;

public class StockUtil {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fname = "data/companylist_nasdaq.csv";
		ArrayList<Stock> NasdaqStocks = new ArrayList<Stock>();
		ArrayList<String> URLlist = new ArrayList<String>();
		YahooComm yahooBot = new YahooComm();
		DataLogger databot = new DataLogger(); 
		stockRanker rankbot = new stockRanker();
		double PRICELIMIT = 25.0;
		int MAXURL = 20;
		int END_HOUR = 15;
		int END_MINUTE = 00;
		int SLEEPTIME = 900000;
		//get a object of utils class 
		Utils utilInst = new Utils();
		UrlCreator  urlbot = new UrlCreator();
		//get stock data from the csv file 
		int run =0;
		int hour, minute;
		NasdaqStocks = utilInst.extractStockDatafromFile(fname,PRICELIMIT);
		// 
		int run_exception=1;
		while(run_exception==1)
		{
			hour= LocalDateTime.now().getHour();
			minute= LocalDateTime.now().getMinute();
			if(NasdaqStocks.size() < 1)
			{
				System.out.println("ERROR: No stock data read from csv file " + fname);
			}
			else
			{
				/*	for(int i = 0;i<NasdaqStocks.size(); i++)
				{
					//NasdaqStocks.get(i).printStockData();
				
				}*/
				System.out.println("Starting query Run number = " + run + "at time" + hour+ ":" +minute);
				System.out.println("Total number of stocks  for this call= "+ NasdaqStocks.size());
				System.out.println("generating URLs");
				URLlist = urlbot.createStockURL(NasdaqStocks,MAXURL);
				System.out.println("Total numbe of URLs generated = " + URLlist.size());
				if(URLlist.size() > 0)
				{
					System.out.println("Sending Request to Yahoo");
					for(int k =0; k<URLlist.size();k++)
					{
						String YahooJSONdata = yahooBot.getXMLDatforURL(URLlist.get(k), 0);
						//System.out.println(YahooJSONdata);
						if(YahooJSONdata != null)
						{
							rankbot.updateStockfromJSON(YahooJSONdata,run,NasdaqStocks);
						}
					}
				}
			}
			if(hour > END_HOUR ||(hour == END_HOUR && minute > END_MINUTE))
			{
				System.out.print("System Time "+ hour + ":" + minute + " Terminating program");
				databot.EndofDayReport(NasdaqStocks);
				break;
			}
			else
			{
				databot.logStockAlertData(NasdaqStocks, run, hour, minute);
				run++;
				try 
				{
					System.out.print("System Time "+ hour + ":" + minute +"\tPutting thread to sleep for " + SLEEPTIME+ " milliseconds");
					Thread.sleep(SLEEPTIME);
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}
			}
		}	
		
	}
	
}
