package com.rimidalv111.websta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.rimidalv111.main.Main;

public class WebstaFindUsers extends Thread
{
	private Main main;
	private String keyword;
	private int howmanyuserstofind;

	private List<String> usersToFollow = new ArrayList<String>();
	private List<String> followedUsers = new ArrayList<String>();

	public WebstaFindUsers(Main m, String k, int hutf)
	{
		main = m;
		keyword = k;
		howmanyuserstofind = hutf;
	}

	private boolean findUsers = true;

	@Override
	public void run()
	{
		main.getDriver().navigate().to("http://logingram.com/index.php/main/tag/" + keyword);
		while (findUsers)
		{
			try
			{

				main.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				main.getLblconsoleLabel().setText("Finding users");
				main.getLblconsoleLabel().repaint();
				
				List<WebElement> foundUsers = main.getDriver().findElements(By.xpath("//a[starts-with(@href, 'http://logingram.com/index.php/main/user/')]"));
				main.getLblconsoleLabel().setText("Total users found: " + foundUsers.size());
				main.getLblconsoleLabel().repaint();
				
				for(WebElement we : foundUsers)
				{
					if(!usersToFollow.contains(we.getText()) && !usersToFollow.contains(we.getText()))
					{
						usersToFollow.add(we.getText());
						main.getLblconsoleLabel().setText("Added user: " + we.getText() + " to follow list!");
						main.getLblconsoleLabel().repaint();
						((JavascriptExecutor) main.getDriver()).executeScript("arguments[0].scrollIntoView(true);", we);
					}
				}
				if(usersToFollow.size() > howmanyuserstofind)
				{
					main.getLblconsoleLabel().setText("Starting follow thread");
					main.getLblconsoleLabel().repaint();
					
					WebstaFollowUsers sd = new WebstaFollowUsers(main, this);
					sd.start();
					findUsers = false;
				} else
				{
					WebElement loadMore = main.getDriver().findElement(By.xpath("//*[@id='middlePage']/div[3]/div/form/input[2]"));
					loadMore.click();
					
					main.getLblconsoleLabel().setText("Finding more users! " + usersToFollow.size() + "/" + howmanyuserstofind);
					main.getLblconsoleLabel().repaint();
					
				}
				Thread.sleep(5000);

			} catch(Exception io)
			{
				io.printStackTrace();
			}
		}
	}

	public void runcode()
	{

		findUsers = true;
		run();
	}

	public Main getMain()
	{
		return main;
	}

	public void setMain(Main main)
	{
		this.main = main;
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}

	public List<String> getUsersToFollow()
	{
		return usersToFollow;
	}

	public void setUsersToFollow(List<String> usersToFollow)
	{
		this.usersToFollow = usersToFollow;
	}

	public List<String> getFollowedUsers()
	{
		return followedUsers;
	}

	public void setFollowedUsers(List<String> followedUsers)
	{
		this.followedUsers = followedUsers;
	}
}
