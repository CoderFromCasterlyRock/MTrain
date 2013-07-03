package org.coder.from.casterly.rock.mtrain.core;

import org.slf4j.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import org.coder.from.casterly.rock.mtrain.event.core.Event;
import org.coder.from.casterly.rock.mtrain.listener.AdminListener;
import org.coder.from.casterly.rock.mtrain.listener.EventListener;

import static java.util.concurrent.TimeUnit.*;


public final class MTrain{

	private volatile boolean keepDispatching;
	
	private final long startTime;
	private final Runnable dispatcher;
	private final AdminListener adminListener;
	private final AtomicInteger messageCounter;
	
	private final BlockingQueue<Event> EVENT_QUEUE;
	private final ExecutorService executorService;
	
	
	private final static String NAME 						     	= MTrain.class.getSimpleName();
	private final static Logger LOGGER 						     	= LoggerFactory.getLogger( NAME );
	private final static CopyOnWriteArrayList<EventListener> ELIST 	= new CopyOnWriteArrayList<EventListener>( );
	
	
	public MTrain(  ){
		this( 64 );
	}
	

	public MTrain( int capacity ){
	    
		this.startTime          = System.nanoTime();
		this.messageCounter     = new AtomicInteger();
		this.adminListener 		= new AdminListener();
		
		this.dispatcher			= new EventDispatcher();
		this.EVENT_QUEUE 		= new LinkedBlockingQueue<Event>( capacity );
		this.executorService	= Executors.newSingleThreadExecutor( new MTrainThreadFactory("MTrainAsync") );

	}
	
	
	public void init( ){
		
		LOGGER.info("Initializing {}.", NAME );
		
		register( adminListener );
		keepDispatching = true;
		executorService.submit( dispatcher );
		
		LOGGER.info("{} successfully initialized.", NAME );
		LOGGER.info(" =========================================\n" );
		
	}
	
	
	public final static void register( EventListener listener ){
		registerAll( listener );
    }
	
	
	public final static void registerAll( EventListener ... listeners ){
		
		for( EventListener listener : listeners ){
			ELIST.add( listener );
			LOGGER.info("Registered event listener [{}] for events [{}].", listener.getName(), listener.getSupportedSet() );	
		}
		
    }
	
    	
	private final void checkSetup(){
		if ( !keepDispatching ) throw new IllegalStateException("init() must be called before posting events.");
	}
	
	
	public final boolean postSync( Event event ){
		
		checkSetup();
		messageCounter.incrementAndGet();
	    
	    int dispatchedCount = 0;
	    for( EventListener listener : ELIST ){
	    	if( listener.getSupportedSet().contains( event.getType() )){
	    		 listener.update( event );
	    		 ++dispatchedCount;
	    	}
	    }
		
	    if( dispatchedCount == 0 ) adminListener.update( event );
	    
		return true;
	}
	
	
	public final boolean postAsync( Event event ){
		checkSetup();
		return EVENT_QUEUE.offer( event );
	}
	
		
	public final String getStatistics(){
	    
	    StringBuilder builder  = new StringBuilder( 32 );
	    builder.append( messageCounter.get() );
	    builder.append( " events where routed through MTrain since ");
	    builder.append( new Date( MICROSECONDS.convert(startTime, NANOSECONDS)) );
	    	    
	    return builder.toString();
	}
	
	
	public final static void deregister( EventListener listener ){
		deregisterAll( listener );
    }
	
	
	public final static void deregisterAll( EventListener ... listeners ){
		
		try{
	
			for( EventListener listener : listeners ){
				Thread.sleep( 10 );
				ELIST.remove( listener );
				LOGGER.info("Deregistered event listener [{}].", listener.getName() );
			}
			
		}catch( Exception e){
			LOGGER.warn("Exception while deregistering event listener [{}].", listeners );
		}
		
    }
	
	
	public final void stop(){
		
		try{
			
			keepDispatching = false;
			executorService.awaitTermination( 2, TimeUnit.SECONDS );
			executorService.shutdownNow();
	
			LOGGER.info("Successfully stopped MTrain.\n" );
			LOGGER.info("{} ", getStatistics() );
		
		}catch( InterruptedException e ){
			LOGGER.warn("Interrupted before MTrain coluld be stopped successfully." );
			LOGGER.warn("{}", e );
		}
	
	}
	
	
	private class EventDispatcher implements Runnable{

		@Override
		public void run( ){
			
			while( keepDispatching ){
				
				try{
					
					Event event 	= EVENT_QUEUE.take();
					
				    messageCounter.incrementAndGet();
				    
				    int dispatchedCount = 0;
				    for( EventListener listener : ELIST ){
				    	if( listener.getSupportedSet().contains( event.getType() )){
				    		 listener.update( event );
				    		 ++dispatchedCount;
				    	}
				    }
					
				    if( dispatchedCount == 0 ) adminListener.update( event );
				
				}catch( InterruptedException ie ){
					LOGGER.info("Successfully shut down EventDispatcher thread.");
										
				}catch( Exception e ){
					LOGGER.warn("Exception occured while dispatching events.");
					LOGGER.warn("{} ", e);
				}
				
			}
			
		}
			
	}
	

	
}
