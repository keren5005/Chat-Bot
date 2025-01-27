# Chatbot Project

This project is a chatbot application that integrates with various APIs. Currently, it includes a feature for fetching Chuck Norris jokes based on user queries. 

## Features

### Chuck Norris Jokes
The chatbot can fetch jokes from the Chuck Norris API, allowing users to search for jokes by keyword.

- **API Used:** [Chuck Norris Jokes API](https://api.chucknorris.io/)
- **Endpoint:** `/bot/jokes`
- **Method:** GET
- **Usage:** Users can send a request with a keyword to get a list of Chuck Norris jokes related to that keyword.

Example response:
```json
{
  "id": "12345",
  "value": "Chuck Norris can divide by zero."
}
```

## Setup Instructions

1. Clone this repository.
2. Ensure you have Java 11 or higher and Docker installed.
3. Build and run the project using Docker:
   ```bash
   docker build -t chatbot .
   docker run -p 8080:8080 chatbot
   ```

## Usage

Once the project is running, you can test the jokes endpoint via a REST client like Postman or directly via Swagger.

## Deployment URL

The project is deployed and can be accessed at: [Swagger UI](https://keren-chatbot.runmydocker-app.com/swagger-ui.html)

## Example Request

To get jokes, send a GET request to:
```
https://keren-chatbot.runmydocker-app.com/bot/jokes?keyword=funny
```

