package bots;

import java.sql.SQLException;

import customObjects.Database.DBO;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class DndBot extends BaseBot implements IListener<MessageReceivedEvent> {


	private DBO dbo;

	public DndBot(IDiscordClient discordClient) throws SQLException
	{
		super(discordClient);

		dbo = new DBO();

		EventDispatcher dispatcher = discordClient.getDispatcher();
		dispatcher.registerListener(this);
	}

	@Override
	public void handle(MessageReceivedEvent event)
	{
		// TODO Auto-generated method stub

	}

}
