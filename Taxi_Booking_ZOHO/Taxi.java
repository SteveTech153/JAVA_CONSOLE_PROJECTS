package Taxi_Booking_ZOHO;

import java.util.ArrayList;

public class Taxi {
    private int freeAfter;
    private int currPoint;

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    private int totalEarnings;
    private ArrayList<Integer[]> earnings;

    public int getFreeAfter() {
        return freeAfter;
    }

    public void setFreeAfter(int freeAfter) {
        this.freeAfter = freeAfter;
    }

    public int getCurrPoint() {
        return currPoint;
    }

    public void setCurrPoint(int currPoint) {
        this.currPoint = currPoint;
    }

    public ArrayList<Integer[]> getEarnings() {
        return earnings;
    }

    public void setEarnings(ArrayList<Integer[]> earnings) {
        this.earnings = earnings;
    }
    public void addEarning(Integer[] arr){
        this.earnings.add(arr);
    }
    public void printEarnings(){
        if(this.earnings==null) {System.out.println("No earnings so far "); return;}
        for(int i=0;i<this.earnings.size();i++){
            for(int j =0 ;j< this.earnings.get(0).length; j++){
                if(j==2 || j==3)
                    System.out.print((char)(this.earnings.get(i)[j]+64)+" ");
                else
                    System.out.print(this.earnings.get(i)[j]+" ");
            }
            System.out.println("");
        }
    }

}
