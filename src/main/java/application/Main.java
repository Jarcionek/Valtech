package application;

import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Users users = new Users();
		Application app = new Application(new PostCommandProcessor(users), new ReadCommandProcessor(users));

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
