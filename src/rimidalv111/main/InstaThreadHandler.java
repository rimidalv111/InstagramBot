package rimidalv111.main;

import java.util.ArrayList;

public class InstaThreadHandler
{
	private Instabot instabot;
	private InstagramAccount account;

	public InstaThreadHandler(Instabot ib, InstagramAccount ac)
	{
		instabot = ib;
		//read, load config
		account = ac;

		new InstagramConnectThread(this).start();
	}

	private ArrayList<String> runningThreads = new ArrayList<String>();

	public void startBot(int id)
	{
		if(id == 0) //follower
		{
			if(!runningThreads.contains("follower"))
			{
				runningThreads.add("follower");
				//do the work

			} else
			{
				System.out.println("Already running follower thread. Stop it first!");
			}
		} else
			if(id == 1) //unfollower
			{
				if(!runningThreads.contains("unfollower"))
				{
					runningThreads.add("unfollower");
					//do the work

				} else
				{
					System.out.println("Already running unfollower thread. Stop it first!");
				}
			} else
				if(id == 2) //liker
				{
					if(!runningThreads.contains("liker"))
					{
						runningThreads.add("liker");
						//do the work

					} else
					{
						System.out.println("Already running liker thread. Stop it first!");
					}
				}
		//System.out.println("Starting: " + id);
	}

	public void stopBot(int id)
	{
		if(id == 0) //follower
		{
			if(runningThreads.contains("follower"))
			{
				runningThreads.remove("follower");
				//do the work

			} else
			{
				System.out.println("Follower Thread Not Running, Start it first...");
			}
		} else
			if(id == 1) //unfollower
			{
				if(runningThreads.contains("unfollower"))
				{
					runningThreads.remove("unfollower");
					//do the work

				} else
				{
					System.out.println("Unfoller Thread Not Running, Start it first...");
				}
			} else
				if(id == 2) //liker
				{
					if(runningThreads.contains("liker"))
					{
						runningThreads.remove("liker");
						//do the work

					} else
					{
						System.out.println("Liker Thread Not Running, Start it first...");
					}
				}
		//System.out.println("Stoping: " + id);
	}

	public Instabot getInstabot()
	{
		return instabot;
	}

	public void setInstabot(Instabot instabot)
	{
		this.instabot = instabot;
	}

	public InstagramAccount getAccount()
	{
		return account;
	}

	public void setAccount(InstagramAccount account)
	{
		this.account = account;
	}

	public ArrayList<String> getRunningThreads()
	{
		return runningThreads;
	}

	public void setRunningThreads(ArrayList<String> runningThreads)
	{
		this.runningThreads = runningThreads;
	}
}
