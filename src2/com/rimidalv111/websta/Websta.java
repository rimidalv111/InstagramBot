package com.rimidalv111.websta;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.rimidalv111.main.Main;

public class Websta
{
	private Main main;

	public Websta(Main m)
	{
		main = m;
	}

	public void runCode(String tag, int howmanyuserstofind, boolean unfollow)
	{
		//System.setProperty("webdriver.chrome.driver", (new File(".")).getAbsolutePath() + "/chromedriver.exe");

		//main.setDriver(new ChromeDriver());
		  
		main.setDriver(new FirefoxDriver());

		//connect to websta website via instagram account validation
		main.getDriver().get("https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3D67be0e2777b94bc9bdacf4e2680d77d4%26response_type%3Dcode%26redirect_uri%3Dhttp%3A//logingram.com/index.php%26scope%3Dlikes%2Bcomments%2Brelationships");

		//enter username and pass
		WebElement username = main.getDriver().findElement(By.xpath("//*[@id='id_username']"));
		username.sendKeys("drinkcrafting");
		WebElement password = main.getDriver().findElement(By.xpath("//*[@id='id_password']"));
		password.sendKeys("drinkcraft3");

		//hit 'log in' button
		WebElement loginbutton = main.getDriver().findElement(By.xpath("//*[@id='login-form']/p[3]/input"));
		loginbutton.click();

		//wait for websta to load
		//main.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if(unfollow)
		{
			new WebstaUnfollow(main).start();
		} else
		{
			webstafindusers = new WebstaFindUsers(main, tag, howmanyuserstofind);
			webstafindusers.start();
		}
	}

	private WebstaFindUsers webstafindusers;
	
	public Main getMain()
	{
		return main;
	}

	public void setMain(Main main)
	{
		this.main = main;
	}

}
