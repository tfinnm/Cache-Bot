package events;

import java.time.OffsetDateTime;

import core.SystemChannels;
import core.settings;
import data.MessageCache;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;

public class messageEdit extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return event instanceof MessageUpdateEvent;
	}

	@Override
	public void action(GenericEvent event) {
		MessageUpdateEvent e = (MessageUpdateEvent) event;
		SystemChannels.logToServer(e.getGuild(), new MessageEmbed(null, "Message Edited","[Message](https://discordapp.com/channels/"+e.getGuild().getId()+"/"+e.getChannel().getId()+"/"+e.getMessageId()+"/)\nUser: "+e.getAuthor().getAsTag()+"\nID: "+e.getAuthor().getId()+"\nChannel: "+e.getChannel().getName()+"\nBefore: "+MessageCache.cache.getMessage(e.getMessageId()).getContentRaw()+"\nAfter: "+e.getMessage().getContentRaw(), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(e.getAuthor().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null));
		MessageCache.cache.addToCache(e.getMessage());
	}

}
