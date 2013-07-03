MTrain
======


Introduction
------------
MTrain, contraction for Message Train, is an in-process, thread-safe, event dispatching mechanism for server components.

Quick Usage Guide
-----------------
Create and initialize MTrain.

	MTrain train = new MTrain( );
	train.init();

Create EventListeners and register them with MTrain.

	EventListener listener1	= new SingleEventListener( LOGIN );
	EventListener listener2	= new MultipleEventListener( CALCULATE, SUBSCRIBE );
	MTrain.registerAll( listener1, listener2 );
	
Dispatch events, **synchronously** or **asynchronously**, to the registered listener.
	
	train.postSync( new LongEvent( LOGIN, 1005) );
	train.postSync( new StringEvent( CALCULATE, "Price to Yield for the 2 Year OTR Bond.") );
	train.postSync( new StringEvent( SUBSCRIBE, "Subscribe for IBM market data." ) );
		
	train.postAsync( new OrderEvent( WRITE_SYNCHRONOUSLY, 1, "IBM", 55d, 100, "Buy" ) );

You will notice that we don't have any takers for **WRITE_SYNCHRONOUSLY** event.
In the parlance of MTRAIN, such events are called **dead** events.  
Dead events, by default, are logged as a warning by a special listener called the AdminListener.  

Optionally, deregister the listener and stop the MTrain.

	MTrain.deregisterAll( listener1, listerner2 );
	train.stop();
	

Disclaimer
----------
Ideas expressed here are my own and do not, in any way, represent those of my employer.