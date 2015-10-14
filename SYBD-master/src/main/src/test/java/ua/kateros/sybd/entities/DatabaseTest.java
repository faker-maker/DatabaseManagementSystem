package ua.kateros.sybd.entities;

import junit.framework.TestCase;
import ua.kateros.sybd.types.Char;
import ua.kateros.sybd.types.IntegerInterval;

public class DatabaseTest extends TestCase {
    private Database db;

    public void setUp() throws Exception {
        db = new Database("db1");

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
        db.addTable(table1);
    }

    public void testAddTable() throws Exception {
        assertEquals(1, db.getTables().size());

        Table table2;
        Scheme scheme = new Scheme();
        Attribute attr1 = new Attribute("attr1", Double.class);

        scheme.addAttribute(attr1);

        table2 = new Table(scheme, "Table2");
        db.addTable(table2);

        assertEquals(2, db.getTables().size());
        assertEquals("Table1", db.getTablesNames()[0]);
        assertEquals("Table2", db.getTablesNames()[1]);

        db.deleteTableWithName("Table2");
        assertEquals(1, db.getTables().size());
    }

    public void testDeleteTableWithName() throws Exception {
        assertEquals(1, db.getTables().size());
        db.deleteTableWithName("Table1");
        assertEquals(0, db.getTables().size());
    }
}