package ua.kateros.sybd.types;

import java.io.Serializable;

/**
 * Created by Masha on 9/27/2015.
 */
public class Int implements Serializable {
    private static final long serialVersionUID = 7754499745398527193L;
    private int value;

    public Int() {
        value = 0;
    }

    public Int(String strValue) {
        value = Integer.parseInt(strValue);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Int that = (Int) o;

        return Integer.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = value;
        result = (int) (temp ^ (temp >>> 32));
        return result;
    }
}