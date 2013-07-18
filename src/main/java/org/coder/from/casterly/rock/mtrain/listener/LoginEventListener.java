package org.coder.from.casterly.rock.mtrain.listener;

import org.slf4j.*;

import org.coder.from.casterly.rock.mtrain.core.*;
import org.coder.from.casterly.rock.mtrain.event.core.*;


public final class LoginEventListener extends AbstractEventListener{

	private final static String NAME	= LoginEventListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	
	public LoginEventListener( ){
		super( NAME,  EventType.LOGIN );
	}
	
	
	@Override
	public void init(){
		MTrain.register( this );
	}

	
	@Override
	public void update( Event event ){
		LOGGER.info("Received Login event >> {} ", event );
	}
	
	
	@Override
	public void stop(){
		MTrain.deregister( this );
	}

	
}
