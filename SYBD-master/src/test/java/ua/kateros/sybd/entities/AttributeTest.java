package ua.kateros.sybd.entities;

import org.junit.Assert;
import org.junit.Test;

public class AttributeTest {

    @Test
    public void testEquals() throws Exception {
        Attribute firstAttribute = new Attribute("Price", Integer.class);
        Attribute secondAttribute = new Attribute("Price", "java.lang.Integer");
        Attribute thirdAttribute = new Attribute("NotAPrice", Integer.class);
        Attribute fourthAttribute = new Attribute("Price", "java.lang.Double");
        Assert.assertEquals(firstAttribute, secondAttribute);
        Assert.assertNotEquals(firstAttribute, thirdAttribute);
        Assert.assertNotEquals(firstAttribute, fourthAttribute);
    }

    @Test
    public void testHashCode() throws Exception {
        Attribute firstAttribute = new Attribute("Price", Integer.class);
        Attribute secondAttribute = new Attribute("Price", "java.lang.Integer");
        Attribute thirdAttribute = new Attribute("NotAPrice", Integer.class);
        Attribute fourthAttribute = new Attribute("Price", "java.lang.Double");
        Assert.assertEquals(firstAttribute.hashCode(), secondAttribute.hashCode());
        Assert.assertNotEquals(firstAttribute.hashCode(), thirdAttribute.hashCode());
        Assert.assertNotEquals(firstAttribute.hashCode(), fourthAttribute.hashCode());
    }
}
