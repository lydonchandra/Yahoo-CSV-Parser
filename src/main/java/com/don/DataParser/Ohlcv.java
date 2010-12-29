package com.don.DataParser;

import java.util.Date;

public class Ohlcv {
	private long open;
	private long high;
	private long low;
	private long close;
	private long volume;
	private Date time;
	
	
	public long getOpen() {
		return open;
	}
	public void setOpen(long open) {
		this.open = open;
	}
	public long getHigh() {
		return high;
	}
	public void setHigh(long high) {
		this.high = high;
	}
	public long getLow() {
		return low;
	}
	public void setLow(long low) {
		this.low = low;
	}
	public long getClose() {
		return close;
	}
	public void setClose(long close) {
		this.close = close;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
