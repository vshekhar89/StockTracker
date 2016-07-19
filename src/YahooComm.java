import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.lang.*;


public class YahooComm {
	
public String getXMLDatforURL(String url, int mode ) throws IOException{
		
	String JSONresponse = null;
	try 
		{
			//System.out.println("incoming url " + url);
			
	        String testurl = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22AAPL%22)&env=store://datatables.org/alltableswithkeys";
	        URL requrl = new URL(url); 
	        HttpURLConnection con = (HttpURLConnection) requrl.openConnection();
	        if(mode==1){
	        	File file = new File("data/jsondata.txt");
	        	BufferedReader reader = new BufferedReader(new FileReader(file));
	        	StringBuilder sb = new StringBuilder();
	        	String line;
	        	while((line= reader.readLine())!=null){
	        		sb.append(line);
	        	}
	
	        	JSONresponse = sb.toString();
	        }
	        else
	        {
	        		// Set the HTTP Request type method to GET (Default: GET)
	        
		        con.setRequestMethod("GET");

		        // Created a BufferedReader to read the contents of the request.
		        BufferedReader in = new BufferedReader(
		                new InputStreamReader(con.getInputStream()));
		        String inputLine;
		        StringBuilder response = new StringBuilder();

		        while ((inputLine = in.readLine()) != null) {
		            response.append(inputLine);
		        }

		        // MAKE SURE TO CLOSE YOUR CONNECTION!
		        in.close();

		        // response is the contents of the XML
		        //System.out.println(response.toString());
		        JSONresponse = response.toString();
	        }   
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return JSONresponse;
	}
	
}
