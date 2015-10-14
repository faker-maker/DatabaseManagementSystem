package ua.kateros.sybd.types;

import java.io.Serializable;

/**
 * Created by Masha on 9/23/2015.
 */

public class IntegerInterval implements Serializable {
    private static final long serialVersionUID = 7754499745398527194L;
    private int fromPoint;
    private int toPoint;

    public IntegerInterval() {
        fromPoint = 0;
        toPoint = 0;
    }

    public IntegerInterval(String interval) throws Exception {
        String[] str = interval.split(";");
        fromPoint = Integer.parseInt(str[0]);
        toPoint = Integer.parseInt(str[1]);

        if (fromPoint > toPoint)
            throw new Exception();
    }

    public Integer getFromPoint() {
        return fromPoint;
    }

    public void setFromPoint(int fromPoint) {
        this.fromPoint = fromPoint;
    }

    public Integer getToPoint() {
        return toPoint;
    }

    public void setToPoint(int toPoint) {
        this.toPoint = toPoint;
    }

    @Override
    public String toString() {
        return fromPoint + ";" + toPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerInterval that = (IntegerInterval) o;

        if (Integer.compare(that.fromPoint, fromPoint) != 0) return false;
        return Integer.compare(that.toPoint, toPoint) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = fromPoint;
        result = (int) (temp ^ (temp >>> 32));
        temp = toPoint;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

