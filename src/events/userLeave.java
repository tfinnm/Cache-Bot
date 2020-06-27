package events;

import java.time.OffsetDateTime;

import core.SystemChannels;
import core.settings;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;

public class userLeave extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return event instanceof GuildMemberLeaveEvent;
	}

	@Override
	public void action(GenericEvent event) {
		GuildMemberLeaveEvent e = (GuildMemberLeaveEvent) event;
		System.out.println("LEAVE: "+e.getGuild()+" "+e.getUser().getName()+" "+e.getUser().getId());
		SystemChannels.logToServer(e.getGuild(), new MessageEmbed(null, "User Left ","Name: "+e.getUser().getAsTag()+"\nID: "+e.getUser().getId()+"\nAccount Created: "+e.getUser().getTimeCreated(), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(e.getUser().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null));
		SystemChannels.updateMemberCount(e.getGuild());
	}

}
