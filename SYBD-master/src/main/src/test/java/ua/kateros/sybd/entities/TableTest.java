package ua.kateros.sybd.entities;

import junit.framework.TestCase;
import ua.kateros.sybd.types.Char;
import ua.kateros.sybd.types.IntegerInterval;

public class TableTest extends TestCase {

    public void testFind() throws Exception {
        Table table1;
        Scheme scheme = new Scheme();
        Attribute attr1 = new Attribute("attr1", String.class);
        Attribute attr2 = new Attribute("attr2", Double.class);
        Attribute attr3 = new Attribute("attr3", IntegerInterval.class);
        Attribute attr4 = new Attribute("attr4", Char.class);

        scheme.addAttribute(attr1);
        scheme.addAttribute(attr2);
        scheme.addAttribute(attr3);
        scheme.addAttribute(attr4);

        table1 = new Table(scheme, "Table1");

        table1.addRecordFromStrings(new String[] {"Petrov", "4.8", "3;9", "a"});
        table1.addRecordFromStrings(new String[] {"Ivanov", "4.8", "1;2", "c"});
        table1.addRecordFromStrings(new String[] {"Sidorov", "3.4", "5;8", "d"});
        table1.addRecordFromStrings(new String[] {"Petrenko", "9.1", "3;9", "h"});
        table1.addRecordFromStrings(new String[] {"Kozlov", "1.0", "2;8", "a"});
        table1.addRecordFromStrings(new String[] {"Kovbasenko", "4.8", "1;2", "d"});
        table1.addRecordFromStrings(new String[] {"Vynokurova", "5.5", "1;1", "a"});

        Table tableRes = table1.find("4.8");
        assertEquals(3, tableRes.size());
        assertEquals("Petrov", tableRes.getRecord(0).getValues().get(0).toString());
    }

    public void testRemoveDuplicates() throws Exception {
        Table table1;
        Scheme scheme = new Scheme();
        Attribute attr1 = new Attribute("attr1", String.class);
        Attribute attr2 = new Attribute("attr2", Double.class);
        scheme.addAttribute(attr1);
        scheme.addAttribute(attr2);
        table1 = new Table(scheme, "Table1");

        table1.addRecordFromStrings(new String[] {"Hi", "2.2"});
        table1.addRecordFromStrings(new String[] {"Guten Abend", "3.4"});
        table1.addRecordFromStrings(new String[] {"Pryvit", "9.1"});
        table1.addRecordFromStrings(new String[] {"Pryvit", "9.1"});
        table1.addRecordFromStrings(new String[] {"Guten Abend", "9.1"});
        table1.addRecordFromStrings(new String[] {"Guten Abend", "3.4"});
        table1.addRecordFromStrings(new String[] {"Hello", "768.5"});

        table1.removeDuplicates();
        assertEquals(5, table1.size());

        table1.removeDuplicates();
        assertEquals(5, table1.size());

        Table table2 = new Table(scheme, "Table2");

        table2.addRecordFromStrings(new String[] {"Red", "2.2"});
        table2.addRecordFromStrings(new String[] {"Black", "3.4"});
        table2.addRecordFromStrings(new String[] {"Green", "9.1"});

        table2.removeDuplicates();
        assertEquals(3, table2.size());

        Table table3 = new Table(scheme, "Table3");
        table3.removeDuplicates();
        assertEquals(0, table3.size());
    }
}