package org.coder.from.casterly.rock.mtrain.runner;

import org.coder.from.casterly.rock.mtrain.core.*;
import org.coder.from.casterly.rock.mtrain.listener.core.*;
import org.coder.from.casterly.rock.mtrain.listener.impl.*;
import org.coder.from.casterly.rock.mtrain.messages.impl.*;


public class Tester{

	
	public static void main( String[] args ){
		
		MTrain train 				= new MTrain( );
		train.init();
		
		MListener listener1			= new LoginListener( );
		MListener listener2			= new SubscribeListener( );
		MListener listener3			= new UnsubscribeListener( );
				
		MTrain.registerAll( listener1, listener2, listener3 );
		
		train.postSync( new LoginMessage( "TestId123" ) );
		train.postSync( new SubscribeMessage("IBM", "APPL", "C", "BAC", "JPM" ) );
		train.postAsync( new UnsubscribeMessage("IBM", "APPL", "C", "BAC", "JPM" ) );
		train.postAsync( new LogoutMessage("TestId123" ) );
		
		MTrain.deregisterAll( listener1, listener2, listener3 );
		train.stop();
		
	}

}
