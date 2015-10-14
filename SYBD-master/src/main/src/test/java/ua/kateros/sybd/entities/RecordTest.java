package ua.kateros.sybd.entities;

import junit.framework.TestCase;
import ua.kateros.sybd.types.Enumeration;
import ua.kateros.sybd.types.IntegerInterval;

import java.util.ArrayList;
import java.util.List;

public class RecordTest extends TestCase {
    private Record record;

    public void setUp() throws Exception {
        List<Object> values = new ArrayList<>();
        values.add(0.0);
        values.add(1L);
        values.add("Hi");
        record = new Record(values);
    }

    public void testMakeFromStrings() throws Exception {
        Scheme scheme = new Scheme();
        Attribute attr1 = new Attribute("attr1", String.class);
        Attribute attr2 = new Attribute("attr2", Double.class);

        List<String> args = new ArrayList<>();
        args.add("red");
        args.add("black");
        args.add("green");
        Attribute attr3 = new Attribute("attr3", Enumeration.class, args);
        Attribute attr4 = new Attribute("attr4", IntegerInterval.class);

        scheme.addAttribute(attr1);
        scheme.addAttribute(attr2);
        scheme.addAttribute(attr3);
        scheme.addAttribute(attr4);

        Record res = record.makeFromStrings(new String[] {"Petrov", "3.7", "red", "5;9"}, scheme);
        assertEquals(4, res.getValues().size());
        assertEquals("Petrov", res.getValues().get(0).toString());
        assertEquals("3.7", res.getValues().get(1).toString());
        assertEquals("red", res.getValues().get(2).toString());
        assertEquals("5;9", res.getValues().get(3).toString());
    }

    public void testContains() throws Exception {
        assertTrue(record.contains("Hi"));
        assertFalse(record.contains("Hello"));
    }

    public void testEquals() throws Exception {
        List<Object> values = new ArrayList<>();
        values.add(0.0);
        values.add(1L);
        values.add("Hi");
        Record another = new Record(values);
        assertEquals(true, record.equals(another));
    }
}