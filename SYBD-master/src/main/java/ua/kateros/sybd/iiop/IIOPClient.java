package ua.kateros.sybd.iiop;

import ua.kateros.sybd.gui.MainForm;
import ua.kateros.sybd.gui.MyFrame;
import ua.kateros.sybd.servers.DatabaseController;
import ua.kateros.sybd.servers.DatabaseRmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 * Created by Masha on 10/11/2015.
 */
public class IIOPClient {
    public static void main(String[] args) {
        MyFrame frame;
        DatabaseRmi rmi;
        try {
            Context ic = new InitialContext();
            Object objref = ic.lookup("DatabaseService");
            DatabaseRmi dbrmi = (DatabaseRmi) PortableRemoteObject.narrow(objref, DatabaseRmi.class);
            frame = new MyFrame();
            frame.setDatabasesService(new DatabaseController(dbrmi));
            MainForm mf = new MainForm(frame);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
