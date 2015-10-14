package ua.kateros.sybd.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecordTest {
    private Record record;

    @Before
    public void initRecord() {
        List<Object> values = new ArrayList<>();
        values.add(0.0);
        values.add(1L);
        values.add("Hi");
        record = new Record(values);
    }

    @Test
    public void testContainsString() throws Exception {
        assertEquals(true, record.contains("Hi"));
    }

    @Test
    public void testEqualsTrue() {
        List<Object> values = new ArrayList<>();
        values.add(0.0);
        values.add(1L);
        values.add("Hi");
        Record another = new Record(values);
        assertEquals(true, record.equals(another));
    }
}