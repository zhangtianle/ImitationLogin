package com.kyle.tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kyle.bean.User;

public class ReadFileFromTxt {

	@Test
	public List<User> readFile() {
		String filePath = "d:/user.txt";
		BufferedReader in = null;
		List<User> users = new ArrayList<User>();
		try {
			in = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = in.readLine()) != null) {
				String[] s = line.split("\t");
				User u = new User();
				u.setId(s[0]);
				u.setPassWord(s[1]);
				users.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return users;
	}
}
