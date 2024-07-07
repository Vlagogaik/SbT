package org.startjava;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DateAndDataContainer {
    private int day;
    private int month;
    private String description;
    private int id;
    public int nextDay(){
        return this.day += 1;
    }

    public int nextMonth(){
        return this.month += 1;
    }

    public int nextId(){
        return this.id += 1;
    }

    public String copyRigthDescription(){
        this.description += " CreatedByOleg";
        return this.description;
    }

}
