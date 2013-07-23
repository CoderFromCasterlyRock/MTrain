package org.coder.from.casterly.rock.mtrain.messages.impl;

import org.coder.from.casterly.rock.mtrain.messages.core.*;

import static org.coder.from.casterly.rock.mtrain.messages.core.Message.MessageType.*;


public final class UnsubscribeMessage implements Message{

	private final String[] instruments;
	
	public UnsubscribeMessage( String ... instruments ){
		this.instruments 	= instruments;
	}
	
	
	@Override
	public final MessageType getType(){
		return UNSUBSCRIBE;
	}
	
	
	public final String[] getInstruments(){
		return instruments;
	}

	
	@Override
	public String toString( ){
		StringBuilder builder = new StringBuilder( 32 );
		builder.append("Message [Type=").append( getType() );
		builder.append(", Instruments=").append( instruments );
		builder.append("]");
		
		return builder.toString();
	}
	
	
}
