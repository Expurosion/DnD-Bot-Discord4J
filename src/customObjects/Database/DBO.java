package customObjects.Database;

import java.sql.SQLException;
import java.util.*;

import customObjects.Server;

//This is the Database Object. This should not directly execute queries. This should only
// interact with DBAO.
public class DBO {


	// this is a cache
	private HashMap<Long, Server> servers = new HashMap<Long, Server>();
	private DBAO dbao;

	public DBO() throws SQLException
	{
		dbao = new DBAO();
	}

	public Boolean addTimeZone(TimeZone tz, long longID)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean deleteTimeZone(String timeZoneName, long longID)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<TimeZone> getTimeZones(long longID)
	{
		// TODO Auto-generated method stub
		if (servers.get(longID) != null)
		{
			return servers.get(longID).getTimezones();
		}
		// dbao.get()
		return null;
	}
}
