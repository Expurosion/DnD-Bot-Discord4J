import java.sql.SQLException;

import bots.DndBot;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Main {


	public static void main(String[] args)
	{
		String token = args[0];
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
