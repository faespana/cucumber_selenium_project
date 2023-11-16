Feature: Mostrar personaje de Star Wars
  Como usuario quiero ingresar el nombre de un personaje de Star Wars en Wikipedia y que se muestre en panatalla su biografia

  Scenario Outline: Consulte el artículo de Wikipedia que se muestra sobre los personajes de Star Wars
    Given Soy un usuario de la página web de Wikipedia y solicito el carácter SW <number>
    When  I search the requested character name on Wikipedia search page <number>
    Then  I should be able to see the article page correctly displayed for the requested character <number>

    Examples:
    | number |
    |   1    |
    #|   2    |
    #|   3    |
    #|   4    |
    #|   5    |

  Scenario: Consulto el articulo de Wikipedia que se muestre sobre las peliculas de Star Wars
    Given Soy un usuario de la página web de Wikipedia y solicito una pelicula de SW
    When  I search the requested random movie name on Wikipedia search page
    Then  I should be able to see the article page correctly displayed for the requested movie
