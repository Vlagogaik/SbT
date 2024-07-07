package org.startjava;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NewAnnotationClass(id = 1, description = "Oleg1")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExtendsForNewAnnotationClass {
    private int id;
    private String description;
    public int nextId(int id){
        this.id = id + 1;
        return this.id;
    }

    public String addDescription(String description){
        this.description = description + "NewAdd";
        return this.description;
    }

    public void displayInfo() {
        System.out.println("Base class info:");
        System.out.println("ID: " + id);
        System.out.println("Desc: " + description);
    }
}
