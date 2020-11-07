package com.rimidalv111.unfollow;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rimidalv111.main.Main;

public class UnFollowThread extends Thread
{
	private Main main;
	private Integer unfollowed = 0;
	
	public UnFollowThread(Main u)
	{
		main = u;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				if(unfollowed >= 3000)
				{
					main.setUnfollowUsers(false);
					main.closeAllRunningThreadsAndRestart();
				}
				//navigate to people I am following
				main.getDriver().navigate().to("http://iconosquare.com/viewer.php#/myFollowings/");

				//wait while new page loads
				main.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

				try
				{
					//find first unfollow button (cuz they scroll up after unfollow anyways)
					WebElement unFollowFirstUserButton = main.getDriver().findElement(By.xpath("//img[@src='http://static.iconosquare.com/images/unfollow.png']"));

					if(unFollowFirstUserButton != null)
					{
						//unfollow the user, then restart unfollow code
						unFollowFirstUserButton.click();
						unfollowed++;
					} else
					{
						//find more button to load more users i am following
						WebElement moreButton = main.getDriver().findElement(By.className("more"));
						if(moreButton != null)
						{
							Random ran = new Random();
							int randomInt = ran.nextInt(5);
							if(randomInt == 5)
							{
								main.getDriver().navigate().refresh();
								System.out.println("Just refreshing page to keep connection alive");
							} else
							{
								moreButton.click();
								System.out.println("Clicked more button!");
							}
						} else
						{
							main.getDriver().navigate().refresh();
							System.out.println("Couldn't find the more button, just refreshing page");
						}
					}
				} catch(Exception io)
				{
					//could'nt locate element
					main.getDriver().navigate().refresh();
					System.out.println("Couldn't locate element, just refreshing page");
				}

				Thread.sleep(27000);
			} catch(InterruptedException iex)
			{
				System.out.println("Exception in clock thread: " + iex.getMessage());
			}
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
}
