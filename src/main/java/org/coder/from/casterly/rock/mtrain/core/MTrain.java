package org.coder.from.casterly.rock.mtrain.core;

import org.slf4j.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import org.coder.from.casterly.rock.mtrain.listener.core.*;
import org.coder.from.casterly.rock.mtrain.listener.impl.*;
import org.coder.from.casterly.rock.mtrain.messages.core.*;
import org.coder.from.casterly.rock.mtrain.messages.core.Message.*;

import static java.util.concurrent.TimeUnit.*;


public final class MTrain{

	private volatile boolean keepDispatching;
	
	private final long startTime;
	private final Runnable dispatcher;
	private final AdminListener adminListener;
	private final AtomicInteger messageCounter;
	
	private final ExecutorService executorService;
	private final BlockingQueue<Message> MESSAGE_QUEUE;
			
	private final static String NAME 						     	= MTrain.class.getSimpleName();
	private final static Logger LOGGER 						     	= LoggerFactory.getLogger( NAME );
	private final static CopyOnWriteArrayList<MListener> ELIST= new CopyOnWriteArrayList<MListener>( );
	
	
	public MTrain(  ){
		this( 64 );
	}
	

	public MTrain( int capacity ){
	    
		this.startTime          = System.nanoTime();
		this.messageCounter     = new AtomicInteger();
		this.adminListener 		= new AdminListener();
		
		this.dispatcher			= new EventDispatcher();
		this.MESSAGE_QUEUE 		= new LinkedBlockingQueue<Message>( capacity );
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
	
	
	public final static void register( MListener listener ){
		registerAll( listener );
    }
	
	
	public final static void registerAll( MListener ... listeners ){
		for( MListener listener : listeners ){
			ELIST.add( listener );
			LOGGER.debug("Registered [{}] for message/s [{}].", listener.getName(), listener.getSupportedMessages() );	
		}
    }
		
	
	public final boolean postSync( Message event ){
		
		int dispatchedCount = 0;
		MessageType type	= event.getType();
	    
	    for( MListener listener : ELIST ){
	    	if( listener.isSupported( type ) ){
	    		 listener.update( event );
	    		 ++dispatchedCount;
	    	}
	    }
		
	    if( dispatchedCount == 0 ){
	    	adminListener.update( event );
	    	return false;
	    }
	    
	    messageCounter.incrementAndGet();
	    return true;
	    
	}
	
	
	public final boolean postAsync( Message event ){
		return MESSAGE_QUEUE.offer( event );
	}
	
		
	public final String getStatistics(){
	    
	    StringBuilder builder  = new StringBuilder( 32 );
	    builder.append( messageCounter.get() );
	    builder.append( " events where routed through MTrain since ");
	    builder.append( new Date( MICROSECONDS.convert(startTime, NANOSECONDS)) );
	    	    
	    return builder.toString();
	}
	
	
	public final static void deregister( MListener listener ){
		deregisterAll( listener );
    }
	
	
	public final static void deregisterAll( MListener ... listeners ){
		
		try{
	
			for( MListener listener : listeners ){
				Thread.sleep( 10 );
				ELIST.remove( listener );
				LOGGER.debug("Deregistered message listener [{}].", listener.getName() );
			}
			
		}catch( Exception e){
			LOGGER.warn("Exception while deregistering message listener [{}].", listeners );
		}
		
    }
	
	
	public final void stop(){
		
		try{
			
			keepDispatching = false;
			executorService.awaitTermination( 2, TimeUnit.SECONDS );
			executorService.shutdownNow();
	
			LOGGER.info("=========================================" );
			LOGGER.info("Successfully stopped MTrain." );
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
					
					Message event 		= MESSAGE_QUEUE.take();
					MessageType type	= event.getType();
					
					int dispatchedCount = 0;
				   				    
				    for( MListener listener : ELIST ){
				    	if( listener.isSupported( type ) ){
				    		 listener.update( event );
				    		 ++dispatchedCount;
				    	}
				    }
					
				    if( dispatchedCount == 0 ){
				    	adminListener.update( event );
				    	continue;
				    }
				    
				    messageCounter.incrementAndGet();
				
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
