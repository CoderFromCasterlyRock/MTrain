package org.coder.from.casterly.rock.mtrain.runner;

import org.coder.from.casterly.rock.mtrain.core.*;
import org.coder.from.casterly.rock.mtrain.event.impl.*;
import org.coder.from.casterly.rock.mtrain.listener.*;

import static org.coder.from.casterly.rock.mtrain.event.core.EventType.*;


public class Tester {

	
	public static void main( String[] args ){
		
		MTrain train 			= new MTrain( );
		train.init();
		
		EventListener listener1	= new SingleEventListener( LOGIN );
		EventListener listener2	= new MultipleEventListener( CALCULATE, SUBSCRIBE );
		MTrain.registerAll( listener1, listener2 );
		
		train.postSync( new LongEvent( LOGIN, 1005) );
		train.postSync( new StringEvent( CALCULATE, "Price to Yield for the 2 Year OTR Bond.") );
		train.postSync( new StringEvent( SUBSCRIBE, "Subscribe for IBM market data." ) );
		
		train.postAsync( new OrderEvent( WRITE_SYNCHRONOUSLY, 1, "IBM", 55d, 100, "Buy" ) );
		
		MTrain.deregisterAll( listener1, listener2 );
		train.stop();
		
	}

}