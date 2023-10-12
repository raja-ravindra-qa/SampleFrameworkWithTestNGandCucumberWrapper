package frame;

import org.testng.annotations.Test;

import java.util.Arrays;

public class array {

    @Test
    public void test1(){
        String in="my name is ravindra, working in beehyv software solutions @5.9 years of experience";
        String cha="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String[] inpArr=in.split("");

        for(String s:inpArr){

            if(cha.contains(s)){
                System.out.println(s);
            }
        }

           }

    @Test
    public void test2(){
        String str1 = "listen";
        String str2 = "silent";

        boolean rslt= anagramTest(str1, str2);
        System.out.println(rslt);

    }

    private static boolean anagramTest(String str1, String str2) {
        int le= str1.length();
        int le1= str2.length();

        if (le!=le1){
            return false;
        }
        int start=0;
        int end=le-1;
        for(int i=0; i<le;i++){

if(str1.charAt(start)!= str2.charAt(end)){
    return false;
}
start+=1;end-=1;
        }
        return true;
    }
}
