package org.coder.from.casterly.rock.mtrain.listener.core;

import java.util.*;

import org.coder.from.casterly.rock.mtrain.messages.core.*;
import org.coder.from.casterly.rock.mtrain.messages.core.Message.*;


public interface MListener{

	public String getName();
	
	public void update( Message message );
	public boolean isSupported( MessageType type );
	public Set<MessageType> getSupportedMessages();
	
}
