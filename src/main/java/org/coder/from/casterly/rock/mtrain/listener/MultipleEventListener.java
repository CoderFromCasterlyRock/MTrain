package org.coder.from.casterly.rock.mtrain.listener;

import org.coder.from.casterly.rock.mtrain.event.core.Event;
import org.coder.from.casterly.rock.mtrain.event.core.EventType;
import org.slf4j.*;

import java.util.*;



public final class MultipleEventListener implements EventListener{

	private final EnumSet<EventType> supportedTypes;
	
	private final static String NAME	= MultipleEventListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	
	
	public MultipleEventListener( EventType ... types ){
		this.supportedTypes	= EnumSet.copyOf( Arrays.asList(types) );
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
		LOGGER.info("Event arrived: {} ", event );
	}

	
}
