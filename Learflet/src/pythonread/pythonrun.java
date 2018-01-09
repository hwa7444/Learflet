package pythonread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pythonrun {
	   static String python35 = "‪C:\\Users\\Byungwoo\\AppData\\Local\\Programs\\Python\\Python35\\python.exe";//파이썬 경로 지정해주면됨
	   
	   public static void main(String args[]) throws IOException {
	      
	   }
	 
		   
		   
	      
	public void run() {
		// TODO Auto-generated method stub
		try {
	         Runtime r = Runtime.getRuntime();
	         Process p = r.exec(
	               "cmd /c python C://zz.py byungwoo");
	               
	         BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));

	         
	         System.out.println(".........start   process.........");
	         String line = "";
	         while ((line = bfr.readLine()) != null) {
	            System.out.println("Python Output: " + line);
	         }
	         System.out.println("........end   process.......");

	      } catch (Exception e) {
	         System.out.println(e);
	      }
	   }
	}
	