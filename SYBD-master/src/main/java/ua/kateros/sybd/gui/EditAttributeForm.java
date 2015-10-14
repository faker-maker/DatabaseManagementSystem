package ua.kateros.sybd.gui;

import ua.kateros.sybd.entities.Table;

/**
 * Created by Masha on 9/28/2015.
 */
public class EditAttributeForm {
    private EditAttributeFrame editAttribFrame;

    public EditAttributeForm(Table table, int attribIndex) {
        editAttribFrame = new EditAttributeFrame(table, attribIndex);
        editAttribFrame.setVisible(true);
    }
}
