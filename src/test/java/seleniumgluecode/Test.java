package seleniumgluecode;

import static io.restassured.RestAssured.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Test {

    private ChromeDriver driver;

    Random random = new Random();
    int rangoInferior = 2;
    int rangoSuperior = 6;
    private final int numeroAleatorio = random.nextInt(rangoSuperior - rangoInferior + 1) + rangoInferior;

    public static String convertirANumeralRomano(int numero) {
        String[] simbolos = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] valores = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

        StringBuilder resultado = new StringBuilder();

        for (int i = valores.length - 1; i >= 0; i--) {
            while (numero >= valores[i]) {
                numero -= valores[i];
                resultado.append(simbolos[i]);
            }
        }
        return resultado.toString();
    }



    @Given("^Soy un usuario de la página web de Wikipedia y solicito el carácter SW (.*)$")
    public void soy_un_usuario_de_la_página_web_de_Wikipedia_y_solicito_el_carácter_SW_number (int arg1) throws Throwable {

        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();

    }

    @When("^I search the requested character name on Wikipedia search page (.*)$")
    public void i_search_the_requested_character_name_on_Wikipedia_search_page_number(int arg1) throws Throwable {

        Response response;
        response = given().get("https://swapi.dev/api/people/" + arg1);
        String name = response.jsonPath().get("name");

        driver.findElement(By.id("searchInput")).sendKeys(name);
        driver.findElement(By.id("searchInput")).sendKeys(Keys.ENTER);
    }

    @Then("^I should be able to see the article page correctly displayed for the requested character (.*)$")
    public void i_should_be_able_to_see_the_article_page_correctly_displayed_for_the_requested_character(int arg1) throws Throwable {
        Response response;
        response = given().get("https://swapi.dev/api/people/" + arg1);
        String name = response.jsonPath().get("name");

        WebElement pageTitleLocator = driver.findElement(By.className("mw-page-title-main"));
        Assert.assertTrue("No se redireccionó correctmente a la pagina", pageTitleLocator.isDisplayed());
        Assert.assertEquals(name,pageTitleLocator.getText());
        driver.quit();

    }

    @Given("^Soy un usuario de la página web de Wikipedia y solicito una pelicula de SW$")
    public void soy_un_usuario_de_la_página_web_de_Wikipedia_y_solicito_una_pelicula_de_SW() throws Throwable {

        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();

    }

    @When("^I search the requested random movie name on Wikipedia search page$")
    public void i_search_the_requested_random_movie_name_on_Wikipedia_search_page() throws Throwable {

        Response response;
        response = given().get("https://swapi.dev/api/films/" + numeroAleatorio);
        String title = response.jsonPath().get("title");

        driver.findElement(By.id("searchInput")).sendKeys(title);
        driver.findElement(By.id("searchInput")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("ca-edit")).click();

    }

    @Then("^I should be able to see the article page correctly displayed for the requested movie$")
    public void i_should_be_able_to_see_the_article_page_correctly_displayed_for_the_requested_movie() throws Throwable {

        Response response;
        response = given().get("https://swapi.dev/api/films/" + numeroAleatorio);
        String title = response.jsonPath().get("title");

        WebElement pageTitleLocator = driver.findElement(By.className("mw-first-heading"));
        Assert.assertTrue("No se redireccionó correctmente a la pagina", pageTitleLocator.isDisplayed());

        Assert.assertEquals("Editing Star Wars: Episode " + convertirANumeralRomano(numeroAleatorio - 3) + " – " + title,pageTitleLocator.getText());
        driver.quit();
        //"Star Wars: Episode " + convertirANumeralRomano(numeroAleatorio) + "–" +
        //View source for The Empire Strikes Back
        //View source for Return of the Jedi
        //View source for Star Wars: Episode II – Attack of the Clones

    }
}
