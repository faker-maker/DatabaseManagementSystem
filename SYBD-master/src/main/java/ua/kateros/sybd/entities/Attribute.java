package ua.kateros.sybd.entities;

import java.io.Serializable;
import java.util.List;

public class Attribute implements Serializable {
    private static final long serialVersionUID = 7754499745398527163L;
	private String name;
    private Class clazz;
    private List<String> args;

    public Attribute(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
        this.args = null;
    }

    public Attribute(String name, String className) throws ClassNotFoundException {
        this.name = name;
        this.clazz = Class.forName(className);
        this.args = null;
    }

    public Attribute(String name, Class className, List<String> args) throws Exception {
        this.name = name;
        this.clazz = className;
        this.args = args;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public Class getClazz() {
        return clazz;
    }

    public List<String> getArgs() { return args; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        if (clazz != null ? !clazz.equals(attribute.clazz) : attribute.clazz != null) return false;
        if (name != null ? !name.equals(attribute.name) : attribute.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
    	return name + " : " + clazz.toString();
    }
}
