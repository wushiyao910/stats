package shiyaowu_CSCI201_Assignment3;

import java.util.Date;

public class Event {
	private String title;
	private String location;
	private int year;
	private int month;
	private int date;
	private String startTime;
	private String endTime;
	
	public Event(String tit, String loc, int yr, int mon, int dat, String start, String end)
	{
		this.title=tit;
		this.location=loc;
		this.year=yr;
		this.month=mon;
		this.date=dat;
		this.startTime=start;
		this.endTime=end;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public int getMonth()
	{
		return this.month;
	}
	
	public int getDate()
	{
		return this.date;
	}
	
	public String print()
	{
		return(this.title+" - "+this.location+" From "+this.startTime+" - "+this.endTime);
	}
}
