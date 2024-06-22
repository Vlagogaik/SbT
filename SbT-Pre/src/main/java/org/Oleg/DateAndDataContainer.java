package org.Oleg;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DateAndDataContainer {
    private int day;
    private int month;
    private String Description;
    private int ID;

    public int NextDay(){
        return this.day += 1;
    }
    public int NextMonth(){
        return this.month += 1;
    }
    public int NextID(){
        return this.ID += 1;
    }
    public String CopyRigthDescription(){
        this.Description += " CreatedByOleg";
        return this.Description;
    }

}
