package org.Oleg;

public class App
{
    public static void main( String[] args ) {
        //Просто проверка @Inherited в DayDefault
        Week.CustomDay dh = new Week.CustomDay();
        boolean isInherited = Week.CustomDay.class.isAnnotationPresent(DayDefault.class);
        dh.nextDay();
        System.out.println(isInherited);
        System.out.println(dh.getDay());
        System.out.println("<================>\n");

        //Проверка @Retention в AWatch
        Watch watch = new Watch();
        System.out.println("Current hour: " + watch.getHour());
        System.out.println("Description: " + watch.getDescription());
        System.out.println("Description: " + watch.getWatchByOleg());
        System.out.println("Description: " + watch.getWatchByOlegM());
        System.out.println("<================>\n");

        //Провека DataContainer
        DataContainer data = new DataContainer();
        data.setID(16);
        data.setDescription("Oleg");
        DataContainer data0 = new DataContainer("OlegBig",18);
        System.out.println("Data0 ID: " + data0.getID());
        System.out.println("Data0 Description: " + data0.getDescription());
        System.out.println("<================>\n");

        //Проверка @NewAnnoClass
        ExtendsForNewAnnoClass ex = new ExtendsForNewAnnoClass();
        ex.setDesc("OlegMain");
        ex.setID(0);
        ex.NextId(ex.getID());
        ex.AddDesc(ex.getDesc());
        ex.displayInfo();
        ExtendsForNewAnnoClass outer = new ExtendsForNewAnnoClass(16, "OlegEx2");
        ExtendsForNewAnnoClass.Ex2 ex1 = outer.new Ex2();
        ex1.displayInfo();
        System.out.println("<================>\n");
    }
}
