package org.coder.from.casterly.rock.mtrain.listener;

import org.slf4j.*;

import org.coder.from.casterly.rock.mtrain.core.MTrain;
import org.coder.from.casterly.rock.mtrain.event.core.*;

import static org.coder.from.casterly.rock.mtrain.event.core.EventType.*;


public final class MultipleEventListener extends AbstractEventListener{

	private final static String NAME	= MultipleEventListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	
	
	public MultipleEventListener( EventType ... types ){
		super( NAME, CALCULATE, SUBSCRIBE );
	}
	
	
	@Override
	public void init(){
		MTrain.register( this );
	}
	
	
	@Override
	public void update( Event event ){
		LOGGER.info("Received {} event >> {} ", event.getType(), event );
	}
	
	
	@Override
	public void stop(){
		MTrain.deregister( this );
	}

	
}
