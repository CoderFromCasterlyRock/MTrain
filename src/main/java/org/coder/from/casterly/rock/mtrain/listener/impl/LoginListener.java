package org.coder.from.casterly.rock.mtrain.listener.impl;

import org.slf4j.*;
import org.coder.from.casterly.rock.mtrain.listener.core.AbstractMListener;
import org.coder.from.casterly.rock.mtrain.messages.core.*;

import static org.coder.from.casterly.rock.mtrain.messages.core.Message.MessageType.*;


public final class LoginListener extends AbstractMListener{

	private final static String NAME	= LoginListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	

	public LoginListener( ){
		super( NAME, LOGIN );
	}
	

	@Override
	public void update( Message message ){
		LOGGER.debug( "Received >> {}", message );
	}	
	
		
}
