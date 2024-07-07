package org.context.task2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DataBase {
    private int id;
    private String phoneNumber;
    private double amount;

    public void save(DataBase data){
        //Выглядит как реализация сохранения данных в базу!
    }
}
