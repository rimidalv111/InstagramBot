package rimidalv111.main;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URI;
import java.util.List;

import javax.swing.JOptionPane;

import org.jinstagram.Instagram;
import org.jinstagram.InstagramClient;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.tags.TagInfoData;
import org.jinstagram.entity.tags.TagInfoFeed;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.tags.TagSearchFeed;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.feed.MediaFeedData;

public class InstagramConnectThread extends Thread
{
	private InstagramService instagram_service;
	private Verifier instagram_verifier;
	private Token instagram_token;
	private InstagramClient instagram_connection;
	
	private InstaThreadHandler instaThreadHandler;
	
	public InstagramConnectThread(InstaThreadHandler ith)
	{
		instaThreadHandler = ith;
	}
	
	public void run()
	{
		try
		{

			instagram_service = new InstagramAuthService().apiKey(instaThreadHandler.getAccount().getClientID()).apiSecret(instaThreadHandler.getAccount().getClientSecret()).callback(instaThreadHandler.getAccount().getCallback()).build();
			 
			String authorizationUrl = instagram_service.getAuthorizationUrl() + "&scope=public_content";
			
			System.out.println("** Instagram Authorization ** \n\n");

			System.out.println("Copy & Paste the below Authorization URL in your browser...");
			System.out.println("Authorization URL : " + authorizationUrl);
			System.out.println("Copy verification Code into program.");
			
//			if(Desktop.isDesktopSupported())
//			{
//			  Desktop.getDesktop().browse(new URI(authorizationUrl));
//			}
			
			
			StringSelection stringSelection = new StringSelection(authorizationUrl);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
			
			JOptionPane.showMessageDialog(instaThreadHandler.getInstabot(), "Authorization URL Copied To Clipboard");
			
			
			String verficiation = JOptionPane.showInputDialog("Please Enter Verification Code: ", "Authentication");
			
			instagram_verifier = new Verifier(verficiation);
			
			instagram_token = instagram_service.getAccessToken(instagram_verifier);


			
			instagram_connection = new Instagram(instagram_token);

			//success connection
			
			UserInfo userInfo = instagram_connection.getCurrentUserInfo();
			
			String query = "snow";
			TagInfoFeed searchFeed = instagram_connection.getTagInfo(query);

		
			
			//tags.get(0).getTagName();
			
			//System.out.println("Connected to Account: " + instagram_connection.getCurrentUserInfo().getData().getFirstName());
			
			
		} catch(Exception io)
		{
			io.printStackTrace();
		}

	}
	
}
