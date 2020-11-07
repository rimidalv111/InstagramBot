package com.rimidalv111.main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FollowThread extends Thread
{
	private FindUsersThread fut;
	private boolean followersInQue;

	public FollowThread(FindUsersThread u)
	{
		fut = u;
		followersInQue = true;
	}

	@Override
	public void run()
	{
		while (followersInQue)
		{
			try
			{
				if(fut.getFoundUsers().size() == 0)
				{
					fut.getMain().getLblconsoleLabel().setText("Looks like we need new uses to follow!");
					fut.getMain().getLblconsoleLabel().repaint();
					followersInQue = false;
					fut.setFindMoreUsers(true);
					Thread.sleep(30000);
					return;
				}
				String userToFollow = (String) fut.getFoundUsers().toArray()[0];

				fut.getMain().getLblconsoleLabel().setText("Following user " + userToFollow);
				fut.getMain().getLblconsoleLabel().repaint();

				//navigate to user profile
				fut.getMain().getDriver().navigate().to(fut.getUserPageUrl() + userToFollow);
				//wait 10 seconds while new page loads (driver wait is not working)
				Thread.sleep(5000);
				//fut.getMain().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				//try and find follow button
				try
				{
					WebElement followButton = fut.getMain().getDriver().findElement(By.xpath("//*[@id='userProfilLarge']/div[2]/div/a"));
					followButton.click();
					fut.getFollowedUsers().add(userToFollow);
					fut.getFoundUsers().remove(userToFollow);

					fut.getMain().getLblconsoleLabel().setText("Sucessfuly followed user: " + userToFollow);
					fut.getMain().getLblconsoleLabel().repaint();

					fut.getMain().getUsersfollowedvar().setText(fut.getFollowedUsers().size() + "");
					fut.getMain().getUsersfollowedvar().repaint();
				} catch(Exception io)
				{
					fut.getMain().getLblconsoleLabel().setText("You are already following " + userToFollow + "... Moving forward..");
					fut.getMain().getLblconsoleLabel().repaint();

					fut.getFollowedUsers().add(userToFollow);
					fut.getFoundUsers().remove(userToFollow);
				}

				Thread.sleep(27000);
			} catch(InterruptedException iex)
			{
				System.out.println("Exception in clock thread: " + iex.getMessage());
			}
		}
	}

	public FindUsersThread getFut()
	{
		return fut;
	}

	public void setFut(FindUsersThread fut)
	{
		this.fut = fut;
	}

	public boolean isFollowersInQue()
	{
		return followersInQue;
	}

	public void setFollowersInQue(boolean followersInQue)
	{
		this.followersInQue = followersInQue;
		run();
	}
}
