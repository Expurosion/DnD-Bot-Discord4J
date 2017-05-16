package customObjects.Exceptions;

public class RepeatedTimezoneException extends Exception {


	/**
	 * This is an exception for a timezone which has already been added to the
	 * server's timezone table
	 */
	private static final long serialVersionUID = 1L;

	private String message = "Repeated timezone";

	@Override
	public String getMessage()
	{
		return message;
	}
}
