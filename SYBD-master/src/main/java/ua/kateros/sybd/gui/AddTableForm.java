package ua.kateros.sybd.gui;

import ua.kateros.sybd.entities.Database;
import ua.kateros.sybd.servers.DatabaseController;

public class AddTableForm {
	private TableFrame tableFrame;

	public AddTableForm(DatabaseController db, String dbName) {
		tableFrame = new TableFrame(db, dbName);
		tableFrame.setVisible(true);
	}

    public AddTableForm(DatabaseController db, String dbName, String tableName) {
        tableFrame = new TableFrame(db, dbName, tableName);
        tableFrame.setVisible(true);
    }
}
