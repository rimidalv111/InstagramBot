package rimidalv111.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Instabot extends JFrame
{

	private static Instabot instance;
	
	private JPanel contentPane;
	private JTextField clientID_tf;
	private JTextField clientSecret_tf;
	private JTextField callbackURL_tf;
	private JLabel clientSecret_lb;
	private JLabel clientID_lb;
	private JLabel callbackURL_lbl;
	private JButton connect_btn;
	private JPanel panel;
	private final JPanel control_panel = new JPanel();
	private JPanel follow_panel;
	private JPanel unfollow_panel;
	private JPanel like_panel;
	private JButton follow_start_btn;
	private JButton follow_stop_btn;
	private JButton unfollow_start_btn;
	private JButton unfollow_stop_btn;
	private JButton like_stop_btn;
	private JButton like_start_btn;
	private InstaThreadHandler instaThreads;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Instabot frame = new Instabot();
					instance = frame;
					frame.setVisible(true);
				} catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Instabot()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 312);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setForeground(SystemColor.inactiveCaption);
		panel.setName("");
		panel.setToolTipText("");
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 208, 202);
		contentPane.add(panel);
		
		clientID_lb = new JLabel("Client ID:");
		
		clientID_tf = new JTextField();
		clientID_tf.setColumns(10);
		
		clientSecret_lb = new JLabel("Client Secret:");
		
		clientSecret_tf = new JTextField();
		clientSecret_tf.setColumns(10);
		
		callbackURL_lbl = new JLabel("Callback URL:");
		
		callbackURL_tf = new JTextField();
		callbackURL_tf.setColumns(10);
		
		connect_btn = new JButton("Connect");
		connect_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InstagramAccount ac = new InstagramAccount();
				ac.setAccountName("yogiabears");
				ac.setClientID("3c93b5d21a994787a4c661059fbc6c2f");
				ac.setClientSecret("e03dd87ea5514a9fbadd662cc23011fd");
				ac.setCallback("http://monarchmarketing.com/oauthtest");
				
				instaThreads = new InstaThreadHandler(instance, ac);
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(clientID_lb, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(clientID_tf, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(clientSecret_tf, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(clientSecret_lb, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(connect_btn, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(callbackURL_lbl, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(callbackURL_tf, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(clientID_lb)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(clientID_tf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(clientSecret_lb)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(clientSecret_tf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(callbackURL_lbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(callbackURL_tf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(connect_btn)
					.addContainerGap(126, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		control_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		control_panel.setBackground(SystemColor.inactiveCaptionBorder);
		control_panel.setBounds(228, 11, 446, 202);
		contentPane.add(control_panel);
		
		follow_panel = new JPanel();
		follow_panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlDkShadow));
		follow_panel.setBackground(new Color(250, 250, 210));
		
		unfollow_panel = new JPanel();
		unfollow_panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlDkShadow));
		unfollow_panel.setBackground(new Color(250, 250, 210));
		
		like_panel = new JPanel();
		like_panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlDkShadow));
		like_panel.setBackground(new Color(250, 250, 210));
		GroupLayout gl_control_panel = new GroupLayout(control_panel);
		gl_control_panel.setHorizontalGroup(
			gl_control_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_control_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_control_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(like_panel, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
						.addComponent(unfollow_panel, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
						.addComponent(follow_panel, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_control_panel.setVerticalGroup(
			gl_control_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_control_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(follow_panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(unfollow_panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(like_panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		like_panel.setLayout(null);
		
		like_stop_btn = new JButton("Stop");
		like_stop_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(instaThreads != null)
				{
					instaThreads.stopBot(2);
				}
			}
		});
		like_stop_btn.setBackground(new Color(178, 34, 34));
		like_stop_btn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		like_stop_btn.setBounds(6, 29, 60, 14);
		like_panel.add(like_stop_btn);
		
		like_start_btn = new JButton("Start");
		like_start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(instaThreads != null)
				{
					instaThreads.startBot(2);
				}
			}
		});
		like_start_btn.setBackground(new Color(143, 188, 143));
		like_start_btn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		like_start_btn.setBounds(6, 6, 60, 14);
		like_panel.add(like_start_btn);
		unfollow_panel.setLayout(null);
		
		unfollow_start_btn = new JButton("Start");
		unfollow_start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(instaThreads != null)
				{
					instaThreads.startBot(1);
				}
			}
		});
		unfollow_start_btn.setBackground(new Color(143, 188, 143));
		unfollow_start_btn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		unfollow_start_btn.setBounds(6, 6, 60, 14);
		unfollow_panel.add(unfollow_start_btn);
		
		unfollow_stop_btn = new JButton("Stop");
		unfollow_stop_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(instaThreads != null)
				{
					instaThreads.stopBot(1);
				}
			}
		});
		unfollow_stop_btn.setBackground(new Color(178, 34, 34));
		unfollow_stop_btn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		unfollow_stop_btn.setBounds(6, 29, 60, 14);
		unfollow_panel.add(unfollow_stop_btn);
		
		follow_start_btn = new JButton("Start");
		follow_start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(instaThreads != null)
				{
					instaThreads.startBot(0);
				}
			}
		});
		follow_start_btn.setBackground(new Color(143, 188, 143));
		follow_start_btn.setBounds(6, 6, 60, 14);
		follow_start_btn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		
		follow_stop_btn = new JButton("Stop");
		follow_stop_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(instaThreads != null)
				{
					instaThreads.stopBot(0);
				}
			}
		});
		follow_stop_btn.setBackground(new Color(178, 34, 34));
		follow_stop_btn.setBounds(6, 29, 60, 14);
		follow_stop_btn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		follow_panel.setLayout(null);
		follow_panel.add(follow_start_btn);
		follow_panel.add(follow_stop_btn);
		control_panel.setLayout(gl_control_panel);
	}
}
