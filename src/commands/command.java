package commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Message;

public abstract class command {
	
	public static ArrayList<command> Commands = new ArrayList<command>();

	public abstract boolean trigger(Message msg);
	
	public abstract boolean checkPerms(Message msg);
	
	public abstract void action(Message msg);
	
	public abstract String helpText();
	
	public abstract String name();
	
	public command() {
		command.Commands.add(this);
	}
	
}
