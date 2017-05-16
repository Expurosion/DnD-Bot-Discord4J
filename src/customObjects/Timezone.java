package customObjects;

public class Timezone {


	private String name;
	private int utcHours;
	private int utcMinutes;

	public Timezone(String name, int utcHours, int utcMinutes)
	{
		super();
		this.name = name;
		this.utcHours = utcHours;
		this.utcMinutes = utcMinutes;
	}

	public String getName()
	{
		return name;
	}

	public int getUtcHours()
	{
		return utcHours;
	}

	public int getUtcMinutes()
	{
		return utcMinutes;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setUtcHours(int utcHours)
	{
		this.utcHours = utcHours;
	}

	public void setUtcMinutes(int utcMinutes)
	{
		this.utcMinutes = utcMinutes;
	}

}
