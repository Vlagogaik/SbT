package org.Oleg;

//import junit.framework.Test;
import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;


class AppTest {
    @Test
    void Weeks(){
        Week.CustomDay dh0 = new Week.CustomDay(1, 2);
        Week.CustomDay dh1 = new Week.CustomDay(2, 2);
        Week.CustomDay dh2 = new Week.CustomDay(3, 2);
        Week.CustomDay dh3 = new Week.CustomDay(4, 2);

        dh0.nextDay();
        dh0.nextDay();
        dh0.nextDay();
        dh0.nextDay();
        dh1.nextDay();
        dh2.nextDay();
        dh3.nextDay();

        int res0 = 5;
        int res1 = 3;
        int res2 = 4;
        int res3 = 5;

        Assert.assertEquals(dh0.getDay(),res0);
        Assert.assertEquals(dh1.getDay(),res1);
        Assert.assertEquals(dh2.getDay(),res2);
        Assert.assertEquals(dh3.getDay(),res3);

    }
    @Test
    void Watchs(){
        Watch w = new Watch();
        Assert.assertEquals(w.getHour(), 12);
        w.setHour(10);
        Assert.assertEquals(w.getHour(), 10);
        w.nextHour();
        w.setDescription("OperOleg");
        Assert.assertEquals(w.getHour(), 11);
        Assert.assertEquals(w.getDescription(), "OperOleg");
        Assert.assertEquals(w.getWatchByOleg(), "OlegMangal");
        Assert.assertEquals(w.getWatchByOlegM(), "JustOleg");

    }

    @Test
    void DataContainer(){
        DataContainer data = new DataContainer();
        data.setID(16);
        data.setDescription("Oleg");
        DataContainer data0 = new DataContainer("OlegBig",18);
        Assert.assertEquals(data.getID(), 16);
        Assert.assertEquals(data.getDescription(), "Oleg");
        Assert.assertEquals(data0.getID(), 18);
        Assert.assertEquals(data0.getDescription(), "OlegBig");

    }
    @Test
    void ExtendsForNewAAnnoClass(){
        ExtendsForNewAnnoClass ex = new ExtendsForNewAnnoClass();
        ex.setDesc("OlegMain");
        ex.setID(0);
        ex.NextId(ex.getID());
        ex.AddDesc(ex.getDesc());
        ExtendsForNewAnnoClass ex1 = new ExtendsForNewAnnoClass(16, "Oleg1");

        Assert.assertEquals(ex.getID(), 1);
        Assert.assertEquals(ex.getDesc(), "OlegMainNewAdd");
        Assert.assertEquals(ex1.getDesc(), "Oleg1");
        Assert.assertEquals(ex1.getID(), 16);

    }
}
