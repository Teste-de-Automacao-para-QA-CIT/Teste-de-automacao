
import java.io.File;
import java.util.stream.Stream;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/*

Cenário Gherkin:

Funcionalidade: Cadastro no Open trivia data base
Cenário: Cadastramento no Open trivia  
Dado que eu esteja no site 
E clique em login 
E clique em register 
Quando preencho os campos 
E clique em register 
Então visualizo uma mensagem com o texto ‘You have been registered. Please verify your email address before logging in.’

 */

public class TerceiraParte extends TestCase {

    public void teste() {

        File file = new File("C:\\Users\\Seidor\\Desktop\\Projetos java\\chromedriver.exe"); 
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()); 

        WebDriver driver = new ChromeDriver(); 

        driver.get("https://opentdb.com/browse.php");
        
        driver.findElement(By.partialLinkText("/login")).click();

        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("register.")));

        driver.findElement(By.partialLinkText("register.")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        driver.findElement(By.id("username")).sendKeys(TerceiraParte.generateRandomString(14, true));

        String password = TerceiraParte.generateRandomString(12, false);

        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password_again")).sendKeys(password);

        driver.findElement(By.id("email")).sendKeys(TerceiraParte.generateRandomEmailAddress());

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Success')]")));

        String text = driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[1]/div")).getText();

        assertEquals(text, "SUCCESS! You have been registered. Please verify your email address before logging in.");

        driver.close();
    }

    private static String generateRandomString(int numOfCharacters, boolean onlyLetters){
      String characteres = "ABCDEFGHIJKLMNOPQRSTUVXWYZ0123456789";
      String stringGenerated = "" ;

      Random generator = new Random();

      for(int i = 0; i < numOfCharacters; i++){

        if(onlyLetters){
          stringGenerated += characteres.charAt(generator.nextInt((characteres.length() - 11)));
        }else {
          stringGenerated += characteres.charAt(generator.nextInt((characteres.length() - 1)));
        }
      }

      return stringGenerated;
    }

    private static String generateRandomEmailAddress(){
      String emailPrefix = TerceiraParte.generateRandomString(8, true);

      return emailPrefix += "@test.com.br";
    }

}
