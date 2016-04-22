package com.kyle.tool;

import java.io.IOException;
import java.util.List;

import com.kyle.bean.User;

public class LoginTool {

	public static void main(String[] args) throws IOException {
		MyRun myRun = new MyRun();
		myRun.start();
	}

	private static class MyRun extends Thread {

		@Override
		public void run() {
			Honor h = new Honor();
			ReadFileFromTxt rff = new ReadFileFromTxt();
			List<User> users = rff.readFile();
			for (User u : users) {
				try {
					h.login(u);
				} catch (IOException e) {
					e.printStackTrace();
				}
				int delay = (int) (Math.random() * 1000 * 60 * 0.5);
				System.out.println("delay time: " + (float) delay / 1000 / 60
						+ "min");
				try {
					sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			super.run();
		}
	}
}
