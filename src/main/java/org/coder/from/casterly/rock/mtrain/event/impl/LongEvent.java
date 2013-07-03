package org.coder.from.casterly.rock.mtrain.event.impl;

import org.coder.from.casterly.rock.mtrain.event.core.Event;
import org.coder.from.casterly.rock.mtrain.event.core.EventType;


public final class LongEvent implements Event{

	private final long payload;
	private final EventType type;
	
	public LongEvent( EventType type ){
		this( type, -1L );
	}	
	
	public LongEvent( EventType type, long payload ){
		this.type		= type;
		this.payload 	= payload;
	}
	

	@Override
	public final EventType getType(){
		return type;
	}
	
	
	public final long getPayload(){
		return payload;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder( 32 );
		builder.append("LongEvent [Type=").append( getType() );
		builder.append(", Payload=").append(payload);
		builder.append("]");
		
		return builder.toString();
	}
	
	
}
