package org.contex;

public class InvalidTransferException extends Exception{

    public InvalidTransferException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
