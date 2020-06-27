package events;

import java.time.OffsetDateTime;

import core.Data;
import core.SystemChannels;
import core.settings;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

public class userJoin extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return event instanceof GuildMemberJoinEvent;
	}

	@Override
	public void action(GenericEvent event) {
		GuildMemberJoinEvent e = (GuildMemberJoinEvent) event;
		System.out.println("JOIN: "+e.getGuild()+" "+e.getUser().getName()+" "+e.getUser().getId());
		SystemChannels.logToServer(e.getGuild(), new MessageEmbed(null, "New User Joined ","Name: "+e.getUser().getAsTag()+"\nID: "+e.getUser().getId()+"\nAccount Created: "+e.getUser().getTimeCreated(), null, OffsetDateTime.now(), 0x33cc33, new Thumbnail(e.getUser().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null));
		SystemChannels.updateMemberCount(e.getGuild());
		for (String temp: Data.BL) {
			if (temp.startsWith(e.getUser().getId())) {
				MessageEmbed embed = new MessageEmbed(null, "Banned User Joined "+e.getGuild().getName(), "Name: "+e.getUser().getAsTag()+"\nID: "+e.getUser().getId()+"\nReason: "+Data.BLR.get(Data.BL.indexOf(e.getUser().getId())), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(e.getUser().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null);
				SystemChannels.onJoin(e.getGuild()).sendMessage(embed).queue();
			}
		}
		for (String temp: Data.AL) {
			if (temp.startsWith(e.getUser().getId())) {
				MessageEmbed embed = new MessageEmbed(null, "User With Active Advisory Joined "+e.getGuild().getName(), "Name: "+e.getUser().getAsTag()+"\nID: "+e.getUser().getId()+"\nReason: "+Data.ALR.get(Data.AL.indexOf(e.getUser().getId())), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(e.getUser().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null);
				SystemChannels.onJoin(e.getGuild()).sendMessage(embed).queue();
			}
		}
		
	}

}
