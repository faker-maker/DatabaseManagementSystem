package ua.kateros.sybd.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.*;

import javax.swing.*;

import ua.kateros.sybd.entities.*;
import ua.kateros.sybd.servers.DatabaseController;
import ua.kateros.sybd.types.*;

public class TableFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -7644150117632623108L;
	private DatabaseController db;
    private String dbName;
	private JTextField nameField;
	private JList<String> attrList;
	private JComboBox<String> typeBox;
	private Map<String, Class> classTypeMap;
	private JButton addAttributeBtn;
	private JButton okBtn;
	private Scheme scheme = new Scheme();
	private JTextField tableNameField;
	private JScrollPane scrollPane;
	private DefaultListModel<String> model = new DefaultListModel<>();

    private JTextField enumValueField;
    private JList<String> enumValuesList;
    private JButton addEnumValueBtn;
    private JScrollPane enumScrollPane;
    private DefaultListModel<String> enumModel = new DefaultListModel<>();
    private JLabel lblEnumValueName;

    public TableFrame(DatabaseController db, String dbName) {
		initializeTable(db, dbName);
	}

    public TableFrame(DatabaseController db, String dbName, String tableName) {
        initializeTable(db, dbName);
        tableNameField.setText(tableName);
        tableNameField.setEnabled(false);
    }

    private void initializeTable(DatabaseController db, String dbName)
    {
        this.db = db;
        this.dbName = dbName;
        classTypeMap = new HashMap<>();
        classTypeMap.put("Integer", Int.class);
        classTypeMap.put("Real", Real.class);
        classTypeMap.put("Char", Char.class);
        classTypeMap.put("Text file", String.class);
        classTypeMap.put("IntegerInterval", IntegerInterval.class);
        classTypeMap.put("Enumeration", ua.kateros.sybd.types.Enumeration.class);
        init();
    }

	public void init() {
		setBounds(100, 100, 943, 535);
		getContentPane().setLayout(null);

		nameField = new JTextField();
        nameField.setUI(new JTextFieldHintUI("Input attribute name here", Color.gray));
        nameField.setBounds(10, 31, 202, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		attrList = new JList<>(model);
		scrollPane = new JScrollPane(attrList,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setLocation(340, 80);
		scrollPane.setSize(220, 250);
		getContentPane().add(scrollPane);

		typeBox = new JComboBox<>();
		typeBox.setBounds(10, 86, 202, 20);
		getContentPane().add(typeBox);
		typeBox.setModel(new DefaultComboBoxModel<>(classTypeMap.keySet()
				.toArray(new String[classTypeMap.size()])));
        typeBox.addActionListener(this);

		addAttributeBtn = new JButton("Add");
		addAttributeBtn.setBounds(10, 133, 202, 23);
		getContentPane().add(addAttributeBtn);
		addAttributeBtn.addActionListener(this);

		okBtn = new JButton("OK");
		okBtn.setBounds(506, 350, 89, 23);
		getContentPane().add(okBtn);
		okBtn.addActionListener(this);

		tableNameField = new JTextField();
        tableNameField.setUI(new JTextFieldHintUI("Input table name here", Color.gray));
        tableNameField.setBounds(337, 31, 214, 20);
		getContentPane().add(tableNameField);
		tableNameField.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 6, 46, 14);
		getContentPane().add(lblName);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 62, 46, 14);
		getContentPane().add(lblType);

		JLabel lblTableName = new JLabel("Table name");
		lblTableName.setBounds(413, 6, 138, 14);
		getContentPane().add(lblTableName);

        enumValueField = new JTextField();
        enumValueField.setUI(new JTextFieldHintUI("Input enum value here", Color.gray));
        enumValueField.setBounds(664, 31, 214, 20);
        getContentPane().add(enumValueField);
        enumValueField.setColumns(10);

        enumValuesList = new JList<>(enumModel);
        enumScrollPane = new JScrollPane(enumValuesList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        enumScrollPane.setLocation(664, 110);
        enumScrollPane.setSize(220, 220);
        getContentPane().add(enumScrollPane);

        addEnumValueBtn = new JButton("Add Enumeration value");
        addEnumValueBtn.setBounds(664, 70, 214, 23);
        getContentPane().add(addEnumValueBtn);
        addEnumValueBtn.addActionListener(this);

        lblEnumValueName = new JLabel("Enumeration value name");
        lblEnumValueName.setBounds(695, 6, 206, 14);
        getContentPane().add(lblEnumValueName);

        setEnumerationVisible(false);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == addAttributeBtn) {
            Attribute attr = null;

            String attributeName = nameField.getText();
            if (attributeName.isEmpty() || scheme.getAttributesNames().contains(attributeName))
            {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Attribute name is empty or already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (typeBox.getSelectedItem().toString().equals("Enumeration"))
            {
                if (enumModel.isEmpty())
                {
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Enumeration can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Set<String> args = new HashSet<>();

                    for (int i = 0; i < enumModel.getSize(); i++)
                        args.add(enumModel.get(i));

                    attr = new Attribute(attributeName,
                            classTypeMap.get(typeBox.getSelectedItem().toString()),
                            new ArrayList<>(args));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else {
                attr = new Attribute(attributeName,
                        classTypeMap.get(typeBox.getSelectedItem().toString()));
            }

			scheme.addAttribute(attr);
			String str = attr.toString();
			model.addElement(str);
		} else if (src == okBtn) {
            String tableName = tableNameField.getText();
            if (tableName.isEmpty())
            {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Table name can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (Arrays.asList(db.getDatabaseWithName(dbName).getTablesNames()).contains(tableName))
                {
                    Table t = db.getDatabaseWithName(dbName).getTableWithName(tableName);
                    Scheme s = t.getScheme();

                    for(Attribute a : scheme.getAttributes()) {
                        s.addAttribute(a);

                        for (Record r : t.getRecords())
                        {
                            Constructor constructor = null;

                            try {
                                constructor = a.getClazz().getDeclaredConstructor();
                            } catch (NoSuchMethodException e1) {
                                e1.printStackTrace();
                            }

                            try {
                                String value;

                                if (typeBox.getSelectedItem().toString().equals("Enumeration"))
                                {
                                    value = a.getArgs().get(0).toString();
                                }
                                else
                                {
                                    value = constructor.newInstance().toString();
                                }

                                r.addValue(value, a);
                            } catch (InstantiationException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                            } catch (NoSuchMethodException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    t.setScheme(s);
                }
                else {
                    Table table = new Table(scheme, tableName);
                    db.addTable(dbName, table);
                }
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Table added", "Info", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else if (src == addEnumValueBtn) {
            String str = enumValueField.getText();
            enumModel.addElement(str);
        }
        else if (src == typeBox)
        {
            enumValueField.setText("");
            enumModel.clear();

            if (typeBox.getSelectedItem().toString().equals("Enumeration"))
                setEnumerationVisible(true);
            else
                setEnumerationVisible(false);
        }
	}

    private void setEnumerationVisible(boolean isVisible)
    {
        enumValueField.setVisible(isVisible);
        lblEnumValueName.setVisible(isVisible);
        addEnumValueBtn.setVisible(isVisible);
        enumScrollPane.setVisible(isVisible);
        enumValuesList.setVisible(isVisible);
    }
}
