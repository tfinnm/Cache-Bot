package core;
/**
 *  Copyright 2020 Toby McDonald
 */

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class SystemChannels {
	
	public static final String[] GuildInviteBL = {"723223561283567657","537826156414500866"};
	
	public static final TextChannel botdev = Data.jda.getTextChannelById("723223561283567660");
	
	public static final TextChannel blacklist = Data.jda.getTextChannelById("723223561283567660");
	
	public static TextChannel onJoin(Guild g) {
		
		String[][] servers = {{"GUILD ID HERE","CHANNEL ID HERE"},
				{"665951159567253529","671436182667657287"},
				{"703301617004445829","726288125365715035"}};
		
		for (String[] t: servers) {
			if (t[0].equals(g.getId())) {
				return g.getTextChannelById(t[1]);
			}
		}
		return g.getSystemChannel();
	}
	
	public static boolean logToServer(Guild g, MessageEmbed messageEmbed) {
		String[][] servers = {{"GUILD ID HERE","CHANNEL ID HERE"},
				{"703301617004445829","726288125365715035"}};
		
		for (String[] t: servers) {
			if (t[0].equals(g.getId())) {
				g.getTextChannelById(t[1]).sendMessage(messageEmbed).queue();
				return true;
			}
		}
		return false;
	}
	
	public static boolean updateMemberCount(Guild g) {
	String[][] servers = {{"GUILD ID HERE","CHANNEL ID HERE"},
			{"665951159567253529","726509318542852197"},
			{"703301617004445829","726858804150665237"}};
		
		for (String[] t: servers) {
			if (t[0].equals(g.getId())) {
				g.getGuildChannelById(t[1]).getManager().setName("Members: "+g.getMemberCount()).queue();
				return true;
			}
		}
		return false;
	}
	
}
