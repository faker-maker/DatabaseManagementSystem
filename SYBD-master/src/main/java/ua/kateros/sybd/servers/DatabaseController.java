package ua.kateros.sybd.servers;

import org.springframework.beans.factory.annotation.Required;
import ua.kateros.sybd.entities.Database;
import ua.kateros.sybd.entities.Root;
import ua.kateros.sybd.entities.Table;
import ua.kateros.sybd.services.DatabasesService;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Masha on 10/11/2015.
 */
public class DatabaseController {
    protected DatabaseRmi controller;

    public DatabaseController() {
        this.controller = new DatabasesService();
    }

    public DatabaseController(DatabaseRmi controller) {
        this.controller = controller;
    }

    public void addDatabase(Database database) {
        try {
            controller.addDatabase(database);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Database getDatabaseWithName(String db) throws RemoteException {
        return controller.getDatabaseWithName(db);
    }

    public String[] getDatabaseNames() throws RemoteException {
        return controller.getDatabaseNames();
    }

    public void addDatabase(String databaseName) throws RemoteException {
        controller.addDatabase(databaseName);
    }

    public void addTable(String dbName, Table table) throws RemoteException {
        controller.addTable(dbName, table);
    }

    public void updateTable(String db, String tableName, String[][] tableData) throws Exception {
        controller.updateTable(db, tableName, tableData);
    }

    public void saveDatabase(String name, String path) throws IOException {
        controller.saveDatabase(name, path);
    }

    public void deleteTable(String db, String table) throws RemoteException {
        controller.getDatabaseWithName(db).deleteTableWithName(table);
    }

    public Table removeDuplicates(String db, String tableName) throws RemoteException
    {
        return controller.getDatabaseWithName(db).getTableWithName(tableName).removeDuplicates();
    }

    public Table search(String database, String table, String what) throws RemoteException {
        return controller.getDatabaseWithName(database).getTableWithName(table).find(what);
    }
}
