package org.Oleg;
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
        private int hour;
        public CustomDay() {
            super();
            this.hour = hour;
        }
        public CustomDay(int day, int hour) {
            super(day);
            this.hour = hour;
        }
    }

}