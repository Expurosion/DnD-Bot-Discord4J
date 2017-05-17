import java.sql.SQLException;

import bots.DndBot;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Main {


	public static void main(String[] args)
	{
		String token = "MzEyMjcxMDYyODUzNDg0NTQ3.C_0i6Q.K45J7FihibrXpGqVXGfggPqSjoY";
		IDiscordClient discordClient = new ClientBuilder().withToken(token).login();
		try
		{
			DndBot bot = new DndBot(discordClient);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
