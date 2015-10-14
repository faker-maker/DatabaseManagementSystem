package ua.kateros.sybd.servers;

import ua.kateros.sybd.gui.MainForm;
import ua.kateros.sybd.gui.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Created by Masha on 10/10/2015.
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            final DatabaseRmi rmi = (DatabaseRmi) Naming.lookup("//localhost/RmiJrmpServer");

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MyFrame frame;
                    frame = new MyFrame();
                    frame.setDatabasesService(new DatabaseController(rmi));
                    frame.setVisible(true);
                }
            });
        }
        catch( Exception e ) {
            System.out.println( e );
        }
    }

}
