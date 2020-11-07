package com.rimidalv111.leechusers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeechUsers extends Thread
{
	private List<String> users = new ArrayList<String>();
	private boolean keepLeeching = true;
	private ArrayList<String> leechTags = new ArrayList<String>();;

	public LeechUsers()
	{
		leechTags.add("drink");
		leechTags.add("drinking");
		leechTags.add("party");
		leechTags.add("partying");
		leechTags.add("beerpong");
		leechTags.add("drinkinggames");
		leechTags.add("alcohol");
		leechTags.add("smashed");

		leechTags.add("love");
		leechTags.add("club");
		leechTags.add("selfie");
		leechTags.add("guilford");
		leechTags.add("instadaily");
		leechTags.add("lastnight");
		leechTags.add("friends");
		leechTags.add("drinking");
		leechTags.add("me");
		leechTags.add("alcohol");
		leechTags.add("instaself");
		leechTags.add("self");
		leechTags.add("casino");
		leechTags.add("clubbing");
		leechTags.add("instagood");
		leechTags.add("amazing");
		leechTags.add("bae");
		leechTags.add("night");
		leechTags.add("instame");
		leechTags.add("happy");
		leechTags.add("meat");
		leechTags.add("cocktail");
		leechTags.add("food");
		leechTags.add("fathersday");
		leechTags.add("froctail");
		leechTags.add("braai");
		leechTags.add("frozen");
		leechTags.add("berry");
		leechTags.add("daiquiri");
		leechTags.add("art");

		leechTags.add("instalike");
		leechTags.add("jimbeam");
		leechTags.add("whiskey");
		leechTags.add("black");
		leechTags.add("jackdaniels");
		leechTags.add("bourbon");
		leechTags.add("cute");
		leechTags.add("drunk");
		leechTags.add("instaphoto");
		leechTags.add("follow");
		leechTags.add("girl");
		leechTags.add("boy");
		leechTags.add("nightout");
		leechTags.add("nighttime");
		leechTags.add("tagsforlikes");
		leechTags.add("smile");
		leechTags.add("followme");
		leechTags.add("photooftheday");
		leechTags.add("followforfollow");
		leechTags.add("birthday");
		leechTags.add("camping");
		leechTags.add("folie");
		leechTags.add("yabon");
		leechTags.add("bouteillesurbouteille");
		leechTags.add("ladies");
		leechTags.add("suckit");
		leechTags.add("yeahbaby");
		leechTags.add("bringit");
		leechTags.add("pub");
		leechTags.add("yeahbaybee");

		leechTags.add("booze");
		leechTags.add("favoritedrinkever");
		leechTags.add("bestie");
		leechTags.add("scottish");
		leechTags.add("bar");
		leechTags.add("peaceman");
		leechTags.add("best");
		leechTags.add("laavit");
		leechTags.add("town");
		leechTags.add("lovers");
		leechTags.add("hellyeah");
		leechTags.add("partyhard");
		leechTags.add("peace");
		leechTags.add("scotland");
		leechTags.add("waiting");
		leechTags.add("party");
		leechTags.add("out");
		leechTags.add("pal");
		leechTags.add("friend");
		leechTags.add("drinks");
		leechTags.add("foodgasm");
		leechTags.add("foodporn");
		leechTags.add("marisaslm");
		leechTags.add("drink");
		leechTags.add("beerporn");
		leechTags.add("mexican");
		leechTags.add("tacos");
		leechTags.add("foodgram");
		leechTags.add("foodie");
		leechTags.add("appetizer");

		leechTags.add("special");
		leechTags.add("foodstagram");
		leechTags.add("deals");
		leechTags.add("like4like");
		leechTags.add("restaurant");
		leechTags.add("drinkup");
		leechTags.add("beer");
		leechTags.add("instafood");
		leechTags.add("sunday");
		leechTags.add("instapics");
		leechTags.add("likeforlike");
		leechTags.add("marisas");
		leechTags.add("happyhour");
		leechTags.add("films");
		leechTags.add("brillday");
		leechTags.add("fiancé");
		leechTags.add("takeaway");
		leechTags.add("brunette");
		leechTags.add("couple");
		leechTags.add("makeup");
		leechTags.add("honey");
		leechTags.add("czechboy");
		leechTags.add("lips");
		leechTags.add("loveforever");
		leechTags.add("kiss");
		leechTags.add("wedding");
		leechTags.add("lovekiss");
		leechTags.add("dress");
		leechTags.add("celebrate");
		leechTags.add("formal");

		leechTags.add("sun");
		leechTags.add("celebration");
		leechTags.add("whitedress");
		leechTags.add("mode");
		leechTags.add("suit");
		leechTags.add("ilove");
		leechTags.add("boyfriend");
		leechTags.add("czechgirl");
		leechTags.add("instaboy");
		leechTags.add("gay");
		leechTags.add("gayboy");
		leechTags.add("instapic");
		leechTags.add("gayguys");
		leechTags.add("cain");
		leechTags.add("gaypic");
		leechTags.add("cold");
		leechTags.add("chats");
		leechTags.add("life");
		leechTags.add("people");
		leechTags.add("blessed");
		leechTags.add("content");
		leechTags.add("nightin");
		leechTags.add("dancing");
		leechTags.add("fun");
		leechTags.add("music");
		leechTags.add("mixology");
		leechTags.add("sundayfunday");
		leechTags.add("mtl");
		leechTags.add("prosecco");
		leechTags.add("montreal");
		leechTags.add("sun");
		leechTags.add("celebration");
		leechTags.add("whitedress");
		leechTags.add("mode");
		leechTags.add("suit");
		leechTags.add("ilove");
		leechTags.add("boyfriend");
		leechTags.add("czechgirl");
		leechTags.add("instaboy");
		leechTags.add("gay");
		leechTags.add("gayboy");
		leechTags.add("instapic");
		leechTags.add("gayguys");
		leechTags.add("cain");
		leechTags.add("gaypic");
		leechTags.add("cold");
		leechTags.add("chats");
		leechTags.add("life");
		leechTags.add("people");
		leechTags.add("blessed");
		leechTags.add("content");
		leechTags.add("nightin");
		leechTags.add("dancing");
		leechTags.add("fun");
		leechTags.add("music");
		leechTags.add("mixology");
		leechTags.add("sundayfunday");
		leechTags.add("mtl");
		leechTags.add("prosecco");
		leechTags.add("montreal");

		leechTags.add("vodka");
		leechTags.add("fashion");
		leechTags.add("freedomjuice");
		leechTags.add("murica");
		leechTags.add("highquality");
		leechTags.add("tasteslikeregret");
		leechTags.add("nofilter");
		leechTags.add("allcapslyrics");
		leechTags.add("grunge");
		leechTags.add("concert");
		leechTags.add("lyrics");
		leechTags.add("indie");
		leechTags.add("f4f");
		leechTags.add("screampoetry");
		leechTags.add("tumblrquotes");
		leechTags.add("likes");
		leechTags.add("tumblr");
		leechTags.add("pale");
		leechTags.add("pills");
		leechTags.add("grungequotes");
		leechTags.add("grungeblog");
		leechTags.add("dark");
		leechTags.add("screamquotes");
		leechTags.add("depressed");
		leechTags.add("vintage");
		leechTags.add("allcaps");
		leechTags.add("allcapspoetry");
		leechTags.add("cigarettes");
		leechTags.add("hipster");
		leechTags.add("s4s");

		leechTags.add("tongue");
		leechTags.add("become");
		leechTags.add("the");
		leechTags.add("with");
		leechTags.add("fearless");
		leechTags.add("hardstyle");
		leechTags.add("defqon1");
		leechTags.add("stampen");
		leechTags.add("crazy");
		leechTags.add("sorfesztival");
		leechTags.add("2015");
		leechTags.add("chill");
		leechTags.add("lace");
		leechTags.add("wineglass");
		leechTags.add("hand");
		leechTags.add("glass");
		leechTags.add("polishgirl");
		leechTags.add("cuteevening");
		leechTags.add("smart");

	}

	@Override
	public void run()
	{
		while (keepLeeching)
		{
			try
			{
				String leechingTag = (String) leechTags.toArray()[0];
				leechEnjoyGram(leechingTag);
				leechWebsta(leechingTag);

				leechTags.remove(leechingTag);

				if(users.size() >= 200)
				{
					try
					{
						save();
					} catch(IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(users.size() + " users leeched");
				Thread.sleep(10000);
			} catch(InterruptedException e)
			{
			}
		}
	}

	public void save() throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter(new FileOutputStream("C:/Users/Vladimir/Desktop/leechedUsers.txt"));
		for(String str : users)
		{
			pw.println(str);
		}
		pw.close();
		System.out.println("Saved users");
	}

	public void leechEnjoyGram(String tag)
	{
		try
		{
			URL myURL = new URL("http://www.enjoygram.com/tag/" + tag);

			HttpURLConnection httpcon = (HttpURLConnection) myURL.openConnection();
			httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");

			BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				if(inputLine.contains("class=\"username\""))
				{
					String[] split1 = inputLine.split("\">");
					String name = split1[1].replace("</a>", "");
					users.add(name);
				}
			}

		} catch(Exception io)
		{
			io.printStackTrace();
		}
	}

	public void leechWebsta(String tag)
	{
		try
		{
			URL myURL = new URL("http://websta.me/tag/" + tag);

			HttpURLConnection httpcon = (HttpURLConnection) myURL.openConnection();
			httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");

			BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));

			List<String> al = new ArrayList<>();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				if(inputLine.contains("class=\"username\""))
				{
					String[] split1 = inputLine.split("\">");
					String name = split1[1].replace("</a>", "");

					//remove dupes
					// add elements to al, including duplicates
					al.add(name);
					Set<String> hs = new HashSet<>();
					hs.addAll(al);
					al.clear();
					al.addAll(hs);

				}
			}
			for(String d : al)
			{
				users.add(d);
			}

		} catch(Exception io)
		{
			io.printStackTrace();
		}
	}

	public List<String> getUsers()
	{
		return users;
	}

	public void setUsers(List<String> users)
	{
		this.users = users;
	}
}
