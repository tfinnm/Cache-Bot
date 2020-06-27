package events;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.GenericEvent;

public abstract class event {
	
	public static ArrayList<event> Events = new ArrayList<event>();

	public abstract boolean trigger(GenericEvent event);
	
	public abstract void action(GenericEvent event);
	
	public event() {
		event.Events.add(this);
	}
	
}
