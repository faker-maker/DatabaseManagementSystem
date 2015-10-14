package ua.kateros.sybd.types;

import java.io.Serializable;

/**
 * Created by Masha on 9/24/2015.
 */
public class Char implements Serializable {
    private static final long serialVersionUID = 7754499745398527191L;
    private char value;

    public Char() {
        this.value = ' ';
    }

    public Char(String value) {
        if (value.isEmpty())
            this.value = ' ';
        else
            this.value = value.charAt(0);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Char that = (Char) o;

        return that.value == value;
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


