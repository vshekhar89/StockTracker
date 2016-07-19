import java.util.*;
import java.lang.*;
public class UrlCreator {

	public ArrayList<String> createStockURL(ArrayList<Stock> stockList, int MAXURL)
	{
		int numStocks = stockList.size();
		double numStockinURL = Math.ceil(numStocks/MAXURL);
		ArrayList<String> stockTickerList = new ArrayList<String>();
		ArrayList<String> stockURLs  = new ArrayList<String>();
		String URL;
		for(int i =0; i<numStocks; i++)
		{
			stockTickerList.add(stockList.get(i).Ticker);
			if(i != 0 && i%MAXURL == 0)
			{
				URL = createURL(stockTickerList);
				//System.out.println(URL);
				stockURLs.add(URL);
				stockTickerList.clear();
			}			
		}
		if((numStocks % MAXURL)  != 0 )
		{
			URL = createURL(stockTickerList);
			stockURLs.add(URL);
			System.out.println(URL);
			stockTickerList.clear();
		}		
		System.out.println("number of urls = " + stockURLs.size());
		return stockURLs;
	}
	
	public String createURL(ArrayList<String> stockTickerList)
	{
		String baseURL = "http://query.yahooapis.com/v1/public/yql?q=";
		String endURL = "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
		StringBuilder queryString = new StringBuilder();
		queryString.append("select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(");
		
		for(int i =0 ; i<stockTickerList.size() ;i++)
		{
			queryString.append("\"");
			queryString.append(stockTickerList.get(i));
			queryString.append("\",");
			
		}
		queryString.deleteCharAt(queryString.length()-1);
		queryString.append(")");
		String queryURL = queryString.toString();
		String URL = baseURL + queryURL + endURL;
		return URL;
		
	}
	
}
