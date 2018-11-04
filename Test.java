
import java.util.*;
public class Test
{
    public static void main(String args[])throws Exception
    {       
      String  c[]={"0","1","2","3","4","5","6","7","8","9","/","*","+","-"};
    Scanner sc=new Scanner(System.in);
      
     
      System.out.println(" enter sum of operator and operand");
      int p=sc.nextInt();
      gnetc g=new gnetc(p,c);
        System.out.println("enter no.");
        g.zp=sc.nextInt();
        g.evlv();
        g.rps();
}
}
