package com.example.maver_000.retrorecycler.Helper;

import java.util.StringTokenizer;

/**
 * Created by Ishan Shaurya Jaiswal.
 */
public class GetDate {
    public GetDate(){
    }
    public String get_date(String date){
        StringTokenizer stringTokenizer=new StringTokenizer(date,"-");
        String year=stringTokenizer.nextToken();
        return(get_month(stringTokenizer.nextToken())+" "+year);
    }
    public String get_month(String date){
        String[] month={"January","February","March","April","May","June"
                ,"July","August","September","October","November","December"};
        return month[Integer.parseInt(date)-1];
    }
}
