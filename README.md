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

Create Message Listeners and register them with MTrain.

		MListener listener1			= new LoginListener( );
		MListener listener2			= new SubscribeListener( );
		MListener listener3			= new UnsubscribeListener( );
		MTrain.registerAll( listener1, listener2, listener3 );
		
Dispatch events, **synchronously** or **asynchronously**, to the registered listener.
	
		train.postSync( new LoginMessage( "TestId123" ) );
		train.postAsync( new SubscribeMessage("IBM", "APPL", "C", "BAC", "JPM" ) );
		train.postAsync( new UnsubscribeMessage("IBM", "APPL", "C", "BAC", "JPM" ) );
		train.postSync( new LogoutMessage("TestId123" ) );
	
Note that the blocking or non-blocking nature of message dispatching is not bound at compile-time.
Therefore, you can dispatch any message either synchronously or asynchronously.
		
Further, you will notice that LogoutMessage has no takers ( no registered listeners ).
In the parlance of MTRAIN, such messages are considered **dead**.  
Dead messages, by default, are logged as a warning by a special listener called the AdminListener.  

Optionally, deregister the listener and stop the MTrain.

	MTrain.deregisterAll( listener1, listerner2 );
	train.stop();
	

Disclaimer
----------
Ideas expressed here are my own and do not, in any way, represent those of my employer.