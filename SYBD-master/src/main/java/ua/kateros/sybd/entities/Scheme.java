package ua.kateros.sybd.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scheme implements Serializable {
    private static final long serialVersionUID = -6945380943878569883L;
	private List<Attribute> attributes;

    public Scheme() {
        attributes = new ArrayList<>();
    }

    public Scheme(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
    }

    public void removeAttribute(Integer attrNumber) {
        this.attributes.remove(attrNumber);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<String> getAttributesNames()
    {
        List<String> names = new ArrayList<>();
        for (Attribute a : attributes)
        {
            names.add(a.getName());
        }

        return names;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Attribute getAttribute(int i) {
        return attributes.get(i);
    }

    public boolean hasSameTypesAs(Scheme anotherScheme) {
        for (int i = 0; i < attributes.size(); i++) {
            if (!(attributes.get(i).getClazz().equals(anotherScheme.getAttribute(i).getClazz()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scheme scheme = (Scheme) o;

        for (int i = 0; i < attributes.size(); i++) {
            if (!(attributes.get(i).equals(scheme.getAttribute(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return attributes.hashCode();
    }

	public String[] getStringRepresentation() {
		List<String> model = new ArrayList<>();
		for(Attribute attr : this.attributes) {
			model.add(attr.getName() + " : " + attr.getClazz().toString());
		}
		return model.toArray(new String[model.size()]);
	}
}
