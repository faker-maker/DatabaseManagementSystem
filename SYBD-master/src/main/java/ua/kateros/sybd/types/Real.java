package ua.kateros.sybd.types;

import java.io.Serializable;

/**
 * Created by Masha on 9/28/2015.
 */
public class Real implements Serializable {
    private static final long serialVersionUID = 7754499745398527195L;
    private double value;

    public Real() {
        value = 0;
    }

    public Real(String strValue) {
        value = Double.parseDouble(strValue);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Real that = (Real) o;

        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> 32));
        return result;
    }
}
