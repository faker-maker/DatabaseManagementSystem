package ua.kateros.sybd.iiop;

import ua.kateros.sybd.servers.DatabaseRmi;
import ua.kateros.sybd.services.DatabasesService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

/**
 * Created by Masha on 10/11/2015.
 */
public class IIOPServer {
    public static void main(String[] args) {
        System.out.println("RMI IIOP server starting");

        Properties p = System.getProperties();
        p.put("java.naming.factory.initial", "com.sun.jndi.cosnaming.CNCtxFactory");
        p.put("java.naming.provider.url", "iiop://localhost:1050");

        try {
            LocateRegistry.createRegistry(1050);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        try {
            DatabasesService srvnt = new DatabasesService();
            DatabaseRmi stub = (DatabaseRmi) PortableRemoteObject.toStub(srvnt);
            Context ctx = new InitialContext();
            ctx.rebind("DatabaseService", stub);
            System.out.println("DatabaseService_IIOP: Ready...");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
