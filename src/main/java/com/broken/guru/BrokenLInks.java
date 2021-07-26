package com.broken.guru;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BrokenLInks {
	/*How to find Broken Links on a web page using Selenium?

1. We will use WebDriver to connect to the desired website.
2. We need to find all the webelements by the tagname a and store them in a list of web elements.
3. We will then iterator over the web elements using an Iterator.
4. While there is another web element (it.next()) then we will perform our operation to find broken links.
5. We can capture the href of each anchor tag using (it.next().getAttribute("href")).
6. We will cast this href to an HTTPUrlConnection Class.
7. Then we will set a request method.
8. After that we will try to connect using the href and capture the response code.
9. if the response code is above 400 then it is a broken link.
10. else the link is valid.*/
	 private static WebDriver driver = null;
     public static void main(String[] args) {
	        String homePage = "http://www.osaconsultingtech.com/"; //http://www.zlti.com
	        String url = "";
	        HttpURLConnection huc = null;
	        int respCode = 200;
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        
	        driver.manage().window().maximize();
	        
	      
	        driver.get(homePage); //http://www.zlti.com
	        
	        List<WebElement> links = driver.findElements(By.tagName("a"));
	       // List<WebElement> links = driver.findElements(By.linkText(""));
	       System.out.println("links.size()  = "+links.size()); 
	       
	        System.out.println("******************************************************");
	        Iterator<WebElement> it = links.iterator();
	        
	        while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");//getText();
	            
	           // System.out.println(url);
	        
	            if(url == null || url.isEmpty()){
	System.out.println(url+"  URL is either not configured for anchor tag or it is empty  "+driver.getWindowHandle()+"  \n"+driver.getTitle());
	                continue;
	            }
	            
	            if(!url.startsWith(homePage)){
	                System.out.println("URL belongs to another domain, skipping it.");
	                continue;
	            }
	            
	            try {
	            	//HttpURLConnection huc = null;
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	               
	                huc.setRequestMethod("HEAD");
	                
	                huc.connect();
	                
	                respCode = huc.getResponseCode();
	                
	                if(respCode >= 400){
	                    System.out.println(url+" is a broken link");
	                }
	                else{
	                    System.out.println(url+" is a valid link");
	                }
	                    
	            } catch (MalformedURLException e) {
	                
	                e.printStackTrace();
	            } catch (Exception e) {
	                
	                e.printStackTrace();
	            }
	        }
	        
	        driver.quit();

	    }
	}


