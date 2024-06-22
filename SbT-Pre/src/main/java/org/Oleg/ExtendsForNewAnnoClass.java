package org.Oleg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    }
    @NewAnnoClass(ID = 3, Desc = "Oleg3")
    public class Ex1 extends ExtendsForNewAnnoClass{


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
