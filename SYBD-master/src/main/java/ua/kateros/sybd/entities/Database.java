package ua.kateros.sybd.entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
	private static final long serialVersionUID = 7312933757232149922L;
	private List<Table> tables;
	private String name;

	public Database(String name) {
		tables = new ArrayList<>();
		this.name = name;
	}

	public void addTable(Table table) {
		for (Table tab : tables) {
			if (tab.getName().equals(table.getName())) {
				return;
			}
		}
		tables.add(table);
	}

	public List<Table> getTables() {
		return tables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Table getTableWithName(String tableName) {
		for (Table table : tables) {
			if (table.getName().equals(tableName)) {
				return table;
			}
		}
		return null;
	}

	public void removeTable(String name) {
		int num = -1;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(name)) {
				num = i;
			}
		}
		if (num != -1) {
			tables.remove(num);
		}
	}

	public void save(String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.flush();
		oos.close();
	}

	public static Database load(String filename) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream oin = new ObjectInputStream(fis);
        return (Database) oin.readObject();
	}

	public String[] getTablesNames() {
		List<String> res = new ArrayList<>();
		for (Table tab : tables) {
			res.add(tab.getName());
		}
		return res.toArray(new String[res.size()]);
	}

	public void deleteTableWithName(String selectedItem) {
		int n = -1;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(selectedItem)) {
				n = i;
			}
		}

		tables.remove(n);
	}
}
