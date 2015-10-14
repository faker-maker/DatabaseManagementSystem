package ua.kateros.sybd.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kateros.sybd.entities.Attribute;
import ua.kateros.sybd.entities.Database;
import ua.kateros.sybd.entities.Scheme;
import ua.kateros.sybd.entities.Table;
import ua.kateros.sybd.servers.DatabaseController;
import ua.kateros.sybd.servers.DatabaseRmi;
import ua.kateros.sybd.services.DatabasesService;

@Component
public class MyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -1012606853805352154L;
	private JTextField dbPath;
	private JTable tableView;
	private JTextField dbNameInput;
	private JTextField searchInput;
    @Autowired
    private DatabaseController databasesService;
	//private Root root = new Root();
	private JComboBox<String> databaseBox;
	private JButton addDb;
	private JButton addTable;
	private JButton delTable;
	private JButton saveDb;
	private JButton loadDb;
	private JButton addRecord;
	private JButton saveChanges;
	private JComboBox tableBox;
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane;
	private DefaultTableModel defTableModel;
	private JButton removeDuplicates;
	private JButton search;

    private JLabel lblDbName;
    private JLabel lblTable;

    private JComboBox attributeBox;
    private JButton addAttribute;
    private JButton delAttribute;
    private JLabel lblAttribute;
    private JButton editAttribute;

    public MyFrame() {
		init();
	}

    public DatabaseController getDatabasesService() {
        return databasesService;
    }

    public void setDatabasesService(DatabaseController databasesService) {
        this.databasesService = databasesService;
    }

    public void init() {
		setBounds(100, 100, 960, 535);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		databaseBox = new JComboBox<>();
		databaseBox.setBounds(10, 45, 129, 20);
		getContentPane().add(databaseBox);
		databaseBox.addActionListener(this);

		addDb = new JButton("Add DB");
		addDb.setBounds(10, 129, 129, 23);
		getContentPane().add(addDb);
		addDb.addActionListener(this);

		saveDb = new JButton("Save DB");
		saveDb.setBounds(10, 194, 129, 23);
		getContentPane().add(saveDb);
		saveDb.addActionListener(this);

		loadDb = new JButton("Load DB");
		loadDb.setBounds(10, 229, 129, 23);
		getContentPane().add(loadDb);
		loadDb.addActionListener(this);

		dbPath = new JTextField();
        dbPath.setUI(new JTextFieldHintUI("Input path here", Color.gray));
		dbPath.setBounds(10, 163, 129, 20);
		getContentPane().add(dbPath);
		dbPath.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				dbPath.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});

		tableBox = new JComboBox();
		tableBox.setBounds(173, 45, 129, 20);
		getContentPane().add(tableBox);
		tableBox.addActionListener(this);

		addTable = new JButton("Add table");
		addTable.setBounds(173, 76, 129, 23);
		getContentPane().add(addTable);
		addTable.addActionListener(this);

		delTable = new JButton("Delete table");
		delTable.setBounds(173, 126, 129, 23);
		getContentPane().add(delTable);
		delTable.addActionListener(this);

		tableView = new JTable();
		tableView.setBounds(348, 222, 512, -173);
		getContentPane().add(tableView);

		dbNameInput = new JTextField();
        dbNameInput.setUI(new JTextFieldHintUI("Input name here", Color.gray));
        dbNameInput.setBounds(10, 98, 129, 20);
		getContentPane().add(dbNameInput);
		dbNameInput.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				dbNameInput.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});

		addRecord = new JButton("Add record");
		addRecord.setBounds(340, 350, 120, 23);
		getContentPane().add(addRecord);
		addRecord.addActionListener(this);

		saveChanges = new JButton("Save changes");
		saveChanges.setBounds(475, 350, 120, 23);
		getContentPane().add(saveChanges);
		saveChanges.addActionListener(this);

		scrollPane = new JScrollPane();
		scrollPane.setLocation(340, 80);
		scrollPane.setSize(420, 250);
		getContentPane().add(scrollPane);

		removeDuplicates = new JButton("Remove duplicates");
        removeDuplicates.setBounds(610, 350, 150, 23);
		getContentPane().add(removeDuplicates);
        removeDuplicates.addActionListener(this);

		searchInput = new JTextField();
		searchInput.setBounds(340, 393, 307, 23);
		getContentPane().add(searchInput);

		search = new JButton("Search");
		search.setBounds(660, 393, 100, 23);
		getContentPane().add(search);
		search.addActionListener(this);

		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setBounds(10, 20, 86, 14);
		getContentPane().add(lblDatabase);

		lblDbName = new JLabel("Table Contents");
		lblDbName.setBounds(433, 20, 116, 14);
		getContentPane().add(lblDbName);

		lblTable = new JLabel("Table");
		lblTable.setBounds(173, 20, 46, 14);
		getContentPane().add(lblTable);

        attributeBox = new JComboBox();
        attributeBox.setBounds(800, 45, 129, 20);
        getContentPane().add(attributeBox);
        attributeBox.addActionListener(this);

        addAttribute = new JButton("Add attribute");
        addAttribute.setBounds(800, 76, 129, 23);
        getContentPane().add(addAttribute);
        addAttribute.addActionListener(this);

        delAttribute = new JButton("Delete attribute");
        delAttribute.setBounds(800, 126, 129, 23);
        getContentPane().add(delAttribute);
        delAttribute.addActionListener(this);

        editAttribute = new JButton("Edit attribute");
        editAttribute.setBounds(800, 176, 129, 23);
        getContentPane().add(editAttribute);
        editAttribute.addActionListener(this);

        lblAttribute = new JLabel("Attributes");
        lblAttribute.setBounds(800, 20, 76, 14);
        getContentPane().add(lblAttribute);

        setDBInfoVisible(false);
        setTableInfoVisible(false);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == databaseBox) {
			String db = (String) databaseBox.getSelectedItem();
            String[] tables = new String[0];
            try {
                tables = databasesService.getDatabaseWithName(db).getTablesNames();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            tableBox.setModel(new DefaultComboBoxModel<String>(tables));
			repaint();
		} else if (src == addDb) {
			String name = dbNameInput.getText();

            try {
                if (name.isEmpty() || Arrays.asList(databasesService.getDatabaseNames()).contains(name))
                {
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Database name is empty or already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

            databasesService.addDatabase(new Database(name));

            try {
                databaseBox.setModel(new DefaultComboBoxModel<>(databasesService
                        .getDatabaseNames()));
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            this.repaint();
            setDBInfoVisible(true);
		} else if (src == saveDb) {
			String path = dbPath.getText();
			String db = (String) databaseBox.getSelectedItem();
            Database database = null;
            try {
                database = databasesService.getDatabaseWithName(db);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            try {
                databasesService.saveDatabase(db, path);
				//database.save(path);
			} catch (Exception e1) {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Could not save to this path!", "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
				return;
			}

            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Database successfully saved", "Info", JOptionPane.INFORMATION_MESSAGE);
		} else if (src == loadDb) {
			String path = dbPath.getText();

			try {
				Database database = Database.load(path);
				databasesService.addDatabase(database);
                setDBInfoVisible(true);
            } catch (Exception e1) {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Could not load from this path!", "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
				return;
			}

            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Database successfully loaded", "Info", JOptionPane.INFORMATION_MESSAGE);
            try {
                databaseBox.setModel(new DefaultComboBoxModel<>(databasesService
                        .getDatabaseNames()));
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            this.repaint();
		} else if (src == addTable) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						String db = (String) databaseBox.getSelectedItem();
						Database database = databasesService.getDatabaseWithName(db);
						AddTableForm addTableForm = new AddTableForm(databasesService, database.getName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else if (src == delTable) {
            Database db = null;
            try {
                db = databasesService.getDatabaseWithName((String) databaseBox
                        .getSelectedItem());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            try {
                databasesService.deleteTable(db.getName(), (String) tableBox.getSelectedItem());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            String[] tables = new String[0];
            try {
                tables = databasesService.getDatabaseWithName(db.getName()).getTablesNames();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            tableBox.setModel(new DefaultComboBoxModel<String>(tables));
			repaint();
            setTableInfoVisible(false);
        } else if (src == tableBox) {
			String db = (String) databaseBox.getSelectedItem();
			String tableName = (String) tableBox.getSelectedItem();
            Table table = null;
            try {
                table = databasesService.getDatabaseWithName(db).getTableWithName(
                        tableName);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            searchInput.setText("");
            setTableInfoVisible(true);
            prepareForRepaint(table);

            String[] attributes = table.getColumnNames();
            attributeBox.setModel(new DefaultComboBoxModel<String>(attributes));

			repaint();
		} else if (src == addRecord) {
			defTableModel.addRow(new Object[] { null });
		} else if (src == saveChanges) {
			int nRow = tableView.getRowCount();
			int nCol = tableView.getColumnCount();
			String[][] tableData = new String[nRow][nCol];

            try {
                for (int i = 0; i < nRow; i++) {
                    for (int j = 0; j < nCol; j++) {
                        tableData[i][j] = tableView.getValueAt(i, j).toString();
                    }
                }
            } catch (Exception ex) {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

			String db = (String) databaseBox.getSelectedItem();
			String tableName = (String) tableBox.getSelectedItem();
            Table table = null;
            try {
                table = databasesService.getDatabaseWithName(db).getTableWithName(
                        tableName);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            try {
                databasesService.updateTable(db, tableName, tableData);
				//table.updateFromView(tableData);
			} catch (Exception ex) {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Could not update", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Data updated", "Info", JOptionPane.INFORMATION_MESSAGE);
			repaint();
		} else if (src == removeDuplicates) {
            String db = (String) databaseBox.getSelectedItem();
            String tableName = (String) tableBox.getSelectedItem();
            Table table = null;
            try {
                table = databasesService.getDatabaseWithName(db).getTableWithName(
                        tableName);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

            Table result = null;

            try {
                result = databasesService.removeDuplicates(db, tableName);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            //table.removeDuplicates();
            prepareForRepaint(result);
            repaint();
		} else if (src == search) {
            String searchStr = searchInput.getText();

            if (searchStr.isEmpty())
            {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Search input should not be empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

            String db = (String) databaseBox.getSelectedItem();
			String tableName = (String) tableBox.getSelectedItem();
            Table table = null;
            try {
                table = databasesService.getDatabaseWithName(db).getTableWithName(
                        tableName);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            Table result = null; //table.find(searchStr);
            try {
                result = databasesService.search(db, tableName, searchStr);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            prepareForRepaint(result);
			repaint();
		} else if (src == delAttribute) {
            Database db = null;
            try {
                db = databasesService.getDatabaseWithName((String) databaseBox
                        .getSelectedItem());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            Table t = db.getTableWithName((String) tableBox.getSelectedItem());
            Scheme s = t.getScheme();
            s.removeAttribute(s.getAttribute(attributeBox.getSelectedIndex()));

            t.setScheme(s);

            List<String> attr = t.getScheme().getAttributesNames();
            attributeBox.setModel(new DefaultComboBoxModel<String>(attr.toArray(new String[attr.size()])));
            repaint();
        } else if (src == addAttribute) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        String db = (String) databaseBox.getSelectedItem();
                        Database database = databasesService.getDatabaseWithName(db);
                        AddTableForm addTableForm = new AddTableForm(databasesService, database.getName(), tableBox.getSelectedItem().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (src == editAttribute) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        String db = (String) databaseBox.getSelectedItem();
                        Database database = databasesService.getDatabaseWithName(db);
                        Table t = database.getTableWithName(tableBox.getSelectedItem().toString());
                        EditAttributeForm editAttributeForm = new EditAttributeForm(t, tableBox.getSelectedIndex());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
	}

    private void prepareForRepaint(Table table)
    {
        String[] columnNames = table.getColumnNames();
        Object[][] data = table.getViewRepresentation();
        JTable oldTable = tableView;
        defTableModel = new DefaultTableModel(data, columnNames);
        tableView = new JTable(defTableModel);

        List<Attribute> l = table.getScheme().getAttributes();
        for (int i = 0; i < l.size(); ++i)
        {
            if (l.get(i).getClazz().equals(ua.kateros.sybd.types.Enumeration.class))
            {
                TableColumn enumColumn = tableView.getColumnModel().getColumn(i);
                JComboBox comboBox = new JComboBox();

                List<String> args = l.get(i).getArgs();
                for (String arg : args) {
                    comboBox.addItem(arg);
                }

                enumColumn.setCellEditor(new DefaultCellEditor(comboBox));
            }
        }

        scrollPane.getViewport().remove(oldTable);
        scrollPane.getViewport().add(tableView);
    }

    private void setDBInfoVisible(boolean isVisible)
    {
        tableBox.setVisible(isVisible);
        addTable.setVisible(isVisible);
        delTable.setVisible(isVisible);
        lblTable.setVisible(isVisible);
    }

    private void setTableInfoVisible(boolean isVisible)
    {
        tableView.setVisible(isVisible);
        addRecord.setVisible(isVisible);
        saveChanges.setVisible(isVisible);
        scrollPane.setVisible(isVisible);
        removeDuplicates.setVisible(isVisible);
        searchInput.setVisible(isVisible);
        search.setVisible(isVisible);
        lblDbName.setVisible(isVisible);
        attributeBox.setVisible(isVisible);
        addAttribute.setVisible(isVisible);
        delAttribute.setVisible(isVisible);
        lblAttribute.setVisible(isVisible);
        editAttribute.setVisible(isVisible);
    }
}
