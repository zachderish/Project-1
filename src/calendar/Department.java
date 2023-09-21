package calendar;

public enum Department{

    CS, EE, MATH, ITI, BAIT;

    public static void main(String[] args){
        Department test = Department.CS;
        Department test1 = Department.EE;
        System.out.print(test.compareTo(test1));
    }

}