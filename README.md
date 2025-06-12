# My First Spring Boot Project

---

<aside>
🙌🏾 Hello coder!

It's time for you to create your first CRUD API. In SpringBoot, you'll create a text-making machine. You can choose from three themes: phrases that inspire you on a daily basis, phrases that are jokes, or Lorem Ipsum phrases.

</aside>

## 🏆 Objectives

- Develop sufficient knowledge to use SpringBoot, using a 3-tier MVC architectural pattern, client-server style, APIRest-like.

## 🔧 Technical Skills

- SpringBoot Fundamentals
- Software Architecture Fundamentals

## 🛠️ Tools

- Intellij
- Java 21
- SpringBoot
- MySQL
- SQL Workbench
- Jira

## 🪜 Instructions

This exercise is about using SpringBoot. You must create the file with Spring Initializr and then add it to the remote repository.

## Functional Requirements

- Phrases must have, at a minimum, the text and the author.
- You must be able to view a list of all phrases.
- You must be able to add a new phrase.
- You must be able to edit an existing phrase.
- You must be able to delete a phrase.
- You must be able to view a phrase by ID.

## Non-Functional Requirements

- You must use Spring Boot for the API.
- You must store the phrases in a MySQL database.
- You must follow the 3-tier MVC architectural pattern to organize your code.
- Must be tested with Postman


## 🔧 Endpoints
| Endpoint                                                 | Method | Description                          |
|----------------------------------------------------------|--------|--------------------------------------|
| /phrases/                                                | GET    | Returns all phrases                  |
| /phrases/                                                | POST   | Creates a phrase                     |
| /phrases/{id}                                            | GET    | Gets one phrase by id                |
| /phrases/{id}                                            | DELETE | Deletes one phrase by id             |
| /phrases/{id}                                            | UPDATE | Updates the phrase (in construction) |
| /phrases/order?orderDirection={0}&orderCategory={author} | GET    | Gets all phrases in order            |
| /phrases/filter?auhor={}                                 | GET    | Gets filtered phrases                |
| /phrases/search?searchText={}                            | GET    | Search in all fields of a phrase     |
| /topics/                                                 | GET    | Returns all topics                   |

## 🤝 Authors
- [Iris Sánchez](https://github.com/isanort)