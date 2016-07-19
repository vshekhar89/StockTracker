
public class Stock {
	public String Ticker;
	public String cName;
	public double lastSale;
	public String sector;
	public String Industry;
	public double currentValue;
	public double lastValue;
	public double initialValue;
	public int stockRank;
	public double  stockPerRank;
	public int stockAlert;
	public int run_rank_3;
	public double price_rank_3;
	public int reached_rank_3;
	public int run_rank_5;
	public double price_rank_5;
	public int reached_rank_5;
	
	public Stock(String[] data)
	{
		this.Ticker = data[1];
		this.cName = data[3];
		try
		{
			this.lastSale = Double.parseDouble(data[5]);
		}
		catch(NumberFormatException ex )
		{
			System.out.println("Cannot parse last sale value for stock" + this.Ticker);
			this.lastSale = 0;
		}
		this.sector = data[11];
		this.Industry = data[13];
		this.currentValue = this.lastSale;
		this.lastValue = this.lastSale;
		this.initialValue = this.lastSale;
		this.stockRank = 0;
		this.stockPerRank = 0;
		this.stockAlert =0;
	}
	public int isPennyStock(double criteria)
	{
		int isPennyStock =0;
		if(this.currentValue<=criteria  && this.currentValue != 0)
		{
			isPennyStock =1;
		}
		return isPennyStock;
	}
	public void printStockData()
	{
		System.out.println("Information for Stock " + this.Ticker);
		System.out.println("\t\tCompany Name :" + this.cName );
		System.out.println("\t\tCurrent Value  : " + this.currentValue);
		System.out.println("\t\tsector : " + this.sector);
		System.out.println("\t\tIndustry" + this.Industry);
	}
}
