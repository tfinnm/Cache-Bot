package core;
/**
 *  Copyright 2020 Toby McDonald
 */

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class SystemChannels {
	
	public static final String[] GuildInviteBL = {"723223561283567657","537826156414500866"};
	
	public static final TextChannel botdev = launcher.jda.getTextChannelById("723223561283567660");
	
	public static final TextChannel blacklist = launcher.jda.getTextChannelById("723223561283567660");
	
	public static final TextChannel hackathon = launcher.jda.getTextChannelById("727609798190825633");
	
	public static TextChannel onJoin(Guild g) {
		for (String[] t: Data.joinChannel) {
			if (t[0].equals(g.getId())) {
				return g.getTextChannelById(t[1]);
			}
		}
		return g.getSystemChannel();
	}
	
	public static boolean logToServer(Guild g, MessageEmbed messageEmbed) {
		for (String[] t: Data.logChannel) {
			System.out.print(t[0]);
			if (t[0].equals(g.getId())) {
				g.getTextChannelById(t[1]).sendMessage(messageEmbed).queue();
				System.out.print("logging!");
				return true;
			}
		}
		System.out.print("not logging!");
		return false;
	}
	
	public static boolean updateMemberCount(Guild g) {
		for (String[] t: Data.memberCount) {
			if (t[0].equals(g.getId())) {
				g.getGuildChannelById(t[1]).getManager().setName("Members: "+g.getMemberCount()).queue();
				return true;
			}
		}
		return false;
	}
	
}
