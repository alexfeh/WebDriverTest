import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WebDriverTest {

    List<String> links = new ArrayList<String>();

    String pw = "";


    List<String> randomLinks = new ArrayList<String>();

    @Before
    public void setUp(){

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("C:\\Users\\Gaming\\Documents\\links.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                links.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void visitListOfLinks(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://seleniumhq.org");

        for(int i = 0; i < links.size(); i++){
            driver.get(links.get(i));
            System.out.println(links.get(i));
        }

        /*WebElement downloadTab = driver.findElement(By.id("menu_download"));
        WebElement downloadLink = downloadTab.findElement(By.tagName("a"));
        downloadLink.click();

        Assert.assertEquals("Downloads", driver.getTitle());*/

        driver.quit();
    }

    @Test
    public void loginHotmail(){
        WebDriver driver = new FirefoxDriver();

        driver.get("http://live.de");
        driver.findElement(By.id("i0116")).sendKeys("feh.alex@live.de");
        driver.findElement(By.id("i0118")).sendKeys(pw);
        driver.findElement(By.id("idSIButton9")).submit();

       //driver.quit();
    }

    @Test
    public void loginGoogle(){
        WebDriver driver = new FirefoxDriver();

        driver.get("https://accounts.google.com/servicelogin?service=local&hl=en#identifier");
        driver.findElement(By.id("Email")).sendKeys("alexandra.fehkuehrer@gmail.com");
        driver.get("https://accounts.google.com/servicelogin?service=local&hl=en#password");
        //driver.findElement(By.id("next")).submit();
        try {
            // random number
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("Passwd")).sendKeys(pw);
        driver.findElement(By.id("signIn")).submit();

        //driver.quit();
    }

    @Test
    public void visitRandomWebsites(){
        WebDriver driver = new FirefoxDriver();

        for(int i = 0; i < 5; i++) {
            driver.get("http://www.randomwebsite.com/cgi-bin/random.pl");
            // http://randomyoutube.net/watch#
            // REST,
            Random random = new Random();

            int number = random.nextInt(600 - 100 + 1) + 100;

            try {
                Thread.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(driver.getCurrentUrl());
            randomLinks.add(driver.getCurrentUrl());
        }

        FileWriter writer = null;

        try {

        writer = new FileWriter("output.txt");
        for(String str : randomLinks) {

            writer.write(str);
            writer.write("\n");

        }
        writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    @Test
    public void getInterests(){
        WebDriver driver = new FirefoxDriver();

        driver.get("https://accounts.google.com/servicelogin?service=local&hl=en#identifier");
        driver.findElement(By.id("Email")).sendKeys("alexandra.fehkuehrer@gmail.com");
        driver.get("https://accounts.google.com/servicelogin?service=local&hl=en#password");
        //driver.findElement(By.id("next")).submit();
        try {
            // random number
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("Passwd")).sendKeys(pw);
        driver.findElement(By.id("signIn")).submit();

        driver.get("https://www.google.com/settings/u/0/ads/authenticated?hl=de");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(driver.findElement(By.className("vr")).getText());
/*
        FileWriter writer = null;

        try {

            writer = new FileWriter("interests.txt");
            for(String str : randomLinks) {

                writer.write(str);
                writer.write("\n");

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();*/
    }
}
