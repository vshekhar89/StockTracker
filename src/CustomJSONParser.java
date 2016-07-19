import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.*;

public class CustomJSONParser {
	
	public Map<String,String> parseJSON(String data,String field) throws JSONException, IOException{
		
		if(field==null || field.length()==0){
			throw new RuntimeException("Please enter a field to be searched");
		}
		Map<String,String> map = new HashMap<String,String>();
		
		JSONObject obj = new JSONObject(data);
		JSONObject resultsObject = obj.getJSONObject("query").getJSONObject("results");
		JSONArray quoteArr = resultsObject.getJSONArray("quote");
		
		for (int i = 0; i < quoteArr.length(); i++)
		{
		    String stockName = quoteArr.getJSONObject(i).getString("symbol");
		    Object fieldObject = quoteArr.getJSONObject(i).get(field);
		    String fieldValue;
		    if(fieldObject instanceof Integer){
		    	fieldValue = String.valueOf(fieldObject);
		    }else{
		    	 fieldValue = fieldObject.toString();
		    }
		    map.put(stockName,fieldValue);
		    //System.out.println(fieldValue);
		}
		return map;
	}

}
