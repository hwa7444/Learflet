package pythonread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pythonRun2 {
	public String id;
   public static void main(String[] args) throws IOException, InterruptedException {
      
	   
	   
      
      
      

	}

public void run() {
	
	
    StringBuffer cmd = new StringBuffer();
    StringBuffer pout = new StringBuffer();
    
    cmd.append("python C:\\zz.py");
    cmd.append(" ");
    cmd.append(" ");
    cmd.append(id);
    
    System.out.println(cmd.toString());
    
    try {
       
       System.out.println("java start");
       Runtime r = Runtime.getRuntime();
       System.out.println("런타임 객체 생성 완료");
       Process p = r.exec(cmd.toString());
       System.out.println("cmd에 넣기");
       int resultSign = -1;
       System.out.println("resultSing"+resultSign);
       try{
    	   System.out.println("resultsign돈다");
          //resultSign = p.waitFor();
          System.out.println("resultsign돈다2");
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
	//values('%s','%s','%s','%s','%s','%s')" %(scorelist[0][0],scorelist[1][0],scorelist[2][0],scorelist[3][0],scorelist[4][0],scorelist[5][0]

	
}

}