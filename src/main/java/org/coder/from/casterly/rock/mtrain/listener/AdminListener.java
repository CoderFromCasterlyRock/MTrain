package org.coder.from.casterly.rock.mtrain.listener;

import org.slf4j.*;

import org.coder.from.casterly.rock.mtrain.core.*;
import org.coder.from.casterly.rock.mtrain.event.core.*;


import static org.coder.from.casterly.rock.mtrain.event.core.EventType.*;


public final class AdminListener extends AbstractEventListener{

	private final static String NAME	= AdminListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	
	public AdminListener( ){
		super( NAME, MISSING_LISTENER );
	}
	

	@Override
	public void init(){
		MTrain.register( this );
	}

	
	@Override
	public void update( Event event ){
		LOGGER.warn("FOUND DEAD event: {}", event );
	}

	
	@Override
	public void stop(){
		MTrain.deregister( this );
	}
	
}

