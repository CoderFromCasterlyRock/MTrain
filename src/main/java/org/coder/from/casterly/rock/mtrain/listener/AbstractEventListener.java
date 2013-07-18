package org.coder.from.casterly.rock.mtrain.listener;

import java.util.*;

import org.coder.from.casterly.rock.mtrain.event.core.*;



public abstract class AbstractEventListener implements EventListener{

	private final String name;
	private final EnumSet<EventType> supportedTypes;
	
	public AbstractEventListener( String name, EventType ... supportedTypes ){
		this.name			= name;
		this.supportedTypes = EnumSet.copyOf( Arrays.asList(supportedTypes) );
	}
		
	
	@Override
	public final String getName(){
		return name;
	}


	@Override
	public final boolean isSupported( EventType type ){
		return supportedTypes.contains( type );
	}
	
	
	@Override
	public final Set<EventType> getSupportedEventSet(){
		return supportedTypes;
	}
	
	
}
