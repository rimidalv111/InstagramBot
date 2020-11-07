package com.rimidalv111.websta;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.rimidalv111.main.Main;

public class WebstaUnfollow extends Thread
{
	private Main main;

	public WebstaUnfollow(Main m)
	{
		main = m;
	}

	private List<String> usersToUnfollow = new ArrayList<String>();

	private int unfollowed = 0;
	
	@Override
	public void run()
	{
		
		while (true)
		{
			try
			{
				if(unfollowed > 100)
				{
					main.getLblconsoleLabel().setText("Restarting unfollower");
					main.getLblconsoleLabel().repaint();
					main.getDriver().close();
					Thread.sleep(5000);
					main.getWebsta().runCode("", 20, true);
					unfollowed = 0;
					this.interrupt();
					this.stop();
					return;
				}
				if(usersToUnfollow.size() <= 0)
				{
					main.getDriver().navigate().to("http://logingram.com/index.php/main/followings");
					List<WebElement> unfollowUsers = main.getDriver().findElements(By.className("usersListInfo"));
					main.getLblconsoleLabel().setText("Found " + unfollowUsers.size() + " users to unfollow!");
					main.getLblconsoleLabel().repaint();
					for(WebElement we : unfollowUsers)
					{
						if(!we.getText().equalsIgnoreCase("") && !we.getText().equalsIgnoreCase("drinkcrafting"))
						{
							usersToUnfollow.add(we.getText());
							main.getLblconsoleLabel().setText("Added " + we.getText() + " user!");
							main.getLblconsoleLabel().repaint();
						}
					}
					Thread.sleep(10000);
				} else
				{
					String user = (String) usersToUnfollow.toArray()[0];
					usersToUnfollow.remove(user);

					main.getDriver().navigate().to("http://logingram.com/index.php/main/user/" + user);
			
					// times out after 5 seconds
					WebDriverWait wait = new WebDriverWait(main.getDriver(), 15);

					// while the following loop runs, the DOM changes - 
					// page is refreshed, or element is removed and re-added
					wait.until(presenceOfElementLocated(By.xpath("//*[@id='unfollowUser']")));        

					// now we're good - let's click the element
					Thread.sleep(5000);
					WebElement unfollow = main.getDriver().findElement(By.xpath("//*[@id='unfollowUser']"));
					((JavascriptExecutor) main.getDriver()).executeScript("arguments[0].click();", unfollow);
					
					
//					WebDriverWait wait = new WebDriverWait(main.getDriver(), 10);
//					WebElement unfollowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='unfollowUser']")));
//					unfollowButton.click();
					unfollowed++;
					main.getLblconsoleLabel().setText("Unfollowed " + user + "  " + unfollowed);
					main.getLblconsoleLabel().repaint();
					Thread.sleep(25000);
				}
			} catch(Exception io)
			{
				io.printStackTrace();
			}

		}
	}

	private static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator)
	{
		return new Function<WebDriver, WebElement>()
		{
			@Override
			public WebElement apply(WebDriver driver)
			{
				return driver.findElement(locator);
			}
		};
	}
}
