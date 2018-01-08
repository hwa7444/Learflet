package pythonread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pythonRun2 {
   public static void main(String[] args) throws IOException, InterruptedException {
      
	   
	   
      String id = "byungwoo";
      
      StringBuffer cmd = new StringBuffer();
      StringBuffer pout = new StringBuffer();
      
      cmd.append("python C:\\zz.py");
      cmd.append(" ");
      cmd.append(id);
      
      System.out.println(cmd.toString());
      
      try {
         
         System.out.println("java start");
         Runtime r = Runtime.getRuntime();
         Process p = r.exec(cmd.toString());
         
         int resultSign = -1;
            
         try{
            resultSign = p.waitFor();
         }catch(Exception e) {
            System.out.println(e);
         }
         if(resultSign == 0) {
            
            System.out.println("Python operation success");
            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line = "";
            while((line = bfr.readLine()) != null) {
               pout.append(line);
               pout.append("\n");
            }
            
            bfr.close();
            
            System.out.println(pout.toString());
         } else {
            System.out.println(resultSign);
         }
         
         System.out.println("java end");
      } catch (Exception e) {
         System.out.println(e);
      }
   }

}