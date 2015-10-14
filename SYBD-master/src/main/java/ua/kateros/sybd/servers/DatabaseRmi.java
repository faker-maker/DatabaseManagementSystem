package ua.kateros.sybd.servers;

import ua.kateros.sybd.entities.Database;
import ua.kateros.sybd.entities.Root;
import ua.kateros.sybd.entities.Table;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Masha on 10/10/2015.
 */
public interface DatabaseRmi extends Remote {
    public Database getDatabaseWithName(String db) throws RemoteException;

    public String[] getDatabaseNames() throws RemoteException;

    public void addDatabase(String databaseName) throws RemoteException;

    public void addDatabase(Database database) throws RemoteException;

    public void setRoot(Root root) throws RemoteException;

    public Root getRoot() throws RemoteException;

    public void saveDatabase(String name, String path) throws IOException;

    public void loadDatabase(String path) throws IOException, ClassNotFoundException;

    public String[] getTableNames(String dbName) throws RemoteException;

    public void addTable(String dbName, Table table) throws RemoteException;

    public void deleteTable(String db, String table) throws RemoteException;

    public Table getTable(String db, String tableName) throws RemoteException;

    public void updateTable(String db, String tableName, String[][] tableData) throws Exception;

    public Table removeDuplicates(String db, String tableName) throws RemoteException;

    public Table search(String database, String table, String what) throws RemoteException;

}
