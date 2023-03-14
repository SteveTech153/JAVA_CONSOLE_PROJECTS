package zoho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main{
    static HashMap<Integer, ArrayList<Integer>> hm;
    public void initObjects(Taxi[] objs){
        for(int i=0;i<objs.length;i++)
            objs[i]=new Taxi();
    }
    public void initHm(){
        hm = new HashMap<>();
        hm.put(1, new ArrayList<>(Arrays.asList(1,2,3,4,5,6)));
        for(int i=1;i<6;i++){
            hm.put(i+1, new ArrayList<>());
        }

    }
    public void book(int customerId, int pickupPoint, int dropPoint, int pickupTime, Taxi[] taxis){
        // CHECHKING IF THERE'S ANY TAXT IN PICKUP POINT THAT'S AVAILABLE
        for(int i=0;i<hm.get(pickupPoint).size(); i++) {
            if (taxis[hm.get(pickupPoint).get(i)].getFreeAfter() <= pickupTime) {
                int taxino = (hm.get(pickupPoint).get(i));
                System.out.println("Taxi " + taxino + " is allotted !");
                taxis[hm.get(pickupPoint).get(i)].setFreeAfter(pickupTime + Math.abs(dropPoint - pickupPoint));
                int taxi = hm.get(pickupPoint).remove(i);
                hm.get(dropPoint).add(taxi);
                Integer[] arr = new Integer[]{customerId, pickupPoint, dropPoint, pickupTime,Math.abs(dropPoint-pickupPoint)*100 };
                taxis[taxino].setTotalEarnings(taxis[taxino].getTotalEarnings()+Math.abs(dropPoint-pickupPoint)*100);
                if(taxis[taxino].getEarnings()==null)
                    taxis[taxino].setEarnings(new ArrayList<>());
                taxis[taxino].addEarning(arr);
                return;
            }
        }
        // CHECHKING FOR AVAILABLE TAXIS AT NEAREST POINTS
        for(int j=pickupPoint-1, k = pickupPoint+1;j>=1&&k<=6;j--,k++){
            for(int i=0;i<hm.get(j).size(); i++) {
                if (taxis[hm.get(j).get(i)].getFreeAfter() <= pickupTime) {
                    System.out.println("Taxi " + (pickupPoint) + " is allotted !");
                    taxis[hm.get(j).get(i)].setFreeAfter(pickupTime + Math.abs(dropPoint - pickupPoint));
                    int taxi = hm.get(j).remove(i);
                    hm.get(dropPoint).add(taxi);
                    return;
                }
            }
            for(int i=0;i<hm.get(k).size(); i++) {
                if (taxis[hm.get(k).get(i)].getFreeAfter() <= pickupTime) {
                    System.out.println("Taxi " + (pickupPoint) + " is allotted !");
                    taxis[hm.get(k).get(i)].setFreeAfter(pickupTime + Math.abs(dropPoint - pickupPoint));
                    int taxi = hm.get(k).remove(i);
                    hm.get(dropPoint).add(taxi);
                    return;
                }
            }
        }
        System.out.println("No taxi is free !");
    }
    public void seeStatus(int taxiNo, Taxi[] taxis){
        System.out.println("Taxi "+taxiNo+" is free after "+taxis[taxiNo].getFreeAfter());
    }
    public void seeEarnings(int taxiNo, Taxi[] taxis){
        taxis[taxiNo].printEarnings();
    }
    public void seeAllstatus(Taxi[] taxis){
        for(int i=1; i<taxis.length; i++){
            System.out.println("Taxi "+i+"| free after "+taxis[i].getFreeAfter()+" | total Earnings "+taxis[i].getTotalEarnings());
        }
    }
    public static void main(String[] args) {
        Scanner S= new Scanner(System.in);
        System.out.println("enter no of taxis ");
        int n = S.nextInt();
        Taxi[] taxis = new Taxi[n+1];
        Main obj = new Main();
        obj.initObjects(taxis);
        obj.initHm();
        int ch = 1;
        System.out.println("");
        boolean flag = true;
        while(flag) {
            System.out.println("");
            System.out.println("Enter choice : ");
            System.out.println("1 -> To book a taxi");
            System.out.println("2 -> To see status of taxis");
            System.out.println("3 -> To see earnings of taxi");
            System.out.println("4 -> See all taxi status");
            System.out.println("5 -> exit");
            System.out.println("");
            ch = S.nextInt();
            switch (ch) {
                case 1:
                    int customerId,pickupPoint, dropPoint, pickupTime;
                    System.out.println("Enter customer id : ");
                    customerId = S.nextInt();
                    System.out.println("Enter pickup point : ");
                    pickupPoint = (int)S.next().charAt(0)-64;
                    System.out.println("Enter drop point : ");
                    dropPoint = (int)S.next().charAt(0)-64;
                    System.out.println("Enter pickup time : ");
                    pickupTime = S.nextInt();
                    System.out.println("pay "+Math.abs(pickupPoint-dropPoint)*100);
                    obj.book(customerId, pickupPoint,dropPoint,pickupTime , taxis);
                    break;
                case 2:
                    System.out.println("Enter taxi number ");
                    int taxiNo = S.nextInt();
                    obj.seeStatus(taxiNo, taxis);
                    break;
                case 3:
                    System.out.println("Enter taxi number ");
                    int taxiNo1 = S.nextInt();
                    obj.seeEarnings(taxiNo1 , taxis);
                    break;
                case 4:
                    obj.seeAllstatus(taxis);
                    break;
                case 5:
                    flag =false;
                    break;
                default:
                    System.out.println("Enter correct choice");
            }
        }
    }
}
