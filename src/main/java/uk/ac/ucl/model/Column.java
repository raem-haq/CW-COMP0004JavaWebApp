package uk.ac.ucl.model;

import java.util.*;

public class Column {
    private String name;
    private ArrayList<String> rows;


    public Column(String name, ArrayList<String> rows){
        this.name = name;
        this.rows = rows;
    }

    public String getName(){
        return name;
    }
    
    public int getSize(){
        return rows.size();
    }

    public String getRowValue(int index){
        return rows.get(index);
    }

    public void setRowValue(int index, String value){
        rows.set(index, value);
    }

    public void addRowValue(String value){
        rows.add(value);
    }

    public ArrayList<String> getRows() { return rows;}

    // For non-unique values e.g., ethnicity
    public ArrayList<Integer> findAllValueIndex(String searchString){
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < getSize(); i ++){
            if (getRowValue(i).equals(searchString)){
                indices.add(i);
            }
        }
        return indices;
    }

    public int getLastIndex() { return rows.size() - 1;}
    // For unique values e.g., ID
    public int findFirstValueIndex(String searchString){
        for (int i = 0; i < getSize(); i ++){
            if (getRowValue(i).equals(searchString)){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> sort(Boolean asc){
        ArrayList<ColTuple> toSort = new ArrayList<>();
        for (int i = 0; i < getSize(); i++){
            toSort.add(new ColTuple(i, getRowValue(i)));
        }
        Collections.sort(toSort);
        if (!asc) {
            Collections.reverse(toSort);
        }
        //ArrayList<String> newRows = new ArrayList<>();
        ArrayList<Integer> newIndexMap = new ArrayList<>();

        for (ColTuple t : toSort){
            newIndexMap.add(t.num);
            //newRows.add(t.str);
        }
        //rows = newRows;
        return  newIndexMap;
    }

}
