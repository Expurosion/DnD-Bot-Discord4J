package customObjects.Database;

import java.sql.*;

//This is the Database Access Object. All queries should be executed here.
public class DBAO {


	private Connection connect = null;
	private Statement statement = null;

	public DBAO() throws SQLException
	{
		connect = DriverManager.getConnection("jdbc:mysql://localhost/bot", "root", "");
		statement = connect.createStatement();
	}
}
