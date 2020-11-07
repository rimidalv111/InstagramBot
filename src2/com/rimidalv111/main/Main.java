package com.rimidalv111.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.rimidalv111.leechusers.LeechUsers;
import com.rimidalv111.unfollow.UnFollowThread;
import com.rimidalv111.websta.Websta;

import javax.swing.JRadioButton;

public class Main
{
	private JFrame frame;
	private WebDriver driver;
	private JTextField textField;

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception io)
		{
		}
		EventQueue.invokeLater(new Runnable()
		{

			//@Override
			public void run()
			{
				try
				{
					Main window = new Main();
					window.runGui();
				} catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel lblHashtag;
	private JLabel label;
	private JTextPane textPane;
	private JButton btnSearchHashtag;
	private JButton btnStartThread;
	private JButton btnStopThread;
	private JLabel lblConsole;
	private JLabel lblconsoleLabel;

	private Websta websta;
			
	/**
     * @return the websta
     */
    public Websta getWebsta()
    {
    	return websta;
    }

	/**
     * @param websta the websta to set
     */
    public void setWebsta(Websta websta)
    {
    	this.websta = websta;
    }

	public void runGui()
	{
		frame = new JFrame();
		frame.setTitle("Instagram Follower");
		frame.setBounds(100, 100, 731, 228);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(108, 11, 165, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		lblHashtag = new JLabel("Hashtag Search");
		lblHashtag.setBounds(10, 14, 93, 14);
		frame.getContentPane().add(lblHashtag);

		label = new JLabel("#");
		label.setBounds(98, 14, 46, 14);
		frame.getContentPane().add(label);

		btnSearchHashtag = new JButton("Search Hashtag");
		btnSearchHashtag.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!textField.getText().equalsIgnoreCase(""))
				{
					hashtagResults(textField.getText());
				}
			}
		});
		btnSearchHashtag.setBounds(283, 10, 161, 23);
		frame.getContentPane().add(btnSearchHashtag);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(493, 11, 222, 178);
		frame.getContentPane().add(scrollPane);

		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setText("click search to see if hashtag is valid..");

		websta = new Websta(this);
		
		btnStartThread = new JButton("Start Thread");
		btnStartThread.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!textField.getText().equalsIgnoreCase(""))
				{
					getLblconsoleLabel().setText("Starting thread!");
					getLblconsoleLabel().repaint();
					websta.runCode(textField.getText(), Integer.parseInt(leechUsersTotal.getText()), false);
					
					
					//runCode();
				}
			}
		});
		btnStartThread.setBounds(10, 166, 102, 23);
		frame.getContentPane().add(btnStartThread);

		btnStopThread = new JButton("Stop Thread");
		btnStopThread.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getLblconsoleLabel().setText("Stopping thread..");
				getLblconsoleLabel().repaint();

				if(findUsers.isAlive())
				{
					findUsers.kill();
					findUsers.interrupt();
					findUsers.stop();
				}
				if(unfollowThread.isAlive())
				{
					unfollowThread.interrupt();
					unfollowThread.stop();
				}
				driver.close();
			}
		});
		btnStopThread.setBounds(164, 166, 102, 23);
		frame.getContentPane().add(btnStopThread);

		lblConsole = new JLabel("Console:");
		lblConsole.setBounds(10, 131, 46, 14);
		frame.getContentPane().add(lblConsole);

		lblconsoleLabel = new JLabel("Waiting...");
		lblconsoleLabel.setBounds(57, 131, 269, 14);
		frame.getContentPane().add(lblconsoleLabel);

		JLabel lblUsersFound = new JLabel("Users Found:");
		lblUsersFound.setBounds(10, 73, 71, 14);
		frame.getContentPane().add(lblUsersFound);

		JLabel lblUsersFollowed = new JLabel("Users Followed:");
		lblUsersFollowed.setBounds(164, 73, 76, 14);
		frame.getContentPane().add(lblUsersFollowed);

		JLabel lblleechusertotal = new JLabel("Leech Users Total");
		lblleechusertotal.setBounds(10, 39, 93, 14);
		frame.getContentPane().add(lblleechusertotal);

		leechUsersTotal = new JTextField();
		leechUsersTotal.setText("20");
		leechUsersTotal.setBounds(108, 36, 46, 20);
		frame.getContentPane().add(leechUsersTotal);
		leechUsersTotal.setColumns(10);

		usersfoundvar = new JLabel("");
		usersfoundvar.setBounds(83, 73, 46, 14);
		frame.getContentPane().add(usersfoundvar);

		usersfollowedvar = new JLabel("");
		usersfollowedvar.setBounds(250, 73, 46, 14);
		frame.getContentPane().add(usersfollowedvar);

		JButton btnUnfollowCurrentUsers = new JButton("Unfollow Current Users");
		btnUnfollowCurrentUsers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				websta.runCode(textField.getText(), Integer.parseInt(leechUsersTotal.getText()), true);
				
