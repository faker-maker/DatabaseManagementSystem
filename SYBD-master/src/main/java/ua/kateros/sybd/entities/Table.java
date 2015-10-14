package ua.kateros.sybd.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {
    private static final long serialVersionUID = 5236655503269561422L;
    private Scheme scheme;
    private List<Record> records;
    private String name;

    public Table(String name) {
        scheme = new Scheme();
        this.name = name;
        records = new ArrayList<>();
    }

    public Table(Scheme scheme, String name) {
        this.scheme = scheme;
        this.name = name;
        records = new ArrayList<>();
    }

    private Table(List<Record> records, Scheme scheme) {
        this.scheme = scheme;
        this.records = records;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public List<Record> getRecords() {
        return records;
    }

    public String getName() {
        return name;
    }

    public Record getRecord(int number) {
        return records.get(number);
    }

    public Record deleteRecord(int number) {
        return records.remove(number);
    }

    public void addRecordFromStrings(String[] values) throws Exception {
        Record recordToAdd = Record.makeFromStrings(values, this.scheme);
        records.add(recordToAdd);
    }

    public Table find(String what) {
        List<Record> result = new ArrayList<>();
        for (Record record : records) {
            if (record.contains(what)) {
                result.add(record);
            }
        }

        return new Table(result, this.scheme);
    }

    public Table removeDuplicates() {
        for (int i = 0; i < records.size(); ++i)
        {
            for (int j = i + 1; j < records.size(); ++j)
            {
                if (records.get(i).equals(records.get(j)))
                {
                    records.remove(j);
                    --j;
                }
            }
        }

        return this;
    }

    private void addRecord(Record record) {
        records.add(record);
    }

    public int size() {
        return records.size();
    }

    public String[] getColumnNames() {
        List<String> names = new ArrayList<>();
        for (Attribute attr : scheme.getAttributes()) {
            names.add(attr.getName());
        }
        String[] res = new String[names.size()];
        names.toArray(res);
        return res;
    }

    public Object[][] getViewRepresentation() {
        Object[][] result = new Object[records.size()][scheme.getAttributes().size()];
        for (int i = 0; i < records.size(); i++) {
            Record rec = records.get(i);
            List<?> values = rec.getValues();
            for (int j = 0; j < scheme.getAttributes().size(); j++) {
                result[i][j] = values.get(j);
            }
        }
        return result;
    }

    public void updateFromView(String[][] tableData) throws Exception {
        List<Record> newRecs = new ArrayList<>();
        for (String[] aTableData : tableData) {
            Record rec = Record.makeFromStrings(aTableData, this.scheme);
            newRecs.add(rec);
        }
        records = newRecs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Table table = (Table) o;

        if (table.getRecords().size() != this.getRecords().size())
            return false;

        if (!table.getScheme().equals(this.getScheme()))
            return false;

        for (int i = 0; i < this.records.size(); i++) {
            if (!this.records.get(i).equals(table.records.get(i))) {
                return false;
            }
        }
        return true;
    }
}
