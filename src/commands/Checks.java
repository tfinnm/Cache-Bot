package commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;

public class Checks {

	private Checks() {}
	private static void failed(Message msg, String condition) {
		msg.getChannel().sendMessage("You are not authorized to do this on this server. ["+condition+" required]").queue();
	}
	
	public static boolean isTfinnm(Message msg) {
		if (msg.getAuthor().getId().equals("213319973756600322")) return true;
		failed(msg,"Tfinnm#8609");
		return false;
	}
	
	public static boolean guild(Message msg) {
		if (msg.isFromType(ChannelType.TEXT)) return true;
		msg.getChannel().sendMessage("This is a Guild-Only command!").queue();
		return false;
	}
	
//	public static boolean memeMode() {
//		return Data.memeMode;
//	}
	
	public static boolean canDelete(Message msg) {
		if (msg.getMember().hasPermission(Permission.MESSAGE_MANAGE)) return true;
		failed(msg,"MESSAGE_MANAGE permission");
		return false;
	}
	
	public static boolean canKick(Message msg) {
		if (msg.getMember().hasPermission(Permission.KICK_MEMBERS)) return true;
		failed(msg,"KICK_MEMBERS permission");
		return false;
	}
	
	public static boolean canBan(Message msg) {
		if (msg.getMember().hasPermission(Permission.BAN_MEMBERS)) return true;
		failed(msg,"BAN_MEMBERS permission");
		return false;
	}
	
	public static boolean isAdmin(Message msg) {
		if (msg.getMember().hasPermission(Permission.ADMINISTRATOR)) return true;
		failed(msg,"ADMINISTRATOR permission");
		return false;
	}
	
}
