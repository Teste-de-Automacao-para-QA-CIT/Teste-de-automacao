
import java.io.File;
import java.util.stream.Stream;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*

Cenário Gherkin:

 */

public class SegundaParte extends TestCase {

    public void teste() {

        String category = "Science: Computers", tipo = "category"; // Variáveis para pesquisa 

        File file = new File("C:\\Users\\Seidor\\Desktop\\Projetos java\\chromedriver.exe"); //Coloque o caminho para o seu chromedrive
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()); //Especifica o driver de navegação
        WebDriver driver = new ChromeDriver(); //Iniciando driver de navegação

        driver.get("https://opentdb.com/browse.php");// Link para busca 
        driver.findElement(By.id("type")).sendKeys(tipo);//Seleciona o elemento de seleção do tipo de pesquisa pelo ID e define um favor
        driver.findElement(By.id("query")).sendKeys(category);//Seleciona o elemento de input de pesquisa pelo ID e preenche ele
        driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[1]/form/div/button")).click();//Seleciona o botão pelo caminho xpath

        WebElement lista = driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[2]/table/tbody")); //Cria um WebElement e joga a listagem de questões nele através do caminho xpath
        String texto = lista.getText(); //Cria uma String e pega o conteudo da listagem em forma de texto
        Stream<String> lines = texto.lines(); //Utilizando o método lines() para processar a string separadamente
        long quantidadeLinha = lines.count(); //Pegando a quantidade de linhas da listagem de questões
        assertEquals(quantidadeLinha, 25); //Comparando a quantidade de linhas encontradas com o valor esperado
        
        WebElement controlePaginacao = driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[2]/center/ul")); //Cria um WebElement e joga o controle de paginação nele através do caminho xpath
        boolean validacaoEncontrada = controlePaginacao.isDisplayed(); // Verifica se o controle de paginação está aparecendo
        assertEquals(validacaoEncontrada, true); //Comparando a validação do controle de paginação com o valor esperado
        
        driver.close();//Fechar o driver após a execução das atividades

    }

}
