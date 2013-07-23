package org.coder.from.casterly.rock.mtrain.listener.impl;

import org.slf4j.*;

import org.coder.from.casterly.rock.mtrain.listener.core.AbstractMListener;
import org.coder.from.casterly.rock.mtrain.messages.core.*;
import org.coder.from.casterly.rock.mtrain.messages.impl.*;

import static org.coder.from.casterly.rock.mtrain.messages.core.Message.MessageType.*;


public final class UnsubscribeListener extends AbstractMListener{

	private final static String NAME	= UnsubscribeListener.class.getSimpleName();
	private final static Logger LOGGER 	= LoggerFactory.getLogger( NAME );
	

	public UnsubscribeListener( ){
		super( NAME, UNSUBSCRIBE );
	}

	
	@Override
	public void update( Message event ){
		
		UnsubscribeMessage message 	 = ( UnsubscribeMessage ) event;
		String[] instruments	 	 = message.getInstruments();
		
		for( String instrument : instruments ){
			LOGGER.info("Unsubscribing Instrument [{}] from market data server.", instrument );
		}
	}

	
}
