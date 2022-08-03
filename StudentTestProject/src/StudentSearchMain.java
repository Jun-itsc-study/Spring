import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentSearchMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색종류 입력 : 학번(sno)||이름(name)|학과(major) \n>> ");
		String kind = sc.nextLine();
		System.out.print("검색어를 입력 : \n>> ");
		String search = sc.nextLine();
		String temp = search;
		try {
			search = URLEncoder.encode(search,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String apiUrl = "http://localhost:9999/search.do?kind="+kind+"&search="+search;
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String result = "";
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
//			System.out.println(result);
			JSONObject json = new JSONObject(result);
			if(json.getInt("code") == 500) {
				throw new Exception("kind:"+kind+", search:"+temp+"\t"+json.getInt("code")+"\t"+json.getString("message"));
			}
			JSONArray array = json.getJSONArray("result");
			for(int i=0;i<array.length();i++) {
				JSONObject obj = array.getJSONObject(i);
				System.out.println(obj.getString("sno")+"\t"
						+obj.getString("name")+"\t"
						+obj.getString("major")+"\t"
						+obj.getDouble("score")+"\t"
						);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			try {
				FileOutputStream fos = new FileOutputStream("error.txt",true);
				PrintWriter pw = new PrintWriter(fos);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_hh:mm:ss");
				String str = sdf.format(Calendar.getInstance().getTime())+"\t"+e.getMessage();
				pw.println(str);
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}//main

}
