package com.don.DataParser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;


public class CsvParserPerformanceTest 
{
	@org.junit.Test
    public void testParseCsv() throws IOException, ParseException
    {
        
        String path = 
        	"/home/lydonchandra/Documents/workspace-sts-2.5.1.RELEASE/DataParser/src/test/resources/DJIA.csv";
        File file = new File(path);        
        List<Ohlcv> candles = new CsvParser().parseCsv(file);
        
        long start = System.nanoTime();
        final int MAX_ITER = 20;
        for( int idx=0; idx< MAX_ITER; idx++) {
        	candles = new CsvParser().parseCsv(file);
        }
        long end = System.nanoTime();
        
        System.out.println("time=" + (end-start)/1000000 + " ms" );

        // average 3080ms 20 iter
        // average 1350ms 20 iter, after moving out SimpleDateFormat out of loop
    }
}
