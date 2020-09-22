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
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;

public class messageRemove extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return event instanceof MessageDeleteEvent;
	}

	@Override
	public void action(GenericEvent event) {
		MessageDeleteEvent e = (MessageDeleteEvent) event;
		if (MessageCache.cache.inCache(e.getMessageId())) {
			SystemChannels.logToServer(e.getGuild(), new MessageEmbed(null, "Message Removed","User: "+MessageCache.cache.getMessage(e.getMessageId()).getAuthor().getAsTag()+"\nID: "+MessageCache.cache.getMessage(e.getMessageId()).getAuthor().getId()+"\nChannel: "+e.getChannel().getName()+"\nMessage: "+MessageCache.cache.getMessage(e.getMessageId()).getContentRaw(), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(MessageCache.cache.getMessage(e.getMessageId()).getAuthor().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null));
		}
		MessageCache.cache.removeFromCache(e);
	}

}
