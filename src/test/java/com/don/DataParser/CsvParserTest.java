package com.don.DataParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class CsvParserTest 
{
	@org.junit.Test
    public void testParseCsv() throws IOException, ParseException
    {
        
        String path = 
        	"/home/lydonchandra/Documents/workspace-sts-2.5.1.RELEASE/DataParser/src/test/resources/testGLD.csv";
        File file = new File(path);        
        List<Ohlcv> candles = new CsvParser().parseCsv(file);
        
//        Date,Open,High,Low,Close,Volume,Adj Close
//        2010-12-01,135.71,136.14,134.96,135.38,16819700,135.38
//        2010-11-30,135.21,135.75,134.78,135.42,17461700,135.42
//        2010-11-29,133.19,133.75,132.33,133.51,13983800,133.51
//        2010-11-26,132.38,133.16,131.93,133.11,7822400,133.11
//        2010-11-24,134.36,134.70,133.74,134.18,8858100,134.18
        
        Assert.assertEquals(candles.get(0).getOpen(), 13571);
        Assert.assertEquals(candles.get(0).getTime().getTime(), 1262275920000L );
        Assert.assertEquals(candles.get(1).getHigh(), 13575);
        Assert.assertEquals(candles.get(2).getLow(),  13233);
        Assert.assertEquals(candles.get(3).getClose(),13311);
        Assert.assertEquals(candles.get(4).getVolume(), 8858100 );
        
    }
	
	
	@Test
	/**
	 * Courtesy http://www.etraderzone.com/free-scripts/47-historical-quotes-yahoo.html 
	 * http://ichart.finance.yahoo.com/table.csv - The default URL to download historical stock quotes, it won't work if you change the 'table.csv' to something else.
		s - This is where you can specify your stock quote, if you want to download stock quote for Microsoft, just enter it as 's=MSFT'
		a - This parameter is to get the input for the start month. '00' is for January, '01' is for February and so on.
		b - This parameter is to get the input for the start day, this one quite straight forward, '1' is for day one of the month, '2' is for second day of the month and so on.
		c - This parameter is to get the input for the start year
		d - This parameter is to get the input for end month, and again '00' is for January, '02' is for February and so on.
		e - This parameter is to get the input for the end day
		f - This parameter is to get the input for the end year
		g - This parameter is to specify the interval of the data you want to download. 'd' is for daily, 'w' is for weekly and 'm' is for monthly prices. The default is 'daily' if you ignore this parameter.
		With all the parameters above, you can now construct a URL to download historical prices for any stock quotes you want. But if you are going to download all historical prices for a stock quotes from day one onward (eg: Intel), you don't need to crack your head to look for information such as when is Intel went IPO. You just need to ignore the start and end date as follow:
		eg: http://ichart.finance.yahoo.com/table.csv?s=INTC
		If you only specify the start date and ignore the end date, it will download everything right from the start date until the most current prices.
		eg: http://ichart.finance.yahoo.com/table.csv?s=INTC&a=00&b=1&c=2000
	 * 
	 */
	public void testParseCsvReader() throws IOException, ParseException {
		// Yahoo URL
		final String YAHOO_URL = "http://table.finance.yahoo.com/table.csv?s={0}&a={1}&b={2}&c={3}" + "&d={4}&e={5}&f={6}&ignore=.csv&g={7}";

		// retrieve GLD price for 1st and 2nd January 2010
		String urlStr = MessageFormat.format(YAHOO_URL, "GLD", 1, 1, "2010", 1 , 2, "2010", "d");
		
		URL url = new URL(urlStr);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		List<Ohlcv> candles = new CsvParser().parseCsvReader(reader);
		
		//Date,Open,High,Low,Close,Volume,Adj Close
		//2010-02-02,109.16,109.60,108.61,109.13,14292200,109.13
		//2010-02-01,106.64,108.48,106.37,108.35,14901900,108.35
		
		// Opening price for 2nd january 2010
		Assert.assertEquals(candles.get(0).getOpen(), 10916);
		Assert.assertEquals(candles.get(0).getClose(), 10913);
		
		// Opening price for 1nd January 2010
		Assert.assertEquals(candles.get(1).getOpen(), 10664);
		Assert.assertEquals(candles.get(1).getClose(), 10835);
		Assert.assertEquals(candles.get(1).getHigh(), 10848);
		Assert.assertEquals(candles.get(1).getVolume(), 14901900);
		
	}
}
