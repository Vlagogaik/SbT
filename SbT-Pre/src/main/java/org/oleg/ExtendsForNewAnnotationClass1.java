package org.oleg;

import lombok.NonNull;

public class ExtendsForNewAnnotationClass1 extends ExtendsForNewAnnotationClass{
    public void printDescription(@NonNull DataContainer data, @NonNull String primeId){
        System.out.println("Description: " + data.getDescription() + " \n" + "ID: " + primeId);
    }

    public String descriprionsAndIdToString(@NonNull DataContainer data, @NonNull String primeId){
        return "Description: " + data.getDescription() + " ID: " + primeId;
    }
}


