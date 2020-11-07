package com.rimidalv111.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class FindUsersThread extends Thread
{
	private Main main;
	private String userPageUrl = "http://iconosquare.com/viewer.php";
	private List<String> foundUsers;
	private List<String> followedUsers;
	private int howManyUsersToFind;
	private FollowThread followThread;

	private int totalFollowed;
	private boolean findMoreUsers;

	public FindUsersThread(Main u)
	{
		main = u;
		foundUsers = new ArrayList<String>();
		followedUsers = new ArrayList<String>();
		followThread = new FollowThread(this);
		howManyUsersToFind = 0;
		totalFollowed = 0;
		findMoreUsers = true;

	}

	@Override
	public void run()
	{
		while (findMoreUsers)
		{
			try
			{
				if(totalFollowed >= 3000)
				{
					main.setUnfollowUsers(true);
					main.closeAllRunningThreadsAndRestart();
				}
				//use new hashtag every 200 follows
				
				
				//navigate to hashtag
				final String keywords = main.getTextField().getText();
				main.getDriver().navigate().to("http://iconosquare.com/viewer.php#/tag/" + keywords + "/");
				
				//wait while new page loads
				main.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				
				List<WebElement> userImage = main.getDriver().findElements(By.cssSelector("a[href*='#/user/']"));

				main.getLblconsoleLabel().setText("Total users found: " + userImage.size());
				main.getLblconsoleLabel().repaint();

				for(int i = 0; i < userImage.size(); i++)
				{
					String rawHTMLLine = userImage.get(i).getAttribute("outerHTML");
					String[] split1 = rawHTMLLine.split("\"");
					String firstSplit = split1[1];
					String[] split2 = firstSplit.split("\"");
					String secondSplit = split2[0];
					//if the list already doesn't contain them && if we havn't followed them yet, 
					if(!foundUsers.contains(secondSplit) && !followedUsers.contains(secondSplit))
					{
						foundUsers.add(secondSplit);
						main.getUsersfoundvar().setText(foundUsers.size() + "");
						main.getUsersfoundvar().repaint();

						main.getLblconsoleLabel().setText("Adding user " + secondSplit + " to found users!");
						main.getLblconsoleLabel().repaint();
					}
				}

				//we found the amount of users requested to follow this session so pause this thread till we require to continue for next session
				if(foundUsers.size() >= howManyUsersToFind)
				{
					main.getLblconsoleLabel().setText("Found all users! Starting follow thread");
					main.getLblconsoleLabel().repaint();
					if(!followThread.isAlive())
					{
						followThread.start();
					} else
					{
						followThread.setFollowersInQue(true);
					}
					findMoreUsers = false;
				} else
				{
					//we havnt found enough users to meet quota so scroll down and add more!
					main.getLblconsoleLabel().setText("Didn't find enough users! (" + foundUsers.size() + "/" + howManyUsersToFind + ") Finding more!");
					main.getLblconsoleLabel().repaint();

					JavascriptExecutor jse = (JavascriptExecutor) main.getDriver();
					jse.executeScript("scroll(0, " + 5000 * foundUsers.size() + ");");

					//now just run this code again to add more users!
					Thread.sleep(5000); //run 5 seconds later
				}

			} catch(InterruptedException iex)
			{
				System.out.println("Exception in clock thread: " + iex.getMessage());
			}
		}
	}

	@SuppressWarnings("deprecation")
    public void kill()
	{
		if(followThread.isAlive())
		{
			followThread.interrupt();
			followThread.stop();
		}
	}
	
	public Main getMain()
	{
		return main;
	}

	public void setMain(Main main)
	{
		this.main = main;
	}

	public String getUserPageUrl()
	{
		return userPageUrl;
	}

	public void setUserPageUrl(String userPageUrl)
	{
		this.userPageUrl = userPageUrl;
	}

	public List<String> getFoundUsers()
	{
		return foundUsers;
	}

	public void setFoundUsers(List<String> foundUsers)
	{
		this.foundUsers = foundUsers;
	}

	public List<String> getFollowedUsers()
	{
		return followedUsers;
	}

	public void setFollowedUsers(List<String> followedUsers)
	{
		this.followedUsers = followedUsers;
	}

	public int getTotalFollowed()
	{
		return totalFollowed;
	}

	public void setTotalFollowed(int totalFollowed)
	{
		this.totalFollowed = totalFollowed;
	}

	public int getHowManyUsersToFind()
	{
		return howManyUsersToFind;
	}

	public void setHowManyUsersToFind(int howManyUsersToFind)
	{
		this.howManyUsersToFind = howManyUsersToFind;
	}

	public boolean isFindMoreUsers()
	{
		return findMoreUsers;
	}

	public void setFindMoreUsers(boolean findMoreUsers)
	{
		this.findMoreUsers = findMoreUsers;
		run();
	}

	public FollowThread getFollowThread()
	{
		return followThread;
	}

	public void setFollowThread(FollowThread followThread)
	{
		this.followThread = followThread;
	}
}
