package com.vo;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.schedule(new Timer1(),0,2000);
        Scanner scanner=new Scanner(System.in);
        System.out.println("按回车停止");
        scanner.nextLine();
        timer.cancel();
    }
}
class Timer1 extends TimerTask{
    String[] str={"其实没什么感觉","反正这个世界上","没有谁能够一直陪着谁","我也不太需要"};
    @Override
    public void run(){
        int ran=(int)(0+Math.random()*4);
        System.out.println("随机数为："+str[ran]+"----"+ran);
    }
}
