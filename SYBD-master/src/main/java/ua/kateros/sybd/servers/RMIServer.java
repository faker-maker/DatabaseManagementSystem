package ua.kateros.sybd.servers;

import ua.kateros.sybd.entities.Root;
import ua.kateros.sybd.services.DatabasesService;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Masha on 10/10/2015.
 */
public class RMIServer {
    public static void main(String[] args) throws Exception {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        DatabaseRmi server = new DatabasesService("DatabaseService");
        server = (DatabaseRmi) UnicastRemoteObject.exportObject(server, 0);
        Naming.rebind("//localhost/RmiJrmpServer", server);
        System.out.println( "RMI DatabaseServer ready..." );
    }

}
