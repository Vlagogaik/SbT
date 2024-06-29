package org.startjava;

@NewAnnotationClass(id = 4, description = "Oleg4")
public class ExtendsForNewAnnotationClass2 extends ExtendsForNewAnnotationClass{
    @Override
    public void displayInfo() {
        ExtendsForNewAnnotationClass ex = new ExtendsForNewAnnotationClass();
        System.out.println("Ex2 info:");
        System.out.println("ID: " + ex.getId());
        System.out.println("Desc: " + ex.getDescription());
    }
}
