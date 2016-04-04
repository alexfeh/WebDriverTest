import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WebDriverInterests {

    public static void main(String[] args){

        //String pw = "e6a5f0cbea8b11cee0716ef6a781f291";
        String pw = "WTF!ilol123";
        //String account = "neinpanik@gmail.com";
        String account = "klarata.hamada@gmail.com";
        //String account = "lemana.jendiua@gmail.com";
        //String account = "lemana.jendiua@gmail.com";


        List<String> randomLinks = new ArrayList<String>();
        List<String> links = new ArrayList<String>();
        List<String> interests = new ArrayList<String>();

        WebDriver driver = new FirefoxDriver();

        driver.get("https://accounts.google.com/servicelogin?service=local&hl=en#identifier");
        driver.findElement(By.id("Email")).sendKeys(account);
        driver.get("https://accounts.google.com/servicelogin?service=local&hl=en#password");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("Passwd")).sendKeys(pw);
        driver.findElement(By.id("signIn")).submit();

        // Get links form txt-file

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("C:\\Users\\Alexandra\\Documents\\links.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FileWriter writer = null;

        // Visit pages from txt-file
        Random random = new Random();
        int number = 0;
        /*
        for(int i = 0; i < links.size(); i++){
            driver.get(links.get(i));

            number = random.nextInt(3000 - 2000 + 1) + 2000;
            System.out.println(number);

            try {
                Thread.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < 500; i++) {

            System.out.println(i);
            try {
                driver.get("http://www.randomwebsite.com/cgi-bin/random.pl");
            }catch(Exception ex){

            }
            // http://randomyoutube.net/watch#
            // REST,

            try {
                Thread.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(driver.getCurrentUrl());
            randomLinks.add(driver.getCurrentUrl());
        }



        try {

            writer = new FileWriter("output.txt");
            for(String str : randomLinks) {

                writer.write(str);
                writer.write("\n");

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // Visit pages from random youtube site

        for(int i = 0; i < 500; i++){
            driver.get("http://randomyoutube.net/watch#");
            driver.navigate().refresh();
            System.out.println(i);
            number = random.nextInt(3000 - 2000 + 1) + 2000;
            System.out.println(number);

            try {
                Thread.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Get interests from google

        driver.get("https://www.google.com/settings/u/0/ads/authenticated?hl=de");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // class name = "Tu"

        try {
            List<WebElement> listOfElements = driver.findElements(By.className("Tu"));

            for(WebElement we : listOfElements) {
                interests.add(we.getText());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        // Write interests into output.txt

        try {

            writer = new FileWriter(account + "interests.txt");
            for(String str : interests) {

                writer.write(str);
                writer.write("\n");

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //driver.quit();
    }
}
