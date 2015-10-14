package ua.kateros.sybd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import ua.kateros.sybd.entities.Database;
import ua.kateros.sybd.entities.Root;
import ua.kateros.sybd.entities.Table;
import ua.kateros.sybd.servers.DatabaseRmi;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class DatabasesService implements DatabaseRmi, Serializable {
    @Autowired
    private Root root;
    private String name;

    public DatabasesService() {
        root = new Root();
    }

    public DatabasesService(String name) {
        this.name = name;
        root = new Root();
    }

    public Root getRoot() {
        return root;
    }

    @Override
    public void saveDatabase(String name, String path) throws IOException {
        root.saveDatabase(name, path);
    }

    @Override
    public void loadDatabase(String path) throws IOException, ClassNotFoundException {
        root.loadDatabase(path);
    }

    @Override
    public String[] getTableNames(String dbName) throws RemoteException {
        return root.getDatabaseWithName(dbName).getTablesNames();
    }

    @Override
    public void addTable(String dbName, Table table) throws RemoteException {
        root.getDatabaseWithName(dbName).addTable(table);
    }

    @Override
    public void deleteTable(String db, String table) throws RemoteException {
        root.deleteTable(db, table);
    }

    @Override
    public Table getTable(String db, String tableName) throws RemoteException {
        return root.getDatabaseWithName(db).getTableWithName(tableName);
    }

    @Override
    public void updateTable(String db, String tableName, String[][] tableData) throws Exception {
        root.getDatabaseWithName(db).getTableWithName(tableName).updateFromView(tableData);
    }

    @Override
    public Table removeDuplicates(String db, String tableName) throws RemoteException
    {
        return root.getDatabaseWithName(db).getTableWithName(tableName).removeDuplicates();
    }

    @Override
    public Table search(String database, String table, String what) throws RemoteException {
        return root.getDatabaseWithName(database).getTableWithName(table).find(what);
    }

    @Required
    public void setRoot(Root root) {
        this.root = root;
    }

    public List<Database> getDatabases() {
        return root.getDatabases();
    }

    public void addDatabase(Database database) {
        root.addDatabase(database);
    }

    public Database getDatabaseWithName(String db) {
        return root.getDatabaseWithName(db);
    }

    public String[] getDatabaseNames() {
        return root.getDatabaseNames();
    }

    @Override
    public void addDatabase(String databaseName) throws RemoteException {
        root.addDatabase(databaseName);
    }
}
