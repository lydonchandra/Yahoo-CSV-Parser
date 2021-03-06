package com.don.DataParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class CsvParser {

	public List<Ohlcv> parseCsvReader(Reader urlReader) throws IOException, ParseException {
		
		return getCandles(new BufferedReader(urlReader));
		
	}
	

	public List<Ohlcv> parseCsv(File file) throws IOException, ParseException {
	    
	    if( file.exists() && file.canRead() ) {
	    	BufferedReader reader = new BufferedReader( 
	    								new InputStreamReader( 
	    									new FileInputStream(file)) );
	    
	    	return getCandles(reader);
	    }
	    
	    // return empty list if file does not exist
	    return new ArrayList<Ohlcv>();
	}

	
	
	public static final String YAHOO_DATE = "yyyy-mm-dd";
	
	public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YAHOO_DATE);
		
	public List<Ohlcv> getCandles(BufferedReader reader) throws IOException, ParseException {
		List<Ohlcv> candles = new ArrayList<Ohlcv>();
		
		String aLine = reader.readLine();
		
    	StringTokenizer str = null;
    	
    	while ((aLine = reader.readLine()) != null) {
			
			str = new StringTokenizer(aLine, ",");
			String dateStr = str.nextToken();
			
			Date theDate = simpleDateFormat.parse(dateStr);
			long open = (long) (Double.parseDouble( str.nextToken()) * 100L);
			long high = (long) (Double.parseDouble( str.nextToken()) * 100L);
			long low = (long)  (Double.parseDouble( str.nextToken()) * 100L);
			long close = (long) (Double.parseDouble( str.nextToken()) * 100L);
			long volume = (long) Long.parseLong(str.nextToken());
			
			Ohlcv ohlcv = new Ohlcv();
			ohlcv.setClose(close);
			ohlcv.setHigh(high);
			ohlcv.setOpen(open);
			ohlcv.setLow(low);
			ohlcv.setVolume(volume);
			ohlcv.setTime(theDate);
			
			candles.add(ohlcv);
		}
    	
    	return candles;
	}

}
