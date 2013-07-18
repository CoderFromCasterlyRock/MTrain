package org.coder.from.casterly.rock.mtrain.event.impl;

import org.coder.from.casterly.rock.mtrain.event.core.*;


public final class LoginEvent implements Event{

	private final String userId;
		
	public LoginEvent( String userId ){
		this.userId		= userId;
	}
	

	@Override
	public final EventType getType(){
		return EventType.LOGIN;
	}
	
	
	public final String getUserId(){
		return userId;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder( 32 );
		builder.append("Event [Type=").append( getType() );
		builder.append(", UserId=").append(userId);
		builder.append("]");
		
		return builder.toString();
	}
	
	
}
