package org.coder.from.casterly.rock.mtrain.listener;

import org.slf4j.*;
import java.util.*;

import org.coder.from.casterly.rock.mtrain.event.core.*;



public final class SingleEventListener implements EventListener{

	private final EnumSet<EventType> supportedTypes;
	
	private final static String NAME	= SingleEventListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	
	
	public SingleEventListener( EventType type ){
		this.supportedTypes	= EnumSet.of( type );
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
