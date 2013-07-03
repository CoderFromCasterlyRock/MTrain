package org.coder.from.casterly.rock.mtrain.event.core;

import java.util.*;

import static org.coder.from.casterly.rock.mtrain.event.core.EventTypeCategory.*;


public enum EventType{
	
	LOGIN					( REQUEST 	),
	CALCULATE				( REQUEST 	),
	SUBSCRIBE				( REQUEST 	),
	UNSUBSCRIBE				( REQUEST 	),

	WRITE_ASYNCHRONOUSLY	( RESPONSE 	),
	WRITE_SYNCHRONOUSLY		( RESPONSE 	),
	
	MISSING_LISTENER		( ADMIN 	);
	
	private static final EnumSet<EventType> ALL = EnumSet.allOf( EventType.class );
	
	private final EventTypeCategory category;
	
	
	private EventType( EventTypeCategory category ){
		this.category = category;
	}
		
	
	public final EventTypeCategory getCategory(){
		return category;
	}
	
	
	public final static EnumSet<EventType> getAllEvents(){
		return ALL;
	}
	
	
}
