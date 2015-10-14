package ua.kateros.sybd.types;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Masha on 9/24/2015.
 */
public class Enumeration implements Serializable {
    private static final long serialVersionUID = 7754499745398527192L;
    private Set<String> eValues;
    private String currentValue;

    public Enumeration(String value, List<String> args) throws Exception {
        eValues = new HashSet<>();
        eValues.addAll(args);

        if (!eValues.contains(value))
        {
            throw new Exception();
        }

        currentValue = value;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public List<String> getEValues() {
        return new ArrayList<>(eValues);
    }

    public void setEValues(List<String> eValues) {
        this.eValues.clear();
        this.eValues.addAll(eValues);
    }

    @Override
    public String toString() {
        return currentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enumeration that = (Enumeration) o;

        if (!that.eValues.equals(eValues)) return false;
        return that.currentValue.equals(currentValue);
    }

    @Override
    public int hashCode() {
        return currentValue.hashCode();
    }
}


