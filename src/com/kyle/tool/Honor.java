package com.kyle.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.kyle.bean.User;

public class Honor {
	
	@Test
	public void login(User u) throws IOException {
		String sUrl = "http://www.baidu.com/Login.do";
		URL url = new URL(sUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream(), "utf-8");
		out.write("Type=Login&UserName=" + u.getId() + "&Password=" + u.getPassWord() + "&UserType=xxx"); // post的关键所在！
		out.flush();
		out.close();

		// getcookie
		String cookieVal = connection.getHeaderField("Set-Cookie");

		String sCurrentLine = "";
		String sTotalString = "";
		sCurrentLine = "";
		sTotalString = "";
		InputStream l_urlStream;
		l_urlStream = connection.getInputStream();
		// 传说中的三层包装阿
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(
				l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sTotalString += sCurrentLine;

		}
		System.out.print("User: " + u.getId() + " login: " + sTotalString + "  ");
		l_reader.close();
		l_urlStream.close();
		connection.disconnect();
		
		
		//vote
		String num = "xxx";
		String s = "http://www.baidu.com/xxx.do?Type=Like&CANDIDATE_ID=" + num;
		String param = "Type=Like&CANDIDATE_ID=" + num;
		// 重新打开一个连接
		url = new URL(s);
		HttpURLConnection resumeConnection = (HttpURLConnection) url.openConnection();
		if (cookieVal != null) {
			// 发送cookie信息上去，以表明自己的身份，否则会被认为没有权限
			resumeConnection.setRequestProperty("Cookie", cookieVal);
		}
		
		resumeConnection.setRequestProperty("Accept", "*/*");
		resumeConnection.setRequestProperty("connection", "Keep-Alive");
		resumeConnection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 发送POST请求必须设置如下两行
		resumeConnection.setDoOutput(true);
		resumeConnection.setDoInput(true);
        // 获取URLConnection对象对应的输出流
		PrintWriter pout = null;
        pout = new PrintWriter(resumeConnection.getOutputStream());
        // 发送请求参数
        pout.print(param);
        // flush输出流的缓冲
        pout.flush();
        // 定义BufferedReader输入流来读取URL的响应
        BufferedReader bin = null;
        bin = new BufferedReader(
                new InputStreamReader(resumeConnection.getInputStream()));
        String line;
        String voteResult = "";
        while ((line = bin.readLine()) != null) {
        	voteResult += line;
        }
		System.out.println("vote: " + voteResult);
	}
}
