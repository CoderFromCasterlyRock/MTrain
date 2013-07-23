package org.coder.from.casterly.rock.mtrain.listener.impl;

import org.slf4j.*;

import org.coder.from.casterly.rock.mtrain.listener.core.AbstractMListener;
import org.coder.from.casterly.rock.mtrain.messages.core.*;
import org.coder.from.casterly.rock.mtrain.messages.impl.*;

import static org.coder.from.casterly.rock.mtrain.messages.core.Message.MessageType.*;


public final class SubscribeListener extends AbstractMListener{

	private final static String NAME	= SubscribeListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	

	public SubscribeListener( ){
		super( NAME, SUBSCRIBE );
	}

	
	@Override
	public void update( Message event ){
		SubscribeMessage message 	= ( SubscribeMessage ) event;
		String[] instruments		= message.getInstruments();
		
		for( String instrument : instruments ){
			LOGGER.info("Subscribing for Market data for Instrument [{}].", instrument );
		}
	}

	
}
