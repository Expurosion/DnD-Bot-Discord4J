package bots;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimeZone;

import customObjects.Offset;
import customObjects.Database.DBO;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.*;

public class DndBot extends BaseBot implements IListener<MessageReceivedEvent> {


	private DBO dbo;
	final String help = " Type '!help timezone' for more details.";

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
			output("ServerID:" + guild.getLongID(), channel);
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
				output(help(), channel);
			}
			else
			{
				String action = msg[1].toLowerCase();
				output(help(action), channel);
			}
		}
	}

	private String help()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private String help(String action)
	{
		// TODO Auto-generated method stub
		String output = "";
		if (action.equals("time"))
		{
			output = "";
		}
		else if (action.equals("timezone"))
		{
			output = "Actions: add, delete, getall \n" + "  add: Adds a timezone to the server.\n"
					+ "    Format: '!timezone add %timezone%'. \n"
					+ "    Appropriate timezones can be found on http://joda-time.sourceforge.net/timezones.html  \n"
					+ "  delete: Deletes a timezone from the server.\n"
					+ "    Format: '!timezone delete %timezone%' \n"
					+ "  getall: Gets all timezones associated with the server.\n"
					+ "    Format: '!timezone getall' \n";
		}
		else if (action.equals("ping"))
		{
			output = "Returns a message with the value 'Pong!' for testing purposes, and the server'd ID.";
		}
		return output;
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
		String output = "";

		if (msg.length > 1)
		{
			String action = msg[1].toLowerCase();
			if (action.equals("add")) // adds a timezone to the server's database
			{
				if (msg.length == 3)
				{
					String timeZoneName = msg[2];
					String[] tzs = TimeZone.getAvailableIDs();
					Boolean added = false;
					for (String timeZone : tzs)
					{
						if (timeZoneName.equals(timeZone))
						{
							added = true;
							TimeZone tz = TimeZone.getTimeZone(timeZoneName);
							Boolean notRepeat = dbo.addTimeZone(tz, guild.getLongID());
							output = "Added " + tz.getDisplayName();
							if (!notRepeat)
							{
								output = "Repeated timezone!" + help;
							}
						}
					}

					if (!added)
					{
						output = "Invalid timezone!" + help;
					}
				}
				else
				{
					output = "Must specify a timezone to add!" + help;
				}
			}

			else if (action.equals("delete")) // deletes a timezone from the server's database
			{
				if (msg.length == 3)
				{
					String timeZoneName = msg[2];
					Boolean deleted = dbo.deleteTimeZone(timeZoneName, guild.getLongID());
					if (deleted)
					{
						output = "Deleted " + timeZoneName;
					}
					else
					{
						output = "Timezone not associated with server!" + help;
					}
				}
				else
				{
					output = "Must specify a timezone to delete!" + help;
				}
			}

			else if (action.equals("getall")) // outputs all timezones associated with the server
			{
				ArrayList<TimeZone> timezones = dbo.getTimeZones(guild.getLongID());
				for (int i = 0; i < timezones.size(); i++)
				{
					TimeZone timezone = timezones.get(i);
					Offset offset = new Offset(timezone.getRawOffset());

					output = output + timezone.getID() + offset.getSign() + offset.getHour() + ":"
							+ offset.getMinute();

					// add a comma and space if not the last item in the array
					if (i < timezones.size() - 1)
					{
						output = output + ", ";
					}
				}
				if (timezones.size() == 0)
				{
					output = "No timezones associated with this server!" + help;
				}
			}
			else
			{
				output = "Invalid action!" + help;
			}
		}

		// No action specified
		else
		{
			output = "Must specify an action." + help;
		}

		return output;
	}

}
