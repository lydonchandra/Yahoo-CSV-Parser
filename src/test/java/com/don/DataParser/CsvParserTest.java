package com.don.DataParser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;


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
}
