package events;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import core.SystemChannels;
import core.settings;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public class hackathonFileSubmit extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return (event instanceof PrivateMessageReceivedEvent);
	}

	@Override
	public void action(GenericEvent event) {
		PrivateMessageReceivedEvent msg = (PrivateMessageReceivedEvent) event;
		List<Attachment> imgs = msg.getMessage().getAttachments();
		if (imgs.size() > 0) {
			for (Attachment img: imgs) {
				System.out.print("file received");
				try {
					MessageEmbed embed = new MessageEmbed(null, "New Entry", "Name: "+msg.getAuthor().getAsTag()+"\nID: "+msg.getAuthor().getId(), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(msg.getAuthor().getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null);
					SystemChannels.hackathon.sendMessage(embed).addFile(img.downloadToFile().get()).queue();
				} catch (InterruptedException | ExecutionException ex) {
					msg.getChannel().sendMessage("An error has occured. Please try again.").queue();
				}
				msg.getChannel().sendMessage("Received Entry.").queue();
			}
		}
	}

}
