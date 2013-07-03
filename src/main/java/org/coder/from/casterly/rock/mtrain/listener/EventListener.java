package org.coder.from.casterly.rock.mtrain.listener;

import java.util.*;

import org.coder.from.casterly.rock.mtrain.event.core.*;


public interface EventListener{

	public String getName();
	public void update( Event event );
	public Set<EventType> getSupportedSet();

}
