package ua.kateros.sybd.entities;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Root {
	private List<Database> databases;

	public Root(List<Database> databases) {
		this.databases = databases;
	}

	public Root() {
		databases = new ArrayList<Database>();
	}

	public List<Database> getDatabases() {
		return databases;
	}

	public void addDatabase(Database database) {
		for (Database db : databases) {
			if (db.getName().equals(database.getName())) {
				return;
			}
		}
		databases.add(database);
	}

    public void addDatabase(String databaseName) {
        for (Database db : databases) {
            if (db.getName().equals(databaseName)) {
                return;
            }
        }
        databases.add(new Database(databaseName));
    }

	public String[] getDatabaseNames() {
		List<String> names = new ArrayList<>();
		for (Database db : this.databases) {
			names.add(db.getName());
		}
		String[] res = new String[names.size()];
		return names.toArray(res);
	}

	public Database getDatabaseWithName(String dbName) {
		for (Database db : databases) {
			if (db.getName().equals(dbName)) {
				return db;
			}
		}
		return null;
	}

    public void saveDatabase(String name, String path) throws IOException
    {
        for (Database db : this.databases) {
            if (db.getName().equals(name))
            {
                db.save(path);
                return;
            }
        }
    }

    public void loadDatabase(String path) throws IOException, ClassNotFoundException
    {
        Database database = Database.load(path);
        databases.add(database);
    }

    public void deleteTable(String dbName, String table) throws RemoteException {
        for (int i = 0; i < databases.size(); ++i)
        {
            if (databases.get(i).getName().equals(dbName))
            {
                databases.get(i).deleteTableWithName(table);
                return;
            }
        }
    }
}
