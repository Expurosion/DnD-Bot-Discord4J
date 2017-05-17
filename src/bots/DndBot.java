package bots;

import java.sql.SQLException;

import customObjects.Database.DBO;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.*;

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
		IMessage message = event.getMessage(); // Gets the message from the event object NOTE: This is not the content of the message, but the object itself
		IChannel channel = message.getChannel(); // Gets the channel in which this message was sent.
		IGuild guild = message.getGuild();
		String[] msg = message.getContent().split(" ");
		String command = msg[0].toLowerCase();

		if (command.equals("!ping"))
		{
			output("Pong!", channel);
			output("" + guild.getLongID(), channel);
		}
		else if (command.equals("!time"))
		{
			output(time(msg, guild), channel);
		}
		else if (command.equals("!timezone"))
		{
			output(timezone(msg, guild), channel);
		}
		else if (command.equals("!help"))
		{
			if (msg.length == 1)
			{
				// TODO output the different help commands
			}
			else
			{
				String action = msg[1];

			}
		}
	}

	private void output(String output, IChannel channel)
	{
		try
		{
			new MessageBuilder(this.client).withChannel(channel).withContent(output).build();
		}
		catch (RateLimitException e)
		{
			System.err.print("Sending messages too quickly!");
		}
		catch (DiscordException e)
		{
			System.err.print(e.getErrorMessage());
			e.printStackTrace();
		}
		catch (MissingPermissionsException e)
		{
			System.err.print("Missing permissions for channel!");
			e.printStackTrace();
		}
	}

	private String time(String[] msg, IGuild guild)
	{
		String output = null;
		long guildID = guild.getLongID(); // Guild ID, so you remember it
		if (msg.length > 0)
		{
			// TODO finish
		}
		else
		{
			output = "Must specify a time to convert! Type '!help time' for more details.";
		}

		return output;
	}

	private String timezone(String[] msg, IGuild guild)
	{
		String output = null;
		String help = " Type '!help timezone' for more details.";

		if (msg.length > 1)
		{
			String action = msg[1];
			if (msg.length > 2)
			{
				if (action.equals("add"))
				{
					String timeZoneName = msg[2];
					if (msg.length > 3)
					{
						// TODO create TimeZone object
						if (msg[3].startsWith("+"))
						{

						}
						else if (msg[3].startsWith("-"))
						{

						}
						else
						{
							output = "Invalid time!" + help;
						}
					}
					else
					{
						output = "Must input a time!" + help;
					}
				}
				else if (action.equals("edit"))
				{
					// TODO add 'edit' functionality
				}
				else if (action.equals("delete"))
				{
					// TODO add 'delete' functionality
				}
				else
				{
					output = "Invalid argument!" + help;
				}
			}
			else
			{
				if (action.equals("add"))
				{
					output = "Must specify a timezone to add!";
				}
				else
				{
					output = "Must specify a timezone to modify!";
				}
			}
		}
		else
		{
			output = "Must specify an action." + help;
		}

		return output;
	}

}
