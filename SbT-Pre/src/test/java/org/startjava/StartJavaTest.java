package org.startjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;

class StartJavaTest {
    @ParameterizedTest
    @MethodSource("provideCustomDayAndExpectedResults")
    void weeksTest(Week.CustomDay customDay, int expectedResult) {
        customDay.nextDay();
        assertEquals(customDay.getDay(), expectedResult);
    }

    private static Stream<Object[]> provideCustomDayAndExpectedResults() {
        return Stream.of(
                new Object[]{new Week.CustomDay(1, 2), 2},
                new Object[]{new Week.CustomDay(2, 2), 3},
                new Object[]{new Week.CustomDay(3, 2), 4},
                new Object[]{new Week.CustomDay(4, 2), 5}
        );
    }

    @ParameterizedTest
    @MethodSource("provideWatchStates")
    void watchsTest(Watch w, int expectedHour, String description, String watchByOleg, String watchByOlegM) {
        assertEquals(12, w.getHour());

        w.setHour(10);
        assertEquals(10, w.getHour());

        w.nextHour();
        w.setDescription(description);

        assertEquals(11, w.getHour());
        assertEquals(description, w.getDescription());
        assertEquals(watchByOleg, w.getWatchByOleg());
        assertEquals(watchByOlegM, w.getWatchByOlegM());
    }

    private static Stream<Arguments> provideWatchStates() {
        return Stream.of(
                Arguments.of(new Watch(), 12, "OperOleg", "OlegMangal", "JustOleg")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataContainers")
    void dataContainerTest(DataContainer data, int expectedId, String expectedDescription) {
        assertEquals(expectedId, data.getId());
        assertEquals(expectedDescription, data.getDescription());
    }

    private static Stream<Arguments> provideDataContainers() {
        return Stream.of(
            Arguments.of(new DataContainer("Oleg",16), 16, "Oleg"),
            Arguments.of(new DataContainer("OlegBig", 18), 18, "OlegBig")
        );
    }
    @ParameterizedTest
    @MethodSource("provideExtendsForNewAnnotationClasses")
    void extendsForNewAnnotationClassTest(ExtendsForNewAnnotationClass ex, int expectedId, String expectedDescription) {
        ex.nextId(ex.getId());
        ex.addDescription(ex.getDescription());

        assertEquals(expectedId, ex.getId());
        assertEquals(expectedDescription, ex.getDescription());
    }

    private static Stream<Arguments> provideExtendsForNewAnnotationClasses() {
        return Stream.of(
                Arguments.of(new ExtendsForNewAnnotationClass(0, "OlegMain"), 1, "OlegMainNewAdd"),
                Arguments.of(new ExtendsForNewAnnotationClass(16, "Oleg1"), 17, "Oleg1NewAdd")
        );
    }

    @ParameterizedTest
    @MethodSource("provideNotNullTestCases")
    void notNullTest(ExtendsForNewAnnotationClass1 ex1, DataContainer dataContainer2, String description, String expectedResult) {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            assertEquals(expectedResult, ex1.descriprionsAndIdToString(dataContainer2, null));
        });
        assertEquals(expectedResult, ex1.descriprionsAndIdToString(dataContainer2, description));
    }

    private static Stream<Arguments> provideNotNullTestCases() {
        return Stream.of(
            Arguments.of(new ExtendsForNewAnnotationClass1(), new DataContainer(), "null", "Description: null ID: null")
        );
    }

    @ParameterizedTest
    @MethodSource("provideCreateClassTestCases")
    void createClassTest(Week w, DataContainer dataContainer, int month, int day, String description, int id) throws NoSuchFieldException, IllegalAccessException {
        Object clazz = w.createClassWithDataContainerAndWeek(16, 5, dataContainer);
        assertNotNull(clazz);
        assertTrue(clazz instanceof DateAndDataContainer);
        DateAndDataContainer newClazz = (DateAndDataContainer) clazz;
        assertEquals("Oleg", newClazz.getDescription());
        assertEquals(18, newClazz.getId());

        assertEquals(6, newClazz.nextMonth());
        assertEquals(17, newClazz.nextDay());
        assertEquals("Oleg CreatedByOleg", newClazz.copyRigthDescription());
        assertEquals(19, newClazz.nextId());

        Field monthField = DateAndDataContainer.class.getDeclaredField("month");
        monthField.setAccessible(true);
        assertEquals(month, monthField.getInt(newClazz));

        Field dayField = DateAndDataContainer.class.getDeclaredField("day");
        dayField.setAccessible(true);
        assertEquals(day, dayField.getInt(newClazz));

        Field descriptionField = DateAndDataContainer.class.getDeclaredField("description");
        descriptionField.setAccessible(true);
        assertEquals(description, descriptionField.get(newClazz));

        Field IDField = DateAndDataContainer.class.getDeclaredField("id");
        IDField.setAccessible(true);
        assertEquals(id, IDField.getInt(newClazz));
    }

    private static Stream<Arguments> provideCreateClassTestCases() {
        return Stream.of(
                Arguments.of(new Week(), new DataContainer("Oleg", 18), 6, 17, "Oleg CreatedByOleg", 19)
        );
    }
}
