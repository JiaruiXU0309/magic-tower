import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class WebService {
	boolean loginValidation(String un, String pw) {
		try {
			String encodedUsername = URLEncoder.encode(un, "UTF-8");
			String encodedPassword = URLEncoder.encode(pw, "UTF-8");
			String url = "http://localhost:8080/validate?username=" + encodedUsername + "&password=" + encodedPassword;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("Response Code: " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line.trim());
			}
			String result = response.toString();
			System.out.println("Response Body: " + result);
			boolean loginSuccess = Boolean.parseBoolean(result);
			System.out.println("Login Success: " + loginSuccess);
			if(loginSuccess) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	void fetchPlayers(int wid) {
		try {
			String worldId = URLEncoder.encode(Integer.toString(wid), "UTF-8");
			String url = "http://localhost:8080/players?wid=" + worldId;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("Response Code: " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line.trim());
			}
			String result = response.toString();
			System.out.println("Response Body: " + result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void fetchWorlds(int pid) {
		try {
			String playerId = URLEncoder.encode(Integer.toString(pid), "UTF-8");
			String url = "http://localhost:8080/worlds?pid=" + playerId;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("Response Code: " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line.trim());
			}
			String result = response.toString();
			System.out.println("Response Body: " + result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
