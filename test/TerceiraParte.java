
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

        File file = new File("C:\\Users\\Seidor\\Desktop\\Projetos java\\chromedriver.exe"); //Caminho para o chromedriver
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()); //Especifica o driver de navegação

        WebDriver driver = new ChromeDriver(); //Cria uma instância do ChromeDriver

        driver.get("https://opentdb.com/"); //Abre o site da URL
        
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[5]/a")).click(); //Acha e clica no botão de login

        WebDriverWait wait = new WebDriverWait(driver, 30); //Cria uma instância de WebDriverWait

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-top\"]/div[2]/form/div[4]/a"))); //Espera até que o botão "Register" esteja visível na página

        driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[2]/form/div[4]/a")).click(); //Acha e clica no botão "Register"

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))); //Espera até que o input "Username" esteja visível na página

        driver.findElement(By.id("username")).sendKeys(TerceiraParte.generateRandomString(14, true)); // Cria uma String de randômica de 14 caracteres contendo somente letras e o preenche o input com ela

        String password = TerceiraParte.generateRandomString(12, false); // Cria uma String de randômica de 12 caracteres contendo letras e números

        driver.findElement(By.id("password")).sendKeys(password); // Insere a senha no campo "password"
        driver.findElement(By.id("password_again")).sendKeys(password); // Insere a senha no campo "password_again"

        driver.findElement(By.id("email")).sendKeys(TerceiraParte.generateRandomEmailAddress()); // Cria uma String de randômica de 20 caracteres contendo somente letras, para representar um e-mail e preenche o input com ela

        driver.findElement(By.cssSelector("button[type='submit']")).click(); // Acha e clica no primeiro botão do tipo 'submit' que encontrar. 

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Success')]"))); // Espera até que um elemento com a frase 'Success' esteja visível

        String text = driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[1]/div")).getText(); // Recupera o texto presente no pop-up de resposta

        assertEquals(text, "SUCCESS! You have been registered. Please verify your email address before logging in."); // Testa se o texto de resposta é o texto esperado

        driver.close(); // Encerra a conexão com o driver
    }

    private static String generateRandomString(int numOfCharacters, boolean onlyLetters){
      String characteres = "ABCDEFGHIJKLMNOPQRSTUVXWYZ0123456789"; // Lista de caracteres
      String stringGenerated = "" ;

      Random generator = new Random(); // Cria uma instância de Random

      for(int i = 0; i < numOfCharacters; i++) {  

        if(onlyLetters){ 
          stringGenerated += characteres.charAt(generator.nextInt((characteres.length() - 11))); // Se a flag onlyLetters for true, limita a busca de caracteres até a letra Z
        }else {
          stringGenerated += characteres.charAt(generator.nextInt((characteres.length() - 1))); // Busca por caracter aleatório
        }
      }

      return stringGenerated;
    }

    private static String generateRandomEmailAddress(){
      String emailPrefix = TerceiraParte.generateRandomString(8, true); // Gera uma sequência aleatória de caracters contendo somente letras

      return emailPrefix += "@test.com.br"; // Anexa o prefixo a um domíino de e-mail e retorna o valor
    }

}
