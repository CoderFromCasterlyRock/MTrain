package org.coder.from.casterly.rock.mtrain.listener;

import org.coder.from.casterly.rock.mtrain.event.core.Event;
import org.coder.from.casterly.rock.mtrain.event.core.EventType;
import org.slf4j.*;

import java.util.*;


import static org.coder.from.casterly.rock.mtrain.event.core.EventType.*;


public class AdminListener implements EventListener{

	private final EnumSet<EventType> supportedTypes;
	
	private final static String NAME	= AdminListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	
	
	public AdminListener( ){
		this.supportedTypes = EnumSet.of( MISSING_LISTENER );
	}
	
	
	@Override
	public String getName(){
		return NAME;
	}

	
	@Override
	public Set<EventType> getSupportedSet(){
	    return supportedTypes;
	}

	
	@Override
	public void update( Event event ){
		LOGGER.warn("FOUND DEAD event: {}", event );
	}

	
}