//				unfollowUsers = true;
//				runCode();
			}
		});
		btnUnfollowCurrentUsers.setBounds(283, 69, 161, 23);
		frame.getContentPane().add(btnUnfollowCurrentUsers);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("WebSta.me\r\n");
		rdbtnNewRadioButton.setBounds(177, 38, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);

		JRadioButton rdbtnLogingram = new JRadioButton("LoginGram");
		rdbtnLogingram.setBounds(310, 39, 109, 23);
		frame.getContentPane().add(rdbtnLogingram);

		JButton btnNewButton = new JButton("Leech Users To Follow\r\n");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LeechUsers lu = new LeechUsers();
				lu.start();
			}
		});
		btnNewButton.setBounds(283, 97, 161, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}

	private JLabel usersfoundvar;
	private JLabel usersfollowedvar;

	public List<String> hashtagResults(String results)
	{

		try
		{
			URL myURL = new URL("http://www.websta.me/search/" + results);
			//URLConnection myURLConnection = myURL.openConnection();

			HttpURLConnection httpcon = (HttpURLConnection) myURL.openConnection();
			httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");

			BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));

			String hashtagName = "";

			List<String> keyWordSearchResult = new ArrayList<String>();

			textPane.setText("Results");

			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				if(inputLine.contains("<div class") && inputLine.contains("tagname") && inputLine.contains("/tag/"))
				{
					String[] arrowSplit = inputLine.split(">");
					String splitName = arrowSplit[2];
					String[] split2 = splitName.split("<");
					String name = split2[0];
					if(hashtagName.equalsIgnoreCase(""))
					{
						hashtagName = name;
					}
				}
				if(inputLine.contains("<div class=") && inputLine.contains("count_num text-right") && inputLine.contains("><strong>"))
				{
					String[] arrowSplit = inputLine.split(">");
					String splitName = arrowSplit[2];
					String[] split2 = splitName.split("<");
					String name = split2[0];
					if(!hashtagName.equalsIgnoreCase(""))
					{
						hashtagName += " -- " + name;
						keyWordSearchResult.add(hashtagName);
						textPane.setText(textPane.getText() + "\n" + hashtagName);
						hashtagName = "";
					}
				}
			}
			in.close();

			//System.out.println(myURLConnection.getContent().toString());
		} catch(MalformedURLException e)
		{
			e.printStackTrace();
			// new URL() failed
			// ...
		} catch(IOException e)
		{
			e.printStackTrace();
			// openConnection() failed
			// ...
		}

		return null;
	}

	private boolean unfollowUsers = false;

	private JTextField leechUsersTotal;
	private FindUsersThread findUsers;

	private UnFollowThread unfollowThread;

	public void runCode()
	{
		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface, 
		// not the implementation.
	//	driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", (new File(".")).getAbsolutePath() + "/chromedriver.exe");

		driver = new ChromeDriver();
		
		//connect to iconsquare website via instagram account validation
		driver.get("https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships");

		//enter username and pass
		WebElement username = driver.findElement(By.xpath("//*[@id='id_username']"));
		username.sendKeys("drinkcrafting");
		WebElement password = driver.findElement(By.xpath("//*[@id='id_password']"));
		password.sendKeys("drinkcraft3");

		//hit 'log in' button
		WebElement loginbutton = driver.findElement(By.xpath("//*[@id='login-form']/p[3]/input"));
		loginbutton.click();

		//wait for iconsquare to load
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		if(unfollowUsers)
		{
			unfollowThread = new UnFollowThread(this);
			unfollowThread.start();
		} else
		{
			findUsers = new FindUsersThread(this);
			findUsers.setHowManyUsersToFind(Integer.parseInt(leechUsersTotal.getText()));
			findUsers.start();
		}
	}

	public boolean isUnfollowUsers()
	{
		return unfollowUsers;
	}

	public void setUnfollowUsers(boolean unfollowUsers)
	{
		this.unfollowUsers = unfollowUsers;
	}

	public FindUsersThread getFindUsers()
	{
		return findUsers;
	}

	public void setFindUsers(FindUsersThread findUsers)
	{
		this.findUsers = findUsers;
	}

	public UnFollowThread getUnfollowThread()
	{
		return unfollowThread;
	}

	public void setUnfollowThread(UnFollowThread unfollowThread)
	{
		this.unfollowThread = unfollowThread;
	}

	public void closeAllRunningThreadsAndRestart()
	{
		getLblconsoleLabel().setText("Stopping thread and restarting thread");
		getLblconsoleLabel().repaint();

		if(findUsers.isAlive())
		{
			findUsers.kill();
			findUsers.interrupt();
			findUsers.stop();
		}
		if(unfollowThread.isAlive())
		{
			unfollowThread.interrupt();
			unfollowThread.stop();
		}
		driver.close();
		runCode();
	}

	public JLabel getLblconsoleLabel()
	{
		return lblconsoleLabel;
	}

	public void setLblconsoleLabel(JLabel lblconsoleLabel)
	{
		this.lblconsoleLabel = lblconsoleLabel;
	}

	public WebDriver getDriver()
	{
		return driver;
	}

	public void setDriver(WebDriver driver)
	{
		this.driver = driver;
	}

	public JFrame getFrame()
	{
		return frame;
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}

	public JTextField getTextField()
	{
		return textField;
	}

	public void setTextField(JTextField textField)
	{
		this.textField = textField;
	}

	public JLabel getLblHashtag()
	{
		return lblHashtag;
	}

	public void setLblHashtag(JLabel lblHashtag)
	{
		this.lblHashtag = lblHashtag;
	}

	public JLabel getLabel()
	{
		return label;
	}

	public void setLabel(JLabel label)
	{
		this.label = label;
	}

	public JTextPane getTextPane()
	{
		return textPane;
	}

	public void setTextPane(JTextPane textPane)
	{
		this.textPane = textPane;
	}

	public JButton getBtnSearchHashtag()
	{
		return btnSearchHashtag;
	}

	public void setBtnSearchHashtag(JButton btnSearchHashtag)
	{
		this.btnSearchHashtag = btnSearchHashtag;
	}

	public JButton getBtnStartThread()
	{
		return btnStartThread;
	}

	public void setBtnStartThread(JButton btnStartThread)
	{
		this.btnStartThread = btnStartThread;
	}

	public JButton getBtnStopThread()
	{
		return btnStopThread;
	}

	public void setBtnStopThread(JButton btnStopThread)
	{
		this.btnStopThread = btnStopThread;
	}

	public JLabel getLblConsole()
	{
		return lblConsole;
	}

	public void setLblConsole(JLabel lblConsole)
	{
		this.lblConsole = lblConsole;
	}

	public JTextField getTextField_1()
	{
		return leechUsersTotal;
	}

	public void setTextField_1(JTextField textField_1)
	{
		this.leechUsersTotal = textField_1;
	}

	public JLabel getUsersfoundvar()
	{
		return usersfoundvar;
	}

	public void setUsersfoundvar(JLabel usersfoundvar)
	{
		this.usersfoundvar = usersfoundvar;
	}

	public JLabel getUsersfollowedvar()
	{
		return usersfollowedvar;
	}

	public void setUsersfollowedvar(JLabel usersfollowedvar)
	{
		this.usersfollowedvar = usersfollowedvar;
	}

	public JTextField getLeechUsersTotal()
	{
		return leechUsersTotal;
	}

	public void setLeechUsersTotal(JTextField leechUsersTotal)
	{
		this.leechUsersTotal = leechUsersTotal;
	}
}
