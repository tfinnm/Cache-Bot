package data;

import java.util.*;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;

public class MessageCache
{

    private final JDA api;
    private final Map<String, Message> messageMap;
    public static MessageCache cache;

    public MessageCache(final JDA api, final boolean weak)
    {
        this.api = api;

        this.messageMap = Collections.synchronizedMap(weak ? new WeakHashMap<String, Message>() : new HashMap<String, Message>());

    }

    public void clear()
    {
        this.messageMap.clear();
    }

    public Collection<Message> getCachedMessages()
    {
        return this.messageMap.values();
    }

    public Message getMessage(final MessageChannel channel, final String Id)
    {
        final Message message = this.getMessage(Id);

        if (message == null)
        	//TODO: This is a mem leak
            return channel.retrieveMessageById(Id).complete();
        else
            return message;
    }

    public Message getMessage(final String Id)
    {
        return this.messageMap.get(Id);
    }

    public Message getMessage(final String channelId, final String Id)
    {
        final Message message = this.getMessage(Id);

        if (message == null)
        {
            MessageChannel channel = this.api.getTextChannelById(channelId);

            if (channel == null)
                channel = this.api.getPrivateChannelById(channelId);

            if (channel != null)
            	//TODO: memleak
                return channel.retrieveMessageById(Id).complete();

        }

        return message;
    }

    public boolean inCache(String messageID) {
    	return this.messageMap.containsKey(messageID);
    }
    public void addToCache(Message message) {
        this.messageMap.put(message.getId(), message);
    }
    public void removeFromCache(MessageDeleteEvent event) {
        this.messageMap.remove(event.getMessageId());
    }
    public void removeFromCache(MessageBulkDeleteEvent event) {
    	this.messageMap.keySet().removeAll(event.getMessageIds());
    }

}