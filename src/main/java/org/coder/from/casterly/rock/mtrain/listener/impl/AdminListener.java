package org.coder.from.casterly.rock.mtrain.listener.impl;

import org.slf4j.*;

import org.coder.from.casterly.rock.mtrain.listener.core.AbstractMListener;
import org.coder.from.casterly.rock.mtrain.messages.core.*;

import static org.coder.from.casterly.rock.mtrain.messages.core.Message.MessageType.*;


public final class AdminListener extends AbstractMListener{

	private final static String NAME	= AdminListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	
	public AdminListener( ){
		super( NAME, MISSING_LISTENER );
	}
	

	@Override
	public void update( Message message ){
		LOGGER.warn("Received DEAD >> {}", message );
	}

	
}

