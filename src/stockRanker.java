import java.io.IOException;
import java.util.*;
import org.json.JSONException;

public class stockRanker {
	
	public void updateStockfromJSON(String YahooJSONDdata, int run, ArrayList<Stock> stockList) throws IOException
	{
		String field1 = "LastTradePriceOnly";
		Map<String,String> results = null;
		CustomJSONParser parser = new CustomJSONParser();
		try
		{
			 results = parser.parseJSON(YahooJSONDdata, field1);
		}
		catch(JSONException ex)
		{
			ex.printStackTrace();
		}
		for(String key : results.keySet())
		{
			String fieldValue = results.get(key);
			double price = Double.parseDouble(fieldValue);
			for(int i = 0; i<stockList.size(); i++)
			{
				if(stockList.get(i).Ticker.compareTo(key) == 0)
				{
					//System.out.println("Updating stock price of "+key + " &  "+  stockList.get(i).Ticker + "to "+ price);
					if(run == 0)
					{
						stockList.get(i).lastValue = price;
						stockList.get(i).currentValue = price;
						stockList.get(i).initialValue = price;
					}
					else
					{
						stockList.get(i).lastValue = stockList.get(i).currentValue;
						stockList.get(i).currentValue = price;
						if(stockList.get(i).currentValue > stockList.get(i).lastValue)
						{
							stockList.get(i).stockRank = stockList.get(i).stockRank +1;
						}
						else if(stockList.get(i).currentValue < stockList.get(i).lastValue)
						{
							stockList.get(i).stockRank = stockList.get(i).stockRank -1;
						}
						double perincr = ((stockList.get(i).currentValue-stockList.get(i).lastValue)/stockList.get(i).lastValue);
						stockList.get(i).stockPerRank = stockList.get(i).stockPerRank + perincr;
					}
					if(stockList.get(i).stockRank == 3 && stockList.get(i).reached_rank_3 == 0)
					{
						stockList.get(i).stockAlert = 1;
						stockList.get(i).run_rank_3 = run;
						stockList.get(i).price_rank_3 = price;
						stockList.get(i).reached_rank_3 = 1;
					}
					if(stockList.get(i).stockRank == 5 && stockList.get(i).reached_rank_5 == 0)
					{
						stockList.get(i).stockAlert = 1;
						stockList.get(i).run_rank_5 = run;
						stockList.get(i).price_rank_5 = price;
						stockList.get(i).reached_rank_5 =1;
					}
				}
			}
		}		
	}
}
