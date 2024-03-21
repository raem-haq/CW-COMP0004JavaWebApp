package uk.ac.ucl.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ColTuple implements Comparable<ColTuple> {
    public int num;
    public String str;

    public ColTuple(int num, String str){
        this.num = num;
        this.str = str;
    }

    @Override
    public int compareTo(ColTuple tup){
        try {
            DateFormat df = DateFormat.getDateInstance();
            Date myDate = df.parse(this.str);
            Date otherDate = df.parse(tup.str);
            return myDate.compareTo(otherDate);
        } catch (ParseException e){
            return this.str.compareTo(tup.str);
        }
    }
}
