package ua.kateros.sybd.gui;

import ua.kateros.sybd.entities.Attribute;
import ua.kateros.sybd.entities.Scheme;
import ua.kateros.sybd.entities.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Masha on 9/28/2015.
 */
public class EditAttributeFrame extends JFrame implements ActionListener {
    private Table table;
    private int attribIndex;
    private JLabel renameFromLbl;
    private JTextField oldName;
    private JLabel renameToLbl;
    private JTextField newName;
    private JButton okBtn;

    public EditAttributeFrame(Table table, int attribIndex) {
        this.table = table;
        this.attribIndex = attribIndex;
        init();
    }

    public void init() {
        setBounds(455, 165, 250, 200);
        getContentPane().setLayout(null);

        renameFromLbl = new JLabel("Rename from:");
        renameFromLbl.setBounds(10, 6, 96, 14);
        getContentPane().add(renameFromLbl);

        oldName = new JTextField();
        oldName.setText(table.getColumnNames()[attribIndex]);
        oldName.setBounds(10, 25, 202, 20);
        getContentPane().add(oldName);
        oldName.setColumns(10);
        oldName.setEnabled(false);

        renameToLbl = new JLabel("To:");
        renameToLbl.setBounds(10, 50, 46, 14);
        getContentPane().add(renameToLbl);

        newName = new JTextField();
        newName.setUI(new JTextFieldHintUI("Input name here", Color.gray));
        newName.setBounds(10, 69, 202, 20);
        getContentPane().add(newName);
        newName.setColumns(10);

        okBtn = new JButton("OK");
        okBtn.setBounds(10, 105, 89, 23);
        getContentPane().add(okBtn);
        okBtn.addActionListener(this);


        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object src = actionEvent.getSource();
        if (src == okBtn) {
            Attribute attr = null;

            String attributeName = newName.getText();
            Scheme s = table.getScheme();
            if (attributeName.isEmpty() || s.getAttributesNames().contains(attributeName))
            {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Attribute name is empty or already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            s.getAttribute(attribIndex).setName(attributeName);
            table.setScheme(s);

            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Attribute renamed", "Info", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
}
