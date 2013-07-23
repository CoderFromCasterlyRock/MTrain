package org.coder.from.casterly.rock.mtrain.listener.core;

import java.util.*;

import org.coder.from.casterly.rock.mtrain.messages.core.Message.*;


public abstract class AbstractMListener implements MListener{

	private final String name;
	private final EnumSet<MessageType> supportedTypes;

	
	public AbstractMListener( String name, MessageType ... supportedTypes ){
		this.name			= name;
		this.supportedTypes = EnumSet.copyOf( Arrays.asList(supportedTypes) );
	}
	
	
	@Override
	public final String getName(){
		return name;
	}


	@Override
	public final boolean isSupported( MessageType type ){
		return supportedTypes.contains( type );
	}
	
	
	@Override
	public final Set<MessageType> getSupportedMessages(){
		return supportedTypes;
	}
	
	
}
