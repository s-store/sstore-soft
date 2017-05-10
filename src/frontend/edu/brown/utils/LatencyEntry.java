package edu.brown.utils;

public class LatencyEntry {
	public long id;
	public String className;
	public String description;
	public long startTime;
	public long endTime;
	public long eventTime;
	
	public LatencyEntry(long id, String className, String description, long startTime)
	{
		this.id = id;
		this.className = className;
		this.description = description;
		this.startTime = startTime;
	}
	
	public void setEndTime(long endTime)
	{
		this.endTime = endTime;
		this.eventTime = this.endTime - this.startTime;
	}
}