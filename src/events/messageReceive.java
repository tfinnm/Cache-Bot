package events;

import commands.commandHandler;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class messageReceive extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return event instanceof MessageReceivedEvent;
	}

	@Override
	public void action(GenericEvent event) {
		MessageReceivedEvent e = (MessageReceivedEvent) event;
		Message msg = e.getMessage();
		log(msg);
		commandHandler.handleMessage(msg);
	}
	
	public void log(Message msg) {
		if (msg.isFromType(ChannelType.TEXT))
		{

			String name;
			if (msg.isWebhookMessage()) {
				name = msg.getAuthor().getName();                
			}else{
				name = msg.getMember().getEffectiveName();      
			}                                           
			System.out.printf("(%s)[%s]<%s>: %s\n", msg.getGuild().getName(), msg.getTextChannel().getName(), name, msg);
		} else if (msg.isFromType(ChannelType.PRIVATE)) {
			System.out.printf("[PRIV]<%s>: %s\n", msg.getAuthor().getName(), msg);
		}
	}

}
