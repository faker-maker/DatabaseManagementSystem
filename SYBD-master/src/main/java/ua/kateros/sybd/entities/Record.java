package ua.kateros.sybd.entities;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Record implements Serializable {
	private static final long serialVersionUID = 3420386175463426958L;
	private List<Object> values;

	public Record(List<Object> values) {
		this.values = values;
	}

	public List<?> getValues() {
		return values;
	}

	public static Record makeFromStrings(String[] strings, Scheme scheme) throws Exception {
		List<Object> result = new ArrayList<>();
		for (int i = 0; i < strings.length; i++) {
			String value = strings[i];
			Class clazz = scheme.getAttribute(i).getClazz();

            Object instance;
            List<String> args = scheme.getAttribute(i).getArgs();
            if (args != null)
            {
                Constructor constructor = clazz.getDeclaredConstructor(String.class, List.class);
                instance = constructor.newInstance(value, args);
            }
            else
            {
                Constructor constructor = clazz.getDeclaredConstructor(String.class);
                instance = constructor.newInstance(value);
            }

            result.add(instance);
		}
		return new Record(result);
	}

    public void addValue(String value, Attribute attr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = attr.getClazz();

        Object instance;
        List<String> args = attr.getArgs();
        if (args != null)
        {
            Constructor constructor = clazz.getDeclaredConstructor(String.class, List.class);
            instance = constructor.newInstance(value, args);
        }
        else
        {
            Constructor constructor = clazz.getDeclaredConstructor(String.class);
            instance = constructor.newInstance(value);
        }

        values.add(instance);
    }

	public boolean contains(String what) {
		for (Object value : values) {
			if (value.toString().contains(what)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Record record = (Record) o;
		for (int i = 0; i < values.size(); i++) {
			if (!values.get(i).equals(record.getValues().get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return values.hashCode();
	}
}
