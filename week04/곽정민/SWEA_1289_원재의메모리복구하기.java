package week04.곽정민;
import java.io.*;
import java.util.*;


public class SWEA_1289_원재의메모리복구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        int test_case = Integer.parseInt(br.readLine());
        for(int tc = 0; tc<test_case; tc++) {
            StringBuilder sb = new StringBuilder();
          String bits = br.readLine();
          boolean toggle = false;
          int count = 0;
          for(int ch = 0; ch<bits.length(); ch++) {
            
            char c = toggle == false ? '0' : '1'; 

            if(bits.charAt(ch) != c ){
                count++;
                toggle = !toggle;
            }

          }


          sb.append("#" + (tc+1) + " "+count);
          System.out.println(sb);
          
          count = 0;
          }


        }
    
        
    }
