package org.Oleg;

public class App
{
    public static void main( String[] args ) {
        Week.CustomDay dh = new Week.CustomDay();
        boolean isInherited = Week.CustomDay.class.isAnnotationPresent(DayDefault.class);
        dh.nextDay();
        System.out.println(isInherited);
        System.out.println(dh.getDay());

        Watch watch = new Watch();
        System.out.println("Current hour: " + watch.getHour());
        System.out.println("Description: " + watch.getDescription());
        System.out.println("Description: " + watch.getWatchByOleg());
        System.out.println("Description: " + watch.getWatchByOlegM());


    }
}
