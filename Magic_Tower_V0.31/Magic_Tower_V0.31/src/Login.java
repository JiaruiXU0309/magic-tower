import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class Login {
	int validated = 0;
	Login() throws ClassNotFoundException, SQLException, InterruptedException {
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setBounds(300, 300, 300, 300);
		JTextField u = new JTextField();
		JTextField p = new JTextField();
		JLabel l1 = new JLabel("Username");
		JLabel l2 = new JLabel("Password");
		JButton b = new JButton("Login");
		u.setBounds(100, 80, 100, 20);
		p.setBounds(100, 150, 100, 20);
		l1.setBounds(30, 80, 100, 20);
		l2.setBounds(30, 150, 100, 20);
		b.setBounds(110, 200, 80, 50);
		f.add(u);
		f.add(p);
		f.add(l1);
		f.add(l2);
		f.add(b);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WebService webService=new WebService();
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (webService.loginValidation(u.getText(), p.getText())) {
					validated = 1;
				}
			}
		});
		while (true) {
			Thread.sleep(100);
			if (validated == 1) {
				f.dispose();
				return;
			}
		}
	}

	
}
