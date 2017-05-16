package customObjects.Database;

import java.sql.SQLException;
import java.util.HashMap;

import customObjects.Server;

//This is the Database Object. This should not directly execute queries. This should only
// interact with DBAO.
public class DBO {


	// this is a cache
	private HashMap<String, Server> servers = new HashMap<String, Server>();
	private DBAO dbao;

	public DBO() throws SQLException
	{
		dbao = new DBAO();
	}
}
