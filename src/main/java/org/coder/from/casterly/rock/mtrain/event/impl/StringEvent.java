package org.coder.from.casterly.rock.mtrain.event.impl;

import org.coder.from.casterly.rock.mtrain.event.core.Event;
import org.coder.from.casterly.rock.mtrain.event.core.EventType;


public final class StringEvent implements Event{

	private final EventType type;
	private final String payload;
	
	public StringEvent( EventType type ){
		this( type, "" );
	}	
	
	public StringEvent( EventType type, String payload ){
		this.type		= type;
		this.payload 	= payload;
	}
	
	
	@Override
	public final EventType getType(){
		return type;
	}
	
	
	public final String getPayload(){
		return payload;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder( 32 );
		builder.append("StringEvent [Type=").append( getType() );
		builder.append(", Payload=").append( payload );
		builder.append("]");
		
		return builder.toString();
	}
	
	
}
