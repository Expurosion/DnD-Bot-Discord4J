package customObjects;

import java.util.ArrayList;
import java.util.TimeZone;

import customObjects.Exceptions.RepeatedTimezoneException;

//This is a server. This should be identified by a guild ID, and contain all the data
// that this bot needs to access. This is for caching purposes, so that the DBO does not
// need to access the database every time somebody uses a command that would otherwise
// require database access. These are to be stored on a hash map in DBO.
public class Server {


	private long id; // guild id
	private String name; // server name
	private ArrayList<TimeZone> timezones = new ArrayList<TimeZone>();

	public Server(long id, String name, ArrayList<TimeZone> timezones)
	{
		super();
		this.id = id;
		this.name = name;
		this.timezones = timezones;
	}

	// Adds a timezone to the array, throws a RepeatedTimezoneException
	public void addTimezone(TimeZone timezone) throws RepeatedTimezoneException
	{
		Boolean repeat = false;
		for (int i = 0; i < timezones.size(); i++)
		{
			if (timezones.get(i).equals(timezone))
			{
				repeat = true;
			}
		}
		if (!repeat)
		{
			timezones.add(timezone);
		}
		else
		{
			throw new RepeatedTimezoneException();
		}
	}

	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<TimeZone> getTimezones()
	{
		return timezones;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
