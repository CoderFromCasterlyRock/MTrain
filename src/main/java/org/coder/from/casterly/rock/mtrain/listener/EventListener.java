package org.coder.from.casterly.rock.mtrain.listener;

import java.util.*;
import org.coder.from.casterly.rock.mtrain.event.core.*;


public interface EventListener{

	public void init();
	public void stop();
	public String getName();
	
	public void update( Event event );
	public boolean isSupported( EventType type );
	public Set<EventType> getSupportedEventSet();
	
}
