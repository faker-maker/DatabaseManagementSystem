package ua.kateros.sybd.entities;

import junit.framework.TestCase;

public class AttributeTest extends TestCase {

    public void testEquals() throws Exception {
        Attribute firstAttribute = new Attribute("Price", Integer.class);
        Attribute secondAttribute = new Attribute("Price", "java.lang.Integer");
        Attribute thirdAttribute = new Attribute("NotAPrice", Integer.class);
        Attribute fourthAttribute = new Attribute("Price", "java.lang.Double");
        assertEquals(firstAttribute, secondAttribute);
        assertNotSame(firstAttribute, thirdAttribute);
        assertNotSame(firstAttribute, fourthAttribute);
    }

    public void testHashCode() throws Exception {
        Attribute firstAttribute = new Attribute("Price", Integer.class);
        Attribute secondAttribute = new Attribute("Price", "java.lang.Integer");
        Attribute thirdAttribute = new Attribute("NotAPrice", Integer.class);
        Attribute fourthAttribute = new Attribute("Price", "java.lang.Double");
        assertEquals(firstAttribute.hashCode(), secondAttribute.hashCode());
        assertNotSame(firstAttribute.hashCode(), thirdAttribute.hashCode());
        assertNotSame(firstAttribute.hashCode(), fourthAttribute.hashCode());
    }
}