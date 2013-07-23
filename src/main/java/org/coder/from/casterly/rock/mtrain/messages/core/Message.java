package org.coder.from.casterly.rock.mtrain.messages.core;

import java.util.*;

import static org.coder.from.casterly.rock.mtrain.messages.core.Message.MessageCategory.*;


public interface Message{
	
	
	public enum MessageCategory{
		REQUEST,
		RESPONSE,
		ADMIN;
	}

	
	public enum MessageType{
		
		LOGIN					( REQUEST 	),
		LOGOUT					( REQUEST 	),
		SUBSCRIBE				( REQUEST 	),
		UNSUBSCRIBE				( REQUEST 	),

		MISSING_LISTENER		( ADMIN 	);
		
		private static final EnumSet<MessageType> ALL = EnumSet.allOf( MessageType.class );
		
		private final MessageCategory category;
		
		
		private MessageType( MessageCategory category ){
			this.category = category;
		}
			
		
		public final MessageCategory getCategory(){
			return category;
		}
		
		
		public final static EnumSet<MessageType> getAllTypes(){
			return ALL;
		}
		
	}
	
	
	
	public MessageType getType();
		
}

