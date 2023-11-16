# cucumber_selenium_project 🥒

In this project, we used the concepts of java 🤎 + Selenium 💚 + Rest Assured 🖤 + Cucumber 🥒.

✅ We started by creating a maven java project and include the dependencies used in order to work with Java, TestNG, and Selenium. Additionally, Add the cucumber dependency and configure cucumber 
into your framework. 

🤓 Our team began by creating some scenarios. Remember, a scenario is the same as a Test Case, but in BDD Syntax and created the following scenarios into your framework:

1. Construct a Scenario Outline, to request the Star Wars API (https://swapi.dev/) on it’s /people/ service (example https://swapi.dev/api/people/1) , get the name of the character, search for it
on wikipedia web page , and make the needed assertions to check if the character page is displayed correctly.

   -Scenario Outline: Check wikipedia article displayed for Star Wars Characters Given I am an user at the Wikipedia WebPage requesting SW character <number> When I search the requested character name
   on Wikipedia search page Then I should be able to see the article page correctly displayed for the requested character.
  -Examples |1| |2| |3| |4| |5| The examples should cover the first 5 characters available on the characters service.

3. Form a scenario to request for a random movie movie using theStar Wars API (Consider only 6 movies are available on the API services, and create a method to request one of them randomly) ,
search for this movie on wikipedia web page, go to the article page and click on the Edit Link. Check the edit page is displayed correctly, including the matching of the title for the article page.
  
  - Example, if the page is for “The empire strikes back, the title of the Edit page should include “Edición de The Empire Strikes Back”
