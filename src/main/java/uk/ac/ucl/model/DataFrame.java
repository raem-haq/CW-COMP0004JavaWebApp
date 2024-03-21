package uk.ac.ucl.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class DataFrame {
    private ArrayList<Integer> indexMapping = new ArrayList<>();
    // ^specifies the ordering of the records so that the table can be sorted without needing to swap records

    private HashMap<String, Column> columns = new HashMap<>();

    public void addColumn(String name) {
        columns.put(name, new Column(name, new ArrayList<String>()));
    }

    public Column getColumn(String name) {
        return columns.get(name);
    }

    public List<String> getColumnNames() {
        return new ArrayList<String>(columns.keySet());
    }

    public int getRowCount() {
        return columns.get(getColumnNames().getFirst()).getSize();
    }

    public String getValue(String columnName, int rowIndex) {
        rowIndex = indexMapping.get(rowIndex);
        return columns.get(columnName).getRowValue(rowIndex); // assumes first column has the largest size
    }


    public void putValue(String columnName, int rowIndex, String value) {
        rowIndex=indexMapping.get(rowIndex);
        columns.get(columnName).setRowValue(rowIndex, value);
    }

    public List<String> getRecord(Integer index){
        index = indexMapping.get(index);
        List<String> record = new ArrayList<>();
        for (String name : getColumnNames()){
            record.add(getColumn(name).getRowValue(index));
        }
        return record;
    }

    // assumes new records are entered from the first column
    // true of this program - see DataLoader
    public void addValue(String columnName, String value) {
        columns.get(columnName).addRowValue(value);
        if (indexMapping.size() !=columns.get(columnName).getLastIndex() + 1 ) { indexMapping.add(columns.get(columnName).getLastIndex());}
    }

    public List<List<String>> basicSearchFor(String searchString){
        List<List<String>> records = new ArrayList<>();
        for (String name: getColumnNames()){
            List<Integer> indices = getColumn(name).findAllValueIndex(searchString);
            if (!indices.isEmpty()) {
                for (int i : indices) {
                    records.add(getRecord(i));
                }
            }
        }
        return records;
    }

    public List<List<String>> searchBy(String searchString, String field){
        List<Integer> indices = getColumn(field).findAllValueIndex(searchString);
        List<List<String>> results = new ArrayList<>();
        for (int i : indices){
            results.add(getRecord(i));
        }
        return results;
    }

    public List<String> searchID(String ID){
        int i = getColumn("ID").findFirstValueIndex(ID);
        return i == -1 ? new ArrayList<String>() : getRecord(i);
    }

    public void sortBy(String field, Boolean asc){
        indexMapping = new ArrayList<Integer>(getColumn(field).sort(asc));
    }

    public List<List<String>> getAll(){
        List<List<String>> all = new ArrayList<>();
        for (int i = 0; i< getRowCount(); i ++){
            all.add(getRecord(i));
        }
        return all;
    }
    
}
