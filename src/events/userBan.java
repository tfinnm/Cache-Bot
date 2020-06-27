package events;

import java.io.IOException;
import java.time.OffsetDateTime;

import core.Data;
import core.SystemChannels;
import core.settings;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;

public class userBan extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return event instanceof GuildBanEvent;
	}

	String r = "";
	
	@Override
	public void action(GenericEvent event) {
		GuildBanEvent e = (GuildBanEvent) event;
		System.out.println("BAN: "+e.getGuild()+" "+e.getUser().getName()+" "+e.getUser().getId());
		r = "";
		e.getGuild().retrieveBan(e.getUser()).queue((b) -> {
			r = b.getReason();
		});
		Data.addBan(e.getUser().getId(),r);
		try {
			Data.getBans();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		MessageEmbed embed = new MessageEmbed(null, "Ban!", "Server: "+e.getGuild().getName()+"\nName: "+e.getUser().getAsTag()+"\nID: "+e.getUser().getId()+"\nReason: "+r, null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(e.getUser().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer("DSC Bot | Powered By Tfinnm Development", settings.logo, null), null, null);
		
		SystemChannels.blacklist.sendMessage(embed).queue();
		e.getGuild().retrieveAuditLogs().queue( (a) -> {
			SystemChannels.logToServer(e.getGuild(),  new MessageEmbed(null, "Ban!", "Name: "+e.getUser().getAsTag()+"\nID: "+e.getUser().getId()+"\nReason: "+r+"\nBanned By: "+a.get(a.size()-1).getUser().getAsMention(), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(e.getUser().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer("DSC Bot | Powered By Tfinnm Development", settings.logo, null), null, null));
		});
	}

}
