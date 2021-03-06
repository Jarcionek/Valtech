package main;

import application.Application;
import application.Clock;
import application.Users;
import application.WallToStringConverter;
import application.commands.FollowCommandProcessor;
import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;
import application.commands.WallCommandProcessor;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Users users = new Users();
		Clock clock = new Clock();
		Application app = new Application(new PostCommandProcessor(users, clock),
										  new ReadCommandProcessor(users),
										  new FollowCommandProcessor(users),
										  new WallCommandProcessor(users),
										  new WallToStringConverter(clock));

		Scanner scanner = new Scanner(System.in);

		while (true) {
			String command = scanner.nextLine();
			String output = app.execute(command);
			if (output != null && output.length() != 0) {
				System.out.println(output);
			}
		}
	}

}
