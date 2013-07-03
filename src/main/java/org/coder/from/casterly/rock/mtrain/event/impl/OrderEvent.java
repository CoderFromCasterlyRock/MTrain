package org.coder.from.casterly.rock.mtrain.event.impl;

import org.coder.from.casterly.rock.mtrain.event.core.Event;
import org.coder.from.casterly.rock.mtrain.event.core.EventType;

public final class OrderEvent implements Event{
	
	private final EventType type;
	private final long orderId;
	private final String instrumentId;
	private final double price;
	private final int quantity;	
	private final String side;
	
	public OrderEvent( EventType type, long orderId, String instrumentId, double price, int quantity, String side ){
		this.type 			= type;
		this.orderId 		= orderId;
		this.instrumentId 	= instrumentId;
		this.price 			= price;
		this.quantity 		= quantity;
		this.side 			= side;	
	}
	
	
	@Override
	public final EventType getType(){
		return type;
	}
	
	
	public final long getOrderId() {
		return orderId;
	}

	
	public final String getInstrumentId() {
		return instrumentId;
	}

	
	public final double getPrice() {
		return price;
	}

	
	public final int getQuantity() {
		return quantity;
	}

	
	public final String getSide() {
		return side;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder( 32 );
		builder.append("StringEvent [Type=").append( getType() );
		builder.append(", OrderId=").append( orderId );
		builder.append(", Instrument=").append( instrumentId );
		builder.append(", Side=").append( side );
		builder.append(", Price=").append( price );
		builder.append(", Quantity=").append( quantity );
		builder.append("]");
		
		return builder.toString();
	}

	

}
