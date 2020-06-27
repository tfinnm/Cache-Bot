package core;
/**
 *  Copyright 2020 Toby McDonald
 */

import java.io.IOException;

import javax.security.auth.login.LoginException;

import commands.commandHandler;
import events.eventHandler;

public class launcher {
	
	public static void main(String[] args) {
		try {
			eventHandler.loadEvents();
			commandHandler.loadCommands();
			startup.loadData();
			startup.loadJDA();
			startup.runInitialTasks();
			System.out.println("READY!");
		} catch (LoginException | InterruptedException | IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
}
