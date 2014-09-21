package com.epam.travel.exceptions;

/**
 * @author Andrei Sauchanka
 */
public class TravelException  extends Exception{

    private static final long serialVersionUID = 1L;

   	public TravelException() {
   		super();
   	}

   	public TravelException(String message, Throwable cause) {
   		super(message, cause);
   	}

   	public TravelException(String message) {
   		super(message);
   	}

   	public TravelException(Throwable cause) {
   		super(cause);
   	}

}
