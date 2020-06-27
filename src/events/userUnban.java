package events;

import java.time.OffsetDateTime;

import core.SystemChannels;
import core.settings;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;

public class userUnban extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return event instanceof GuildUnbanEvent;
	}

	@Override
	public void action(GenericEvent event) {
		GuildUnbanEvent e = (GuildUnbanEvent) event;
		System.out.println("UNBAN: "+e.getGuild()+" "+e.getUser().getName()+" "+e.getUser().getId());
		SystemChannels.logToServer(e.getGuild(), new MessageEmbed(null, "User UnBanned ","Name: "+e.getUser().getAsTag()+"\nID: "+e.getUser().getId()+"\nAccount Created: "+e.getUser().getTimeCreated(), null, OffsetDateTime.now(), 0x33cc33, new Thumbnail(e.getUser().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null));
		
	}

}
