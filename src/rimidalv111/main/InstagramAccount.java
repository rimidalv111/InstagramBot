package rimidalv111.main;

public class InstagramAccount
{
	private String accountName;
	private String clientID;
	private String clientSecret;
	private String callback;

	public String getAccountName()
	{
		return accountName;
	}

	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}

	public String getClientID()
	{
		return clientID;
	}

	public void setClientID(String clientID)
	{
		this.clientID = clientID;
	}

	public String getClientSecret()
	{
		return clientSecret;
	}

	public void setClientSecret(String clientSecret)
	{
		this.clientSecret = clientSecret;
	}

	public String getCallback()
	{
		return callback;
	}

	public void setCallback(String callback)
	{
		this.callback = callback;
	}
}
