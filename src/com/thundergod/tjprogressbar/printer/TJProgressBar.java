package com.thundergod.tjprogressbar.printer;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TJProgressBar {
    int i = 0;
    private String URL;
    private double start;
    private int total;
    private Class c;
    private long byte_for_Download;

    private  int is;

    public static void main(String[] args) {
        TJProgressBar printer = new TJProgressBar();
        printer.getProgressBar(0,100,null,null,null,1);
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setClass(Class c) {
        this.c = c;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private  String progressBar(int start, int total, Class s, String url_source,long byte_for_download){
        byte_for_Download = byte_for_download;
        if (url_source==null){
            if(URL==null){
                URL="non-url-download";
            }
            else{
                url_source=URL;
            }
        }
        if (s==null){
            if (c==null){
            c= empty_class.class;
            }
            s=c;
        }
        if (total==0){
            if (this.total<0){
            this.total = 0;
            }
            total=this.total;
        }
        if (start==0){
            if (this.start<0){
            this.start=0;
            }
            start = (int) this.start;
        }
/*
        if (start>total){
        throw new RuntimeException("start val bigger than total val.");
        }

 */
        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy/MM/dd/hh/mm/ss]");
        Date date = new Date();
        dateFormat.format(date);
        //start = start*total/100; don't use if you don't want a fully printed 100.
        StringBuilder  start_pattern =new StringBuilder(100);
        char empty = '-';
        char fill = '#';

        char left_bracket = '[';
        char right_bracket = ']';

        for (int i = 0; i < 10; i++) {
            start_pattern.insert(i,empty);
        }
        //0
        if (start>=10*total/100){
            start_pattern.setCharAt(10/(10/1)*0/1,fill);
        }
        //1
        if (start>=20*total/100){
            start_pattern.setCharAt(20/(20/2)*1/2,fill);
        }
        //2
        if (start>=30*total/100){
            start_pattern.setCharAt(30/(30/3)*2/3,fill);
        }
        //3
        if (start>=40*total/100){
            start_pattern.setCharAt((40/(40/4))*3/4,fill);
        }
        //4
        if (start>=50*total/100){
            start_pattern.setCharAt((50/(50/5))*4/5,fill);
        }
        //5
        if (start>=60*total/100){
            start_pattern.setCharAt((60/(60/6))*5/6,fill);
        }
        //6
        if (start>=70*total/100){
            start_pattern.setCharAt((70/(70/7))*6/7,fill);
        }
        //7
        if (start>=80*total/100){
            start_pattern.setCharAt((80/(80/8))*7/8,fill);
        }
        //8
        if (start>=90*total/100){
            start_pattern.setCharAt((90/(90/9))*8/9,fill);
        }
        //9

        if (start==100*total/100){
            start_pattern.setCharAt((100/(100/10))*9/10,fill);
            i++;
            is = i;
            sys_out_new("\n"+i+"# "+"download finished");
            sys_out_new("total byte download **> "+total+" B");
            sys_out_new("TOTAL! total byte download **> "+total*i);
            sys_out_new(";::LOG::;");
        }
        int gk =total-start;
        if (gk<byte_for_download){
            byte_for_download = gk;
        }
        if (start>total){
            start = total;
        }


        return left_bracket+start_pattern.toString()+right_bracket+start*100/total+"%"+" downloaded-->:"+start+" waiting-->"+(total-start)+" "+dateFormat.format(date)+" "+total;



    }
    public void getProgressBar(){
       String source ="";
        empty_class emptyClass = new empty_class();
        if (URL==null){
            URL="non-url-download";
        }
        if (c==null){
            source = emptyClass.toString();
        }else {
            source = c.toString();
        }
        if (start<0){
            start =0;
        }
        if (total<0){
            total =0;
        }
        if (start>total){
            throw new RuntimeException("start val bigger than total val.");
        }
        sys_out_new(" Source::"+"["+source+"] /"
                +"  URL::"+URL);
        for (int i = (int) start; i < total; ) {
            i=i+1;
            sys_out(progressBar(i,total,c,URL,1));
        }
    }
    public  void getProgressBar(int start,int total,Class s,String url_Source_nullable,String file_name_nullable,int byte_for_Download){
        clear();
        String  source = "";
        empty_class e = new empty_class();
        if (url_Source_nullable==null){
            if (URL==null){
                url_Source_nullable="non-url-download";
            }
            else {
                url_Source_nullable=URL;
            }
        }
        if (s==null){
            source=e.toString();
        }
        else {
            source=s.toString();
        }
        sys_out_new(" Source::"+"["+source+"] /"
                +"  URL::"+url_Source_nullable);

        for ( int i=start;i <= total; ) {
            i=i+byte_for_Download;
            sys_out(progressBar(i,  total,s,url_Source_nullable,byte_for_Download));
        }
        String finalUrl_Source_nullable = url_Source_nullable;
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {

                sys_out_new("\nSHUT_DOWN_HOOK/* TOTAL BYTE DOWNLOAD - "+byte_for_Download*is+"/* FILE-"+file_name_nullable+"/* URL-"+ finalUrl_Source_nullable);


            }
        });

    }
    private void sys_out(String s){
        wait_sc(500);
        clear();
        System.out.print(s);
    }
    private void sys_out_new (String s){
        System.out.println(s);
    }
    private void clear(){
        System.out.print("\r");
    }
    private void wait_sc(int milli_sc){
        try {
            Thread.sleep(milli_sc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void exit(int exit_code){
          System.exit(exit_code);

    }
}
class empty_class{
    @Override
    public String toString() {
        return "please enter your class to know where this file installing.";
    }
}
