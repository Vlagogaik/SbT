package org.oleg;

import java.lang.reflect.Constructor;

@DayDefault
public class Week {
    private int day;
    public Week(int day) {
        this.day = day;
    }

    public Week() {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int nextDay(){
        day += 1;
        return day;
    }

    public static class CustomDay extends Week {
        private int month;
        public CustomDay() {
            super();
            this.month = month;
        }
        public CustomDay(int day, int month) {
            super(day);
            this.month = month;
        }
    }

    // Тут метод, получающий на вход дату и данные с типами полей из класса DataContainer и исходя из нее создает нужные экземпляры классов.(Reflections)
    public Object createClassWithDataContainerAndWeek(int day, int month, DataContainer dataContainer){
        String Desc = dataContainer.getDescription();
        int ID = dataContainer.getId();
        try {
            Class<?> clazz = Class.forName("org.oleg.DateAndDataContainer");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();

            for (Constructor<?> constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == 4 &&
                        parameterTypes[0].equals(int.class) &&
                        parameterTypes[1].equals(int.class) &&
                        parameterTypes[2].equals(String.class) &&
                        parameterTypes[3].equals(int.class)
                )
                {
                    return constructor.newInstance(day, month, Desc, ID);
                }
            }
            throw new IllegalArgumentException("Error: Can`t create Class");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}