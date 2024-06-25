package org.oleg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NonNull;

@NewAnnoClass(ID = 1, Desc = "Oleg1")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExtendsForNewAnnoClass {
    private int ID;
    private String Desc;

    public int NextId(int ID){
        this.ID = ID + 1;
        return this.ID;
    }
    public String AddDesc(String Desc){
        this.Desc = Desc + "NewAdd";
        return this.Desc;
    }

    public void displayInfo() {
        System.out.println("Base class info:");
        System.out.println("ID: " + ID);
        System.out.println("Desc: " + Desc);
    }

    @NewAnnoClass(ID = 2, Desc = "Oleg2")
    public class Ex0 extends ExtendsForNewAnnoClass{
        @NewRepetableAnno(ID = 200, Desc = "Method 1")
        @NewRepetableAnno(ID = 201, Desc = "Method 2")

        public void PrintDescription(DataContainer data){
            if(data.getDescription() == null){
                System.out.println("Print Default data:");
                NewRepetableAnno[] annotations = null;
                try {
                    annotations = this.getClass().getMethod("PrintDescription", DataContainer.class).getAnnotationsByType(NewRepetableAnno.class);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Description: " +annotations[0].Desc());
                System.out.println("Description: " +annotations[1].Desc() + "\n");
            }else{
                System.out.println("Description: " + data.getDescription() + " \n");
            }
        }

        public void PrintID(DataContainer data) {
            if(data.getID() == 0){
                System.out.println("Print Default data:");
                NewRepetableAnno[] annotations = null;
                try {
                    annotations = this.getClass().getMethod("PrintDescription", DataContainer.class).getAnnotationsByType(NewRepetableAnno.class);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("ID: " +annotations[0].ID());
                System.out.println("ID: " +annotations[1].ID() + " \n");
            }else{
                System.out.println("ID: " + data.getID() + " \n");
            }
        }

    }
    //Тут использую NotNull
//    @NewAnnoClass(ID = 3, Desc = "Oleg3")
    public static class Ex1 extends ExtendsForNewAnnoClass{

        public void PrintDescription(@NonNull DataContainer data, @NonNull String PrimeID){
                System.out.println("Description: " + data.getDescription() + " \n" + "ID: " + PrimeID);

        }
        public String DescriprionsAndIDToString(@NonNull DataContainer data, @NonNull String PrimeID){
            return "Description: " + data.getDescription() + " ID: " + PrimeID;
        }

    }
    @NewAnnoClass(ID = 4, Desc = "Oleg4")

    public class Ex2 extends ExtendsForNewAnnoClass{

        @Override
        public void displayInfo() {
            System.out.println("Ex2 info:");
            System.out.println("ID: " + ID);
            System.out.println("Desc: " + Desc);
        }

    }
}
