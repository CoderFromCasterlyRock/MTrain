package org.coder.from.casterly.rock.mtrain.messages.impl;

import org.coder.from.casterly.rock.mtrain.messages.core.*;


public final class LoginMessage implements Message{

	private final String userId;
		
	public LoginMessage( String userId ){
		this.userId		= userId;
	}
	

	@Override
	public final MessageType getType(){
		return MessageType.LOGIN;
	}
	
	
	public final String getUserId(){
		return userId;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder( 32 );
		builder.append("Message [Type=").append( getType() );
		builder.append(", UserId=").append(userId);
		builder.append("]");
		
		return builder.toString();
	}
	
	
}
