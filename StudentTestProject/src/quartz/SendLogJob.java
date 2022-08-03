package quartz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendLogJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		FileReader fr = null;
		BufferedReader br = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			fr = new FileReader("error.txt"); 
			br = new BufferedReader(fr);
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				String[] arr = str.split("\t");
				String log_date = arr[0];
				String content=arr[1];
				String error_code=arr[2];
				Map<String,String> map = new HashMap<String, String>();
				log_date = log_date.replace("_", " ");
				map.put("log_date", log_date);
				map.put("content", content);
				map.put("error_code", error_code);
				list.add(map);
			}
			
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","testonly","1234");
			if(list.size() != 0) {
				for(Map<String,String> map : list) {
					String sql = "insert into student_log values(TO_DATE(?,'yyyy/mm/dd hh24:mi:ss'),?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, map.get("log_date"));
					pstmt.setString(2, map.get("error_code"));
					pstmt.setString(3, map.get("content"));
					pstmt.executeQuery();
					conn.commit();
				}
			}
			FileOutputStream fos = new FileOutputStream("error.txt",false);
			PrintWriter pw = new PrintWriter(fos);
			pw.print("");
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

}
