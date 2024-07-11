package org.startjava;

@NewAnnotationClass(id = 2, description = "Oleg2")
public class ExtendsForNewAnnotationClass0 extends ExtendsForNewAnnotationClass{
    @NewRepetableAnnotation(id = 200, description = "Method 1")
    @NewRepetableAnnotation(id = 201, description = "Method 2")
    public void printDescription(DataContainer data){
        if(data.getDescription() == null){
            System.out.println("Print Default data:");
            NewRepetableAnnotation[] annotations = null;
            try {
                annotations = this.getClass().getMethod("printDescription", DataContainer.class).getAnnotationsByType(NewRepetableAnnotation.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Description: " +annotations[0].description());
            System.out.println("Description: " +annotations[1].description() + "\n");
        }else{
            System.out.println("Description: " + data.getDescription() + " \n");
        }
    }

    public void printId(DataContainer data) {
        if(data.getId() == 0){
            System.out.println("Print Default data:");
            NewRepetableAnnotation[] annotations = null;
            try {
                annotations = this.getClass().getMethod("printDescription", DataContainer.class).getAnnotationsByType(NewRepetableAnnotation.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ID: " +annotations[0].id());
            System.out.println("ID: " +annotations[1].id() + " \n");
        }else{
            System.out.println("ID: " + data.getId() + " \n");
        }
    }
}
