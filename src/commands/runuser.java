package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class runuser extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"runuser");
	}

	@Override
	public void action(Message msg) {

		if (msg.getMentionedMembers().size() > 0) {
			msg.getChannel().sendMessage(online(msg)).queue();
		} else {
			msg.getChannel().sendMessage(offline(msg)).queue();
		}
	}

	@Override
	public String helpText() {
		return "Provides usefull information about a user.";
	}

	@Override
	public String name() {
		return settings.prefix+"runuser <@user>";
	}

	@Override
	public boolean checkPerms(Message msg) {
		return true;
	}

	public MessageEmbed offline(Message msg) {
		List<Field> cmds = new ArrayList<Field>();
		String curruser = msg.getContentRaw().substring(17);
		cmds.add(new Field("ID", curruser, true));
		String Badges = "";
		try {
			for(String[] temparr: Data.getBadges()) {
				if (temparr[0].equals(curruser)) {
					Badges += temparr[1] + "\n";
				}
			}
			System.out.print(Badges);
			cmds.add(new Field(settings.netname+" Badges", Badges,true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Data.memeMode) {
			int c = 0;
			for(String[] s: Data.bruhCount) {
				if (s[0].equals(curruser)) {
					c = Integer.parseInt(s[1]);
				}
			}
			cmds.add(new Field("Bruhs", String.valueOf(c),true));
		}
		int color = 0x33cc33; 
		String warnmsg = "User is in good standing with DSC.";
		if (Data.memeMode) {
			warnmsg = "User is guilty of warcrimes for eating pizza on pineapple.";
		}
		if (Data.BL.contains(curruser)) {
			color = 0xF40C0C;
			warnmsg =  "User has a ban on a "+settings.netname+" member server for "+Data.BLR.get(Data.BL.indexOf(curruser));
		} else if (Data.AL.contains(curruser)) {
			color = 0xd4af37;
			warnmsg =  "User has an advisory on a "+settings.netname+" member server for "+Data.ALR.get(Data.AL.indexOf(curruser));
		}
		System.out.println(warnmsg);
		return new MessageEmbed(null, "User Info", warnmsg, null, null, color, null, null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null,cmds);
	}

	public MessageEmbed online(Message msg) {
		List<Field> cmds = new ArrayList<Field>();
		List<User> mentionedUsers = msg.getMentionedUsers();
		User curruser = mentionedUsers.get(0);
			cmds.add(new Field("Name", curruser.getAsTag(), true));
			cmds.add(new Field("ID", curruser.getId(), true));
			cmds.add(new Field("Joined Discord", String.valueOf(curruser.getTimeCreated()), true));
			cmds.add(new Field("Joined Server", String.valueOf(msg.getGuild().getMemberById(curruser.getId()).getTimeJoined()), true));
			String roles = "";
			for(Role temprole: msg.getGuild().getMemberById(curruser.getId()).getRoles()) {
				roles += temprole.getAsMention();
			}
			cmds.add(new Field("Roles", roles, true));
			String servers = "";
			for(Guild tempguild: curruser.getMutualGuilds()) {
				servers += tempguild.getName() + "\n";
			}
			cmds.add(new Field(settings.netname+" Servers", servers,true));
			String Badges = "";
			try {
				for(String[] temparr: Data.getBadges()) {
					if (temparr[0].equals(curruser.getId())) {
						Badges += temparr[1] + "\n";
					}
				}
				cmds.add(new Field(settings.netname+" Badges", Badges,true));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (Data.memeMode) {
				int c = 0;
				for(String[] s: Data.bruhCount) {
					if (s[0].equals(curruser.getId())) {
						c = Integer.parseInt(s[1]);
					}
				}
				cmds.add(new Field("Bruhs", String.valueOf(c),true));
			}
		int color = 0x33cc33; 
		String warnmsg = "User is in good standing with "+settings.netname+".";
		if (Data.memeMode) {
			warnmsg = "User is guilty of warcrimes for eating pizza on pineapple.";
		}
		if (Data.BL.contains(curruser.getId())) {
			color = 0xF40C0C;
			warnmsg =  "User has a ban on a "+settings.netname+" member server for "+Data.BLR.get(Data.BL.indexOf(curruser.getId()));
		} else if (Data.AL.contains(curruser.getId())) {
			color = 0xd4af37;
			warnmsg =  "User has an advisory on a "+settings.netname+" member server for "+Data.ALR.get(Data.AL.indexOf(curruser.getId()));
		}
		return new MessageEmbed(null, "User Info", warnmsg, null, null, color, new Thumbnail(curruser.getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null,cmds);
	}

}

