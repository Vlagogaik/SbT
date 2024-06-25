package org.oleg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static junit.framework.Assert.*;

class AppTest {
    @Test
    void WeeksTest(){
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

        assertEquals(dh0.getDay(),res0);
        assertEquals(dh1.getDay(),res1);
        assertEquals(dh2.getDay(),res2);
        assertEquals(dh3.getDay(),res3);

    }
    @Test
    void WatchsTest(){
        Watch w = new Watch();
        assertEquals(w.getHour(), 12);
        w.setHour(10);
        assertEquals(w.getHour(), 10);
        w.nextHour();
        w.setDescription("OperOleg");
        assertEquals(w.getHour(), 11);
        assertEquals(w.getDescription(), "OperOleg");
        assertEquals(w.getWatchByOleg(), "OlegMangal");
        assertEquals(w.getWatchByOlegM(), "JustOleg");

    }

    @Test
    void DataContainerTest(){
        DataContainer data = new DataContainer();
        data.setID(16);
        data.setDescription("Oleg");
        DataContainer data0 = new DataContainer("OlegBig",18);
        assertEquals(data.getID(), 16);
        assertEquals(data.getDescription(), "Oleg");
        assertEquals(data0.getID(), 18);
        assertEquals(data0.getDescription(), "OlegBig");

    }
    @Test
    void ExtendsForNewAAnnoClassTest(){
        ExtendsForNewAnnoClass ex = new ExtendsForNewAnnoClass();
        ex.setDesc("OlegMain");
        ex.setID(0);
        ex.NextId(ex.getID());
        ex.AddDesc(ex.getDesc());
        ExtendsForNewAnnoClass ex1 = new ExtendsForNewAnnoClass(16, "Oleg1");

        assertEquals(ex.getID(), 1);
        assertEquals(ex.getDesc(), "OlegMainNewAdd");
        assertEquals(ex1.getDesc(), "Oleg1");
        assertEquals(ex1.getID(), 16);

    }

    @Test
    void NotNullTest(){

        ExtendsForNewAnnoClass.Ex1 e1 = new ExtendsForNewAnnoClass.Ex1();
        DataContainer dataContainer2 = new DataContainer();
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            assertEquals(e1.DescriprionsAndIDToString(dataContainer2, null), "Description: null" + " ID: null");
                });
        assertEquals(e1.DescriprionsAndIDToString(dataContainer2, "null"), "Description: null" + " ID: null");
    }
    @Test
    void CreateClassTest() throws NoSuchFieldException, IllegalAccessException {
        Week w = new Week();
        DataContainer dataContainerR = new DataContainer("Oleg", 18);
        Object clazz = w.CreateClassWithDataContainerAndWeek(16, 5, dataContainerR);
        assertNotNull(clazz);
        assertTrue(clazz instanceof DateAndDataContainer);
        DateAndDataContainer newClazz = (DateAndDataContainer) clazz;
        assertEquals("Oleg", newClazz.getDescription());
        assertEquals(18, newClazz.getID());

        // Тут задача 2
        assertEquals(6, newClazz.NextMonth());
        assertEquals(newClazz.NextDay(),17);
        assertEquals(newClazz.CopyRigthDescription(),"Oleg CreatedByOleg");
        assertEquals(newClazz.NextID(),19);

        //Тут задача 3
        Field monthField = DateAndDataContainer.class.getDeclaredField("month");
        monthField.setAccessible(true);
        assertEquals(6, monthField.getInt(newClazz));

        Field dayField = DateAndDataContainer.class.getDeclaredField("day");
        dayField.setAccessible(true);
        assertEquals(17, dayField.getInt(newClazz));

        Field descriptionField = DateAndDataContainer.class.getDeclaredField("Description");
        descriptionField.setAccessible(true);
        assertEquals("Oleg CreatedByOleg", descriptionField.get(newClazz));

        Field IDField = DateAndDataContainer.class.getDeclaredField("ID");
        IDField.setAccessible(true);
        assertEquals(19, IDField.getInt(newClazz));

    }
}
