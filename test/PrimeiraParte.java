
import java.io.File;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*

Cenário Gherkin:

Funcionalidade: Busca no Banco de Questões
Cenário: Busca por questão inexistente
Dado que navego para a página de busca do banco de questões
E digito 'Science: Computers' no campo de busca
Quando clico no botão de buscar
Então visualizo uma mensagem de erro com o texto 'No questions found.'

*/

public class PrimeiraParte extends TestCase {

    public void teste() {

        String question = "Science: Computers"; // Variável para pesquisa 

        File file = new File("C:\\Users\\David\\Downloads\\chromedriver.exe"); //Coloque o caminho para o seu chromedrive
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()); //Especifica o driver de navegação (utilizado: Google Chrome)
        WebDriver driver = new ChromeDriver(); //Iniciando driver de navegação
        
        driver.get("https://opentdb.com/browse.php");// Link para busca 
        driver.findElement(By.id("query")).sendKeys(question);//O driver irá selecionar a label de pesquisa pelo ID e preenche ela
        driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[1]/form/div/button")).click();//Selecionar botão pelo caminho xpath

        String texto = driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[2]/div")).getText(); // Pega o resultado da pesquisa e coloca em uma string
        assertEquals(texto, "No questions found."); // Compara o resultado obtido com o resultado esperado
        
        driver.close();//Fechar o driver após a execução das atividades e teste realizado com sucesso
 
    }

}
