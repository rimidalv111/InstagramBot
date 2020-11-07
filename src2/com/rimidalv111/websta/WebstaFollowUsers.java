package com.rimidalv111.websta;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rimidalv111.main.Main;

public class WebstaFollowUsers extends Thread
{
	private Main main;
	private WebstaFindUsers wfu;

	public WebstaFollowUsers(Main m, WebstaFindUsers s)
	{
		main = m;
		wfu = s;

	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				String user = (String) wfu.getUsersToFollow().toArray()[0];
				wfu.getUsersToFollow().remove(user);
				System.out.println("loading user " + user);
				main.getDriver().navigate().to("http://logingram.com/index.php/main/user/" + user);
				Thread.sleep(5000); //wait 5 seconds for page to load

				try
				{
					WebElement followButton = main.getDriver().findElement(By.xpath("//*[@id='followUser']/img"));
					if(followButton != null)
					{
						followButton.click();
						main.getLblconsoleLabel().setText("Followed user: " + user);
						main.getLblconsoleLabel().repaint();
					} else
					{
						main.getLblconsoleLabel().setText("Null button so skiping");
						main.getLblconsoleLabel().repaint();
					}
				} catch(Exception io)
				{
					main.getLblconsoleLabel().setText("Null button so skiping");
					main.getLblconsoleLabel().repaint();
				}
				wfu.getFollowedUsers().add(user);
				if(wfu.getUsersToFollow().size() <= 0)
				{
					wfu.runcode();
					this.interrupt();
					this.stop();
				}
				Thread.sleep(27000);
			} catch(Exception io)
			{
				 io.printStackTrace();
			}

		}
	}
}
